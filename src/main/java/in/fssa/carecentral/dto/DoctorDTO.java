package in.fssa.carecentral.dto;

import in.fssa.carecentral.enumfiles.Gender;

public class DoctorDTO {
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private Gender gender;
	private long mobileNumber;
	private String emailId;
	private String password;
	private boolean isActive = true;
	private String qualifications;
	private double experience;
	private String department;
	private boolean isDocActive = true;
	private int userId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer userId) {
		this.id = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender2) {
		this.gender = gender2;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getQualifications() {
		return qualifications;
	}
	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}
	public double getExperience() {
		return experience;
	}
	public void setExperience(double experience) {
		this.experience = experience;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public boolean isDocActive() {
		return isDocActive;
	}
	public void setDocActive(boolean isActive) {
		this.isDocActive = isActive;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	
	public String fullName() {
		return firstName.concat(" ").concat(lastName);
	}

	@Override
	public String toString() {
		return "DoctorDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", gender=" + gender + ", mobileNumber=" + mobileNumber + ", emailId=" + emailId + ", password="
				+ password + ", isActive=" + isActive + ", qualifications=" + qualifications + ", experience="
				+ experience + ", department=" + department + ", isDocActive=" + isDocActive + ", userId=" + userId
				+ "]\n";
	}
}
