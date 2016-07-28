package picchetto

class ReportController {
	static defaultAction = "people"
	
	
	def people(){
		def interval = params.to?params.from.date.until(params.to):params.from.date.month
		def report = new PeopleReport(period:interval)
		
		render(view:"people", model: [report: report])
	}

}
