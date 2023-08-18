package in.fssa.carecentral.testDelete;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.service.DoctorService;

public class TestDeleteDoctor {
	
	
	@Test
	public void testDeleteDoctorWithValidId() {
		DoctorService ds = new DoctorService();
		assertDoesNotThrow(()->{
			ds.delete(3);
		});
	}
	
	@Test
	public void testDeleteDoctorWithInvalidId() {
		DoctorService ds = new DoctorService();
		Exception excp = assertThrows(ValidationException.class , ()->{
			ds.delete(-45);
		});
		String m1 = "id cannot be negative";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
}
