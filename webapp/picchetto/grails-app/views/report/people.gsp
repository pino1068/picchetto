<%@ page contentType="text/html"%>
<html>
<head>

<style>
@import url('http://fonts.googleapis.com/css?family=Amarante');

html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p,
	blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn,
	em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var,
	b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend,
	table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas,
	details, embed, figure, figcaption, footer, header, hgroup, menu, nav,
	output, ruby, section, summary, time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
	outline: none;
	-webkit-font-smoothing: antialiased;
	-webkit-text-size-adjust: 100%;
	-ms-text-size-adjust: 100%;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

html {
	overflow-y: scroll;
}

body {
	background: #eee url('http://i.imgur.com/eeQeRmk.png');
	/* http://subtlepatterns.com/weave/ */
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	font-size: 62.5%;
	line-height: 1;
	color: #585858;
	padding: 22px 10px;
	padding-bottom: 55px;
}

::selection {
	background: #5f74a0;
	color: #fff;
}

::-moz-selection {
	background: #5f74a0;
	color: #fff;
}

::-webkit-selection {
	background: #5f74a0;
	color: #fff;
}

br {
	display: block;
	line-height: 1.6em;
}

article, aside, details, figcaption, figure, footer, header, hgroup,
	menu, nav, section {
	display: block;
}

ol, ul {
	list-style: none;
}

input, textarea {
	-webkit-font-smoothing: antialiased;
	-webkit-text-size-adjust: 100%;
	-ms-text-size-adjust: 100%;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	outline: none;
}

blockquote, q {
	quotes: none;
}

blockquote:before, blockquote:after, q:before, q:after {
	content: '';
	content: none;
}

strong, b {
	font-weight: bold;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
}

img {
	border: 0;
	max-width: 100%;
}

h1 {
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	font-weight: bold;
	font-size: 3.6em;
	line-height: 1.7em;
	margin-bottom: 10px;
	text-align: center;
}

h2 {
	font-weight: bold;
	font-size: 1.6em;
	line-height: 1.7em;
	margin-bottom: 1px;
	text-align: center;
}

/** page structure **/
#wrapper {
	display: block;
	width: 850px;
	background: #fff;
	margin: 0 auto;
	padding: 10px 17px;
	-webkit-box-shadow: 2px 2px 3px -1px rgba(0, 0, 0, 0.35);
}

#actions {
	margin: 0px auto;
}

#keywords {
	margin: 0 auto;
	font-size: 1.2em;
	margin-bottom: 15px;
}

#keywords thead {
	cursor: pointer;
	background: #c9dff0;
}

#keywords thead tr th {
	font-weight: bold;
	padding: 12px 30px;
	padding-left: 42px;
}

#keywords thead tr th span {
	padding-right: 20px;
	background-repeat: no-repeat;
	background-position: 100% 100%;
}

#keywords thead tr th.headerSortUp, #keywords thead tr th.headerSortDown
	{
	background: #acc8dd;
}

#keywords thead tr th.headerSortUp span {
	background-image: url('http://i.imgur.com/SP99ZPJ.png');
}

#keywords thead tr th.headerSortDown span {
	background-image: url('http://i.imgur.com/RkA9MBo.png');
}

#keywords tbody tr {
	color: #555;
}

#keywords tbody tr td {
	text-align: center;
	padding: 15px 10px;
}

#keywords tbody tr td.lalign {
	text-align: left;
}
</style>

</head>

<body>
	<div id="wrapper">
		<h1>Report "picchetto"</h1>
		<h2>from:${params.from.date.month.from}</h2>
		<h2>to:${params.from.date.month.to}</h2>
		<h2>
<a href="mailto:?subject=look at this website&body=Hi,I found this website
and thought you might like it http://www.geocities.com/wowhtml/">tell a friend</a>
		</h2>
		<h2>download <a href="download">(Excel)</a></h2>
		<table id="keywords" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th>Person</th>
					<th>weekdays</th>
					<th>holidays</th>
					<th>weekend days</th>
					<g:if test="${params.total}">
						<th>total</th>
					</g:if>
				</tr>
			</thead>
			<tbody>
				<g:each in="${report.all}" var="person">
					<tr>
						<td class="lalign">
							${person.name }
						</td>
						<td>
							${person.weekdays.size() }
						</td>
						<td>
							${person.holidays.size() }
						</td>
						<td>
							${person.weekendDays.size() }
						</td>
						<g:if test="${params.total}">
						<td>
							<g:formatNumber number="${person.total}" type="currency" currencyCode="CHF" />
						</td>
						</g:if>
					</tr>
					<g:if test="${params.details}">
						<g:each in="${person.weekdays}" var="day">
							<tr>
								<td class="lalign">
								</td>
								<td>
									<g:formatDate format="dd.MM.yyyy" date="${day}"/>
								</td>
								<td>
								</td>
								<td>
								</td>
							</tr>
						</g:each>
						<g:each in="${person.holidays}" var="day">
							<tr>
								<td class="lalign">
								</td>
								<td>
								</td>
								<td>
									<g:formatDate format="dd.MM.yyyy" date="${day}"/>
								</td>
								<td>
								</td>
							</tr>
						</g:each>
						<g:each in="${person.weekendDays}" var="day">
							<tr>
								<td class="lalign">
								</td>
								<td>
								</td>
								<td>
								</td>
								<td>
									<g:formatDate format="dd.MM.yyyy" date="${day}"/>
								</td>
							</tr>
						</g:each>
					</g:if>
				</g:each>
			</tbody>
		</table>
		<div style="text-align:right;">
		<a href="http://localhost:8080/picchetto/report?from=1.7.2016">simple</a><br/>
		<a href="http://localhost:8080/picchetto/report?from=1.7.2016&details=true">with details</a><br/>
		<a href="http://localhost:8080/picchetto/report?from=1.7.2016&total=true">with total</a><br/>
		<a href="http://localhost:8080/picchetto/report?from=1.7.2016&details=true&total=true">with details &amp; totals</a><br/>
		</div>
	</div>
</body>
</html>