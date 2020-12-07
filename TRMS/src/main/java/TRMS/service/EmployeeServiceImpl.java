package TRMS.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import TRMS.dao.Dao;
import TRMS.dao.EmployeeDao;
import TRMS.dao.TrainingTypeDao;
import TRMS.pojos.Employee;
import TRMS.pojos.Training;
import TRMS.pojos.TrainingType;

public class EmployeeServiceImpl implements EmployeeService {
		
	private EmployeeDao empDao = new EmployeeDao();
	private TrainingService trainingService = new TrainingServiceImpl();
	private Dao<TrainingType> typeDao = new TrainingTypeDao();
	
	public double calculateAvailableReimbursement(String email) {
		double balance = 1000;
		double current = 0;
		Optional<Employee> e = empDao.get(email);
		Employee emp = e.orElseGet(() -> new Employee(0, "employee does not exist"));
		
		List<Training> trainingList = trainingService.findAllTrainings();
		
		for (Training t : trainingList) {
			if (t.getEmployeeId() == emp.getEmployeeId() && t.getTrainingStatus() == 6 
					&& t.getDate().getYear() == (LocalDate.now().getYear())) {
				Optional<TrainingType> tt = typeDao.get(t.getTrainingTypeId());
				TrainingType type = tt.orElseGet(() -> new TrainingType());
				current = t.getCost()*type.getPercentCovered()/100;
				balance -= current;
				if (balance < 0) {
					balance = 0;
				}
			}
		}
		return balance;
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
