<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Start Page</title>
<fmt:setLocale value="${sessionScope.command}"/>
<fmt:setBundle basename="by.epam.authorization.localization.locale" var="loc" />
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
<style>
.wrapper{
  position: relative;
  overflow: auto;
  float:center;
}
.conteiner{
  position:relative;
  overflow:auto;
 }
.top{
  text-align: left;
  font-size: 40pt;
  border-bottom-width: 5px;
  border-bottom-style: solid;
  border-bottom-color: lightblue;
  margin-bottom: 20px;
  background-color: grey;
  color: #ffffff;
  float:center;
  border-radius: 4px;
  margin: 4px;
}
.menubar {
   background-color: grey;
   color: white;
   padding: 10px;
   width: 180px;
   float: left;
   text-align: center;
   border-radius: 4px;
   margin: 4px;
}
.main{
   padding: 10px;
   float:center;
   border-radius: 4px;
   margin: 4px;
}
.rightBar{
    background-color: grey;
    color: white;
    padding: 10px;
    width: 130px;
    float: right;
    border-radius: 4px;
  	margin: 4px;
  	text-align: center;
}
.ru{
    float: left;
    width: 65px;
}
.en{
float: left;;
width: 65px;
}

</style>
</head>
<body>
<div class="container">
<div class="top">
<b>${main_message}</b>
</div>
<div class="wrapper">
<div class="menubar">
<b>${author_message}</b>
<form action="Controller" method="get">
	<input type="hidden" name="command" value="login" /> 
	${login_message} <br /> 
	<input type="text" name="login" value=""/> <br />
	${password_message}<br /> 
	<input type="password" name="password" value="" /><br />
	<input type="submit" value="${login_button}" />
</form>
<b>${registr1_message} <a href="registration.jsp"> ${registr2_message}</a></b>
</div>
<div class="rightBar">
<b>${locmenu}</b>
	<div>
	<form class="ru" action="Controller" method="post">
		<input type="hidden" name="command" value="ru" /> 
		<input type="submit" value="${ru_button}"/>
	</form>
	<form class="en" action="Controller" method="get">
		<input type="hidden" name="command" value="en" /> 
		<input type="submit" value="${en_button}"/>
	</form>
	</div>
</div>
<div class="main"></div>
</div>
</div>
</body>
</html>