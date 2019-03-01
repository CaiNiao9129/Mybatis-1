package com.cduestc.mybatis.dao;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.cduestc.mybatis.Employee;



public interface EmployeeMapper {
	
	public Employee getEmployeeById(Integer id);
	
	public void addEmp(Employee employee);
	public boolean updateEmp(Employee employee);
	public void deleteEmpById(Integer id);
	/*
	 * 在mybatis处理多个参数时，多个参数将被封装一个Map，
	 * key是:param1,param2,,,或者参数额索引(0,1,2)
	 * value 是实际的参数值
	 * 在EmployeeMapper.xml配置文件中sql语句：select from tbl_employee where id = #{param1} and last_name = #{param2}
	 */
	public Employee getEmployeeByIdAndLastName(Integer id,String lastName);
//	命名参数法:可以直接在方法中赋予参数指定
	public Employee getEmployeeByIdAndLastName2(@Param("id")Integer id,@Param("last_name")String lastName);

//	POJO:如果多个参数是业务逻辑的数据模型，我们就可以直接传人Pojo;#{属性名}，取出传人的Pojo的属性值
//	Map：如果不是，没有对应的pojo，可以传人map,#{key}
	public Employee getEmpByMap(Map<String, Object> map);
	
//	liek用法，模糊查询，返回集合
	public List<Employee> getEmpsBylastNameLike(String lastName);
	
//	返回一个map对象
	public Map<String, Object> getEmpsByIdReturnMap(Integer id);
	
//	返回多个map对象
	@MapKey("id")//告诉mybatis map的主键值就是id
	public Map<Integer, Employee> getEmpsByLastNameLikeReturnMap(String lastname);
}
