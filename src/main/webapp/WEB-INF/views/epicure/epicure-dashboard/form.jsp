<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ page import="org.springframework.data.util.Pair"%>

<style>
 th, td {
 	padding: 10px 40px;
 	border-bottom: 1px solid #333;
 }
 
 h2 {
 	margin-bottom: 10px;
 }
 
table {
 	margin-bottom: 40px;
 }
</style>

<h2>
	<acme:message code="epicure.dashboard.title.FineDish" />
</h2>
<table>
<caption>Fine Dish Statuses</caption>
	<tr>
		<th id="Blank"></th>
		<jstl:forEach var="status" items="${fineDishStatuses}">
			<th id="Blank">${status}</th>
		</jstl:forEach>
	</tr>
	<tr>
		<td><acme:message
				code="epicure.dashboard.total" /></td>
		<jstl:forEach var="status" items="${fineDishStatuses}">
			<td>${totalFineDish.get(status)}</td>
		</jstl:forEach>
	</tr>
	<tr>
		<td><acme:message
				code="epicure.dashboard.averageFineDishBudget" /></td>
		<jstl:forEach var="status" items="${fineDishStatuses}">
			<td>${averageFineDishBudget.get(status)}</td>
		</jstl:forEach>
	</tr>
	<tr>
		<td><acme:message
				code="epicure.dashboard.deviationFineDishBudget" /></td>
		<jstl:forEach var="status" items="${fineDishStatuses}">
			<td>${deviationFineDishBudget.get(status)}</td>
		</jstl:forEach>
	</tr>
	<tr>
		<td><acme:message
				code="epicure.dashboard.maximumFineDishBudget" /></td>
		<jstl:forEach var="status" items="${fineDishStatuses}">
			<td>${maximumFineDishBudget.get(status)}</td>
		</jstl:forEach>
	</tr>
	<tr>
		<td><acme:message
				code="epicure.dashboard.minimumFineDishBudget" /></td>
		<jstl:forEach var="status" items="${fineDishStatuses}">
			<td>${minimumFineDishBudget.get(status)}</td>
		</jstl:forEach>
	</tr>
</table>