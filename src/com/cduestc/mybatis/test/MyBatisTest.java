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
	 * 1：根据xml配置文件创建一个SqlSessionFactory对象
	 * 2：sql映射文件，配置了每一个sql，以及sql的封装规则
	 * 3：将sql映射文件注册到全局配置文件中
	 * 4：编写执行代码
	 * 			1):根据全局配置文件得到SqlSessionFactory
	 * 			2):使用sqlsessionFactory工厂，获取到sqlSession来执行操作
	 * 			3):使用sql的唯一标识来告诉MyBatis来执行sql，sql都是保存在sql配置文件中(这里的是：EmployeeMapper.xml文件)
	 */
	@Test
	public void test() throws IOException{
		
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) new SqlSessionFactoryBuilder().build(inputStream);
		
//		2:获取sqlSession实例，能直接执行已经映射的sql语句
//		sql的唯一标识，
//		执行sql要用的参数
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
//		获取sqlSessionFactry
		SqlSessionFactory sessionFactory = getSqlSessionFactory();
//		获取sqlSession对象
		SqlSession openSession = sessionFactory.openSession();
		
		try {
			//		获取接口的实现类对象
//			在接口与xml进行绑定后，mybatis会自动创建一个代理对象，代理对象去执行方法操作
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
	/*mybatis允许增删改直接定义以下类型的返回值
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
