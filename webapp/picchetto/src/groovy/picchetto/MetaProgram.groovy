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
			new SimpleDateFormat("dd.MM.yyyy").format(delegate)
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
			new SimpleDateFormat("dd.MM.yyyy").parse(delegate)
		}
		Date.metaClass.until = { String to ->
			new Interval(from: delegate.startOfDay(), to:to.date.endOfDay())
		}
		Date.metaClass.isSunday = { 	
			delegate[DAY_OF_WEEK] == SUNDAY
		}
		String.metaClass.until = { String to ->
			delegate.date.until(to)
		}
		String.metaClass.getMonth = { 
			delegate.date.month
		}
		String.metaClass.getYear = { 
				("1.1."+delegate).until("31.12."+delegate)
		}
		Integer.metaClass.getYear = { 
				("1.1."+delegate).until("31.12."+delegate)
		}
		Date.metaClass.getMonth = { 
			new Interval(from: delegate.firstDayOfMonth).untilEndOfMonth()
		}
		Date.metaClass.getFirstDayOfMonth = {
			Calendar cal = Calendar.getInstance()
			cal.setTime(delegate)
			cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE))
			cal.getTime()
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
		
		
		java.lang.Integer.metaClass.jan = { int year ->
			(""+delegate+".1."+year).date
		}
//		java.lang.Integer.metaClass.feb = { int year ->
//			(""+delegate+".2."+year).date
//		}
//		java.lang.Integer.metaClass.mar = { int year ->
//			(""+delegate+".3."+year).date
//		}
//		java.lang.Integer.metaClass.apr = { int year ->
//			(""+delegate+".4."+year).date
//		}
//		java.lang.Integer.metaClass.may = { int year ->
//			(""+delegate+".5."+year).date
//		}
//		java.lang.Integer.metaClass.june = { int year ->
//			(""+delegate+".6."+year).date
//		}
//		java.lang.Integer.metaClass.july = { int year ->
//			(""+delegate+".7."+year).date
//		}
//		java.lang.Integer.metaClass.aug = { int year ->
//			(""+delegate+".8."+year).date
//		}
//		java.lang.Integer.metaClass.sept = { int year ->
//			(""+delegate+".9."+year).date
//		}
//		java.lang.Integer.metaClass.oct = { int year ->
//			(""+delegate+".10."+year).date
//		}
//		java.lang.Integer.metaClass.nov = { int year ->
//			(""+delegate+".11."+year).date
//		}
		java.lang.Integer.metaClass.dec = { int year ->
			(""+delegate+".12."+year).date
		}
		
	}
	
	static Date getStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
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
