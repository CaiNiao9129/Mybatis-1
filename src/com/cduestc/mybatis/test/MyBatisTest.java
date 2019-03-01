package com.cduestc.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cduestc.mybatis.Employee;
import com.cduestc.mybatis.dao.EmployeeMapper;
import com.cduestc.mybatis.dao.EmployeeMapperAnnoation;

public class MyBatisTest {
	
	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	/*
	 * 1������xml�����ļ�����һ��SqlSessionFactory����
	 * 2��sqlӳ���ļ���������ÿһ��sql���Լ�sql�ķ�װ����
	 * 3����sqlӳ���ļ�ע�ᵽȫ�������ļ���
	 * 4����дִ�д���
	 * 			1):����ȫ�������ļ��õ�SqlSessionFactory
	 * 			2):ʹ��sqlsessionFactory��������ȡ��sqlSession��ִ�в���
	 * 			3):ʹ��sql��Ψһ��ʶ������MyBatis��ִ��sql��sql���Ǳ�����sql�����ļ���(������ǣ�EmployeeMapper.xml�ļ�)
	 */
	@Test
	public void test() throws IOException{
		
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) new SqlSessionFactoryBuilder().build(inputStream);
		
//		2:��ȡsqlSessionʵ������ֱ��ִ���Ѿ�ӳ���sql���
//		sql��Ψһ��ʶ��
//		ִ��sqlҪ�õĲ���
		SqlSession openSession =sqlSessionFactory.openSession();
		
		try {
			Employee employee =openSession.selectOne("com.cduestc.Mybatis.EmployeeMapper.selectEmp",1);
			System.out.println(employee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			openSession.close();
		}
	}
	@Test
	public void testgetEmployeeById() throws IOException{
//		��ȡsqlSessionFactry
		SqlSessionFactory sessionFactory = getSqlSessionFactory();
//		��ȡsqlSession����
		SqlSession openSession = sessionFactory.openSession();
		
		try {
			//		��ȡ�ӿڵ�ʵ�������
//			�ڽӿ���xml���а󶨺�mybatis���Զ�����һ��������󣬴������ȥִ�з�������
			EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
			Employee employee = employeeMapper.getEmployeeById(3);
			System.out.println(employeeMapper.getClass());
			System.out.println(employee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			openSession.close();
		}
	}
	
	@Test
	public void testGetEmployeeById2() throws IOException{
		SqlSessionFactory sessionFactory = getSqlSessionFactory();
		SqlSession openSession = sessionFactory.openSession();
		
		try {
			EmployeeMapperAnnoation eAnnoation = openSession.getMapper(EmployeeMapperAnnoation.class);
			Employee employee = eAnnoation.getEmployeeById(2);
			System.out.println(employee);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			openSession.close();
		}
	}
	/*mybatis������ɾ��ֱ�Ӷ����������͵ķ���ֵ
	 * 
	 * */
	@Test
	public void test3() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession =sqlSessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Employee employee = new Employee("LiSa","2","LiSa@qq.com");
			mapper.addEmp(employee);
			System.out.println(employee.getId());
			openSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			openSession.close();
		}
	}
	@Test
	public void test4() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession =sqlSessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Employee employee = new Employee(2,"jerry","1","jerry@qq.com");
			System.out.println(mapper.updateEmp(employee));
			openSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			openSession.close();
		}
	}
	@Test
	public void test5() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession =sqlSessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			mapper.deleteEmpById(2);
			openSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			openSession.close();
		}
	}
	@Test
	public void test6() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession =sqlSessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			List<Employee> eList = mapper.getEmpsBylastNameLike("%i%");
			for(Employee employee : eList){
				System.out.println(employee);
			}
			openSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			openSession.close();
		}
	}
	@Test
	public void test7() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession =sqlSessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			List<Employee> eList = mapper.getEmpsBylastNameLike("%i%");
			for(Employee employee : eList){
				System.out.println(employee);
			}
			Map<String, Object> getEmpsByIdReturnMap = mapper.getEmpsByIdReturnMap(1);
			System.out.println(getEmpsByIdReturnMap);
			openSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			openSession.close();
		}
	}
	@Test
	public void test8() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession =sqlSessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Map<Integer, Employee> emap = mapper.getEmpsByLastNameLikeReturnMap("%i%");
			
			System.out.println(emap);
			openSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			openSession.close();
		}
	}
}
