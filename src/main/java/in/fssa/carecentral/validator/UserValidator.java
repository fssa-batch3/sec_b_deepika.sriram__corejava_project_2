package in.fssa.carecentral.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.carecentral.dao.UserDAO;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.User;
import in.fssa.carecentral.util.NumberUtil;
import in.fssa.carecentral.util.StringUtil;

public class UserValidator {
	
	/**
	 * 
	 * @param user
	 * @throws ValidationException
	 */
	public static void validateForCreate(User user) throws ValidationException {
		if(user == null) {
			throw new ValidationException("User cannot be null");
		}
//		if(user.getId()<0) {
//			throw new ValidationException("id cannot be negative"); 
//		}
//		if(user.getAge()<0) {
//			throw new ValidationException("age cannot be negative");
//		}
		NumberUtil.rejectIfInvalidInteger(user.getId(), "id");
		NumberUtil.rejectIfInvalidInteger(user.getAge(), "age");
		if(user.getAge()<18) {
			throw new ValidationException("age must be atleast greater than or equal to 18");
		}
		
		UserDAO userDAO = new UserDAO();
		User user1 = userDAO.findByEmail(user.getEmailId());
		if(user1!=null) {
			throw new ValidationException("User already exists");
		}
		
		StringUtil.rejectIfInvalidString(user.getEmailId(), "email");
		StringUtil.rejectIfInvalidString(user.getPassword(), "password");
		StringUtil.rejectIfInvalidString(user.getFirstName(), "first name");
		StringUtil.rejectIfInvalidString(user.getLastName(), "last name");
		
		StringUtil.rejectIfIsNotAlphabetic(user.getFirstName(), "first name");
		StringUtil.rejectIfIsNotAlphabetic(user.getLastName(), "last name");
		
		if(user.getMobileNumber()<=0) {
			throw new ValidationException("invalid mobile number");
		}
		
		if(user.getMobileNumber()<6000000000l || user.getMobileNumber()>9999999999l) {
			throw new ValidationException("mobile number should start from between 6 and 9");
		}
		
		Pattern pattern3 = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
		Matcher matcher3 = pattern3.matcher(user.getEmailId());
		if(matcher3.matches()==false) {
			throw new ValidationException("email must contain lowercase letters followed by '@' and '.'");
		}
		
		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^*&+=])(?=\\S+$).{8,}$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(user.getPassword());

        if (!matcher.matches()) {
            throw new ValidationException("Password should contain the combination of uppercase , lowercase , numbers and symbols");
        }
		
		
	}
	
	
	/**
	 * 
	 * @param id
	 * @param user
	 * @throws ValidationException
	 */
	public static void validateForUpdate(int id,User user) throws ValidationException{
		if(user == null) {
			throw new ValidationException("user cannot be null or empty");
		}
		NumberUtil.rejectIfInvalidInteger(id, "id");
		NumberUtil.rejectIfInvalidInteger(user.getAge(), "age");
		if(user.getAge()<18) {
			throw new ValidationException("age must be atleast greater than or equal to 18");
		}
		
		UserDAO userDAO = new UserDAO();
		User user1 = userDAO.findById(id);
		if(user1==null) {
			throw new ValidationException("User doesn't exists");
		}
		
		StringUtil.rejectIfInvalidString(user.getPassword(), "password");
		StringUtil.rejectIfInvalidString(user.getFirstName(), "first name");
		StringUtil.rejectIfInvalidString(user.getLastName(), "last name");
		
		StringUtil.rejectIfIsNotAlphabetic(user.getFirstName(), "first name");
		StringUtil.rejectIfIsNotAlphabetic(user.getLastName(), "last name");
		
		
		if(user.getMobileNumber()<=0) {
			throw new ValidationException("invalid mobile number");
		}
		if(user.getMobileNumber()<6666666666l || user.getMobileNumber()>9999999999l) {
			throw new ValidationException("mobile number should start from between 6 and 9");
		}
		
		
		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^*&+=])(?=\\S+$).{8,}$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(user.getPassword());

        if (!matcher.matches()) {
            throw new ValidationException("Password should contain the combination of uppercase , lowercase , numbers and symbols");
        }
	}
	
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void validateForId(int id) throws ValidationException {
		NumberUtil.rejectIfInvalidInteger(id, "id");
		UserDAO userDAO = new UserDAO();
		User user1 = userDAO.findById(id);
		if(user1==null) {
			throw new ValidationException("User doesn't exists");
		}
		
	}
	
	
	/**
	 * 
	 * @param email
	 * @throws ValidationException
	 */
	public static void validateForEmail(String email) throws ValidationException{
		StringUtil.rejectIfInvalidString(email, "email");
		
		Pattern pattern1 = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
		Matcher matcher1 = pattern1.matcher(email);
		if(matcher1.matches()==false) {
			throw new ValidationException("email must contain lowercase letters followed by '@' and '.'");
		}
	}
	
	
}
