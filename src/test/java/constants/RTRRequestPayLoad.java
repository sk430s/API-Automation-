package constants;

public class RTRRequestPayLoad {

public String createReqBodyRTR(String ticketNum) {
		
		return "{\r\n"
				+ "    \"short_description\": \"Router Down\",\r\n"
				+ "    \"contact_type\": \"web\",\r\n"
				+ "    \"comments\": \"Test IProuter now\\n\\nIP From: \\nIP To: \\n\",\r\n"
				+ "    \"is_test_authorized\": \"yes\",\r\n"
				+ "    \"power_to_cpe\": \"yes\",\r\n"
				+ "    \"is_dispatch_authorized\": \"yes\",\r\n"
				+ "    \"contact\": \"shiprkum@in.ibm.com\",\r\n"
				+ "    \"priority\": \"1\",\r\n"
				+ "    \"category\": \"Fault\",\r\n"
				+ "    \"contactfirstname\": \"SHIPRA\",\r\n"
				+ "    \"contactlastname\": \"KUMARI\",\r\n"
				+ "    \"contact_phone\": \"040259999\",\r\n"
				+ "    \"reported_trouble\": \"Router Down\",\r\n"
				+ "    \"primary_site_contact_name\": \"Marilyn\",\r\n"
				+ "    \"primary_site_contact_phone\": \"9194741857\",\r\n"
				+ "    \"primary_site_contact_email\": \"marilynlee@ems.att.com\",\r\n"
				+ "    \"site_access_hours\": \"{\\\"listofaccesshour\\\":[{\\\"day_of_week\\\":\\\"MON\\\",\\\"start\\\":\\\"08:00\\\",\\\"end\\\":\\\"17:00\\\"},{\\\"day_of_week\\\":\\\"TUE\\\",\\\"start\\\":\\\"08:00\\\",\\\"end\\\":\\\"17:00\\\"},{\\\"day_of_week\\\":\\\"WED\\\",\\\"start\\\":\\\"08:00\\\",\\\"end\\\":\\\"17:00\\\"},{\\\"day_of_week\\\":\\\"THU\\\",\\\"start\\\":\\\"08:00\\\",\\\"end\\\":\\\"17:00\\\"},{\\\"day_of_week\\\":\\\"FRI\\\",\\\"start\\\":\\\"08:00\\\",\\\"end\\\":\\\"17:00\\\"},{\\\"day_of_week\\\":\\\"SAT\\\",\\\"start\\\":\\\"08:00\\\",\\\"end\\\":\\\"17:00\\\"},{\\\"day_of_week\\\":\\\"SUN\\\",\\\"start\\\":\\\"08:00\\\",\\\"end\\\":\\\"17:00\\\"}]}\",\r\n"
				+ "    \"reported_item\": \"USORTBDURNC01R\",\r\n"
				+ "    \"reported_type\": \"Install Base Item\",\r\n"
				+ "    \"callingsystem\": \"NOWIA\"\r\n"
				+ "}";

		
		
	}

public String updateLogReqBodyRTR(String ticketNum) {
	
	return "{\r\n"
			+ "    \"comments\": \"Adding new notes from EM to ATTNOW - dev1\",\r\n"
			+ "    \"contact\": \"nj5695@exo.att.com\",\r\n"
			+ "    \"priority\": \"4\",\r\n"
			+ "    \"case_number\": \""+ticketNum+"\",\r\n"
			+ "    \"contactfirstname\": \"NEENA\",\r\n"
			+ "    \"contactlastname\": \"JETHANI\",\r\n"
			+ "    \"contact_phone\": \"7324200703\",\r\n"
			+ "    \"follow_up\": \"2021-11-29 15:57:12\",\r\n"
			+ "    \"callingsystem\": \"NOWIA\"\r\n"
			+ "}";

	}


public String caseEscalationReqBodyRTR(String ticketNum) {
	
	return "{\r\n"
			+ "  \"comments\" : \"*****Customer Requests Escalation of Ticket *****\\nEscalation Level: 1\\nEscalation DeadLine: Monday, 29 Nov 2021 16:32:08 UTC\\nEscalating a case from EM- Dev1\",\r\n"
			+ "  \"contact\" : \"nj5695@exo.att.com\",\r\n"
			+ "  \"priority\" : \"4\",\r\n"
			+ "  \"case_number\" : \""+ticketNum+"\",\r\n"
			+ "  \"contactfirstname\" : \"NEENA\",\r\n"
			+ "  \"contactlastname\" : \"JETHANI\",\r\n"
			+ "  \"contact_phone\" : \"7324200703\",\r\n"
			+ "  \"follow_up\" : \"2021-11-29 15:32:08\",\r\n"
			+ "  \"callingsystem\" : \"NOWIA\"\r\n"
			+ "}";



}


public String closeReqBodyRTR(String ticketNum) {
	
	return "{\r\n"
			+ "    \"comments\": \"\\\"Important : The Customer requested to close this ticket. Please review whether there is an associated ticket or not. If not, close this ticket.\\\"Closing the case from EM - 11292021-dev1\",\r\n"
			+ "    \"contact\": \"nj5695@exo.att.com\",\r\n"
			+ "    \"priority\": \"4\",\r\n"
			+ "    \"case_number\": \""+ticketNum+"\",\r\n"
			+ "    \"contactfirstname\": \"NEENA\",\r\n"
			+ "    \"contactlastname\": \"JETHANI\",\r\n"
			+ "    \"contact_phone\": \"7324200703\",\r\n"
			+ "    \"follow_up\": \"2021-11-29 16:08:39\",\r\n"
			+ "    \"callingsystem\": \"NOWIA\"\r\n"
			+ "}";



}
}
