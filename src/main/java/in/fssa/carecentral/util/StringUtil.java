package in.fssa.carecentral.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.carecentral.exception.ValidationException;

public class StringUtil {
	
	/**
	 * 
	 * @param input
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidString(String input , String inputName) throws ValidationException{
		if(input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
	} 
	
	public static void rejectIfIsNotAlphabetic(String input , String inputName) throws ValidationException {
		Pattern pattern1 = Pattern.compile("^[A-Za-z\\s'-]+$");
		Matcher matcher1 = pattern1.matcher(input);
		if(matcher1.matches()==false) {
			throw new ValidationException(inputName.concat(" should contain only alphabets not numbers and symbols"));
		}
	}
}
