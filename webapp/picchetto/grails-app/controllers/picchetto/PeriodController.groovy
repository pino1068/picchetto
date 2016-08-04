package picchetto



import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PeriodController {
	static scaffold = Period
	
	static allowedMethods = [sell: "POST",buy: "POST", save: "POST", update: "PUT", delete: "DELETE"]
	
    def list(Integer max) {
    	params.max = Math.min(max ?: 100, 500)
		render Period.findAllByParams(params) as JSON
    }

	@Transactional
	def generate(){
		def range = params.year?params.year.year:params.from.until(params.to)
		new PeriodsGenerator(range: range).generate()
		redirect(action: "search")
	}
	
	def search(){}
	
	@Transactional
	def sell(Period p){
		p.status = "on-market"
		p.save( flush:true)
		render true
	}
	
	@Transactional
	def buy(Period p){
		p.status = "assigned"
		p.person = session.user
		p.save( flush:true)
		render true
	}
}
