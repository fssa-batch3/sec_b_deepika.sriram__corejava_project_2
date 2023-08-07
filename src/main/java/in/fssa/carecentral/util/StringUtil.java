package in.fssa.carecentral.util;

import in.fssa.carecentral.exception.ValidationException;

public class StringUtil {
	public static void rejectIfInvalidString(String input , String inputName) throws ValidationException{
		if(input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
	}
}
