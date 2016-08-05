package picchetto

class Notification {
	def mailService
	Date dateCreated, lastUpdated
	
	Person target
	String message
	boolean sent = false
	
	void send(){
		if(! sent)
			mailService?.sendMail {
				to "matteo.besutti@gmtech.com", "giuseppe.dipierri@gmtech.com"
				from "mekkano@mfgroup.ch"
				subject "Picchetto notification"
	//			body "to:"+target.name+" - message: "+message
				body "to:"+this.target.name+" - message: "+ this.message
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
