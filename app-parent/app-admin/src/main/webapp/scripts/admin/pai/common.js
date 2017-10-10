function refresh()
{
	location.reload();
}
function getMsg(msgCode){
	if(msgCode=="CREATE"){
		return "新建成功";
	}else if(msgCode=="UPDATE"){
		return "更新成功";
	}else if(msgCode=="DELETE"){
		return "删除成功";
	}
	return msgCode;
}
	
// ========== 新增或编辑页面使用的JS方法 ============
/**
 * 通用的状态下拉列表数据绑定
 * @param id
 * @param value
 */
function bindStatusSelect(id,value){
	$("#"+id).ligerComboBox({  
		data: [
			{ text: '未激活', id: 'inactive' },
        	{ text: '激活', id: 'actived' }
		], 
		valueFieldID: 'status',
		valueField:'id',
		textField:'text',                
		emptyText:'（请选择状态）'      
	});
	
	//设置初始化值
	if(value!=""){
		$("#"+id).ligerGetComboBoxManager().setValue(value);	
	}else{
		$("#"+id).ligerGetComboBoxManager().setValue('actived');
	}                        
	$("#"+id).ligerGetComboBoxManager().updateStyle();				
}

function bindStatusSelectWithLock(id,value){
	$("#"+id).ligerComboBox({  
		data: [
			{ text: '未激活', id: 'inactive' },
        	{ text: '激活', id: 'actived' },
			{ text: '锁定', id: 'locked' }
		], 
		valueFieldID: 'status',
		valueField:'id',
		textField:'text',                
		emptyText:'（请选择状态）'           
	});
	
	//设置初始化值
	if(value!=""){
		$("#"+id).ligerGetComboBoxManager().setValue(value);	
	}else{
		$("#"+id).ligerGetComboBoxManager().setValue('actived');
	}                        
	$("#"+id).ligerGetComboBoxManager().updateStyle();				
}

function bindBoolSelect(id,value,valueFieldID){
	$("#"+id).ligerComboBox({  		
		data: [
			{ text: '是', id: 'Y' },
        	{ text: '否', id: 'N' }			
		], 
		valueFieldID: valueFieldID,
		valueField:'id',
		textField:'text',                
		emptyText:'（请选择是否）'          
	});
	
	//设置初始化值
	if(value=='N'){
		$("#"+id).ligerGetComboBoxManager().setValue(value);	
	}else{
		$("#"+id).ligerGetComboBoxManager().setValue('Y');
	}	                       
	$("#"+id).ligerGetComboBoxManager().updateStyle();				
}

function bindDataTypeSelect(id,value,valueFieldID){
	$("#"+id).ligerComboBox({  		
		data: [
			{ text: '字符串', id: 'varchar' },
        	{ text: '整型', id: 'int' },			
        	{ text: '浮点', id: 'decimal' },
        	{ text: '日期', id: 'datetime' },
        	{ text: '大文本', id: 'text' },
        	{ text: '可选项', id: 'options' }
		], 
		valueFieldID: valueFieldID,
		valueField:'id',
		textField:'text',                
		emptyText:'（请选择数据类型）'         
	});
	
	//设置初始化值
	if(value!=''){
		$("#"+id).ligerGetComboBoxManager().setValue(value);	
	}else{
		$("#"+id).ligerGetComboBoxManager().setValue('varchar');
	}	                       
	$("#"+id).ligerGetComboBoxManager().updateStyle();				
}


/**
 * 通用的表单编辑返回
 * @param responseText
 */
function showResponse(responseText) {
	var result = JSON.parse(responseText);
	if(result.success){
		var msg = "";
		if(result.msgCode){
			msg = getMsg(result.msgCode);
			/*if(result.msg){
				msg = msg + "." + result.msg;
			}*/
		}else{
			msg = result.msg;
		}		
		$.ligerDialog.success(msg,function(){				
				parent.$.ligerDialog.close();
				parent.refresh();
                parent.$(".l-dialog,.l-window-mask").remove();                                                                                 
        });    	 	
    }else{
    	alert(result.msg);
    }
	setBegin(true,"button_save");
} 


var posting = true;
function bindFormValidation(formId,url,directPost){
	$.metadata.setType("attr", "validate");
	$('#'+formId).validate({
		debug: true,
 		errorPlacement: function (lable, element)	{
			if (element.hasClass("l-textarea"))
			{
				element.ligerTip({ content: lable.html(), target: element[0] }); 
            }
			else if (element.hasClass("l-text-field"))
			{
				element.parent().ligerTip({ content: lable.html(), target: element[0] });
			}
			else
			{
				lable.appendTo(element.parents("td:first").next("td"));
			}
		},
    	success: function(label) {
			label.ligerHideTip();
            label.remove();       
    	},		
    	submitHandler:function(form){     
    		if(posting){
    			posting = false;
    			if(directPost){    			
        			form.submit();
        			return false;
        		}else{
        			var params=$(form).serialize();	    		
        			$.post(url,params,showResponse);    			
        		}
    		}
    		
    	}	    
	});		
}

/*
 *fileId:要上传的文件的id
 *valueId:存入数据库的id
 *displayId:需要显示图片的id
 *categoryDir：上传云端的目录，没有的话自动新建
 *isRename_:是否重命名
*/
function ajaxFileUploadCommonNew(fileId,valueId,displayId,categoryDir,isRename_) {
	var isRename = 'Y';
	if(isRename_==false){
		isRename = 'N';
	}
   $.ajaxFileUpload({
	   url:__ctxPath+'/platform/upload/ajax.do?categoryDir=' + categoryDir + '&isRename='+isRename, //你处理上传文件的服务端
	   secureuri:false,
	   fileElementId:fileId,
	   dataType: 'json',
	   success: function (data) {	
		   var urlTemp = data.lastFile;
		   urlTemp = urlFormat(urlTemp);
		   	$("#"+valueId).attr("value",data.cloudUploadResult.cloudPath);
		   	if($("#" + displayId)){
		   		$("#" + displayId).attr("src",urlTemp);
	   		}
		   	$.ligerDialog.tip({  title: '系统信息',content:'文件上传成功'});
	   },
	   error:function(data){
		   alert('error');
	   }
   });
   return false;
}

function ajaxFileUploadCommon(fileId,valueId,categoryDir,isRename_) {
	var isRename = 'Y';
	if(isRename_==false){
		isRename = 'N';
	}
   $.ajaxFileUpload({
	   url:__ctxPath+'/platform/upload/ajax.do?categoryDir=' + categoryDir + '&isRename='+isRename, //你处理上传文件的服务端
	   secureuri:false,
	   fileElementId:fileId,
	   dataType: 'json',
	   success: function (data) {	
		   var urlTemp = data.lastFile;
		   urlTemp = urlFormat(urlTemp);
		   	$("#"+valueId).attr("value",data.cloudUploadResult.cloudPath);
		   	if($("#" + valueId + "ImageId")){
		   		$("#" + valueId + "ImageId").attr("src",urlTemp);
	   		}
		   	$.ligerDialog.tip({  title: '系统信息',content:'文件上传成功'});
	   },
	   error:function(data){
		   alert('error');
	   }
   });
   return false;
} 

function ajaxFileUploadForProductMedia(fileId,categoryDir,callback,isRename_) {
	var isRename = 'Y';
	if(isRename_==false){
		isRename = 'N';
	}	
   $.ajaxFileUpload({
	   url:__ctxPath+'/platform/upload/ajax.do?categoryDir=' + categoryDir + '&isRename='+isRename, //你处理上传文件的服务端
	   secureuri:false,
	   fileElementId:fileId,
	   dataType: 'text',
	   success:callback
   });
   return false;
} 

function moveToLeft(src,dest,hiddenIds)
{
    var box1 = liger.get(src), box2 = liger.get(dest);
    var selecteds = box2.getSelectedItems();
    if (!selecteds || !selecteds.length) return;
    box2.removeItems(selecteds);
    box1.addItems(selecteds);
    
    setSelectIds(dest,hiddenIds);
}
function moveToRight(src,dest,hiddenIds)
{
    var box1 = liger.get(src), box2 = liger.get(dest);
    var selecteds = box1.getSelectedItems();
    if (!selecteds || !selecteds.length) return;
    box1.removeItems(selecteds);
    box2.addItems(selecteds);
    
    setSelectIds(dest,hiddenIds);
}
function moveAllToLeft(src,dest,hiddenIds)
{ 
    var box1 = liger.get(src), box2 = liger.get(dest);
    var selecteds = box2.data;
    if (!selecteds || !selecteds.length) return;
    box1.addItems(selecteds);
    box2.removeItems(selecteds);
    
    setSelectIds(dest,hiddenIds);
}
function moveAllToRight(src,dest,hiddenIds)
{ 
    var box1 = liger.get(src), box2 = liger.get(dest);
    var selecteds = box1.data;
    if (!selecteds || !selecteds.length) return;
    box2.addItems(selecteds);
    box1.removeItems(selecteds);
   
    setSelectIds(dest,hiddenIds);
}
function setSelectIds(dest,hiddenIds){
	var box2 = liger.get(dest);
	var ids = "";
	var selecteds = box2.data;
	for(var i=0;i<selecteds.length;i++){
		var selected = selecteds[i];
		if(ids==""){
			ids = selected.id;
		}else{
			ids = ids + "," + selected.id;
		}
	}
	$("#"+hiddenIds).val(ids);
}

/**
 * 提交保单
 * url 提交地址
 * formId表单ID，默认为infoForm
 * callback 回调函数，默认为showResponse
 */
function saveForm(url,formId,callback){
	setBegin(false,"button_save");
	if(!formId){
		formId="infoForm";
	}
	var form=$("#"+formId);
	//验证表单
	if(form.valid()){
		var data=getFormData(form);
		if(!callback){
			$.post(url,data,showResponse);
		}else{
			$.post(url,data,callback);  
		}
	//验证不通过
	}else{
		setBegin(true,"button_save");
	}
}

/**
 * 设置按钮 置灰或恢复
 * falg=true 恢复，falg=false 置灰
 * buttonId 按钮Id ，默认button_save
 */
function setBegin(flag,buttonId){
	if(!buttonId ){
		buttonId="button_save";
	}
	if(flag){
		$("#"+buttonId).removeAttr("disabled");
	}else{
		$("#"+buttonId).attr("disabled","disabled");
	}
	
}
/**
 * 封装表单
 */
function getFormData(form){
	var objJson={};
	$(form).serialize();	   
	$(':checkbox,:radio',form).each(function(){
		var name=$(this).attr('name');
		objJson[name]='';
	});

	$(':checkbox:checked,:radio:checked',form).each(function(){
			var name=$(this).attr('name');
			if(objJson[name]==''){
				objJson[name]=$(this).val();
			}
			else{
				objJson[name]+="," + $(this).val();
			}
	});
	
	form.find('input:text,input:hidden,input:password,textarea,select').each(function(){
		var value=$(this).val();
	
		var name=$(this).attr('name');
		if(name){
			if(value==null){
				value="";
			}
			objJson[name]=value;
		}
	});
	return objJson;
}

/**
 * 清空表单
 * formId表单Id 默认infoForm
 */
function cleanForm(formId){
	if(!formId){
		formId="infoForm";
	}
	$(':input','#'+formId)  
	 .not(':button, :submit, :reset, :hidden')  
	 .val('')  
	 .removeAttr('checked')  
	 .removeAttr('selected');
}




function urlFormat(url){
	var temp = url.replace(/[\\/=]/g,"/");	
	return temp.replaceAll("//","");
}