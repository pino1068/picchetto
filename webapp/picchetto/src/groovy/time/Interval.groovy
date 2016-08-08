package time

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import java.text.SimpleDateFormat

@EqualsAndHashCode
@ToString
class Interval {
	Date from = new Date(), to = from
	
	def getWeekdays(){
		days.findAll{it.weekDay}
	}
	
	def getWeekendDays(){
		days.findAll{it.weekend}
	}
	
	def getDays(){
		if(empty)
			return []
		def min = min(from, to)
		def items = 0..daysCount
		items.collect{new Date(min.time + (MILLISECS_IN_DAY*it))}
	}
	
	boolean intersectsAny(others){
		intersect(others).findAll {!it.empty}.size() > 0
	}
	
	def untilEndOfMonth(){
		new Interval(from:from, to:endOfMonth)
	}
	
	def getEndOfMonth(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(from.endOfDay());
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		cal.getTime();
	}
	
	def intersect(Interval other){
		def result = new Interval(
			from: 	max(from, other.from),
			to: 	min(to, other.to))
		result.positive()? result : newEmpty()
	}
	
	def intersect(others){
		others.collect{intersect(it)}
	}
	
	def filter(daysToPick){
		days.intersect(daysToPick)
	}
	
	boolean positive(){
		to.time >= from.time
	}
	
	def min(Date one, Date other){
		one.time < other.time? one : other
	}
	
	def max(Date one, Date other){
		one.time < other.time? other : one 
	}
	
	def swap(){
		new Interval(from:to, to:from)
	}
	
	def getWeeks(){
		split{ day -> day.sunday}
	}

	private def split(Closure rule) {
		def ranges = []
		def currentDays = []
		days.each{
			if(rule(it)){
				ranges << new Interval(from:currentDays.empty?it:currentDays.first(), to:it.endOfDay())
				currentDays.clear()
			}else
				currentDays << it
		}
		if(!currentDays.empty)
			ranges <<  new Interval(from:currentDays.first(), to:currentDays.last().endOfDay())
		ranges
	}
	
	static newEmpty(){
		new Interval()
	}
	
	boolean isEmpty(){
		to.time == from.time
	}
	
	private long getDaysCount(){
		def diff = to.time - from.time
		Math.abs(diff / MILLISECS_IN_DAY)
	}

	static final MILLISECS_IN_DAY = 1000*60*60*24
}
