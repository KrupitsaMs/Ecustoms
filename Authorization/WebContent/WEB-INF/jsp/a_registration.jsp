<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="mytag" uri="LogoTag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>New User Registration</title>
<fmt:setLocale value="${sessionScope.command}"/>
<fmt:setBundle basename="resources.locale" var="loc" />
<fmt:message bundle="${loc}" key="local.content.a_declaration_status" var="a_declaration_status"/>
<fmt:message bundle="${loc}" key="local.content.a_user_status_succ" var="a_declaration_status_succ"/>
<fmt:message bundle="${loc}" key="local.leftcol.menu" var="menu"/>
<fmt:message bundle="${loc}" key="local.topbar.usual" var="head"/>
<fmt:message bundle="${loc}" key="local.rightcol.organization_name" var="organization_name"/>
<fmt:message bundle="${loc}" key="local.rightcol.role" var="role"/>
<fmt:message bundle="${loc}" key="local.rightcol.user_log_out" var="user_log_out"/>
<fmt:message bundle="${loc}" key="local.leftcol.a_show_declaration" var="a_show_declaration"/>
<fmt:message bundle="${loc}" key="local.leftcol.a_show_all_users" var="a_show_all_users"/>
<fmt:message bundle="${loc}" key="local.leftcol.a_user_review" var="a_user_review"/>
<fmt:message bundle="${loc}" key="local.leftcol.a_new_declarations_review" var="a_new_declarations_review"/>
<fmt:message bundle="${loc}" key="local.leftcol.a_registration" var="a_registration"/>
<fmt:message bundle="${loc}" key="local.registration.login.message" var="login_message"/>
<fmt:message bundle="${loc}" key="local.registration.password.message" var="password_message"/>
<fmt:message bundle="${loc}" key="local.registration.role_message" var="role_message"/>
<fmt:message bundle="${loc}" key="local.registration.utn_message" var="utn_message"/>
<fmt:message bundle="${loc}" key="local.registration.org_name_message" var="org_name_message"/>
<fmt:message bundle="${loc}" key="local.registration.addres_message" var="addres_message"/>
<fmt:message bundle="${loc}" key="local.registration.mail_message" var="mail_message"/>
<fmt:message bundle="${loc}" key="local.registration.login_condition" var="login_condition"/>
<fmt:message bundle="${loc}" key="local.registration.utn_condition" var="utn_condition"/>
<fmt:message bundle="${loc}" key="local.registration.org_name_condition" var="org_name_condition"/>
<fmt:message bundle="${loc}" key="local.registration.addres_condition" var="addres_condition"/>
<fmt:message bundle="${loc}" key="local.registration.role_condition" var="role_condition"/>
<fmt:message bundle="${loc}" key="local.regbutton.name.registration" var="regitsr_button"/>
<fmt:message bundle="${loc}" key="local.content.registration_head" var="registration_head"/>


<link rel="stylesheet" href="${pageContext.request.contextPath}\Resources\css\registration.css">
<script type="text/javascript" src="${pageContext.request.contextPath}\Resources\js\timedate.js"></script>
</head>
<body onload="digitalWatch()">
<div class="container">
 <div class="topbar">
 <b>${head}</b>
 </div>
 <div id="wrapper">
  <div id="container3">
    <div id="container2">
        <div id="container1">
          <div id="leftcol">
            <h1>${menu}</h1>
			 <form action="Controller" method="post">
				<input type="hidden" name="command" value="a_show_all_users" />
		        <input id="cont_button" type="submit" value="${a_show_all_users}" />
	        </form>
			<form action="Controller" method="post">
			    <input type="hidden" name="command" value="a_user_review" />
		        <input id="cont_button" type="submit" value="${a_user_review}" />
	        </form>
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="admin_page" />
				<input type="hidden" name="button" value="a_show_declaration" />
		        <input id="cont_button" type="submit" value="${a_show_declaration}" />
	        </form>
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="a_new_declarations_review" />
		        <input id="cont_button" type="submit" value="${a_new_declarations_review}" />
	         </form>
			 <form action="Controller" method="post">
				<input type="hidden" name="command" value="admin_page" />
				<input type="hidden" name="button" value="a_registration" />
		        <input id="cont_button" type="submit" value="${a_registration}" />
	         </form>
          </div>
          <div id="content">
		    <h1>${registration_head}</h1>
			<form action="Controller" method="post">
		      <input type="hidden" name="command" value="a_registration" /> 
		      ${login_message} <br /> 
		      <input type="text" name="newLogin" value="" /> <br />
		      ${password_message} <br /> 
		      <input type="password" name="newPassword" value="" /><br />
			  ${role_message} <br />
			  <input type="text" name="newRole" value="" /><br />
			  ${utn_message} <br />
			  <input type="text" name="newUTN" value="" /><br />
			  ${org_name_message} <br />
			  <input type="text" name="newOrgName" value="" /><br />
			  ${addres_message} <br />
			  <input type="text" name="newAddres" value="" /><br />
			  ${mail_message} <br />
			  <input type="text" name="newMail" value="" /><br />
		      <input id="button" type="submit" value="${regitsr_button}" /> <br />
			  ${login_condition}<br />
			  ${utn_condition}<br />
			  ${org_name_condition}<br />
			  ${addres_condition}<br />
			  ${role_condition}<br />
	        </form>
		  </div>
          <div id="rightcol">
		    <h3 id="user_inf">${organization_name} <c:out value="${sessionScope.login.organizationName}"/></h3>
		    <h3 id="user_inf">${role} <c:out value="${sessionScope.login.role}"/></h3><br />
            <p id="digital_watch"></p>
		    <p id="current_date"></p>
			<br>
			<form action="Controller" method="post">
			  <input type="hidden" name="command" value="log_out"/>
		      <input id="cont_button" type="submit" value="${user_log_out}" />
			</form>
		  </div>
        </div>
    </div>
  </div>
 </div>
 <div id = "footer">
   <h2><mytag:img url="Resources\img\wcologo.gif" showBorder="true"/> E-cust Service. All rights reserved 2016</h2>
 </div>
</div>
</body>