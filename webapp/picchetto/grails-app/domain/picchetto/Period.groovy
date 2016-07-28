package picchetto

import groovy.transform.ToString
import time.Interval

@ToString
class Period {
	Date fromDate, toDate
	String status = "open"
	Person person
	Double price
	
	void setInterval(Interval range){
		fromDate = range.from
		toDate = range.to
	}
	
	Interval getInterval(){
		new Interval(from:fromDate, to:toDate)
	}
	
	static constraints = {
		id()
		person()
		fromDate(nullable:true,format:"dd.MM.yyyy")
		toDate(nullable:true,format:"dd.MM.yyyy")
		status(inList: ["open", "assigned", "on-market"])
		price(nullable:true)
	} 
}
