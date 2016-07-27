import picchetto.MetaProgram
import picchetto.Person

class BootStrap {

    def init = { servletContext ->
		def people = ["matteo","pino","enrico"]
		people.each{ person ->
			new Person(name:person).save()
		}
		
		MetaProgram.enrich()
		
    }
    def destroy = {
    }
}
