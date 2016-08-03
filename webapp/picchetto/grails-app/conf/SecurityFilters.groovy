
class SecurityFilters {
	def filters = {
		assets(uri: '/*') {
			before = {
				if (!session.user && request.requestURI == "/picchetto/") {
					redirect(controller: 'login', action:"index")
					return true
				}
			}
		}
		loginCheck(controller: '*', action: 'index', controllerExclude:"login") {
			before = {
				if (!session.user && !request.requestURI.contains("assets")) {
					redirect(controller: 'login', action:"index")
					return true
				}
			}
		}
	}
}