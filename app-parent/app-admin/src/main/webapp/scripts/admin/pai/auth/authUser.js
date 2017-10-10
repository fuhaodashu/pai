function add() {
	$.ligerDialog.open({
		height:600,
		width: 800,
		title : '新增用户 ',
		url: __ctxPath + '/admin/pai/auth/authUser/edit.do', 
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
		title : '修改用户 ',
		url: __ctxPath + '/admin/pai/auth/authUser/edit.do?id=' + id, 
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
	            		var url = __ctxPath + "/admin/pai/auth/authUser/delete.do";
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

function bindSelectTree(id,valueFieldId,options){ 
	var getGridOptions =function(checkbox) {		
	    var option = {
	    		url:__ctxPath+"/admin/pai/auth/authRole/listData.do", 
	    		checkbox: true,
	    		switchPageSizeApplyComboBox: false,
	    		width: 200,
                selectBoxWidth: 200,	   
                usePager:false,
                autoCheckChildren:false,
                allowUnSelectRow:true,//是否允许取消选择行
                cancelable: true,
	            columns: [				
	            { display: 'ID' ,hide:'1', name: 'id',id:"id", align: 'left', width: 10, minWidth: 60 },          
	            { display: '角色名称', name: 'name',id:"name", align: 'left', width: 150, minWidth: 60 },
	    		{ display: '描述', name: 'descript', align: 'left', width: 100, minWidth: 60 },
	    		{ display: '状态', name: 'status', align: 'left', width: 120, minWidth: 60, render: function(rowdata,index,value){if(value==1)return "有效";else if(value==2)return "无效";} }
	            ]
	    };
	    return option;
	};
	var	manager =$("#" + id).ligerPopupEdit({
		condition: { fields: [{ name: 'name', label: '角色名称', op : 'like', vt : 'string', width: 150, type: 'text' }] },
        grid: getGridOptions(false),
        valueField: 'id',
        textField: 'name',
        heigth:800,
        valueFieldID :valueFieldId,
        onSelected:function(rowdata){
        	var oldKeys=[];
        	if(options.value != "")
        		oldKeys=options.value.split(";");
        	var keys=rowdata.value.split(";");
        	var oldNames=options.text.split(";");
        	var names=rowdata.text.split(";");
        	for(var i=0; i<oldKeys.length; i++){
        		keys.splice(jQuery.inArray(oldKeys[i],keys),1); 
        		names.splice(jQuery.inArray(oldNames[i],names),1);
        	}
        	options.value=keys.join(";");
        	options.text=names.join(";");
        	$("#" + id).ligerGetPopupEditManager().setValue(keys.join(";"));
        	$("#" + id).ligerGetPopupEditManager().setText(names.join(";"));
        }
    });
	if(options.text){
		manager.setText(options.text);
		manager.setValue(options.value);
	}	
	manager.updateStyle() ;
	return manager;
}