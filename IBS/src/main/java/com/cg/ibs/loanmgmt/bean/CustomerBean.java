package com.cg.ibs.loanmgmt.bean;

import java.math.BigInteger;

public class CustomerBean{

	private String firstName;
	private String lastName;
	private String UCI; // 16 digit Unique Customer ID
	private String userId; // unique credentials created by customer for login
	private String password; // unique credentials created by customer for login

	public CustomerBean(String firstName, String lastName, String uCI, String userId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.UCI = uCI;
		this.userId = userId;

	}

	public CustomerBean() {
		super();
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

	public String getUCI() {
		return UCI;
	}

	public void setUCI(String uCI) {
		UCI = uCI;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Name :\t" + firstName + " " + lastName + "\nUCI :\t" + UCI + "\nuserId:\t" + userId ;
	}
}
