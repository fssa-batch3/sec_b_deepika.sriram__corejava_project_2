package in.fssa.carecentral.service;

import in.fssa.carecentral.dto.DoctorDTO;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.model.*;
import in.fssa.carecentral.validator.DoctorValidator;
import java.util.Set;
import in.fssa.carecentral.dao.*;

public class DoctorService {
	
	/**
	 * 
	 * @param newDoctor
	 * @throws ValidationException
	 */
	public void createDoctor(DoctorDTO newDoctor) throws ValidationException {
		UserService userService = new UserService();
		
		
		DoctorValidator.validate(newDoctor);
		User user = new User();
		user.setFirstName(newDoctor.getFirstName());
		user.setLastName(newDoctor.getLastName());
		user.setGender(newDoctor.getGender());
		user.setAge(newDoctor.getAge());
		user.setMobileNumber(newDoctor.getMobileNumber());
		user.setEmailId(newDoctor.getEmailId());
		user.setPassword(newDoctor.getPassword());

		int userId = userService.createUser(user);

		
		DoctorDAO doctorDAO = new DoctorDAO();
		doctorDAO.create(newDoctor, userId);

	}
	
	
	/**
	 * 
	 * @return doctorList
	 * @throws ValidationException
	 */
	public Set<DoctorDTO> listAllDoctor() throws ValidationException {
		DoctorDAO doctorDAO = new DoctorDAO();
		Set<DoctorDTO> doctorList = doctorDAO.findAll();
		return doctorList;
	}
	
	

	/**
	 * 
	 * @param id
	 * @param newDoctor
	 * @throws ValidationException
	 */
	public void updateDoctor(int id, Doctor newDoctor) throws ValidationException {
		DoctorValidator.validate1(newDoctor,id);
		DoctorDAO doctorDAO = new DoctorDAO();
		
		Doctor user1 = doctorDAO.findById(id);
		int userId = user1.getUserId();
		
		User user = new User();
		user.setFirstName(  newDoctor.getFirstName() ); 
		user.setLastName(newDoctor.getLastName());
		user.setAge(newDoctor.getAge());
		user.setGender(newDoctor.getGender());
		user.setMobileNumber(newDoctor.getMobileNumber());
		user.setPassword(newDoctor.getPassword());
		UserService.updateUser(userId , user );

		 Doctor doctor = new Doctor();
		 doctor.setQualifications( newDoctor.getQualifications() );
		 doctor.setExperience(newDoctor.getExperience());
		 doctor.setDepartment(newDoctor.getDepartment());
		 
		doctorDAO.update(id, doctor);
	}
	
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public void deleteDoctor(int id) throws ValidationException {
		DoctorValidator.validateForDoctorId(id);
		DoctorDAO doctorDAO = new DoctorDAO();
		doctorDAO.delete(id);
	}
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public  static void reactivate(int id){
		DoctorDAO doctorDAO = new DoctorDAO();
		doctorDAO.reactivate(id);
	}
	
	/**
	 * 
	 * @param id
	 * @return doctor detail
	 * @throws ValidationException
	 */
	public DoctorDTO getDoctorById(int id) throws ValidationException {
		DoctorValidator.validateForDoctorId(id);
		DoctorDAO doctorDAO = new DoctorDAO();
		return doctorDAO.findDoctorById(id);
		
	}
	
	/**
	 * 
	 * @param email
	 * @return doctor detail
	 * @throws ValidationException
	 */
	public DoctorDTO getDoctorByEmail(String email) throws ValidationException{
		DoctorValidator.validateForEmail(email);
		DoctorDAO doctorDAO = new DoctorDAO();
		return doctorDAO.findDoctorByEmail(email);
	}
	
	/**
	 * 
	 * @param year
	 * @return month
	 */
	public static double convertYearToMonth(double year) {
		double month = year*12;
		return month;
	}
	
	/**
	 * 
	 * @param month
	 * @return year
	 */
	public static double convertMonthToYear(double month) {
		double year = month/12;
		return year;
	}
}
