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
	<acme:input-textbox code="authenticated.epicure.form.label.organisation" path="organisation"/>
	<acme:input-textbox code="authenticated.epicure.form.label.assertion" path="assertion"/>
	<acme:input-url code="authenticated.epicure.form.label.url" path="url"/>
	
	<acme:submit test="${command == 'create'}" code="authenticated.epicure.form.button.create" action="/authenticated/epicure/create"/>
	<acme:submit test="${command == 'update'}" code="authenticated.epicure.form.button.update" action="/authenticated/epicure/update"/>
</acme:form>