import java.io.ObjectInputFilter.Config;
import java.time.LocalDateTime;

import io.javalin.Javalin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.MergedReimbursmentPojo;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import service.ManagerService;
import service.ManagerServiceImpl;

public class ErsMain {

	public static void main(String[] args) {
		EmployeeService employeeService = new EmployeeServiceImpl();
		ManagerService  managerService  = new ManagerServiceImpl();
		
		
       Javalin myServer = Javalin.create((config)-> config.enableCorsForAllOrigins()).start(4040);
       System.out.println("Server listening at port 4040...");
       
   
       myServer.get("/get-ers-obj", (ctx)-> {
    	   EmployeePojo employee = new EmployeePojo(10,"root","Jack","Miller","Jack@gmail.com","35 boulovard","");
    	   ctx.json(employee);
       });
       
       		// GET - fetch data - select(DAO)
    		// DELETE - delete data - delete(DAO)
    		// POST - add data - insert(DAO)
    		// PUT - update data - update(DAO)
       //login
       
  
       /////////////////EMPLOYEE///////////
       myServer.get("/employees/login/{empcontact}/{pswd}", (ctx)->{
    	     
       String employeeContact = ctx.pathParam("empcontact");
       String employeePassword = ctx.pathParam("pswd");
       EmployeePojo loginEmployee = employeeService.login(employeeContact, employeePassword);
       ctx.json(loginEmployee);
       });
       //emp view their info
       myServer.get("/employees/view/{empid}", (ctx)->{
    	   String employeeId = ctx.pathParam("empid");
    	   EmployeePojo viewDetails = employeeService.employeeInfo(Integer.parseInt(employeeId));
    	   ctx.json(viewDetails);
       });
   	

       //----employee submit request 
       myServer.post("/employees/submit", (ctx)->{
    	   MergedReimbursmentPojo newReq = ctx.bodyAsClass(MergedReimbursmentPojo.class);
    	   MergedReimbursmentPojo empSubmit  = employeeService.submitReimbursementReq(newReq);
    	   ctx.json(empSubmit);
       });
       //emp view reimbursement pending
       myServer.get("/employees/pen-req/{rid}", (ctx)-> {
    	   String rimId = ctx.pathParam("rid");
    	   MergedReimbursmentPojo pending = employeeService.viewReimbursementPendingReq(Integer.parseInt(rimId));
    	   ctx.json(pending);
       });
       
       //emp view reimbursement resolved
       myServer.get("/employees/resolved/{rid}", (ctx)->{
    	   String rimId = ctx.pathParam("rid");
    	   MergedReimbursmentPojo resolved = employeeService.viewReimbursementResolveReq(Integer.parseInt(rimId));
    	   ctx.json(resolved);
       });
       
       //emp view their info
       myServer.get("/employees/view/{empcontact}", (ctx)->{
    	   String employeeContact = ctx.pathParam("empcontact");
    	   EmployeePojo viewDetails = employeeService.employeeViewDetails(employeeContact);
    	   ctx.json(viewDetails);
       });
     //----employee update their info
       myServer.put("/employees/updateEmp", (ctx)-> {
    	   EmployeePojo employeePojo = ctx.bodyAsClass(EmployeePojo.class);
    	   EmployeePojo returnedUpdateEmployee = employeeService.updateEmployeeInfo(employeePojo);
    	   ctx.json(returnedUpdateEmployee);
       });
       
       
       ////////////MANAGER/////////////
       
       //login
       myServer.get("/managers/login/{mancontact}/{pswd}", (ctx)->{
  	     
           String managerContact = ctx.pathParam("mancontact");
           String managerPassword = ctx.pathParam("pswd");
           ManagerPojo loginManager = managerService.login(managerContact, managerPassword);
           ctx.json(loginManager);
           });
       //View the manager home page
       //fethOneManager
       myServer.get("/managers/view/{manContact}", (ctx)-> {
    	String managerContact = ctx.pathParam("manContact");
    	ManagerPojo viewHomepage = managerService.managerViewinfo(managerContact);
    	ctx.json(viewHomepage);
       });
       //manager Approve or deny
       myServer.post("/managers/resolve", (ctx)-> {
    	   MergedReimbursmentPojo resolvedReimbursmentPojo = ctx.bodyAsClass(MergedReimbursmentPojo.class);
    	   MergedReimbursmentPojo returnResolved = managerService.approveOrDeny(resolvedReimbursmentPojo);
    	   ctx.json(returnResolved);
       });
//       delete don't need this anymore
//       myServer.delete("/api/managers/{rid}", (ctx)->{
//    	   String reimbursementId = ctx.pathParam("rid");
//    	   MergedReimbursmentPojo denyRequest = managerService.denyRequest(Integer.parseInt(reimbursementId));
//    	   ctx.json(denyRequest);
//    	   
//       });
       myServer.get("/managers/pendReq", (ctx)-> {
    	   List<MergedReimbursmentPojo> allPendingReq = managerService.fetchAllPendingReq();
    	   ctx.json(allPendingReq);
       });
       
       myServer.get("/managers/resolvedReq", (ctx)-> {
    	   List<MergedReimbursmentPojo> allResolvedReq = managerService.fetchAllResolveReq();
    	   ctx.json(allResolvedReq);
       });
       
       myServer.get("/managers/viewAllReq/{reqEmpId}", (ctx)-> {
    	   String requestingEmployeeId = ctx.pathParam("reqEmpId");
    	   List<MergedReimbursmentPojo> allReq = managerService.viewReimbursementReq(Integer.parseInt(requestingEmployeeId));
    	   ctx.json(allReq);
       });
       myServer.get("/managers/fetchAllEmp", (ctx)-> {
    	   List<EmployeePojo> allEmployees = managerService.fetchAllEmployee();
    	   ctx.json(allEmployees);
       });
       
    // this is the catch block for SystemException
    		myServer.exception(SystemException.class,(se, ctx)->{
    			Map<String, String> error = new HashMap<String, String>();
    			error.put("message", se.getMessage());
    			error.put("datetime", LocalDateTime.now()+"");
    			ctx.json(error);
    		} );
    		
//    		myServer.exception(BooksNotFoundException.class,(be, ctx)->{
//    			Map<String, String> error = new HashMap<String, String>();
//    			error.put("message", be.getMessage());
//    			error.put("datetime", LocalDateTime.now()+"");
//    			ctx.json(error);
//    		} );
       
	}
	
}
