package com.atos.af.services.user;

import java.util.List;

import com.atos.af.services.dto.UserDTO;
import com.atos.af.services.exceptions.OnlyAdultFrenchException;

public interface UserService  {
	public void saveUserOnlyFrench(UserDTO userDTO) throws OnlyAdultFrenchException;
	public List<UserDTO> getUsers();
}
