package in.fssa.carecentral.testCreate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.carecentral.enumFiles.Gender;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.User;
import in.fssa.carecentral.service.UserService;

public class TestCreateUser {
	
	@Test
	public void testCreateUserWithValidData() {
		UserService userService = new UserService();
		User user = new User();
		
		user.setFirstName("Sujatha");
		user.setLastName("Sriram");
		user.setAge(51);
		user.setGender(Gender.F);
		user.setMobileNumber(7845854167l);
		user.setEmailId("sujathasriram@yahoo.com");
		user.setPassword("SuJaThA@#1972");
		
		assertDoesNotThrow(() ->{
			userService.create(user);
		});
		
	}
	
	@Test
	public void testCreateUserWithInvalidData() {
		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class,() ->{
			userService.create(null);
		});
		String expectedMessage = "User cannot be null";
		String receivedMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(receivedMessage));
	}
	
	@Test
	public void testCreateUserWithFirstNameNull() {
		UserService us = new UserService();
		User user = new User();
		
		user.setFirstName(null);
		user.setLastName("Shriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(9876543210l);
		user.setEmailId("abcd@gmail.com");
		user.setPassword("!@#$1234Deepu");
		
		Exception excp = assertThrows(ValidationException.class , ()->{
			us.create(user);
		});
		
		String m1 = "first name cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateUserWithFirstNameEmpty() {
		UserService us = new UserService();
		User user = new User();
		
		user.setFirstName("");
		user.setLastName("Shriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(9876543210l);
		user.setEmailId("abcd@gmail.com");
		user.setPassword("!@#$1234Deepu");
		
		Exception excp = assertThrows(ValidationException.class , ()->{
			us.create(user);
		});
		
		String m1 = "first name cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateUserWithLastNameNull() {
		UserService us = new UserService();
		User user = new User();
		
		user.setFirstName("Deepika");
		user.setLastName(null);
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(9876543210l);
		user.setEmailId("abcd@gmail.com");
		user.setPassword("!@#$1234Deepu");
		
		Exception excp = assertThrows(ValidationException.class , ()->{
			us.create(user);
		});
		
		String m1 = "last name cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateUserWithLastNameEmpty() {
		UserService us = new UserService();
		User user = new User();
		
		user.setFirstName("Deepika");
		user.setLastName("");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(9876543210l);
		user.setEmailId("abcd@gmail.com");
		user.setPassword("!@#$1234Deepu");
		
		Exception excp = assertThrows(ValidationException.class , ()->{
			us.create(user);
		});
		
		String m1 = "last name cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateUserWithNegativeAge() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(-56);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setEmailId("asdf@gmail.com");
		user.setPassword("Deepu*&*&1234");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.create(user);
		});
		String m1 = "age cannot be negative";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateUserWithInvalidAge() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(12);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setEmailId("asdf@gmail.com");
		user.setPassword("Deepu*&*&1234");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.create(user);
		});
		String m1 = "age must be atleast greater than or equal to 18";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateUserWithInvalidMobileNumber() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(20);
		user.setGender(Gender.F);
		user.setMobileNumber(0);
		user.setEmailId("asdf@gmail.com");
		user.setPassword("Deepu*&*&1234");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.create(user);
		});
		String m1 = "invalid mobile number";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
		
	}
	
	@Test
	public void testCreateUserWithIncorrectMobileNumber() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(20);
		user.setGender(Gender.F);
		user.setMobileNumber(4598214309l);
		user.setEmailId("asdf@gmail.com");
		user.setPassword("Deepu*&*&1234");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.create(user);
		});
		String m1 = "mobile number doesn't match the required format";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateEmailWithEmailNull() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setEmailId(null);
		user.setPassword("Deepu**1234");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.create(user);
		});
		String m1 = "email cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
		
	}
	
	@Test
	public void testCreateUserWithEmailEmpty() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setEmailId("");
		user.setPassword("Deepu**1234");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.create(user);
		});
		String m1 = "email cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateUserWithIncorrectEmail() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setEmailId("deepikA23");
		user.setPassword("Deepu**1234");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.create(user);
		});
		String m1 = "email doesn't match the required format";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateUserWithExistingEmail() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setEmailId("sivavicky@gmail.com");
		user.setPassword("Deepu**1234");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.create(user);
		});
		String m1 = "User already exists";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	
	@Test
	public void testCreateUserWithPasswordNull() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setEmailId("deepika@gmail.com");
		user.setPassword(null);
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.create(user);
		});
		String m1 = "password cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateUserWithPasswordEmpty() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setEmailId("deepika@gmail.com");
		user.setPassword("");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.create(user);
		});
		String m1 = "password cannot be null or empty";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateUserWithIncorrectPassword() {
		UserService us = new UserService();
		User user = new User();
		user.setFirstName("Deepika");
		user.setLastName("Sriram");
		user.setAge(18);
		user.setGender(Gender.F);
		user.setMobileNumber(7397314532l);
		user.setEmailId("deepika@gmail.com");
		user.setPassword("deepika123");
		
		Exception excp = assertThrows(ValidationException.class , () ->{
			us.create(user);
		});
		String m1 = "Password doesn't match the required format";
		String m2 = excp.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	
}
