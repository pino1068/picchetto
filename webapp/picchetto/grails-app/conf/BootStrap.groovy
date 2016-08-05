import picchetto.MetaProgram
import picchetto.Person

class BootStrap {

    def init = { servletContext ->
		def people = [
			"Alessandro Misenta":"alessandro.misenta@gmtech.ch",
			"Dario Zivko":"dario.zvinko@gmtech.ch",
			"Enrico Mangano":"Enrico.Mangano@gmtech.ch",
			"Enrico Mazzi":"Enrico.Mazzi@gmtech.ch",
			"Gabriele Izzo":"Gabriele.Izzo@gmtech.ch",
			"Gennaro Errico":"Gennaro.Errico@gmtech.ch",
			"Giuseppe Di Pierri":"Giuseppe.DiPierri@gmtech.ch",
			"Manlio Modugno":"Manlio.Modugno@gmtech.ch",
			"Manlio Vaccalluzzo":"Manlio.Vaccalluzzo@gmtech.ch",
			"Massimiliano Pepe":"Massimiliano.Pepe@gmtech.ch",
			"Matteo Besutti":"Matteo.Besutti@gmtech.ch",
			"Mattia Cattaneo":"Mattia.Cattaneo@gmtech.ch",
			"Stefano Coluccia":"Stefano.Coluccia@gmtech.ch",
			"Valentino Decarli":"Valentino.Decarli@gmtech.ch",
			]
		if(Person.count()==0)
			people.each{ name, email ->
				new Person(name:name, email:email.toLowerCase()).save()
			}
		
		MetaProgram.enrich()
		
    }
    def destroy = {
    }
}
