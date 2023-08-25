package in.fssa.carecentral.service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppointmentService {
	
	public static Date convertStringToDate(String date) throws ParseException {
		String dateString = date;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = simpleDateFormat.parse(dateString);
		
		return parsedDate;
	}
	
	public static LocalTime convertStringToTime(String time) {
		
		String timeString = time;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Define the pattern
        LocalTime localTime = LocalTime.parse(timeString, formatter); 
		return localTime;
		
	}
}
