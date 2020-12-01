package TRMS.service;

import java.util.List;

import TRMS.pojos.Training;
import TRMS.pojos.TrainingInfo;


public interface TrainingService {
	
	Training findTrainingById(long id);
	
	List<Training> findAllTrainings();
	
	void saveTraining(Training t, TrainingInfo ti);
	
	void updateTraining(long id, Training t, TrainingInfo ti);
	
	public void deleteEmployeeById(Training t, TrainingInfo ti);

}
