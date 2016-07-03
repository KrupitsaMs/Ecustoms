<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="mytag" uri="LogoTag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Logging application</title>
<fmt:setLocale value="${sessionScope.command}"/>
<fmt:setBundle basename="resources.locale" var="loc" />
<fmt:message bundle="${loc}" key="local.authorization.message" var="author_message"/>
<fmt:message bundle="${loc}" key="local.leftcol.about" var="about"/>
<fmt:message bundle="${loc}" key="local.leftcol.menu" var="menu"/>
<fmt:message bundle="${loc}" key="local.leftcol.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.topbar.usual" var="head"/>
<fmt:message bundle="${loc}" key="local.content.registration_application" var="registration_application"/>
<fmt:message bundle="${loc}" key="local.leftcol.export_declaration" var="export_declaration"/>
<fmt:message bundle="${loc}" key="local.content.logging_error" var="logging_error"/>
<fmt:message bundle="${loc}" key="local.content.logging1_error_message" var="logging1_error_message"/>
<fmt:message bundle="${loc}" key="local.content.logging2_error_message" var="logging2_error_message"/>
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
			<form action="Registration" >
		        <input id="cont_button" type="submit" value="${registration}" />
	         </form>
			 <form action="EX_Check">
		        <input id="cont_button" type="submit" value="${export_declaration}" />
	        </form>
          </div>
          <div id="content">
		    <h1>${logging_error}</h1>
			<b><a href="index.jsp"> ${logging1_error_message}</a></b><br />
			<b><a href="Account_recovery"> ${logging2_error_message}</a></b><br />
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