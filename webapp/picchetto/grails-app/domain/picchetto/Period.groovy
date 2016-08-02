package picchetto

import groovy.transform.ToString

import org.hibernate.criterion.CriteriaSpecification

import time.Interval

@ToString
class Period {
	Date fromDate = new Date(), toDate=fromDate
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
	
	static def findAllByParams(params){
		Period.createCriteria().listDistinct {
			List people = Person.findAllByNameIlike("%$params.person%")
			if(params.person)
				'in'("person", people)
			if(params.from && !params.from.empty)
				gte("fromDate", params.from.date)
			if(params.to && !params.to.empty)
				lte("toDate", params.to.date)
		}
	}
	
	static transients = [ "interval"]
	
	static constraints = {
		id()
		person()
		fromDate(nullable:true,format:"dd.MM.yyyy")
		toDate(nullable:true,format:"dd.MM.yyyy")
		status(inList: ["open", "assigned", "on-market"])
		price(nullable:true)
	} 
}
