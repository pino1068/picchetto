package picchetto

class Person {
	String name
	
	@Override
	public String toString() {
		return name;
	}
	
	def getPeriods(){
		println Period.findAllByPerson(this)
		Period.findAllByPerson(this)
	}
	
    static constraints = {
    }
}
