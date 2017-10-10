<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>pai_message_record管理</title>
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
					{ display: '状态:', name: 'Q__S__EQ__status', newline : true, align: 'left', width: 140, type : "select", 
						options: {
		                     valueField: 'id',
		                     textField: 'name',
		                     data:[{'id':'WAITING_CONFIRM','name':'等待确认'},{'id':'SENDING','name':'发送中'}]
		                 }
					},
					{ display: '是否死亡:', name: 'Q__S__EQ__areadly_dead', newline : false, align: 'left', width: 140, type : "select", 
						options: {
		                     valueField: 'id',
		                     textField: 'name',
		                     data:[{'id':'Y','name':'是'},{'id':'N','name':'否'}]
		                 }
					},
					{ display: 'handler前缀:', name: 'Q__S__EQ__msg_type', newline : false, align: 'left', width: 140 },
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
					{ display: '状态', name: 'status', align: 'left', width: 80, minWidth: 60,
						render:function(rowdata,index,value) {
							if(value == "SENDING") {
								return '发送中';
							}else if(value == "WAITING_CONFIRM") {
								return '等待确认';
							}
						}
					},
					{ display: '回调URL', name: 'url', align: 'left', width: 200, minWidth: 60 },
					{ display: '消息内容', name: 'messageBody', align: 'left', width: 300, minWidth: 60 },
					{ display: '消息重发次数', name: 'messageSendTimes', align: 'left', width: 40, minWidth: 60 },
					{ display: '是否死亡', name: 'areadlyDead', align: 'left', width: 60, minWidth: 60,
						render:function(rowdata,index,value) {
							if(value == "Y") {
								return '<font color="#e00">是</font>';
							}else if(value == "N") {
								return '<font color="#e00">否</font>';
							}
						}
					},
					{ display: '监听处理类handler前缀', name: 'msgType', align: 'left', width: 80, minWidth: 60 },
					{ display: '备注', name: 'remark', align: 'left', width: 60, minWidth: 60 },
					{ display: '创建时间', name: 'createTime', align: 'left', type:'date', options:{showTime: true,format:'yyyy-MM-dd hh:mm:ss'}, width: 140, minWidth: 60 },
					{ display: '最后修改时间', name: 'updateTime', align: 'left', type:'date', options:{showTime: true,format:'yyyy-MM-dd hh:mm:ss'}, width: 140, minWidth: 60 }
                ], 
                url:'${CtxPath}/admin/pai/message/messageRecord/listData.do', 
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
    <script type="text/javascript" src="${CtxPath}/scripts/admin/pai/message/messageRecord.js" ></script>
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