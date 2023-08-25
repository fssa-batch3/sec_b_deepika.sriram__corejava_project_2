package in.fssa.carecentral.interfaces;

import java.util.Set;

import in.fssa.carecentral.dto.AppointmentDTO;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.Appointment;

public interface AppointmentInterface {
	public abstract void createAppointment(Appointment  app , int doctorId , int userId) throws  ValidationException;
	public abstract Set<AppointmentDTO> findAllAppointmentByDoctorId(int doctorId);
	public abstract Set<AppointmentDTO> findAllAppointmentByUserId(int userId);
	public abstract void updateAppointmentStatus(int appId , AppointmentDTO appointment);
	public abstract AppointmentDTO findAppointmentByAppointmentId(int appId);
	public abstract Set<AppointmentDTO> findAllAppointmentByStatus(String status);
}
