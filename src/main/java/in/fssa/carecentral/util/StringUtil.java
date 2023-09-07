package in.fssa.carecentral.util;

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
	
	public static void rejectIfInvalidInteger(int input , String inputName) throws ValidationException {
		if(input<=0) {
			throw new ValidationException(inputName.concat(" cannot be negative"));
		}
	}
}
