package com.example.employee.dto;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tbl_employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empId", nullable = false)
	private Long id;

	@Column(name = "firstName", nullable = false, length = 30)
	private String firstName;

	@Column(name = "lastName", length = 30)
	private String lastName;

	@Column(name = "contactNumber", nullable = false, length = 10, unique = true)
	private String contactNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "dob", nullable = false)
	private Date dob;

	@Column(name = "salary", nullable = false)
	private double salary;

	@Column(name = "email", nullable = false, unique = true, length = 80)
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name = "joinDate", nullable = false)
	private Date joinDate;

	@Column(name = "role", nullable = false)
	private String role;

	@Column(name = "address", length = 400)
	private String address;

	@Column(name = "isActive")
	private boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, contactNumber, dob, email, firstName, id, isActive, joinDate, lastName, role,
				salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(address, other.address) && Objects.equals(contactNumber, other.contactNumber)
				&& Objects.equals(dob, other.dob) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& isActive == other.isActive && Objects.equals(joinDate, other.joinDate)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(role, other.role)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary);
	}

	public Employee(Long id, String firstName, String lastName, String contactNumber, Date dob, double salary,
			String email, Date joinDate, String role, String address, boolean isActive) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.dob = dob;
		this.salary = salary;
		this.email = email;
		this.joinDate = joinDate;
		this.role = role;
		this.address = address;
		this.isActive = isActive;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

}
