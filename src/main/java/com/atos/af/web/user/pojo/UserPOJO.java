package com.atos.af.web.user.pojo;

import java.util.Date;

import com.atos.af.dao.entities.Countries;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserPOJO {
	private Long id;
	private String name;
	private String birthdate;
	private Countries contryOfResidence;
	private String phoneNumber; 
}
