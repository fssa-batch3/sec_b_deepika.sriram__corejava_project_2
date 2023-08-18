package in.fssa.carecentral.testCreate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.carecentral.dto.DoctorDTO;
import in.fssa.carecentral.enumFiles.Gender;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.service.DoctorService;

public class TestCreateDoctor {

	@Test
	public void testCreateDoctorWithValidData() {
		DoctorService ds = new DoctorService();
		
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Prema");
		ddto.setLastName("Balachandran");
		ddto.setAge(45);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(7345872311l);
		ddto.setEmailId("prema123@gmail.com");
		ddto.setPassword("PrEmA@#@#1234");
		ddto.setQualifications("MBBS , MS-Obstetrics and gynecologist");
		ddto.setExperience(10);
		ddto.setDepartment("Obstetrician and Gynecologist");
		
		assertDoesNotThrow(()->{
			ds.create(ddto);
		});
	}
	
	@Test
	public void testCreateDoctorWithInvalidData() {
		DoctorService ds = new DoctorService();
		
		DoctorDTO ddto = null;
		Exception ex = assertThrows(ValidationException.class,() ->{
			ds.create(ddto);
		});
		String m1 = "Doctor cannot be null";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithFirstNameNull() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		
		ddto.setFirstName(null);
		ddto.setLastName("Ramamoorthy");
		ddto.setAge(48);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(8967454501l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "first name cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	
	@Test
	public void testCreateDoctorWithFirstNameEmpty() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		
		ddto.setFirstName("");
		ddto.setLastName("Ramamoorthy");
		ddto.setAge(48);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(8967454501l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "first name cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithLastNameNull() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		
		ddto.setFirstName("Nihya");
		ddto.setLastName(null);
		ddto.setAge(48);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(8967454501l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "last name cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithLastNameEmpty() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		
		ddto.setFirstName("Nihya");
		ddto.setLastName("");
		ddto.setAge(48);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(8967454501l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "last name cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithNegativeAge() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(-23);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(8967454501l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "age cannot be negative";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithInvalidAge() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(16);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(8967454501l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "age must be atleast greater than or equal to 18";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithInvalidMobileNumber() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(0);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "invalid mobile number";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithIncorrectMobileNumber() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(0);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "invalid mobile number";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithEmailNull() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(9034121267l);
		ddto.setEmailId(null);
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "email cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithEmailEmpty() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(9034121267l);
		ddto.setEmailId("");
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "email cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithIncorrectEmail() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(9034121267l);
		ddto.setEmailId("NiTHY$%$.com");
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "email doesn't match the required format";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithPasswordNull() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(9034121267l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword(null);
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "password cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithPasswordEmpty() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(9034121267l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "password cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithIncorrectPassword() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(9034121267l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("1234567890");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "Password doesn't match the required format";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
		
	}
	
	@Test
	public void testCreateDoctorWithQualificationNull() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(9034121267l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("Nithya@1234");
		ddto.setQualifications(null);
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "qualifications cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithQualificationEmpty() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(9034121267l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("Nithya@1234");
		ddto.setQualifications("");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "qualifications cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithDepartmentNull() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(9034121267l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("Nithya@1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment(null);
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "department cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithDepartmentEmpty() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(9034121267l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("Nithya@1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "department cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithInvalidExperience() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(9034121267l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("Nithya@1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(0);
		ddto.setDepartment("General Medicine");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "Invalid yrs of experience";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testCreateDoctorWithIncorrectExperience() {
		DoctorService ds = new DoctorService();
		DoctorDTO ddto = new DoctorDTO();
		ddto.setFirstName("Nihya");
		ddto.setLastName("Ramamorthy");
		ddto.setAge(44);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(9034121267l);
		ddto.setEmailId("nithya@gmail.com");
		ddto.setPassword("Nithya@1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(5);
		ddto.setDepartment("General Medicine");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.create(ddto);
		});
		
		String m1 = "Doctor should have atleast 10 yrs of experience";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
}
