package in.fssa.carecentral.util;

import in.fssa.carecentral.exception.ValidationException;

public class NumberUtil {

	public static void rejectIfInvalidInteger(int input , String inputName) throws ValidationException {
		if(input<=0) {
			throw new ValidationException(inputName.concat(" cannot be negative"));
		}
	}

}
