<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="mytag" uri="LogoTag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List of users</title>
<fmt:setLocale value="${sessionScope.command}"/>
<fmt:setBundle basename="resources.locale" var="loc" />
<fmt:message bundle="${loc}" key="local.leftcol.menu" var="menu"/>
<fmt:message bundle="${loc}" key="local.topbar.usual" var="head"/>
<fmt:message bundle="${loc}" key="local.rightcol.organization_name" var="organization_name"/>
<fmt:message bundle="${loc}" key="local.rightcol.role" var="role"/>
<fmt:message bundle="${loc}" key="local.rightcol.user_log_out" var="user_log_out"/>
<fmt:message bundle="${loc}" key="local.content.all_users_message" var="all_users_message"/>
<fmt:message bundle="${loc}" key="local.content.all_users_colomn_header1" var="all_users_colomn_header1"/>
<fmt:message bundle="${loc}" key="local.content.all_users_colomn_header2" var="all_users_colomn_header2"/>
<fmt:message bundle="${loc}" key="local.content.all_users_colomn_header3" var="all_users_colomn_header3"/>
<fmt:message bundle="${loc}" key="local.content.all_users_colomn_header4" var="all_users_colomn_header4"/>
<fmt:message bundle="${loc}" key="local.content.all_users_colomn_header5" var="all_users_colomn_header5"/>
<fmt:message bundle="${loc}" key="local.content.all_users_colomn_header6" var="all_users_colomn_header6"/>
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
		    <table id="decl_table">
			      <caption><h1>${all_users_message}</h1></caption>
				  <thead>
				    <tr>
                      <th>${all_users_colomn_header1}</th>
                      <th>${all_users_colomn_header2}</th>
					  <th>${all_users_colomn_header3}</th>
					  <th>${all_users_colomn_header4}</th>
					  <th>${all_users_colomn_header5}</th>
					  <th>${all_users_colomn_header6}</th>
                    </tr>
				  </thead>
				  <tbody>
					<jsp:useBean id="user_list" class="java.util.ArrayList" scope="request"/>
					<c:forEach items="${user_list}" var="user">
					<tr>
					  <td>${user.name}</td>
					  <td>${user.password}</td>
					  <td>${user.role}</td>
					  <td>${user.UTN}</td>
					  <td>${user.organizationName}</td>
					  <td>${user.mail}</td>
					</tr>
					</c:forEach>
				  </tbody>
				  <tfoot>
				  </tfoot>
			    </table>
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