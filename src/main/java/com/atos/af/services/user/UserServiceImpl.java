package com.atos.af.services.user;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atos.af.dao.entities.Countries;
import com.atos.af.dao.entities.Utilisateur;
import com.atos.af.dao.repositories.UserRepository;
import com.atos.af.services.dto.UserDTO;
import com.atos.af.services.exceptions.OnlyAdultFrenchException;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveUserOnlyFrench(UserDTO userDTO) throws OnlyAdultFrenchException {
		if(!Countries.FR.equals(userDTO.getContryOfResidence()) && calculateAge(userDTO.getBirthdate())< 18){
			throw new OnlyAdultFrenchException();
		}
		userRepository.save(mapUserDTOToUser(userDTO));
	}
	
	@Override
	public List<UserDTO> getUsers() {
		List<Utilisateur> users = userRepository.findAll();
		if(users.isEmpty()) {
			return new ArrayList<>();
		}
		return users.stream().map(user -> mapUserToUserDTO(user)).collect(Collectors.toList());
	}
	
	private UserDTO mapUserToUserDTO(Utilisateur user) {
		UserDTO userDTO=new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setBirthdate(user.getBirthdate());
		userDTO.setContryOfResidence(user.getContryOfResidence());
		userDTO.setName(user.getName());
		userDTO.setPhoneNumber(user.getPhoneNumber());
		return userDTO;
	}
	
	
	private Utilisateur mapUserDTOToUser(UserDTO userDTO) {
		Utilisateur user=new Utilisateur();
		user.setBirthdate(userDTO.getBirthdate());
		user.setGender(userDTO.getGender());
		user.setContryOfResidence(userDTO.getContryOfResidence());
		user.setName(userDTO.getName());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		return user;
	}
	
	
	private int calculateAge(Date birthDate) {
		return (int) ChronoUnit.YEARS.between(asLocalDate(birthDate), LocalDate.now());
	}
	
	private LocalDate asLocalDate(Date date) {
	    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}


	

	
}
