<%@ page import="picchetto.Notification" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="application.css"/>
		<asset:javascript src="application.js"/>
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
		<script type="text/javascript">
		var app = angular.module('myApp', []);
		app.controller('notificationsView', function($scope, $http) {
			$scope.notifications = false;
		});
		</script>
		<g:layoutHead/>
	</head>
	<body ng-app="myApp" >
		<div id="mfgroupLogo" role="banner">
			<a href="${createLink(uri: '/')}"><asset:image src="mf-logo.png" alt="MF Group"/></a>
			<div  style="float: right; margin: 30px;">
				<g:if test="${!session.user}">
					<g:link controller="login" action="index">login</g:link>
				</g:if>
				<g:if test="${session.user}">
					Logged in as ${session.user?.name } - 
					<g:link controller="login" action="logout">logout</g:link>
				<div ng-controller="notificationsView">
					<g:if test="${Notification.findAllByTarget(session.user).size() > 0}">
							you have 
							<a href="#" ng-model="notifications"
							ng-init="notifications = false"
							ng-click="notifications=!notifications">news</a> 
						<div class="modal-content" ng-show="notifications">
							<div class="modal-header">
								<span class="close" ng-click="notifications=!notifications">×</span>
								<h2>Notifications</h2>
							</div>
							<div class="modal-body">
								<ul>
									<g:each in="${Notification.findAllByTarget(session.user).sort{it.dateCreated}}" var="n">
										<li class="blue">
											<small style="float: right"><g:formatDate date="${n.dateCreated}" format="dd.MM.yyyy hh.mm" /></small>
											<a>${n.message}</a>
										</li>
									</g:each>
								</ul>
							</div>
							<div class="modal-footer">
								<button ng-show="notifications"
									ng-click="notifications=!notifications">close</button>
							</div>
						</div>
					</g:if>
				</div>
			</g:if>
			</div>
		</div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
	</body>
</html>
