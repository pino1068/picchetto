
<%@ page import="picchetto.PublicHoliday" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'publicHoliday.label', default: 'PublicHoliday')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-publicHoliday" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-publicHoliday" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		<div style="margin:20px;">
		<form action="${createLink(action: 'upload')}" method="post" id="uploadForm">
			Content: 
			<textarea name=content form="uploadForm"  placeholder="paste public holidays here..."></textarea>
			<input type="submit">
		</form>
		</div>

		<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="date" title="${message(code: 'publicHoliday.date.label', default: 'Date')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'publicHoliday.description.label', default: 'Description')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${publicHolidayInstanceList}" status="i" var="publicHolidayInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${publicHolidayInstance.id}">
							<g:formatDate format="dd.MM.yyyy" date="${publicHolidayInstance.date}"/>
						</g:link></td>
					
						<td>${fieldValue(bean: publicHolidayInstance, field: "description")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${publicHolidayInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
