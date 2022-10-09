package com.atos.af.services.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.atos.af.dao.entities.Countries;
import com.atos.af.dao.entities.Utilisateur;
import com.atos.af.dao.repositories.UserRepository;
import com.atos.af.services.dto.UserDTO;
import com.atos.af.services.exceptions.OnlyAdultFrenchException;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepository;

	@DisplayName("find collaborateur")
    @Nested
    class saveUser {
		
		@DisplayName("test save only adult french residents ")
		@Test
		void testSaveOnlyFrenchResidents() throws ParseException, OnlyAdultFrenchException {
			//Arrange
			UserDTO userDTO=addUserDTO(Countries.FR, "05-05-1991");
			//Act
			userServiceImpl.saveUserOnlyFrench(userDTO);
			//Assert
			
			verify(userRepository,times(1)).save(any(Utilisateur.class));
		}
		
		private UserDTO addUserDTO(Countries country,String strDate) throws ParseException {
			UserDTO userDTO=new UserDTO();
			userDTO.setContryOfResidence(Countries.FR);
			SimpleDateFormat dateformat = new SimpleDateFormat("dd-M-yyyy");
			Date newdate = dateformat.parse(strDate);
			userDTO.setBirthdate(newdate);
			userDTO.setGender("Female");
			userDTO.setName("Youssef");
			userDTO.setNumber("0658623966");
			return userDTO;
		}
		
		
	}

}
