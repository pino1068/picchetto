package builder

import picchetto.Person

class PersonBuilder {

	static def aPerson(name){
		Person.findByName(name)?: new Person(name:name, email:"person@gmtech.ch").save()
	}
	static def enrico(){aPerson("enrico")}
	static def matteo(){aPerson("matteo")}
	
}
