
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

<div ng-app="myApp" ng-controller="customersCtrl">
<div style="text-align:right;margin:5px;">
<label>Year:</label>
<input type="text" name="year" ng-model="year">
<a href="generate?year={{year}}">Generate</a>
</div>
<table>
<thead>
		<tr>
			<th>Found: {{periods.length}} periods
				<span ng-show="searching"><asset:image src="spinner.gif" alt="searching"/></span>
			</th>
			<th colspan=2>2016 2017 2018</th>
			<th></th>
		</tr>
		<tr>
			<th>Person <br/><input type="text" name="person" ng-model="person" ng-change="refresh()" ng-model-options='{ debounce: 300 }'></th>
			<th>From Date <br/><input type="text" name="from" ng-model="from" ng-change="refresh()" ng-model-options='{ debounce: 1000 }'></th>
			<th>To Date <br/><input type="text" name="to" ng-model="to" ng-change="refresh()" ng-model-options='{ debounce: 1000 }'></th>
			<th>Status</th>
		</tr>
	</thead>
	<tbody>
		<tr ng-repeat="period in periods">
			<td>{{period.person.name}}</td>
			<td>{{period.fromDate | date:'dd.MM.yyyy'}}</td>
			<td>{{period.toDate | date:'dd.MM.yyyy'}}</td>
			<td>{{period.status}}</td>
		</tr>
	</tbody>
</table>
</div>

<script>
var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
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
	$scope.person="";
	$scope.searching=false;
	$scope.refresh();
});
</script>

</body>
</html>