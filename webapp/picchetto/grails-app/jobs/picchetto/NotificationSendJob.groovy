package picchetto

class NotificationSendJob {
	
	static triggers = {
		simple name: 'sendNotificationEmails', startDelay: 2000, repeatInterval: 20000
	}
	def group = "All jobs"
	def description = "Sending email job"

	def execute(){
		Notification.sendThemAll()
	}
}
