
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
		
		def report = new PoepleReport(period:2016.jan)
		
		assertEquals 0, report.all.size()
	}
	
	@Test
	public void personPeriods() {
		Person enrico = new Person(name:"enrico").save()
		new Period(person: enrico, range:"1.1.2016".until("10.1.2016")).save()
		assertEquals 1, enrico.periods.size()
		def period = enrico.periods.first()
		
		assertEquals "1.1.2016".date, period.fromDate
		assertEquals "10.1.2016".date, period.toDate
	}
	
	@Test
	public void oneRecordReport() {
		Person enrico = new Person(name:"enrico").save()
		new Period(person: enrico, range:"1.1.2016".until("10.1.2016")).save()
		assertEquals 1, enrico.periods.size()
		assertEquals "1.1.2016".date, enrico.periods.first().fromDate
		assertEquals "10.1.2016".date, enrico.periods.first().toDate
		def report = new PoepleReport(period:2016.jan)
		
		assertEquals 1, report.all.size()
		def first = report.all.first()
		
		assertEquals "enrico", first.name
		assertEquals 1, enrico.periods.size()
//		println enrico.periods
//		assertEquals 6, first.weekdays
//		assertEquals 0, first.holidays
//		assertEquals 4, first.weekendDays
	}
}
