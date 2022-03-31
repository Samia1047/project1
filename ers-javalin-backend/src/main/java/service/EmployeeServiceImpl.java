package service;

import java.util.List;

import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
import exception.SystemException;
import pojo.EmployeePojo;
import pojo.MergedReimbursmentPojo;


public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao employeeDao;

	public EmployeeServiceImpl() {
		employeeDao = new EmployeeJdbcDaoImpl();
	}
	@Override
	public EmployeePojo employeeViewDetails(String employeeContact) throws SystemException {
		// TODO Auto-generated method stub
		return employeeDao.employeeViewDetails(employeeContact);
	}
	
	@Override
	public EmployeePojo login(String employeeContact, String employeePassword) throws SystemException {
		// TODO Auto-generated method stub
		return employeeDao.login(employeeContact, employeePassword);
	}

	@Override
	public MergedReimbursmentPojo submitReimbursementReq(MergedReimbursmentPojo pendingReimbursementPojo)
			throws SystemException {
		// TODO Auto-generated method stub
		return employeeDao.submitReimbursementReq(pendingReimbursementPojo);
	}

	@Override
	public MergedReimbursmentPojo viewReimbursementPendingReq(int reimbursementId) throws SystemException {
		// TODO Auto-generated method stub
		return employeeDao.viewReimbursementPendingReq(reimbursementId);
	}

	@Override
	public MergedReimbursmentPojo viewReimbursementResolveReq(int reimbursementId) throws SystemException {
		// TODO Auto-generated method stub
		return employeeDao.viewReimbursementResolveReq(reimbursementId);
	}



	@Override
	public EmployeePojo updateEmployeeInfo(EmployeePojo employeePojo) throws SystemException {
		// TODO Auto-generated method stub
		return employeeDao.updateEmployeeInfo(employeePojo);
	}
	@Override
	public EmployeePojo employeeInfo(int employeeId) throws SystemException {
		// TODO Auto-generated method stub
		return employeeDao.employeeInfo(employeeId);
	}

	

	

}
