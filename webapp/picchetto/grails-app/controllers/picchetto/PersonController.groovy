package picchetto

import grails.converters.JSON

class PersonController {
	static scaffold = Person
    
    def json(Person p) {
		render p as JSON
    }
	
	def holidays(Person p) {
		render p._holidays as JSON
	}
	
	def addHolidays(Person p){
		def interval = params.from.until(params.to)
		def holiday = new Holidays(fromDate: interval.from, toDate: interval.to, person: p).save()
		redirect(action:"show", id:p.id)
	}
}
