<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>用户管理</title>
	<#include "/WEB-INF/view/common/jquery.ftl">
	<#include "/WEB-INF/view/common/ligerUI.ftl">		 
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/common.js" ></script>					    					    
    <script type="text/javascript">
    	var grid = null;
        var searchForm = null;
        $(function ()
        {
        	searchForm = $("#form1").ligerForm({
				inputWidth : 180, labelWidth : 50, space : 50, rightToken :'',
				fields : [
					{ display: '用户名:', name: 'Q__S__LK__a__name', newline : true, align: 'left', width: 140 },
					{ display: '角色:', name: 'roleId', newline : false, align: 'left', width: 140, type:'combobox', options:{
							valueField: 'id',
	                        textField: 'name',
	                        slide: false,
	                        selectBoxWidth: 400,
	                        selectBoxHeight: 290,
	                        condition: { fields: [{ name: 'name', label: '角色名称', op : 'like', vt : 'string', width: 150, type: 'text' }] },
	                        grid: roleGridOptions(false),
	                        hideGridOnLoseFocus: true
						} 
					},
					{ display: 'aliasSortName', name: 'aliasSortName',type:'hidden'},	
		          	{ display: "<input type='button' value='查询' class='l-button' onClick='javascript:fnListSearch();' style='width:50px;'>", name: "searchButton", newline: false, width:0.01}
				 ]
			 });        
        	
            grid = $("#maingrid").ligerGrid({
                height:'100%',
                
                onChangeSort: function (sortname, sortorder) {
	    			  $("input[name='aliasSortName']").val(sortname);
		              fnListSearch();
		              return;
	             } ,
	             
                columns: [
					{ display: '用户名', name: 'name', align: 'left', width: 80, minWidth: 60 },
					{ display: '手机号', name: 'phone', align: 'left', width: 150, minWidth: 60 },
					/* { display: '密码', name: 'password', align: 'left', width: 80, minWidth: 60 }, */
					{ display: '状态', name: 'status', align: 'left', width: 80, minWidth: 60, render: function(rowdata,index,value){
							if(value==1)
								return "正常";
							else if(value==2)
								return "冻结";
						} 
					},
					{ display: '所属角色', name: 'roleNames', align: 'left', width: 150, minWidth: 60 },
					{ display: '创建人', name: 'createBy', align: 'left', width: 80, minWidth: 60 },
					{ display: '创建时间', name: 'createTime', align: 'left', type:'date', options:{showTime: true,format:'yyyy-MM-dd hh:mm:ss'}, width: 180, minWidth: 60 },
					{ display: '修改人', name: 'updateBy', align: 'left', width: 80, minWidth: 60 },
					{ display: '修改时间', name: 'updateTime', align: 'left', type:'date', options:{showTime: true,format:'yyyy-MM-dd hh:mm:ss'}, width: 180, minWidth: 60 }
                ], 
                url:'${CtxPath}/admin/pai/auth/authUser/listData.do', 
                pageSize:30 ,
                rownumbers:true,
                pagesizeParmName:'pageSize',
                onReload:setDataToGrid,
                onDblClickRow : function (rowdata,index,value){
                     editDialog(rowdata.id);
                },
                toolbar: { items: [
	                { id:'add',text: '增加', click: add, icon: 'add' },
	                { line: true },
	                { id:'modify',text: '修改', click: edit, icon: 'modify' },
	                { line: true },
	                { id:'delete',text: '删除', click: deleteRow, img: '${CtxPath}/scripts/ligerUI/skins/icons/delete.gif' },
	                { line: true },
	                { id:'modify',text: '刷新', click: refresh, icon: 'refresh' }                
              	  ]
                }
            });             

            $("#pageloading").hide();
        });
        
        var __ctxPath = "${CtxPath}";
        function fnListSearch(){
			if(grid!=null){	            
				grid.setOptions({newPage:1});
				setDataToGrid();				
				grid.loadData();
			}
		}
		function setDataToGrid(){		
			var data = searchForm.getData();
			if(grid!=null){
				grid.set("parms",[]);
				for(var param in data){					
					$("input[name='"+param+"']").each(function() {
						var id = $(this).attr("id");						
						var paramValue = $("#" + id).val();					
                    	grid.get("parms").push({ name: param, value: paramValue });
     				});					
				}
			}			
		}
		function roleGridOptions(checkbox){
            var options = {
                url: __ctxPath+"/admin/pai/auth/authRole/listData.do",
	    		switchPageSizeApplyComboBox: false,
	    		checkbox:checkbox,
	    		width:500,
                selectBoxWidth:500,	   
                usePager:false,
                autoCheckChildren:false,
                allowUnSelectRow:true,
	            columns: [				
		            { display: 'ID',hide:'1', name: 'id',id:"id", align: 'left', width: 100, minWidth: 60 },          
		            { display: '角色名称', name: 'name',id:"name", align: 'left', width: 150, minWidth: 60 },
		    		{ display: '描述', name: 'descript', align: 'left', width: 100, minWidth: 60 },
		    		{ display: '状态', name: 'status', align: 'left', width: 120, minWidth: 60, render: function(rowdata,index,value){
								if(value==1)
									return "正常";
								else if(value==2)
									return "冻结";
							}
					 }
	           ]
            };
            return options;
		}
    </script>
    <script type="text/javascript" src="${CtxPath}/scripts/admin/pai/auth/authUser.js" ></script>
</head>
<body style="overflow-x:hidden; padding:2px;">
	<div class="l-loading" style="display:block" id="pageloading"></div>
 		<a class="l-button" style="width:120px;float:left; margin-left:10px; display:none;" onclick="deleteRow()">删除选择的行</a> 
 		<div class="l-clear"></div>
 		<form id="form1"></form>
    	<div id="maingrid"></div>
   		<div style="display:none;">
	</div> 
</body>
</html>