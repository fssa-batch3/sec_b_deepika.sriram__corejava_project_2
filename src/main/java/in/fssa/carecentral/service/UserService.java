package in.fssa.carecentral.service;

import java.util.Set;

import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.User;
import in.fssa.carecentral.validator.UserValidator;
import in.fssa.carecentral.dao.*;
import in.fssa.carecentral.dto.DoctorDTO;



public class UserService {
	public int create(User newUser) throws ValidationException{
		
		UserValidator.validateForCreate(newUser);
		
		UserDAO userObj = new UserDAO();
		int user_id = userObj.create(newUser);
		return user_id;
		
		
	}
	
	public Set<User> getAll() {
		UserDAO userObj = new UserDAO();
		Set<User> userArray = userObj.findAll();
		return userArray;
	}
	
	
	public static void update(int id , User  newUser) throws ValidationException {
		UserValidator.validateForUpdate(id , newUser);
		UserDAO userObj = new UserDAO();
		
		 userObj.update(id, newUser);
	}
	
	
	public void delete(int id) throws ValidationException {
		UserValidator.validateForId(id);
		UserDAO userObj = new UserDAO();
		userObj.delete(id);
	}
	
	public static User getById(int userId) throws ValidationException {
		UserValidator.validateForId(userId);
		UserDAO userObj = new UserDAO();
		return userObj.findById(userId);
		
	}
	
	public static User getByEmail(String email) throws ValidationException{
		UserValidator.validateForEmail(email);
		UserDAO ud = new UserDAO();
		return ud.findByEmail(email);
	}
}
