package picchetto

import grails.transaction.Transactional

@Transactional(readOnly = true)
class NotificationController {
	static scaffold = Notification
}
