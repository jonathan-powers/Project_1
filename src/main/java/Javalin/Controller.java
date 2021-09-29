package Javalin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import io.javalin.http.Handler;
import models.Request;
import models.User;
import services.ManagerService;
import services.UserService;

public class Controller {

	public static Handler fetchAllRequests = ctx -> {
		ManagerService service = new ManagerService();
		List<Request> Requests = new ArrayList<Request>();
		Requests = service.getAllRequests();

		ctx.status(200);
		ctx.json(Requests);
	};
	
	public static Handler fetchReviewedRequests = ctx -> {
		ManagerService service = new ManagerService();
		List<Request> Requests = new ArrayList<Request>();
		Requests = service.getReviewedRequests();

		ctx.status(200);
		ctx.json(Requests);
	};
	
	public static Handler fetchPendingRequests = ctx -> {
		ManagerService service = new ManagerService();
		List<Request> Requests = new ArrayList<Request>();
		Requests = service.getPendingRequests();

		ctx.status(200);
		ctx.json(Requests);
	};
	
	@SuppressWarnings("rawtypes")
	public static Handler fetchStats = ctx -> {
		ManagerService service = new ManagerService();
		Map Stats = new HashMap();
		Stats = service.getStats();

		ctx.status(200);
		ctx.json(Stats);
	};

	@SuppressWarnings("unchecked")
	public static Handler login = ctx -> {

		HashMap<String, String> credentials = new HashMap<String, String>();
		credentials = ctx.bodyAsClass(credentials.getClass());

		UserService service = new UserService();
		User user = service.getUser(credentials.get("email"));

		if (user.getPassword().equals(credentials.get("password"))) {

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
		
		HttpSession session = ctx.req.getSession(false);
		int Id = (int) session.getAttribute("Id");
		
		Requests = service.getMyRequests(Id);

		ctx.status(200);
		ctx.json(Requests);
	};
	
	public static Handler fetchMyReviewedRequests = ctx -> {
		UserService service = new UserService();
		List<Request> Requests = new ArrayList<Request>();
		
		HttpSession session = ctx.req.getSession(false);
		int Id = (int) session.getAttribute("Id");
		
		Requests = service.getMyReviewedRequests(Id);

		ctx.status(200);
		ctx.json(Requests);
	};
	
	public static Handler fetchMyPendingRequests = ctx -> {
		UserService service = new UserService();
		List<Request> Requests = new ArrayList<Request>();
		
		HttpSession session = ctx.req.getSession(false);
		int Id = (int) session.getAttribute("Id");
		
		Requests = service.getMyPendingRequests(Id);

		ctx.status(200);
		ctx.json(Requests);
	};

	public static Handler newRequest = ctx -> {
		UserService service = new UserService();

		Request request = ctx.bodyAsClass(Request.class);
		
		HttpSession session = ctx.req.getSession(false);
		int Id = (int) session.getAttribute("Id");
		
		request.setUserId(Id);

		service.newRequest(request);

		ctx.status(200);
	};

	public static Handler updateMyRequest = ctx -> {
		UserService service = new UserService();

		Request request = ctx.bodyAsClass(Request.class);

		service.updateRequest(request);
		
		ctx.status(200);
	};
	
	@SuppressWarnings("rawtypes")
	public static Handler getSession = ctx -> {
		HttpSession session = ctx.req.getSession(false);
		int Id = (int) session.getAttribute("Id");
		boolean manager = (boolean) session.getAttribute("Manager");
		
		Map<String, Comparable> sessionData = new HashMap<String, Comparable>();
		sessionData.put("Id", Id);
		sessionData.put("Manager", manager);
		
		ctx.json(sessionData);
	};
	
	public static Handler setRequestId = ctx -> {
		HttpSession session = ctx.req.getSession(false);
		
		session.setAttribute("Request Id", ctx.pathParam(":id"));
		System.out.println(session.getAttribute("Request Id"));
	};
	
	public static Handler getRequest = ctx -> {
		HttpSession session = ctx.req.getSession(false);
		
		System.out.println(session.getAttribute("Request Id") + "" + session.getAttribute("Id"));
		int Id = Integer.parseInt((String) session.getAttribute("Request Id"));
		
		UserService service = new UserService();
		
		Request request = service.getMyRequest(Id);
		
		ctx.status(200);
		ctx.json(request);
	};
	
	public static Handler approve = ctx -> {
		ManagerService service = new ManagerService();

		Request request = ctx.bodyAsClass(Request.class);

		service.approveRequest(request);
		
		ctx.status(200);
	};
	
	public static Handler deny = ctx -> {
		ManagerService service = new ManagerService();

		Request request = ctx.bodyAsClass(Request.class);

		service.denyRequest(request);
		
		ctx.status(200);
	};
	
	public static Handler preflight = ctx -> {
		ctx.res.addHeader("Access-Control-Allow-Methods", "PUT");
		ctx.status(200);
	};

}
