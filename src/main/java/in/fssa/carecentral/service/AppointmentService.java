package in.fssa.carecentral.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import in.fssa.carecentral.dao.AppointmentDAO;
import in.fssa.carecentral.dto.AppointmentDTO;
import in.fssa.carecentral.enumfiles.MethodOfConsultation;
import in.fssa.carecentral.enumfiles.Status;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.Appointment;
import in.fssa.carecentral.util.StringUtil;
import in.fssa.carecentral.validator.AppointmentValidator;

public class AppointmentService {
	
	public static LocalDate convertStringToDate(String date) throws ParseException {
		String dateString = date;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate parsedDate = LocalDate.parse(dateString , formatter);
		
		return parsedDate;
	}
	
	public static LocalTime convertStringToTime(String time) {
		
		String timeString = time;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Define the pattern
        LocalTime localTime = LocalTime.parse(timeString, formatter); 
		return localTime;
		
	}
	
	public static String convertLocalDateToString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        return formattedDate;
	}
	
	public static String convertLocalTimeToString(LocalTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
		String formattedTime = time.format(formatter);
		return formattedTime;
	}
	

	public static  List<Map<String,LocalTime>> getAllBookedTimingsByDate(String date){
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		System.out.println(appointmentDAO.findAllBookedTimingsByDate(date));
		return appointmentDAO.findAllBookedTimingsByDate(date);
	}
	
	public void create(Appointment appointment) throws ValidationException {
		AppointmentValidator.validateForCreateAppointment(appointment);
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		appointmentDAO.createAppointment(appointment);
	}
	
	public  Set<AppointmentDTO> getAllAppointmentsByDoctorId(int doctorId) throws ValidationException{
		AppointmentValidator.validateExistenceOfDoctor(doctorId);
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		return appointmentDAO.findAllAppointmentByDoctorId(doctorId);
	}
	
	public  Set<AppointmentDTO> getAllAppointmentsByUserId(int userId) throws ValidationException{
		AppointmentValidator.validateExistenceOfUser(userId);
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		return appointmentDAO.findAllAppointmentByUserId(userId); 
	}
	
	public  void updateAppointmentStatusByAppointmentId(int id , Appointment appointment) throws ValidationException {
		try {
			AppointmentValidator.validateForUpdate(id, appointment);
		} catch (ValidationException e) {
			throw new ValidationException(e.getMessage());
		}
		AppointmentDAO appointmentDAO =  new AppointmentDAO();
		appointmentDAO.updateAppointmentStatus(id, appointment);
	}
	
	public static AppointmentDTO getAppointmentByAppointmentId(int id) throws ValidationException {
		AppointmentDAO appointmentDAO =  new AppointmentDAO();
		StringUtil.rejectIfInvalidInteger(id, "id");
		try {
			return appointmentDAO.findAppointmentByAppointmentId(id);
		}catch(RuntimeException e) {
			throw new ValidationException(e.getMessage());
		}
		
	}
	
}
