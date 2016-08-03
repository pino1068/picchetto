package picchetto

import grails.transaction.Transactional

@Transactional(readOnly = true)
class HolidaysController {
	static scaffold = Holidays
	
	def deleteGet(Holidays h){
		h.delete(flush: true)
		redirect(action: "show", controller: "person", id: h.person.id)
	}
}
