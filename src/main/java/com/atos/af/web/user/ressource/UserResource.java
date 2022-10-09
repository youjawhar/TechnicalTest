package com.atos.af.web.user.ressource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.af.dao.entities.Countries;
import com.atos.af.services.dto.UserDTO;
import com.atos.af.services.exceptions.OnlyAdultFrenchException;
import com.atos.af.services.user.UserService;
import com.atos.af.web.user.pojo.UserPOJO;

@RestController
@RequestMapping("/user")
public class UserResource {
	
	@Autowired
	private UserService serviceUser;
	
	@PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserPOJO userPojo) throws ParseException {
		UserDTO userDTO = mapUserPojoToUserDTO(userPojo);
		try {
			serviceUser.saveUserOnlyFrench(userDTO);
			return new ResponseEntity<>("User saved", HttpStatus.OK);
		} catch (OnlyAdultFrenchException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<UserPOJO>> getUsers(){
		
		List<UserDTO> users = serviceUser.getUsers();
		
		List<UserPOJO> usersPojo=users.stream().map(userDTO -> mapUserDTOToUserPojo(userDTO)).collect(Collectors.toList());
		
		return new ResponseEntity<>(usersPojo, HttpStatus.OK);
	}
	
	
	private UserDTO mapUserPojoToUserDTO(UserPOJO userPojo) throws ParseException {
		UserDTO userDTO=new UserDTO();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		userDTO.setBirthdate(formatter.parse(userPojo.getBirthdate()));
		userDTO.setContryOfResidence(userPojo.getContryOfResidence());
		userDTO.setName(userPojo.getName());
		userDTO.setPhoneNumber(userPojo.getPhoneNumber());
		return userDTO;
	}
	
	private UserPOJO mapUserDTOToUserPojo(UserDTO userDTO) {
		UserPOJO userPojo=new UserPOJO();
		userPojo.setId(userDTO.getId());
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
		userPojo.setBirthdate(newFormat.format(userDTO.getBirthdate()));
		userPojo.setContryOfResidence(userDTO.getContryOfResidence());
		userPojo.setName(userDTO.getName());
		userPojo.setPhoneNumber(userDTO.getPhoneNumber());
		return userPojo;
	}
	
	
	
	
	
	
}
