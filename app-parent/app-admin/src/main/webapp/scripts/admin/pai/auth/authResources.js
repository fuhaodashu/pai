function add() {
	$.ligerDialog.open({
		height:600,
		width: 800,
		title : '新增资源 ',
		url: __ctxPath + '/admin/pai/auth/authResources/edit.do', 
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
		title : '修改资源 ',
		url: __ctxPath + '/admin/pai/auth/authResources/edit.do?id=' + id, 
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
	            		var url = __ctxPath + "/admin/pai/auth/authResources/delete.do";
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
	    		url:__ctxPath+"/admin/pai/auth/authResources/listData.do?Q__S__EQ__type=1", 
	    		checkbox: checkbox,
	    		switchPageSizeApplyComboBox: false,
	    		width: 200,
                selectBoxWidth: 200,	   
                usePager:false,
                autoCheckChildren:false,
                allowUnSelectRow:true,//是否允许取消选择行
	    		tree : {
	    		//	needCancel:true,是否连选子节点
	    			isExpand : false,
	    			slide : false,
	    			columnId : 'name',
	    			idField : 'id',
	    			parentIDField : 'parentId',
	    			autoCheckboxEven:false
	    		},
	            columns: [				
	            { display: 'ID' ,hide:'1', name: 'id',id:"id", align: 'left', width: 10, minWidth: 60 },          
	            { display: '菜单名称', name: 'name',id:"name", align: 'left', width: 150, minWidth: 60 },
	    		{ display: '层次', name: 'depth', align: 'left', width: 100, minWidth: 60 },
	    		{ display: '排序', name: 'sort', align: 'left', width: 100, minWidth: 60 },
	    		{ display: '状态', name: 'status', align: 'left', width: 120, minWidth: 60, render: function(rowdata,index,value){if(value==1)return "有效";else if(value==2)return "无效";} }
	            ]
	    };
	    return option;
	};
	var	manager =$("#" + id).ligerPopupEdit({
		// condition: { fields: [{ name: 'name', label: '分类名称', width: 90, type: 'text' }] },
        grid: getGridOptions(false),
        valueField: 'id',
        textField: 'name',
        heigth:800,
        onSelected:function(rowdata){$("#depth").val(parseInt(rowdata.data[0].depth)+1);},
        valueFieldID :valueFieldId
    });

	if(options.text){
		manager.setText(options.text);
		manager.setValue(options.value);
	}	

	manager.updateStyle() ;

	return manager;
}