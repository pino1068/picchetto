package picchetto
import static java.util.Calendar.*

import java.text.SimpleDateFormat

import time.Interval

class MetaProgram {
	
	static void enrich(){
		Date.metaClass.isWeekend = {
			delegate[DAY_OF_WEEK] in [SATURDAY, SUNDAY]
		}
		Date.metaClass.isWeekDay = {
			!delegate.weekend
		}
		Date.metaClass.simpleFormat = {
			def formatter = new SimpleDateFormat("dd.MM.yyyy")
			formatter.format(delegate)
		}
		Date.metaClass.getSimpleFormat = {
			delegate.simpleFormat()
		}
		String.metaClass.getDate = {
			def formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
			def date = formatter.parse(delegate+" 00:00:00")
		}
		Date.metaClass.until = { String to ->
			new Interval(from: delegate, to:to.date)
		}
		String.metaClass.until = { String to ->
			delegate.date.until(to)
		}
		String.metaClass.getMonth = { 
			delegate.date.month
		}
		Date.metaClass.getMonth = { 
				new Interval(from: delegate).untilEndOfMonth()
		}
		Integer.metaClass.getJan = {  ("1.1."+delegate).month }
		Integer.metaClass.getFeb = {  ("1.2."+delegate).month }
		Integer.metaClass.getMar = {  ("1.3."+delegate).month }
		Integer.metaClass.getApr = {  ("1.4."+delegate).month }
		Integer.metaClass.getMay = {  ("1.5."+delegate).month }
		Integer.metaClass.getJun = {  ("1.6."+delegate).month }
		Integer.metaClass.getJul = {  ("1.7."+delegate).month }
		Integer.metaClass.getAug = {  ("1.8."+delegate).month }
		Integer.metaClass.getSep = {  ("1.9."+delegate).month }
		Integer.metaClass.getOct = {  ("1.10."+delegate).month }
		Integer.metaClass.getNov = {  ("1.11."+delegate).month }
		Integer.metaClass.getDec = {  ("1.12."+delegate).month }
//		java.lang.Integer.metaClass.static.feb = { int year ->
//			return new Day(day:delegate, month: 2, year: year)
//		}
//		java.lang.Integer.metaClass.static.mar = { int year ->
//			return new Day(day:delegate, month: 3, year: year)
//		}
//		java.lang.Integer.metaClass.static.apr = { int year ->
//			return new Day(day:delegate, month: 4, year: year)
//		}
//		java.lang.Integer.metaClass.static.may = { int year ->
//			return new Day(day:delegate, month: 5, year: year)
//		}
//		java.lang.Integer.metaClass.static.june = { int year ->
//			return new Day(day:delegate, month: 6, year: year)
//		}
//		java.lang.Integer.metaClass.static.july = { int year ->
//			return new Day(day:delegate, month: 7, year: year)
//		}
//		java.lang.Integer.metaClass.static.aug = { int year ->
//			return new Day(day:delegate, month: 8, year: year)
//		}
//		java.lang.Integer.metaClass.static.sept = { int year ->
//			return new Day(day:delegate, month: 9, year: year)
//		}
//		java.lang.Integer.metaClass.static.oct = { int year ->
//			return new Day(day:delegate, month: 10, year: year)
//		}
//		java.lang.Integer.metaClass.static.nov = { int year ->
//			return new Day(day:delegate, month: 11, year: year)
//		}
//		java.lang.Integer.metaClass.static.dec = { int year ->
//			return new Day(day:delegate, month: 12, year: year)
//		}
	}

}
