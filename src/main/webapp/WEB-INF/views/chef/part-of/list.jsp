<%--
- list.jsp
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

<acme:list>
	<acme:list-column code="any.partOf.list.label.quantity" path="quantity" width="20%"/>
	<acme:list-column code="any.partOf.list.label.unit" path="unit" width="20%"/>
	<jstl:choose>
		<jstl:when test="${cameFromArtifact == true}">
			<acme:list-column code="any.partOf.list.label.recipe.heading" path="recipe.heading" width="60%"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:list-column code="any.partOf.list.label.artifact.name" path="artifact.name" width="60%"/>
		</jstl:otherwise>
	</jstl:choose>
	
</acme:list>
<jstl:if test="${cameFromArtifact == false}">
	<jstl:if test="${publish == false}"></jstl:if>
	<acme:button code="chef.partOf.list.button.create.ingredient" action="/chef/part-of/add-ingredient?masterId=${artifactId}"/>
	<acme:button code="chef.partOf.list.button.create.utensil" action="/chef/part-of/add-utensil?masterId=${artifactId}"/>
</jstl:if>
