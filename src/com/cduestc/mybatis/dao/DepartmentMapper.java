package com.cduestc.mybatis.dao;

import com.cduestc.mybatis.Department;

public interface DepartmentMapper {
	
	public Department getDeptById(Integer id);
	
	public Department getDeptByIdPlus(Integer id);
}
