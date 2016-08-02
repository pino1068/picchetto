package picchetto

import time.Interval

class PeriodsGenerator {
	Interval range
	
	def generate(){
		range.weeks.collect{
			new Period(interval:it, person:Person.random1()).save()
		}
	}
}
