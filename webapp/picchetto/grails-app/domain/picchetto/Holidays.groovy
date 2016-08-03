package picchetto

import time.Interval

class Holidays {
	Date fromDate, toDate
	
	void setInterval(Interval interval){
		fromDate = interval.from
		toDate = interval.to
	}
	
	Interval getInterval(){
		new Interval(from:fromDate, to:toDate)
	}
	
	static belongsTo = [person: Person]
	
	static transients = ["interval"]
}
