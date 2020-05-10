package com.crud.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crud.app.entity.EmployeeDto;
import com.crud.app.entity.MesseageDto;
import com.crud.app.services.EmpServices;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmpController {
	
	@Autowired
	EmpServices empSerices;

	@RequestMapping("/welcome")
	public String sayMessgae()
	{
		return "Welcome to Spring Application";
	}
	
	@RequestMapping(value="/empDetails",method=RequestMethod.GET)
	public List<EmployeeDto> getEmpData()
	{
		return empSerices.getEmpData();
	}
	
	
	@RequestMapping(value="/saveEmp",method=RequestMethod.POST)
	public MesseageDto saveEmployee(@RequestBody EmployeeDto empDao)
	{
		System.out.println("save employee method controller"+empDao.getName());
		return empSerices.saveEmp(empDao);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public MesseageDto deleteEmployee(@RequestBody EmployeeDto empDao)
	{
		System.out.println("delete employee method controller"+empDao.getName());
		return empSerices.deleteEmployee(empDao);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public MesseageDto updateEmployee(@RequestBody EmployeeDto empDao)
	{
		System.out.println("update employee method controller"+empDao.getName());
		return empSerices.updateEmployee(empDao);
	}
	
}
