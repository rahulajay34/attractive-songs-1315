package com.Bean;

public class Faculty {

	private int facultyId;
	private String facultyName;
	private String facultyAdress;
	private String mobile;
	private String email;
	private String username;
	private String password;
	
	public Faculty() {

	}

	public Faculty(int facultyId, String facultyName, String facultyAdress, String mobile, String email,
			String username, String password) {
		super();
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		this.facultyAdress = facultyAdress;
		this.mobile = mobile;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getFacultyAdress() {
		return facultyAdress;
	}

	public void setFacultyAdress(String facultyAdress) {
		this.facultyAdress = facultyAdress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", facultyName=" + facultyName + ", facultyAdress=" + facultyAdress
				+ ", mobile=" + mobile + ", email=" + email + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	
	
}
