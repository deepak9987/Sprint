package com.usermanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private int adminId;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	// ✅ Default constructor (Hibernate needs this)
	public Admin() {}

	// ✅ Parameterized constructor used in DAO
	public Admin(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	// ✅ Getters and Setters
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// ✅ For displaying Admin info in console
	@Override
	public String toString() {
		return adminId + " - " + name + " (" + email + ")";
	}
}
