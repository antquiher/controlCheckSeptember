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
	<jstl:when test="${command == 'add-ingredient'}">
		<acme:form>
			<acme:input-double code="any.partOf.form.label.quantity" path="quantity"/>	
			<acme:input-textbox code="any.partOf.form.label.unit" path="unit"/>
			<acme:input-select code="chef.partof.form.label.artifact" path="artifact">
				<jstl:forEach items="${artifacts}" var="art">
					<acme:input-option code="${art.code}" value="${art.id}"  />
				</jstl:forEach>
			</acme:input-select>
			<acme:submit code="chef.partOf.form.button.create" action="/chef/part-of/add-ingredient?masterId=${masterId}"/>
		</acme:form>
	</jstl:when>
	<jstl:when test="${command == 'add-utensil'}">
		<acme:form>
			<acme:input-select code="chef.partof.form.label.artifact" path="artifact">
				<jstl:forEach items="${artifacts}" var="art">
					<acme:input-option code="${art.code}" value="${art.id}"  />
				</jstl:forEach>
			</acme:input-select>
			<acme:submit code="chef.partOf.form.button.create" action="/chef/part-of/add-utensil?masterId=${masterId}"/>
		</acme:form>
	</jstl:when>
	<jstl:otherwise>
		<acme:form readonly="true">
			<acme:input-textbox code="any.partOf.form.label.artifact.name" path="artifact.name"/>
			<acme:input-textbox code="any.partOf.form.label.artifact.code" path="artifact.code"/>
			<acme:input-textarea code="any.partOf.form.label.artifact.description" path="artifact.description"/>
			<acme:input-money code="any.partOf.form.label.artifact.retailPrice" path="artifact.retailPrice"/>
			<acme:input-textbox code="any.partOf.form.label.artifact.link" path="artifact.link"/>
			<acme:input-textbox code="any.partOf.form.label.artifact.type" path="artifact.type"/>
			<jstl:if test="${published==false}">
				<acme:submit code="chef.partOf.form.button.delete" action="/chef/part-of/delete"/>
			</jstl:if>
		</acme:form>
		</jstl:otherwise>
	</jstl:choose>
	
