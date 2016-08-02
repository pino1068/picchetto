
package picchetto;

import grails.test.mixin.*

import org.junit.Before
import org.junit.Test


@Mock([Person, Period])
class CoreTest {
	
	@Before
	void setup(){
		MetaProgram.enrich()
	}
	
	@Ignore
	public void randomPerson() {
		Person enrico = new Person(name:"enrico").save()
		Person matteo = new Person(name:"matteo").save()
		
		println Person.random1()
		println Person.random1()
		println Person.random1()
		println Person.random1()
		println Person.random1()
	}
	
	@Test
	public void generateWithOnePerson() {
		Person enrico = new Person(name:"enrico").save()
		
		new PeriodsGenerator(range:2016.year).generate()
		
		assertEquals 53, Period.all.size()
		assertEquals enrico, Period.all.first().person
	}
	
	@Test
	public void picksRandom() {
		Person enrico = new Person(name:"enrico").save()
		Person matteo = new Person(name:"matteo").save()
		
		new PeriodsGenerator(range:2016.year).generate()
		
		assertEquals 53, Period.all.size()
		assertTrue Period.first().person in [enrico,matteo]
	}
	
	@Test
	public void firstDayOfTheYearIsSunday() {
		Person enrico = new Person(name:"enrico").save()
				
		new PeriodsGenerator(range:2017.year).generate()
				
		assertEquals 53, Period.all.size()
	}
}
