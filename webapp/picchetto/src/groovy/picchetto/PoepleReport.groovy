package picchetto

import time.Interval

class PoepleReport {
	Interval period
	
	def getAll(){
		Person.all.findAll{it.periods.size() > 0}
			.collect{new PersonReport(person:it, reportInterval:period)}
	}
}
