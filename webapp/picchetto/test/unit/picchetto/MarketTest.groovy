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
		
		assertEquals enrico(), Period.first().person
		assertEquals "on-market", Period.first().status
	}
	
	@Test
	public void buy() {
		session.user = matteo()
		Period p = onePeriod(enrico())
		
		request.method = "POST"
		controller.sell(p)
		controller.buy(p)		
		
		assertEquals matteo(), Period.first().person
		assertEquals "assigned", Period.first().status
	}
	
	@Test
	public void notifications() {
		session.user = matteo()
		Period p = onePeriod(enrico())
		
		request.method = "POST"
		controller.sell(p)
		controller.buy(p)		
		
		assertEquals 3, Notification.count
		
		assertEquals matteo(), Notification.first().target
		assertEquals "period.sell.notify.message", Notification.first().message
		assertEquals "http://localhost:8080/picchetto/period/search?id=1", Notification.first().link
		
		assertEquals enrico(), Notification.get(2).target
		assertEquals "period.buy.gone.notify.message", Notification.get(2).message
		assertEquals "http://localhost:8080/picchetto/period/search?id=1", Notification.get(2).link
		
		assertEquals matteo(), Notification.get(3).target
		assertEquals "period.buy.got.notify.message",  Notification.last().message
		assertEquals "http://localhost:8080/picchetto/period/search?id=1", Notification.last().link
	}
	
	@Test
	public void userNotifications() {
		session.user = matteo()
		Period p = onePeriod(enrico())
				
		request.method = "POST"
		controller.sell(p)
		controller.buy(p)		
				
		assertEquals 2, matteo().notifications.size()
		assertEquals 1, enrico().notifications.size()
	}
	
	@Test
	public void sendNotifications() {
		session.user = matteo()
		Period p = onePeriod(enrico())
		request.method = "POST"
		controller.buy(p)		
		assertEquals 2, Notification.count
		assertFalse Notification.first().sent
		assertFalse Notification.last().sent
		
		disableSendRealEmails()
		
		Notification.sendThemAll()
		
		assertTrue Notification.first().sent
		assertTrue Notification.last().sent
	}

	void disableSendRealEmails(){
		Notification.all*.grailsApplication = {config.picchetto.send.emails=false}
	}
	
	private onePeriod(Person enrico) {
		new Period(person: enrico, interval:"1.1.2016".until("10.1.2016")).save()
	}
}
