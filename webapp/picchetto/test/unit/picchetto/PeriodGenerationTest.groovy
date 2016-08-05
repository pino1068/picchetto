
package picchetto;

import static builder.PersonBuilder.*
import grails.test.mixin.Mock
import grails.test.mixin.TestFor;

import org.junit.Before
import org.junit.Test


@TestFor(PeriodController)
@Mock([Person, Period, Holidays, Notification])
class PeriodGenerationTest {
	
	@Before
	void setup(){
		MetaProgram.enrich()
	}
	
	@Test
	public void onlyAdminCanGenerate() {
		Person matteo = matteo()
		Person enrico = session.user = enrico().makeAdmin()
		
		params.year = "2016"
		controller.generate()
		
		assertEquals 53, Period.all.size()
	}
	
	@Test
	public void generationNotifiesNewOwners() {
		Person matteo = matteo()
		Person enrico = session.user = enrico().makeAdmin()
		
		params.year = "2016"
		controller.generate()
		
		assertEquals 26, matteo.notifications.size()
		assertEquals "http://localhost:8080/picchetto/period/search?id=2", matteo.notifications.first().link
		assertEquals "period.create.notify.message", matteo.notifications.first().message
	}
	
	@Test
	public void "user is not allowed to generate"() {
		Person enrico = session.user = enrico()
				
		params.year = "2016"
		controller.generate()
		
		assertEquals "only admin can generate periods", flash.message
		assertEquals 0, Period.all.size()
	}
	
	@Test
	public void generateWithOnePerson() {
		Person enrico = enrico()
				
		new PeriodsGenerator(range:2016.year).generate()
				
		assertEquals 53, Period.all.size()
		assertEquals enrico, Period.all.first().person
	}
	
	@Test
	public void doubleGeneration() {
		Person enrico = enrico()
				
		new PeriodsGenerator(range:2016.year).generate()
		new PeriodsGenerator(range:2016.year).generate()
			
		assertEquals 53, Period.all.size()
		assertEquals enrico, Period.all.first().person
	}
	
	@Test
	public void generateWithIntersection() {
		Person enrico = enrico()
		
		new PeriodsGenerator(range:2016.year).generate()
		new PeriodsGenerator(range:"1.1.2016".until("10.1.2016")).generate()
		
		assertEquals 53, Period.all.size()
		assertEquals enrico, Period.all.first().person
	}
	
	@Test
	public void picksRandom() {
		Person matteo = matteo()
		Person enrico = enrico()
		
		new PeriodsGenerator(range:2016.year).generate()
		
		assertEquals 53, Period.all.size()
		assertEquals enrico, Period.get(1).person
		assertEquals matteo, Period.get(2).person
		assertEquals enrico, Period.get(3).person
		assertEquals matteo, Period.get(4).person
	}
	
	@Test
	public void stressTest() {
		(0..1000).each{
			aPerson("matteo"+it)
		}
		new PeriodsGenerator(range:2016.year).generate()
				
		assertEquals 53, Period.all.size()
		assertEquals "matteo0", Period.first().person.name
		assertEquals "matteo144", Period.last().person.name
	}
	
	@Test
	public void firstDayOfTheYearIsSunday() {
		enrico()
				
		new PeriodsGenerator(range:2017.year).generate()
				
		assertEquals 53, Period.all.size()
	}
	
	@Test
	public void withHolidays() {
		Person enrico = enrico()
		Person matteo = matteo()
		matteo.addToHolidays(new Holidays(interval: 2016.year))
		matteo.save()
		
		new PeriodsGenerator(range:2016.year).generate()
		
		assertEquals 0, matteo.periods.size()
		assertEquals 53, enrico.periods.size()
	}
	
	@Test(expected=IllegalStateException.class)
	public void allOnHolidays() {
		Person enrico = enrico()
		enrico.addToHolidays(new Holidays(interval: 2016.year))
		Person matteo = matteo()
		matteo.addToHolidays(new Holidays(interval: 2016.year))
		matteo.save()
				
		new PeriodsGenerator(range:2016.year).generate()
	}
	
	@Test(expected=IllegalStateException.class)
	public void noPerson() {
		new PeriodsGenerator(range:2016.year).generate()
	}
}
