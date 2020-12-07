package TRMS.controller;

import java.util.List;

import TRMS.pojos.Employee;
import TRMS.service.EmployeeService;
import TRMS.service.EmployeeServiceImpl;
import io.javalin.http.Context;

public class EmployeeController {
	
	private EmployeeServiceImpl empService = new EmployeeServiceImpl();
	private AuthController authController = new AuthController();
	
	public void getAllEmployees(Context ctx) {
		
		List<Employee> empList = empService.findAllEmployees();
		
		ctx.json(empList);
	}

	public void getAvailableFunds(Context ctx) {
		String email = authController.checkUser(ctx);
		
		double funds = empService.calculateAvailableReimbursement(email);
		
		ctx.json(funds);
	}

}
