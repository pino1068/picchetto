package picchetto

import time.Interval

class PeriodsGenerator {
	Interval range
	
	def generate(){
		range.weeks.collect{
			if(! Period.existsByInterval(it))
				new Period(interval:it, person:Person.random()).save()
		}
	}
}
