package in.fssa.carecentral.app;

import java.util.HashSet;
import in.fssa.carecentral.model.*;
import java.util.Set;

import in.fssa.carecentral.service.*;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.dto.*;
import in.fssa.carecentral.enumFiles.Gender;

public class App {

	public static void main(String[] args) throws ValidationException {
		
//			UserService userService = new UserService();

//			User newUser = new User();
//			newUser.setFirstName("Deepika");
//			newUser.setLastName("Sriram");
//			newUser.setAge(20);
//			newUser.setGender(Gender.F);
//			newUser.setMobileNumber(7397314532l);
//			newUser.setEmailId("deepika@gmail.com");
//			newUser.setPassword("Strong@123Asdf123");
//			userService.create(newUser);
//			System.out.println(userService.getAll());
			
//			userService.delete(6);
			DoctorService ds = new DoctorService();
//			DoctorDTO dc = new DoctorDTO();
//			
//			dc.setFirstName("Vani");
//			dc.setLastName("Narayanan");
//			dc.setAge(56);
//			dc.setGender(Gender.F);
//			dc.setMobileNumber(9023454512l);
//			dc.setEmailId("vani1967@gmail.com");
//			dc.setPassword("Vani*%*%123456");
//			dc.setQualifications("MBBS");
//			dc.setExperience(28);
//			dc.setDepartment("General Physician");
//			ds.create(dc);
		
//			Doctor d = new Doctor();
//			d.setFirstName("Vani");
//			d.setLastName("Narayanan");
//			d.setAge(67);
//			d.setGender(Gender.F);
//			d.setMobileNumber(9876543210l);
//			d.setPassword("aAsdf***1234");
//			d.setQualifications("MBBS , MS-Obstetrics and Gynecology");
//			d.setExperience(30);
//			d.setDepartment("Gynecologist");
//			ds.update(5, d);
			
//			System.out.println(ds.getAll());
			System.out.println(ds.getByEmail("vani1967@gmail.com"));
			
			
			
			

		
	}

}
