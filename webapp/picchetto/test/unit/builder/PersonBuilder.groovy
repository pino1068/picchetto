package builder

import picchetto.Person

class PersonBuilder {

	static Person aPerson(name){
		Person.findByName(name)?: new Person(name:name, email:"person@gmtech.ch").save()
	}
	static Person enrico(){aPerson("enrico")}
	static Person matteo(){aPerson("matteo")}
	
}
