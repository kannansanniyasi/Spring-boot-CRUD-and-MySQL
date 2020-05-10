package com.crud.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crud.app.entity.ContactDto;
import com.crud.app.entity.MesseageDto;
import com.crud.app.services.ContactServices;

@CrossOrigin
@RestController
@RequestMapping("/contact")
public class ContactController {
	
	@Autowired
	ContactServices contactSerices;

	@RequestMapping("/welcome")
	public String sayMessgae()
	{
		return "Welcome to Contact";
	}
	@RequestMapping(value="/contactData",method=RequestMethod.GET)
	public List<ContactDto> getContactData()
	{
		return contactSerices.getContactData();
	}
	
	@RequestMapping(value="/saveContact",method=RequestMethod.POST)
	public MesseageDto saveContact(@RequestBody ContactDto contDao)
	{
		System.out.println("save contact method data");
		return contactSerices.saveContact(contDao);
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public MesseageDto deleteContact(@RequestBody ContactDto contDao)
	{
		System.out.println("delete contact method data");
		return contactSerices.deleteContact(contDao);
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public MesseageDto updateContact(@RequestBody ContactDto contDao)
	{
		System.out.println("update contact method data");
		return contactSerices.updateContact(contDao);
	}
	
	
	
}
