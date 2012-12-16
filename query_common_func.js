
var stopHoverDate;
var showStopContent = [];
var pageSize = 8;
var mouseOnPanel = 0;
var timer = false;
//时间日期格式话	
Date.prototype.format = function(format) 
		{ 
		  var o = { 
		    "M+" : this.getMonth()+1, //month 
		    "d+" : this.getDate(),    //day 
		    "h+" : this.getHours(),   //hour 
		    "m+" : this.getMinutes(), //minute 
		    "s+" : this.getSeconds(), //second 
		    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter 
		    "S" : this.getMilliseconds() //millisecond 
		  } 
		  if(/(y+)/.test(format)) format=format.replace(RegExp.$1, 
		    (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		  for(var k in o)if(new RegExp("("+ k +")").test(format)) 
		    format = format.replace(RegExp.$1, 
		      RegExp.$1.length==1 ? o[k] : 
		        ("00"+ o[k]).substr((""+ o[k]).length)); 
		  return format; 
		} 

// 判断查询是单程还是往返
function getSingleRoundType() {
	var singleRound = "";
	var singleRoundComp = $(":radio[name='singleRoundType']:checked");
	if (singleRoundComp.length > 0) {
		singleRound = $(singleRoundComp[0]).val();
	}
	return singleRound;
}



// 日期转换
function parseDateyyyyMMddSplit(dateStr) {
	var currnetDate = new Date();
	var dates = dateStr.split("-");
	currnetDate.setFullYear(parseInt(dates[0]), parseInt(dates[1] - 1,10),
			parseInt(dates[2],10));
	if(dates.length>=6){
		currnetDate.setHours(dates[3], dates[4], dates[5]);
	}
	return currnetDate;
}


function checkRoundValid() {
	var valid = true;
	if (getSingleRoundType() == '2') {
		if ($('#roundTrainDate').val() == "") {
			valid = false;
			alert("您选择了返程查询，请填写返回日期");
			return valid;
		}

		if (parseDateyyyyMMddSplit($('#startdatepicker').val()) > parseDateyyyyMMddSplit($(
				'#roundTrainDate').val())) {
			valid = false;
			alert("返回日期不能早于出发日期");
			return valid;
		}
	}

	return valid;
}

// 获取列车类别，为多选框，#号分隔
function getTrainClassString() {
	var trainClassArr = $(":checkbox[name='trainClassArr']:checked");
	var split = "#";
	var trainClass = "";
	trainClassArr.each(function(index, domEle) {
		trainClass = trainClass + $(domEle).val() + "#";
	});
	return trainClass;
}

//当前选票售数
function getCurrentNum() {
	var seatTypeNum = 0;
	var resultContainer = $("#seatAddResult");
	var seatNumLis = resultContainer.find('li');
	seatNumLis.each(function() {
		var currentLi = $(this);
		seatTypeNum = seatTypeNum + parseInt(currentLi.attr("seatnum"));
	});
	return seatTypeNum;
}



// 获取列车路过的类型
function getTrainPassType() {
	var trainTypeArr = $(":radio[name='trainPassType']:checked");
	var trainType = trainTypeArr.val();
	return trainType;
}
// 获取席别、数量的集合，采用席别#数量@
function getSeanTypeAndNum() {
	var seatTypeNum = "";
	var resultContainer = $("#seatAddResult");
	var seatNumLis = resultContainer.find('li');
	seatNumLis.each(function() {
		var currentLi = $(this);
		seatTypeNum = seatTypeNum + currentLi.attr("id") + "#"
				+ currentLi.attr("seatnum") + "@";
	});

	return seatTypeNum;
}
// 查询中是否包含学生票
function getIncludeStudent() {
	var returnvalue = "00";// 默认全部
	var includeStudent = $("#includeStudent");
	if(clickBuyStudentTicket=='Y'){
		includeStudent.attr("checked",true);
	}else{
		includeStudent.attr("checked",false);
	}
	if (includeStudent.attr("checked") == true) {
		returnvalue = includeStudent.val();
	}
	return returnvalue;

}

// 判断内否提交查询
function canquery() {
	var canquery = true;
	// 防止操作产生的关键数据不正确开始
	if (jQuery.trim($('#trainCodeText').val()) == '') {
		$('#trainCode').val('');
	}
	if (jQuery.trim($('#fromStationText').val()) == '') {
		$('#fromStation').val('');
	}
	if (jQuery.trim($('#toStationText').val()) == '') {
		$('#toStation').val('');
	}
	// 防止操作产生的关键数据不正确计数

	if ($.trim($("#toStationText").val()) == ""
			|| $.trim($("#fromStationText").val()) == ""
				|| $.trim($("#toStationText").val()) == "简码/汉字"
					|| $.trim($("#fromStationText").val()) == "简码/汉字") {
		if ($.trim($("#fromStationText").val()) == ""
				|| $.trim($("#fromStationText").val()) == "简码/汉字") {
			alert("请填写出发地！");
			canquery = false;
			return canquery;
		}

		if ($.trim($("#toStationText").val()) == ""
			|| $.trim($("#toStationText").val()) == "简码/汉字") {
			alert("请填写目的地！");
			canquery = false;
			return canquery;
		}
	}

	if ($.trim($("#fromStationText").val()) == $
			.trim($("#toStationText").val())) {

		alert("出发地与目的地不能相同！");
		canquery = false;
		return canquery;

	}

	if ($.trim($("#startdatepicker").val()) == "") {
		alert("请填写出发日期！");
		canquery = false;
		return canquery;
	}
	if (checkBeyondMixTicketNum()) {
		canquery = false;
		return canquery;
	}

	if (getSingleRoundType() == "") {
		canquery = false;
		alert("请选择单程查询还是往返查询！");
		return canquery;
	}

	if (!checkRoundValid()) {
		canquery = false;
		return canquery;
	}
	
	//检查日期是否在预期可售时间内
	if(clickBuyStudentTicket=='Y'){
		if($("#startdatepicker").attr("class")!="input_20hui"){
			var go_date=$.trim($("#startdatepicker").val());
			if(go_date<stu_can_buy[0] ||  go_date>stu_can_buy[1]){
				canquery = false;
				alert("学生票的预售期是["+stu_can_buy[0]+" ～ "+stu_can_buy[1]+"],请重新选择日期查询！");
				return canquery;
			}
		}else{
			var back_date=$.trim($("#roundTrainDate").val());
			if(back_date<stu_can_buy[0] ||  back_date>stu_can_buy[1]){
				canquery = false;
				alert("学生票的预售期是["+stu_can_buy[0]+" ～ "+stu_can_buy[1]+"],请重新选择日期查询！");
				return canquery;
			}
		}
	}else{
		if($("#startdatepicker").attr("class")!="input_20hui"){
			var go_date=$.trim($("#startdatepicker").val());
			if(go_date<other_can_buy[0] ||  go_date>other_can_buy[1]){
				canquery = false;
				alert("您选择的日期不在预售期范围内！");
				return canquery;
			}
		}else{
			var back_date=$.trim($("#roundTrainDate").val());
			if(back_date<other_can_buy[0] ||  back_date>other_can_buy[1]){
				canquery = false;
				alert("您选择的日期不在预售期范围内！");
				return canquery;
			}
		}
	}
	
	
	if(passengerTypesString == ""){//单程或往程
		//点击的是查询学生按钮
		if(clickBuyStudentTicket=="Y"){
			if(!isStudentTicketDateValid()){
				var alertMessage="学生票的乘车时间为每年的暑假6月1日至9月30日、寒假12月1日至3月31日，目前不办理学生票业务。";
				alert(alertMessage);
				canquery = false;
				return canquery;
			}
		}
	}else{//返程
		if(!isStudentTicketDateValid()){
			var alertMessage="学生票的乘车时间为每年的暑假6月1日至9月30日、寒假12月1日至3月31日，目前不办理学生票业务。";
			alert(alertMessage);
			canquery = false;
			return canquery;
		}
	}
	
	
	return canquery;
}

//如果是学生票，判断乘车时间是否在学生票的可买时间内
function isStudentTicketDateValid(){
	var sTicketStartDate=studentTicketStartDate.split("&");
	var sTicketEndDate=studentTicketEndDate.split("&");
	
	var  curDate=new Date();
	var curYear=curDate.getFullYear();
	
	var resign_ticket=$("#startdatepicker").val();
	var back_ticket=$("#roundTrainDate").val();
	var compDateString;
	if(resign_ticket!="" && $("#startdatepicker").attr("class")!="input_20hui"){
		compDateString=resign_ticket+" 00:00:00";
	}else if(back_ticket!=""){
		compDateString=back_ticket+" 00:00:00";
	}
	var summenrHolidayStart=curYear+"-"+sTicketStartDate[0];
	var summenrHolidayEnd=curYear+"-"+sTicketEndDate[0];
	var winterHolidayStart=curYear+"-"+sTicketStartDate[1];
	var winterHolidayEnd=curYear+"-"+sTicketEndDate[1];
	
	var winterHolidayStart1=curYear+"-"+sTicketStartDate[2];
	var winterHolidayEnd1=curYear+"-"+sTicketEndDate[2];
	var winterHolidayStart2=(parseInt(curYear, 10)+1)+"-"+sTicketStartDate[2];
	var winterHolidayEnd2=(parseInt(curYear, 10)+1)+"-"+sTicketEndDate[2];
	if( (compDateString>=summenrHolidayStart && compDateString<=summenrHolidayEnd) || (compDateString>=winterHolidayStart && compDateString<=winterHolidayEnd)
			|| (compDateString>=winterHolidayStart1 && compDateString<=winterHolidayEnd1) || (compDateString>=winterHolidayStart2 && compDateString<=winterHolidayEnd2)){
		return true;
	}else{
		return false;
	}
}

//购票主方法

//参数格式：#号分隔
function getSelected(selectStr) {
	var selectStr_arr = selectStr.split("#");
	var station_train_code=selectStr_arr[0];
	var lishi=selectStr_arr[1];
	var starttime=selectStr_arr[2];
	var trainno=selectStr_arr[3];
	var from_station_telecode=selectStr_arr[4];
	var to_station_telecode=selectStr_arr[5];
	var arrive_time=selectStr_arr[6];
	var from_station_name=selectStr_arr[7];
	var to_station_name=selectStr_arr[8];
	var from_station_no=selectStr_arr[9];
	var to_station_no=selectStr_arr[10];
	var ypInfoDetail=selectStr_arr[11];
	var mmStr = selectStr_arr[12];
	var flag = true;
	if (checkBeyondMixTicketNum()) {
		flag = false;
		return;
	}
	// 该方法在各个页面中分别书写，因为根据页面的不同行为不同，相当于重写
	if (flag) {
		submitRequest(station_train_code,lishi,starttime,trainno,from_station_telecode,to_station_telecode,arrive_time,from_station_name,to_station_name,from_station_no,to_station_no,ypInfoDetail,mmStr);
	}
}


// 改变查询按钮的
function renameButton(classname) {
	var queryButton = $("#submitQuery");
	queryButton.removeClass();
	queryButton.addClass(classname);
	if(classname=="research_u" && $("#stu_submitQuery").attr("class")=='research_u'){
		stu_renameButton("stu_search_u");
	}
};

function stu_renameButton(classname) {
	var queryButton = $("#stu_submitQuery");
	queryButton.removeClass();
	queryButton.addClass(classname); 
	if(classname=="research_u" && $("#submitQuery").attr("class")=='research_u'){
		renameButton("search_u");
	}
};

//改变无效查询按钮的
//function invalidOrderButton(button) {
//	$(button).removeClass();
//	$(button).addClass("yuding_x");
//	$(button).unbind("click");
//};

function invalidQueryButton() {
	var queryButton = $("#submitQuery");
	queryButton.unbind("click");
	if(queryButton.attr("class")=="research_u"){
		 renameButton("research_x");
	}else if(queryButton.attr("class")=="search_u"){
		 renameButton("search_x");
	}
};

function stu_invalidQueryButton() {
	var queryButton = $("#stu_submitQuery");
	//queryButton.unbind("click");
	queryButton.attr("disabled",true);
	if(queryButton.attr("class")=="research_u"){
		stu_renameButton("research_x");
	}else if(queryButton.attr("class")=="stu_search_u"){
		stu_renameButton("stu_search_x");
	}
	
};

function validQueryButton() {
	var queryButton = $("#submitQuery");
	queryButton.click(sendQueryFunc);
	if(queryButton.attr("class")=="research_x"){
		 renameButton("research_u");
	}else if(queryButton.attr("class")=="search_x"){
		 renameButton("search_u");
	}
	
};

function stu_validQueryButton() {
	var queryButton = $("#stu_submitQuery");
	queryButton.attr("disabled",false);
	if(queryButton.attr("class")=="research_x"){
		stu_renameButton("research_u");
	}else if(queryButton.attr("class")=="stu_search_x"){
		stu_renameButton("stu_search_u");
	}
	
};

// 点击查询按钮后的动作开始
var sendQueryFunc = function() {
	var _id=$(this).attr("id");
	if(_id=='stu_submitQuery'){
		clickBuyStudentTicket='Y';
	}else{
		clickBuyStudentTicket='N';
	}
	
	var validQuery = canquery();
	if (!validQuery) {
		return;
	}
	/*if(clickBuyStudentTicket=='Y'){
		stu_invalidQueryButton();
	}else{
		invalidQueryButton();
	}*/
	
	stu_invalidQueryButton();
	invalidQueryButton();
	
	loadData();
	//处理发送预订请求的数据
	prepareOrderData();
};
// 点击查询按钮后的动作开始
// <--隐藏/显示复杂查询开始-->
function slidediv(clk,showdiv){
	$(clk).toggle(function(){
		
				$(showdiv).slideDown(function(){
				$(clk).removeClass().addClass('panle_arrow_d');
				parent.doIframe() ;
			});
		},function(){
				$(showdiv).slideUp(function(){
				$(clk).removeClass().addClass('panle_arrow_u');
				parent.doIframe() ;
			});

		});
}



// <--选择作为席别，票数开始-->
function addSeatTypeAndNum(seatTypeId, seatNumId, resultContainer) {
	//alert("最多预订票数:" + maxTicketNum);
	var seatTypeComp = $(seatTypeId);
	var seatNumComp = $(seatNumId);
	var resultComp = $(resultContainer);
	var selectSeatType = seatTypeComp.find("option:selected");
	var selectNum = seatNumComp.find("option:selected");
	var seatTypeCode = selectSeatType.val();
	var seatTypeText = selectSeatType.text();

	var seatNumValue = selectNum.val();
	if(seatNumValue==0){
		alert("请选择购票张数");
		return;
	}
	var currnetLi = resultComp.find("li[id='" + seatTypeCode + "']");
	
	var beforeSelectNum = getCurrentNum();
	var futureNum = parseInt(beforeSelectNum) + parseInt(seatNumValue);
	
	if(currnetLi.length>0){
		var existnum = 	parseInt($(currnetLi[0]).attr('seatnum'));
		if((futureNum-existnum)>maxTicketNum){
			alert("修改后的总票数为"+(futureNum-existnum)+"张，您最多只能预订" + maxTicketNum + "张票！");
			return;
		}
	}else{
		if(futureNum>maxTicketNum){
			alert("您最多只能预订" + maxTicketNum + "张票！");
			return;
		}
	}
	
	 
	
	if (currnetLi.length > 0) {
		currnetLi = $(currnetLi[0]);
		currnetLi.empty();
		currnetLi.attr("seatnum", seatNumValue);
		currnetLi.append(seatTypeText + ":" + seatNumValue
				+ "张   <a title='点击删除' style='color:#2C72BA'>删除</a>");
	} else {
		resultComp
				.append("<li id='"
						+ seatTypeCode
						+ "' seatnum='"
						+ seatNumValue
						+ "'>"
						+ seatTypeText
						+ ":"
						+ seatNumValue
						+ "张  <a title='点击删除'  style='color:#2C72BA'>删除</a></li>");

	}

	currnetLi = resultComp.find("li[id='" + seatTypeCode + "']");
	currnetLi = $(currnetLi[0]);
	var deleteImg = currnetLi.find('a');
	$(deleteImg[0]).click(function() {
		var a = $(this).parent();
		$(this).parent().remove();
	});
    parent.doIframe();
    
}
// <--选择作为席别，票数结束-->

function popSeatTypeSelect(data,selectId){
	$(selectId).empty();
	$(data).each(function(index,current){
		//二等座和硬卧默认选中
		if(current.id =="O" || current.id=="3"){
         $("<option value='"+current.id+"' selected='true'>"+current.value+"</option>").appendTo(selectId);
		}else{
		 $("<option value='"+current.id+"'>"+current.value+"</option>").appendTo(selectId);
		}
	});
}



function showLoadMsg(grid){
	$('<div class="datagrid-mask"></div>').css({
		display:'block',
		width: grid.width(),
		height: grid.height()
	}).appendTo(grid);
	$('<div class="datagrid-mask-msg"></div>')
			.html("正在查询，请稍候！")//该提示文字写死还是用配置文件请根据项目决定
			.appendTo(grid)
			.css({
				display:'block',
				left:(grid.width()-$('.datagrid-mask-msg',grid).outerWidth())/2,
				top:(grid.height()-$('.datagrid-mask-msg',grid).outerHeight())/2
			});
	
}

function removeLoadMsg(){
	$('.datagrid-mask').remove();
	$('.datagrid-mask-msg').remove();
}


//鼠标悬停显示提示框(停靠站信息)
function onStopHover(str) {
	
	var str_arr = str.split("#");
	var train_no = str_arr[0];
	var from_station_telecode = str_arr[1];
	var to_station_telecode = str_arr[2];
	if (timer) 
		clearTimeout(timer);
	
	$.ajax({
		type: "GET",   
		dataType: "json",   
		url: ctx+'/order/querySingleAction.do?method=queryaTrainStopTimeByTrainNo',
		data:{
			train_no:train_no,
			from_station_telecode:from_station_telecode,
			to_station_telecode:to_station_telecode,
			depart_date:stopHoverDate
			
		},
		//发送请求前，显示加载动画        
        //beforeSend:function(){$("#divload").show();$("#stopDiv").hide();},
        //请求完毕后，隐藏加载动画
        //complete:function(){$("#divload").hide();$("#stopDiv").show();},
		success: function(data) {   
			var grayStr = "";
			$("#station_stop_time tr").remove();   
			var dataLen = data.length;
			var trainStopTime_start_time;
			var trainStopTime_stopover_time;
			var showContent = [];
			$(data).each(function(index, trainStopTime){
				//alert(trainStopTime.isEnabled);
				if(!trainStopTime.isEnabled) {
					grayStr = "color: #999;";	//无效站，灰色字体
				} else {
					grayStr = "";
				}
				//alert(grayStr); 
				//var tr_first = "<tr><td style=\"width:50px;text-align: center;\"><span style='"+grayStr+"'>"+trainStopTime.station_no+"</span></td>";
				if(index == dataLen-1) {
					trainStopTime_start_time = "----";
					trainStopTime_stopover_time = "----";
				} else {
					trainStopTime_start_time = trainStopTime.start_time;
					trainStopTime_stopover_time = trainStopTime.stopover_time;
				}
				if(dataLen <= pageSize){
					var tr_first = "<tr><td style=\"width:50px;text-align: center;\"><span style='"+grayStr+"'>"+trainStopTime.station_no+"</span></td>";
					tr_first += "<td style=\"width:90px;text-align: center;\"><span style='"+grayStr+"'>"+trainStopTime.station_name+"</span></td>";
					tr_first += "<td style=\"width:90px;text-align: center;\"><span style='"+grayStr+"'>"+trainStopTime.arrive_time+"</span></td>";
					tr_first += "<td style=\"width:90px;text-align: center;\"><span style='"+grayStr+"'>"+trainStopTime_start_time+"</span></td>";
					tr_first += "<td style=\"width:90px;text-align: center;\"><span style='"+grayStr+"'>"+trainStopTime_stopover_time+"</span></td></tr>";
					$('#station_stop_time').append(tr_first);
				}else{
					showContent.push(grayStr+"|"+trainStopTime.station_no+"|"+trainStopTime.station_name+"|"+trainStopTime.arrive_time+"|"+trainStopTime_start_time+"|"+trainStopTime_stopover_time);
				}
			});
		    if(dataLen > pageSize){
		    	$("#stopContent").css("height", "200px");
		    }else{
		    	$("#stopContent").css("height", "auto");
		    }
			var position_ = $("#id_" + train_no).offset();
		    var pos_top = position_.top; 
		    var pos_left = position_.left;
		    var pos_top_new = pos_top ;
		    var pos_left_new = pos_left + 44; 
		    var div_height = $("#stopDiv").height();
		    pos_top_new = pos_top_new - div_height + 19;
			if(dataLen > pageSize){
				showStopContent = showContent;
				pageDes(0);
			}
			$("#stopDiv").show();
		    //alert($("#stopDiv").attr("style"));
		    if($("#stopDiv").attr("style").indexOf("block") != -1 || $("#stopDiv").attr("style").indexOf("none") == -1) {
		    	$("#stopDiv").attr("style","display:block;position:absolute;top:"+pos_top_new+"px;left:"+pos_left_new+"px;");
		    	
		    }
		    if(dataLen > pageSize){
		    	$("#station_page").css("display", "block");
		    }else{
		    	$("#station_page").css("display", "none");
		    }
		    
		},
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			 alert("服务器忙，请稍后重试");
	 	} 
		
	});
}
$(document).ready(
		
	function(){
		$("#stopDiv").mouseover(
			function(event){
//				var elem = event.fromElement || event.relatedTarget;            
//				if (!this.contains(elem)) { 
//					mouseOnPanel = 1; 
//				} 
				if(checkHover(event,this)){
					mouseOnPanel = 1; 
				}

			}
		).mouseout(
			function(event){
//				var elem = event.toElement || event.relatedTarget;          
//				if (!this.contains(elem)) {
//					mouseOnPanel = 0;
//					$("#stopDiv").hide();
//					$('#station_stop_time').html('');
//				}
				 if(checkHover(event,this)){
					mouseOnPanel = 0;
					$("#stopDiv").hide();
					$('#station_stop_time').html('');
				 }
			}	
		);		

});

function contains(parentNode, childNode) {
    if (parentNode.contains) {
        return parentNode != childNode && parentNode.contains(childNode);
    } else {
        return !!(parentNode.compareDocumentPosition(childNode) & 16);
    }
}

function checkHover(e,target){
    if (getEvent(e).type=="mouseover")  {
        return !contains(target,getEvent(e).relatedTarget||getEvent(e).fromElement) && !((getEvent(e).relatedTarget||getEvent(e).fromElement)===target);
    } else {
        return !contains(target,getEvent(e).relatedTarget||getEvent(e).toElement) && !((getEvent(e).relatedTarget||getEvent(e).toElement)===target);
    }
}

function getEvent(e){
    return e||window.event;
}




function pageDes(aPageNo){
	var ulHtml = "";
	var show = [];
	var pagecount = Math.ceil((showStopContent.length)/pageSize);
	if(pagecount > 1){
		if(aPageNo==-1) 
			aPageNo = (pagecount-1);
		else if(aPageNo==pagecount) 
			aPageNo = 0; 
		show = showStopContent.slice(pageSize*(aPageNo),  Math.min(pageSize*(aPageNo+1), showStopContent.length) );
		$('#station_stop_time').html('');
		for(var a=0;a<show.length;a++){
			var showData = show[a].split('|');
			var grayStr = showData[0];
			var tr_first = "<tr><td style=\"width:50px;text-align: center;\"><span style='"+grayStr+"'>"+showData[1]+"</span></td>";
			tr_first += "<td style=\"width:90px;text-align: center;\"><span style='"+grayStr+"'>"+showData[2]+"</span></td>";
			tr_first += "<td style=\"width:90px;text-align: center;\"><span style='"+grayStr+"'>"+showData[3]+"</span></td>";
			tr_first += "<td style=\"width:90px;text-align: center;\"><span style='"+grayStr+"'>"+showData[4]+"</span></td>";
			tr_first += "<td style=\"width:90px;text-align: center;\"><span style='"+grayStr+"'>"+showData[5]+"</span></td></tr>";
			$('#station_stop_time').append(tr_first);	
		}
		
		var stopHtml = (aPageNo==0)?"&laquo;&nbsp;上一页":"<a   href=''  class='stopflip' onclick='pageDes("+(aPageNo-1)+");return false;'>&laquo;&nbsp;上一页</a>";
		stopHtml += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;";
		stopHtml += (aPageNo==pagecount-1)?"下一页&nbsp;&raquo;":"<a  href=''   class='stopflip'  onclick='pageDes("+(aPageNo+1)+");return false;'>下一页&nbsp;&raquo;</a>";
		$("#station_page").html(stopHtml);
	}else{

	}
}

//鼠标移开隐藏提示框(停靠站信息)
function onStopOut() {
    //$('#stopDiv').style.display = 'none'; &&  $("#stopDiv").css("display")=="block" 

	if (timer) 
		clearTimeout(timer);
	timer = window.setTimeout('tim()',135);

}

function tim(){
	if(mouseOnPanel != 1){
		mouseOnPanel = 0;
		$("#stopDiv").hide();
		$('#station_stop_time').html('');
	}
}


//设置发到站样式
function setFromToStationShow() {
	var fromStation_text = jQuery.trim($("#fromStationText").val());
	var fromStation = jQuery.trim($("#fromStation").val());
	if(isValueNullOrBlank(fromStation_text) || isValueNullOrBlank(fromStation)) {
		$("#fromStationText").val("简码/汉字");
		from_to_station_class_gray($("#fromStationText"));
		$("#fromStation").val("");
	} else {
		from_to_station_class_plain($("#fromStationText"));
	}
	var toStation_text = jQuery.trim($("#toStationText").val());
	var toStation = jQuery.trim($("#toStation").val());
	if(isValueNullOrBlank(toStation_text) || isValueNullOrBlank(toStation)) {
		$("#toStationText").val("简码/汉字");
		from_to_station_class_gray($("#toStationText"));
		$("#toStation").val("");
	} else {
		from_to_station_class_plain($("#toStationText"));
	}
}
// 判断元素值是否为空：null、“null”、“”、undefined
function isValueNullOrBlank(str) {
	if(str == null || str == "" || str == "null" || str == undefined) {
		return true;
	} else {
		return false;
	}
}
