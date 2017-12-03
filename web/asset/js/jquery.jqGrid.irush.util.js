/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *	
  *	jqGrid.irush.util.js
  */

var __irush_selICol; //iCol of selected cell
var __irush_selIRow; //iRow of selected cell
var __irush_editColIdx_array = new Array();	//editabled column index array 



/**
 * jqGrid의 colModel중에서 editabled 속성이 true인 컬럼에 사용되는 editoptions 속성을 이용해서
 * 탭키나 엔터키를 눌었을 때 포커스 이동되게 한다.
 * 
 *	jqGrid 옵션에서 아래 옵션들을 넣어줘야 함.
 * 	cellEdit: true,	//cell edit 여부. 필수.
 * 	cellsubmit: 'clientArray',	//cell edit시 즉시 저장 하지 않을 경우.
 * 	beforeEditCell : function(rowid, cellname, value, iRow, iCol)	//cell이 editalbe로 변경시. 필수
 * 	{
 * 		console.log('beforeEditCell : '+rowid+'|'+cellname+'|'+value+'|'+iRow+'|'+iCol+'|');
 * 		__irush_selICol = iCol;
 * 		__irush_selIRow = iRow;
 * 	},
 * 	afterEditCell : function(rowid, cellname, value, iRow, iCol)		//cell이 editalbe에 read로 변경시
 * 	{
 * 		console.log('afterEditCell : '+rowid+'|'+cellname+'|'+value+'|'+iRow+'|'+iCol+'|');
 * 	},
 * 
 * @param jqGridId	 jqGrid ID
 * @param firstEditCol	 첫 editabled 속성이 true인 컬럼의 index
 * @param lastEditCol	 마지막 editabled 속성이 true인 컬럼의 index
 */
var __irush_createEditOptions = function(jqGridId, firstEditCol, lastEditCol){
	if(firstEditCol === undefined){
		firstEditCol = -1;
	}
	if(lastEditCol === undefined){
		lastEditCol = -1;
	}
	return {
			dataInit : function (elem) { $(elem).focus(function(){ this.select();}) },
		    dataEvents: [
		        { 
		            type: 'keydown', 
		            fn: function(e) { 
		                var key = e.charCode || e.keyCode;
		                console.log('tab============================================>');
		                if(key == 9)   // tab
		                {
		                	
		                	var nextRow = __irush_selIRow;		//focus가 이동할 다음 cell의 row
		                	var nextCol = __irush_selICol;		//focus가 이동할 다음 cell의 column
		                	
		                	if(lastEditCol > -1 && lastEditCol <= __irush_selICol){		//마지막 에디트 컬럼보다 현재 컬럼이 크거나 같으면 다음 로우로 이동
		                		nextRow = parseInt(__irush_selIRow) + 1;
		                		nextCol = firstEditCol > 0 ? firstEditCol : __irush_selICol;
		                		
		                	} else if(lastEditCol > -1 && lastEditCol > __irush_selICol) {	//마지막 에디트 컬럼보다 현재 컬럼이 작다면 다음 컬럼으로 이동
		                		
		                		/*
			                	 * __irush_editColIdx_array 배열에 값이 있을 경우 해당 배열을 참조해서 다음 editabled 컬럼 index값을 구해서
			                	 * 다음 이동할 컬럼으로 정한다.
			                	 * */
			                	var availNextColIdx = -1;
			                	if(__irush_editColIdx_array && __irush_editColIdx_array.length && __irush_editColIdx_array.length > 0){
			                		for(var i = 0; i < __irush_editColIdx_array.length; i++){
			                			if(__irush_editColIdx_array[i] == nextCol && i < (__irush_editColIdx_array.length - 1) ){
			                				availNextColIdx = __irush_editColIdx_array[i+1];
			                			}
			                		}
			                	}
			                	console.log('availNextColIdx============================================>'+availNextColIdx);
		                		nextCol = availNextColIdx > -1 ? availNextColIdx :  parseInt(__irush_selICol) + 1;
		                		
		                	} else {													// 다음 로우, 현재 컬럼 인덱스로 이동
		                		nextRow = __irush_selIRow + 1;
			                	nextCol = __irush_selICol;
		                	}
		                	console.log('__irush_selIRow============================================>'+__irush_selIRow);
		                	console.log('__irush_selICol============================================>'+__irush_selICol);
		                	console.log('nextRow============================================>'+nextRow);
		                	console.log('nextCol============================================>'+nextCol);
		                	
		                	/*
		                	*	현재 로우가 마지막 줄이 아닌 경우에만 tab키 이동
		                	*/
		                	if(!($("#"+jqGridId).getGridParam("reccount") == __irush_selIRow
		                			&& nextRow > __irush_selIRow
		                			&& __irush_selIRow != nextRow)
		                		){	
		                		
		                		/*setTimeout으로 0.1초뒤에 해당 column으로 이동하면서 수정폼으로 변경. */
			                    setTimeout("console.log('///////////////////////////////////////nextCol "+nextCol+"'); jQuery('#"+jqGridId+"').editCell(" + nextRow + ", " + nextCol + ", true);", 100);
			                    e.stopPropagation();	//이벤트 전파 중지
				                e.preventDefault(); 	//이벤트 취소
		                	}
		                }
		                else if (key == 13)//enter
		                {
		                	/*
		                	 * 현재 로우가 마지막 줄이 아닌 경우에만 엔터키 이동
		                	 * */
		                	if($("#"+jqGridId).getGridParam("reccount") != __irush_selIRow){	
			                	/*setTimeout으로 0.1초뒤에 해당 column으로 이동하면서 수정폼으로 변경. */
			                    setTimeout("jQuery('#"+jqGridId+"').editCell(" + __irush_selIRow + " + 1, " + __irush_selICol + ", true);", 100);
			                    e.stopPropagation();	//이벤트 전파 중지
				                e.preventDefault(); 	//이벤트 취소
		                	}
		                }
		            }
		        } 
		    ]
		};
};


/**
*	편집 모드가 있은 jgGrid에 자동적으로 탭키, 엔터키로 포커스 이동이 가능하도록 해준다.
*	jqGrid 생성한 다음에 __irush_setEditOptions() 함수를 호출한다.
*	@param jqGridId	jqGrid ID
*	@param beforeEditCell	jqGrid의 beforeEditCell옵션에 들어갈 이벤트 콜백 함수
*	@param beforeSubmitCell	jqGrid의 beforeSubmitCell옵션에 들어갈 이벤트 콜백 함수 
*	@param cellsubmit	jqGrid의 cellsubmit옵션에 들어갈 cell editing type. 
*	@param cellurl	jqGrid의 cellurl옵션에 들어갈 cell editing ajax url.
*/
var __irush_setEditOptions = function(jqGridId, beforeEditCell, beforeSubmitCell, cellsubmit, cellurl ){
	
	if(jqGridId === undefined){
		return false;
	}
	
	if(beforeEditCell === undefined){
		beforeEditCell = function(rowid, cellname, value, iRow, iCol){
			console.log('beforeEditCell : '+rowid+'|'+cellname+'|'+value+'|'+iRow+'|'+iCol+'|');
			__irush_selICol = iCol;
			__irush_selIRow = iRow;
		};
	} else {
		var oldBeforeEditCell = beforeEditCell;
		beforeEditCell = function(rowid, cellname, value, iRow, iCol){
			__irush_selICol = iCol;
			__irush_selIRow = iRow;
			console.log('beforeEditCell : '+rowid+'|'+cellname+'|'+value+'|'+iRow+'|'+iCol+'|');
			try{
				oldBeforeEditCell.call(this, rowid, cellname, value, iRow, iCol);
			}catch(e){console.log('oldBeforeEditCell.call error'); console.log(e);}
		};
		
	}
	
	
	if(beforeSubmitCell === undefined){
		beforeSubmitCell = function(rowid, cellname, value){
			console.log('beforeSubmitCell : '+rowid+'|'+cellname+'|'+value);
		};
	} else {
		var oldBeforeSubmitCell = beforeSubmitCell;
		beforeSubmitCell = function(rowid, cellname, value){
			console.log('beforeSubmitCell : '+rowid+'|'+cellname+'|'+value);
			try{
				oldBeforeSubmitCell.call(this, rowid, cellname, value);
			}catch(e){console.log('oldBeforeSubmitCell.call error'); console.log(e);}
		};
		
	}
	
	if(cellsubmit === undefined){
		cellsubmit = 'clientArray';
	}
	
	/**
	 * jqGrid의 colModel옵션에 적용된 컬럼 모델 정보를 이용해서 editable속성 컬럼의 시작 인덱스와 마지막 인덱스를 구하고
	 * 그 컬럼마다 editoptions 옵션으로 __irush_createEditOptions 함수를 설정한다.
	 */
	var colModel = $('#'+jqGridId).getGridParam("colModel");
	var firstEditCol = -1, lastEditCol = -1;
	if(colModel.length !== 'undefined' && colModel.length > 0){
		var arrIdx = 0;
		for(var i = 0; i < colModel.length; i++){
			console.log(colModel[i].name + ':' + colModel[i].editable );
			if(colModel[i].editable && firstEditCol < 0){
				firstEditCol = i;
			}
			if(colModel[i].editable){
				lastEditCol = i;
				__irush_editColIdx_array[arrIdx] = i;
				arrIdx++;
			}
		}
		console.log('__irush_editColIdx_array '+__irush_editColIdx_array);
		
		console.log('firstEditCol '+firstEditCol);
		console.log('lastEditCol '+lastEditCol);
		
		if(firstEditCol > -1){
			for(var i = 0; i < colModel.length; i++){
				if(colModel[i].editable){
					colModel[i].editoptions = __irush_createEditOptions(jqGridId, firstEditCol, lastEditCol);	
					console.log(colModel[i].name + '.editoptions :' + colModel[i].editoptions );
				}
			}
		}
		
	}
	
	/**
	 * 기본적으로 들어가야 하는 옵션들을 셋팅한다.
	 */
	if(firstEditCol){
		$('#'+jqGridId).jqGrid("setGridParam", {"cellEdit" :true});      
		$('#'+jqGridId).jqGrid("setGridParam", {"cellsubmit" : cellsubmit});   
		$('#'+jqGridId).jqGrid("setGridParam", {"beforeEditCell" : beforeEditCell});
		$('#'+jqGridId).jqGrid("setGridParam", {"beforeSubmitCell" : beforeSubmitCell}); 
		if(cellurl === undefined){
			$('#'+jqGridId).jqGrid("setGridParam", {"cellurl" : cellurl}); 
		}
		
		console.log($('#'+jqGridId).getGridParam("cellEdit"));
		console.log($('#'+jqGridId).getGridParam("cellsubmit"));
		console.log($('#'+jqGridId).getGridParam("beforeEditCell"));
		console.log($('#'+jqGridId).getGridParam("beforeSubmitCell"));
	}
};


/**
*	row단위로 편집모드 사용할때
*/

/**
 * jqGrid 편집모드에서 tab키 이동 적용
 * colModel중 마지막 편집 컬럼의 editoptions에 이 함수를 적용한다.
 * 		{name:"qty",		index:"qty", 		align:"center", width:100,	editable: true, editype : "text", formatter:"textbox",
 *       editoptions : 	Commons.jqgridEditOptions('grid_list')	},			//수량
 * @param jqGridId	jqGrid ID
 * @param saveRowYn	값이 없거나 N이면 다음 로우 셀렉트만, Y이면 현재 로우 저장하고 다음 로우 셀렉트한다.
 */
var __irush_jqgridEditOptions_row = function(jqGridId, saveRowYn){
	if(saveRowYn === undefined){
		saveRowYn = 'N';
	}
	return {
		dataInit : function (elem) { $(elem).focus(function(){ this.select();}); },
	    dataEvents: [
	        { 
	            type: 'keydown', 
	            fn: function(e) { 
	                var key = e.charCode || e.keyCode;
	                
	                if(key == 9){	//tab key
	                	var selRowId = $('#'+jqGridId).getGridParam("selrow");
			            var nextRowId = Commons.jqGridGetNextRowId(jqGridId);
			                
	                	if(nextRowId && nextRowId != null){
	                		
	                		if(saveRowYn == 'Y'){
	                			$('#'+jqGridId).jqGrid('saveRow', selRowId);			//현재 로우 저장
	                			$('#'+jqGridId).jqGrid('restoreRow', selRowId);			//현재 로우 편집 모드 해제
	                		}
	                		$('#'+jqGridId).jqGrid('setSelection', nextRowId, true); //다음 로우 셀렉트
	                		
	                		if(saveRowYn == 'Y'){
	                			$('#'+jqGridId).jqGrid('editRow', nextRowId, true);		//다음 로우 편집 모드 변경
	                		}
	                		
	                		e.stopPropagation();	//이벤트 전파 중지
			                e.preventDefault(); 	//이벤트 취소
	                	}
	                	
	                } else if (key == 13){	//enter key
	                	var selRowId = $('#'+jqGridId).getGridParam("selrow");
			            var nextRowId = Commons.jqGridGetNextRowId(jqGridId); 
			            
			            if(nextRowId && nextRowId != null){
			            	if(saveRowYn == 'Y'){
	                			$('#'+jqGridId).jqGrid('saveRow', selRowId);			//현재 로우 저장
	                			$('#'+jqGridId).jqGrid('restoreRow', selRowId);			//현재 로우 편집 모드 해제
	                		}
	                		$('#'+jqGridId).jqGrid('setSelection', nextRowId, true); //다음 로우 셀렉트
	                		
	                		if(saveRowYn == 'Y'){
	                			$('#'+jqGridId).jqGrid('editRow', nextRowId, true);		//다음 로우 편집 모드 변경
	                		}
	                		e.stopPropagation();	//이벤트 전파 중지
			                e.preventDefault(); 	//이벤트 취소
	                	}
	                }
	            }
	        } 
	    ]
	};
};

/**
 * jqGrid에서 현재 선택한 로우의 다음 로우의 id 구하기.
 * colModel중 key속성이 true경우 그 컬럼의 값이 row id임.
 * 없을 경우 row index가 row id임.
 * @param jqGridId	jqGrid ID
 */
var __irush_jqGridGetNextRowId = function(jqGridId){
	var isKey = false;
	var colModel = $('#'+jqGridId).getGridParam("colModel");
	if(colModel && colModel.length){
		for(var i = 0; i < colModel.length; i++){
			if(colModel[i].key){
				isKey = true;
			}
		}
	}
	var retRowId = -1;
	var selRowId = $('#'+jqGridId).getGridParam("selrow");
	
	if(isKey){
		var ids = $('#'+jqGridId).jqGrid('getDataIDs');
		if(ids && ids.length){
			for(var i = 0; i < ids.length; i++){
				if(ids[i] == selRowId){
					retRowId = ids[i+1];
				}
			}
		}
	} else {
		retRowId = parseInt(selRowId) + 1;
	}
	return retRowId;
};