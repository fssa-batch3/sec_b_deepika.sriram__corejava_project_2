package in.fssa.carecentral.testDelete;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.service.UserService;

public class TestDeleteUser {

	@Test
	public void testDeleteUserWithValidId() {
		UserService us = new UserService();
		assertDoesNotThrow(()->{
			us.delete(2);
		});
	}
	
	@Test
	public void testDeleteUserWithInvalidId() {
		UserService us = new UserService();
		Exception excp = assertThrows(ValidationException.class , ()->{
			us.delete(-45);
		});
		String m1 = "id cannot be negative";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	
	
}
