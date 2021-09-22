package Javalin;

import io.javalin.Javalin;

public class Driver {
	
	
	
	public static void main(String[] args) {
		
		//creates local host to receive requests from Postman
		Javalin app = Javalin.create().start(7000);
		
		app.get("/manager/:id/requests", Controller.fetchUserRequests);
		
		app.get("/manager/requests/:id", Controller.fetchRequestbyId);
		
		app.put("/manager/requests/:id", Controller.reviewRequest);
		
		app.get("/manager/requests", Controller.fetchAllRequets);
		
		app.get("/manager/requests/:approval", Controller.fetchSortRequets);
		
		app.get("/user/myrequests", Controller.fetchMyRequests);
		
		app.get("user/myrequests/:id", Controller.fetchMyRequest);
		
		app.put("user/myrequests/:id", Controller.updateMyRequest);
		
		app.post("/user/newrequest", Controller.newRequest);
		
		app.post("/login", Controller.login);
		
		app.exception(null, null);
		
		app.error(0, null);
	}
}
