﻿<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="com.siteview.ecc.log.OpObjectId"%>
<%@page import="com.siteview.ecc.log.OpTypeId"%>
<%@page import="com.siteview.actions.LoginUserRight"%>
<%@page import="com.siteview.ecc.log.AppendOperateLog"%>
<%@page import="com.siteview.base.queue.EccSessionListener"%>
<%@page import="com.siteview.base.queue.QueueManager"%>
<%@page import="com.siteview.ecc.util.Toolkit"%>
<%@page import="com.siteview.base.manage.Manager"%>
<%@page import="com.siteview.base.manage.View"%>
<%@page import="com.siteview.base.template.TemplateManager"%> 
<%@page import="java.util.Enumeration"%>
<%@page import="com.siteview.ecc.alert.util.BaseTools"%>
<%@page import="com.siteview.ecc.system.impl.ClientDiagnosisImpl"%>
<%@page import="com.siteview.base.data.IniFile"%>
<%@page import="java.util.Locale"%>
<%@page import="org.zkoss.util.Locales"%>
<%@page import="org.zkoss.web.Attributes"%>
<%
	String loginMsg = "";
 	try{
 		DiagnosisUtils.getClientDiagnosisImpl(request).execute();
 	}catch(Exception e){
		loginMsg=e.getMessage();
 	}

 	String user = request.getParameter("Account");
 	String password = request.getParameter("Password");
 	
 	//新增 ntlm 认证逻辑 NTLM(NT Lan Manager)是NT系统一种用户认证方法
	if ("NTLM".equalsIgnoreCase(BaseTools.getConfigString("auth.type"))) {
 		jcifs.smb.NtlmPasswordAuthentication ntlm = (jcifs.smb.NtlmPasswordAuthentication)request.getSession().getAttribute("NtlmHttpAuth");
 		user = ntlm.getUsername();
	}else if ("LDAP".equalsIgnoreCase(BaseTools.getConfigString("auth.type"))) {
	}else if ("ECC".equalsIgnoreCase(BaseTools.getConfigString("auth.type"))) {
	}else{
		loginMsg="Log type not set (NTLM/LDAP/ECC).";
	}
 	if (user == null) {
 		if (request.getCookies() != null) {
 			String autoLogin = Toolkit.getToolkit().getCookie(
 					"autoLogin", request);
 			String recordAccount = Toolkit.getToolkit().getCookie(
 					"recordAccount",request);
 			if (autoLogin != null && autoLogin.equals("ON")) {
 				user = Toolkit.getToolkit().getCookie("user", request);
 				password = Toolkit.getToolkit().getCookie("password",
 						request);
 			}
 			if (recordAccount != null ) {
 				user = Toolkit.getToolkit().getCookie("user", request);
 			}
 		}
 	}
	IniFile ini = new IniFile("general.ini");
	
	ini.load();
	String strCustomerTitle = "";
	String strCustomer = ini.getValue("license", "Customer");
	if(!strCustomer.isEmpty())
		strCustomerTitle = "Copyright©:Siteview Licesen to" + strCustomer + "'s Normal version" ;
	else
		strCustomerTitle = "Copyright©:SiteView Ecc 8.8 Test" ;
 	String sid = (String) session.getAttribute("usersessionid");
 	View view = null;
 	if (sid != null)
 		view = Manager.getView(sid);
 	if (view != null) {
 		System.out.println(" ---------- 用户 F5 刷新页面 ! ----------- ");
 		//按F5刷新时清空session中最近浏览监测器的信息
 		session.removeAttribute("recentlyViewMonitors");
 		//Toolkit.getToolkit().InvalidateEccMainPage(session, sid);
    %>
		<jsp:forward page="main/index.zul"/>
	<%
	} else if (user != null && password!= null) {
			try {
				Toolkit.getToolkit().cleanSession(session);
				if (Manager.IPCheck(request.getRemoteAddr().trim())) {
					long l = System.currentTimeMillis();
					String strSession = "";
					
					if ("NTLM".equalsIgnoreCase(BaseTools.getConfigString("auth.type"))) {
						strSession = Manager.createView_zhongZuBu(user);
					}else if ("LDAP".equalsIgnoreCase(BaseTools.getConfigString("auth.type"))) {
						strSession = Manager.createViewByLdap(user, password);
					}else if ("ECC".equalsIgnoreCase(BaseTools.getConfigString("auth.type"))) {
						strSession = Manager.createView(user, password);
					}
					
					System.out.println("To spend time: Manager.createView="
							+ (System.currentTimeMillis() - l));

					if (strSession == "") {
						loginMsg = "The user name or password is wrong!";
					} else {
						//EccSessionListener.removeZkSession(session);
						Toolkit.getToolkit().cleanSession(session);
						String localeValue = "zh_CN";
						localeValue = "en";
						//String localeValue = "ja";						
						Locale prefer_locale = localeValue.length() > 2 ? 
								new Locale(localeValue.substring(0,2),localeValue.substring(3)) : new Locale(localeValue);		
						session.setAttribute(Attributes.PREFERRED_LOCALE,prefer_locale);
						Locales.setThreadLocal(prefer_locale);						
						loginMsg = "success";
						session.setAttribute("usersessionid", strSession);
						LoginUserRight userRight = Toolkit.getToolkit()
								.getUserRight(session);
						userRight.setLoginIp(request.getRemoteAddr());
						if (request.getParameter("autoLogin") != null) {
							Toolkit.getToolkit().setCookie("autoLogin",
									"ON", response, Integer.MAX_VALUE);
							Toolkit.getToolkit().setCookie("user", user,
									response, Integer.MAX_VALUE);
							Toolkit.getToolkit().setCookie("password",
									password, response, Integer.MAX_VALUE);
						} else {
							Toolkit.getToolkit().setCookie("user", user,
									response, Integer.MAX_VALUE);
							Toolkit.getToolkit().setCookie("autoLogin",
									"OFF", response, -1);
						}
						if (request.getParameter("recordAccount") != null) {
							Toolkit.getToolkit().setCookie("recordAccount",
									"ON", response, Integer.MAX_VALUE);
							Toolkit.getToolkit().setCookie("user", user,
									response, Integer.MAX_VALUE);
						}
						
						
						AppendOperateLog.addOneLog(user, userRight
								.getUserName()
								+ "Login is successful!", OpTypeId.signin,
								OpObjectId.login);
	%>

					<jsp:forward page="main/index.zul"/>
					<%
						}
								} else {
									throw new Exception("Login anomaly:<br>"
											+ "The IP address of the machine is not in the scope allowed to login, login is terminated. ");
								}

							} catch (Exception e) {
								e.printStackTrace();
								loginMsg = e.getMessage();
							}
						}
						if (user == null)
							user = "admin";
						if (password == null)
							password = "";
					%>


<%@page import="com.siteview.ecc.system.DiagnosisUtils"%><html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8"> 
    <title>SITEVIEW ECC 8.8</title>
	<link rel="icon" href="favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
	<link type="text/css" href="Styles.css" rel="stylesheet"/>
</head>
<body scroll="no">
	<table cellspacing="0px" cellpadding="0px" class="panel">
		<tr>
			<td valign="middle" align="center">
				<table cellSpacing="0px" cellPadding="0px" style="background-image:url(images/login_bg.jpg); width:522px; height:328px;">
	                <tr>
                        <td valign="bottom" style="color:Red; font-size:14; font-weight:bold; padding-left:125px; padding-top:30px; padding-bottom:10px;">&nbsp;<%=loginMsg%>&nbsp;</td>
                    </tr>			
					<tr>
						<td valign="top" style="padding-left:125px;">
						<form method="post" id="login" name="login" action="/ecc/index.jsp">
							<table cellSpacing="0px" cellPadding="0px" class="widthauto">					
								<tr>
									<td style="color:#000000; font-weight:bold; line-height:25px; width:60px; white-space:nowrap;">Login Name：</td>
									<td ><input onFocus="this.select()" onkeypress="if(event.keyCode==13){document.getElementById('login').submit();}" style="border:1px solid #19598D; width:150px;" name="Account" value="<%=user%>"/>&nbsp;</td>
									<td class="hand" rowspan="2"><img onClick="document.getElementById('login').submit();" src="images/login_but_cn.png"/></td>
								</tr>
								<tr>
									<td style="color:#000000; font-weight:bold; line-height:22px; width:60px; white-space:nowrap;">Login Password：</td>
									<td ><input onFocus="this.select()" onkeypress="if(event.keyCode==13){document.getElementById('login').submit();}" style="border:1px solid #19598D; width:150px;" name="Password" type=password value="<%=password%>"/></td>
								</tr>
								<tr>
									<td ></td>
									<td ><input type="checkbox" name="autoLogin" value="ON">Atuo Login</td>
								</tr>
								<tr>
									<td ></td>
									<td ><input type="checkbox" name="recordAccount" value="ON">Remember Login Account Info</td>
								</tr>
							</table>
						</form>
						</td>
					</tr>	
			        <tr>
			            <td align="center" style="color:#000000; font-weight:bold; white-space:nowrap;">&nbsp;<%=strCustomerTitle%>&nbsp;</td>
			        </tr>		
				</table>
			</td>
		</tr>			
	</table>
</body>
</html>
