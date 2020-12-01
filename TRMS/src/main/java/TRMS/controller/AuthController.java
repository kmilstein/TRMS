package TRMS.controller;

import TRMS.service.AuthService;
import TRMS.service.AuthServiceDB;
import io.javalin.http.Context;

public class AuthController {
	
	private AuthService auth = new AuthServiceDB();
	
	public void login(Context ctx) {
		String email = ctx.formParam("email");
		String password = ctx.formParam("password");
		boolean authenticated = auth.authenticateUser(email, password);
		
		if (authenticated) {
			//ctx.status(200);
			ctx.cookieStore("security", auth.createToken(email));
			ctx.redirect("dashboard.html");
		} else {
			//ctx.status(401);
			ctx.redirect("login.html?error=failed-login");
		}
		
	}
	
	public void checkUser(Context ctx) {
		//ctx.html(auth.validateToken(ctx.cookieStore("security")));
	}

}
