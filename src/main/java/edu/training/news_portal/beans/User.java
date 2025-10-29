package edu.training.news_portal.beans;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
    private String email;
    private String name;
    private String surname;
    private int roleId;
    private int statusId;
    private String dateRegistration;
    
    public User() {
    }
	
    public User(int id, String email, String name, String surname, int roleId, int statusId, String dateRegistration) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.roleId = roleId;
        this.statusId = statusId;
        this.dateRegistration = dateRegistration;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getDateRegistration() {
		return dateRegistration;
	}

	public void setDateRegistration(String dateRegistration) {
		this.dateRegistration = dateRegistration;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dateRegistration, email, id, name, roleId, statusId, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(dateRegistration, other.dateRegistration) && Objects.equals(email, other.email)
				&& id == other.id && Objects.equals(name, other.name) && roleId == other.roleId
				&& statusId == other.statusId && Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", surname=" + surname + ", roleId=" + roleId
				+ ", statusId=" + statusId + ", dateRegistration=" + dateRegistration + "]";
	}

	
}
