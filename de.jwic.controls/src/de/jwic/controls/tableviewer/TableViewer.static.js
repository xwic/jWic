/*
 * Copyright 2007 jWic group (http://www.jwic.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

(function($) {

	$.extend(JWic.controls,
		{
			TableViewer : {
				
				globals : {
					resizer 	: null,
					colIdx 		: null,
					resXStart 	: null,
					minX 		: null,
					oldSize 	: null,
					ctrlId		: null,
					pushedCol 	: "", 
					scrolledX	: 0,
					fixed		: 0
				},
				
				/**
				 * Initialize the TableViewer.
				 */
				initialize : function(element, viewerCtrlId, options) {
					function closestNon0WidthParent(elm){
						if(elm.width() !== 0){
							return elm.width();//some have spans as parents and some spans have 0px width. so we find the closes one with non-zero width and use that one
						}
						return closestNon0WidthParent(elm.parent());
					}
					
					function sizeSetter(){
						var parent = closestNon0WidthParent(jQuery('#ctrl_'+JWic.util.JQryEscape(viewerCtrlId)).parent().parent()) -2,//goto go 2 parents above for firefox, it doesn't affect chorme or IE since it the first parent on chrome and IE has width 0 anyway
							table = jQuery('#ctrl_'+JWic.util.JQryEscape(viewerCtrlId));
						table.find('.tblViewDataLayer').width(parent);
						
						table.find('.tblViewHead').width(parent);
						table.find('.tblViewStatusBar').width(parent);
						table.find('table:first').attr('width', parent);
						jQuery("#tblContent_" + JWic.util.JQryEscape(viewerCtrlId)).width(parent);
					}
					
					
					
					var tableObject = JWic.$("tblViewData_" + viewerCtrlId),
						tableContent = JWic.$("tblContent_" + viewerCtrlId),
						tableControl = JWic.$('ctrl_'+viewerCtrlId);
					
					if (options.colResize) {
						tableObject.find("img.tblResize").on("mousedown", function(e) {
							JWic.controls.TableViewer.resizeColumn(e, viewerCtrlId);
						});
					}
					if (options.menu != null) {
						tableContent.find("tbody > tr").bind("contextmenu", function(e){JWic.controls.TableViewer.handleContextMenu(this, options.menu, e)});
					}
					
					if(JWic.$('ctrl_'+viewerCtrlId) && options.fitToParent){//only trigger this if the control is fitToParent
						sizeSetter();
						setTimeout(sizeSetter, 0);
						(function (){
							var resizeTimer;
							jQuery(window).resize(function() {
							    clearTimeout(resizeTimer);
							    resizeTimer = setTimeout(sizeSetter, 100);//fancy resize callback to make the ui more responsive
							});
						}());
					}
					
					
				},
				
				/**
				 * Handle a right-click
				 */
				handleContextMenu : function(row, menuId, e) {
					// select the row
					this.clickRow(row, e, false, function() {
						JWic.controls.Menu.show(menuId, {
							my : "left top",
							of : e,
							collision: "fit"
						});
					});
					e.preventDefault();
					return false;
				},
			
				/**
				 * Scroll the header and store scroll info for re-rendering.
				 */
				handleScroll : function (e, viewerCtrlId) {
					if (!e) e = window.event;
					var sourceLayer = JWic.controls.TableViewer.getTarget(e);
					
					var divHeader = document.getElementById("tblViewHead_" + viewerCtrlId);
					if (divHeader) {
						divHeader.scrollLeft = sourceLayer.scrollLeft;
					}
					var fixData = document.getElementById("tblViewLeftDataLayer_" + viewerCtrlId);
					if (fixData) {
						fixData.scrollTop = sourceLayer.scrollTop;
						
						var ie6fix = document.getElementById("ie6fixscroll_" + viewerCtrlId);
						ie6fix.style.display = "inline";
						ie6fix.style.display = "none";
					}
					document.forms['jwicform'].elements['fld_' + viewerCtrlId + '.top'].value = sourceLayer.scrollTop;
					document.forms['jwicform'].elements['fld_' + viewerCtrlId + '.left'].value = sourceLayer.scrollLeft;
				},
				
				/**
				 * Modifies the column css_class to simulate a pushed button.
				 */
				pushColumn : function(colIdx, viewerCtrlId, fixed) {
					var tableObject = document.getElementById("tblViewData_" + viewerCtrlId);
					if (fixed) {
						tableObject = document.getElementById("tblViewLeftData_" + viewerCtrlId);
					}
					var colNodes = JWic.controls.TableViewer.getColumnNodes(tableObject);
					for (var i = 0; i <colNodes.length; i++) {
						var objTH = colNodes[i];
						if (objTH.nodeName == "TH" && objTH.attributes.getNamedItem("colIdx").value == colIdx) {
							objTH.className = "pushed";
							JWic.controls.TableViewer.globals.pushedCol = objTH;
							break;
						}
					}
				},
				
				/**
				 * Modifies the column css_class so that it looks like before if
				 * the button was released.
				 */
				releaseColumn : function() {
					var g = JWic.controls.TableViewer.globals;
					if (g.pushedCol != "") {
						g.pushedCol.className = "";
						g.pushedCol = "";
						return;
					}
				},
				
				getResizer : function() {
					var g = JWic.controls.TableViewer.globals;
					if (g.resizer == null) {
						// create the resizer element.
						g.resizer = jQuery('<DIV id="tblViewerResizer" class="tblViewResizer"></DIV>');
						jQuery("body").append(g.resizer);
					}
					return g.resizer;
				},
				
				/**
				 * starts column resizing.
				 */
				resizeColumn : function (e, viewerCtrlId, fixed) {
					
					var g = JWic.controls.TableViewer.globals;
					
					if (!e) e = window.event;
					if (e.preventDefault) {
						e.preventDefault();
					}
					var imgSeperator = JWic.controls.TableViewer.getTarget(e);
			
					// show a DIV layer
					var resizer = JWic.controls.TableViewer.getResizer();
					
					var tableObject = JWic.$("tblViewData_" + viewerCtrlId);
					var tableContent = JWic.$("tblContent_" + viewerCtrlId);
					
					var tblWidth = tableObject.parent().width();
					var tblHeight = tableObject.parent().height();
					if (tableContent) tblHeight = tableContent.height();
					var colIdx = imgSeperator.attributes.getNamedItem("colIdx").value;
					if (fixed) {
						tableObject = JWic.$("tblViewLeftData_" + viewerCtrlId);
					}
					var colWidth = new Array();
					var offset = tableContent.offset();
			
					resizer.css("top", offset.top + "px");
					resizer.css("left", offset.left + "px");
					resizer.height(tblHeight);
					
					var minX = offset.left;
					// build list of the width of all columns
					var colNodes = JWic.controls.TableViewer.getColumnNodes(tableObject);
					for (var i = 0; i < colNodes.length; i++) {
						var objTH = colNodes.get(i);
						if (objTH.nodeName == "TH" && objTH.attributes.getNamedItem("colIdx")) {
							var idx = objTH.attributes.getNamedItem("colIdx").value;
							colWidth[idx] = jQuery(objTH).width();
							if (idx == colIdx) {
								break;
							}
							minX += parseInt(jQuery(objTH).width());
							minX += parseInt(JWic.controls.TableViewer.getStyle(objTH, "borderRightWidth", "border-right-width")) +
									+ parseInt(JWic.controls.TableViewer.getStyle(objTH, "borderLeftWidth", "border-left-width"))
									+ parseInt(JWic.controls.TableViewer.getStyle(objTH, "paddingLeft", "padding-left"))	
									+ parseInt(JWic.controls.TableViewer.getStyle(objTH, "paddingRight", "padding-right"));
						}
					}
					
					var divHeader = document.getElementById("tblViewHead_" + viewerCtrlId);
					if (divHeader && !fixed) {
						g.scrolledX = divHeader.scrollLeft;
					} else {
						g.scrolledX = 0;
					}
					g.currResizer = resizer;
					g.colIdx = colIdx;
					g.resXStart = e.pageX;
					g.minX = minX;
					g.oldSize = colWidth[colIdx];
					g.ctrlId = viewerCtrlId;
					g.fixed = fixed;
			
					var newWidth = JWic.controls.TableViewer.getNewWidth(e);
					resizer.css("display", "inline");
					if (tblHeight > 30) {
						jQuery(resizer).height(tblHeight);// + "px";
					}
					var newLeft = (minX + newWidth - g.scrolledX) + "px";
					resizer.css("left", newLeft);
					
					if (resizer[0].setCapture) { // IE mode
						resizer
							.on("mousemove", JWic.controls.TableViewer.resizeColumMove)
							.on("mouseup", JWic.controls.TableViewer.resizeColumnDone);
						resizer[0].setCapture();
					} else { // Mozilla
						jQuery(window)
							.on("mousemove", JWic.controls.TableViewer.resizeColumMove)
							.on("mouseup", JWic.controls.TableViewer.resizeColumnDone);
					}
					
					
				},
				
				/**
				 * returns the list of TH elements that specify the columns.
				 */
				getColumnNodes : function(tableObject) {
					var objTR = jQuery(tableObject).find("tr");
					if (objTR == null || objTR.length == 0) {
						JWic.log("ERROR JWic.controls.TableViewer.getColumnNodes: cant find TH tags");
						return null;
					} 
					return jQuery(objTR[0]).find("TH");
				},
				
				/**
				 * Invoked when the mouse is moved.
				 */
				resizeColumMove : function(e) {
					var g = JWic.controls.TableViewer.globals;
					if (!e) e = window.event;
					var newWidth = JWic.controls.TableViewer.getNewWidth(e);
					g.currResizer.css("left", (g.minX + newWidth - g.scrolledX) + "px");
					//g.currResizer.style.left = e.pageX+'px';
				},
				
				/**
				 * Invoked when the user released the resizer.
				 */
				
				resizeColumnDone : function(e) {
					var g = JWic.controls.TableViewer.globals;
					if (!e){
						e = window.event;
					}
					g.resizer.css("display", "none");
					
					if (g.resizer[0].setCapture) { // IE mode
						g.resizer[0].releaseCapture();
						g.resizer
							.off("mousemove", JWic.controls.TableViewer.resizeColumMove)
							.off("mouseup", JWic.controls.TableViewer.resizeColumnDone);
					} else { // Mozilla mode
						jQuery(window)
							.off("mousemove", JWic.controls.TableViewer.resizeColumMove)
							.off("mouseup", JWic.controls.TableViewer.resizeColumnDone);
					}
					
					var newWidth = JWic.controls.TableViewer.getNewWidth(e);
					
					if (newWidth != g.oldSize) {
						//clientseitige verschiebung
						JWic.controls.TableViewer.resizeOnClientSide(g.colIdx,newWidth);
						//serverseitige verschiebung
						JWic.fireAction(g.ctrlId, 'resizeColumnWithoutRedraw', g.colIdx + ";" + newWidth);
			
					}
				},
			
				resizeOnClientSide : function(localColIdx, newWidth){
					var g = JWic.controls.TableViewer.globals;
					
					var contentTableName = "#tblContent_"+JWic.util.JQryEscape(g.ctrlId);
					
					var divLeftViewHeadName = "#tblViewLeftHead_"+JWic.util.JQryEscape(g.ctrlId);
					var divLeftDataLayerName = "#tblViewLeftDataLayer_"+JWic.util.JQryEscape(g.ctrlId);
			
					var divViewHeadName = "#tblViewHead_"+JWic.util.JQryEscape(g.ctrlId);
					var divDataLayerName = "#tblViewDataLayer_"+JWic.util.JQryEscape(g.ctrlId);
					
					
					
					var contentTable = jQuery(contentTableName);
					
					var divLeftViewHead =  jQuery(divLeftViewHeadName);
					var divLeftDataLayer = jQuery(divLeftDataLayerName);
					
					var divViewHead =  jQuery(divViewHeadName);
					var divDataLayer = jQuery(divDataLayerName);
					
					
					var contentTableWidth = contentTable.width();
					var divDataLayerWidth = divDataLayer.width();
					var divLeftDataLayerWidth;
					var divLeftViewHeadWidth;
					var divViewHeadWidth = divViewHead.width();
					
					var selectedHead;
					var selectedBody;
					var unselectedHead;
					var unselectedBody;
					
					//eigenen index f�hren da es sich hier um 2 verschiedene Tabellen (left,right) handelt
					var columnIndex;
					
					//es existiert nur eine Tabelle, die left Table Elemente sind somit unn�tz
					//diese bedingung ber�cksichtigt 3 Zust�nde
					
					//TableView mit 1 Tabelle
					//TableView mit 2 Tabellen und die Linke selektiert
					//TableView mit 2 Tabellen und die Rechte selektiert
					if(typeof g.fixed=='undefined'){
						columnIndex = localColIdx;
						
						//gibt kein left, also hauptlayer standardm��ig selektiert
						selectedHead = divViewHead;
						selectedBody = divDataLayer;
			
					//welche Tabelle soll manipuliert werden, gleichzeitig den echten Index berechnen
					}else if (g.fixed){
						columnIndex = localColIdx;
						//wir befinden uns in der linken tabelle
						selectedHead = divLeftViewHead;
						selectedBody = divLeftDataLayer;
						
						unselectedHead = divViewHead;
						unselectedBody = divDataLayer;
						
						divLeftViewHeadWidth = divLeftViewHead.getWidth();
						divLeftDataLayerWidth = divLeftDataLayer.getWidth()
						
						
					}else {
						
						selectedHead = divViewHead;
						selectedBody = divDataLayer;
						
						unselectedHead = divLeftViewHead;
						unselectedBody = divLeftDataLayer;
						
						//linke spalten abziehen
						columnIndex = localColIdx-2;
						
					}
					
					var selectedHeadTable = jQuery(selectedHead.children()[0]);
					var selectedBodyTable = jQuery(selectedBody.children()[0]);
					
					var headRows = jQuery(selectedHeadTable.children()[0]).children();
					var bodyRows = jQuery(selectedBodyTable.children()[0]).children();
					
					//alle Rows zusammenfassen
					
					var allRows = [];//headRows.concat(bodyRows)
					headRows.each(function(i,e){
						allRows.push(e);
					});
					bodyRows.each(function(i,e){
						allRows.push(e);
					})
					
					
					//�ber alle Rows iterieren und Cols suchen
					for(var i = 0; i < allRows.length; i++){
						var myRow = allRows[i];
						//hole spalten
						var allCols = jQuery(myRow).children();
						//selektiere spalte �ber errechneten index
						var myCol = jQuery(allCols[columnIndex]);
						//alte breite der spalte ermitteln
						var oldWidth = myCol.width();
						//neue spaltenbreite setzen
						myCol.width(newWidth);
						var elements = jQuery(myCol.find('.tbvColHeadCell')[0]).width(newWidth-10);
						//myCol.attr('width',newWidth);
						//head und body haben unterschiedliche strukturen
						if(i == 0)//head
						{	
							//auch die breite des Inhaltes setzen
							var myColChild = jQuery(jQuery(jQuery(jQuery(myCol).children()[0]).children()[0]).children()[0]);
							//innere breite immer -5 damit man den spaltenschieber noch sieht
							myColChild.width(newWidth-5);
							//aktuelle tabelle mit der differenz der ge�nderten breiten ausgleichen
							var diff = newWidth - oldWidth;
							var nwidth =  parseInt(selectedHeadTable.width) + diff;
							if(g.fixed){
								selectedHeadTable.width(nwidth);
								selectedBodyTable.width(nwidth);
							
								selectedHead.width(nwidth);
								selectedBody.width(nwidth);
								
								//passt die �nderungen an den anderen tabellen an
								unselectedBody.width((divViewHeadWidth-diff));
								//der div layer enth�lt eine tabelle welche auch angepasst werden muss 
								//sonst sieht es zerst�ckelt aus
								unselectedBody.children().width(divViewHeadWidth-diff);
								
								unselectedHead.width((divViewHeadWidth-diff));
							}
									
						}
						
					}
					
				},
				
				
				getNewWidth : function(e) {
					var g = JWic.controls.TableViewer.globals;
					var x = e.pageX;
					var diff = x - g.resXStart;
					//jQuery("#debugOut").val("Move x=" + x + ", g.resXStart=" + g.resXStart + ", diff=" + diff + ", g.oldSize=" + g.oldSize);
					var newWidth = parseInt(diff) + parseInt(g.oldSize);
					if (newWidth < 6) {
						newWidth = 6;
					}
					return newWidth;
				},
				
			
				/**
				 * Activated by onClick event on a row.
				 */
				clickRow : function(tblRow, e, dblClick, callBack) {
				
					if (!e) e = window.event;
					if (typeof dblClick == 'undefined') {
						dblClick = false;
					} else if (dblClick) {
						JWic.util.clearSelection();
					}
					var rowKey = tblRow.attributes.getNamedItem("tbvRowKey").value;
					// TR->TBODY->TABLE
					var tableNode = tblRow.parentNode.parentNode;
					var attributes = tableNode.attributes;
					var tbvCtrlId = attributes.getNamedItem("tbvctrlid").value;
					var tbvSelKey = attributes.getNamedItem("tbvSelKey").value;		
					var tbvSelMode = attributes.getNamedItem("tbvSelMode").value;
					
					var tableNode2;
					if (tableNode.id.indexOf("tblViewDataTbl_") == 0) {
						tableNode2 = document.getElementById("tblViewLeftDataTbl_" + tbvCtrlId);
					} else if (tableNode.id.indexOf("tblViewLeftDataTbl_") == 0) {
						tableNode2 = document.getElementById("tblViewDataTbl_" + tbvCtrlId);
					}
				
					JWic.log("JWic.controls.TableViewer.ClickRow: " + tbvSelMode + "; " + tbvSelKey);
					if (tbvSelMode == "multi") {
						JWic.controls.TableViewer.flipRowSelection(tblRow);
						if (tableNode2) JWic.controls.TableViewer.flipRowSelection(tableNode2.rows[tblRow.rowIndex]);
					} else if (tbvSelMode == "single") {
						if (tbvSelKey != rowKey) {
							// deselect old row
							if (tbvSelKey != "") {
								// find selected row and deselect it.
								for (var rowNr = 0; rowNr < tableNode.rows.length; rowNr++)  {
									var trElm = tableNode.rows[rowNr];
									var trKeyItem = trElm.attributes.getNamedItem("tbvRowKey");
									if (trKeyItem != null && trKeyItem.value == tbvSelKey) {
										JWic.controls.TableViewer.flipRowSelection(trElm);
										if (tableNode2) JWic.controls.TableViewer.flipRowSelection(tableNode2.rows[trElm.rowIndex]);
										break;
									}
								}
							}
							// select
							JWic.controls.TableViewer.flipRowSelection(tblRow); // select row
							tableNode.attributes.getNamedItem("tbvSelKey").value = rowKey;
							if (tableNode2) {
								JWic.controls.TableViewer.flipRowSelection(tableNode2.rows[tblRow.rowIndex]);
								tableNode2.attributes.getNamedItem("tbvSelKey").value = rowKey;
							}
						}
					}
					// change selection
				
					// notify control
					JWic.fireAction(tbvCtrlId, dblClick ? 'dblClick' : 'selection', rowKey, callBack);
				
				},
				
				flipRowSelection : function(tblRow) {
					if (tblRow.className == "selected") {
						tblRow.className = "";
					} else if (tblRow.className == "" || tblRow.className == "undefined") {
						tblRow.className = "selected";
					} else if (tblRow.className == "lastRow") {
						tblRow.className = "lastRowselected";
					} else if (tblRow.className == "lastRowselected") {
						tblRow.className = "lastRow";
					}
					//tblRow.innerHtml = tblRow.innerHtml;
				},
				
				/**
				 * Returns the event target.
				 */
				getTarget : function(e) {
					if (e.target) {
						return e.target;
					} else {
						return e.srcElement;
					}
				},
				
				/**
				 * Find a specified child element.
				 */
				findElement : function(elm, sName) {
			
					if (elm.nodeName == sName) {
						return elm;
					}
					for (var i = 0; i < elm.childNodes.length; i++) {
						var child = elm.childNodes[i];
						if (child.nodeName == sName) {
							return child;
						}
						if (child.childNodes && child.childNodes.length > 0) {
							child = JWic.controls.TableViewer.findElement(child, sName);
							if (child != null) {
								return child;
							}
						}
					}
					return null;
			
				},
				
				getStyle : function(elm, ieName, mozName) {
					var v = "";
					if (elm.currentStyle)
						v = elm.currentStyle[ieName];
					else if (window.getComputedStyle) {
						try {
							v = document.defaultView.getComputedStyle(elm,null).getPropertyValue(mozName);
						} catch (e) {
							alert("error reading styles:" + e + "\n name: " + mozName + "\n elm" + elm);
						}
					}
					return v;
				},
				
				  /**
				   * Expand a node in the selected element.
				   */
				expand : function(e) {
				  	if (!e) e = window.event;
				  	var element = JWic.controls.TableViewer.getTarget(e);
				  	var key = JWic.controls.TableViewer.getRowKey(element);
				  	var ctrlId = JWic.controls.TableViewer.getControlID(element);

					JWic.fireAction(ctrlId, "expand", key);

					// prevent the click function from runing as we do not want a selection.  	
					// tblv_ignoreClick = window.event.X + "." + window.event.Y;
					e.cancelBubble = true;
					if (e.stopPropagation) e.stopPropagation();
					
				  	return false;
				},
				  
				  /**
				   * Collapse the node in the selected element.
				   */
				collapse : function(e) {
				  	if (!e) e = window.event;
				  	var element = JWic.controls.TableViewer.getTarget(e);
				  	var key = JWic.controls.TableViewer.getRowKey(element);
				  	var ctrlId = JWic.controls.TableViewer.getControlID(element);

				  	JWic.fireAction(ctrlId, "collapse", key);

					// prevent the click function from runing as we do not want a selection.  	
					e.cancelBubble = true;
					if (e.stopPropagation) e.stopPropagation();

				  	return false;
				},
				  
				getRowKey : function(element) {
				  	var node = element;
				  	while (node.attributes.getNamedItem("tbvRowKey") == null || node == null) {
				  		node = node.parentNode;
				  	}
				  	if (node != null) {
				  		return node.attributes.getNamedItem("tbvRowKey").value;
				  	}
				  	return "";
				},
				  
				getControlID : function(element) {
				  	var node = element;
				  	while (node.attributes.getNamedItem("tbvctrlid") == null || node == null) {
				  		node = node.parentNode;
				  	}
				  	if (node != null) {
				  		return node.attributes.getNamedItem("tbvctrlid").value;
				  	}
				  	return "";
				}				
		
			} //TableViewer
		}
	);
})(jQuery);
