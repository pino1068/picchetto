package picchetto



import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PeriodController {
	static scaffold = Period
    
    def list(Integer max) {
    	params.max = Math.min(max ?: 100, 500)
		render Period.findAllByParams(params) as JSON
    }

	
	def generate(){
		new PeriodsGenerator(range: params.year.year).generate()
		redirect(action: "index")
	}
	
	def search(){}
}