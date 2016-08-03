package picchetto

import time.Interval

class PersonReport {
	Person person
	Interval reportInterval
	
	String getName(){
		person?.name
	}
	
	def getWeekdays(){
		def days = periods*.weekdays.flatten()
		days.removeAll(PublicHoliday.all*.date)
		days
	}
	
	private def getPeriods(){
		reportInterval.intersect(person.periods*.interval)
	}
	
	def getHolidays(){
		periods*.filter(PublicHoliday.all*.date).flatten()
	}
	
	def getWeekendDays(){
		periods*.weekendDays.flatten()
	}
	
	double getTotal(){
		(weekdays.size()*25) + (weekendDays.size())*50
	}
}
