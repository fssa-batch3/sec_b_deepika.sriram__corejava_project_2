package in.fssa.carecentral.testFind;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.service.AppointmentService;

public class TestFindAppointments {

	
	@Test
	public void testFindAppointmentWithValidDoctorId() {
		AppointmentService appointmentService = new AppointmentService();
		assertDoesNotThrow(()->{
			System.out.println(appointmentService.getAllAppointmentsByDoctorId(2));
		});
	}
	
	@Test 
	public void testFindAppointmentWithValidPatientId() {
		AppointmentService appointmentService = new AppointmentService();
		assertDoesNotThrow(()->{
			System.out.println(appointmentService.getAllAppointmentsByUserId(3));
		});
	}
	
	@Test
	public void testFindAppointmentByValidAppointmentId() {
		AppointmentService appointmentService = new AppointmentService();
		assertDoesNotThrow(()->{
			appointmentService.getAppointmentByAppointmentId(4);
		});
	}
	
	@Test
	public void testFindAppointmentByInvalidDoctorId() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.getAllAppointmentsByDoctorId(0);
		});
		String expectedMessage = "Doctor doesn't exists";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Disabled
	@Test
	public void testFindAppointmentByInvalidUserId() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.getAllAppointmentsByUserId(20);
		});
		String expectedMessage = "User doesn't exists";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindAppointmentByInvalidAppointmentId() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.getAppointmentByAppointmentId(16);
		});
		String expectedMessage = "Appointment doesn't exists";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Disabled
	@Test
	public void testFindCountOfAppointmentsByInvalidDoctorId() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.getCountOfAppointmentsByDateAndDoctorId(0, "2023-09-18");
		});
		
		String expectedMessage = "doctor id cannot be negative";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindCountOfAppointmentsByNullDate() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.getCountOfAppointmentsByDateAndDoctorId(2, null);
		});
		
		String expectedMessage = "date of consultation cannot be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindCountOfAppointmentsByEmptyDate() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.getCountOfAppointmentsByDateAndDoctorId(2, "");
		});
		
		String expectedMessage = "date of consultation cannot be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Disabled
	@Test
	public void testFindCountOfAppointmentsByIncorrectPatternOfDate() {
		AppointmentService appointmentService = new AppointmentService();
		Exception exception = assertThrows(ValidationException.class,()->{
			appointmentService.getCountOfAppointmentsByDateAndDoctorId(2, "12-09-2023");
		});
		
		String expectedMessage = "date should be in the format of 'yyyy-MM-dd'";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
}
