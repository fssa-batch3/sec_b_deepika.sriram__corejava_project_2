package in.fssa.carecentral.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.carecentral.exception.ValidationException;

public class DateUtil {
	
	public static void rejectIfInvalidDate(String date , String inputName) throws ValidationException {
		StringUtil.rejectIfInvalidString(date, inputName);
		
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		Matcher matcher = pattern.matcher(date);
		if(matcher.matches()==false) {
			throw new ValidationException(inputName.concat(" should be in the format of 'yyyy-MM-dd'"));
		}
	}
	
}
