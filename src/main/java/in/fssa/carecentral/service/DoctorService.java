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
	public void create(DoctorDTO newDoctor) throws ValidationException {
		UserService us = new UserService();
		
		
		DoctorValidator.validate(newDoctor);
		User user = new User();
		user.setFirstName(newDoctor.getFirstName());
		user.setLastName(newDoctor.getLastName());
		user.setGender(newDoctor.getGender());
		user.setAge(newDoctor.getAge());
		user.setMobileNumber(newDoctor.getMobileNumber());
		user.setEmailId(newDoctor.getEmailId());
		user.setPassword(newDoctor.getPassword());

		int user_id = us.create(user);

		
		DoctorDAO dd = new DoctorDAO();
		dd.create(newDoctor, user_id);

	}
	
	
	/**
	 * 
	 * @return doctorList
	 * @throws ValidationException
	 */
	public Set<DoctorDTO> getAll() throws ValidationException {
		DoctorDAO ddao = new DoctorDAO();
		Set<DoctorDTO> doctorList = ddao.findAll();
		return doctorList;
	}
	
	

	/**
	 * 
	 * @param id
	 * @param newDoctor
	 * @throws ValidationException
	 */
	public void update(int id, Doctor newDoctor) throws ValidationException {
		DoctorValidator.validate1(newDoctor,id);
		DoctorDAO ddao = new DoctorDAO();
		
		Doctor user1 = ddao.findById(id);
		int uid = user1.getUserId();
		
		User user = new User();
		user.setFirstName(  newDoctor.getFirstName() ); 
		user.setLastName(newDoctor.getLastName());
		user.setAge(newDoctor.getAge());
		user.setGender(newDoctor.getGender());
		user.setMobileNumber(newDoctor.getMobileNumber());
		user.setPassword(newDoctor.getPassword());
		UserService.update(uid , user );

		 Doctor doc = new Doctor();
		 doc.setQualifications( newDoctor.getQualifications() );
		 doc.setExperience(newDoctor.getExperience());
		 doc.setDepartment(newDoctor.getDepartment());
		 
		ddao.update(id, doc);
	}
	
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public void delete(int id) throws ValidationException {
		DoctorValidator.validateForDoctorId(id);
		DoctorDAO ddao = new DoctorDAO();
		ddao.delete(id);
	}
	
	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public  static void reactivate(int id){
		DoctorDAO docObj = new DoctorDAO();
		docObj.reactivate(id);
	}
	
	/**
	 * 
	 * @param id
	 * @return doctor detail
	 * @throws ValidationException
	 */
	public DoctorDTO getById(int id) throws ValidationException {
		DoctorValidator.validateForDoctorId(id);
		DoctorDAO dd = new DoctorDAO();
		return dd.findDoctorById(id);
		
	}
	
	/**
	 * 
	 * @param email
	 * @return doctor detail
	 * @throws ValidationException
	 */
	public DoctorDTO getByEmail(String email) throws ValidationException{
		DoctorValidator.validateForEmail(email);
		DoctorDAO dd = new DoctorDAO();
		return dd.findDoctorByEmail(email);
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
