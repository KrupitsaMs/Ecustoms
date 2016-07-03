<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="mytag" uri="LogoTag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Changing declaration not founed</title>
<fmt:setLocale value="${sessionScope.command}"/>
<fmt:setBundle basename="resources.locale" var="loc" />
<fmt:message bundle="${loc}" key="local.leftcol.user_change_declaration" var="user_change_declaration"/>
<fmt:message bundle="${loc}" key="local.leftcol.user_check_declaration" var="user_check_declaration"/>
<fmt:message bundle="${loc}" key="local.leftcol.menu" var="menu"/>
<fmt:message bundle="${loc}" key="local.topbar.usual" var="head"/>
<fmt:message bundle="${loc}" key="local.leftcol.user_new_declaration" var="user_new_declaration"/>
<fmt:message bundle="${loc}" key="local.rightcol.organization_name" var="organization_name"/>
<fmt:message bundle="${loc}" key="local.rightcol.role" var="role"/>
<fmt:message bundle="${loc}" key="local.rightcol.user_log_out" var="user_log_out"/>
<fmt:message bundle="${loc}" key="local.leftcol.show_all_declarations" var="show_all_declarations"/>
<fmt:message bundle="${loc}" key="local.content.choosed_declaration_failed" var="choosed_declaration_failed"/>
<fmt:message bundle="${loc}" key="local.leftcol.show_all_declarations" var="show_all_declarations"/>

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
				<input type="hidden" name="command" value="user_page" />
				<input type="hidden" name="button" value="declaration_check" />
		        <input id="cont_button" type="submit" value="${user_check_declaration}" />
	        </form>
			<form action="Controller" method="post">
			    <input type="hidden" name="command" value="user_page" />
				<input type="hidden" name="button" value="user_change_declaration" />
		        <input id="cont_button" type="submit" value="${user_change_declaration}" />
	        </form>
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="user_new_declaration" />
		        <input id="cont_button" type="submit" value="${user_new_declaration}" />
	         </form>
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="show_all_declarations" />
		        <input id="cont_button" type="submit" value="${show_all_declarations}" />
	        </form>
          </div>
          <div id="content">
			<h3>${choosed_declaration_failed}</h3>
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
</html>