<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.user-account.list.label.username" path="username" width="20%"/>
	<acme:list-column code="any.user-account.list.label.name" path="identity.name" width="30%"/>
	<acme:list-column code="any.user-account.list.label.surname" path="identity.surname" width="30%"/>
	<acme:list-column code="any.user-account.list.label.roles" path="roleList" width="20%"/>		
</acme:list>