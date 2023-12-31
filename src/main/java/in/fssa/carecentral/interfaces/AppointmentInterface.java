package in.fssa.carecentral.interfaces;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import in.fssa.carecentral.dto.AppointmentDTO;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.Appointment;

public interface AppointmentInterface {
	
	public abstract Set<AppointmentDTO> findAllAppointmentByDoctorId(int doctorId);
	public abstract Set<AppointmentDTO> findAllAppointmentByUserId(int userId);
	public abstract void updateAppointmentStatus(int appId , Appointment appointment);
	public abstract AppointmentDTO findAppointmentByAppointmentId(int appId);
	public abstract List<Map<String,LocalTime>>findAllBookedTimingsByDate(String date);
	public abstract void createAppointment(Appointment app) throws ValidationException;
	public abstract int countOfAppointmentsByDateAndDoctorId(int doctorId, String date);
	public abstract List<Appointment> findWaitingListAppointmentsByDoctorIdAndDate(int doctorId , String date);
	public abstract void changeWaitingListToBooked(int appointmentId);
}
