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

<acme:form>
	<acme:input-textbox code="chef.recipe.form.label.heading" path="heading"/>	
	<acme:input-textbox code="chef.recipe.form.label.code" path="code" placeholder="AA:AAA-000 || AAA-000"/>
	<acme:input-textarea code="chef.recipe.form.label.description" path="description"/>
	<acme:input-textarea code="chef.recipe.form.label.preparation-notes" path="preparationNotes"/>
	<acme:input-url code="chef.recipe.form.label.link" path="link"/>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.recipe.form.button.create" action="/chef/recipe/create" />
		</jstl:when>
		<jstl:otherwise>
			<jstl:if test="${isPublished==false}">
				<acme:submit code="chef.recipe.form.button.update" action="/chef/recipe/update" />
				<acme:submit code="chef.recipe.form.button.delete" action="/chef/recipe/delete" />
				<acme:submit code="chef.recipe.form.button.publish" action="/chef/recipe/publish" />
			</jstl:if>
			<acme:button code="chef.recipe.form.button.partOf" action="/chef/part-of/list-artifacts?masterId=${id}"/>
		</jstl:otherwise>
	</jstl:choose>
	
</acme:form>