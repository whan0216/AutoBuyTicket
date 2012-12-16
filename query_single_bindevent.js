//本文件中包含了适用于单程查询使用的特殊的功能模块，
//部分功能因为规则冲突而采用了不同的实现。


//处理查询列表动态显示查询信息，因为返程和其他查询不一致，所以需要特殊化开始

function dealwithQueryInfo(datagrid){
	stopHoverDate=$('#startdatepicker').val();
	$("#cx_titleleft").empty();
	$("#cx_titleleft").append(
			"出发日期：<span>" + stopHoverDate
					+ "&nbsp;&nbsp;&nbsp;&nbsp;"
					+ $('#fromStationText').val() + "→"
					+ $('#toStationText').val()
					+ "</span>(共  <span> " + datagrid.getRowsNum()+ " </span>趟列车)");
					//+"&nbsp;&nbsp;&nbsp;&nbsp;最后更新时间：<span>"+cacheTime+"</span>");
	//查询出结果后，“重新查询”按钮延迟5秒出现
//	window.setTimeout('delayButton()',5000);
	
}

function delayButton(){
	validQueryButton();
	nmg_validQueryButton();
	if(isStudentTicketDateValid()){
		stu_validQueryButton();
	}
	
	if(clickBuyStudentTicket=='Y'){
		stu_renameButton('research_u');
		
	 }else if(clickBuyNongmgTicket == 'Y'){
		 nmg_renameButton('research_u');
	 }
	 else{
		 renameButton('research_u');
		 //stu_renameButton('research_u');
	}
	
}
//处理查询列表动态显示查询信息，因为返程和其他查询不一致，所以需要特殊化结束


//是否超过最多可售数
function checkBeyondMixTicketNum() {
	var seatTypeNum = getCurrentNum();
	if (seatTypeNum > maxTicketNum) {
		alert("您最多只能预订" + maxTicketNum + "张票！");
		return true;
	};
	return false;
}





// 加载查询数据开始
function loadData() {
	var gridbox = $("#gridbox");
	showLoadMsg(gridbox);
	//异步请求站名
	$.ajax( {
		url : ctx+'/query/queryRemanentTicketAction.do?method=queryLeftTicket',
		type : "GET",
		dataType:'json',
		data:{
			'orderRequest.train_date' : $('#startdatepicker').val(),
			'orderRequest.from_station_telecode' : $('#fromStation').val(),
			'orderRequest.to_station_telecode' : $('#toStation').val(),
			'orderRequest.train_no' : $('#trainCode').val(),
			'trainPassType' : getTrainPassType(),
			'trainClass' : getTrainClassString(),
			'includeStudent' : getIncludeStudent(),
			'seatTypeAndNum' : getSeanTypeAndNum(),
			'orderRequest.start_time_str' : $('#startTime').val()
		},
		success : function(data, textStatus) {

			if(data.datas == "-1") {
				alert("服务器忙，加载查询数据失败！");
				data.datas = "";
			} else if(data.datas !="undefine" &&data.datas.split(",")[0]=="-2"){
				alert(data.datas.split(",")[1]);
				data.datas="";
				
			}else {
				data.datas=data.datas.replaceAll("\\\\n",String.fromCharCode(10));
				cacheTime = data.time;
			}
			mygrid.clearAll();
			mygrid.startFastOperations();
			mygrid.parse(data.datas,"csv");
			mygrid.stopFastOperations();
			dealwithQueryInfo(mygrid);

			
			removeLoadMsg();
		},
		error : function(e) {
			 alert("服务器忙，加载查询数据失败！");
			 removeLoadMsg();
//			 validQueryButton();
//			 nmg_validQueryButton();
//				if(isStudentTicketDateValid()){
//					stu_validQueryButton();
//				}
//			 if(clickBuyStudentTicket=='N'){
//				 renameButton('research_u');
//				 nmg_renameButton('research_u');
//			 }else{
//				 stu_renameButton('research_u');
//			 }
		}
	});
}
// 加载查询数据结束

// 设置席别和购票张数是否置灰
function setSeatTypeAndNumDisabled() {
	var dongche = "D";
	var trainClassArr = $(":checkbox[name='trainClassArr']:checked");
	if(trainClassArr.length>=2){
		var containDongche = false;
		trainClassArr.each(function(index, domEle) {
			if($(domEle).val()==dongche){
				containDongche = true;
			}
		});
		if(containDongche){
		$("#seatType").attr("disabled","disabled");
		$("#seatNum").attr("disabled","disabled");
		}else{
			$("#seatType").removeAttr("disabled");
			$("#seatNum").removeAttr("disabled");
			popSeatTypeSelect(seatTypeRelation[$(trainClassArr[0]).val()],"#seatType");
		}
	} else if(trainClassArr.length==1){
		popSeatTypeSelect(seatTypeRelation[$(trainClassArr[0]).val()],"#seatType");
		$("#seatType").removeAttr("disabled");
		$("#seatNum").removeAttr("disabled");
	}else if(trainClassArr.length==0){
		$("#seatType").attr("disabled","disabled");
		$("#seatNum").attr("disabled","disabled");
	}else{
		$("#seatType").removeAttr("disabled");
		$("#seatNum").removeAttr("disabled");
	}
}

//设置列车字头默认值
function setTrainClassDefaultSelect() {
	var trainClassArr = $(":checkbox[name='trainClassArr']");
	var isNotSelect = true;
	trainClassArr.each(function() {
		if (this.checked) {
			isNotSelect = false;
			return;
		}
	});
	// 若未选择任何字头，则默认选中“动车”
	if (isNotSelect) {
		trainClassArr.each(function() {
			if ("D" == this.value) {
				this.checked = true;
				return;
			}
		});
	}
}


$(function() {
	// 设置发到站样式
	setFromToStationShow();
	$(":checkbox[name='trainClassArr']").click(function(){
		if($(this).val()!='QB'&&$(this).attr("checked") != true) {
			$('#ccType').attr("checked", false);
		}else if($(this).val()=='QB'&&$(this).attr("checked") != true){
			$("input[name='trainClassArr']").attr("checked", false);
		}else if($(this).val()=='QB'&&$(this).attr("checked") == true){
			$("input[name='trainClassArr']").attr("checked", true);
		}
		setSeatTypeAndNumDisabled();
	});
	//默认选中全部
	$(":checkbox[name='trainClassArr'][value='QB']")[0].click();


	//出发地下拉效果开始
	$("#fromStationText").focus(function(){
		$("#trainCodeSuggest").css("display", "none");
		$("#trainCodeSuggest").empty();
		if($("#trainCodeText").attr("readonly")){
			$("#trainCodeText").removeAttr("readonly");
			$("#trainCodeText").attr("class","input_20txt");
		}
		$("#trainCodeText").val('');
		$("#trainCode").val('');

	});
	//出发地下拉效果结束
	 
	//终到地下拉效果开始
	$("#toStationText").focus(function(){
		$("#trainCodeSuggest").css("display", "none");
		$("#trainCodeSuggest").empty();
		 if($("#trainCodeText").attr("readonly")){
			 $("#trainCodeText").removeAttr("readonly");
			 $("#trainCodeText").attr("class","input_20txt-1");
		   }
		 $("#trainCodeText").val('');
		 $("#trainCode").val('');

	});
	//终到地下拉效果结束
	 
	// <--开始日期控件开始-->
		$("#startdatepicker").focus(function() {
			WdatePicker( {
				minDate : minPeriod,
				maxDate : maxPeriod,
				isShowClear : false,
				readOnly : true,
				qsEnabled : false,
				onpicked:function(){ 
				    //判断是否可买学生票
					if(!isStudentTicketDateValid()){//不能买
						canBuyStudentTicket="N";
						stu_invalidQueryButton();
					}else{//可以买
						canBuyStudentTicket="Y";
//						stu_renameButton("stu_search_u2");
						$("#stu_submitQuery").attr("disabled",false);
					}
					$('#startTime').focus();
					
				}
			});
		});
		
		// <--开始日期控件结束-->
		
		
		//点击单程/返程单选框,引发图片和显示框的切换开始
		$("#singleRadio").click(function(){
			var roundDiv = $(".single_round");
			roundDiv.each(function(){
				$(this).hide();
		});
			
			var pic_title = $("#wc_titleleftX");
			pic_title.find("img").each(function(){
				if($(this).attr("id")!="pic_cxdc" ){
					$(this).hide();	
				}
			});
			$("#pic_cxdc").show();
		});
		
		
		$("#roundRadio").click(function(){
			var roundDiv = $(".single_round");
			roundDiv.each(function(){
				$(this).show();
			});
			var pic_title = $("#wc_titleleftX");
			pic_title.find("img").each(function(){
				if($(this).attr("id")!="pic_cxwc" ){
					$(this).hide();	
				}
			});
			$("#pic_cxwc").show();
		});
		
		//点击单程/返程单选框,引发图片和显示框的切换结束
		
		
		// <--返回日期控件开始-->
		$("#roundTrainDate").focus(function() {
			WdatePicker( {
				minDate : minPeriod,
				maxDate : maxPeriod,
				isShowClear : false,
				readOnly : true,
				qsEnabled : false,
				onpicked:function(){
					$('#roundStartTimeStr').focus();
				}
			});
		});
		// <--返回日期控件结束-->
		
		//车次下拉效果开始
		// 原始查询条件，防止当车次依赖的几个查询条件不变时重新查询
		var fromStation_old='';
		var toStation_old='';
		var startdatepicker_old='';
		var startTime_old='';
		 $("#trainCodeText").click(function(event){
				 if(jQuery.trim($("#fromStation").val())==""||jQuery.trim($("#toStation").val())==""||$("#startdatepicker").val()==""){
					 alert("请填写完整出发地、目的地以及出发日期再进行车次选择");
					 return;
				 }
				 if(jQuery.trim($("#fromStation").val())!=""&&jQuery.trim($("#toStation").val())!=""&&
						 jQuery.trim($("#fromStationText").val())!=""&&jQuery.trim($("#toStationText").val())!=""&&$("#startdatepicker").val()!=""){
					 $("#trainCodeText").val("");
					 $("#trainCode").val("");
					 if(fromStation_old == $("#fromStation").val() && toStation_old == $("#toStation").val() 
							 && startdatepicker_old == $("#startdatepicker").val() && startTime_old == $("#startTime").val()) {
						 // 若出发地、目的地、出发日期、出发时间都没变的话，则不重新查询出发车次
						 //alert("不重新查询车次");
					 } else {
						 $.ajax(
								 {
									 url :ctx+'/query/queryRemanentTicketAction.do?method=queryststrainall',
									 type :"POST",
									 dataType :"json",
									 data:{
										 date:$("#startdatepicker").val(),
										 fromstation:$("#fromStation").val(),
										 tostation:$("#toStation").val(),
										 starttime:$("#startTime").val()
									 },
									 success:function(data,textStatus){
										 $("#trainCodeText").trainCodesuggest(data,{dataContainer:'#trainCode', attachObject:'#trainCodeSuggest',	resultsClass:'ac_results_width'});
										 $("#trainCodeText").focus();
									 },
									 error:function(e){
										 alert("服务器错误，获取车次失败");
									 }
								 });
						 // 重新赋值查询条件
						 fromStation_old = $("#fromStation").val();
						 toStation_old = $("#toStation").val();
						 startdatepicker_old = $("#startdatepicker").val();
						 startTime_old = $("#startTime").val();
					 }
				 }
			 });
		//车次下拉效果结束
	   if(indexQueryResult==null||indexQueryResult==undefined||indexQueryResult=='null'&&indexQueryResult!=""){
	   } else {
		   if(indexQueryResult == "-1") {
			   alert("服务器忙，加载查询数据失败！");
			   indexQueryResult = "";
		   }else if(indexQueryResult !="undefine" && indexQueryResult!=null &&indexQueryResult.split(",")[0]=="-2"){
				alert(indexQueryResult.split(",")[1]);
				indexQueryResult="";
		   }
			mygrid.clearAll();
			mygrid.startFastOperations();
			mygrid.parse(indexQueryResult,"csv");
			mygrid.stopFastOperations();
			dealwithQueryInfo(mygrid);
	   }
	   
	   //处理首页查询附带的参数，页面回显席别座位数
	   //if(index_seatTypeAndNum!=''){
		   var trainclassarray = index_train_class.split("#");
		   var seatarray = index_seatTypeAndNum.split('@');
		   if(seatarray.length>0){
		      for(var i = 0;i<seatarray.length-1;i++){
		    	  var singleSeat = seatarray[i];
		    	  var seatinfo = singleSeat.split('#');
		    	  var finded = false;
		    	  for(var j =0;j<trainclassarray.length-1;j++){
		    		var currentClass = trainclassarray[j];
		    		var currrentRelation  = seatTypeRelation[currentClass];
		    		for(var m =0;m<currrentRelation.length;m++){
		    			
		    			var currentseattype = currrentRelation[m];
		    			if(currentseattype['id']==seatinfo[0]){
		    		    	  var resultComp = $("#seatAddResult");
		    		    	  resultComp.append("<li id='"
		    							+ seatinfo[0]
		    							+ "' seatnum='"
		    							+  seatinfo[1]
		    							+ "'>"
		    							+ currentseattype['value']
		    							+ ":"
		    							+ seatinfo[1]
		    							+ "张  <a title='点击删除' style='color:#2C72BA'>删除</a></li>");
		    		    	  finded = true;
		    		    	  break;
		    			}
		    		}
		    		if(finded){
		    			 break;
		    		}
		    	  }
		      }
		   }
		   // 赋值列车等级
		   var trainClassArr = $(":checkbox[name='trainClassArr']");
		   if(index_train_class != null && index_train_class != "") {
			   trainClassArr.each(function() {
				   if (index_train_class.indexOf(this.value) != -1) {  
					   this.checked = true;
					   $("#seatType").removeAttr("disabled");
					   $("#seatNum").removeAttr("disabled");
				   } else {
					   this.checked = false;  
				   }
			   });		   
		   }
	   
	   // 若未选择任何字头，则默认选中“动车”
	   if(index_seatTypeAndNum == "") {
		   setTrainClassDefaultSelect();
	   }
	   setSeatTypeAndNumDisabled();
	   
	   //初始化返程选中的动作
	   var checkSingleRound = $(":radio[name='singleRoundType']:checked");
	   if(checkSingleRound.length>0){
		   var initchecked = $(checkSingleRound[0]);
		   if(initchecked.val()=="2"){
		     initchecked.click();
		   }
	   }
});