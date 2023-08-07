package in.fssa.carecentral.app;

import java.util.HashSet;
import java.util.Set;

import in.fssa.carecentral.service.*;
import in.fssa.carecentral.enumFiles.Gender;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.*;

public class App {

	public static void main(String[] args) {
		
			UserService userService = new UserService();

//			User newUser = new User();
//			newUser.setFirstName("Deepika");
//			newUser.setLastName("Sriram");
//			newUser.setAge(20);
//			newUser.setGender(Gender.F);
//			newUser.setMobileNumber(7397314532l);
//			newUser.setEmailId("deepika@gmail.com");
//			newUser.setPassword("Strong@123Asdf123");
//			userService.create(newUser);
//			userService.getAll();
//
//			User newUser2 = new User();
//			newUser2.setFirstName("Vaishnavi");
//			newUser2.setLastName("Sriram");
//			newUser.setAge(18);
//			newUser.setGender(Gender.F);
//			newUser.setMobileNumber(9876121345l);
//			newUser2.setEmailId("vaishu@gmail.com");
//			newUser2.setPassword("Strong@123Asdf123");
//
//			userService.create(newUser2);
			System.out.println(userService.getAll());

//			Set<User> users = new HashSet<User>();
//			users.add(newUser);
//			users.add(newUser2);

//			System.out.println(users);

		
	}

}
