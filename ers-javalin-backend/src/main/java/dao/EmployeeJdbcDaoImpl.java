package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import exception.SystemException;
import pojo.EmployeePojo;
import pojo.MergedReimbursmentPojo;



public class EmployeeJdbcDaoImpl implements EmployeeDao{

	public static final Logger LOG = LogManager.getLogger(EmployeeJdbcDaoImpl.class);
	
	@Override
	public EmployeePojo employeeInfo(int employeeId) throws SystemException {
		LOG.info("Entered employeeInfo() in DAO");
		EmployeePojo employeePojo = null;
		Connection conn = DBUtil.obtainConnection();
		
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM employee WHERE employee_id =" + employeeId ;
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				employeePojo = new EmployeePojo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			
			}
		} catch (SQLException e) {

			throw new SystemException();
		}
		LOG.info("Exited employeeInfo() in DAO");
		return employeePojo;
		
	}
	
	@Override
	public EmployeePojo employeeViewDetails(String employeeContact) throws SystemException {
		LOG.info("Entered employeeViewDetails() in DAO");
		EmployeePojo employeePojo = null;
		Connection conn = DBUtil.obtainConnection();
		
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM employee WHERE employee_contact=" + "'" + employeeContact + "'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				employeePojo = new EmployeePojo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			
			}
		} catch (SQLException e) {

			throw new SystemException();
		}
		LOG.info("Exited employeeViewDetails() in DAO");
		return employeePojo;
		
	}

	@Override
	public EmployeePojo login(String employeeContact, String employeePassword) throws SystemException {
		// TODO Auto-generated method stub
		
		LOG.info("Entered login() in DAO");
		EmployeePojo employeePojo = null;

		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			employeePojo = employeeViewDetails(employeeContact);
			String query = "SELECT * FROM employee WHERE employee_contact="+"'"+employeeContact+"'" +" AND employee_password="+"'"+employeePassword+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));

			}
		} catch (SQLException | SystemException e) {
			throw new SystemException();
		
		}
		LOG.info("Exited login() in DAO");
		return employeePojo;
		
	}


	@Override
	public MergedReimbursmentPojo submitReimbursementReq(MergedReimbursmentPojo pendingReimbursementPojo)throws SystemException {
		
		LOG.info("Entered submitReimbursementReq() in Emplyee DAO");
		Connection conn = DBUtil.obtainConnection();
		try {
		Statement stmt = conn.createStatement();
		String query = "INSERT INTO pending_reimbursment(requesting_employee_id,reimbursement_amount) VALUES("+pendingReimbursementPojo.getRequestingEmployeeId()+","+pendingReimbursementPojo.getReimbursementAmount()+")";
		int rows = stmt.executeUpdate(query);
		
		}catch(SQLException e){
			throw new SystemException();
		}
		LOG.info("Exited submitReimbursementReq() in Employee DAO");
		return pendingReimbursementPojo;
		
	}
	
	@Override
	public MergedReimbursmentPojo viewReimbursementPendingReq(int reimbursementId) throws SystemException {
		LOG.info("Entered viewReimbursementPendingReq() in Emplyee DAO");
		
		MergedReimbursmentPojo pendingReimbursmentPojo = null;
		Connection conn = DBUtil.obtainConnection();
		try{
		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM pending_reimbursment WHERE reimbursement_id="+reimbursementId;
		ResultSet rs = stmt.executeQuery(query);
		if(rs.next()) {
		pendingReimbursmentPojo = new MergedReimbursmentPojo(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getString(4));
		}	
		}catch(SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited viewReimbursementPendingReq() in Employee DAO");
		return pendingReimbursmentPojo;
		
	}

	@Override
	public MergedReimbursmentPojo viewReimbursementResolveReq(int reimbursementId) throws SystemException {
		LOG.info("Entered viewReimbursementResolveReq() in Emplyee DAO");
		MergedReimbursmentPojo resolvedReimbursementPojo = null;
		Connection conn = DBUtil.obtainConnection();
		try {
		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM resolved_reimbursment WHERE reimbursement_id=" + reimbursementId;
		ResultSet rs = stmt.executeQuery(query);
		if(rs.next()) {
			resolvedReimbursementPojo = new MergedReimbursmentPojo(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getBoolean(4),rs.getString(5));
					
		}
		}catch(SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited viewReimbursementResolveReq() in Employee DAO");
		return resolvedReimbursementPojo;
	}



	@Override
	public EmployeePojo updateEmployeeInfo(EmployeePojo employeePojo) throws SystemException {
		LOG.info("Entered updateEmployeeInfo() in Emplyee DAO");
		Connection conn = DBUtil.obtainConnection();
		try{
			Statement stmt = conn.createStatement();
			//String query = "UPDATE employee SET employee_address ='D drive lane',employee_contact='Jack@gmail.com' Where employee_id=3;
			String query = "UPDATE employee SET employee_password="+"'"+employeePojo.getEmployeePassword()+"'"+",employee_first_name="+"'"+employeePojo.getEmployeeFirstName()+"'"+",employee_last_name="+"'"+employeePojo.getEmployeeLastName()+"'"+",employee_contact="+"'"+employeePojo.getEmployeeContact()+"'"+",employee_address="+"'"+employeePojo.getEmployeeAddress()+"'"+" Where employee_id="+employeePojo.getEmployeeId();
			
			int rows = stmt.executeUpdate(query);
		}catch(SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited updateEmployeeInfo() in Employee DAO");
		return employeePojo;
	}
	
	

}
