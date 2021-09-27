package services;

import java.util.ArrayList;
import java.util.List;

import DAO.DAOsession;
import models.Request;
import models.User;

public class UserService {
	
	public User getUser(String email) {
		DAOsession dao = new DAOsession();
		return dao.getUser(email);
	}
	
	public List<Request> getMyRequests(int Id) {
		DAOsession dao = new DAOsession();
		User user = dao.getUser(Id);
		return dao.getRequestsbyUser(user);
	}
	
	public List<Request> getMyReviewedRequests(int Id) {
		DAOsession dao = new DAOsession();
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
		DAOsession dao = new DAOsession();
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
		DAOsession dao = new DAOsession();
		return dao.getRequestbyId(Id);
	}
	
	public void newRequest(Request request) {
		DAOsession dao = new DAOsession();
		request.setApproval(0);
		dao.createRequest(request);
	}
	
	public void updateRequest(Request request) {
		DAOsession dao = new DAOsession();
		dao.updateRequest(request);
	}
}
