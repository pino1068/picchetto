
package picchetto;

import org.junit.Before
import org.junit.Test


@Mock([Person, Period, Holidays])
class PeriodGenerationTest {
	
	@Before
	void setup(){
		MetaProgram.enrich()
	}
	
	@Test
	public void generateWithOnePerson() {
		Person enrico = new Person(name:"enrico").save()
		
		new PeriodsGenerator(range:2016.year).generate()
		
		assertEquals 53, Period.all.size()
		assertEquals enrico, Period.all.first().person
	}
	
	@Test
	public void doubleGeneration() {
		Person enrico = new Person(name:"enrico").save()
				
		new PeriodsGenerator(range:2016.year).generate()
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
	
	@Test
	public void withHolidays() {
		Person enrico = new Person(name:"enrico").save()
		Person matteo = new Person(name:"matteo").save()
		matteo.addToHolidays(new Holidays(interval: 2016.year))
		matteo.save()
		
		new PeriodsGenerator(range:2016.year).generate()
		
		assertEquals 0, matteo.periods.size()
		assertEquals 53, enrico.periods.size()
	}
}
