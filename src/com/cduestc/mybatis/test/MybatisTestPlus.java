package com.cduestc.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cduestc.mybatis.Department;
import com.cduestc.mybatis.Employee;
import com.cduestc.mybatis.dao.DepartmentMapper;
import com.cduestc.mybatis.dao.EmployeeMapperPlus;



public class MybatisTestPlus {
	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}

	@Test
	public void test0() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			EmployeeMapperPlus eMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
			Employee employee = eMapperPlus.getEmpAndDept(3);
			System.out.println(employee);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
		
	}
	@Test
	public void test1() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			EmployeeMapperPlus eMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
			Employee employee = eMapperPlus.getEmpByIdStep(3);
			System.out.println(employee);
			System.out.println(employee.getDept());
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
		
	}
	@Test
	public void test2() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			DepartmentMapper dMapper = sqlSession.getMapper(DepartmentMapper.class);
			Department department = dMapper.getDeptByIdPlus(3);
			System.out.println(department);
			System.out.println(department.getEmps());
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
		
	}
	
}
