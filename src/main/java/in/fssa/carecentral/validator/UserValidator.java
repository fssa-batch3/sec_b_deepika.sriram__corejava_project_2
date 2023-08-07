package in.fssa.carecentral.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.User;
import in.fssa.carecentral.util.StringUtil;

public class UserValidator {
	public static void validate(User user) throws ValidationException {
		if(user == null) {
			throw new ValidationException("user cannot be null or empty");
		}
		if(user.getUserId()<0) {
			throw new ValidationException("id cannot be negative");
		}
		if(user.getAge()<18) {
			throw new ValidationException("age must be atleast greater than or equal to 18");
		}
		StringUtil.rejectIfInvalidString(user.getEmailId(), "email");
		StringUtil.rejectIfInvalidString(user.getPassword(), "password");
		StringUtil.rejectIfInvalidString(user.getFirstName(), "first name");
		StringUtil.rejectIfInvalidString(user.getLastName(), "last name");
		
		if(user.getMobileNumber()<6666666666l || user.getMobileNumber()>9999999999l) {
			throw new ValidationException("mobile number doesn't match the required format");
		}
		
		Pattern ptn2 = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
		Matcher mtch2 = ptn2.matcher(user.getEmailId());
		if(mtch2.matches()==false) {
			throw new ValidationException("email doesn't match the required format");
		}
		
		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^*&+=])(?=\\S+$).{16,}$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(user.getPassword());

        if (!matcher.matches()) {
            throw new ValidationException("Password doesn't match the required format");
        }
		
		
	}
}
