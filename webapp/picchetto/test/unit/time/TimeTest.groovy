package time;

import java.text.DateFormat

import org.junit.Before
import org.junit.Test

import picchetto.MetaProgram;
import time.Interval


class TimeTest {
	
	@Before
	void setup(){
		MetaProgram.enrich()
	}

	@Test
	public void createFromString() {
		assertEquals "Jan 1, 2016", DateFormat.getDateInstance().format("1.1.2016".date)
	}
	
	@Test
	public void formatDate(){
		assertEquals "1.1.2016".date, "2016-01-01".date("yyyy-MM-dd")
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
		
		assertEquals 2, "1.01.2016".until("02.01.2016").days.size()
		assertEquals 1, "02.01.2016".until("1.01.2016").days.size()
	}
	
	@Test
	public void positive() {
		assertTrue "02.01.2016".until("03.01.2016").positive()
		assertFalse "02.01.2016".until("1.01.2016").positive()
	}
	
	@Test
	public void swap() {
		assertFalse "02.01.2016".until("03.01.2016").swap().positive()
		assertTrue "04.01.2016".until("1.01.2016").swap().positive()
	}
	
	@Test
	public void empty() {
		assertEquals 0, Interval.newEmpty().days.size()
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
		assertEquals "31.01.2016".date.endOfDay(), range.to
		assertEquals 31, range.days.size()
		assertEquals 10, range.weekendDays.size()
		assertEquals 31-10, range.weekdays.size()
	}
	
	@Test
	public void nov() {
		def range = 2016.nov
		assertEquals "01.11.2016".date, range.from
		assertEquals "30.11.2016".date.endOfDay(), range.to
		assertEquals 30, range.days.size()
		assertEquals 8, range.weekendDays.size()
		assertEquals 30-8, range.weekdays.size()
	}
	
	@Test
	public void monthOf() {
		def range = "01.01.2016".month
		assertEquals "01.01.2016".date, range.from
		assertEquals "31.01.2016".date.endOfDay(), range.to
		assertEquals 31, range.days.size()
		assertEquals 10, range.weekendDays.size()
		assertEquals 31-10, range.weekdays.size()
		
		range = "01.01.2016".date.month
		assertEquals "01.01.2016".date, range.from
		assertEquals 31, range.days.size()
		
		range = "08.01.2016".month
		assertEquals "01.01.2016".date, range.from
	}
	
	@Test
	public void intersect() {
		def jan = 2016.jan
		def feb = 2016.feb
		
		def within = "01.01.2016".until("10.01.2016")
		assertEquals within, jan.intersect(within)
		
		def out = "01.02.2016".until("10.02.2016")
		assertEquals 0, jan.intersect(out).days.size()
		
		def partial = "20.01.2016".until("10.02.2016")
		assertEquals 10, feb.intersect(partial).days.size()
	}
	
	@Test
	public void intersectMore() {
		def within = "02.01.2016".until("10.01.2016")
		def out = "01.02.2016".until("10.02.2016")
		def partial = "20.12.2015".until("5.1.2016")
		
		def intersections = 2016.jan.intersect([within, out, partial])
		def sizes = [9,0,5]
		assertEquals sizes, intersections*.days*.size()
		def weekdays = [5,0,3]
		assertEquals weekdays, intersections*.weekdays*.size()
		
		assertTrue 2016.jan.intersectsAny([within, out, partial])
		assertFalse 2016.jan.intersectsAny([out, out])
	}
	
	@Test
	public void intersectsAny() {
		def within = "02.01.2016".until("10.01.2016")
		def firstDayWithin = "1.1.2016".until("1.1.2016")
		def out = "01.02.2016".until("10.02.2016")
		def out2 = "01.03.2016".until("10.03.2016")
		def partial = "20.12.2015".until("5.1.2016")
		
		assertTrue 2016.jan.intersectsAny([within, firstDayWithin, out, partial])
		assertFalse 2016.jan.intersectsAny([out, out2])
	}
	
	@Test
	public void filterDays() {
		def range = "1.01.2016".until("10.01.2016")
		
		assertEquals 2, range.filter(["2.1.2016".date, "4.1.2016".date]).size()
		assertEquals 1, range.filter(["2.1.2016".date, "4.2.2016".date]).size()
	}
	
	@Test
	public void createYear() {
		assertEquals 366, 2016.year.days.size()
	}
	
	@Test
	public void isSunday() {
		assertFalse "1.1.2016".date.sunday
		assertTrue "3.1.2016".date.sunday
	}

	@Test
	public void generateWeeks() {
		assertEquals 53, 2016.year.weeks.size()
		assertEquals 1.jan(2016).time, 2016.year.weeks.first().from.time
		assertEquals 31.dec(2016).endOfDay().time, 2016.year.weeks.last().to.time
	}
}
