

package picchetto;

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

import org.junit.Before
import org.junit.Test

@TestFor(PublicHolidayController)
@Mock([PublicHoliday])
class PublicHolidayTest {

	@Before
	void setup(){
		MetaProgram.enrich()
	}

	@Test
	public void parse() {
		String content = """
"2014-08-01|Festa Nazionale Svizzera", "2014-08-15|Assunzione", "2014-11-01|Ognissanti",  "2014-12-08|Immacolata", ""2014-12-25|Natale", "2014-12-26|Santo Stefano","2015-01-01|Capodanno", "2015-01-06|Epifania", "2015-03-19|San Giuseppe", "2015-04-06|Lunedi di Pasqua", "2015-05-01|Festa del lavoro", "2015-05-14|Ascensione", "2015-05-25|Lunedi di Pentecoste", "2015-06-04|Corpus Domini", "2015-06-29|San Pietro e Paolo", "2015-08-01|Festa Nazionale Svizzera", "2015-08-15|Assunzione", "2015-11-01|Ognissanti", "2015-12-08|Immacolata", "2015-12-25|Natale", "2015-12-26|Santo Stefano","2016-01-01|Capodanno", "2016-01-06|Epifania", "2016-03-19|San Giuseppe", "2016-03-28|Lunedi di Pasqua", "2016-05-01|Festa del lavoro", "2016-05-05|Ascensione", "2016-05-16|Lunedi di Pentecoste", "2016-05-26|Corpus Domini", "2016-06-29|San Pietro e Paolo", "2016-08-01|Festa Nazionale Svizzera", "2016-08-15|Assunzione", "2016-11-01|Ognissanti", "2016-12-08|Immacolata", "2016-12-25|Natale","2016-12-26|Santo Stefano","2017-01-01|San Silvestro"
""";
		
		def collection = new PublicHolidayParser().parse(content)
		
		assertEquals 37, collection.size()
		assertEquals "1.8.2014".date, collection.first().date
		assertEquals "Festa Nazionale Svizzera", collection.first().description
	}
	
	@Test
	public void save(){
		String content = """
"2014-08-01|Festa Nazionale Svizzera", "2014-08-15|Assunzione", "2014-11-01|Ognissanti",  "2014-12-08|Immacolata", ""2014-12-25|Natale", "2014-12-26|Santo Stefano","2015-01-01|Capodanno", "2015-01-06|Epifania", "2015-03-19|San Giuseppe", "2015-04-06|Lunedi di Pasqua", "2015-05-01|Festa del lavoro", "2015-05-14|Ascensione", "2015-05-25|Lunedi di Pentecoste", "2015-06-04|Corpus Domini", "2015-06-29|San Pietro e Paolo", "2015-08-01|Festa Nazionale Svizzera", "2015-08-15|Assunzione", "2015-11-01|Ognissanti", "2015-12-08|Immacolata", "2015-12-25|Natale", "2015-12-26|Santo Stefano","2016-01-01|Capodanno", "2016-01-06|Epifania", "2016-03-19|San Giuseppe", "2016-03-28|Lunedi di Pasqua", "2016-05-01|Festa del lavoro", "2016-05-05|Ascensione", "2016-05-16|Lunedi di Pentecoste", "2016-05-26|Corpus Domini", "2016-06-29|San Pietro e Paolo", "2016-08-01|Festa Nazionale Svizzera", "2016-08-15|Assunzione", "2016-11-01|Ognissanti", "2016-12-08|Immacolata", "2016-12-25|Natale","2016-12-26|Santo Stefano","2017-01-01|San Silvestro"
""";
				
		new PublicHolidayParser().parse(content)*.save()
		
		assertEquals 37, PublicHoliday.count
	}
	
	@Test
	public void upload(){
		String content = """"2014-08-01|Festa Nazionale Svizzera", "2014-08-15|Assunzione", "2014-11-01|Ognissanti",  "2014-12-08|Immacolata", ""2014-12-25|Natale", "2014-12-26|Santo Stefano","2015-01-01|Capodanno", "2015-01-06|Epifania", "2015-03-19|San Giuseppe", "2015-04-06|Lunedi di Pasqua", "2015-05-01|Festa del lavoro", "2015-05-14|Ascensione", "2015-05-25|Lunedi di Pentecoste", "2015-06-04|Corpus Domini", "2015-06-29|San Pietro e Paolo", "2015-08-01|Festa Nazionale Svizzera", "2015-08-15|Assunzione", "2015-11-01|Ognissanti", "2015-12-08|Immacolata", "2015-12-25|Natale", "2015-12-26|Santo Stefano","2016-01-01|Capodanno", "2016-01-06|Epifania", "2016-03-19|San Giuseppe", "2016-03-28|Lunedi di Pasqua", "2016-05-01|Festa del lavoro", "2016-05-05|Ascensione", "2016-05-16|Lunedi di Pentecoste", "2016-05-26|Corpus Domini", "2016-06-29|San Pietro e Paolo", "2016-08-01|Festa Nazionale Svizzera", "2016-08-15|Assunzione", "2016-11-01|Ognissanti", "2016-12-08|Immacolata", 
"2016-12-25|Natale","2016-12-26|Santo Stefano","2017-01-01|San Silvestro" """;
		
		request.method = "POST"
		params.content = content
		controller.upload()
		
		assertNull flash.message
		assertEquals 37, PublicHoliday.count
	}
}