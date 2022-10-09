package com.atos.af.dao.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "BIRTH_DATE")
	private Date birthdate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CONTRY_RESIDENCE")
	private Countries contryOfResidence;
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber; 
	
	@Column(name = "gender")
	private String gender;
}
