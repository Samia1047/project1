package service;

import java.util.List;

import exception.SystemException;
import pojo.EmployeePojo;
import pojo.MergedReimbursmentPojo;


public interface EmployeeService {
	EmployeePojo employeeInfo(int employeeId) throws SystemException;
//	  View their information. ------fetchAnEmployee
	EmployeePojo employeeViewDetails(String employeeContact)throws SystemException;
//	Login. 
	EmployeePojo login(String employeeContact, String employeePassword)throws SystemException;	
//	 Submit a reimbursement request.
	MergedReimbursmentPojo submitReimbursementReq(MergedReimbursmentPojo pendingReimbursementPojo)throws SystemException;
//	imp• View their pending reimbursement requests. 
	
	MergedReimbursmentPojo viewReimbursementPendingReq(int reimbursementId)throws SystemException;
//	imp• View their resolved reimbursement requests.
	MergedReimbursmentPojo viewReimbursementResolveReq(int reimbursementId)throws SystemException;
		
//	• Update their information.-----update 
	
	EmployeePojo updateEmployeeInfo(EmployeePojo employeePojo)throws SystemException;

}
