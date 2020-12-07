package TRMS.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import TRMS.dao.EmployeeDao;
import TRMS.pojos.Employee;
import TRMS.pojos.Training;
import TRMS.pojos.TrainingInfo;
import TRMS.pojos.WebTraining;
import TRMS.service.TrainingService;
import TRMS.service.TrainingServiceImpl;
import io.javalin.http.Context;

public class TrainingController {

	private TrainingServiceImpl trainingService = new TrainingServiceImpl();
	// private AuthService auth = new AuthServiceDB();
	private AuthController authController = new AuthController();
	private EmployeeDao empDao = new EmployeeDao();

	public void getAllTrainings(Context ctx) {

		String email = authController.checkUser(ctx);

		List<Training> trainingList = trainingService.findAllTrainingsByEmail(email);

		ctx.json(trainingList);
	}

	public void processForm(Context ctx) {

		String email = authController.checkUser(ctx);

		String description = ctx.formParam("description");
		LocalDate date = Date.valueOf(ctx.formParam("date")).toLocalDate();
		String location = ctx.formParam("location");
		double cost = Double.valueOf(ctx.formParam("cost"));
		String gradeType = ctx.formParam("grade-format");
		String trainingType = ctx.formParam("training-type");
		String justification = ctx.formParam("justification");

		WebTraining training = new WebTraining(email, description, date, location, cost, gradeType, trainingType,
				justification);
		trainingService.saveTraining(training);

		ctx.redirect("dashboard.html");

	}

	public void getPendingRequests(Context ctx) {

		String email = authController.checkUser(ctx);

		Optional<Employee> e = empDao.get(email);
		Employee approver = e.orElseGet(() -> new Employee(0, "Employee does not exist"));

		List<Training> trainingList = trainingService.findAllTrainings();
		List<Training> pendingList = new ArrayList<>();

		for (Training t : trainingList) {
			e = empDao.get(t.getEmployeeId());
			Employee emp = e.orElseGet(() -> new Employee(0, "Employee does not exist"));

			if (emp.getSupervisorId() == approver.getEmployeeId() && t.getTrainingStatus() == 1) {
				pendingList.add(t);
			} else if (emp.getDeptHeadId() == approver.getEmployeeId() && t.getTrainingStatus() == 2) {
				pendingList.add(t);
			} else if (emp.getBenCoId() == approver.getEmployeeId()
					&& (t.getTrainingStatus() == 3 || t.getTrainingStatus() == 5)) {
				pendingList.add(t);
			}
		}

		ctx.json(pendingList);
	}

	public void approveRequests(Context ctx) {

		String trainingId = ctx.pathParam("id");

		Training t = trainingService.findTrainingById(Long.parseLong(trainingId));
		t.setTrainingStatus(t.getTrainingStatus() + 1);
		TrainingInfo i = t.getTrainingInfo();

		trainingService.updateTraining(Long.parseLong(trainingId), t, i);

		ctx.redirect("/approver-request.html");
	}

	public void submitGrade(Context ctx) {
		String trainingId = ctx.pathParam("id");

		// ctx.redirect("/grade/"+trainingId);

	}

	public void updateGrade(Context ctx) {
		String id = ctx.formParam("id");
		int grade = Integer.valueOf(ctx.formParam("final-grade"));

		Training t = trainingService.findTrainingById(Long.parseLong(id));
		t.setGrade(grade);
		t.setTrainingStatus(t.getTrainingStatus() + 1);
		TrainingInfo i = t.getTrainingInfo();

		trainingService.updateTraining(Long.parseLong(id), t, i);

		ctx.redirect("/dashboard.html");
	}

	public void denyRequests(Context ctx) {
		String trainingId = ctx.pathParam("id");

		Training t = trainingService.findTrainingById(Long.parseLong(trainingId));
		t.setTrainingStatus(7);
		TrainingInfo i = t.getTrainingInfo();

		trainingService.updateTraining(Long.parseLong(trainingId), t, i);

		ctx.redirect("/approver-request.html");
	}
}
