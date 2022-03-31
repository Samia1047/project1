package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
import dao.ManagerDao;
import dao.ManagerJdbcDaoImpl;
import exception.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import service.ManagerService;
import service.ManagerServiceImpl;

public class ServiceTest {
	
	EmployeeDao employeeDao = mock(EmployeeJdbcDaoImpl.class);
	ManagerDao managerDao = mock(ManagerJdbcDaoImpl.class);
	
	static EmployeeService employeeService;
	static ManagerService managerService;
	
	@BeforeAll
	public static void setup( ) {
		employeeService = new EmployeeServiceImpl();
		
		
		managerService = new ManagerServiceImpl();
	}
	
	@Test
	public void testEmployeeLogin() {
		try {
			EmployeePojo actualResult = employeeService.login("samia@gmail.com","root");
			EmployeePojo expectedResult = new EmployeePojo(1,"root","Samia", "Jahan","samia@gmail.com" ,"D drive","");
			when(employeeDao.employeeViewDetails("samia@gmail.com")).thenReturn(new EmployeePojo(1, "root","Samia", "Jahan",  "samia@gmail.com" , "D drive",""));
			assertEquals(actualResult,expectedResult);
			
			
		} catch (SystemException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	@Test
	public void testManagerLogin() {
		try {
			
			
			
			ManagerPojo actualResult = managerService.login("Poonga@gmail.com","root");
			ManagerPojo expectedResult = new ManagerPojo(1,"root","Poonga", "Anand","Poonga@gmail.com" ,"F drive lane","");
			when(managerDao.fetchOneManager("Poonga@gmail.com")).thenReturn(new ManagerPojo(1,"root","Poonga", "Anand","Poonga@gmail.com" ,"F drive lane",""));
			assertEquals(actualResult,expectedResult);
			
		} catch (SystemException e) {
			e.printStackTrace();
		} 
		
		
	}
	}


