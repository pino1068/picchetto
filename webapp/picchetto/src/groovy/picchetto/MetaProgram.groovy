package picchetto
import static java.util.Calendar.*

import java.text.SimpleDateFormat

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
			def formatter = new SimpleDateFormat("dd.MM.yyyy")
			def date = formatter.parse(delegate)
		}
	}

}
