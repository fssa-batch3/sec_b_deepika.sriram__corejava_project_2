package in.fssa.carecentral.testUpdate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import in.fssa.carecentral.enumfiles.Status;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.Appointment;
import in.fssa.carecentral.service.AppointmentService;

public class TestUpdateAppointment {
	
	@Disabled
	@Test
	public void testUpdateAppointmentWithValidData() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		
		appointment.setStatus(Status.Approved);
		
		
		assertDoesNotThrow(()->{
			appointmentService.updateAppointmentStatusByAppointmentId(2, appointment); 
		});
	}
	 
	@Test 
	public void testUpdateAppointmentWithSameStatusInStatusField() {
		AppointmentService appointmentService = new AppointmentService();
		Appointment appointment = new Appointment();
		
		appointment.setStatus(Status.Cancelled_by_doctor);
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.updateAppointmentStatusByAppointmentId(5, appointment);
		});
		
		String expectedMessage = "status is already in Cancelled_by_doctor";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
}
