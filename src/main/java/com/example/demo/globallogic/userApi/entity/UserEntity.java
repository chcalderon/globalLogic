package com.example.demo.globallogic.userApi.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "usuario")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "user_id", updatable = false, nullable = false)
	private UUID id;
	@Column(name = "name", unique = true)
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "password", unique = true)
	private String password;
	@Column(name = "creado")
	private java.sql.Timestamp creado;
	@Column(name = "modificado")
	private java.sql.Timestamp modificado;
	@Column(name = "last_login")
	private java.sql.Timestamp last_login;
	@Column(name = "active")
	private boolean active;
	@Column(name = "token")
	private String token;

	public UserEntity(UUID id, String name, String email, String password, Timestamp creado, Timestamp modificado,
			Timestamp last_login, boolean active, String token, List<PhoneEntity> phones) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.creado = creado;
		this.modificado = modificado;
		this.last_login = last_login;
		this.active = active;
		this.token = token;
		this.phones = phones;
	}

	public UserEntity() {

	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public java.sql.Timestamp getCreado() {
		return creado;
	}

	public void setCreado(java.sql.Timestamp creado) {
		this.creado = creado;
	}

	public java.sql.Timestamp getModificado() {
		return modificado;
	}

	public void setModificado(java.sql.Timestamp modificado) {
		this.modificado = modificado;
	}

	public java.sql.Timestamp getLast_login() {
		return last_login;
	}

	public void setLast_login(java.sql.Timestamp last_login) {
		this.last_login = last_login;
	}

	@OneToMany(mappedBy = "entityUser", cascade = CascadeType.PERSIST)
	private List<PhoneEntity> phones;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public List<PhoneEntity> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneEntity> phones) {
		this.phones = phones;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((creado == null) ? 0 : creado.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((last_login == null) ? 0 : last_login.hashCode());
		result = prime * result + ((modificado == null) ? 0 : modificado.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phones == null) ? 0 : phones.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		if (active != other.active)
			return false;
		if (creado == null) {
			if (other.creado != null)
				return false;
		} else if (!creado.equals(other.creado))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (last_login == null) {
			if (other.last_login != null)
				return false;
		} else if (!last_login.equals(other.last_login))
			return false;
		if (modificado == null) {
			if (other.modificado != null)
				return false;
		} else if (!modificado.equals(other.modificado))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phones == null) {
			if (other.phones != null)
				return false;
		} else if (!phones.equals(other.phones))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

}
