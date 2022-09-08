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

<jstl:if test="${isIngredient == null}">
	<jstl:set var="isIngredient" value="false"/>
</jstl:if>

<acme:form>
	<acme:input-textbox code="any.artifact.form.label.name" path="name"/>	
	<acme:input-textbox code="any.artifact.form.label.code" placeholder="AAA-000" path="code"/>
	<acme:input-textbox code="any.artifact.form.label.description" path="description"/>
	<acme:input-textbox code="any.artifact.form.label.retail-price" path="retailPrice"/>
	<jstl:if test="${command == 'show'}">
	<acme:input-money code="any.artifact.form.label.change" path="change" readonly="true"/>
	</jstl:if>
	<acme:input-textbox code="any.artifact.form.label.link" path="link"/>
	<jstl:choose>
		<jstl:when test="${command == 'create-ingredient' || command == 'create-utensil'}">
			<jstl:choose>
				<jstl:when test="${isIngredient}">
					<acme:submit code="chef.artifact.form.button.create"
						action="/chef/artifact/create-ingredient" />
				</jstl:when>
				<jstl:otherwise>
					<acme:submit code="chef.artifact.form.button.create"
					action="/chef/artifact/create-utensil" />
				</jstl:otherwise>
			</jstl:choose>
		</jstl:when>
		<jstl:otherwise>
			<jstl:if test="${!isPublished}">
				<acme:submit code="chef.artifact.form.button.update"
					action="/chef/artifact/update" />
				<acme:submit code="chef.artifact.form.button.delete"
					action="/chef/artifact/delete" />
				<acme:submit code="chef.artifact.form.button.publish"
					action="/chef/artifact/publish" />
			</jstl:if>
		</jstl:otherwise>
	</jstl:choose>
</acme:form>