
class SecurityFilters {
	def filters = {
		allowAssets(uri: '/*') {
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
		userCheck(controller: '*', action: 'search|login|logout|json', invert:true) {
			before = {
					if(controllerName=='login'){
						return true
					}
					else if(controllerName=='period' && actionName in ["list", "buy", "sell"]){
						return true
					}
					else if (!session.user?.admin  && !request.requestURI.contains("assets")) {
						redirect(controller: 'period', action:"search")
						return true
					}
			}
		}
	}
}