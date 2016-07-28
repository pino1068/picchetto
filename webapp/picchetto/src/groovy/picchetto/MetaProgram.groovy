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
		Date.metaClass.startOfDay = {
			getStartOfDay(delegate)
		}
		Date.metaClass.endOfDay = {
			getEndOfDay(delegate)
		}
		String.metaClass.getDate = {
			def formatter = new SimpleDateFormat("dd.MM.yyyy")
			def date = formatter.parse(delegate)
		}
		Date.metaClass.until = { String to ->
			new Interval(from: delegate.startOfDay(), to:to.date.endOfDay())
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
	}
	
	static Date getStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 1);
		return calendar.getTime();
	}

	static Date getEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 998);
		return calendar.getTime();
	}

}
