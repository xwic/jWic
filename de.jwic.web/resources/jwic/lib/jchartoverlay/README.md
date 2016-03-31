# Chart.js 

[![Build Status](https://travis-ci.org/nnnick/Chart.js.svg?branch=master)](https://travis-ci.org/nnnick/Chart.js) [![Code Climate](https://codeclimate.com/github/leighquince/Chart.js/badges/gpa.svg)](https://codeclimate.com/github/leighquince/Chart.js)


*Simple HTML5 Charts using the canvas element* [chartjs.org](http://www.chartjs.org)
##Changes
 - Bar chart tool-tip will show when hovering labels as well as data (this was the norm for line charts so just brought the two in-line)
 - Line, Bar and OVerlay charts now handle negative values better, just make sure you pass `scaleBeginAtZero: false,` so that the chart does not reset the scale [http://fiddle.jshell.net/leighking2/gvo0u2oy/](http://fiddle.jshell.net/leighking2/gvo0u2oy/)

##New Features In This Fork
 - labelsFilter to filter x-axis labels based on user provided function, the function is provided with the value of the label, the index of the label and the full labels array, to use declare a new option called `labelsFilter` as a function that returns true if the label is to be filtered out or false if the label is not to be filtered out (shown)
 
             labelsFilter: function (value, index, labels) {
                 //show every 5th label
                 return (index + 1) % 5 !== 0;
             } 

 [http://jsfiddle.net/leighking2/mea767ss](http://jsfiddle.net/leighking2/mea767ss/)
 - Template interpolator can be changed from default `<%` `%>` to what ever you want [http://jsfiddle.net/leighking2/d5yq9x32](http://fiddle.jshell.net/leighking2/d5yq9x32/)
 - New chart - overlay chart - for combing both bar and line charts on the same chart [http://jsfiddle.net/leighking2/y58n7m3z](http://fiddle.jshell.net/leighking2/y58n7m3z/)
 - new chart options for pie and dougnut to allow the creation of semi circle (or any size) charts drawn at user defined starting angle [http://jsfiddle.net/leighking2/f62Lghy1](http://fiddle.jshell.net/leighking2/f62Lghy1/)
 - line and overlay charts can handle sparse datasets, just include null in the dataset where no data is avaliable, this can be seen in the samples [http://jsfiddle.net/leighking2/ntuwuk5v](http://fiddle.jshell.net/leighking2/ntuwuk5v/)
 - line,bar and overlay charts can have the tooltip display finely tuned. For each dataset an option called showTooltip can be passed to turn off the displaying off tooltips for that one dataset but not the whole chart [http://jsfiddle.net/leighking2/at3w2aej](http://fiddle.jshell.net/leighking2/at3w2aej/)
 - bar (and overlay) chart can have an option passed to overlay bars (draw on top of each other), just pass the option `overlayBars:true` when creating the chart [http://jsfiddle.net/leighking2/h2f7rs8d/](http://jsfiddle.net/leighking2/h2f7rs8d/)
 - line (and overlay) chart can have an option passed to populate sparse data sets. When this is passed the chart will connect any blank values in the chart so that the line is continous (starts at the first data poitn entered and goes untill the last data point), just pass the option `populateSparseData:true` when creating the chart. [http://jsfiddle.net/leighking2/uhs6rbt8/](http://jsfiddle.net/leighking2/uhs6rbt8/)
 - line,bar and overlay charts have a new option called `labelLength`. This is a number,which if greater than 0, will trim x-axis labels to a specific length [http://fiddle.jshell.net/leighking2/vepoxa54/](http://fiddle.jshell.net/leighking2/vepoxa54/)
 - Multiple y-axis can now be set on bar, line and overlay charts. This feature allow for more than 1 y-axis to be decalred and options to be set for it at a very basic level with no extra options to have multiple axis just include ` yAxesGroup: GROUPNAME` with each dataset, any that are set to the same will be combined.
 
             datasets: [{
                         label: "My First dataset",
                         type: "bar",
                         yAxesGroup: "1",
                         fillColor: "rgba(151,137,200,0.5)",
                         strokeColor: "rgba(151,137,200,0.8)",
                         highlightFill: "rgba(151,137,200,0.75)",
                         highlightStroke: "rgba(151,137,200,1)",
                         data: [28, 48, 40, 19, 86, 27, 90]
                     }, {
                         label: "My Second dataset",
                         type: "line",
                         yAxesGroup: "2",
                         fillColor: "rgba(151,187,205,0.5)",
                         strokeColor: "rgba(151,187,205,0.8)",
                         highlightFill: "rgba(151,187,205,0.75)",
                         highlightStroke: "rgba(151,187,205,1)",
                         data: [8, 38, 30, 29, 46, 67, 80]
                     }]

for more control a further option can be passed with the data called `yAxes` in which you can specify fontColour, poisition and other settings like

                     var overlayData = {
                             labels: ["January", "Februarya", "March", "April", "May", "Jun", "July"],
                             datasets: [{
                                 label: "My First dataset",
                                 type: "bar",
                                 yAxesGroup: "1",
                                 fillColor: "rgba(151,137,200,0.5)",
                                 strokeColor: "rgba(151,137,200,0.8)",
                                 highlightFill: "rgba(151,137,200,0.75)",
                                 highlightStroke: "rgba(151,137,200,1)",
                                 data: [28, 48, 40, 19, 86, 27, 90]
                             }, {
                                 label: "My Second dataset",
                                 type: "line",
                                 yAxesGroup: "2",
                                 fillColor: "rgba(151,187,205,0.5)",
                                 strokeColor: "rgba(151,187,205,0.8)",
                                 highlightFill: "rgba(151,187,205,0.75)",
                                 highlightStroke: "rgba(151,187,205,1)",
                                 data: [8, 38, 30, 29, 46, 67, 80]
                             }],
                             yAxes: [{
                                 name: "1",
                                 scalePositionLeft: false, //setting to false will dispaly this on the right side of the graph
                                 scaleFontColor: "rgba(151,137,200,0.8)"
                             }, {
                                 name: "2",
                                 scalePositionLeft: true,
                                 scaleFontColor: "rgba(151,187,205,0.8)"
                             }]
                         };
                         
 just make sure that the name in the `yAxes` group matches 1 of the names declared in the datasets.
 
 Examples:
 
 - Overlay
 [http://fiddle.jshell.net/leighking2/2pb5xwez/](http://fiddle.jshell.net/leighking2/2pb5xwez/)

 - Bar - with two bar groups in one axis and another bar in a second
 [http://fiddle.jshell.net/leighking2/b233c2f0/](http://fiddle.jshell.net/leighking2/b233c2f0/)

 - line - using old features such as populating sparse datasets
 [http://fiddle.jshell.net/leighking2/8ae9go3y/](http://fiddle.jshell.net/leighking2/8ae9go3y/)
 
Most new features are documented in the forks docs section or follow the links to the fiddles to see a working example.           
## Feature Removed
This feature has been removed as it clashed with the multiple y axis feature, this feature was orginoally added to mainly colour the y axis but this is achived in the multiple y axis feature much easier so this has been removed now
 - line, bar and overlay charts have a new option called `customYLabel`, this is function that can be used to give a custom display to y labels, here is an example showing the parameters and changing the colour of the label based on the index position (also a fiddle) [http://fiddle.jshell.net/leighking2/jLzvhf4f/](http://fiddle.jshell.net/leighking2/jLzvhf4f/)
 
             customYLabel: function (value, x, y, ctx, index) {
                     var defaultStyle = ctx.fillStyle;
                     ctx.fillStyle = '#' + intToARGB(index * 123456);
                     ctx.fillText(value, x, y);
                     ctx.fillStyle = defaultStyle;
              }


## Documentation

You can find documentation at [chartjs.org/docs](http://www.chartjs.org/docs/). The markdown files that build the site are available under `/docs`. Please note - in some of the json examples of configuration you might notice some liquid tags - this is just for the generating the site html, please disregard.

## Bugs, issues and contributing

Before submitting an issue or a pull request to the project, please take a moment to look over the [contributing guidelines](https://github.com/nnnick/Chart.js/blob/master/CONTRIBUTING.md) first.

For support using Chart.js, please post questions with the [`chartjs` tag on Stack Overflow](http://stackoverflow.com/questions/tagged/chartjs).

## License


Chart.js is available under the [MIT license](http://opensource.org/licenses/MIT).

