package picchetto
import grails.converters.*

class PeriodController {
	static scaffold = Period
	
	def calendar(){
		println params
		render(contentType: "application/json") {
			JSON.parse('''{
	"success": 1,
	"result": [
		{
			"id": "291",
			"title": "Enrico Mazzi",
			"url": "http://www.example.com/",
			"class": "event-warning",
			"start": "1468195200000",
			"end":   "1468713600000"
		},
		{
			"id": "200",
			"title": "Pino",
			"url": "http://www.example.com/",
			"class": "event-warning",
			"start": "1468195200000",
			"end":   "1468713600000"
		},
		{
			"id": "293",
			"title": "Matteo Besutti",
			"url": ".",
			"class": "event-info",
			"start": "1468800000000",
			"end":   "1469318400000"
		}
	]
}''')
		}
	}
}
