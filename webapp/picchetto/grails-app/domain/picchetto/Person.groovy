package picchetto

import groovy.transform.ToString
import time.Interval

@ToString
class Person {
	String name
	
	static hasMany = [holidays: Holidays]
	
	def getPeriods(){
		Period.findAllByPerson(this)
	}
	
	boolean hasHolidaysIn(Interval interval){
		interval.intersectsAny(holidays*.interval)
//		holidays*.interval.findAll {!interval.intersect(it).empty}.size() > 0
	}
	
	static Person randomAtWorkIn(Interval interval){
		def lucky = random()
		while(lucky?.hasHolidaysIn(interval))
			lucky =  random()
		lucky
	}
	
	static Person random(){
		Person.get(new Random().nextInt(Person.count())+1)
	}
	
	static transients = ["holidaysInterval"]
	
    static constraints = {
    }
	
	static mapping = {
	}
}
