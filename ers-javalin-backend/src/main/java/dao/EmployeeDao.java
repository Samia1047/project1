package dao;



import exception.SystemException;
import pojo.EmployeePojo;
import pojo.MergedReimbursmentPojo;


public interface EmployeeDao {
	EmployeePojo employeeInfo(int employeeId) throws SystemException;
   //	Login. 
	EmployeePojo login(String employeeContact, String employeePassword)throws SystemException;	
//	 Submit a reimbursement request.
	MergedReimbursmentPojo submitReimbursementReq(MergedReimbursmentPojo pendingReimbursementPojo)throws SystemException;
//	imp• View their pending reimbursement requests. 
	
	MergedReimbursmentPojo viewReimbursementPendingReq(int reimbursementId)throws SystemException;
//	imp• View their resolved reimbursement requests.
	MergedReimbursmentPojo viewReimbursementResolveReq(int reimbursementId)throws SystemException;
	
//	 • View their information. ------fetchanemplyee 
	EmployeePojo employeeViewDetails(String employeeContact)throws SystemException;
	
//	• Update their information.-----update 
	
	EmployeePojo updateEmployeeInfo(EmployeePojo employeePojo)throws SystemException;
	

	

	

}
