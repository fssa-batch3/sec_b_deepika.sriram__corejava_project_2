package in.fssa.carecentral.validator;

import in.fssa.carecentral.dao.DoctorDAO;
import in.fssa.carecentral.dto.DoctorDTO;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.Doctor;
import in.fssa.carecentral.util.NumberUtil;
import in.fssa.carecentral.util.StringUtil;

public class DoctorValidator extends UserValidator{
	
	/**
	 * 
	 * @param newdoctor
	 * @param id
	 * @throws ValidationException
	 */
	public static void validate1(Doctor newdoctor, int id) throws ValidationException {
		if(newdoctor == null) {
			throw new ValidationException("Doctor cannot be null");
		}
		NumberUtil.rejectIfInvalidInteger(id, "id");
		
		DoctorDAO doctorDAO = new DoctorDAO();
		Doctor doctor = doctorDAO.findById(id);
		if(doctor == null) {
			throw new ValidationException("Doctor doesn't exists");
		}
		StringUtil.rejectIfInvalidString(newdoctor.getQualifications(), "qualifications");
		
		if(newdoctor.getExperience()<=0) {
			throw new ValidationException("Invalid yrs of experience");
		}
		if(newdoctor.getExperience()<10) {
			throw new ValidationException("Doctor should have atleast 10 yrs of experience");
		}
		StringUtil.rejectIfInvalidString(newdoctor.getDepartment(), "department");
	}
	
	
	
	/**
	 * 
	 * @param newDoctor
	 * @throws ValidationException
	 */
	public static void validate(DoctorDTO newDoctor) throws ValidationException {
		if(newDoctor == null) {
			throw new ValidationException("Doctor cannot be null");
		}
		
		StringUtil.rejectIfInvalidString(newDoctor.getQualifications(), "qualifications");
		
		if(newDoctor.getExperience()<=0) {
			throw new ValidationException("Invalid yrs of experience");
		}
		if(newDoctor.getExperience()<10) {
			throw new ValidationException("Doctor should have atleast 10 yrs of experience");
		}
		StringUtil.rejectIfInvalidString(newDoctor.getDepartment(), "department");
	} 
	
	
	
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void validateForDoctorId(int id) throws ValidationException {
		NumberUtil.rejectIfInvalidInteger(id, "id");
		DoctorDAO doctorDAO = new DoctorDAO();
		Doctor doctor = doctorDAO.findById(id);
		if(doctor == null) {
			throw new ValidationException("Doctor doesn't exists");
		}
	}
}
