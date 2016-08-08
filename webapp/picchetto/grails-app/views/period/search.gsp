
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
<div>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
	<div style="text-align:right;margin:5px;">
		<g:link action='people' controller='report' params="[from:new Date().firstDayOfMonth.simpleFormat]" target="_blank">Report current month</g:link>
	</div>
	<g:if test="${session.user.admin}">
		<div style="text-align:right;margin:5px;" ng-controller="generateCtrl">
			<label>Year:</label>
			<input type="text" name="year" ng-model="year">
			<a href="generate?year={{year}}">Generate</a>
		</div>
		<div style="text-align:right;margin:5px;" ng-controller="generateCtrl">
			<label>From:</label>
			<input type="text" name="from" ng-model="from">
			<label>To:</label>
			<input type="text" name="to" ng-model="to">
			<a href="generate?from={{from}}&amp;to={{to}}">Generate</a>
		</div>
	</g:if>
	<div style="height:400px; overflow:auto;">
	<table  ng-controller="searchCtrl">
		<thead>
			<tr>
				<th colspan="2">Search
					<span ng-show="searching"><asset:image src="spinner.gif" alt="searching"/></span>
					 <a href="#" ng-click="clearAndRefresh()">clear</a>
				</th>
				<th></th>
				<th></th>
				<th>{{periods.length}} periods found</th>
				<th></th>
			</tr>
			<tr>
				<th>ID</th>
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
				<td><a href="" ng-click="select(period.id)">{{period.id}}</a></td>
				<td><a href="" ng-click="byPerson(period.person.name)">{{period.person.name}}</a> </td>
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
app.controller('generateCtrl', function($scope, $http) {});

app.controller('searchCtrl', function($scope, $http) {
  	$scope.userId='${session.user.id}';
  	$scope.userName='${session.user.name}';
	$scope.clear = function() {
		$scope.id="";
		$scope.to="";
		$scope.from="";
		$scope.status="";
		$scope.person="";
	};
	$scope.clearAndRefresh = function() {
		$scope.clear();
		$scope.refresh();
	};
	$scope.refresh = function() {
		$scope.searching=true;
	    $http.get("list?id="+$scope.id+"&person="+$scope.person+"&from="+$scope.from+"&to="+$scope.to+"&status="+$scope.status).then(function (response) {
	    	$scope.periods = response.data;
	    	angular.forEach($scope.periods, function(period) {
	            $http.get("../person/json/"+period.person.id).then(function (response) {
	            	period.person.name = response.data.name;
	            });
	    	});
			$scope.searching=false;
	    });
      };
  	$scope.select = function(id) {
  		$scope.clear();
  		$scope.id = id;
  		$scope.refresh();
      };
  	$scope.mineOnly = function() {
  		$scope.byPerson($scope.userName)
      };
  	$scope.byPerson = function(person) {
  		$scope.clear();
  		$scope.person = person;
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
  	$scope.init = function() {
	  	$scope.id="${params.id?:""}";
		$scope.to="${params.to?:""}";
		$scope.from="${params.from?:""}";
		$scope.status="";
		$scope.person="${params.person?:""}";
		$scope.refresh();
  	};
  	$scope.init();
});
</script>

</body>
</html>