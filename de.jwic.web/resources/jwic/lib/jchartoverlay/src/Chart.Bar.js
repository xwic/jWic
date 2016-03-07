(function() {
    "use strict";

    var root = this,
        Chart = root.Chart,
        helpers = Chart.helpers;


    var defaultConfig = {
        //Function - Whether the current x-axis label should be filtered out, takes in current label and 
        //index, return true to filter out the label return false to keep the label
        labelsFilter: function(label, index) {
            return false;
        },


        //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
        scaleBeginAtZero: true,

        //Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines: true,

        //String - Colour of the grid lines
        scaleGridLineColor: "rgba(0,0,0,.05)",

        //Number - Width of the grid lines
        scaleGridLineWidth: 1,

        //Boolean - Whether to show horizontal lines (except X axis)
        scaleShowHorizontalLines: true,

        //Boolean - Whether to show vertical lines (except Y axis)
        scaleShowVerticalLines: true,

        //Boolean - If there is a stroke on each bar
        barShowStroke: true,

        //Number - Pixel width of the bar stroke
        barStrokeWidth: 2,

        //Number - Spacing between each of the X value sets
        barValueSpacing: 5,

        //Number - Spacing between data sets within X values
        barDatasetSpacing: 1,

        //Boolean - bars are overlayed behind each other (smallest at front)
        overlayBars: false,

        //Number - length of labels being displayed on graph, 0 represents full length
        labelLength: 0,

        //String - A legend template
        legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",

        //Array - specific yAxis details
        yAxes: [],

        //Boolean - set default yAxis on the left of chart
        scalePositionLeft: true,

    };


    Chart.Type.extend({
        name: "Bar",
        defaults: defaultConfig,
        initialize: function(data) {
            //Expose options as a scope variable here so we can access it in the ScaleClass
            var options = this.options;
            this.ScaleClass = Chart.Scale.extend({
                offsetGridLines: true,
                calculateBarX: function(datasetCount, datasetIndex, barIndex, overlayBars) {

                    if (overlayBars) {
                        datasetIndex = 0;
                    }
                    //Reusable method for calculating the xPosition of a given bar based on datasetIndex & width of the bar
                    var xWidth = this.calculateBaseWidth(),
                        xAbsolute = this.calculateX(barIndex) - (xWidth / 2),
                        barWidth = this.calculateBarWidth(datasetCount, overlayBars);

                    return xAbsolute + (barWidth * datasetIndex) + (datasetIndex * options.barDatasetSpacing) + barWidth / 2;
                },
                calculateBaseWidth: function() {
                    return (this.calculateX(1) - this.calculateX(0)) - (2 * options.barValueSpacing);
                },
                calculateBarWidth: function(datasetCount, overlayBars, bar, yAxesGroupCount) {
                    if (overlayBars) {
                        datasetCount = 1;
                    }
                    //The padding between datasets is to the right of each bar, providing that there are more than 1 dataset
                    var baseWidth = this.calculateBaseWidth() - ((datasetCount - 1) * options.barDatasetSpacing);
                    return (baseWidth / datasetCount);
                }
            });

            this.datasets = [];
            this.yAxes = data.yAxes;
            //Set up tooltip events on the chart
            if (this.options.showTooltips) {
                helpers.bindEvents(this, this.options.tooltipEvents, function(evt) {
                    var activeBars = (evt.type !== 'mouseout') ? this.getBarsAtEvent(evt) : [];

                    this.eachBars(function(bar) {
                        bar.restore(['fillColor', 'strokeColor']);
                    });
                    helpers.each(activeBars, function(activeBar) {
                        if (activeBar) {
                            activeBar.fillColor = activeBar.highlightFill;
                            activeBar.strokeColor = activeBar.highlightStroke;
                        }
                    });
                    this.showTooltip(activeBars);
                });
            }

            //Declare the extension of the default point, to cater for the options passed in to the constructor
            this.BarClass = Chart.Rectangle.extend({
                strokeWidth: this.options.barStrokeWidth,
                showStroke: this.options.barShowStroke,
                ctx: this.chart.ctx
            });

            //Iterate through each of the datasets, and build this into a property of the chart
            helpers.each(data.datasets, function(dataset, datasetIndex) {
                var datasetObject = {
                    label: dataset.label || null,
                    fillColor: dataset.fillColor,
                    strokeColor: dataset.strokeColor,
                    showTooltip: dataset.showTooltip,
                    bars: [],
                    yAxesGroup: dataset.yAxesGroup,
                    values: dataset.data
                };

                this.datasets.push(datasetObject);

                helpers.each(dataset.data, function(dataPoint, index) {
                    //Add a new point for each piece of data, passing any required data to draw.
                    datasetObject.bars.push(new this.BarClass({
                        value: dataPoint,
                        showTooltip: dataset.showTooltip === undefined ? true : dataset.showTooltip,
                        label: data.labels[index],
                        datasetLabel: dataset.label,
                        strokeColor: dataset.strokeColor,
                        fillColor: dataset.fillColor,

                        highlightFill: dataset.highlightFill || dataset.fillColor,
                        highlightStroke: dataset.highlightStroke || dataset.strokeColor,
                        yAxesGroup: dataset.yAxesGroup,
                    }));
                }, this);

            }, this);

            this.buildScale(data.labels);
            if (this.scale.min < 0) {

                var basePercetage = (-1 * parseFloat(this.scale.min) /
                    (this.scale.max - this.scale.min) * 1.00);
                var totalHeight = (this.scale.endPoint - this.scale.startPoint);
                var originFromEnd = basePercetage * totalHeight;
                var base = this.scale.endPoint - originFromEnd + this.options.scaleGridLineWidth


                this.BarClass.prototype.base = base;
            } else {
                this.BarClass.prototype.base = this.scale.endPoint;
            }

            this.eachBars(function(bar, index, datasetIndex) {
                helpers.extend(bar, {
                    width: this.scale.calculateBarWidth(this.datasets.length, this.options.overlayBars),
                    x: this.scale.calculateBarX(this.datasets.length, datasetIndex, index, this.options.overlayBars),
                    y: bar.base
                });
                bar.save();
            }, this);

            this.render();
        },
        update: function() {
            this.scale.update();
            // Reset any highlight colours before updating.
            helpers.each(this.activeElements, function(activeElement) {
                activeElement.restore(['fillColor', 'strokeColor']);
            });

            this.eachBars(function(bar) {
                bar.save();
            });
            this.render();
        },
        eachBars: function(callback) {
            helpers.each(this.datasets, function(dataset, datasetIndex) {
                helpers.each(dataset.bars, callback, this, datasetIndex);
            }, this);
        },
        getBarsAtEvent: function(e) {
            var barsArray = [],
                eventPosition = helpers.getRelativePosition(e),
                datasetIterator = function(dataset) {
                    barsArray.push(dataset.bars[barIndex]);
                },
                barIndex;

            for (var datasetIndex = 0; datasetIndex < this.datasets.length; datasetIndex++) {
                for (barIndex = 0; barIndex < this.datasets[datasetIndex].bars.length; barIndex++) {
                    if (this.datasets[datasetIndex].bars[barIndex].inRange(eventPosition.x, eventPosition.y, this.scale.endPoint) && this.datasets[datasetIndex].bars[barIndex].showTooltip) {
                        helpers.each(this.datasets, datasetIterator);
                        return barsArray;
                    }
                }
            }

            return barsArray;
        },
        buildScale: function(labels) {
            var self = this;

            var dataTotal = function() {
                var values = [];
                self.eachBars(function(bar) {
                    values.push(bar.value);
                });
                return values;
            };

            var scaleOptions = {
                labelLength: this.options.labelLength,
                labelsFilter: this.options.labelsFilter,
                templateString: this.options.scaleLabel,
                height: this.chart.height,
                width: this.chart.width,
                ctx: this.chart.ctx,
                textColor: this.options.scaleFontColor,
                fontSize: this.options.scaleFontSize,
                fontStyle: this.options.scaleFontStyle,
                fontFamily: this.options.scaleFontFamily,
                valuesCount: labels.length,
                beginAtZero: this.options.scaleBeginAtZero,
                integersOnly: this.options.scaleIntegersOnly,
                xLabels: labels,
                font: helpers.fontString(this.options.scaleFontSize, this.options.scaleFontStyle, this.options.scaleFontFamily),
                lineWidth: this.options.scaleLineWidth,
                lineColor: this.options.scaleLineColor,
                showHorizontalLines: this.options.scaleShowHorizontalLines,
                showVerticalLines: this.options.scaleShowVerticalLines,
                gridLineWidth: (this.options.scaleShowGridLines) ? this.options.scaleGridLineWidth : 0,
                gridLineColor: (this.options.scaleShowGridLines) ? this.options.scaleGridLineColor : "rgba(0,0,0,0)",
                padding: (this.options.showScale) ? 0 : (this.options.barShowStroke) ? this.options.barStrokeWidth : 0,
                showLabels: this.options.scaleShowLabels,
                display: this.options.showScale,
                yAxes: this.yAxes,
                positionLeft: this.options.scalePositionLeft,
                datasets: this.datasets,
            };

            if (this.options.scaleOverride) {
                helpers.extend(scaleOptions, {
                    scaleOverride: this.options.scaleOverride,
                    calculateYRange: helpers.noop,
                    steps: this.options.scaleSteps,
                    stepValue: this.options.scaleStepWidth,
                    min: this.options.scaleStartValue,
                    max: this.options.scaleStartValue + (this.options.scaleSteps * this.options.scaleStepWidth)
                });
            }

            this.scale = new this.ScaleClass(scaleOptions);
        },
        addData: function(valuesArray, label) {
            //Map the values array for each of the datasets
            helpers.each(valuesArray, function(value, datasetIndex) {
                //Add a new point for each piece of data, passing any required data to draw.
                this.datasets[datasetIndex].bars.push(new this.BarClass({
                    value: value,
                    label: label,
                    x: this.scale.calculateBarX(this.datasets.length, datasetIndex, this.scale.valuesCount + 1, this.options.overlayBars),
                    y: this.scale.endPoint,
                    width: this.scale.calculateBarWidth(this.datasets.length, this.options.overlayBars),
                    base: this.scale.endPoint,
                    strokeColor: this.datasets[datasetIndex].strokeColor,
                    fillColor: this.datasets[datasetIndex].fillColor,
                    yAxesGroup: this.datasets[datasetIndex].yAxesGroup
                }));
            }, this);

            this.scale.addXLabel(label);
            //Then re-render the chart.
            this.update();
        },
        removeData: function() {
            this.scale.removeXLabel();
            //Then re-render the chart.
            helpers.each(this.datasets, function(dataset) {
                dataset.bars.shift();
            }, this);
            this.update();
        },
        reflow: function() {
            helpers.extend(this.BarClass.prototype, {
                y: this.scale.endPoint,
                base: this.scale.endPoint
            });
            var newScaleProps = helpers.extend({
                height: this.chart.height,
                width: this.chart.width
            });
            this.scale.update(newScaleProps);
        },


        getLargestValue: function(array) {
            var largest = array[0]

            for (var i = 1; i < array.length; i++) {
                if (array[i].value > largest.value) {
                    largest = array[i];
                }
            }

            return largest;
        },
        //extracted from draw so it can be used to draw any bar datasets
        drawDatasets: function(datasets, easingDecimal) {
            if (this.options.overlayBars && datasets[0]) {

                //go through each data set and sort in order of value size
                for (var index = 0; index < datasets[0].bars.length; index++) {

                    //create buckets based on axis group, all axis groups that are overlay get grouped together for drawing
                    var drawBucket = [];
                    for (var datasetIndex = 0; datasetIndex < datasets.length; datasetIndex++) {
                        if (datasets[datasetIndex].bars[index]) {

                            var value = datasets[datasetIndex].bars[index].value;
                            drawBucket.push({
                                value: value,
                                index: index,
                                datasetIndex: datasetIndex,
                                yAxesGroup: datasets[datasetIndex].bars[index].yAxesGroup

                            });
                        }
                    }

                    while (drawBucket.length > 0) {
                        var bucketInfo = this.getLargestValue(drawBucket);
                        var bar = datasets[bucketInfo.datasetIndex].bars[bucketInfo.index];
                        if (bar.hasValue()) {
                            if (this.scale.getAxisMin(bar) < 0) {
                                bar.base = this.scale.getAxisBase(bar);
                            } else {
                                bar.base = this.scale.endPoint;
                            }
                            //Transition then draw
                            bar.transition({
                                x: this.scale.calculateBarX(datasets.length, datasetIndex, index, this.options.overlayBars),
                                y: this.scale.calculateY(bar),
                                width: this.scale.calculateBarWidth(datasets.length, this.options.overlayBars)
                            }, easingDecimal).draw();
                        }
                        var objectIndex = helpers.indexOf(drawBucket, bucketInfo);
                        if (objectIndex === 0) {
                            if (drawBucket.length === 1) {
                                drawBucket = [];
                            } else {
                                drawBucket = drawBucket.slice((drawBucket.length * -1) + 1);

                            }
                        } else {
                            drawBucket = drawBucket.slice(0, objectIndex).concat(drawBucket.slice(objectIndex + 1, drawBucket.length));
                        }
                    }
                }



            } else {
                //Draw all the bars for each dataset
                helpers.each(datasets, function(dataset, datasetIndex) {
                    helpers.each(dataset.bars, function(bar, index) {
                        if (bar.hasValue()) {
                            if (this.scale.getAxisMin(bar) < 0) {
                                bar.base = this.scale.getAxisBase(bar);
                            } else {
                                bar.base = this.scale.endPoint;
                            }
                            //Transition then draw
                            bar.transition({
                                x: this.scale.calculateBarX(datasets.length, datasetIndex, index, this.options.overlayBars),
                                y: this.scale.calculateY(bar),
                                width: this.scale.calculateBarWidth(datasets.length, this.options.overlayBars)
                            }, easingDecimal).draw();
                        }
                    }, this);

                }, this);
            }


        },
        draw: function(ease) {
            var easingDecimal = ease || 1;
            this.clear();

            var ctx = this.chart.ctx;
            this.scale.draw(easingDecimal);
            helpers.each(this.scale.yAxes._yAxes, function(axisGroup) {
                var dataSetToSend = [];
                helpers.each(this.scale.datasets, function(dataset) {
                    if (dataset.yAxesGroup === axisGroup.name && dataset.bars) {
                        dataSetToSend.push(dataset);
                    }
                });
                this.drawDatasets(dataSetToSend, easingDecimal);
            }, this);


        }
    });


}).call(this);
