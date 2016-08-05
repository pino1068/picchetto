package picchetto

import static builder.PersonBuilder.*

import org.junit.Before;
import org.junit.Test;

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

import org.junit.Before
import org.junit.Test

import builder.PersonBuilder

@TestFor(PeriodController)
@Mock([Person, Period, Notification])
class MarketTest {
	@Before
	void setup(){
		MetaProgram.enrich()
	}
	
	@Test
	public void sell() {
		onePeriod(enrico())
		
		request.method = "POST"
		controller.sell(Period.first())		
		
		assertEquals "on-market", Period.first().status
	}
	
	@Test
	public void sellNotifiesOthers() {
		matteo()
		Period p = onePeriod(enrico())
		
		request.method = "POST"
		controller.sell(p)		
		
		assertEquals 1, Notification.count
		assertEquals matteo(), Notification.first().target
		assertEquals "period.sell.notify.message", Notification.first().message
	}
	
	@Test
	public void buyNotifiesCurrentAndPreviousOwner() {
		session.user = matteo()
		Period p = onePeriod(enrico())
		
		request.method = "POST"
//		controller.sell(p)
		controller.buy(p)		
		
		assertEquals 2, Notification.count
		
		assertEquals enrico(), Notification.first().target
		assertEquals "period.buy.gone.notify.message", Notification.first().message
		
		assertEquals matteo(), Notification.last().target
		assertEquals "period.buy.got.notify.message",  Notification.last().message
	}
	
	@Test
	public void notificationTriggerAll() {
		session.user = matteo()
		Period p = onePeriod(enrico())
		request.method = "POST"
		controller.buy(p)		
		assertEquals 2, Notification.count
		assertFalse Notification.first().sent
		assertFalse Notification.last().sent
		
		Notification.sendThemAll()
		
		assertTrue Notification.first().sent
		assertTrue Notification.last().sent
	}
	
	private onePeriod(Person enrico) {
		new Period(person: enrico, interval:"1.1.2016".until("10.1.2016")).save()
	}
}
