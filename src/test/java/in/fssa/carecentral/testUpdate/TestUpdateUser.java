package in.fssa.carecentral.testUpdate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.carecentral.enumFiles.Gender;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.User;
import in.fssa.carecentral.service.UserService;


public class TestUpdateUser {
	
	@Test
	public void testUpdateUserWithValidData() {
		UserService userService = new UserService();
		User user = new User();
		
		user.setFirstName("Praveen kumar");
		user.setLastName("Sekar");
		user.setAge(18);
		user.setGender(Gender.M);
		user.setMobileNumber(9876543210l);
		user.setPassword("Praveen@2004");
		
		assertDoesNotThrow(() ->{
			userService.update(27, user);
			
			
		});
	}
	
	@Test
	public void testUpdateUserWithInvalidId() {
		UserService userService = new UserService();
		User user = new User();
		
		user.setFirstName("Uthra");
		user.setLastName("Boopathy");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(9876543210l);
		user.setPassword("SuJaThA@#1972");
		
		Exception excp = assertThrows(ValidationException.class , ()->{
			userService.update(12, user);
		});
		
		String m1 = "User doesn't exists";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateUserWithFirstNameNull() {
		UserService us = new UserService();
		User user = new User();
		
		user.setFirstName(null);
		user.setLastName("Shriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(9876543210l);
		user.setPassword("!@#$1234Deepu");
		
		Exception excp = assertThrows(ValidationException.class , ()->{
			us.update(3,user);
		});
		
		String m1 = "first name cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	
	@Test
	public void testUpdateUserWithFirstNameEmpty() {
		UserService us = new UserService();
		User user = new User();
		
		user.setFirstName("");
		user.setLastName("Shriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(9876543210l);
		user.setPassword("!@#$1234Deepu");
		
		Exception excp = assertThrows(ValidationException.class , ()->{
			us.update(3,user);
		});
		
		String m1 = "first name cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateUserWithLastNameNull() {
		UserService us = new UserService();
		User user = new User();
		
		user.setFirstName("Deepika");
		user.setLastName(null);
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(9876543210l);
		user.setPassword("!@#$1234Deepu");
		
		Exception excp = assertThrows(ValidationException.class , ()->{
			us.update(3, user);
		});
		
		String m1 = "last name cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateUserWithLastNameEmpty() {
		UserService us = new UserService();
		User user = new User();
		
		user.setFirstName("Deepika");
		user.setLastName("");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(9876543210l);
		user.setPassword("!@#$1234Deepu");
		
		Exception excp = assertThrows(ValidationException.class , ()->{
			us.update(3, user);
		});
		
		String m1 = "last name cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateUserWithNegativeAge() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(-56);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setPassword("Deepu*&*&1234");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.update(3, user);
		});
		String m1 = "age cannot be negative";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateUserWithInvalidAge() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(12);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setPassword("Deepu*&*&1234");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.update(3, user);
		});
		String m1 = "age must be atleast greater than or equal to 18";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateUserWithInvalidMobileNumber() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(20);
		user.setGender(Gender.F);
		user.setMobileNumber(0);
		user.setPassword("Deepu*&*&1234");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.update(3, user);
		});
		String m1 = "invalid mobile number";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateUserWithIncorrectMobileNumber() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(20);
		user.setGender(Gender.F);
		user.setMobileNumber(4598214309l);
		user.setPassword("Deepu*&*&1234");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.update(3, user);
		});
		String m1 = "mobile number doesn't match the required format";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateUserWithPasswordNull() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setPassword(null);
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.update(3, user);
		});
		String m1 = "password cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateUserWithPasswordEmpty() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setPassword("");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.update(3, user);
		});
		String m1 = "password cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateUserWithIncorrectPassword() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setPassword("deepika123");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.update(3, user);
		});
		String m1 = "Password doesn't match the required format";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	
}
