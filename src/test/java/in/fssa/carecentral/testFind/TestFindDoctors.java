package in.fssa.carecentral.testFind;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.service.DoctorService;

public class TestFindDoctors {

	@Test
	public void testFindDoctors() {
		DoctorService ds = new DoctorService();
		assertDoesNotThrow(()->{
			ds.getAll();
		});
	}
	
	@Test
	public void testFindDoctorByValidDoctorId() {
		DoctorService ds = new DoctorService();
		assertDoesNotThrow(()->{
			ds.getById(12);
		});
	}
	
	@Test
	public void testFindDoctorByInvalidId() {
		DoctorService ds = new DoctorService();
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.getById(-12);
		});
		
		String m1 = "id cannot be negative";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testFindUserWithValidEmail() {
		DoctorService us = new DoctorService();
		assertDoesNotThrow(()->{
			us.getByEmail("vani1967@gmail.com");
		});
	}
	
	@Test
	public void testFindUserWithNullEmail() {
		DoctorService us = new DoctorService();
		Exception ex = assertThrows(ValidationException.class,()->{
			us.getByEmail(null);
		});
		String m1 = "email cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
}
