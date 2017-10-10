<link href="${CtxPath}/styles/admin/pai/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${CtxPath}/scripts/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var __ctxPath = "${CtxPath}";
	$(document).ready(function(){		
  		$("#userName").focus();  
  		$("#password").val("${loginInfo.password}");
  		$('#sub').bind("click", function(){  
			var userName=$("#userName").val();
			var password=$("#password").val();
			var captchaCode=$("#captchaCode").val();
			if(userName==''||userName==null){
				$(".err_info").html('<img src="${CtxPath}/images/common/icon_warning.gif"/>&nbsp;<span style="color:#ff0000;">用户名不能为空</span>');
				return false;
			}
			if(password==''||password==null){
				$(".err_info").html('<img src="${CtxPath}/images/common/icon_warning.gif"/>&nbsp;<span style="color:#ff0000;">密码不能为空</span>');
				return false;
			}
			if(captchaCode==''||captchaCode==null){
				$(".err_info").html('<img src="${CtxPath}/images/common/icon_warning.gif"/>&nbsp;<span style="color:#ff0000;">验证码不能为空</span>');
				return false;
			}
			$("#formLogin").attr("action","${CtxPath}/adminLogin.do");
			$("#formLogin").submit();   
		}); 
	});
	function refreshImage(imageId){
		var image=document.getElementById(imageId);
		var url="${CtxPath}/generateImage.do";
		var xmlHttpReq=null;
		if(window.ActiveXObject){
			xmlHttpReq=new ActiveXObject("Microsoft.XMLHTTP");
		}else if(window.XMLHttpRequest){
			xmlHttpReq = new XMLHttpRequest();
		}
		xmlHttpReq.open("Post", url, true);
		xmlHttpReq.send(null); 
		
		image.src=url + "?rand="+parseInt(1000*Math.random());
		return false;
	}
	
	$("#captchaCode").keydown(function(e){  
  		if(e.keyCode==13){  
   			$("#formLogin").submit();//处理事件  
  		}  
	});	
	if(top!=this){//当这个窗口出现在iframe里，表示其目前已经timeout，需要把外面的框架窗口也重定向登录页面
		top.location='${CtxPath}/adminLogin.do';
	}
</script>
<body>
<div class="wrap">
	<div id="m" class="left_div">    
		<p id="lg">
			<img src="${CtxPath}/images/dragon/before.gif" style="width:100%;" usemap="#mp">
			<map name="mp">
				<area shape="rect" coords="22,17,252,110" href=""
				target="_blank" onmousedown="return ns_c({'fm':'behs','tab':'bdlogo'})">
			</map>
		</p>
	</div>
	<div class="verticalLine"></div>
	<div class="right_div">
		<div class="loginForm_wap">
			<div class="top_title">π后台</div>
			<form id="formLogin" method="post">
					用户名：<input type="text" class="input_txt" id="userName" name="userName" value="${loginInfo.userName}"/> <br /> 
					<input type="hidden" id="isLogin" name="isLogin" value="Y"/>
					<div class="input_space"></div>
					<font style="margin-left:16px;">密码：</font><input type="password" class="input_txt" id="password" name="password"  value="${loginInfo.password}"/> <br />  
					<div class="input_space"></div>
					验证码：<input type="text" id="captchaCode" name="captchaCode"  tabindex="3" class="input_code"/><span><img id="imgCaptchaCode" name="imgCaptchaCode" class="imgCaptchaCode" src="${CtxPath}/generateImage.do" 
									onclick="refreshImage('imgCaptchaCode')" alt="换一张" title="换一张" /></span>
					<br /> <input type="submit" id="sub" class="sub" value="登 录" />
					<#if loginInfo?exists>
					<#assign msgCode="${loginInfo.commonResult.msgCode}"/>
					<#if msgCode?exists && msgCode?length gt 0>
					<div class="err_info"><img src="${CtxPath}/images/common/icon_warning.gif"/>&nbsp;<span style="color:#ff0000;">					
						${loginInfo.commonResult.msg}							
					</span></div>
					</#if>
				</#if>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/dragon.js"></script>
</body>
