package TRMS.service;

import java.util.List;

import TRMS.pojos.Employee;

public interface EmployeeService {
	
	Employee findEmployeeById(long id);
	
	List<Employee> findAllEmployees();
	
	void saveEmployee(Employee e);
	
	void updateEmployee(long id, Employee e);
	
	public void deleteEmployeeById(Employee e);

}
