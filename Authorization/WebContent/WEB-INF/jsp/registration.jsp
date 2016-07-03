<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="mytag" uri="LogoTag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Registration</title>
<fmt:setLocale value="${sessionScope.command}"/>
<fmt:setBundle basename="resources.locale" var="loc" />
<fmt:message bundle="${loc}" key="local.authorization.message" var="author_message"/>
<fmt:message bundle="${loc}" key="local.leftcol.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.leftcol.about" var="about"/>
<fmt:message bundle="${loc}" key="local.leftcol.menu" var="menu"/>
<fmt:message bundle="${loc}" key="local.topbar.usual" var="head"/>
<fmt:message bundle="${loc}" key="local.content.about" var="about_Ecust"/>
<fmt:message bundle="${loc}" key="local.content.ex_declaration_check_head" var="ex_declaration_check_head"/>
<fmt:message bundle="${loc}" key="local.content.ex_declaration_check_button" var="ex_declaration_check_button"/>
<fmt:message bundle="${loc}" key="local.content.registration_head" var="registration_head"/>
<fmt:message bundle="${loc}" key="local.registration.login.message" var="login_message"/>
<fmt:message bundle="${loc}" key="local.registration.password.message" var="password_message"/>
<fmt:message bundle="${loc}" key="local.regbutton.name.registration" var="regitsr_button"/>
<fmt:message bundle="${loc}" key="local.leftcol.export_declaration" var="export_declaration"/>
<fmt:message bundle="${loc}" key="local.registration.utn_message" var="utn_message"/>
<fmt:message bundle="${loc}" key="local.registration.org_name_message" var="org_name_message"/>
<fmt:message bundle="${loc}" key="local.registration.addres_message" var="addres_message"/>
<fmt:message bundle="${loc}" key="local.registration.mail_message" var="mail_message"/>
<fmt:message bundle="${loc}" key="local.registration.login_condition" var="login_condition"/>
<fmt:message bundle="${loc}" key="local.registration.utn_condition" var="utn_condition"/>
<fmt:message bundle="${loc}" key="local.registration.org_name_condition" var="org_name_condition"/>
<fmt:message bundle="${loc}" key="local.registration.addres_condition" var="addres_condition"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}\Resources\css\registration.css">
</head>
<body>
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
			<form action="About" >
		        <input id="cont_button" type="submit" value="${about}" />
	        </form>
			<form action="index.jsp" >
		        <input id="cont_button" type="submit" value="${author_message}" />
	        </form>
			<form action="EX_Check">
		        <input id="cont_button" type="submit" value="${export_declaration}" />
	        </form>
          </div>
          <div id="content">
		    <h1>${registration_head}</h1>
			<form action="Controller" method="post">
		      <input type="hidden" name="command" value="registration" /> 
		      ${login_message} <br /> 
		      <input type="text" name="newLogin" value="" /> <br />
		      ${password_message} <br /> 
		      <input type="password" name="newPassword" value="" /><br />
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
	        </form>
		  </div>
          <div id="rightcol">
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
</html>