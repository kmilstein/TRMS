package TRMS;

import TRMS.controller.AuthController;
import TRMS.controller.EmployeeController;
import TRMS.controller.TrainingController;
import io.javalin.Javalin;

public class ServerDriver {
	
	private static AuthController authController = new AuthController();
	private static EmployeeController empController = new EmployeeController();
	private static TrainingController trainingController = new TrainingController();
	private static final String EMPLOYEE_PATH = "/employee";
	private static final String TRAINING_PATH = "/training";
	private static final String LOGIN_PATH = "/login";
	private static final String FORM_PATH = "/form";
	private static final String PENDING_REQUESTS = "/approver";
	
	public static void main(String[] args) {
		Javalin app = Javalin.create( config -> {
			config.addStaticFiles("/public");
		}).start(9090);
		app.get("/", ctx -> ctx.redirect("LandingPage.html"));
		app.get(EMPLOYEE_PATH, ctx -> empController.getAllEmployees(ctx));
		app.get(TRAINING_PATH, ctx -> trainingController.getAllTrainings(ctx));
		app.post(LOGIN_PATH, ctx -> authController.login(ctx));
		app.get(LOGIN_PATH, ctx -> authController.checkUser(ctx));
		app.post(FORM_PATH, ctx -> trainingController.processForm(ctx));
		app.get(PENDING_REQUESTS, ctx -> trainingController.getPendingRequests(ctx));
		app.get("/approve/:id", ctx -> trainingController.approveRequests(ctx));
		app.get("/deny/:id", ctx -> trainingController.denyRequests(ctx));
		app.post("/submit-grade", ctx -> trainingController.updateGrade(ctx));
		app.get("/funds", ctx -> empController.getAvailableFunds(ctx));
	}
}
