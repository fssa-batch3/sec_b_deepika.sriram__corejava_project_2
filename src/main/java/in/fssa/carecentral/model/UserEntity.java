package in.fssa.carecentral.model;
import javax.validation.constraints.NotNull;

import in.fssa.carecentral.enumfiles.*;
public abstract class UserEntity implements Comparable<UserEntity>{
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private Gender gender;
	private long mobileNumber;
	@NotNull
	private String emailId;
	
	private String password;
	private boolean isActive = true;
	
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

	
	
	public String fullName() {
		return firstName.concat(" ").concat(lastName);
	}
	
	
	
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", gender=" + gender + ", mobileNumber=" + mobileNumber + ", emailId=" + emailId + ", password="
				+ password + ", isActive=" + isActive + "]\n";
	}

	
	@Override
	public int compareTo(UserEntity o) {

		if (this.getId() == o.getId()) {
			return 0;
		} else {
			if (this.getId() > o.getId()) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}
