package in.fssa.carecentral.validator;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.carecentral.dao.DoctorDAO;
import in.fssa.carecentral.dao.UserDAO;
import in.fssa.carecentral.dto.AppointmentDTO;
import in.fssa.carecentral.dto.DoctorDTO;
import in.fssa.carecentral.enumfiles.MethodOfConsultation;
import in.fssa.carecentral.enumfiles.Status;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.Appointment;
import in.fssa.carecentral.model.Doctor;
import in.fssa.carecentral.model.User;
import in.fssa.carecentral.service.AppointmentService;
import in.fssa.carecentral.service.DoctorService;
import in.fssa.carecentral.service.UserService;
import in.fssa.carecentral.util.DateUtil;
import in.fssa.carecentral.util.NumberUtil;
import in.fssa.carecentral.util.StringUtil;

public class AppointmentValidator {
	
	public static boolean validateAppointmentAlreadyExists(LocalTime time , String date) {
		
		boolean isBooked = false;
		LocalTime timing = time;
		List<Map<String,LocalTime>> listOfTimings = AppointmentService.getAllBookedTimingsByDate(date);
		if(listOfTimings.isEmpty()==false) {
			for(Map<String,LocalTime> timeRange : listOfTimings) {
		    	
		    	LocalTime startTime = timeRange.get("start time");
		    	LocalTime endTime = timeRange.get("end time");
		    	
		    	
		    	if(timing.isAfter(startTime) && timing.isBefore(endTime)) {
		    		isBooked = true;
		    		break;
		    	}
		    	if(timing.equals(startTime)) {
		    		isBooked = true;
		    		break;
		    	}
		    }
		}
		
		
		return isBooked;
	}
	
	
	public static void validateForCreateAppointment(Appointment appointment) throws ValidationException{
		
		// appointment
		if(appointment == null) {
			throw new ValidationException("appointment cannot be empty");
		}
		
		// id validation
		NumberUtil.rejectIfInvalidInteger(appointment.getDoctorId(), "doctor id");
		NumberUtil.rejectIfInvalidInteger(appointment.getPatientId(), "patient id");
		
		
		// validation for existence of doctor and user
		DoctorDAO doctorDAO = new DoctorDAO();
		Doctor doctor = doctorDAO.findById(appointment.getDoctorId());
		if(doctor==null) {
			throw new ValidationException("no doctor exists in the given doctor id");
		}
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findById(appointment.getPatientId());
		if(user==null) {
			throw new ValidationException("no user exists in the  given user id");
		}
		
		// reason for consultation validation
		StringUtil.rejectIfInvalidString(appointment.getReasonForConsultation(), "reason for consultation");
		Pattern sentencePattern = Pattern.compile("^[a-zA-Z0-9,\\.\\s]+$");
		Matcher sentenceMatcher  = sentencePattern.matcher(appointment.getReasonForConsultation());
		if(sentenceMatcher.matches()==false) {
			throw new ValidationException("reason should not include any special symbols except ',' and '.'");
		}
		
		// date of consultation validation
		DateUtil.rejectIfInvalidDate(appointment.getDateOfConsultation(), "date");
	    
	    LocalDate date = LocalDate.parse(appointment.getDateOfConsultation());
	    
	    DayOfWeek dateOfWeek = date.getDayOfWeek();
	    String week = dateOfWeek.toString();
	    if(week.equals("SATURDAY") || week.equals("SUNDAY")) {
	    	throw new ValidationException("you cannot book the appointment for saturday and sunday");
	    }
	    
	    
	    LocalDate currentDate = LocalDate.now();
	    if(date.isBefore(currentDate)) {
	    	throw new ValidationException("consultation date is before the today's date");
	    }
	    
	    
	    // start time validation 
	    if(appointment.getStartTime()==null || "".equals(appointment.getStartTime())) {
	    	throw new ValidationException("start time should not be empty");
	    }
	    
	    
	    //validation for appointment booking before default timing
	    LocalTime startingTime = LocalTime.parse(appointment.getStartTime());
	    if(startingTime.isBefore(LocalTime.of(9, 00, 00))|| startingTime.isAfter(LocalTime.of(20, 00, 00))) {
	    	throw new ValidationException("you cannot book the appointment for before 9 am or after 8 pm");
	    }
	    
	    
	    
	    // validation for appointment already exists in this time 
	     boolean exists = validateAppointmentAlreadyExists(startingTime , appointment.getDateOfConsultation());
	     if(exists == true) {
	    	 throw new ValidationException("appointment already exists in this timings");
	     }
//	     boolean endTimeCheck = validateAppointmentAlreadyExists(appointment.getEndTime(),appointment.getDateOfConsultation());
//	     if(endTimeCheck = true) {
//	    	 throw new ValidationException("plz enter the end time which should be less than 1 hr or exactly 1 hr from the start time of the consultation");
//	     }
	     
	     
	    
	    //end time validation
	    if(appointment.getEndTime()==null || "".equals(appointment.getEndTime())) {
	    	throw new ValidationException("end time should not be empty");
	    }
	    
	    LocalTime endingTime = LocalTime.parse(appointment.getEndTime());
//	    
//	    Duration duration = Duration.between(startingTime, endingTime);
//        long hoursDifference = duration.toHours();
//        if(hoursDifference>1) {
//        	throw new ValidationException("you can only book appointment for 1 hr");
//        }
        
//	    boolean endTimeExists = validateAppointmentAlreadyExists(endingTime,appointment.getDateOfConsultation());
//	    if(endTimeExists == true) {
//	    	throw new ValidationException("appointment already exists in this timings");
//	    }
	    
	    // minimum appointments validation
	    int count = AppointmentService.getCountOfAppointmentsByDateAndDoctorId(appointment.getDoctorId(), appointment.getDateOfConsultation());
	    System.out.println(count);
	    if(count>=5) {
	    	appointment.setStatus(Status.Waiting_list);
	    }else {
	    	appointment.setStatus(Status.Booked);
	    }
	    
	    
	}
	
	public static void validateExistenceOfDoctor(int doctorId) throws ValidationException{
		NumberUtil.rejectIfInvalidInteger(doctorId, "doctor id");
		try {
			DoctorService.getDoctorById(doctorId);
		}catch(ValidationException e) {
			throw new ValidationException(e.getMessage());
		}
		
	}
	
	public static void validateExistenceOfUser(int userId) throws ValidationException {
		NumberUtil.rejectIfInvalidInteger(userId, "user id");
		try {
			UserService.getUserById(userId);
		}catch(ValidationException e) {
			throw new ValidationException(e.getMessage());
		}
	}
	
	public static void validateForUpdate(int id , Appointment appointment) throws ValidationException {
		if(appointment==null) {
			throw new ValidationException("appointment cannot be empty");
		}
		NumberUtil.rejectIfInvalidInteger(id, "appointment id");
		AppointmentDTO app = AppointmentService.getAppointmentByAppointmentId(id);
		
		if(app==null) {
			throw new ValidationException("no appointment exist to update");
		}
		
		String status = app.getStatus().name();  
		if(status.equals(appointment.getStatus().name())){
			throw new ValidationException("status is already in ".concat(status));
		}
		
//		Pattern pattern = Pattern.compile("^[a-zA-Z0-9,\\.\\s]+$");
//		Matcher matcher =  pattern.matcher(appointment.getReasonForRejection());
//		if(matcher.matches()==false) {
//			throw new ValidationException("reason should not include any special symbols");
//		}
	}
}
