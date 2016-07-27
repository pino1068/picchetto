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

}
