<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>资源管理</title>
	<#include "/WEB-INF/view/common/jquery.ftl">
	<#include "/WEB-INF/view/common/ligerUI.ftl">		 
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/common.js" ></script>					    					    
    <script type="text/javascript">
    	var grid = null;
        var searchForm = null;
        $(function ()
        {
        	searchForm = $("#form1").ligerForm({
				inputWidth : 180,labelWidth : 70,space : 50,rightToken :'',
				fields : [
					{ display: '名称:', name: 'Q__S__LK__name', newline : false, align: 'left', width: 140 },
					{ display: '资源类型:', name: 'Q__S__EQ__type', newline : false, align: 'left', width: 140, type : "select",
						options: {
		                     valueField: 'id',
		                     textField: 'name',
		                     data:[{'id':'1','name':'左侧菜单'},{'id':'2','name':'功能按钮'}]
		                 }
					},
					{ display: '层次:', name: 'Q__S__EQ__depth', newline : false, align: 'left', width: 140 },
					{ display: 'aliasSortName', name: 'aliasSortName',type:'hidden'},	
					{ display: "<input type='button' value='查询' class='l-button' onClick='javascript:fnListSearch();' style='width:50px;'>",name: "searchButton", newline: false, width:0.01}
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
					{ display: '名称', name: 'name', id: 'deptname', align: 'left', width: 250, minWidth: 60 },
					{ display: '资源类型', name: 'type', align: 'left', width: 80, minWidth: 60,
						render: function(rowdata,index,value){
							if(value==1)
								return "左侧菜单";
							else if(value==2)
								return "功能按钮";
						}
					},
					{ display: '资源链接', name: 'url', align: 'left', width: 350, minWidth: 60 },
					{ display: '树路劲', name: 'path', align: 'left', width: 150, minWidth: 60 },
					{ display: '层次', name: 'depth', align: 'left', width: 60, minWidth: 60 },
					//{ display: '图标', name: 'icon', align: 'left', width: 150, minWidth: 60 },
					{ display: '排序', name: 'sort', align: 'left', width: 60, minWidth: 60 },
					{ display: '状态', name: 'status', align: 'left', width: 60, minWidth: 60,
						render: function(rowdata,index,value){
							if(value==1)
								return "有效";
							else if(value==2)
								return "无效";
						}
					}
                ], 
                url:'${CtxPath}/admin/pai/auth/authResources/listData.do', 
                pageSize:500 ,
                pageSizeOptions : [ 500, 1000, 2000 ],
                rownumbers:true,
                usePager:false,
                pagesizeParmName:'pageSize',
                alternatingRow : false,
				enabledSort : false,
                onReload:setDataToGrid,
                onDblClickRow : function (rowdata,index,value){
                     editDialog(rowdata.id);
                },
                tree : {
					isExpand : false,
					slide : false,
					columnId : 'deptname',
					idField : 'id',
					parentIDField : 'parentId'
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
    </script>
    <script type="text/javascript" src="${CtxPath}/scripts/admin/pai/auth/authResources.js" ></script>
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