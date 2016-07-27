package picchetto

class Period {
	Date fromDate, toDate
	String status
	Person person
	Double price
	
	static constraints = {
		id()
		person()
		fromDate(format:"dd.MM.yyyy")
		toDate(format:"dd.MM.yyyy")
		status(inList: ["open", "assigned", "on-market"])
		price(nullable:true)
	} 
}
