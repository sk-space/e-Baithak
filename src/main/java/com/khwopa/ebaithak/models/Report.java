package com.khwopa.ebaithak.models;

import java.util.ArrayList;

public class Report {

	private ArrayList<String> user;
	private String subject;
	private String description;
	private String b_name;

	public ArrayList<String> getUser() {
		return user;
	}

	public void setUser(ArrayList<String> user) {
		this.user = user;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

}
