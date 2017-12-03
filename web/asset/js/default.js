/** 
 * @파일명 : default.js
 * @기능 :  화면 resize, gnb, tab 관련 화면 컨트롤 기능
 * @Comments : 퍼블리싱 관련 화면 컨트롤하는 js
 * @최초작성일 : 2014/10/20
 * @author : 류중열  
 * @수정내역 : 
 */
$(document).ready(function() {
	resize();
	gnb();
	datepicker();
	tabControll();
	
	$(".tabs").css({"height":"26px"});
	
	
	$(".pl_file_btn").click(function() {
		$('#file').click();                             
	});
	
	$(".pl_photo_btn").click(function() {
		$('#itemPhoto').click();                             
	});
});

/**
 * 화면 resize시 tab관련 width값 변경
 */
function resize() {
	$(window).resize(function() {
		$(".panel-body").css({"width":"100%"});
		$(".tabs-header .tabs-wrap").css({"width":"100%"});
	});
}

/**
 * gnb 셋팅 및 기능 정의, 화면 제어 이벤트 설정
 */
function gnb() {
	var sp = 100;
	
	var onGnbOver = function() {
		var num = Number($(this).parent().index() + 1);
		$(this).css({"background":"url('/sale_on/asset/img/gnb_icon_0"+num+"_on.jpg') no-repeat 0 0"});
		$(this).parent().each(function() {
			$(this).parent().find(".wrap_sub_top").fadeOut(sp);
		});
		$(this).next().fadeIn(sp);
	};
	
	var onGnbOut = function() {
		var num = Number($(this).index() + 1);
		if (!$(this).children().eq(0).hasClass('active')) {
			$(this).children().eq(0).css({"background":"url('/sale_on/asset/img/gnb_icon_0"+num+".jpg') no-repeat 0 0"});
		}
		$(this).find(".wrap_sub_top").fadeOut(sp);
	};
	
	var onGnbClick = function() {
		$(".wrap_sub_top").fadeOut(sp);
	};
	
	$(".gnb > li > a").on("mouseover onfocus", onGnbOver);
	$(".gnb > li").on("mouseleave focusout", onGnbOut);
	$(".wrap_sub_top a").on("click", onGnbClick);
	
}


/**
 * 날짜 선택
 */
function datepicker() {
	$(".carendar, .btn_date").click(function() {
		$(this).prev().datepicker({"dateFormat":"yy-mm-dd"});
		$(this).prev().focus();
	});
};


/**
 * tab 제어
 */
function tabControll() {
	$("div.tab_all:not("+$("ul.customer_teb li a.active").attr("href")+")").hide();
	$("ul.customer_teb li a").click(function(){
		$("ul.customer_teb li a").removeClass("active");
		$(this).addClass("active");
		$("div.tab_all").hide();
		$($(this).attr("href")).show();
		return false;
	});
};

/**
 * 파일 선택시 파일명 셋팅
 */
function fileNameInput() {
	var fName = $("#file").val().split("\\");
	$("#file_name").val(fName[fName.length-1]);
}