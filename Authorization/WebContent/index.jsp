<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="mytag" uri="WEB-INF/tlds/LogoTag.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Start Page</title>
<fmt:setLocale value="${sessionScope.command}"/>
<fmt:setBundle basename="resources.locale" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>
<fmt:message bundle="${loc}" key="local.logbutton.name.login" var="login_button"/>
<fmt:message bundle="${loc}" key="local.locmenu.message" var="locmenu"/>
<fmt:message bundle="${loc}" key="local.main.message" var="main_message"/>
<fmt:message bundle="${loc}" key="local.authorization.message" var="author_message"/>
<fmt:message bundle="${loc}" key="local.login.message" var="login_message"/>
<fmt:message bundle="${loc}" key="local.password.message" var="password_message"/>
<fmt:message bundle="${loc}" key="local.registration.message1" var="registr1_message"/>
<fmt:message bundle="${loc}" key="local.registration.message2" var="registr2_message"/>
<fmt:message bundle="${loc}" key="local.leftcol.about" var="about"/>
<fmt:message bundle="${loc}" key="local.leftcol.export_declaration" var="export_declaration"/>
<fmt:message bundle="${loc}" key="local.leftcol.menu" var="menu"/>
<fmt:message bundle="${loc}" key="local.topbar" var="head"/>
<link rel="stylesheet" href="Resources\css\index.css">
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
			 <form action="EX_Check">
		        <input id="cont_button" type="submit" value="${export_declaration}" />
	         </form>
          </div>
          <div id="content">
		    <h1>${author_message}</h1> <br /> 
            <form action="Controller" method="post">
	          <input type="hidden" name="command" value="login" /> 
	          ${login_message} <br /> 
	          <input type="text" name="login" value=""/> <br />
	          ${password_message}<br /> 
	          <input type="password" name="password" value="" /><br />
	          <input id="button" type="submit" value="${login_button}" />
            </form>
			<b>${registr1_message} <a href="Registration"> ${registr2_message}</a></b>
		  </div>
          <div id="rightcol">
		      <h1>${locmenu}</h1>
	          <div>
	            <form class="ru" action="Controller" method="post">
		          <input type="hidden" name="command" value="ru" /> 
		          <input id="ru" type="submit" value="${ru_button}"/>
	            </form>
	            <form class="en" action="Controller" method="post">
		          <input type="hidden" name="command" value="en" /> 
		          <input id="button" type="submit" value="${en_button}"/>
	            </form>
	          </div>
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