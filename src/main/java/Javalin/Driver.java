package Javalin;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Driver {
	
	
	
	public static void main(String[] args) {
		
		//creates local host to receive requests from Postman
		Javalin app = Javalin.create().start(7000);
		
		app.get("/manager/requests", Controller.fetchAllRequests);
		
		app.get("/manager/requests/reviewed", Controller.fetchReviewedRequests);
		
		app.get("/manager/requests/pending", Controller.fetchPendingRequests);
		
		app.get("/manager/statistics", Controller.fetchStats);
		
		app.get("/user/myrequests", Controller.fetchMyRequests);
		
		app.get("/user/myrequests/reviewed", Controller.fetchMyReviewedRequests);
		
		app.get("/user/myrequests/pending", Controller.fetchMyPendingRequests);
		
		app.put("/user/myrequests/:id", Controller.updateMyRequest);
		
		app.post("/user/newrequest", Controller.newRequest);
		
		app.post("/login", Controller.login);
		
		app.get("/session", Controller.getSession);
		
		app.post("/requestId/:id", Controller.setRequestId);
		
		app.get("/request", Controller.getRequest);
		
		app.put("/request/approve", Controller.approve);
		
		app.put("/request/deny", Controller.deny);
		
		app.options("/request/approve", Controller.preflight);
		
		app.options("/request/deny", Controller.preflight);
		
		app.after(ctx -> {
			ctx.res.addHeader("Access-Control-Allow-Origin", "null");
			ctx.res.addHeader("Access-Control-Allow-Credentials", "true");
			ctx.res.addHeader("Access-Control-Expose-Headers", "*");
			ctx.res.addHeader("Access-Control-Allow-Headers", "*");
		});
		
		app.config.addStaticFiles("/static", Location.CLASSPATH);

		
		app.exception(null, null);
		
		app.error(0, null);
	}
}
