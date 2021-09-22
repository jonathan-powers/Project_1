package services;

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
	
	public Request getMyRequest(int Id) {
		DAOsession dao = new DAOsession();
		return dao.getRequestbyId(Id);
	}
	
	public void newRequest(Request request) {
		DAOsession dao = new DAOsession();
		dao.createRequest(request);
	}
	
	public void updateRequest(Request request) {
		DAOsession dao = new DAOsession();
		dao.updateRequest(request);
	}
}
