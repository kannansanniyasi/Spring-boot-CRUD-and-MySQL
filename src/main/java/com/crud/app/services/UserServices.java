package com.crud.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.app.entity.MesseageDto;
import com.crud.app.entity.UserDto;

import household.appliance.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;
	public List<UserDto> getUserData() {
		// TODO Auto-generated method stub
		return (List<UserDto>) userRepository.findAll();
	}
	public MesseageDto saveUser(UserDto userDao) {
		// TODO Auto-generated method stub
		MesseageDto msg=new MesseageDto();
		try
		{
			userRepository.save(userDao);
			msg.setMessage("success");
		}catch(Exception e)
		{
			e.printStackTrace();
			msg.setMessage("Error"+e.getMessage());
		}
		return msg;
	}
	public MesseageDto deleteUser(UserDto userDto) {
		MesseageDto user=new MesseageDto();
	try
	{
		userRepository.deleteUser(userDto);
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return user;
	}
	public MesseageDto updateUser(UserDto userDto) {
		// TODO Auto-generated method stub
		MesseageDto obj=new MesseageDto();
		try
		{
			userRepository.updateUser(userDto);
			obj.setMessage("success");
		}catch(Exception e)
		{
			e.printStackTrace();
			obj.setMessage("Error "+e.getMessage());
		}
				
		return obj;
	}
	
}
