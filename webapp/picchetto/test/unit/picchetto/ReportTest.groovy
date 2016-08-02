
package picchetto;

import grails.test.mixin.Mock

import org.junit.Before
import org.junit.Test


@Mock([Person, Period])
class ReportTest {
	
	@Before
	void setup(){
		MetaProgram.enrich()
	}
	
	@Test
	public void empty() {
		Person enrico = new Person(name:"enrico").save()
		
		def report = new PeopleReport(period:2016.jan)
		
		assertEquals 0, report.all.size()
	}
	
	@Test
	public void personPeriods() {
		Person enrico = new Person(name:"enrico").save()
		new Period(person: enrico, interval:"1.1.2016".until("10.1.2016")).save()
		assertEquals 1, enrico.periods.size()
		def period = enrico.periods.first()
		
		assertEquals "1.1.2016".date.simpleFormat, period.fromDate.simpleFormat
		assertEquals "10.1.2016".date.simpleFormat, period.toDate.simpleFormat
	}
	
	@Test
	public void oneRecordReport() {
		Person enrico = new Person(name:"enrico").save()
		new Period(person: enrico, interval:"1.1.2016".until("10.1.2016")).save()
		def report = new PeopleReport(period:2016.jan)
		
		assertEquals 1, report.all.size()
		def first = report.all.first()
		
		assertEquals "enrico", first.name
		assertEquals 6, first.weekdays.size()
		assertEquals 0, first.holidays.size()
		assertEquals 4, first.weekendDays.size()
	}
}