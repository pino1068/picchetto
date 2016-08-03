package picchetto

import time.Interval

class PeriodsGenerator {
	Interval range
	
	def generate(){
		range.weeks.collect{ Interval interval ->
			if(! Period.existsIn(interval))
				new Period(interval:interval, person:Person.randomAtWorkIn(interval)).save()
		}
	}
}
