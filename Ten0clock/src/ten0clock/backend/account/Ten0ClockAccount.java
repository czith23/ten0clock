package ten0clock.backend.account;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/*
 * Used to manage, authenticate, and create accounts
 */
public class Ten0ClockAccount {
	private long internalUserID;	
	private String firstName;
	private String lastName;
	private String userID;
	private String password;
	private String email;
	private String phoneNumber;
	private String school;
	private String birthday;
	
	public Ten0ClockAccount() {
		
	}
	
	public Ten0ClockAccount(String firstName, String lastName, String userID, String password, String email, String phoneNumber, String school, String birthday) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userID = userID;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.school = school;
		this.birthday = birthday;
	}
	
	public Ten0ClockAccount(long internalUserID, String firstName, String lastName, String userID, String password, String email, String phoneNumber, String school, String birthday) {
		this.internalUserID = internalUserID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userID = userID;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.school = school;
		this.birthday = birthday;
	}
	
	public long getInternalID() {
		return internalUserID;
	}

	public void setInternalID(long internalID) {
		this.internalUserID = internalID;
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public boolean isValidAccount() {
		return false;
	}
	
	public boolean isNewAccount() {
		return false;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
