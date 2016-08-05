package picchetto

class Notification {
	def mailService
	def grailsApplication
	Date dateCreated, lastUpdated
	
	Person target
	String message
	String link
	boolean sent = false
	
	void send(){
		if(! sent  && grailsApplication.config.picchetto.send.emails)
			mailService?.sendMail {
				cc "matteo.besutti@gmtech.ch", "giuseppe.dipierri@gmtech.ch"
				to this.target.email
				from "tech.support@mfgroup.ch"
				subject "Picchetto: notification"
				body this.message
			 }
		sent=true
		save()
	}
	
	static void sendThemAll(){
		Notification.findAllBySent(false)*.send()
	}
	
	static mapping = {
		autoTimestamp true
	}
}
