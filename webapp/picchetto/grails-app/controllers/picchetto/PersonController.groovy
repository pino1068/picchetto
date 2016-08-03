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
		println interval
		def holiday = new Holidays(fromDate: interval.from, toDate: interval.to, person: p).save()
//		p.addToHolidays(holiday).save()
//		p.addHolidays(interval)
		println Person.get(p.id)
		redirect(action:"show", id:p.id)
	}
}
