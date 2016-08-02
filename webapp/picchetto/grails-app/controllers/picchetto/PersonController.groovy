package picchetto

import grails.converters.JSON

class PersonController {
	static scaffold = Person
    
    def json(Person p) {
		render p as JSON
    }
}
