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
	<acme:input-textbox code="chef.pimpam.form.label.title" path="title" />
	<acme:input-textbox code="chef.pimpam.form.label.seis-numeros" path="auxiliarSeisNumeros" placeholder="123456"/>
	<acme:input-textarea code="chef.pimpam.form.label.description" path="description" />
	<acme:input-moment code="chef.pimpam.form.label.startPeriod" path="startPeriod" />
	<acme:input-moment code="chef.pimpam.form.label.finishPeriod" path="finishPeriod" />
	<acme:input-money code="chef.pimpam.form.label.budget" path="budget" />
	<acme:input-textbox code="chef.pimpam.form.label.link" path="link" />
	<acme:input-select code="chef.pimpam.form.label.select.artifact" path="artifacts">
				<jstl:forEach items="${artifacts}" var="artifact">
					<acme:input-option code="${artifact.getCode()}" value="${artifact.getId()}" />
				</jstl:forEach>
			</acme:input-select>
	
	
	<acme:submit code="chef.pimpam.form.button.create" action="/chef/delor/create"/>
</acme:form>



</jstl:when>


<jstl:otherwise>	
<acme:form >
	<acme:input-textbox code="chef.pimpam.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="chef.pimpam.form.label.instantiationMoment" path="instantiationMoment" readonly="true"/>
	<acme:input-textbox code="chef.pimpam.form.label.title" path="title" />
	<acme:input-textbox code="chef.pimpam.form.label.description" path="description" />
	<acme:input-textbox code="chef.pimpam.form.label.startPeriod" path="startPeriod" />
	<acme:input-textbox code="chef.pimpam.form.label.finishPeriod" path="finishPeriod" />
	<acme:input-money code="chef.pimpam.form.label.budget" path="budget" />
	<acme:input-textbox code="chef.pimpam.form.label.link" path="link" />
	<acme:button code="chef.pimpam.form.label.artifact" action="/any/artifact/show?id=${artifactId}" />

<jstl:if test="${artifactPublish == false}">
<acme:submit code="chef.pimpam.form.button.update" action="/chef/delor/update"/>
<acme:submit code="chef.pimpam.form.button.delete" action="/chef/delor/delete"/>
</jstl:if>

</acme:form>
	</jstl:otherwise>
</jstl:choose>