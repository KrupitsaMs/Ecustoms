<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Add good to changing declaration</title>
<fmt:setLocale value="${sessionScope.command}"/>
<fmt:setBundle basename="resources.locale" var="loc" />
<fmt:message bundle="${loc}" key="local.leftcol.menu" var="menu"/>
<fmt:message bundle="${loc}" key="local.topbar.usual" var="head"/>
<fmt:message bundle="${loc}" key="local.content.registration_head" var="registration_head"/>
<fmt:message bundle="${loc}" key="local.content.decl_confirmation" var="decl_confirmation"/>
<fmt:message bundle="${loc}" key="local.leftcol.export_declaration" var="export_declaration"/>
<fmt:message bundle="${loc}" key="local.leftcol.user_change_declaration" var="user_change_declaration"/>
<fmt:message bundle="${loc}" key="local.leftcol.user_check_declaration" var="user_check_declaration"/>
<fmt:message bundle="${loc}" key="local.leftcol.user_new_declaration" var="user_new_declaration"/>
<fmt:message bundle="${loc}" key="local.content.new_goods_message" var="new_goods_message"/>
<fmt:message bundle="${loc}" key="local.rightcol.user_log_out" var="user_log_out"/>
<fmt:message bundle="${loc}" key="local.rightcol.organization_name" var="organization_name"/>
<fmt:message bundle="${loc}" key="local.rightcol.role" var="role"/>
<fmt:message bundle="${loc}" key="local.content.submit_declaration" var="submit_declaration"/>
<fmt:message bundle="${loc}" key="local.content.goods_colomn_header1" var="goods_colomn_header1"/>
<fmt:message bundle="${loc}" key="local.content.goods_colomn_header2" var="goods_colomn_header2"/>
<fmt:message bundle="${loc}" key="local.content.goods_colomn_header3" var="goods_colomn_header3"/>
<fmt:message bundle="${loc}" key="local.content.goods_colomn_header4" var="goods_colomn_header4"/>
<fmt:message bundle="${loc}" key="local.content.goods_colomn_header5" var="goods_colomn_header5"/>
<fmt:message bundle="${loc}" key="local.content.goods_colomn_header6" var="goods_colomn_header6"/>
<fmt:message bundle="${loc}" key="local.content.change_new_good" var="change_new_good"/>
<fmt:message bundle="${loc}" key="local.leftcol.show_all_declarations" var="show_all_declarations"/>
<fmt:message bundle="${loc}" key="local.content.submit_changing_declaration" var="submit_changing_declaration"/>

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
		  <form action="Controller" method="post">
	          <input type="hidden" name="command" value="change_new_good" />
				<table>
			      <caption><h2>${new_goods_message}</h2></caption>
				    <tr>
					  <th>${goods_colomn_header6}</th>
                      <th>${goods_colomn_header1}</th>
                      <th>${goods_colomn_header2}</th>
					  <th>${goods_colomn_header3}</th>
					  <th>${goods_colomn_header4}</th>
					  <th>${goods_colomn_header5}</th>
                    </tr>
					<tr>
					  <td><input id="good" type="text" name="good_number" value=""/></td>
					  <td><input id="good" type="text" name="tar_code" value=""/></td>
					  <td><input id="good" type="text" name="good" value=""/></td>
					  <td><input id="good" type="text" name="value" value=""/></td>
					  <td><input id="good" type="text" name="currency" value=""/></td>
					  <td><input id="good" type="text" name="origin" value=""/></td>
					</tr>
			    </table>
				<input id="button" type="submit" value="${change_new_good}"/>
		  </form>
		  <br/>
		  <form action="Controller" method="post">
		    <input type="hidden" name="command" value="submit_changing_declaration" />
		    <input id="button" type="submit" value="${submit_changing_declaration}"/>
		  </form>
		  </div>
          <div id="rightcol">
			<h3 id="user_inf">${organization_name} <c:out value="${sessionScope.login.organizationName}"/></h3>
		    <h3 id="user_inf">${role} <c:out value="${sessionScope.login.role}"/></h3><br />
            <p id="digital_watch"></p>
		    <p id="current_date"></p>
			<br/>
			<form action="Controller" method="post">
			  <input type="hidden" name="command" value="log_out"/>
		      <input id="button" type="submit" value="${user_log_out}" />
			</form>
		  </div>
        </div>
    </div>
  </div>
 </div>
</div>
</body>