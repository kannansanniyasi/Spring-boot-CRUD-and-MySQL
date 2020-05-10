package com.crud.app.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.app.entity.MesseageDto;
import com.crud.app.entity.UserDto;
import com.crud.app.services.UserServices;
import com.crud.app.utils.ExcelGeneration;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServices userSerices;

	@RequestMapping("/welcome")
	public String sayMessgae()
	{
		return "Welcome to Contact";
	}
	
	@RequestMapping(value="/userDetails",method=RequestMethod.GET)
	public List<UserDto> getUserData()
	{
		return userSerices.getUserData();
	}
	

	/*@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	public MesseageDto saveUser(@RequestBody UserDto userDto)
	{
		System.out.println("save contact method data");
		return userSerices.saveUser(userDto);
	}*/
	
	@RequestMapping(value="/saveUser",method=RequestMethod.PUT)
	public MesseageDto saveUser(@RequestBody UserDto userDto)
	{
		System.out.println("save contact method data");
		return userSerices.saveUser(userDto);
	}
	
	@RequestMapping(value="/saveUser1",method=RequestMethod.PUT)
	public String saveUserParameter(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("phone") String phone)
	{
		System.out.println("Name :"+name);
		System.out.println("Email :"+email);
		System.out.println("Phone :"+phone);
		return "Data is"+name+"/"+email+""+phone;
	}
	

	@RequestMapping(value="/getUser/{id}",method=RequestMethod.GET)
	public MesseageDto getUser(@PathVariable("id") String id)
	{
		System.out.println("get User data "+id);
		return new MesseageDto();
	}
	

	/*@RequestMapping(value="/delete",method=RequestMethod.POST)
	public MesseageDto deleteUser(@RequestBody UserDto userDto)
	{
		System.out.println("delete use id"+userDto.getId());
		return userSerices.deleteUser(userDto);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public MesseageDto updateUser(@RequestBody UserDto userDto)
	{
		System.out.println("update Id"+userDto.getId());
		return userSerices.updateUser(userDto);
	}*/
	
	@RequestMapping(value="/delete",method=RequestMethod.PUT)
	public MesseageDto deleteUser(@RequestBody UserDto userDto)
	{
		System.out.println("delete use put"+userDto.getId());
		return userSerices.deleteUser(userDto);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public MesseageDto updateUser(@RequestBody UserDto userDto)
	{
		System.out.println("update in using put"+userDto.getId());
		return userSerices.updateUser(userDto);
	}
	
	@GetMapping("/exportExcel")
    public void downloadCsv(HttpServletResponse response) throws IOException 
	{   
		List<UserDto> userDtoList=userSerices.getUserData();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=customers.xlsx");
        ByteArrayInputStream stream = ExcelGeneration.generateExcel(userDtoList);
        IOUtils.copy(stream, response.getOutputStream());
    }
	
	
	@RequestMapping(value="/userScroll",method=RequestMethod.GET)
	public List<UserDto> getUserDataVirtualScrolling()
	{
		List<UserDto> list=new ArrayList<UserDto>();
		UserDto dto=new UserDto();
		for(int i=0;i<1000;i++)
		{
			long count=i+1;
			dto=new UserDto();
			dto.setId(count);
			dto.setName("name"+count);
			dto.setEmail("email"+count);
			dto.setPhone("phobe"+count);
			list.add(dto);
		}
		
		return list;
	}
	
	
}
