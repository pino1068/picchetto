package picchetto

class LoginController {
	
	def index(){
	}
	
	def loginAs(Person p){
		session.user = p
		redirect(uri: "/")
	}
	
	def logout(Person p){
		session.user = null
		redirect(action:"index")
	}
}
