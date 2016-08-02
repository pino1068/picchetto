
package picchetto;

import grails.test.mixin.*

import org.junit.Before
import org.junit.Test


@TestFor(PeriodController)
@Mock([Person, Period])
class PeriodTest {
	
	@Before
	void setup(){
		MetaProgram.enrich()
		Person enrico = new Person(name:"enrico").save()
		Person matteo = new Person(name:"matteo").save()
		new PeriodsGenerator(range: 2016.year).generate()
	}
	
	@Ignore
	public void searchByPerson() {
		params.person="ric"
		controller.list();
		
		assertTrue response.json.size() > 0
	}
	
	@Test
	public void searchByNonExistingPerson() {
		params.person="ciccio"
		controller.list();
		
		assertEquals 0, response.json.size()
	}
	
	@Test
	public void searchByFromDate() {
		params.from="1.1.2017"
		controller.list();
		
		assertEquals 0, response.json.size()
	}
	
	@Test
	public void searchByFromDateWithEmptyTo() {
		params.from="1.1.2017"
		params.to=""
		controller.list();
		
		assertEquals 0, response.json.size()
	}
	
	@Test
	public void searchByToDate() {
		params.to="31.12.2015"
		controller.list();
		
		assertEquals 0, response.json.size()
	}
}
