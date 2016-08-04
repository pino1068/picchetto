
<%@ page import="picchetto.Period" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Search periods</title>
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js">
		</script>
	</head>
<body>
<div ng-app="myApp" ng-controller="myperiodsCtrl">
	<div style="text-align:left;margin:0 0 50px 50px;">
		<g:link action="search">all assignments</g:link>
	</div>
	<div style="height:400px; overflow:auto;">
	<table>
		<thead>
			<tr>
				<th colspan=2>Assigned to me</th>
				<th>Found: {{periods.length}} periods
					<span ng-show="searching"><asset:image src="spinner.gif" alt="searching"/></span>
				</th>
			</tr>
			<tr>
				<th>From Date <br/><input type="text" name="from" ng-model="from" ng-change="refresh()" ng-model-options='{ debounce: 1000 }'></th>
				<th>To Date <br/><input type="text" name="to" ng-model="to" ng-change="refresh()" ng-model-options='{ debounce: 1000 }'></th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="period in periods">
				<td>{{period.fromDate | date:'dd.MM.yyyy'}}</td>
				<td>{{period.toDate | date:'dd.MM.yyyy'}}</td>
				<td>{{period.status}}</td>
			</tr>
		</tbody>
	</table>
	</div>
</div>

<script>
var app = angular.module('myApp', []);
app.controller('myperiodsCtrl', function($scope, $http) {
	$scope.to="";
	$scope.from="";
	$scope.refresh = function() {
		$scope.searching=true;
	    $http.get("list?person="+$scope.person+"&from="+$scope.from+"&to="+$scope.to).then(function (response) {
	    	$scope.periods = response.data;
	    	angular.forEach($scope.periods, function(period) {
	            $http.get("../person/json/"+period.person.id).then(function (response) {
	            	period.person.name = response.data.name;
	            });
	    	});
			$scope.searching=false;
	    });
      };
  	$scope.person='${session.user.name}';
	$scope.searching=false;
	$scope.refresh();
});
</script>

</body>
</html>