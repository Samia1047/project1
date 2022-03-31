package service;

import java.util.List;

import dao.EmployeeDao;
import dao.ManagerDao;
import dao.ManagerJdbcDaoImpl;
import exception.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.MergedReimbursmentPojo;


public class ManagerServiceImpl implements ManagerService {
	
	ManagerDao managerDao;
	
	public ManagerServiceImpl() {
		managerDao = new ManagerJdbcDaoImpl();
	}

	@Override
	public ManagerPojo fetchOneManager(String employeeContact) throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.fetchOneManager(employeeContact);
	}

	@Override
	public ManagerPojo login(String managerContact, String managerPassword) throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.login(managerContact, managerPassword);
	}

	@Override
	public ManagerPojo managerViewinfo(String managerContact) throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.managerViewinfo(managerContact);
	}

	@Override
	public MergedReimbursmentPojo approveOrDeny(MergedReimbursmentPojo resolvedReimbursmentPojo)
			throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.approveOrDeny(resolvedReimbursmentPojo);
	}

//	@Override
//	public MergedReimbursmentPojo denyRequest(int reimbursementId) throws SystemException {
//		// TODO Auto-generated method stub
//		return managerDao.denyRequest(reimbursementId);
//	}
//
//	@Override
//	public MergedReimbursmentPojo approveOrDeny(MergedReimbursmentPojo mergedReimbursementPojo) throws SystemException {
//		// TODO Auto-generated method stub
//		return managerDao.approveOrDeny(mergedReimbursementPojo);
//	}

	@Override
	public MergedReimbursmentPojo fetchAPendingRequest(int reimbursementId) throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.fetchAPendingRequest(reimbursementId);
	}

	@Override
	public List<MergedReimbursmentPojo> fetchAllPendingReq() throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.fetchAllPendingReq();
	}

	@Override
	public List<MergedReimbursmentPojo> fetchAllResolveReq() throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.fetchAllResolveReq();
	}

	@Override
	public List<MergedReimbursmentPojo> viewReimbursementReq(int requestingEmployeeId) throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.viewReimbursementReq(requestingEmployeeId);
	}

	@Override
	public List<EmployeePojo> fetchAllEmployee() throws SystemException {
		// TODO Auto-generated method stub
		return managerDao.fetchAllEmployee();
	}

	
	
//	
//	public EmployeeServiceImpl() {
//		employeeDao = new ManagerJdbcDaoImpl();
//	}
//	
//
//	@Override
//	public ManagerPojo FetchOneEmployee(String employeeContact)throws SystemException {
//		
//		return employeeDao.FetchOneEmployee(employeeContact);
//	}
//
//	@Override
//	public ManagerPojo login(String employeeContact, String employeePassword)throws SystemException {
//		
//		return employeeDao.login(employeeContact, employeePassword);
//	}
//
//	@Override
//	public List<EmployeePojo> fetchAllTransactioninfo()throws SystemException {
//		
//		return employeeDao.fetchAllTransactioninfo();
//		
//	}
//
//	@Override
//	public EmployeePojo createNewCustomer(EmployeePojo customerPojo)throws SystemException {
//		
//		return employeeDao.createNewCustomer(customerPojo);
//	}
//
//	@Override
//	public ManagerPojo createNewEmployee(ManagerPojo employeePojo)throws SystemException {
//		
//		return employeeDao.createNewEmployee(employeePojo);
//	}

	
	
	
	
}

