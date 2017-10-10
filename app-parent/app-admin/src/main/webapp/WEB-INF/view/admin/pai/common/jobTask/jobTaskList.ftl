<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>任务调度管理</title>
	<#include "/WEB-INF/view/common/jquery.ftl">
	<#include "/WEB-INF/view/common/ligerUI.ftl">		 
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/common.js" ></script>					    					    
    <script type="text/javascript">
    	var grid = null;
        var searchForm = null;
        $(function ()
        {
        	searchForm = $("#form1").ligerForm({
				inputWidth : 180, labelWidth : 90, space : 50, rightToken :'',
				fields : [
					{ display: '名称:', name: 'Q__S__EQ__name', newline : true, align: 'left', width: 140 },
					{ display: '类名:', name: 'Q__S__EQ__bean', newline : false, align: 'left', width: 140 },
					{ display: '类型:', name: 'Q__S__EQ__type', newline : true, align: 'left', width: 140, type : "select", 
						options: {
		                     valueField: 'id',
		                     textField: 'name',
		                     data:[{'id':'one_time','name':'执行一次'},{'id':'expression','name':'按表达式执行'}]
		                 }
					},
					{ display: '状态:', name: 'Q__S__EQ__status', newline : false, align: 'left', width: 140, type : "select", 
						options: {
		                     valueField: 'id',
		                     textField: 'name',
		                     data:[{'id':'1','name':'运行中'},{'id':'2','name':'停止中'}]
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
					{ display: '名称', name: 'name', align: 'left', width: 100, minWidth: 60 },
					{ display: '描述', name: 'description', align: 'left', width: 140, minWidth: 60 },
					{ display: '类名', name: 'bean', align: 'left', width: 100, minWidth: 60 },
					{ display: '所属组', name: 'groupName', align: 'left', width: 80, minWidth: 60 },
					{ display: '类型', name: 'type', align: 'left', width: 80, minWidth: 60, 
						render: function(rowdata,index,value){
							if(value=='one_time')
								return "执行一次";
							else if(value=='expression')
								return "按表达式执行";
						}
					},
					{ display: 'cron表达式', name: 'expression', align: 'left', width: 100, minWidth: 60 },
					{ display: '状态', name: 'status', align: 'left', width: 80, minWidth: 60, 
						render: function(rowdata,index,value){
							if(value==1)
								return "<font color='#e00'>运行中</font>";
							else if(value==2)
								return "<font color='#e00'>停止中</font>";
						}
					},
					{ display: '操作', name: 'operation', align: 'left', width: 80, minWidth: 60, 
						render: function(rowdata,index,value){
							if(rowdata.status==1)
								return "<a color='#e00' href=\"javascript:operateJob(2, '"+ rowdata.id +"');\">停止</a>";
							else if(rowdata.status==2)
								return "<a color='#e00' href=\"javascript:operateJob(1, '"+ rowdata.id +"');\">运行</a>";
						}
					},
					
                ], 
                url:'${CtxPath}/admin/pai/common/jobTask/listData.do', 
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
	                { id:'param',text: '配置参数', click: param, img: '${CtxPath}/scripts/ligerUI/skins/icons/page_white_code.png' },
	                { line: true },
	                { id:'log',text: '查看日志', click: log, icon: 'view' },
	                { line: true },
	                { id:'delete',text: '删除', click: deleteRow, icon: 'delete' },
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
    <script type="text/javascript" src="${CtxPath}/scripts/admin/pai/common/jobTask.js" ></script>
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