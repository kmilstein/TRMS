package TRMS.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import TRMS.dao.Dao;
import TRMS.dao.EmployeeDao;
import TRMS.dao.GradingFormatDao;
import TRMS.dao.TrainingDao;
import TRMS.dao.TrainingInfoDao;
import TRMS.dao.TrainingTypeDao;
import TRMS.pojos.Employee;
import TRMS.pojos.GradingFormat;
import TRMS.pojos.Training;
import TRMS.pojos.TrainingInfo;
import TRMS.pojos.TrainingType;
import TRMS.pojos.WebTraining;
import TRMS.util.TrainingException;

public class TrainingServiceImpl implements TrainingService {
		
	private TrainingDao trainingDao = new TrainingDao();
	private Dao<TrainingInfo> trainingInfoDao = new TrainingInfoDao();
	private Dao<GradingFormat> formatDao = new GradingFormatDao();
	private EmployeeDao empDao = new EmployeeDao();
	private Dao<TrainingType> trainingTypeDao = new TrainingTypeDao();
	private List<String> validationErrorList =  new ArrayList<>();
	
	@Override
	public Training findTrainingById(long id) {
		Optional<Training> t = trainingDao.get(id);
		Training training =  t.orElseGet(() -> new Training(0, 0));
		Optional<TrainingInfo> ti = trainingInfoDao.get(id);
		TrainingInfo trainingInfo = ti.orElseGet(() -> new TrainingInfo());
		training.setTrainingInfo(trainingInfo);
		
		return training;
	}

	@Override
	public List<Training> findAllTrainings() {
		List<Training> trainingList = trainingDao.getAll();
		
		for (Training training : trainingList) {
			Optional<TrainingInfo> ti = trainingInfoDao.get(training.getTrainingId());
			TrainingInfo trainingInfo = ti.orElseGet(() -> new TrainingInfo());
			training.setTrainingInfo(trainingInfo);
		}
		return trainingList;
	}
	
	public List<Training> findAllTrainingsByEmail(String email) {
		List<Training> trainingList = this.findAllTrainings();
		List<Training> employeeTrainingList = new ArrayList<>();
		Optional<Employee> e = empDao.get(email);
		Employee emp = e.orElseGet(() -> new Employee(0, "Employee does not exist"));
		
		for (Training t : trainingList) {
			if (t.getEmployeeId() == emp.getEmployeeId()) {
				employeeTrainingList.add(t);
			}
		}
		return employeeTrainingList;
	}

	@Override
	public void saveTraining(Training t, TrainingInfo ti) {
		if (validateForeignKeys(t, ti)) {
			long id = trainingDao.saveWithReturnId(t);
			ti.setTrainingId(id);
			trainingInfoDao.save(ti);
		} else {
			throw new TrainingException(validationErrorList.toString());
		}

	}
	
	public void saveTraining(WebTraining webTraining) {
		Optional<Employee> e = empDao.get((webTraining.getEmail()));
		Employee emp = e.orElseGet(() -> new Employee(0, "Employee does not exist"));
		List<GradingFormat> gradingList = formatDao.getAll();
		long formatId = 1;
		for (GradingFormat format : gradingList) {
			if (format.getName().equals(webTraining.getGradeType())) {
				formatId = format.getGradingFormatId();
				break;
			}
		}
		long typeId = 1;
		List<TrainingType> typeList = trainingTypeDao.getAll();
		for (TrainingType type: typeList) {
			if (type.getName().equals(webTraining.getTrainingType())) {
				typeId = type.getTrainingTypeId();
				break;
			}
		}
		
		TrainingInfo info = new TrainingInfo(webTraining.getDescription(), LocalTime.now(), 
				webTraining.getLocation(), webTraining.getJustification());
		Training training = new Training(emp.getEmployeeId(), webTraining.getCost(), webTraining.getDate(), 
				formatId, typeId, emp.getRole(), info);
		this.saveTraining(training, info);
	}

	@Override
	public void updateTraining(long id, Training t, TrainingInfo ti) {
		if (validateForeignKeys(t, ti)) {
			trainingInfoDao.update(id, ti);
			trainingDao.update(id, t);
		} else {
			throw new TrainingException(validationErrorList.toString());
		}
	}

	@Override
	public void deleteEmployeeById(Training t, TrainingInfo ti) {
		if (validateForeignKeys(t, ti)) {
			trainingInfoDao.delete(ti);
			trainingDao.delete(t);
		} else {
			throw new TrainingException(validationErrorList.toString());
		}
	}
	
	private boolean validateForeignKeys(Training t, TrainingInfo ti) {
		boolean valid = true;
		Optional<GradingFormat> gf = formatDao.get(t.getGradingFormatId());
		GradingFormat format =  gf.orElseGet(() -> new GradingFormat(0, "grading format does not exist", 0));
		
		if (format.getGradingFormatId() == 0) {
			validationErrorList.add(format.getName());
			valid = false;
		}
		
		Optional<Employee> e = empDao.get(t.getEmployeeId());
		Employee emp = e.orElseGet(() -> new Employee(0, "Employee does not exist"));
		
		if (emp.getEmployeeId() == 0) {
			validationErrorList.add(emp.getEmail());
			valid = false;
		}
		
		Optional<TrainingType> tt = trainingTypeDao.get(t.getTrainingTypeId());
		TrainingType type = tt.orElseGet(() -> new TrainingType(0, "Training type does not exist", 0));
		
		if (type.getTrainingTypeId() == 0) {
			validationErrorList.add(type.getName());
			valid = false;
		}
		
		if (t.getTrainingId() != ti.getTrainingId()) {
			validationErrorList.add("Training and TrainingInfo Ids do not match");
		}
		
		return valid;
	}
}