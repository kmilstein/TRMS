package TRMS.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import TRMS.pojos.Employee;

public class AuthServiceDB implements AuthService {

	private Map<String, String> tokenRepo = new HashMap<>();
	private EmployeeService empService = new EmployeeServiceImpl();

	@Override
	public boolean authenticateUser(String email, String password) {
		List<Employee> empList = empService.findAllEmployees();
		
		for (Employee emp : empList) {
			if (emp.getEmail().equals(email) && emp.getPassword().equals(password)) {
				return true;
			}
		}
				
		return false;
	}

	@Override
	public String createToken(String username) {
		String token = Integer.toString((int) (Math.random()*10000));
		tokenRepo.put(token, username);
		return token;
	}

	@Override
	public String validateToken(String token) {
		return tokenRepo.get(token);
	}

}
