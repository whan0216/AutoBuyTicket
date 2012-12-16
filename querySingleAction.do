





 










<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">



<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta name="Robots"   content="none"/>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="Cache-Control"  content="no-cache"/> 
<meta http-equiv="pragma"  content="no-cache"/> 
<title>车票预订</title>

<link  href="/otsweb/css/style.css" rel="stylesheet"  type="text/css"/>
<link  href="/otsweb/css/newsty.css" rel="stylesheet"  type="text/css"/>
<link  href="/otsweb/css/contact.css" rel="stylesheet"  type="text/css"/>
<link href="/otsweb/css/validation.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/otsweb/css/easyui.css"/>
<link rel="stylesheet" type="text/css" href="/otsweb/css/suggest.css"/>
<link href="/otsweb/css/cupertino/jquery-ui-1.8.2.custom.css" rel="stylesheet" type="text/css" />
<link href="/otsweb/css/ots_common.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="http://www.12306.cn/mormhweb/images/favicon.ico"/>
<script type="text/javascript" src="/otsweb/js/common/iepngfix_tilebg.js?version=5.1"></script>
<script src="/otsweb/js/common/jquery-1.4.2.min.js?version=5.1" type="text/javascript"></script>
<script src="/otsweb/js/common/autoHeight.js?version=5.1" type="text/javascript"></script>
<script src="/otsweb/js/common/jquery.bgiframe.min.js?version=5.1" type="text/javascript"></script>
<script src="/otsweb/js/common/jquery.easyui.min.js?version=5.1" type="text/javascript"></script>
<script src="/otsweb/js/common/jquery.form.js?version=5.1" type="text/javascript"></script>
<script src="/otsweb/js/common/datepicker/WdatePicker.js?version=5.1" type="text/javascript"></script>
<script src="/otsweb/js/common/j.suggest.js?version=5.1" type="text/javascript"></script>
<script src="/otsweb/js/common/school_display1.js?version=5.1" type="text/javascript"></script>
<script src="/otsweb/js/common/school_suggest1.js?version=5.1" type="text/javascript"></script>
<script src="/otsweb/js/common/city_name_suggest.js?version=5.1" type="text/javascript"></script>
<script src="/otsweb/js/common/trainCodeSuggest.js?version=5.1" type="text/javascript"></script>
<script src="/otsweb/js/common/date.js?version=5.1" type="text/javascript"></script>
<script src="/otsweb/js/common/sidetag.js?version=5.1" type="text/javascript"></script>


<script>
   $(document).ready(function(){
	   if(parent.notLogin==undefined) {
		   parent.location='/otsweb' + '/main.jsp';
		   return;
		   }
	        var isHide = "";
	        if (isHide == "true") {
		        if(parent.hideMenu){
		        	//parent.hideMenu();
			    }
		     } else {
		    	 if(parent.showMenu){
		    		 parent.showMenu();
				 }
			 }
			 var clicktitle = '车票预订';
			 if(parent.clickMenu&&clicktitle!==''){
				 parent.clickMenu('车票预订');
		     }

			 
			 var isLogin= true
			 var u_name = '王瀚';
			 if (isLogin) {
			  parent.hasLogin(u_name);
	  	     }else{
	  	  	  parent.notLogin();
	      	 }
	 });
</script>
</head>
<body>
<!-- dhtmlxGrid表格控件 -->
<script src="/otsweb/dhtmlxGrid/codebase/dhtmlxcommon.js?version=5.1"
	type="text/javascript"></script>
<script src="/otsweb/dhtmlxGrid/codebase/dhtmlxgrid.js?version=5.1"
	type="text/javascript"></script>
<script
	src="/otsweb/dhtmlxGrid/codebase/dhtmlxgridcell.js?version=5.1"
	type="text/javascript"></script>
<script
	src="/otsweb/dhtmlxGrid/codebase/ext/dhtmlxgrid_fast.js?version=5.1"
	type="text/javascript"></script>
<link href="/otsweb/dhtmlxGrid/codebase/dhtmlxgrid_skins.css"
	rel="stylesheet" type="text/css" />
<link href="/otsweb/dhtmlxGrid/codebase/dhtmlxgrid.css" rel="stylesheet"
	type="text/css" />

<link href="/otsweb/css/table.css" rel="stylesheet" type="text/css" />
<link href="/otsweb/css/station.css" rel="stylesheet" type="text/css" />
<script src="/otsweb/js/common/station_name.js?version=5.1"
	type="text/javascript"></script>
<script src="/otsweb/js/common/favorite_name.js?version=5.1"
	type="text/javascript"></script>
<script src="/otsweb/js/common/city_name.js?version=5.1"
	type="text/javascript"></script>
<script>
	$(document).ready(function(){
		if('' == "go") {
			$("#roundRadio").click();
		}
		/**
		if($("#station_name_show").val() == 'show') {
			if($("#fromStationText").val() == null || $("#fromStationText").val() == "") {
				$("#fromStationText").val("简码/汉字");
				$("#fromStation").val("");
				
			}
			if($("#toStationText").val() == null || $("#toStationText").val() == "") {
				$("#toStationText").val("简码/汉字");
				$("#toStation").val("");
			}
		}
		**/
	});
 
	var ctx ='/otsweb';
	var dhtmlx_img_path = ctx + '/dhtmlxGrid/codebase/grid_imgs/';
    var linkAddress = 'http://dynamic.12306.cn/TrainQuery/skbcx.jsp?';
	var maxPeriod = '2012-12-27 21:25:20';
	var minPeriod = '2012-12-16 21:25:20';
	var maxTicketNum = '5';
	var index_seatTypeAndNum = '';
	var index_train_class = '';
	  //设置查询结果
	var indexQueryResult = "null";
    var seatTypeRelation = jQuery.parseJSON('{"D":[{"end_station_name":"","end_time":"","id":"4","start_station_name":"","start_time":"","value":"软卧"},{"end_station_name":"","end_time":"","id":"6","start_station_name":"","start_time":"","value":"高级软卧"},{"end_station_name":"","end_time":"","id":"9","start_station_name":"","start_time":"","value":"商务座"},{"end_station_name":"","end_time":"","id":"M","start_station_name":"","start_time":"","value":"一等座"},{"end_station_name":"","end_time":"","id":"O","start_station_name":"","start_time":"","value":"二等座"},{"end_station_name":"","end_time":"","id":"P","start_station_name":"","start_time":"","value":"特等座"},{"end_station_name":"","end_time":"","id":"Q","start_station_name":"","start_time":"","value":"观光座"},{"end_station_name":"","end_time":"","id":"S","start_station_name":"","start_time":"","value":"一等包座"}],"Z":[{"end_station_name":"","end_time":"","id":"1","start_station_name":"","start_time":"","value":"硬座"},{"end_station_name":"","end_time":"","id":"2","start_station_name":"","start_time":"","value":"软座"},{"end_station_name":"","end_time":"","id":"3","start_station_name":"","start_time":"","value":"硬卧"},{"end_station_name":"","end_time":"","id":"4","start_station_name":"","start_time":"","value":"软卧"},{"end_station_name":"","end_time":"","id":"6","start_station_name":"","start_time":"","value":"高级软卧"}],"T":[{"end_station_name":"","end_time":"","id":"1","start_station_name":"","start_time":"","value":"硬座"},{"end_station_name":"","end_time":"","id":"2","start_station_name":"","start_time":"","value":"软座"},{"end_station_name":"","end_time":"","id":"3","start_station_name":"","start_time":"","value":"硬卧"},{"end_station_name":"","end_time":"","id":"4","start_station_name":"","start_time":"","value":"软卧"},{"end_station_name":"","end_time":"","id":"6","start_station_name":"","start_time":"","value":"高级软卧"}],"K":[{"end_station_name":"","end_time":"","id":"1","start_station_name":"","start_time":"","value":"硬座"},{"end_station_name":"","end_time":"","id":"2","start_station_name":"","start_time":"","value":"软座"},{"end_station_name":"","end_time":"","id":"3","start_station_name":"","start_time":"","value":"硬卧"},{"end_station_name":"","end_time":"","id":"4","start_station_name":"","start_time":"","value":"软卧"},{"end_station_name":"","end_time":"","id":"6","start_station_name":"","start_time":"","value":"高级软卧"}],"QT":[{"end_station_name":"","end_time":"","id":"1","start_station_name":"","start_time":"","value":"硬座"},{"end_station_name":"","end_time":"","id":"2","start_station_name":"","start_time":"","value":"软座"},{"end_station_name":"","end_time":"","id":"3","start_station_name":"","start_time":"","value":"硬卧"},{"end_station_name":"","end_time":"","id":"4","start_station_name":"","start_time":"","value":"软卧"},{"end_station_name":"","end_time":"","id":"6","start_station_name":"","start_time":"","value":"高级软卧"}]}');
    var passengerTypesString = "";
    var studentTicketStartDate = "06-01 00:00:00&12-01 00:00:00&01-01 00:00:00";
	var studentTicketEndDate = "09-30 00:00:00&12-31 00:00:00&03-31 00:00:00";
	var canBuyStudentTicket = "N";
	var clickBuyStudentTicket = "null";
	if (clickBuyStudentTicket.length == 0) {
		clickBuyStudentTicket.length = "N";
	}

	var stu_buy_date = "2012-12-16&2012-12-27";
	var other_buy_date = "2012-12-16&2012-12-27";
	var stu_can_buy = stu_buy_date.split("&");
	var other_can_buy = other_buy_date.split("&");
	
	
	$(function() {
      	if(indexQueryResult == null || indexQueryResult == 'undefined' || indexQueryResult == 'null' && indexQueryResult != "") {
          	// 默认显示当天日期
          	if($('#startdatepicker').val() == null || $('#startdatepicker').val() == "" || $('#roundTrainDate').val() == null || $('#roundTrainDate').val() == "") {
				$('#startdatepicker').val('2012-12-16');
				$('#roundTrainDate').val('2012-12-16');
            } else {
            	if(isStudentTicketDateValid()) {
            		canBuyStudentTicket = 'Y';
            	}
            }
	    } else {
			$('#train_date').val('2012-12-16');
			$('#from_station_telecode').val('null');
			$('#to_station_telecode').val('null');
			$('#seattype_num').val('null');
			$('#include_student').val('null');
			$('#single_round_type').val('null');
	
			$('#from_station_telecode_name').val('null');
			$('#to_station_telecode_name').val('null');
			$('#round_train_date').val('2012-12-16');
			$('#round_start_time_str').val('null');
			$('#start_time_str').val('null');
			if (isStudentTicketDateValid()) {
				canBuyStudentTicket = 'Y';
			}
		}
		$('#createStr').click(function() {
			$.ajax({
				url : ctx + '/order/querySingleAction.do?method=createStr',
				type : "POST",
				dataType : 'text',
				success : function(data, textStatus) {
				},
				error : function(e) {
					alert("服务器忙，加载查询数据失败");
				}
			});
		});
	});
</script>
<script src="/otsweb/js/order/query_common_func.js?version=5.1"
	type="text/javascript"></script>
<script src="/otsweb/js/order/query_common_bind.js?version=5.1"
	type="text/javascript"></script>
<script src="/otsweb/js/order/query_single_bindevent.js?version=5.1"
	type="text/javascript"></script>

<div style="top: 0; left: 0; z-index: 1000; POSITION: absolute;">
	<div id='form_cities'>
		<div id='top_cities'>操作提示</div>
		<div id='panel_cities'></div>
		<div id='flip_cities'>翻页控制区</div> 
	</div>
</div>
<div style="top: 0; left: 0; z-index: 2000; POSITION: absolute;">
	<div id='form_cities2'>
		<div id='panel_cities2'></div>

	</div>
</div>

<div class="conWrap">
	<div class="enter_w">
		<!--title -->
		<div class="wc_titlewb">
			<div class="wc_titleleftX" id="wc_titleleftX">

				<img src="/otsweb/images/font/cxdc.gif" /> <img

					src="/otsweb/images/font/cxdc.gif" style="display: none"
					id="pic_cxdc" /> <img src="/otsweb/images/font/cxwc.gif"
					style="display: none" id="pic_cxwc" />
			</div>
			<div class="wc_titler">
				<ul>
					<li><img src="/otsweb/images/er/jc_off.gif" />完成</li>
					<li><img src="/otsweb/images/er/jc_off.gif" />确认</li>
					<li><img src="/otsweb/images/er/jc_off.gif" />预订</li>
					<li><img src="/otsweb/images/er/jc_on.gif" />查询</li>
				</ul>
			</div>
		</div>
		<div class="clear"></div>
		<!--title end-->
		<div>
			<div class="box-top"></div>
			<div class="box-mid">
				<div class="cx_form">
					<form name="querySingleForm" method="post" action="/otsweb/order/querySingleAction.do?method=queryTrain"><div><input type="hidden" name="org.apache.struts.taglib.html.TOKEN" value="2dde1bd435d560b9c6c5c9a3464b12b0"></div>
						<!--form_con-->
						<div class="cx_tab">
							<ul>
								<li><input type="radio" name="singleRoundType" value="1" checked="checked" id="singleRadio">单程</li>
								<li><input type="radio" name="singleRoundType" value="2" id="roundRadio">往返</li>
								<!--<li><input type="button" name="createStr" id="createStr" value="create"/></li>-->
							</ul>
						</div>
						<div id="right">
							<div>
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									class="cx_from">
									<tr>
										<td width="140px" class="font_r"><span>*</span>出发地：</td>
										<td width="100px"><input type="hidden" name="orderRequest.from_station_telecode" value="NJH" id="fromStation"> <input type="text" name="orderRequest.from_station_name" maxlength="15" value="南京" id="fromStationText" style="width: 100px;" class="input_20txt"> <input
											type="hidden" id="station_name_show" name="station_name_show"
											value="show" /></td>
										<td width="64px" class="font_r"><span>*</span>目的地：</td>
										<td width="100px"><input type="hidden" name="orderRequest.to_station_telecode" value="XFN" id="toStation">
											<input type="text" name="orderRequest.to_station_name" maxlength="15" value="襄阳" id="toStationText" style="width: 100px;" class="input_20txt"></td>
										<td width="140px" class="font_r"><span>*</span>出发日期：</td>
										<td><input type="text" name="orderRequest.train_date" value="2012-12-21" id="startdatepicker" style="width: 150px;" class="input_20txt"></td>
										<td width="120px" class="font_r">出发时间：</td>
										<td><select name="orderRequest.start_time_str" id="startTime"><option value="00:00--24:00">00:00--24:00</option>
<option value="00:00--06:00">00:00--06:00</option>
<option value="06:00--12:00">06:00--12:00</option>
<option value="12:00--18:00">12:00--18:00</option>
<option value="18:00--24:00">18:00--24:00</option></select></td>
										<td class="font_r">
											<button class="search_u" id="submitQuery" type="button"></button>
										</td>

									</tr>
									<tr>
										<td class="font_r">出发车次：</td>
										<td colspan="3"><input type="hidden" name="orderRequest.train_no" value="" id="trainCode"> <input type="text" name="orderRequest.trainCodeText" value="" id="trainCodeText" style="width: 270px" class="input_20txt">
											<div id='trainCodeSuggest' class="ac_results_width"></div></td>
										<td class="font_r">
											<div class="single_round"
												style="display: none; text-align: right;">
												<span>*</span>返回日期：
											</div>
										</td>
										<td>
											<div class="single_round" style="display: none">
												<input type="text" name="roundTrainDate" value="2012-12-21" id="roundTrainDate" style="width: 150px" class="input_20txt">
											</div>
										</td>
										<td class="font_r">
											<div class="single_round"
												style="display: none; text-align: right;">返回时间：</div>
										</td>
										<td>
											<div class="single_round" style="display: none">
												<select name="roundStartTimeStr" id="roundStartTimeStr"><option value="00:00--24:00">00:00--24:00</option>
<option value="00:00--06:00">00:00--06:00</option>
<option value="06:00--12:00">06:00--12:00</option>
<option value="12:00--18:00">12:00--18:00</option>
<option value="18:00--24:00">18:00--24:00</option></select>
											</div>
										</td>
										<td class="font_r">
											<button class="stu_search_u" id="stu_submitQuery"
												type="button" style="margin-top: 5px;"></button>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div id="show1">
							<div class="in_from2">
								<div class="in_fromleft">
									<ul>
										<li><input type="checkbox" name="trainClassArr"
											id="ccType" value="QB" />全部</li>
										<li><input type="checkbox" id="ccType"
											name="trainClassArr" value="D" checked="checked" />动车</li>
										<li><input type="checkbox" id="ccType"
											name="trainClassArr" value="Z" />Z字头</li>
										<li><input type="checkbox" id="ccType"
											name="trainClassArr" value="T" />T字头</li>
										<li><input type="checkbox" id="ccType"
											name="trainClassArr" value="K" />K字头</li>
										<li><input type="checkbox" id="ccType"
											name="trainClassArr" value="QT" />其他</li>

									</ul>
								</div>
								<div class="in_fromr">
									<ul>
										<li><input name="trainPassType" type="radio" value="GL" />过路</li>
										<li><input name="trainPassType" type="radio" value="SF" />始发</li>
										<li><input name="trainPassType" type="radio" value="QB"
											checked="checked" />全部</li>

									</ul>
								</div>
							</div>
							<div class="clear"></div>
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="cx_from" style="display: none;">
								<tr>
									<td width="70" class="font_r">席别：</td>
									<td width="100"><select name="orderRequest.seat_type_code" id="seatType" style="width:90px"><option value="4">软卧</option>
<option value="6">高级软卧</option>
<option value="9">商务座</option>
<option value="M">一等座</option>
<option value="O" selected="selected">二等座</option>
<option value="P">特等座</option>
<option value="Q">观光座</option>
<option value="S">一等包座</option></select></td>
									<td width="70" class="font_r">购票张数：</td>
									<td width="100"><select name="seatNum" id="seatNum" style="width:90px"><option value="0">0</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option></select></td>
									<td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>

									<td style="display: none;"><input type="checkbox" name="includeStudent" value="0X00" id="includeStudent">乘客中包含学生</td>
								</tr>
							</table>
							<div class="cx_zc">
								<ul id="seatAddResult">

								</ul>
							</div>
						</div>
						<!--shens end-->
						<div id="tip"></div>
					</form>
					<!--form_con end-->
				</div>
			</div>
			<div class="box-bottom"></div>
		</div>
		<div class="cx_title_w">
			<div class="cx_titleleft" id="cx_titleleft"></div>
			<div class="cx_titler">
				<ul>
					<li>&nbsp;&nbsp;“--”：无此席别</li>
					<li>&nbsp;&nbsp;“无”：票已售完</li>
					<li>“有”：票源充足</li>
				</ul>
			</div>
		</div>

		<!-- dhtmlxGrid -->
		<div id="gridbox" width="99%" height="350px" align="left"
			style="background-color: white; overflow: hidden"></div>
		<div class="clear"></div>
		<!--con end-->
	</div>
</div>
<!-- 停靠站信息弹出框提示 -->

<!-- 停靠站信息  -->
<div id="stopDiv" class="stopdivstyle" 
	style="display: none; position: absolute; top: 172px; left: 442px;">
	<div class="jmp_hd">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tb_train_title">
			<tr>
				<th style="width: 50px; text-align: center;">站序</th>
				<th style="width: 90px; text-align: center;">站名</th>
				<th style="width: 90px; text-align: center;">到站时间</th>
				<th style="width: 90px; text-align: center;">出发时间</th>
				<th style="width: 90px; text-align: center;">停留时间</th>
			</tr>
		</table>
	</div>
	<div class="jmp_cd" id="stopContent">
		<table id="station_stop_time" width="100%" border="0" cellspacing="0"
			cellpadding="0" class="tb_train_title">
			<!-- 停靠站信息显示区域 -->
		</table>

	</div>
	<div id="station_page" class="pg_station" style="display: none;">

	</div>
	<div class="jmp_bd" id="stopBut"></div>
</div>
<form id="orderForm" method="post">
	<input type="hidden" name="station_train_code" id="station_train_code"></input>
	<input type="hidden" name="train_date" id="train_date"></input> <input
		type="hidden" name="seattype_num" id="seattype_num"></input> <input
		type="hidden" name="from_station_telecode" id="from_station_telecode"></input>
	<input type="hidden" name="to_station_telecode"
		id="to_station_telecode"></input> <input type="hidden"
		name="include_student" id="include_student"></input> <input
		type="hidden" name="from_station_telecode_name"
		id="from_station_telecode_name"></input> <input type="hidden"
		name="to_station_telecode_name" id="to_station_telecode_name"></input>
	<input type="hidden" name="round_train_date" id="round_train_date"></input>
	<input type="hidden" name="round_start_time_str"
		id="round_start_time_str"></input> <input type="hidden"
		name="single_round_type" id="single_round_type"></input> <input
		type="hidden" name="train_pass_type" id="train_pass_type"></input> <input
		type="hidden" name="train_class_arr" id="train_class_arr"></input> <input
		type="hidden" name="start_time_str" id="start_time_str"></input>
	<!-- 获取具体车次的值 -->
	<input type="hidden" name="lishi" id="lishi"></input> <input
		type="hidden" name="train_start_time" id="train_start_time"></input> <input
		type="hidden" name="trainno4" id="trainno"></input> <input
		type="hidden" name="arrive_time" id="arrive_time"></input> <input
		type="hidden" name="from_station_name" id="from_station_name"></input>
	<input type="hidden" name="to_station_name" id="to_station_name"></input>
	<input type="hidden" name="from_station_no" id="from_station_no"></input>
	<input type="hidden" name="to_station_no" id="to_station_no"></input>
	<input type="hidden" name="ypInfoDetail" id="ypInfoDetail"></input> <input
		type="hidden" name="mmStr" id="mmStr"></input>
</form>
</body>
</html>

<script language="javascript">
var f = function() {
		var message = "";
        var messageShow ="";
        var flag ='null';
        if(messageShow != ""){
			if(flag == 'u'){
				$("#messageUp").css({display:"inline"});
				document.getElementById("messageUp").innerHTML = message;
			}else{
				$("#messageDown").css({display:"inline"});
				document.getElementById("messageDown").innerHTML = message;
			}
         }else{
     		if ( message != ""){ 
    			alert(message);

    		}

         } }
         window.setTimeout(f, 500);
</script>
