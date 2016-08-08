package picchetto

import time.Interval

class PeriodsGenerator {
	Interval range
	
	def generate(){
		range.weeks.collect{ Interval interval ->
			if(! Period.existsInPersistence(interval)){
				def period = new Period(interval:interval, person:Person.randomAtWorkIn(interval)).save()
			}
		}
	}
}
