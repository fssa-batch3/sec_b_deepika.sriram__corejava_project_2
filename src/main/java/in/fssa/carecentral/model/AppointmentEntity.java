package in.fssa.carecentral.model;

import in.fssa.carecentral.enumfiles.MethodOfConsultation;
import in.fssa.carecentral.enumfiles.Status;

public abstract class AppointmentEntity {
	private int id;
	private int patientId;
	private int doctorId;
	private String reasonForConsultation;
	private MethodOfConsultation methodOfConsultation;
	private String dateOfConsultation;
	private String dateOfBooking;
	private String startTime;
	private String endTime;
	private Status status;
	private String reasonForRejection;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
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
	public String getDateOfBooking() {
		return dateOfBooking;
	}
	public void setDateOfBooking(String dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getReasonForRejection() {
		return reasonForRejection;
	}
	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}
	@Override
	public String toString() {
		return "AppointmentEntity [id=" + id + ", patientId=" + patientId + ", doctorId=" + doctorId
				+ ", reasonForConsultation=" + reasonForConsultation + ", methodOfConsultation=" + methodOfConsultation
				+ ", dateOfConsultation=" + dateOfConsultation + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", status=" + status + ", reasonForRejection=" + reasonForRejection + "]";
	}
	
	
}
