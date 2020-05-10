package com.crud.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.app.entity.EmployeeDto;
import com.crud.app.entity.MesseageDto;

import household.appliance.repository.ApplianceRepository;

@Service
public class EmpServices {

	@Autowired
	private ApplianceRepository empRepository;
	public List<EmployeeDto> getEmpData() {
		// TODO Auto-generated method stub
		return empRepository.findAll();
	}
	public MesseageDto saveEmp(EmployeeDto empDao) {
		// TODO Auto-generated method stub
		MesseageDto msg=new MesseageDto();
		try
		{
		empRepository.save(empDao);
		msg.setMessage("success");
		}catch(Exception e)
		{
			msg.setMessage("error"+e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	public MesseageDto deleteEmployee(EmployeeDto empDao) {
		// TODO Auto-generated method stub
		MesseageDto msg=new MesseageDto();
		try
		{
		empRepository.delete(empDao);
		msg.setMessage("success");
		}catch(Exception e)
		{
			msg.setMessage("error"+e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	public MesseageDto updateEmployee(EmployeeDto empDao) {
		// TODO Auto-generated method stub
		MesseageDto msg=new MesseageDto();
		try
		{
			empRepository.updateEmployee(empDao);
		msg.setMessage("success");
		}catch(Exception e)
		{
			msg.setMessage("error"+e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	
	
}
