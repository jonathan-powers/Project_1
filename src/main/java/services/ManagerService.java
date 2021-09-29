package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.DAOsession;
import models.Request;
import models.User;

public class ManagerService {
	
	private DAOsession dao = new DAOsession();

	public List<Request> getAllRequests() {
		return dao.getAllRequests();
	}

	public List<Request> getReviewedRequests() {
		List<Request> allRequests = dao.getAllRequests();
		List<Request> requests = new ArrayList<Request>();
		for (Request request : allRequests) {
			if (request.getApproval() != 0) {
				requests.add(request);
			}
		}
		return requests;
	}

	public List<Request> getPendingRequests() {
		List<Request> allRequests = dao.getAllRequests();
		List<Request> requests = new ArrayList<Request>();
		for (Request request : allRequests) {
			if (request.getApproval() == 0) {
				requests.add(request);
			}
		}
		return requests;
	}

	public List<Request> getUserRequests(User user) {
		return dao.getRequestsbyUser(user);
	}

	public Request getRequest(int id) {
		return dao.getRequestbyId(id);
	}

	public void updateRequest(Request request) {
		dao.updateRequest(request);
	}

	public User getUser(int id) {
		return dao.getUser(id);
	}

	public void approveRequest(Request request) {
		request.setApproval(1);
		dao.updateRequest(request);
	}

	public void denyRequest(Request request) {
		request.setApproval(-1);
		dao.updateRequest(request);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getStats() {
		Map stats = new HashMap();
		double average = this.findAverage();
		double max = this.findMax();
		double most = this.findMost();
		
		stats.put("Average", average);
		stats.put("Max", max);
		stats.put("Most", most);
		
		return stats;
	}

	public double findAverage() {
		List<Request> allRequests = dao.getAllRequests();
		double sum = 0;
		for (Request request : allRequests) {
			sum = sum + request.getAmount();
		}
		
		return (sum / allRequests.size());
	}

	public double findMax() {
		List<Request> allRequests = dao.getAllRequests();
		double max = 0;
		for (Request request : allRequests) {
			if (request.getAmount() > max) {
				max = request.getAmount();
			}
		}
		return max;
	}

	public int findMost() {
		List<Request> allRequests = dao.getAllRequests();
		List<User> users = dao.getAllUsers();
		List< Double> useramounts = new ArrayList< Double>();
		int user_index = 0;
		for (User user : users) {
			double sum = 0;
			for (Request request : allRequests) {
				if (user.getId() == request.getUser_Id()) {
					sum = sum + request.getAmount();
				}
			}
			useramounts.add(sum);
		}
		double max = 0;
		for (int i = 0; i<useramounts.size(); i++) {
			if (useramounts.get(i) > max) {
				max = useramounts.get(i);
				user_index = i;
			}
		}
		return users.get(user_index).getId();
	}
}
