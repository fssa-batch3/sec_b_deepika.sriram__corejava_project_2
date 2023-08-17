package in.fssa.carecentral.model;

import in.fssa.carecentral.enumFiles.Gender;
import in.fssa.carecentral.enumFiles.MethodOfConsultation;
import in.fssa.carecentral.enumFiles.Status;

public abstract class AppointmentEntity {
	private int id;
	private int userId;
	private int doctorId;
	private String patientName;
	private int age;
	private Gender gender;
	private long mobileNumber;
	private String reasonForConsultation;
	private MethodOfConsultation methodOfConsultation;
	private String dateOfConsultation;
	private Status status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	
	
	
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
	
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
	
	public String getReasonForConsultation() {
		return reasonForConsultation;
	}
	public void setReasonForConsultation(String reasonForConsultation) {
		this.reasonForConsultation = reasonForConsultation;
	}
	
	
	
	public MethodOfConsultation getMethodOfConsultation() {
		return methodOfConsultation;
	}
	public void setMethodOfConsultation(MethodOfConsultation methodOfConsultation) {
		this.methodOfConsultation = methodOfConsultation;
	}
	
	
	
	public String getDateOfConsultation() {
		return dateOfConsultation;
	}
	public void setDateOfConsultation(String dateOfConsultation) {
		this.dateOfConsultation = dateOfConsultation;
	}
	
	
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
