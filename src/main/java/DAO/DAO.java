package DAO;

import java.util.List;

import models.Request;
import models.User;

public interface DAO {
	
	public void createRequest(Request request);
	
	public List<Request> getAllRequests();
	
	public List<Request> getAllRequests(int approval);
	
	public List<Request> getRequestsbyUser(User user);
	
	public Request getRequestbyId(int Id);
	
	public void updateRequest(Request request);
	
	public void deleteRequest(Request request);
	
	public User getUser(int Id);
	
	public User getUser(String email);
}
