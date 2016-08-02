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
	
	static Person random(){
		Person.get(new Random().nextInt(Person.count())+1)
	}
	
    static constraints = {
    }
}
