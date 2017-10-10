function add() {
	$.ligerDialog.open({
		height:600,
		width: 800,
		title : '任务调度参数 ',
		url: __ctxPath + '/admin/pai/common/jobTaskParam/edit.do?jobId='+$('#jobId').val(), 
		showMax: true,
		showToggle: true,
		showMin: true,
		isResize: true,
		slide: false                
	}).max();
}
		
function edit(item)
{
	var selected = grid.getSelected();
	if(selected){
		editDialog(selected.id);
	}else{
		$.ligerDialog.tip({  title: '操作提示',content:'请选择要操作的记录'});
	}		
}

function editDialog(id) {
	$.ligerDialog.open({
		height:600,
		width: 800,
		title : '任务调度参数',
		url: __ctxPath + '/admin/pai/common/jobTaskParam/edit.do?id=' + id, 
		showMax: true,
		showToggle: true,
		showMin: true,
		isResize: true,
		slide: false                
	}).max();
}
		
function deleteRow()
{
	var selected = grid.getSelected();
	if(selected){
		$.ligerDialog.confirm('请确认是否删除该记录', function (yes)
				{
	            	if(yes){
	            		var url = __ctxPath + "/admin/pai/common/jobTaskParam/delete.do";
	            		var params = "id="+selected.id;
	            		$.post(url,params,deleteResponse);            		            		
	            	}             
				});        	
	}else{
		$.ligerDialog.tip({  title: '操作提示',content:'请选择要删除的记录'});
	}                    
}		
        
function deleteResponse(responseText){        
	var result = JSON.parse(responseText);
	if(result.success){	
		var msg = getMsg(result.msgCode);				
		$.ligerDialog.success(msg);   
		grid.deleteSelectedRow(); 	 	
	} else {
		var msg = getMsg(result.msgCode);				
		$.ligerDialog.error(msg);
	}       	
}

function valueTypeSelect(id,value,status){
	$("#"+id).ligerComboBox({
		data: [
			{ text: '字符串', id: 'String'},
			{ text: '整形', id: 'int'},
			{ text: '长整型', id: 'long'},
			{ text: '浮点', id: 'double'}
        	
		],
		valueField:'id',
		textField:'text',
		valueFieldID:status
	});
	//设置初始化值
	$("#"+id).ligerGetComboBoxManager().setValue(value);
	$("#"+id).ligerGetComboBoxManager().updateStyle();
}