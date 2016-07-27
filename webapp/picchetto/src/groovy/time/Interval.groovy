package time

class Interval {
	Date from, to
	
	def getWeekdays(){
		days.findAll{it.weekDay}
	}
	
	def getWeekendDays(){
		days.findAll{it.weekend}
	}
	
	def getDays(){
		def min = to.time < from.time? to : from
		def items = 0l..(daysCount-1l)
		items.collect{new Date(min.time + (MILLISECS_IN_DAY*it))}
	}
	
	def untilEndOfMonth(){
		new Interval(from:from, to:endOfMonth())
	}
	
	def endOfMonth(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(from);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		cal.getTime();
	}
	
	private int getDaysCount(){
		def diff = to.time - from.time
		Math.abs(diff / MILLISECS_IN_DAY) + 1
	}

	static final MILLISECS_IN_DAY = 1000*60*60*24
}
