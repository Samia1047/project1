package dao;

import java.util.List;

import exception.SystemException;

import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.MergedReimbursmentPojo;



public interface ManagerDao {
	//READ FROM MANAGER  TABLE
	ManagerPojo fetchOneManager(String employeeContact)throws SystemException;
	
	ManagerPojo login(String managerContact, String managerPassword)throws SystemException;
	ManagerPojo managerViewinfo(String managerContact)throws SystemException;
	
 
	// Add/insert to Resolved Reimbursement Table
	MergedReimbursmentPojo approveOrDeny(MergedReimbursmentPojo resolvedReimbursmentPojo)throws SystemException;
	//  Deny Pending Reimbursement Request
	//MergedReimbursmentPojo deleteRequest(int reimbursementId)throws SystemException;
	// Approve or deny pending reimbursement request
	//MergedReimbursmentPojo approveOrDeny(MergedReimbursmentPojo mergedReimbursementPojo) throws SystemException;
	//Fetch/read a pending request
	MergedReimbursmentPojo fetchAPendingRequest(int reimbursementId) throws SystemException;
//	imp• View all pending requests of all employees. 
	List<MergedReimbursmentPojo> fetchAllPendingReq()throws SystemException;
//	imp• View all resolved requests of all employees.
	 List<MergedReimbursmentPojo> fetchAllResolveReq()throws SystemException;
	
	//• View reimbursement requests of a specific employee.
	 List<MergedReimbursmentPojo> viewReimbursementReq(int requestingEmployeeId)throws SystemException;
	
	//read
		List<EmployeePojo> fetchAllEmployee()throws SystemException;
	


	
}
