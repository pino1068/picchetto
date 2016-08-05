
<%@ page import="picchetto.Period" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Search periods</title>
		<style type="text/css">
		th a {
    /* display: block; */
    text-decoration: none;
    float: right;
}
		</style>
	</head>
<body>

<div ng-controller="searchCtrl">
	<div style="text-align:right;margin:5px;">
		<g:link action='people' controller='report' params="[from:new Date().firstDayOfMonth.simpleFormat]" target="_blank">Report current month</g:link>
	</div>
	<div style="text-align:right;margin:5px;">
		<label>Year:</label>
		<input type="text" name="year" ng-model="year">
		<a href="generate?year={{year}}">Generate</a>
	</div>
	<div style="text-align:right;margin:5px;">
		<label>From:</label>
		<input type="text" name="from" ng-model="from">
		<label>To:</label>
		<input type="text" name="to" ng-model="to">
		<a href="generate?from={{from}}&amp;to={{to}}">Generate</a>
	</div>
	<div style="height:400px; overflow:auto;">
	<table>
		<thead>
			<tr>
				<th>Search
					<span ng-show="searching"><asset:image src="spinner.gif" alt="searching"/></span>
					 <a href="#" ng-click="clear()">clear</a>
				</th>
				<th></th>
				<th></th>
				<th>{{periods.length}} periods found</th>
				<th></th>
			</tr>
			<tr>
				<th>Person <a href="#" ng-click="mineOnly()">mine</a>
				<br/><input type="text" name="person" ng-model="person" ng-change="refresh()" ng-model-options='{ debounce: 300 }'>
				</th>
				<th>From Date <br/><input type="text" name="from" ng-model="from" ng-change="refresh()" ng-model-options='{ debounce: 1000 }'>
				</th>
				<th>To Date <br/><input type="text" name="to" ng-model="to" ng-change="refresh()" ng-model-options='{ debounce: 1000 }'></th>
				<th>Status<br/><input type="text" name="status" ng-model="status" ng-change="refresh()" ng-model-options='{ debounce: 1000 }'></th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="period in periods">
				<td>{{period.person.name}}</td>
				<td>{{period.fromDate | date:'dd.MM.yyyy'}}</td>
				<td>{{period.toDate | date:'dd.MM.yyyy'}}</td>
				<td>{{period.status}}</td>
				<td>
					<button ng-click="sell(period.id)" ng-show="period.status=='assigned' && period.person.id==userId">sell</button>
					<button ng-click="buy(period.id)" ng-show="period.status=='on-market'">buy</button>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
</div>

<script>
app.controller('searchCtrl', function($scope, $http) {
  	$scope.userId='${session.user.id}';
  	$scope.userName='${session.user.name}';
	$scope.clear = function() {
		$scope.to="";
		$scope.from="";
		$scope.status="";
		$scope.person="";
		$scope.refresh();
	};
	$scope.refresh = function() {
		$scope.searching=true;
	    $http.get("list?person="+$scope.person+"&from="+$scope.from+"&to="+$scope.to+"&status="+$scope.status).then(function (response) {
	    	$scope.periods = response.data;
	    	angular.forEach($scope.periods, function(period) {
	            $http.get("../person/json/"+period.person.id).then(function (response) {
	            	period.person.name = response.data.name;
	            });
	    	});
			$scope.searching=false;
	    });
      };
  	$scope.mineOnly = function(id) {
  		$scope.person = $scope.userName;
  		$scope.refresh();
      };
  	$scope.sell = function(id) {
	    $http.post("sell/"+id).then(function (response) {
			$scope.refresh();
	    });
      };
	$scope.buy = function(id) {
	    $http.post("buy/"+id).then(function () {
			$scope.refresh();
	    });
      };
	$scope.person="";
	$scope.clear();
});
</script>

</body>
</html>