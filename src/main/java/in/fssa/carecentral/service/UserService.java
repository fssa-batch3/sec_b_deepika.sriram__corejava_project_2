package in.fssa.carecentral.service;

import java.util.Set;

import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.User;
import in.fssa.carecentral.validator.UserValidator;
import in.fssa.carecentral.dao.*;



public class UserService {
	/**
	 * 
	 * @param newUser
	 * @return userId
	 * @throws ValidationException
	 */
	public  int createUser(User newUser) throws ValidationException{
		
		UserValidator.validateForCreate(newUser);
		
		UserDAO userDAO = new UserDAO();
		int userId = userDAO.create(newUser);
		return userId;
		
		
	}
	
	/**
	 * 
	 * @return users
	 */
	
	public Set<User> listAllUser() {
		UserDAO userDAO = new UserDAO();
		Set<User> users = userDAO.findAll();
		return users;
	}
	
	
	
	/**
	 * 
	 * @param id
	 * @param newUser
	 * @throws ValidationException
	 */
	public static void updateUser(int id , User  newUser) throws ValidationException {
		UserValidator.validateForUpdate(id , newUser);
		UserDAO userDAO = new UserDAO();
		
		 userDAO.update(id, newUser);
	}
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public void deleteUser(int id) throws ValidationException {
		UserValidator.validateForId(id);
		UserDAO userDAO = new UserDAO();
		userDAO.delete(id);
	}
	
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public  static void reactivate(int id) throws ValidationException {
		UserDAO userDAO = new UserDAO();
		userDAO.reactivate(id);
	}
	
	
	/**
	 * 
	 * @param userId
	 * @return User
	 * @throws ValidationException
	 */
	public static User getUserById(int userId) throws ValidationException {
		UserValidator.validateForId(userId);
		UserDAO userDAO = new UserDAO();
		return userDAO.findById(userId);
		
	}
	
	
	/**
	 * 
	 * @param email
	 * @return User
	 * @throws ValidationException
	 */
	public static User getUserByEmail(String email) throws ValidationException{
		UserValidator.validateForEmail(email);
		UserDAO userDAO = new UserDAO();
		return userDAO.findByEmail(email);
	}
}
