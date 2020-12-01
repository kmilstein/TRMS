package TRMS.controller;

import java.util.List;

import TRMS.pojos.Employee;
import TRMS.service.EmployeeService;
import TRMS.service.EmployeeServiceImpl;
import io.javalin.http.Context;

public class EmployeeController {
	
	private EmployeeService empService = new EmployeeServiceImpl();
	
	public void getAllEmployees(Context ctx) {
		List<Employee> empList = empService.findAllEmployees();
		
		ctx.json(empList);
	}

}
