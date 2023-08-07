package in.fssa.carecentral.model;

enum Gender{M , F , O};

public abstract class UserEntity implements Comparable<UserEntity>{
	private Integer id;
	private String firstName;
	private String lastName;
	private Integer age;
	private Gender gender;
	private long mobileNumber;
	private String emailId;
	private String password;
	private boolean isActive = true;
	
	public Integer getUserId() {
		return id;
	}

	public void setUserId(Integer userId) {
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
	
	public void setGender(Gender gender) {
		this.gender = gender;
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
				+ password + ", isActive=" + isActive + "]";
	}

	
	@Override
	public int compareTo(UserEntity o) {

		if (this.getUserId() == o.getUserId()) {
			return 0;
		} else {
			if (this.getUserId() > o.getUserId()) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}
