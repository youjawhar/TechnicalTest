package com.atos.af.services.dto;

import java.util.Date;

import com.atos.af.dao.entities.Countries;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class UserDTO {
	
	private Long id;
	private String name;
	private Date birthdate;
	private Countries contryOfResidence;
	private String phoneNumber; 
	private String number; 
	private String gender;
}
