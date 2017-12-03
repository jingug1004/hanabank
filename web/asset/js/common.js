/** 
 * @파일명 : common.js
 * @기능 : 공통 스크립트. 
 * @Comments : Commons.함수명() 형태로 호출.
 * 				공통적으로 적용되는 키 이벤트 및 ajax error 이벤트 설정.
 * @최초작성일 : 2014/10/17
 * @author : 김재갑  
 * @수정내역 : 
 */
var Commons = {
		
	/**
	 * 윈도우 open팝업 공통 스크립트	
	 * @param name		window name
	 * @param url		popup url
	 * @param width		popup width
	 * @param height	popup height	
	 */
	popupOpen : function(name,url,width,height){
		var sw  = screen.availWidth ;
		var sh  = screen.availHeight ;
		/*
		 * 2015-02-11 팝업 옵션 간소화
		var top = 0;
		var left = 0;
		*/
		// 모니터 화면  중앙 위치 구하기
		px=(sw - width)/2 ;
		py=(sh - height)/2 ;
		
		var set  = 'top=' + py + ',left=' + px ;
		set += ',width=' + width + ',height=' + height + ',toolbar=0,resizable=1,status=0,scrollbars=1';

		/*2015-02-11 팝업 옵션 간소화*/
		//var win = window.open(url,name,'width='+width+', height='+height+', top='+top+', left='+left+','+set);
		var win = window.open(url,name,set);
		
		if (win == null || typeof(win) == "undefined" || (win == null && win.outerWidth == 0) || (win != null && win.outerHeight == 0) || win.test == "undefined") {
			alert("팝업 차단 기능이 설정되어있습니다\n차단 기능을 해제(팝업허용) 한 후 다시 이용해 주십시오.");   
		}else{
			win.focus();
		}
		   
	},
	
	/**
	 * print div 본창 인쇄
	 * @returns {Boolean}	false
	 */
	printPage : function(){
		var initBody;
		window.onbeforeprint = function(){
			initBody = document.body.innerHTML;
			document.body.innerHTML =  document.getElementById('printarea').innerHTML;
		};
		
		window.onafterprint = function(){
			document.body.innerHTML = initBody;
		};
		
		window.print();
		return false;
	},
	
	/**
	 *  새창띄우고 인쇄
	 * @param title	새 창의 title 문구
	 */
	printPagePopup : function(title){
		var sw=screen.width;
		var sh=screen.height;
		var w=800;//팝업창 가로길이
		var h=600;//세로길이
		var xpos=(sw-w)/2; //화면에 띄울 위치
		var ypos=(sh-h)/2; //중앙에 띄웁니다.
	 
	    var pHeader="<html><head><link rel='stylesheet' type='text/css' href='/Exp_admin/css/print.css'><title>" + title + "</title></head><body>";
	    var pgetContent=document.getElementById("printarea").innerHTML + "<br>";
	 
	    //innerHTML을 이용하여 Div로 묶어준 부분을 가져옵니다.
	    var pFooter="</body></html>";
	    pContent=pHeader + pgetContent + pFooter;  
	  
	    pWin=window.open("","print","width=" + w +",height="+ h +",top=" + ypos + ",left="+ xpos +",status=yes,scrollbars=yes"); //동적인 새창을 띄웁니다.
	    pWin.document.open(); //팝업창 오픈
	    pWin.document.write(pContent); //새롭게 만든 html소스를 씁니다.
	    pWin.document.close(); //클로즈
	    pWin.print(); //윈도우 인쇄 창 띄우고
	    pWin.close(); //인쇄가 되던가 취소가 되면 팝업창을 닫습니다.
	},
	
	/**
	 * 탭추가 영역
	 * @param title		탭의 타이틀명
	 * @param url		탭 iframe의 url
	 * @param closable	탭의 닫기 여부. true면 x 버튼이 탭에 보여짐
	 */
	addTab : function (title, url, closable){
		
		if(typeof closable == 'undefined'){
			closable = true;
		}
		
		if ($('#tabs').tabs('exists', title)){
			$('#tabs').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
			$('#tabs').tabs('add',{
				title:title,
				content:content,
				closable:closable
			});
		}
		Commons.resizePanel();
	},
	
	/**
	 * 탭 닫기
	 * @param title	탭의 타이틀명
	 */
	closeTab : function (title){
		$('#tabs').tabs('close', title);
	},
	
	/**
	 * 엑셀 파일 다운로드
	 * @param downUrl	download url
	 */
	goExcelDownload : function(downUrl){
		var frm = document.frm;
		var originMethod = frm.method;
		
		frm.target = "excelDownFrame";
		frm.method = "post";
		frm.action = downUrl;
		frm.submit();
		
		// method 원상복구
		frm.method = originMethod;
	},
	
	/** 
	 * 	달력 초기값 셋팅
	 *  @param fromDate: 시작날짜. 이번달 1일로 날짜 셋팅
	 *  @param toDate: 종료날짜. 오늘 날짜 셋팅
	 */
	setDate : function(fromDate, toDate){
		var now = new Date();
		var year= now.getFullYear();
		var mon = (now.getMonth()+1)>9 ? ""+(now.getMonth()+1) : "0"+(now.getMonth()+1);
		var day = now.getDate()>9 ? ""+now.getDate() : "0"+now.getDate();
		
		var firstDayOfThisMonth = year + "-" + mon + "-01";
		var today = year + "-" + mon + "-" + day;
		
		$("#"+fromDate).val(firstDayOfThisMonth);
		$("#"+toDate).val(today);
	},
	
	/** 금액 3자리 마다 콤마찍기
	 *	콤마를 더한 금액을 반환한다.
	 *	@param str	금액
	 */
	addComma : function(str) {
	    str = String(str);
	    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	},
	
	/**
	 * 자릿수 앞자리 0 채우기
	 * > leadingZeros(12, 3) -> '012'
	 * @param n		변경할 문자열
	 * @param digits	0을 붙을 자릿수
	 */
	leadingZeros : function (n, digits) {
		var zero = '';
		n = n.toString();
		if (n.length < digits) {
			for (var idxZero = 0; idxZero < digits - n.length; idxZero++) {
				zero += '0';
			}
		}
		return zero + n;
	},
	
	/**
	 * jqGrid 클릭한 로우의 컬러를 변경하기
	 * @param gridNm	jqGrid id
	 * @param rowId		row id
	 */
	setClickRowColor : function(gridNm, rowId){
	    var ids = $('#'+gridNm).getDataIDs();
	    $.each(ids, function(idx){
	        idx+=1;
	        if(idx==rowId){
	            $('#'+gridNm).setRowData(idx, false, {background:'#DDFFFF'});
	        }else{
	            $('#'+gridNm).setRowData(idx, false, {background:'#FFFFFF'});
	        }
	    });
	},
	
	/**
	 * treeMenuLeft용 공통 Ajax
	 * treeMenuLeftAjax([type]) 
	 * @param type	
	 */
	treeMenuLeftAjax : function(type){
		$.ajax({
			type : "POST"
			       	, async : true
			    	, url : ONLINE_ROOT+"/mgmt/common/treeMenuAjax.do"
			    	, dataType : "json"
			    	, data : {type:type}
			    	, success : function(data) {
			    		
			    	if(data.menuList.length > 0 && typeof data.menuList != "undefined"){
			    		
			    		d = new dTree('d');
			    		
			    		d.add(0,-1,data.menuList[0].pgm_name);
			    		
			    		for(var menuIdx=0; menuIdx<data.menuList.length; menuIdx++){
			    			d.add(data.menuList[menuIdx].pgm_no,data.menuList[menuIdx].parent_pgm,data.menuList[menuIdx].pgm_name,data.menuList[menuIdx].pgm_id,data.menuList[menuIdx].pgm_name,"left");
			    		}
			    		
			    		leftTreeComplete(d);
			    	}
			    	
			    }
			    , error : function(request, status, error) {
				     alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
			    }
			});
	},
	
	/**
	 * treeMenuRight용 공통 Ajax
	 * 
	 * @param type
	 * @param param
	 */
	treeMenuRightAjax : function(type,param){
		
		if(param == "" || typeof param == "undefined"){
			param = "";
		}
		
		$.ajax({
			type : "POST"
			       	, async : true
			    	, url : ONLINE_ROOT+"/mgmt/common/treeMenuAjax.do"+param
			    	, dataType : "json"
			    	, data : {type:type}
			    	, success : function(data) {
		    	
			    	if(data.menuList.length > 0 && typeof data.menuList != "undefined"){
			    		
			    		
			    		d2 = new dTree('d2');
			    		
			    		d2.add(0,-1,data.menuList[0].pgm_name);
			    		
			    		for(var menuIdx=0; menuIdx<data.menuList.length; menuIdx++){
			    			d2.add(data.menuList[menuIdx].pgm_no,data.menuList[menuIdx].parent_pgm,data.menuList[menuIdx].pgm_name,data.menuList[menuIdx].pgm_id,data.menuList[menuIdx].pgm_name,"right");
			    		}
			    		
			    		rightTreeComplete(d2);
	
			    	}
			    	
			    }
			    , error : function(request, status, error) {
				     alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
			    }
			});
	},
	
	/**
	 * 금액값이 있을 경우 세자리마다 콤마(,)를 찍고
	 * 값이 없는 경우는 ''을 return
	 * @param cellvalue	변경할 금액값
	 * @returns	변경한 금액값
	 */
	integerFmt : function (cellvalue){
		if(cellvalue==0){
			return '';	
		}else{
			return Commons.addComma(cellvalue);	
		}
	},
	
	/**
	 * trim 앞뒤 공백 제거
	 * @param str	문자열
	 */
	trim : function (str){
		return str.replace( /(^\s*)|(\s*$)/g, "" );
	},
	
	/**
	 * 검색 결과가 있을 경우에 따라 인쇄, 엑셀 다운로드 기능하도록 함
	 * @param flag 기능 작동 여부
	 * @param type 기능 종류
	 * @param url
	 * @param id 인쇄 팝업 id
	 * @returns
	 */
	extraAction : function (flag, type, url, id){
		if (flag) {
			if ("print" == type) {
				Commons.popupOpen(id, url, '1024', '768');
			} else if ("excel" == type) {
				Commons.goExcelDownload(url);				
			}
		} else {
			alert("조회 결과가 없습니다.");
		}
	},
	
	/**
	 * 세션이 끊겼을때 로그인 페이지로 
	 * @param msgType	메시지 알림 타입.
	 */
	sessionOut : function(msgType){
		if(typeof msgType !== "undefined" && msgType == 'C'){
			if(!confirm("로그인 정보가 없습니다. 로그인 화면으로 이동하시겠습니까?")){
				return;
			}
		} else if(typeof msgType !== "undefined" && msgType == 'A'){
			alert("로그인 정보가 없습니다. 로그인 화면으로 이동하겠습니다.");
		} 
		
	    if(parent.parent && window!=window.top){	// iframe안에 iframe...
	    	parent.parent.Commons.goLogin();
	    } else if(parent && window!=window.top){	// iframe
			parent.Commons.goLogin();
		} else if(opener){	//팝업창
			if(opener.parent && opener.top && opener.top != opener && opener.parent.Commons){	//opener가 iframe
				opener.parent.Commons.goLogin();
				self.close();
			} else {	// opener가 top frame
				try{
					if(opener.Commons){
						opener.Commons.goLogin();
						self.close();
					} else {
						Commons.goLogin();
					}
				}catch(e){
					Commons.goLogin();
				}
				
			}
		} else {	//top frame
			Commons.goLogin();
		}
	},
	
	/**
	 * 로그인 페이지로 이동
	 */
	goLogin : function(){
		location.href = "/sale_on/member/login.do";
	},
	
	/**
	 * ajax error 처리
	 * @param event	event
	 * @param jqxhr	jqxhr
	 * @param settings	settings
	 */
	ajaxError : function(event, jqxhr, settings) {
		if(jqxhr.status == '200'){/*200은 성공*/}
		else if(jqxhr.status == '401'){alert("요청하신 URL에 대한 접근 권한이 없습니다.");Commons.sessionOut();}
		else if(jqxhr.status == '403'){Commons.sessionOut('A');}
		else if(jqxhr.status == '404'){alert("요청 URL을 찾을 수 없습니다. ( " + settings.url + ")");}
		else {console.log("Commons.ajaxError : code : " + jqxhr.status + "\r\nmessage : " + jqxhr.reponseText);}
	},
	
	/**
	 * jqQrid의 width, height를 조정해주는 것.
	 * @param tableId 리사이즈할 jqGrid의 테이블 ID, 입력을 안하거나 ''로 입력할 경우 해당 화면의 모든 jqGrid를 대상으로 한다. ['id1','id2'] 형식으로 입력 가능.
	 * @param width jqGrid의 전체 width값. ''나 true나 auto로 입력할 경우 자동조절, 숫자로 입력할 경우 해당 width적용, false 입력할 경우 변경 안 함
	 * @param height jqGrid의 전체 height값. ''나 true나 auto로 입력할 경우 자동조절, 100%로 입력할 경우 창 높이를 기준으로 꽉차게 조절, 숫자로 입력할 경우 해당 height적용, false 입력할 경우 변경 안 함
	 * @param bottomHeight jqGrid의 전체 height값에서 뺄 수치. jqgrid밑 부분의 높이를 넘겨준다. 안 넘겨줄 경우 20으로 처리.
	 * @param autoColWidth jqGrid의 width를 조정할때 컬럼의 width를 자동으로 조절하려면 true, 유지하려면 false. 
	 */
	resizeJqGrid : function(tableId, width, height, autoColWidth, bottomHeight) {
		// tableId가 여러개일 경우
		var arrTableId = new Array();
		if(typeof tableId === 'undefined' || tableId == ''){	// tableId가 공백으로 올 경우 현재 화면의 모든 jqGrid를 구한다.
			$('div[id^="gbox_"]').each(function(idx){
				arrTableId.push($(this).attr('id').replace('gbox_', ''));
			});
		} else if(typeof tableId === 'object' && tableId.length){	// tableId가 배열로 올 경우
			$.each(tableId, function(idx){
				arrTableId.push(tableId[idx]);
			});	
		} else {	// 그외 경우 문자열로 인식
			arrTableId.push(tableId);
		}
		
		
		//width 인수값 셋팅
		if(typeof width === 'undefined' || width == '' || width == 'auto' || width == 'true' || isNaN(width)){
			width = true;
		}
		
		//height 인수값 셋팅
		if(typeof height === 'undefined' || height == '' || height == 'auto' || height == 'true' || (height != '100%' && isNaN(height))){
			height = true;
		}
		
		//autoColWidth 인수값 셋팅
		if(typeof autoColWidth !== 'undefined' && (autoColWidth == false || autoColWidth == 'false')){
			autoColWidth = false;
		} else {
			autoColWidth = true;
		}
		
		//bottomHeight 인수값 셋팅
		if(typeof bottomHeight === 'undefined' || bottomHeight == '' || isNaN(bottomHeight)){
			bottomHeight = 20;
		}
		
		
		// 위에서 등록된 arrTableId를 루프를 돌면서 jqGrid를 리사이즈 한다.
		$.each(arrTableId, function(idx){
			tableId = arrTableId[idx];
			
			// 현재 jqGrid의 top 값을 구하고
			var tableTop = 0;
			if($('#gbox_'+tableId).offset()){
				tableTop = parseInt($('#gbox_'+tableId).offset().top, 10);
			}
			
			// 프레임 맨 밑의 여백 수치.
			var marginBottom = bottomHeight;
			
			// 프레임의 높이.
			var frameHeight = $(window).innerHeight();
			
			// iframe일 경우 부모창의 .tabs-panels의 height를 기준으로, 아닐 경우 window.innerHeight를 기준으로
			if(parent){
				if($('.tabs-panels', parent.document).size() > 0){
					frameHeight = parseInt($('.tabs-panels', parent.document).height(), 10);
					marginBottom = bottomHeight;
				}
			}
			
			// jqgrid 밑 부분의 높이. 해당 테이블 밑에 엘리멘트가 존재하는 경우나 패딩이 있을 경우.
			var wrapBottom = $(document).innerHeight() - tableTop - $('#gbox_'+tableId).height();
			if(height == true){
				wrapBottom = 0;	// auto일 경우 jqGrid의 height 변경될 수 있어서 밑 부분의 높이가 정확하지 않음.
			}
			
			// paging paper 부분이 있는 경우
			var pagerId = $('#'+tableId).getGridParam('pager');
			var pagerHeight = 0;
			if(pagerId && pagerId.length > 0){
				pagerHeight = $(pagerId).height();
			}
			
			// jqGrid에 적용할 height값. 
			// jqGrid가 속한 프레임의 height에서 현재 jqGrid의 top 수치와 paging paper높이, jqgrid 밑 부분의 높이. 밑의 여백 수치를 뺀다. 
			var bdivHeight = frameHeight - tableTop - pagerHeight - wrapBottom - marginBottom;
			
			// jqgrid옵션의 height값
			var jqGridOpt_height = 0;
			if(!isNaN($('#'+tableId).getGridParam('height'))){
				jqGridOpt_height = $('#'+tableId).getGridParam('height');
			}
			console.log('bdivHeight ' + bdivHeight);
			// 옵션에 따라서 최종 적용할 height값을 정한다.
			if(height == true){
				//자동조절이므로 jqgrid 높이 설정값, 실제 데이터 로우의 height값, bdivHeight값 중 가장 작은 값을 적용한다.
				var arrHeight = [jqGridOpt_height, bdivHeight, $('#'+tableId).height()];
				var min = bdivHeight;
				$.each(arrHeight, function(idx){
					if(min > arrHeight[idx]){
						min = arrHeight[idx];
					}
				});
				console.log('min '+min);
				bdivHeight = min;
			} else if(height == '100%'){
				// 100%이므로 bdivHeight 값 그대로 적용한다.
				console.log('height == 100%');
			} else if(!isNaN(height) && height > 0){
				bdivHeight = height;
				console.log('height == 숫자');
			} else {
				bdivHeight = 0;
				console.log('height == 0');
			}			
			console.log('bdivHeight ' + bdivHeight);
			// jqGrid height 설정
			if(bdivHeight > 0){
				$('#'+tableId).setGridHeight(bdivHeight);
			}
			
			
			// 데이터가 없을 경우를 대비해 min-height 설정한다.
			if($('#'+tableId).height() < 23){
				$('#'+tableId).parents('div.ui-jqgrid-bdiv').css('min-height','23px');
			} 
			
			// jqGrid width 설정
			if(width == true){
				$('#'+tableId).setGridWidth($('#gbox_'+tableId).parent().width(), autoColWidth);
			} else if(!isNaN(width)){
				$('#'+tableId).setGridWidth(width, autoColWidth);
			}
			
			
		});
	},
	
	/**
	 * 메인 tabs의 panel부분 사이즈
	 */
	resizePanel : function() {
		var frameHeight = window.innerHeight;
		if(typeof frameHeight == 'undefined' || isNaN(frameHeight)){
			frameHeight = document.documentElement.clientHeight;
		}
		var footerHeight = $('div.wrap_foot').height();	
		var panelsTop = parseInt($('div.tabs-panels').offset().top, 10);
		var hMargin = 7;
		$('div.tabs-panels .panel:visible').height(frameHeight - panelsTop - footerHeight - hMargin);
		var panelPadding = 0;
		if($('.tabs-panels .panel > div').css('padding') && '' != $('.tabs-panels .panel > div').css('padding')){
			panelPadding = parseInt($('.tabs-panels .panel > div').css('padding').replace('px', ''), 10);
		}
		$('div.tabs-panels .panel:visible iframe').height($('.tabs-panels .panel:visible').height() - panelPadding);
	},
	
	/**
	 * 쿠키생성
	 * @param cname	cookie 명
	 * @param cvalue	cookie 값
	 * @param exdays	cookie 지속일. -1이면 cookie삭제
	 */
	setCookie : function(cname, cvalue, exdays) {
	    var d = new Date();
	    d.setTime(d.getTime() + (exdays*24*60*60*1000));
	    var expires = "expires="+d.toUTCString();
	    document.cookie = cname + "=" + cvalue + "; " + expires;
	},
	
	/**
	 * cookie 조회
	 * @param cname	cookie 명
	 * @returns
	 */
	getCookie : function(cname) {
	    var name = cname + "=";
	    var ca = document.cookie.split(';');
	    for(var i=0; i<ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0)==' ') c = c.substring(1);
	        if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
	    }
	    return "";
	}, 
	
	/**
	 * 문자열중 숫자만 뽑아서 리턴
	 * @param str_value	문자열
	 */
	numberFilter : function(str_value){
		return str_value.replace(/[^0-9]/gi, ""); 
	},
	
	/**
	 * 구매번호 형식 변환
	 * @param cellvalue	구매번호
	 * @returns	변환된 구매번호
	 */
	gumaeNo : function(cellvalue){
		str = String(cellvalue);
	    return str.replace(/(\d)(?=(?:\d{4})+(?!\d))/g, '$1-');
	},
	
	/**
	 * 세로고침 방지
	 * @returns {Boolean}
	 */
	LockF5 : function(){
		if (event.keyCode == 116) {
			event.keyCode = 0;
			return false;
		}
	},
	
	/**
	 * 숫자 배열 중간에 값이 없는 index의 값을 0으로 입력
	 * @param array	숫자 배열
	 * @returns
	 */
	rep0UndefInArr : function(array){
		// 데이터가 없는 index에 0을 삽입함.
		for(var idx=0; idx < array.length; idx++){
			if(typeof array[idx] == 'undefined' || isNaN(array[idx])){
				array[idx] = 0;
			}
		}
		return array;
	},
	
	/**
	 * chart.js legend(범례 생성)
	 * @param parent	legend가 삽입될 부분의 element
	 * @param data		차트에 사용된 data set
	 */
	legend : function(parent, data) {
		parent.className = 'legend';
	    var datas = data.hasOwnProperty('datasets') ? data.datasets : data;
	    
	    // remove possible children of the parent
	    while(parent.hasChildNodes()) {
	        parent.removeChild(parent.lastChild);
	    }
	    
	    for(var i=0; i < datas.length; i++){
	    	var d = datas[i];
	    	var title = document.createElement('span');
	        title.className = 'title';
	        parent.appendChild(title);
	        
	        var colorSample = document.createElement('div');
	        colorSample.className = 'color-sample';
	        try{
	        	colorSample.style.backgroundColor = d.hasOwnProperty('strokeColor') ? d.strokeColor : d.color;
	        }catch(e){
	        	colorSample.style.backgroundColor = Commons.findRGB((d.hasOwnProperty('strokeColor') ? d.strokeColor : d.color));
	        }
	        try{
	        	colorSample.style.borderColor = d.hasOwnProperty('fillColor') ? d.fillColor : d.color;
	        }catch(e){
	        	colorSample.style.borderColor = Commons.findRGB((d.hasOwnProperty('fillColor') ? d.fillColor : d.color));
	        }
	        title.appendChild(colorSample);

	        var text = document.createTextNode(d.label);
	        title.appendChild(text);
	    }
	},
	
	/**
	 * rgba => rgb. 문자열 단순 변환임.
	 * @param colorStr	rgba 문자열
	 * @returns {String}	rgb문자열
	 */
	findRGB : function(colorStr){
		if(typeof colorStr === 'string'){
			var match = /([\d]{1,3})[^\d]+([\d]{1,3})[^\d]+([\d]{1,3})/.exec(colorStr);
			if(match && match.length > 3) {
				return 'rgb(' + parseInt(match[1], 10) + ',' + parseInt(match[2], 10) + ',' + parseInt(match[3], 10) + ')';
			}
		}
		
		return "";
	},
	
	/**
	 * jqGrid 편집모드에서 tab키 이동 적용. 해당 컬럼에서 tab키를 입력시 다음 로우로 이동한다.
	 * colModel 옵션중 컬럼 속성중에 editOptions 값을 생성해서 리턴해준다.
	 * colModel 옵션중 편집 모드로 사용되는 컬럼중 마지막 컬럼에만 세팅해준다.
	 * @param jqGridId	jqGrid ID
	 * @param saveRowYn	값이 없거나 N이면 다음 로우 셀렉트만, Y이면 현재 로우 저장하고 다음 로우 셀렉트한다.
	 * @param callbackFunc	tab키나 엔터키 클릭시 호출할 callback함수. 인수로 '현재 row id', '다음 row id' 를 넘겨준다.
	 */
	jqgridEditOptions : function(jqGridId, saveRowYn, callbackFunc){
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
		                	var selRowId = $('#'+jqGridId).getGridParam("selrow");	//현재 로우 id 
				            var nextRowId = Commons.jqGridGetNextRowId(jqGridId); 		//다음 로우 id 
				                
		                	if(nextRowId && nextRowId != null){
		                		if(saveRowYn == 'Y'){
		                			$('#'+jqGridId).jqGrid('saveRow', selRowId);			//현재 로우 저장
		                			$('#'+jqGridId).jqGrid('restoreRow', selRowId);			//현재 로우 편집 모드 해제
		                		}
		                		$('#'+jqGridId).jqGrid('setSelection', nextRowId, true); //다음 로우 셀렉트
		                		
		                		if(saveRowYn == 'Y'){
		                			$('#'+jqGridId).jqGrid('editRow', nextRowId, true);		//다음 로우 편집 모드 변경
		                		}
		                		
		                	}
		                	
		                	callbackFunc = (typeof callbackFunc == "function") ? callbackFunc : window[callbackFunc];  	//callbackFunc의 type이 function이 아닐 경우 window에서 해당 이름을 가진 객체를 찾는다. 
	                		if(typeof callbackFunc == "function"){		//callbackFunc가 함수일 경우
	                			callbackFunc.call(this, selRowId, nextRowId);		//인수로 '현재 row id', '다음 row id' 를 넘겨주면서 함수 호출
	                		}
		                	
		                	e.stopPropagation();	//이벤트 전파 중지
			                e.preventDefault(); 	//이벤트 취소
		                	
		                } else if (key == 13){	//enter key
		                	var selRowId = $('#'+jqGridId).getGridParam("selrow");	//현재 로우 id
				            var nextRowId = Commons.jqGridGetNextRowId(jqGridId); 		//다음 로우 id
				            
				            if(nextRowId && nextRowId != null){
				            	if(saveRowYn == 'Y'){
		                			$('#'+jqGridId).jqGrid('saveRow', selRowId);			//현재 로우 저장
		                			$('#'+jqGridId).jqGrid('restoreRow', selRowId);			//현재 로우 편집 모드 해제
		                		}
		                		$('#'+jqGridId).jqGrid('setSelection', nextRowId, true); //다음 로우 셀렉트
		                		
		                		if(saveRowYn == 'Y'){
		                			$('#'+jqGridId).jqGrid('editRow', nextRowId, true);		//다음 로우 편집 모드 변경
		                		}
		                	}
				            
				            callbackFunc = (typeof callbackFunc == "function") ? callbackFunc : window[callbackFunc];  	//callbackFunc의 type이 function이 아닐 경우 window에서 해당 이름을 가진 객체를 찾는다. 
	                		if(typeof callbackFunc == "function"){		//callbackFunc가 함수일 경우
	                			callbackFunc.call(this, selRowId, nextRowId);		//인수로 '현재 row id', '다음 row id' 를 넘겨주면서 함수 호출
	                		}
		                	
		                	e.stopPropagation();	//이벤트 전파 중지
			                e.preventDefault(); 	//이벤트 취소
		                }
		            }
		        } 
		    ]
		};
	},
	
	/**
	 * jqGrid에서 현재 선택한 로우(or 인자로 넘겨받은 로우)의 다음 로우의 id 구하기.
	 * colModel중 key속성이 true경우 그 컬럼의 값이 row id임.
	 * 없을 경우 row index가 row id임.
	 * @param jqGridId	jqGrid ID
	 * @param selRowId	selRowId 다음 로우의 ID를 구하기 위한 현재 로우의 id. 없으면 현재 선택된 로우의 ID로 대체됨
	 */
	jqGridGetNextRowId : function(jqGridId, selRowId){
		var isKey = false;		//colModel에서 key속성이 true인 컬럼의 여부
		var colModel = $('#'+jqGridId).getGridParam("colModel");
		if(colModel && colModel.length){
			for(var i = 0; i < colModel.length; i++){
				if(colModel[i].key){
					isKey = true;
				}
			}
		}
		
		var retRowId = -1;		//리턴값					
		if(selRowId === undefined || selRowId == ''){	// 인자값이 없으면 현재 선택된 로우의 ID로 대체됨
			selRowId = $('#'+jqGridId).getGridParam("selrow");
		}
		
		/*
		 * colModel중 key속성이 true경우 그 컬럼의 값을 구해서 넘겨주고,
		 * false인 경우 index값이므로 +1해서 넘겨준다.
		 */
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
	},
	
	/**
	 * jqGrid에서 입력받은 colName에 해당하는 컬럼이 편집모드 컬럼중에서 마지막 index에 해당하는 지 여부
	 * 예) editoptions : 	Commons.jqgridEditOptionsEnter('grid_list', 'supply_vat')
	 * @param jqGridId	jqGrid ID
	 * @param colName	colModel에 사용되는 컬럼 속성중 name에 해당하는 값
	 */
	jqGridGetLastEditColYn : function(jqGridId, colName){
		var colModel = $('#'+jqGridId).getGridParam("colModel");	//jqGrid의 colModel 옵션정보를 가져옴
		var colNameIndex = -1;		//colName에 해당하는 컬럼 인덱스
		var lastEditColIndex = -1;		//편집모드 컬럼중에서 마지막 index
		if(colModel && colModel.length){
			for(var i = 0; i < colModel.length; i++){
				if(colModel[i].name ==  colName){	
					colNameIndex = i;
				}
				
				if(colModel[i].editable){
					lastEditColIndex = i;
				}
			}
		}
		
		/*
		 * colName에 해당하는 컬럼 인덱스와 편집모드 컬럼중에서 마지막 index가 같으면서 -1보다 큰 경우만 Y로 리턴.
		 */
		if(colNameIndex > -1 && lastEditColIndex == colNameIndex){
			return 'Y';
		} else {
			return 'N';
		}
	},
	
	/**
	 * jqGrid 편집모드에서 tab키/enter키 이동 적용
	 * @param jqGridId	jqGrid ID
	 * @param colName	colModel에 사용되는 컬럼 속성중 name에 해당하는 값
	 * @param saveRowYn	값이 없거나 N이면 다음 로우 셀렉트만, Y이면 현재 로우 저장하고 다음 로우 셀렉트한다.
	 */
	jqgridEditOptionsEnter : function(jqGridId, colName, saveRowYn){
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
		                	var selRowId = $('#'+jqGridId).getGridParam("selrow");	//현재 로우 id 
				            var nextRowId = Commons.jqGridGetNextRowId(jqGridId); 		//다음 로우 id 
				            var lastColYn = Commons.jqGridGetLastEditColYn(jqGridId, colName); 		//현재 컬럼의 마지막 편집모드 컬럼 여부
				            
				            if(lastColYn == 'Y'){
				            	if(saveRowYn == 'Y'){
		                			$('#'+jqGridId).jqGrid('saveRow', selRowId);			//현재 로우 저장
		                			$('#'+jqGridId).jqGrid('restoreRow', selRowId);			//현재 로우 편집 모드 해제
		                		}
		                		$('#'+jqGridId).jqGrid('setSelection', nextRowId, true); //다음 로우 셀렉트
		                		
		                		if(saveRowYn == 'Y'){
		                			$('#'+jqGridId).jqGrid('editRow', nextRowId, true);		//다음 로우 편집 모드 변경
		                		}
		                		
		                		callbackFunc = (typeof callbackFunc == "function") ? callbackFunc : window[callbackFunc];  	//callbackFunc의 type이 function이 아닐 경우 window에서 해당 이름을 가진 객체를 찾는다. 
		                		if(typeof callbackFunc == "function"){		//callbackFunc가 함수일 경우
		                			callbackFunc.call(this, selRowId, nextRowId);		//인수로 '현재 row id', '다음 row id' 를 넘겨주면서 함수 호출
		                		}
		                		
			                	e.stopPropagation();	//이벤트 전파 중지
				                e.preventDefault(); 	//이벤트 취소
				            }
		                	
		                } else if (key == 13){	//enter key
		                	var selRowId = $('#'+jqGridId).getGridParam("selrow");	//현재 로우 id
				            var nextRowId = Commons.jqGridGetNextRowId(jqGridId); 		//다음 로우 id
				            
				            if(nextRowId && nextRowId != null){
				            	if(saveRowYn == 'Y'){
		                			$('#'+jqGridId).jqGrid('saveRow', selRowId);			//현재 로우 저장
		                			$('#'+jqGridId).jqGrid('restoreRow', selRowId);			//현재 로우 편집 모드 해제
		                		}
		                		$('#'+jqGridId).jqGrid('setSelection', nextRowId, true); //다음 로우 셀렉트
		                		
		                		if(saveRowYn == 'Y'){
		                			$('#'+jqGridId).jqGrid('editRow', nextRowId, true);		//다음 로우 편집 모드 변경
		                		}
		                		setTimeout("$('#"+nextRowId+"_"+colName+"'"+").focus();", 10);	//다음 로우 현재 컬럼으로 포커스. 편집모드가 완료되기 전에 포커스가 이동되서 setTimeout 사용함.
		                	}
				            
				            callbackFunc = (typeof callbackFunc == "function") ? callbackFunc : window[callbackFunc];  	//callbackFunc의 type이 function이 아닐 경우 window에서 해당 이름을 가진 객체를 찾는다. 
	                		if(typeof callbackFunc == "function"){		//callbackFunc가 함수일 경우
	                			callbackFunc.call(this, selRowId, nextRowId);		//인수로 '현재 row id', '다음 row id' 를 넘겨주면서 함수 호출
	                		}
				            
				            e.stopPropagation();	//이벤트 전파 중지
			                e.preventDefault(); 	//이벤트 취소
		                }
		            }
		        } 
		    ]
		};
	}
	
};

/**
 * 화면의 DOM 객체가 준비되었을 때 전체 화면에 공통적으로 적용되는 이벤트 바인딩
 */ 
$(document).ready(function(){ 
	//공통 팝업닫기 스크립트 추가
	$(".close").bind("click",function(){
		self.close();
	});
	
	//특정 키를 눌렀을 때 기본 이벤트 작동 금지
	$("body").on("keydown", function(event){
		//백스페이스키 눌렀을때 뒤로 가는 거 방지
		if(event.keyCode== 8){
			var allowTagTypes = ['text', 'password', 'textarea'];		//백스페이스키 눌렀을때 작동 허용하는 tag types
			var type = event.target.type === undefined ? event.target.tagName : event.target.type;
			var isAllow = false;	//백스페이스키 눌렀을때 작동 허용하는 여부
			for(var i = 0; i < allowTagTypes.length; i++){
				if(allowTagTypes[i] == type.toLowerCase()){
					if(event.target.readOnly){	//readOnly 속성이 있을 경우 
						isAllow = false;		//백스페이스키 눌렀을때 작동 허용 안함
					} else {
						isAllow = true;		//백스페이스키 눌렀을때 작동 허용	
					}
				}
				
				
			}
			if(!isAllow){
				event.stopPropagation();	//이벤트 전파 중지
	            event.preventDefault(); 	//이벤트 취소
			}
		}
		
		//F5키 새로고침 방지
		if (event.keyCode == 116) {
			event.stopPropagation();	//이벤트 전파 중지
            event.preventDefault(); 	//이벤트 취소
		}
	});
	
});

/*
 * ajax 통신시 에러발생할 경우 공통 callback 함수 적용.
 */
$(document).ajaxError(function(event, jqxhr, settings) {
	Commons.ajaxError(event, jqxhr, settings);
});

/*
 *	우클릭 방지
 */
$(document).on("contextmenu", function(event){
	event.stopPropagation();	//이벤트 전파 중지
    event.preventDefault(); 	//이벤트 취소
    return false;
});




