
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import exception.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.MergedReimbursmentPojo;


public class ManagerJdbcDaoImpl  implements ManagerDao{

	public static final Logger LOG = LogManager.getLogger(ManagerJdbcDaoImpl.class);

	@Override
	public ManagerPojo fetchOneManager(String managerContact) throws SystemException {
		// TODO Auto-generated method stub
		LOG.info("Entered FetchOneManager() in DAO");
		ManagerPojo managerPojo = null;
		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM manager WHERE manager_contact=" +"'"+managerContact+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				managerPojo = new ManagerPojo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited FetchOneManager() in DAO");

		return managerPojo;
	}

	@Override
	public ManagerPojo login(String managerContact, String managerPassword) throws SystemException {

		LOG.info("Entered login() in DAO");
		//		
		Connection conn = DBUtil.obtainConnection();
		ManagerPojo managerPojo = null;

		try {
			Statement stmt = conn.createStatement();
			managerPojo = fetchOneManager(managerContact);

			String query = "SELECT * FROM manager WHERE manager_contact="+"'"+managerPojo.getManagerContact()+"'"+ "AND manager_password=" +"'"+managerPojo.getManagerPassword()+"'";

			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				managerPojo = new ManagerPojo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));

			}
		}  catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited login() in DAO");
		return managerPojo;

	}

	@Override
	public ManagerPojo managerViewinfo(String managerContact) throws SystemException {
		LOG.info("Entered managerViewinfo() in DAO");
		ManagerPojo managerPojo = null;
		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = " SELECT * FROM manager WHERE manager_contact=" + "'" + managerContact + "'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {

				managerPojo = new ManagerPojo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));

			}

		} catch (SQLException e) {

			throw new SystemException();
		}

		LOG.info("Exited managerViewinfo() in DAO");
		return managerPojo;

	}

	

	@Override
	public MergedReimbursmentPojo approveOrDeny(MergedReimbursmentPojo resolvedReimbursmentPojo) throws SystemException {
		LOG.info("Entered approveRequest() in DAO");
		
		
		Connection conn = DBUtil.obtainConnection();
		
		try {
			Statement stmt = conn.createStatement();
			String query1 = "INSERT INTO resolved_reimbursment(requesting_employee_id,reimbursement_amount,request_approved) VALUES("
			    +resolvedReimbursmentPojo.getRequestingEmployeeId()+","+resolvedReimbursmentPojo.getReimbursementAmount()+",'"+resolvedReimbursmentPojo.getRequestApproved()+"'" +")";
				
			String query = "DELETE FROM pending_reimbursment WHERE reimbursement_id="+resolvedReimbursmentPojo.getReimbursementId();
			// wrapp next 2 lines inside a transaction				
			conn.setAutoCommit(false);
			
			int rowDelete = stmt.executeUpdate(query);
			int rows = stmt.executeUpdate(query1);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				System.out.println(" failed...");
			} catch (SQLException e1) {
				throw new SystemException();
			}
			throw new SystemException();
			
		}
		LOG.info("Exited approveRequest() in DAO");
		return resolvedReimbursmentPojo; 
		
	}
		

		

//	@Override
//	public MergedReimbursmentPojo deleteRequest(int reimbursementId) throws SystemException {
//		LOG.info("Entered denyRequest() in DAO");
//		MergedReimbursmentPojo pendingReimbursmentPojo = null;
//		Connection conn = DBUtil.obtainConnection();
//		try {
//			Statement stmt = conn.createStatement();
//			pendingReimbursmentPojo = fetchAPendingRequest(reimbursementId);
//			
//		} catch (SQLException e) {
//			throw new SystemException();
//		}
//		LOG.info("Exited denyRequest() in DAO");
//		return pendingReimbursmentPojo;
//		
//	}
//	@Override
//	public MergedReimbursmentPojo approveOrDeny(MergedReimbursmentPojo mergedReimbursementPojo) throws SystemException{
//		LOG.info("Entered approveOrDeny() in DAO");
//		Connection conn = DBUtil.obtainConnection();
//		
//		try {
//			
//			conn.setAutoCommit(false);
//			mergedReimbursementPojo	= denyRequest(mergedReimbursementPojo.getReimbursementId());
//			if(mergedReimbursementPojo.getRequestApproved()) {
//				mergedReimbursementPojo.setRequestApproved(true);
//			} else {
//				mergedReimbursementPojo.setRequestApproved(false);
//			}
//			approveRequest(new MergedReimbursmentPojo(mergedReimbursementPojo.getRequestingEmployeeId(),mergedReimbursementPojo.getReimbursementAmount(),mergedReimbursementPojo.getRequestApproved()));
//			
//			conn.commit();	
//		} catch (SQLException e) {
//			try {
//				conn.rollback();
//				System.out.println(" failed...");
//			} catch (SQLException e1) {
//				throw new SystemException();
//			}
//			throw new SystemException();
//			
//		}
//
//		LOG.info("Exited approveOrDeny() in DAO");
//		return mergedReimbursementPojo;
//	}
	
	@Override
	public MergedReimbursmentPojo fetchAPendingRequest(int reimbursementId) throws SystemException {
		LOG.info("Entered fetchAPendingRequest() in Manager DAO");
		MergedReimbursmentPojo pendingReimbursmentPojo = null;
		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM pending_reimbursment WHERE reimbursement_id="+reimbursementId;
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				pendingReimbursmentPojo = new MergedReimbursmentPojo(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4));
				
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited fetchAPendingRequest() in Manager DAO");
		return pendingReimbursmentPojo;
		
		
		
	}

	@Override
	public List<MergedReimbursmentPojo> fetchAllPendingReq() throws SystemException {
		LOG.info("Entered fetchAllPendingReq() in Manager DAO");
		List<MergedReimbursmentPojo> allPendingReq = new ArrayList<MergedReimbursmentPojo>();
		
		Connection conn = DBUtil.obtainConnection();
	try {
		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM pending_reimbursment";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			MergedReimbursmentPojo pendingReimbursmentPojo = new MergedReimbursmentPojo(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4));
			allPendingReq.add(pendingReimbursmentPojo);
		}

	   } catch (SQLException e) {
		throw new SystemException();
	   }
		LOG.info("Exited fetchAllPendingReq() in Manager DAO");
		return allPendingReq;
	}

	@Override
	public List<MergedReimbursmentPojo> fetchAllResolveReq() throws SystemException {
		LOG.info("Entered fetchAllResolveReq() in Manager DAO");
		List<MergedReimbursmentPojo> allResolvedReq = new ArrayList<MergedReimbursmentPojo>();
		Connection conn = DBUtil.obtainConnection();
		try{
		Statement stmt = conn.createStatement();
		String query = "select * from resolved_reimbursment";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			MergedReimbursmentPojo resolvedReimbursementPojo = new MergedReimbursmentPojo(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getBoolean(4),rs.getString(5));
			allResolvedReq.add(resolvedReimbursementPojo);
		}
		}catch (SQLException e) {
			throw new SystemException();
		   }
		LOG.info("Exited fetchAllResolveReq() in Manager DAO");
		return allResolvedReq;
	}

	@Override
	public List<MergedReimbursmentPojo> viewReimbursementReq(int requestingEmployeeId) throws SystemException {
		LOG.info("Entered viewReimbursementReq() in Manager DAO");
		List<MergedReimbursmentPojo> allReimbursementReq = new ArrayList<MergedReimbursmentPojo>();
		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			String query1 = "SELECT * FROM pending_reimbursment WHERE requesting_employee_id="+requestingEmployeeId;
			ResultSet rs = stmt.executeQuery(query1);
			while(rs.next()) {
				MergedReimbursmentPojo pendingReimbursmentPojo = new MergedReimbursmentPojo(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4));
				allReimbursementReq.add(pendingReimbursmentPojo);
			}
			String query2 = "select * from resolved_reimbursment WHERE requesting_employee_id="+requestingEmployeeId;
			ResultSet rs1 = stmt.executeQuery(query2);
			while(rs1.next()) {
				MergedReimbursmentPojo resolvedReimbursementPojo = new MergedReimbursmentPojo(rs1.getInt(1),rs1.getInt(2),rs1.getDouble(3),rs1.getBoolean(4),rs1.getString(5));
				allReimbursementReq.add(resolvedReimbursementPojo);
			}
//			"SELECT resolved_reimbursement_id, reimbursement_details.reimbursement_id, requesting_employee_id,
//			 reimbursement_amount, reimbursement_pending, request_approved, date_of_request, date_resolved FROM reimbursement_details 
//			LEFT JOIN resolved_reimbursements ON reimbursement_details.reimbursement_id=resolved_reimbursements.reimbursement_id 
//			WHERE requesting_employee_id="+employeeId+" ORDER BY reimbursement_details.reimbursement_id";

		   } catch (SQLException e) {
			throw new SystemException();
		   }
		LOG.info("Exited viewReimbursementReq() in Manager DAO");
		return allReimbursementReq;
	}

	@Override
	public List<EmployeePojo> fetchAllEmployee() throws SystemException {
		LOG.info("Entered fetchAllEmployee() in Manager DAO");
		List<EmployeePojo> allEmployees = new ArrayList<EmployeePojo>();
		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();


			String query = "SELECT * FROM employee";
			ResultSet rs = stmt.executeQuery(query);
			//iterate throw the resultset
			while (rs.next()) {
				// copy each record into the employeePojo object
				EmployeePojo employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3),
				rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));


				allEmployees.add(employeePojo);
			}

		} catch (SQLException e) {
			throw new SystemException();
		}


		LOG.info("Exited fetchAllEmployee() in Manager DAO");
		return allEmployees;

	}
	
	


	






}
