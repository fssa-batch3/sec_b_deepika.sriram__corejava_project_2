package in.fssa.carecentral.service;

import java.util.Set;

import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.User;
import in.fssa.carecentral.validator.UserValidator;
import in.fssa.carecentral.dao.*;



public class UserService {
public void create(User newUser) throws ValidationException{
		
		UserValidator.validate(newUser);
		
		UserDAO userObj = new UserDAO();
		userObj.create(newUser);
		
		
	}
	
	public Set<User> getAll() {
		UserDAO userObj = new UserDAO();
		Set<User> userArray = userObj.findAll();
		return userArray;
	}
	
	
	public void update(int id , User  newUser) throws ValidationException {
		UserValidator.validate(newUser);
		UserDAO userObj = new UserDAO();
		userObj.update(12, newUser);
	}
	
	
	public void delete(int id) {
		User newUser4 = new User();
		newUser4.setActive(false);
		
		UserDAO userObj = new UserDAO();
		userObj.delete(12);
	}
	
	public User getById(int userId) {
		UserDAO userObj = new UserDAO();
		return userObj.findById(userId);
		
	}
}
