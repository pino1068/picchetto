package picchetto

import groovy.transform.ToString
import time.Interval

@ToString
class Person {
	String name, email
	boolean admin
	
	static hasMany = [holidays: Holidays]
	
	def getFirstName(){
		name.split(" ")[0]
	}
	
	def getPeriods(){
		Period.findAllByPerson(this)
	}
	
	boolean hasHolidaysIn(Interval interval){
		interval.intersectsAny(holidays*.interval)
	}
	
	Person makeAdmin(){
		admin = true
		save()
	}
	
	def getNotifications(){
		Notification.findAllByTarget(this)
	}
	
	static Person randomAtWorkIn(Interval interval){
		if(Person.count == 0)
			throw new IllegalStateException("empty people")
		def availablePeople = Person.all.findAll{!it.hasHolidaysIn(interval)}
		if(availablePeople.empty)
			throw new IllegalStateException("no available people")
		circular(availablePeople)?:random()
	}
	
	static Person circular(people){
		def lastPersonName = Period.last()?.person?.name
		def person = people.sort{it.name}.find{it.name > lastPersonName}
		person?:people.first()
	}
	
	static Person random(){
		Person.get(new Random().nextInt(Person.count())+1)
	}
	
	static transients = ["holidaysInterval"]
	
    static constraints = {
		name()
		email(email:true)
    }
	
	static mapping = {
	}
}
