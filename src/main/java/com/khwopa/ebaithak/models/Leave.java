package com.khwopa.ebaithak.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_leave")
public class Leave {

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String username;
	@Column
	private Long baithak_id;
	@Column
	private String message;
	@Column
	private String created_at;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getBaithak_id() {
		return baithak_id;
	}
	public void setBaithak_id(Long long1) {
		this.baithak_id = long1;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String msg) {
		this.message = msg;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

}
