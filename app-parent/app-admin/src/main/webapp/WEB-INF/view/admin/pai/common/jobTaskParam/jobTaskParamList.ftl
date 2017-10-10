<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参管理</title>
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
					{ display: '参数键:', name: 'Q__S__EQ__paramKey', newline : false, align: 'left', width: 140 },
					{ display: '参数值:', name: 'Q__S__EQ__paramValue', newline : false, align: 'left', width: 140 },
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
					{ display: '所属job', name: 'name', align: 'left', width: 140, minWidth: 60 },
					{ display: '所属类名', name: 'bean', align: 'left', width: 140, minWidth: 60 },
					{ display: '参数键', name: 'paramKey', align: 'left', width: 140, minWidth: 60 },
					{ display: '参数值', name: 'paramValue', align: 'left', width: 140, minWidth: 60 },
					{ display: '参数值类型', name: 'valueType', align: 'left', width: 140, minWidth: 60, 
						render:function(rowdata,index,value){
			        		if(value=='string') return '字符串';
			        		if(value=='int') return '整型';
			        		if(value=='long') return '长整型';
			        		if(value=='double') return '浮点型';
			        		return '特殊类型';
			        	}
					}
                ], 
                url:'${CtxPath}/admin/pai/common/jobTaskParam/listData.do?Q__S__EQ__job_id_=${jobId}', 
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
    </script>
    <script type="text/javascript" src="${CtxPath}/scripts/admin/pai/common/jobTaskParam.js" ></script>
</head>
<body style="overflow-x:hidden; padding:2px;">
	<div class="l-loading" style="display:block" id="pageloading"></div>
 		<a class="l-button" style="width:120px;float:left; margin-left:10px; display:none;" onclick="deleteRow()">删除选择的行</a> 
 		<div class="l-clear"></div>
 		<form id="form1"></form>
    	<div id="maingrid"></div>
    	<input type="hidden" value="${jobId}"  id="jobId"/>
   		<div style="display:none;">
	</div> 
</body>
</html>