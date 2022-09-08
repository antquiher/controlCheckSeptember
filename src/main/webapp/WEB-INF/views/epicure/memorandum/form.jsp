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
<jstl:when test="${isNew}">
<acme:form>
	<acme:input-textbox code="epicure.memorandum.form.label.report" path="report"/>
	<acme:input-textbox code="epicure.memorandum.form.label.link" path="link"/>
	
	<acme:input-select code="epicure.memorandum.form.label.fine-dish" path="finedishes">
		<jstl:forEach items="${finedishes}" var="fd">
			<acme:input-option code="${fd.code}" value="${fd.code}"
				selected="${ fd.code == fine-dish.code }" />
		</jstl:forEach>
	</acme:input-select>	
	
	<acme:input-checkbox code="epicure.memorandum.confirmation" path="confirmation"/> 
	<acme:submit code="epicure.memorandum.form.button.create" action="/epicure/memorandum/create"/>
</acme:form>
</jstl:when>

<jstl:otherwise>
<acme:input-textbox code="authenticated.epicure.form.label.report" path="report"/>
</jstl:otherwise>
</jstl:choose>
