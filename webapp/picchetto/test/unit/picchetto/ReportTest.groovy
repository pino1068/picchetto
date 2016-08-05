
package picchetto;

import static builder.PersonBuilder.*
import grails.test.mixin.Mock

import org.junit.Before
import org.junit.Test


@Mock([Person, Period, PublicHoliday])
class ReportTest {
	
	@Before
	void setup(){
		MetaProgram.enrich()
	}
	
	@Test
	public void empty() {
		Person enrico = enrico()
		
		def report = new PeopleReport(period:2016.jan)
		
		assertEquals 0, report.all.size()
	}
	
	@Test
	public void personPeriods() {
		Person enrico = enrico()
		new Period(person: enrico, interval:"1.1.2016".until("10.1.2016")).save()
		assertEquals 1, enrico.periods.size()
		def period = enrico.periods.first()
		
		assertEquals "1.1.2016".date.simpleFormat, period.fromDate.simpleFormat
		assertEquals "10.1.2016".date.simpleFormat, period.toDate.simpleFormat
	}
	
	@Test
	public void oneRecord() {
		Person enrico = enrico()
		new Period(person: enrico, interval:"1.1.2016".until("10.1.2016")).save()
		def report = new PeopleReport(period:2016.jan)
		
		assertEquals 1, report.all.size()
		def first = report.all.first()
		
		assertEquals "enrico", first.name
		assertEquals 6, first.weekdays.size()
		assertEquals 0, first.holidays.size()
		assertEquals 4, first.weekendDays.size()
	}
	
	@Test
	public void publicHoliday() {
		new PublicHoliday(date:"6.1.2016".date,description:"Epiphany").save()
		Person enrico = enrico()
		new Period(person: enrico, interval:"1.1.2016".until("10.1.2016")).save()
		def report = new PeopleReport(period:2016.jan)
		
		assertEquals 1, report.all.size()
		def first = report.all.first()
		
		assertEquals "enrico", first.name
		assertEquals 5, first.weekdays.size()
		assertEquals 1, first.holidays.size()
		assertEquals 4, first.weekendDays.size()
	}
}
