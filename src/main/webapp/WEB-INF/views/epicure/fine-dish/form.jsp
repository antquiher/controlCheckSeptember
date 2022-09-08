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

<jstl:choose>
<jstl:when test="${isNew == true}">	
		<acme:form>
			<acme:input-textbox code="epicure.fine-dish.form.label.code" path="code"/>	
			<acme:input-textbox code="epicure.fine-dish.form.label.request" path="request"/>	
			<acme:input-money code="epicure.fine-dish.form.label.budget" path="budget"/>
			<acme:input-moment code="epicure.fine-dish.form.label.initialDate" path="initialDate"/>
			<acme:input-moment code="epicure.fine-dish.form.label.finishDate" path="finishDate"/>
			<acme:input-textbox code="epicure.fine-dish.form.label.url" path="url"/>
			<acme:input-select code="epicure.fine-dish.form.label.select.chef" path="chefs">
				<jstl:forEach items="${chefs}" var="chef">
					<acme:input-option code="${chef.getUserAccount().getUsername()}" value="${chef.getId()}" />
				</jstl:forEach>
			</acme:input-select>
		
		<acme:submit code="epicure.fine-dish.form.button.create" action="/epicure/fine-dish/create"/>
		



</acme:form>

</jstl:when>


<jstl:otherwise>	
<acme:form >
	<acme:input-textbox readonly="true" code="epicure.fine-dish.form.label.status" path="status"/>	
	<acme:input-textbox code="epicure.fine-dish.form.label.code" path="code"/>	
	<acme:input-textarea code="epicure.fine-dish.form.label.request" path="request"/>
	<acme:input-money code="epicure.fine-dish.form.label.budget" path="budget"/>
	<jstl:if test="${command == 'show'}">
	<acme:input-money code="any.artifact.form.label.change" path="change" readonly="true"/>
	</jstl:if>
	<acme:input-moment code="epicure.fine-dish.form.label.initialDate" path="initialDate"/>
	<acme:input-moment code="epicure.fine-dish.form.label.finishDate" path="finishDate"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.url" path="url"/>
	<acme:button code="epicure.fine-dish.form.label.chef" action="/any/user-account/show?id=${chefId}"/>

<jstl:if test="${isPublish == false}">
<acme:submit code="epicure.fine-dish.form.button.update" action="/epicure/fine-dish/update"/>
<acme:submit code="epicure.fine-dish.form.button.delete" action="/epicure/fine-dish/delete"/>
<acme:submit code="epicure.fine-dish.form.button.publish" action="/epicure/fine-dish/publish" />
</jstl:if>
</acme:form>
	</jstl:otherwise>
</jstl:choose>
