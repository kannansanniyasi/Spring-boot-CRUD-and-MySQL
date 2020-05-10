package com.crud.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.app.entity.ContactDto;
import com.crud.app.entity.MesseageDto;


@Service
public class ContactServices {

	@Autowired
	private ContactRepository conRepository;
	public List<ContactDto> getContactData() {
		// TODO Auto-generated method stub
		return conRepository.findAll();
	}
	public MesseageDto saveContact(ContactDto contDao) {
		// TODO Auto-generated method stub
		MesseageDto msg=new MesseageDto();
		try
		{
			conRepository.save(contDao);
			msg.setMessage("success");
		}catch(Exception e)
		{
			e.printStackTrace();
			msg.setMessage("Error"+e.getMessage());
		}
		return msg;
	}
	public MesseageDto deleteContact(ContactDto contDao) {
		// TODO Auto-generated method stub
		MesseageDto msg=new MesseageDto();
		try
		{
			conRepository.delete(contDao);
			msg.setMessage("success");
		}catch(Exception e)
		{
			e.printStackTrace();
			msg.setMessage("Error"+e.getMessage());
		}
		return msg;
	}
	public MesseageDto updateContact(ContactDto contDao) {
		// TODO Auto-generated method stub
		MesseageDto msg=new MesseageDto();
		try
		{
			conRepository.updateContact(contDao);
			msg.setMessage("success");
		}catch(Exception e)
		{
			e.printStackTrace();
			msg.setMessage("Error"+e.getMessage());
		}
		return msg;
	}
	
	
}
