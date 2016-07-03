<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="mytag" uri="LogoTag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Export declaration confirmation</title>
<fmt:setLocale value="${sessionScope.command}"/>
<fmt:setBundle basename="resources.locale" var="loc" />
<fmt:message bundle="${loc}" key="local.authorization.message" var="author_message"/>
<fmt:message bundle="${loc}" key="local.leftcol.about" var="about"/>
<fmt:message bundle="${loc}" key="local.leftcol.menu" var="menu"/>
<fmt:message bundle="${loc}" key="local.topbar.usual" var="head"/>
<fmt:message bundle="${loc}" key="local.content.registration_head" var="registration_head"/>
<fmt:message bundle="${loc}" key="local.content.ex_decl_rej" var="ex_decl_rej"/>
<fmt:message bundle="${loc}" key="local.leftcol.export_declaration" var="export_declaration"/>
<fmt:message bundle="${loc}" key="local.leftcol.registration" var="registration"/>

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
		  <jsp:useBean id="declaration" class="by.epam.authorization.entity.Declaration" type="java.lang.Object" scope="request"/>
		    <h1> Declaration Number - <jsp:getProperty property="number" name = "declaration"/></h1>
		    <h1> Status: ${ex_decl_rej}</h1>
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