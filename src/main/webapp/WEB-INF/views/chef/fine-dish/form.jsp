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
<%@ page import="acme.entities.fineDish.StatusType" %>

		
		<acme:form>
			<acme:input-textbox code="chef.fine-dish.form.label.status" path="status" readonly="true"/>	
			<acme:input-textbox code="chef.fine-dish.form.label.code" path="code" readonly="true"/>	
			<acme:input-textbox code="chef.fine-dish.form.label.request" path="request" readonly="true"/>	
			<acme:input-textbox code="chef.fine-dish.form.label.budget" path="budget" readonly="true"/>
			<jstl:if test="${command == 'show'}">
			<acme:input-money code="any.artifact.form.label.change" path="change" readonly="true"/>
			</jstl:if>
			<acme:input-textbox code="chef.fine-dish.form.label.initialDate" path="initialDate" readonly="true"/>
			<acme:input-textbox code="chef.fine-dish.form.label.finishDate" path="finishDate" readonly="true"/>
			<acme:input-textbox code="chef.fine-dish.form.label.url" path="url" readonly="true"/>
			<acme:button code="chef.fine-dish.form.label.epicure" action="/any/user-account/show?id=${epicureId}"/>
			
			
		<jstl:if test="${status == StatusType.PROPOSED && isPublish == true}">
			<acme:submit code="chef.fine-dish.form.button.updateStatus.accept" action="/chef/fine-dish/update-status?status=${StatusType.ACCEPTED}"/>
			<acme:submit code="chef.fine-dish.form.button.updateStatus.deny" action="/chef/fine-dish/update-status?status=${StatusType.DENIED}"/>
		</jstl:if>
			
		</acme:form>
		
		



