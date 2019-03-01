package com.cduestc.mybatis.dao;

import com.cduestc.mybatis.Employee;

public interface EmployeeMapperPlus {
	public Employee getEmpAndDept(Integer id);
	
	public Employee getEmpByIdStep(Integer id);
	
}
