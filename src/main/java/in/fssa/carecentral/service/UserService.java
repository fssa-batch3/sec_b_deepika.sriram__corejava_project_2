package in.fssa.carecentral.service;

import java.util.Set;

import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.User;
import in.fssa.carecentral.validator.UserValidator;
import in.fssa.carecentral.dao.*;
import in.fssa.carecentral.dto.DoctorDTO;



public class UserService {
	/**
	 * 
	 * @param newUser
	 * @return user_id
	 * @throws ValidationException
	 */
	public int create(User newUser) throws ValidationException{
		
		UserValidator.validateForCreate(newUser);
		
		UserDAO userObj = new UserDAO();
		int user_id = userObj.create(newUser);
		return user_id;
		
		
	}
	
	/**
	 * 
	 * @return userArray
	 */
	
	public Set<User> getAll() {
		UserDAO userObj = new UserDAO();
		Set<User> userArray = userObj.findAll();
		return userArray;
	}
	
	
	
	/**
	 * 
	 * @param id
	 * @param newUser
	 * @throws ValidationException
	 */
	public static void update(int id , User  newUser) throws ValidationException {
		UserValidator.validateForUpdate(id , newUser);
		UserDAO userObj = new UserDAO();
		
		 userObj.update(id, newUser);
	}
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public void delete(int id) throws ValidationException {
		UserValidator.validateForId(id);
		UserDAO userObj = new UserDAO();
		userObj.delete(id);
	}
	
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public  static void reactivate(int id) throws ValidationException {
		UserDAO userObj = new UserDAO();
		userObj.reactivate(id);
	}
	
	
	/**
	 * 
	 * @param userId
	 * @return User
	 * @throws ValidationException
	 */
	public static User getById(int userId) throws ValidationException {
		UserValidator.validateForId(userId);
		UserDAO userObj = new UserDAO();
		return userObj.findById(userId);
		
	}
	
	
	/**
	 * 
	 * @param email
	 * @return User
	 * @throws ValidationException
	 */
	public static User getByEmail(String email) throws ValidationException{
		UserValidator.validateForEmail(email);
		UserDAO ud = new UserDAO();
		return ud.findByEmail(email);
	}
}
