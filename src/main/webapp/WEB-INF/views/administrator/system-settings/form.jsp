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

<acme:form>
	<acme:input-double code="administrator.systemSettings.form.label.spamThreshold" path="spamThreshold" />
	<acme:input-textbox code="administrator.systemSettings.form.label.defaultCurrency" path="defaultCurrency" />
	<acme:input-textbox code="administrator.systemSettings.form.label.acceptedCurrencies" path="acceptedCurrencies" />
	<acme:input-textbox code="administrator.systemSettings.form.label.spamTuples" path="spamTuples"/>

	<acme:submit code="administrator.systemSettings.form.button.update" action="/administrator/system-settings/update"/>
	
</acme:form>