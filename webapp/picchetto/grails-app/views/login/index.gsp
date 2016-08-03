<%@ page import="picchetto.Person" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Search periods</title>
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js">
		</script>
	</head>
<body>
		<div id="show-person" class="content scaffold-show" role="main">
<h1>Login as  ...</h1>
<div style="margin:80px;">
	<ul>
		<g:each in="${Person.all}" var="p">
			<li>
				<g:link controller="login" action="loginAs" id="${p.id}">${p.name}</g:link>
			</li>
		</g:each>
	</ul>
</div>
		</div>

</body>
</html>