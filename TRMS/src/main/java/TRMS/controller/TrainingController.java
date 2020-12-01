package TRMS.controller;

import java.util.List;

import TRMS.pojos.Training;
import TRMS.service.TrainingService;
import TRMS.service.TrainingServiceImpl;
import io.javalin.http.Context;

public class TrainingController {
	
	private TrainingService trainingService = new TrainingServiceImpl();
	
	public void getAllTrainings(Context ctx) {
		List<Training> trainingList = trainingService.findAllTrainings();
		
		ctx.json(trainingList);
	}

}
