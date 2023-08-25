package in.fssa.carecentral.testFind;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.service.UserService;

public class TestFindUsers {
	
	@Test
	public void testFindUsers() {
		UserService us = new UserService();
		assertDoesNotThrow(()->{
			System.out.print(us.listAllUser());
		});
	}
	
	@Test
	public void testFindUserByValidId() {
		UserService us = new UserService();
		assertDoesNotThrow(()->{
			us.getUserById(3);
		});
	}
	
//	@Test
//	public void testFindUserByInactiveId() {
//		UserService us = new UserService();
//		Exception ex = assertThrows(ValidationException.class , ()->{
//			us.getById(4);
//		});
//		String m1 = "User doesn't exists";
//		String m2 = ex.getMessage();
//		assertTrue(m1.equals(m2));
//	}
	
	@Test
	public void testFindUserByInvalidId() {
		UserService us = new UserService();
		Exception ex = assertThrows(ValidationException.class , ()->{
			us.getUserById(-90);
		});
		String m1 = "id cannot be negative";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testFindUserWithValidEmail() {
		UserService us = new UserService();
		assertDoesNotThrow(()->{
			us.getUserByEmail("deepika@gmail.com");
		});
	}
	
	@Test
	public void testFindUserWithNullEmail() {
		UserService us = new UserService();
		Exception ex = assertThrows(ValidationException.class,()->{
			us.getUserByEmail(null);
		});
		String m1 = "email cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
}
