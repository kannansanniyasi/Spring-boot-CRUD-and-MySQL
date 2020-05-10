package com.crud.app.entity.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public class EmpRepository  extends JpaRepository<EmployeeDto, Long>{


	@Modifying
	@Query("update EmployeeDto e set e.name=:#{#emp.getName()},e.email=:#{#emp.getEmail()},"
			+ " e.phone=:#{#emp.getPhone()},e.state=:#{#emp.getState()},"
			+ "e.country=:#{#emp.getCountry()} where e.id=:#{#emp.getId()}")
	public int updateEmployee(@Param("emp") EmployeeDto emp);

}
