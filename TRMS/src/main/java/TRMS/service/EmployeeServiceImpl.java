package TRMS.service;

import java.util.List;
import java.util.Optional;

import TRMS.dao.Dao;
import TRMS.dao.EmployeeDao;
import TRMS.pojos.Employee;
import TRMS.pojos.Training;

public class EmployeeServiceImpl implements EmployeeService {
		
	private Dao<Employee> empDao = new EmployeeDao();
	private TrainingService trainingService = new TrainingServiceImpl();
	
	public double calculateAvailableReimbursement(long id) {
		double balance;
		double recieved;
		double currentRow;
		List<Training> trainingList = trainingService.findAllTrainings();
		return 0.0;
	}

	@Override
	public Employee findEmployeeById(long id) {
		Optional<Employee> emp = empDao.get(id);
		
		return emp.orElseGet(() -> new Employee(0, "employee does not exist"));
	}

	@Override
	public List<Employee> findAllEmployees() {
		return empDao.getAll();
	}

	@Override
	public void saveEmployee(Employee e) {
		empDao.save(e);
	}

	@Override
	public void updateEmployee(long id, Employee e) {
		empDao.update(id, e);
	}

	@Override
	public void deleteEmployeeById(Employee e) {
		empDao.delete(e);
	}
}
