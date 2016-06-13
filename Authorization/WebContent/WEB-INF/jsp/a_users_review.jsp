<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="mytag" uri="LogoTag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>User review</title>
<fmt:setLocale value="${sessionScope.command}"/>
<fmt:setBundle basename="resources.locale" var="loc" />
<fmt:message bundle="${loc}" key="local.user_page.message" var="user_message"/>
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
<fmt:message bundle="${loc}" key="local.content.account_data" var="account_data"/>
<fmt:message bundle="${loc}" key="local.content.account_data_colomn_header1" var="account_data_colomn_header1"/>
<fmt:message bundle="${loc}" key="local.content.account_data_colomn_header2" var="account_data_colomn_header2"/>
<fmt:message bundle="${loc}" key="local.content.account_data_colomn_header3" var="account_data_colomn_header3"/>
<fmt:message bundle="${loc}" key="local.content.company_data" var="company_data"/>
<fmt:message bundle="${loc}" key="local.content.company_data_colomn_header1" var="company_data_colomn_header1"/>
<fmt:message bundle="${loc}" key="local.content.company_data_colomn_header2" var="company_data_colomn_header2"/>
<fmt:message bundle="${loc}" key="local.content.company_data_colomn_header3" var="company_data_colomn_header3"/>
<fmt:message bundle="${loc}" key="local.content.company_data_colomn_header4" var="company_data_colomn_header4"/>
<fmt:message bundle="${loc}" key="local.content.not_yet_examined_user" var="not_yet_examined_user"/>
<fmt:message bundle="${loc}" key="local.content.a_user_confirm" var="a_user_confirm"/>
<fmt:message bundle="${loc}" key="local.content.a_user_decline" var="a_user_decline"/>

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
		    <jsp:useBean id="not_examined_user" class="by.epam.authorization.entity.User" type="java.lang.Object" scope="session"/>
			<table id="decl_table">
			      <caption><h1>${account_data}</h1></caption>
				  <thead>
				    <tr>
                      <th>${account_data_colomn_header1}</th>
                      <th>${account_data_colomn_header2}</th>
					  <th>${account_data_colomn_header3}</th>
                    </tr>
				  </thead>
				  <tbody>
					<tr>
					  <td><jsp:getProperty property="name" name = "not_examined_user"/></td>
					  <td><jsp:getProperty property="password" name = "not_examined_user"/></td>
					  <td><jsp:getProperty property="role" name = "not_examined_user"/></td>
					</tr>
				  </tbody>
				  <tfoot>
				  </tfoot>
			</table>
		    <table id="decl_table">
			      <caption><h1>${company_data}</h1></caption>
				  <thead>
				    <tr>
                      <th>${company_data_colomn_header1}</th>
                      <th>${company_data_colomn_header2}</th>
					  <th>${company_data_colomn_header3}</th>
					  <th>${company_data_colomn_header4}</th>
                    </tr>
				  </thead>
				  <tbody>
					<tr>
					  <td><jsp:getProperty property="organizationName" name = "not_examined_user"/></td>
					  <td><jsp:getProperty property="UTN" name = "not_examined_user"/></td>
					  <td><jsp:getProperty property="address" name = "not_examined_user"/></td>
					  <td><jsp:getProperty property="mail" name = "not_examined_user"/></td>
					</tr>
				  </tbody>
				  <tfoot>
				  </tfoot>
			</table>
			<br/>
			<form action="Controller" method="post">
			  <input type="hidden" name="command" value="a_user_confirm"/>
		      <input id="button" type="submit" value="${a_user_confirm}" />
			</form>
			<br/>
			<form action="Controller" method="post">
			  <input type="hidden" name="command" value="a_user_decline"/>
		      <input id="button" type="submit" value="${a_user_decline}" />
			</form>
			<br/>
			<h1>${not_yet_examined_user}<c:out value="${sessionScope.list_size}"/></h1>
			<br/>
		  </div>
          <div id="rightcol">
		    <h3 id="user_inf">${organization_name} <c:out value="${sessionScope.login.organizationName}"/></h3>
		    <h3 id="user_inf">${role} <c:out value="${sessionScope.login.role}"/></h3><br />
            <p id="digital_watch"></p>
		    <p id="current_date"></p>
			<br/>
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