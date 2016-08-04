package picchetto

class PublicHolidayParser {
	def parse(text){
		text.replaceAll("\"|\n", "").split(",").collect{
			def item = it.trim().split("\\|")
			new PublicHoliday(
				date:item[0].date("yyyy-MM-dd"), 
				description:item[1])
		}
	}
}
