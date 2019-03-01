package com.cduestc.mybatis.dao;

import org.apache.ibatis.annotations.Select;

import com.cduestc.mybatis.Employee;



public interface EmployeeMapperAnnoation {
	
	@Select("select * from tbl_employee where id = #{id}")
	public Employee getEmployeeById(Integer id);
}
