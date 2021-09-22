package services;

import java.util.List;

import DAO.DAOsession;
import models.Request;
import models.User;

public class ManagerService {
	
	public List<Request> getAllRequests(){
		DAOsession dao = new DAOsession();
		return dao.getAllRequests();
	}
	
	public List<Request> getSortRequest(int approval){
		DAOsession dao = new DAOsession();
		return dao.getAllRequests(approval);
	}
	
	public List<Request> getUserRequests(User user){
		DAOsession dao = new DAOsession();
		return dao.getRequestsbyUser(user);
	}
	
	public Request getRequest(int id) {
		DAOsession dao = new DAOsession();
		return dao.getRequestbyId(id);
	}
	
	public void updateRequest(Request request) {
		DAOsession dao = new DAOsession();
		dao.updateRequest(request);
	}

	public User getUser(int id) {
		DAOsession dao = new DAOsession();
		return dao.getUser(id);
	}

}
