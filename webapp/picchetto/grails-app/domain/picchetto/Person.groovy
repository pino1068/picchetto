package picchetto

class Person {
	String name
	
	@Override
	public String toString() {
		return name;
	}
	
	def getPeriods(){
		Period.findAllByPerson(this)
	}
	
    static constraints = {
    }
}
