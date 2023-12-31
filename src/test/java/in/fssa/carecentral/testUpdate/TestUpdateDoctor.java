package in.fssa.carecentral.testUpdate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.carecentral.dto.DoctorDTO;
import in.fssa.carecentral.enumfiles.Gender;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.Doctor;
import in.fssa.carecentral.service.DoctorService;

public class TestUpdateDoctor {
	
	
	@Test
	public void testUpdateDoctorWithValidData() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("Vani");
		d.setLastName("Narayanan");
		d.setAge(54);
		d.setGender(Gender.F);
		d.setMobileNumber(9023454512l);
		d.setPassword("aAsdf***1234");
		d.setQualifications("MBBS , MS-Obstetrics and Gynecology");
		d.setExperience(30);
		d.setDepartment("Gynecologist");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		assertDoesNotThrow(()->{
			ds.updateDoctor(1, d);
		});
		
	}
	
	@Test
	public void testUpdateDoctorWithInvalidData() {
		DoctorService ds = new DoctorService();
		Doctor d = null;
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, d);
		});
		
		String m1 = "Doctor cannot be null";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithInvalidId() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("Vani");
		d.setLastName("Narayanan");
		d.setAge(54);
		d.setGender(Gender.F);
		d.setMobileNumber(9023454512l);
		d.setPassword("aAsdf***1234");
		d.setQualifications("MBBS , MS-Obstetrics and Gynecology");
		d.setExperience(30);
		d.setDepartment("Gynecologist");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(0, d);
		});
		String m1 = "Doctor doesn't exists";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithNegativeId() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("Vani");
		d.setLastName("Narayanan");
		d.setAge(54);
		d.setGender(Gender.F);
		d.setMobileNumber(9023454512l);
		d.setPassword("aAsdf***1234");
		d.setQualifications("MBBS , MS-Obstetrics and Gynecology");
		d.setExperience(30);
		d.setDepartment("Gynecologist");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(-1, d);
		});
		String m1 = "id cannot be negative";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithFirstNameNull() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName(null);
		d.setLastName("Narayanan");
		d.setAge(54);
		d.setGender(Gender.F);
		d.setMobileNumber(9023454512l);
		d.setPassword("aAsdf***1234");
		d.setQualifications("MBBS , MS-Obstetrics and Gynecology");
		d.setExperience(30);
		d.setDepartment("Gynecologist");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, d);
		});
		String m1 = "first name cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithFirstNameEmpty() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("");
		d.setLastName("Narayanan");
		d.setAge(54);
		d.setGender(Gender.F);
		d.setMobileNumber(9023454512l);
		d.setPassword("aAsdf***1234");
		d.setQualifications("MBBS , MS-Obstetrics and Gynecology");
		d.setExperience(30);
		d.setDepartment("Gynecologist");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, d);
		});
		String m1 = "first name cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithImproperFirstName() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("Vani@#@#");
		d.setLastName("Narayanan");
		d.setAge(54);
		d.setGender(Gender.F);
		d.setMobileNumber(9023454512l);
		d.setPassword("aAsdf***1234");
		d.setQualifications("MBBS , MS-Obstetrics and Gynecology");
		d.setExperience(30);
		d.setDepartment("Gynecologist");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, d);
		});
		String m1 = "first name should contain only alphabets not numbers and symbols";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
		
	}
	
	@Test
	public void testUpdateDoctorWithLastNameNull() {
		DoctorService ds = new DoctorService();
		Doctor ddto = new Doctor();
		
		ddto.setFirstName("Nihya");
		ddto.setLastName(null);
		ddto.setAge(48);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(8967454501l);
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/J99xK37.png");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, ddto);
		});
		
		String m1 = "last name cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithLastNameEmpty() {
		DoctorService ds = new DoctorService();
		Doctor ddto = new Doctor();
		
		ddto.setFirstName("Nihya");
		ddto.setLastName("");
		ddto.setAge(48);
		ddto.setGender(Gender.F);
		ddto.setMobileNumber(8967454501l);
		ddto.setPassword("NiThYa*$%1234");
		ddto.setQualifications("MBBS");
		ddto.setExperience(20);
		ddto.setDepartment("General Physician");
		ddto.setDoctorImage("https://iili.io/J99xK37.png");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, ddto);
		});
		
		String m1 = "last name cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithInvalidAge() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("Nithya");
		d.setLastName("Ramamorthy");
		d.setAge(17);
		d.setGender(Gender.F);
		d.setMobileNumber(9078563412l);
		d.setPassword("Nithya***1234");
		d.setQualifications("MBBS");
		d.setExperience(20);
		d.setDepartment("General Physician");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, d);
		});
		String m1 = "age must be atleast greater than or equal to 18";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithInvalidMobileNumber() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("Nithya");
		d.setLastName("Ramamorthy");
		d.setAge(45);
		d.setGender(Gender.F);
		d.setMobileNumber(0);
		d.setPassword("Nithya***1234");
		d.setQualifications("MBBS");
		d.setExperience(20);
		d.setDepartment("General Physician");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, d);
		});
		String m1 = "invalid mobile number";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithIncorrectMobileNumber() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("Nithya");
		d.setLastName("Ramamorthy");
		d.setAge(45);
		d.setGender(Gender.F);
		d.setMobileNumber(3434786905l);
		d.setPassword("Nithya***1234");
		d.setQualifications("MBBS");
		d.setExperience(20);
		d.setDepartment("General Physician");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, d);
		});
		String m1 = "mobile number should start from between 6 and 9";
		String m2 = ex.getMessage();
		System.out.println(m2);
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithQualificationNull() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("Nithya");
		d.setLastName("Ramamorthy");
		d.setAge(45);
		d.setGender(Gender.F);
		d.setMobileNumber(9812123409l);
		d.setPassword("Nithya***1234");
		d.setQualifications(null);
		d.setExperience(20);
		d.setDepartment("General Physician");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, d);
		});
		String m1 = "qualifications cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithQualificationNEmpty() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("Nithya");
		d.setLastName("Ramamorthy");
		d.setAge(45);
		d.setGender(Gender.F);
		d.setMobileNumber(9812123409l);
		d.setPassword("Nithya***1234");
		d.setQualifications("");
		d.setExperience(20);
		d.setDepartment("General Physician");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, d);
		});
		String m1 = "qualifications cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithInvalidExperience() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("Nithya");
		d.setLastName("Ramamorthy");
		d.setAge(45);
		d.setGender(Gender.F);
		d.setMobileNumber(9812123409l);
		d.setPassword("Nithya***1234");
		d.setQualifications("MBBS");
		d.setExperience(0);
		d.setDepartment("General Physician");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, d);
		});
		String m1 = "Invalid yrs of experience";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithIncorrectExperience() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("Nithya");
		d.setLastName("Ramamorthy");
		d.setAge(45);
		d.setGender(Gender.F);
		d.setMobileNumber(9812123409l);
		d.setPassword("Nithya***1234");
		d.setQualifications("MBBS");
		d.setExperience(7);
		d.setDepartment("General Physician");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, d);
		});
		String m1 = "Doctor should have atleast 10 yrs of experience";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithDepartmentNull() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("Nithya");
		d.setLastName("Ramamorthy");
		d.setAge(45);
		d.setGender(Gender.F);
		d.setMobileNumber(9812123409l);
		d.setPassword("Nithya***1234");
		d.setQualifications("MBBS");
		d.setExperience(15);
		d.setDepartment(null);
		d.setDoctorImage("https://iili.io/J99xK37.png");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, d);
		});
		String m1 = "department cannot be null or empty";
		String m2 = ex.getMessage();
		assertTrue(m1.equals(m2));
	}
	
	@Test
	public void testUpdateDoctorWithDepartmentEmpty() {
		DoctorService ds = new DoctorService();
		Doctor d = new Doctor();
		d.setFirstName("Nithya");
		d.setLastName("Ramamorthy");
		d.setAge(45);
		d.setGender(Gender.F);
		d.setMobileNumber(9812123409l);
		d.setPassword("Nithya***1234");
		d.setQualifications("MBBS");
		d.setExperience(15);
		d.setDepartment("");
		d.setDoctorImage("https://iili.io/J99xK37.png");
		
		Exception ex = assertThrows(ValidationException.class , ()->{
			ds.updateDoctor(1, d);
		});
		String m1 = "department cannot be null or empty";
		String m2 = ex.getMessage();
		System.out.println(m2);
		assertTrue(m1.equals(m2));
	}
}
