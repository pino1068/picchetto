import picchetto.MetaProgram
import picchetto.Person

class BootStrap {

    def init = { servletContext ->
		def people = [
			"Alessandro Misenta",
			"Dario Zivko",
			"Enrico Mangano",
			"Enrico Mazzi",
			"Gabriele Izzo",
			"Gennaro Errico",
			"Giuseppe Di Pierri",
			"Manlio Modugno",
			"Manlio Vaccalluzzo",
			"Massimiliano Pepe",
			"Matteo Besutti",
			"Mattia Cattaneo",
			"Stefano Coluccia",
			"Valentino Decarli",
			]
		if(Person.count()==0)
			people.each{ person ->
				new Person(name:person).save()
			}
		
		MetaProgram.enrich()
		
    }
    def destroy = {
    }
}
