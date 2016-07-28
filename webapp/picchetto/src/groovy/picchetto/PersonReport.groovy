package picchetto

import time.Interval

class PersonReport {
	Person person
	Interval reportInterval
	
	String getName(){
		person?.name
	}
	
	def getWeekdays(){
		reportInterval.intersect(person.periods*.interval)*.weekdays.flatten()
	}
	
	def getHolidays(){
		[]
	}
	
	def getWeekendDays(){
		reportInterval.intersect(person.periods*.interval)*.weekendDays.flatten()
	}
	
	double getTotal(){
		(weekdays.size()*25) + (weekendDays.size())*50
	}
}
