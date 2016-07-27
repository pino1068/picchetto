package picchetto;

import java.text.DateFormat

import javax.validation.constraints.AssertTrue;

import org.junit.Before
import org.junit.Test


class DateTest {
	
	@Before
	void setup(){
		MetaProgram.enrich()
	}

	@Test
	public void createFromString() {
		assertEquals "Jan 1, 2016", DateFormat.getDateInstance().format("1.1.2016".date)
	}
	
	@Test
	public void formatAllDates() {
		assertEquals "01.01.2016", "1.1.2016".date.simpleFormat()
		assertEquals "01.01.2016", "1.1.2016".date.simpleFormat
	}
	
	@Test
	public void isWeekDay() {
		assertTrue "01.01.2016".date.weekDay
		assertTrue "01.01.2016".date.isWeekDay()
		assertFalse "01.01.2016".date.weekend
		assertFalse "01.01.2016".date.isWeekend()
	}
	
	@Test
	public void isWeekend() {
		assertTrue "02.01.2016".date.weekend
		assertTrue "02.01.2016".date.isWeekend()
		assertFalse "02.01.2016".date.isWeekDay()
		assertFalse "02.01.2016".date.weekDay
	}
	
	@Test
	public void interval() {
		assertEquals 1, "02.01.2016".date.until("02.01.2016").days.size()
		assertEquals 1, "02.01.2016".until("02.01.2016").days.size()
		assertEquals 2, "02.01.2016".until("03.01.2016").days.size()
		assertEquals 2, "02.01.2016".until("1.01.2016").days.size()
	}
	
	@Test
	public void intervalWeekendDays() {
		def range = "01.01.2016".until("10.01.2016")
		assertEquals 10, range.days.size()
		assertEquals 10-4, range.weekdays.size()
		
		range = "03.01.2016".until("10.01.2016")
		assertEquals 8, range.days.size()
		assertEquals 8-3, range.weekdays.size()
	}
	
	@Test
	public void jan() {
		def range = 2016.jan
		assertEquals "01.01.2016".date, range.from
		assertEquals "31.01.2016".date, range.to
		assertEquals 31, range.days.size()
		assertEquals 10, range.weekendDays.size()
		assertEquals 31-10, range.weekdays.size()
	}
	
	@Test
	public void nov() {
		def range = 2016.nov
		assertEquals "01.11.2016".date, range.from
		assertEquals "30.11.2016".date, range.to
		assertEquals 30, range.days.size()
		assertEquals 8, range.weekendDays.size()
		assertEquals 30-8, range.weekdays.size()
	}
	
	@Test
	public void monthOf() {
		def range = "01.01.2016".month
		assertEquals "01.01.2016".date, range.from
		assertEquals "31.01.2016".date, range.to
		assertEquals 31, range.days.size()
		assertEquals 10, range.weekendDays.size()
		assertEquals 31-10, range.weekdays.size()
		
		range = "01.01.2016".date.month
		assertEquals "01.01.2016".date, range.from
		assertEquals 31, range.days.size()
	}
}
