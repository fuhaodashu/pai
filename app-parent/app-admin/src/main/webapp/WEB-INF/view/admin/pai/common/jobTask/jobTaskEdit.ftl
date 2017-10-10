<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title></title>
	<#include "/common/jquery.ftl">
	<#include "/common/ligerUI.ftl">		 			
	<link href="${CtxPath}/scripts/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="${CtxPath}/styles/admin/pai/form.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/common.js" ></script>		    	            	    	
    <script type="text/javascript">  
	$(function(){		
		bindFormValidation("jobTaskEditForm","${CtxPath}/admin/pai/common/jobTask/save.do");
		
		setTimeout(function(){
		     if("${jobTaskPo.expression}" != "" && "${jobTaskPo.expression}" != null){
		          $(window.frames["cronFtl"].document).find("#cron").val($.trim("${jobTaskPo.expression}"));
		          $(window.frames["cronFtl"].document).find("#btnFan").click();
		     }
		},100);
			
		$("#Button1").click(function(){
		    $("#expression").val($(window.frames["cronFtl"].document).find("#cron").val());
		});
										
		$("#jobTaskEditForm").ligerForm();   
		
		$(".type").click(function(){
			if($("#type_1").attr("checked")){
				$("#cron").hide();
			}else{
				$("#cron").show();
			}
		});        
	});
	var __ctxPath = "${CtxPath}";	   
    </script>
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/common/jobTask.js" ></script>
</head>

<body style="padding:10px">	
    <form id="jobTaskEditForm" name="jobTaskEditForm" method="post">
    <#if jobTaskPo.id?exists>
    	<input type="hidden" name="pk" value="${jobTaskPo.id}" ltype="text"/>
    </#if>    	
    <#if jobTaskPo.createTime?exists>
    	<input type="hidden" name="createTime" value="${jobTaskPo.createTime?string('yyyy-MM-dd HH:mm:ss')}" ltype="text"/>
    </#if>
    <#if jobTaskPo.createBy?exists>
    	<input type="hidden" name="createBy" value="${jobTaskPo.createBy}" ltype="text"/>
    </#if>
    	<input type="hidden" id="expression" name="expression" value="${jobTaskPo.expression}" ltype="text"/>
    	<div>&nbsp;</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit">
		        <tr>
		            <td align="right" class="l-table-edit-td">名称:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="name" type="text" id="name"  value="${jobTaskPo.name}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">描述:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="description" type="text" id="description"  value="${jobTaskPo.description}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">类名(首字母小写):</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="bean" type="text" id="bean"  value="${jobTaskPo.bean}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <#if jobTaskPo.id?exists>
			        <tr>
			            <td align="right" class="l-table-edit-td">状态:</td>
			            <td align="left" class="l-table-edit-td">
			            	<input id="status_1" type="radio" name="status" value="1" <#if jobTaskPo.status != 2>checked="checked"</#if>/><label for="status_1">运行中</label> 
		                	&nbsp;&nbsp;&nbsp;&nbsp;
		                	<input id="status_2" type="radio" name="status" value="2" <#if jobTaskPo.status == 2>checked="checked"</#if>/><label for="status_2">停止中</label>
			            </td>
			            <td align="left"></td>
			        </tr>
			    </#if>
		        <tr>
		            <td align="right" class="l-table-edit-td">类型:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input id="type_1" type="radio" class="type" name="type" value="EXPRESSION" <#if jobTaskPo.type != 'one_time'>checked="checked"</#if>/><label for="type_1">按表达式执行</label> 
	                	&nbsp;&nbsp;&nbsp;&nbsp;
	                	<input id="type_2" type="radio" class="type" name="type" value="ONE_TIME" <#if jobTaskPo.type == 'one_time'>checked="checked"</#if>/><label for="type_2">执行一次</label>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr id="cron">
		            <td align="right" class="l-table-edit-td">表达式:</td>
		            <td align="left" class="l-table-edit-td" colspan="2">
		            	<iframe name="cronFtl" id="cronFtl" src="${CtxPath}/cron/cron.ftl" height=430 width=850 frameborder=0 frameborder="no" border="0" scrolling="no"></iframe>
		            </td>
		            <td align="left"></td>
		        </tr> 
        </table>
 		<br />
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
    </form>   
</body>
</html>
