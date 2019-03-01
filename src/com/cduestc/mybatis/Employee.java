package com.cduestc.mybatis;

import org.apache.ibatis.type.Alias;

@Alias(value = "employee")
public class Employee {
	private Integer id;
	private String lastname;
	private String gender;
	private String email;
	private Department dept;
	
	
	public Employee() {
		super();
	}

	public Employee(String lastname, String gender, String email) {
		super();
		
		this.lastname = lastname;
		this.gender = gender;
		this.email = email;
	}
	
	public Employee(Integer id, String lastname, String gender, String email) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.gender = gender;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastname=" + lastname + ", gender="
				+ gender + ", email=" + email + ", dept=" + dept + "]";
	}

	
}
