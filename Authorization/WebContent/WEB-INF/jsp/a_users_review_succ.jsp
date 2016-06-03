<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>User review successed</title>
<fmt:setLocale value="${sessionScope.command}"/>
<fmt:setBundle basename="resources.locale" var="loc" />
<fmt:message bundle="${loc}" key="local.content.a_user_status" var="a_user_status"/>
<fmt:message bundle="${loc}" key="local.content.a_user_status_succ" var="a_user_status_succ"/>
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
				<input type="hidden" name="command" value="a_show_declaration" />
		        <input id="cont_button" type="submit" value="${a_show_declaration}" />
	        </form>
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="a_new_declarations_review" />
		        <input id="cont_button" type="submit" value="${a_new_declarations_review}" />
	         </form>
			 <form action="Controller" method="post">
				<input type="hidden" name="command" value="a_registration" />
		        <input id="cont_button" type="submit" value="${a_registration}" />
	         </form>
          </div>
          <div id="content">
		    <h1>${a_user_status}<c:out value="${sessionScope.not_examined_user.organizationName}"/> ${a_user_status_succ}</h1>
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
</div>
</body>
</html>