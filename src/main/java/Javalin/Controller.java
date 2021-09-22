package Javalin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import io.javalin.http.Handler;
import models.Request;
import models.User;
import services.Login;
import services.ManagerService;
import services.UserService;

public class Controller {

	public static Handler fetchAllRequets = ctx -> {
		ManagerService service = new ManagerService();
		List<Request> Requests = new ArrayList<Request>();
		Requests = service.getAllRequests();

		ctx.status(200);
		ctx.json(Requests);
	};

	public static Handler fetchSortRequets = ctx -> {
		ManagerService service = new ManagerService();
		List<Request> Requests = new ArrayList<Request>();
		Requests = service.getSortRequest(Integer.parseInt(ctx.pathParam(":approval")));

		ctx.status(200);
		ctx.json(Requests);
	};

	public static Handler fetchUserRequests = ctx -> {
		ManagerService service = new ManagerService();
		List<Request> Requests = new ArrayList<Request>();

		User user = service.getUser(Integer.parseInt(ctx.pathParam(":id")));
		Requests = service.getUserRequests(user);

		ctx.status(200);
		ctx.json(Requests);
	};

	public static Handler fetchRequestbyId = ctx -> {
		ManagerService service = new ManagerService();
		Request request = new Request();

		request = service.getRequest(Integer.parseInt(ctx.pathParam(":id")));

		ctx.status(200);
		ctx.json(request);
	};

	public static Handler reviewRequest = ctx -> {
		UserService service = new UserService();

		Request request = ctx.bodyAsClass(Request.class);
		service.updateRequest(request);

		ctx.status(200);
	};

	@SuppressWarnings("unchecked")
	public static Handler login = ctx -> {

		HashMap<String, String> credentials = new HashMap<String, String>();
		credentials = ctx.bodyAsClass(credentials.getClass());

		UserService service = new UserService();
		User user = service.getUser(credentials.get("email"));
		System.out.println(user.getPassword() + "  " +credentials.get("password"));

		if (Login.login(user, credentials.get("password"))) {

			HttpSession session = ctx.req.getSession();

			session.setAttribute("Id", user.getId());
			session.setAttribute("Manager", user.isManager());

			ctx.status(200);
			ctx.json(user);
		} else {
			ctx.result("Incorrect credentials");
			ctx.status(400);
		}
	};

	public static Handler fetchMyRequests = ctx -> {
		UserService service = new UserService();
		List<Request> Requests = new ArrayList<Request>();
		
		ctx.req.getSession();

		int Id = ctx.sessionAttribute("Id");
		Requests = service.getMyRequests(Id);

		ctx.status(200);
		ctx.json(Requests);
	};

	public static Handler fetchMyRequest = ctx -> {
		UserService service = new UserService();
		Request request = new Request();

		request = service.getMyRequest(Integer.parseInt(ctx.pathParam(":id")));

		ctx.status(200);
		ctx.json(request);
	};

	public static Handler newRequest = ctx -> {
		UserService service = new UserService();

		Request request = ctx.bodyAsClass(Request.class);

		service.newRequest(request);

		ctx.status(200);
	};

	public static Handler updateMyRequest = ctx -> {
		UserService service = new UserService();

		Request request = ctx.bodyAsClass(Request.class);

		service.updateRequest(request);

		ctx.status(200);
	};

}
