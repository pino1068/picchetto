package picchetto

import groovy.transform.ToString

import org.hibernate.criterion.CriteriaSpecification

import time.Interval

@ToString
class Period {
	Date fromDate = new Date(), toDate=fromDate
	String status = "assigned"
	Person person
	Double price
	
	void setInterval(Interval range){
		fromDate = range.from
		toDate = range.to.endOfDay()
	}
	
	Interval getInterval(){
		new Interval(from:fromDate, to:toDate)
	}
	
	String createLink(prefix){
		prefix+"/period/search?id=$id"
	}
	
	boolean existsIn(intervals){
		interval.intersectsAny(intervals)
	}
	
	static boolean existsInPersistence(interval){
		interval.intersectsAny(Period.all*.interval)
	}
	
	static def findAllByParams(params){
		if(params.id && !params.id.empty)
			return [Period.get(params.id)]
		Period.createCriteria().listDistinct {
			List people = Person.findAllByNameIlike("%$params.person%")
			if(params.person)
				'in'("person", people)
			if(params.from && !params.from.empty)
				gte("fromDate", params.from.date)
			if(params.to && !params.to.empty)
				lte("toDate", params.to.date.endOfDay())
			if(params.status && !params.status.empty)
				ilike("status", "%$params.status%")
		}
	}
	
	static transients = [ "interval"]
	
	static constraints = {
		id()
		person()
		fromDate(nullable:true,format:"dd.MM.yyyy")
		toDate(nullable:true,format:"dd.MM.yyyy")
		status(inList: ["assigned", "on-market"])
		price(nullable:true)
	} 
}
