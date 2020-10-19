package com.example.demo.globallogic.userApi.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name="Phone")
public class PhoneEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManyToOne(fetch=FetchType.EAGER)
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name="EntityUser_id")
    private UserEntity entityUser;
    @Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "phone_id", updatable = false, nullable = false)
	private UUID phoneId;
    @Column (name="number")
    private String number;
    @Column (name="city_code")
    private String citycode;
    @Column(name="country_code")
    private String countrycode;

    public String getNumber() {
        return number;
    }

    public UserEntity getEntityUser() {
        return entityUser;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public void setEntityUser(UserEntity entityUser) {
        this.entityUser = entityUser;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCountrycode() {
        return countrycode;
    }

	public UUID getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(UUID phoneId) {
		this.phoneId = phoneId;
	}

}
