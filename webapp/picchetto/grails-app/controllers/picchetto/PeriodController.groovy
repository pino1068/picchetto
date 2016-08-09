package picchetto



import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.GrailsApplication

@Transactional(readOnly = true)
class PeriodController {
	static scaffold = Period
	static allowedMethods = [sell: "POST",buy: "POST", save: "POST", update: "PUT", delete: "DELETE"]
	
	def grailsApplication
	
    def list(Integer max) {
    	params.max = Math.min(max ?: 100, 500)
		render Period.findAllByParams(params) as JSON
    }

	@Transactional
	def generate(){
		if(session.user.admin){
			def range = params.year?params.year.year:params.from.until(params.to)
			new PeriodsGenerator(range: range).generate().findAll{it!=null}.each{ Period p ->
				def link = p.createLink(grailsApplication.config.picchetto.server.url)
				new Notification(target:p.person,
					link: link,
					message:
					message(code: "period.create.notify.message", args:
						[p.person.firstName, p.fromDate.simpleFormat, p.toDate.simpleFormat, link])
					).save()
			}
			
		}else
			flash.message = "only admin can generate periods"
		redirect(action: "search")
	}
	
	def search(){
	}
	
	@Transactional
	def sell(Period p){
		p.status = "on-market"
		p.save( flush:true)
		def link = p.createLink(grailsApplication.config.picchetto.server.url)
		Person.findAllByIdNotEqual(p.person.id).each{
			new Notification(target:it, 
				link: link,
				message:
				message(code: "period.sell.notify.message", args: 
					[it.firstName, p.person.firstName, p.fromDate.simpleFormat, p.toDate.simpleFormat,link])
				).save()
		}
		render true
	}
	
	@Transactional
	def buy(Period p){
		Person previous = p.person
		p.status = "assigned"
		p.person = session.user
		p.save( flush:true)
		def link = p.createLink(grailsApplication.config.picchetto.server.url)
		new Notification(target:previous,
				link: link,
				message: message(code: "period.buy.gone.notify.message", args: 
				[previous.firstName, p.person.firstName, p.fromDate.simpleFormat, p.toDate.simpleFormat, link])
			).save()
		new Notification(target:p.person, 
				link: link,
				message: message(code: "period.buy.got.notify.message", args: 
				[p.person.firstName, previous.firstName, p.fromDate.simpleFormat, p.toDate.simpleFormat, link])
				).save()
		render true
	}
}
