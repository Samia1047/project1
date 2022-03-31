package service;

import java.util.List;

import exception.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.MergedReimbursmentPojo;


public interface ManagerService {

	  //fetch a manager
		ManagerPojo fetchOneManager(String employeeContact)throws SystemException;
		
		ManagerPojo login(String managerContact, String managerPassword)throws SystemException;
		ManagerPojo managerViewinfo(String managerContact)throws SystemException;
		
	 
		// add/delete in two Reimbursement table
		MergedReimbursmentPojo approveOrDeny(MergedReimbursmentPojo resolvedReimbursmentPojo)throws SystemException;
		
		//Fetch/read a pending request
		MergedReimbursmentPojo fetchAPendingRequest(int reimbursementId) throws SystemException;
//		imp• View all pending requests of all employees. 
		List<MergedReimbursmentPojo> fetchAllPendingReq()throws SystemException;
//		imp• View all resolved requests of all employees.
		 List<MergedReimbursmentPojo> fetchAllResolveReq()throws SystemException;
		
		//• View reimbursement requests of a specific employee.
		 List<MergedReimbursmentPojo> viewReimbursementReq(int requestingEmployeeId)throws SystemException;
		
		//read
			List<EmployeePojo> fetchAllEmployee()throws SystemException;
	
	



}
