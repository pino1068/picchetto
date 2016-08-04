package picchetto

import grails.transaction.Transactional


class PublicHolidayController {
	static scaffold = PublicHoliday
	
	static allowedMethods = [upload: "POST"]
	
	def upload(){
		try {
			new PublicHolidayParser().parse(params.content)*.save()
		} catch (Exception e) {
			flash.message = e.message
		}
		redirect (action:"index")
	}
}
