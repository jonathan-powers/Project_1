package services;

import java.util.ArrayList;
import java.util.List;

import DAO.DAOsession;
import models.Request;
import models.User;

public class UserService {
	
	private DAOsession dao = new DAOsession();
	
	public User getUser(String email) {
		return dao.getUser(email);
	}
	
	public List<Request> getMyRequests(int Id) {
		User user = dao.getUser(Id);
		return dao.getRequestsbyUser(user);
	}
	
	public List<Request> getMyReviewedRequests(int Id) {
		User user = dao.getUser(Id);
		List<Request> allRequests = dao.getRequestsbyUser(user);
		List<Request> requests = new ArrayList<Request>();
		for (Request request : allRequests) {
			if(request.getApproval() != 0) {
				requests.add(request);
			}
		}
		return requests;
	}
	
	public List<Request> getMyPendingRequests(int Id) {
		User user = dao.getUser(Id);
		List<Request> allRequests = dao.getRequestsbyUser(user);
		List<Request> requests = new ArrayList<Request>();
		for (Request request : allRequests) {
			if(request.getApproval() == 0) {
				requests.add(request);
			}
		}
		return requests;
	}
	
	public Request getMyRequest(int Id) {
		return dao.getRequestbyId(Id);
	}
	
	public void newRequest(Request request) {
		request.setApproval(0);
		dao.createRequest(request);
	}
	
	public void updateRequest(Request request) {
		dao.updateRequest(request);
	}
}
