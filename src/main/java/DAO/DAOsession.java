package DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.Request;
import models.User;
import utils.HibernateSessionFactory;

public class DAOsession implements DAO {

	public void createRequest(Request request) {
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.save(request);
			tx.commit();
			
		} catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		
	}

	public List<Request> getAllRequests() {
		Session s = null;
		Transaction tx = null;
		List<Request>  requests = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			requests = s.createQuery("FROM models.Request", Request.class).getResultList();
			tx.commit();
		} catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} 
		return requests;
	}

	public List<Request> getAllRequests(int approval) {
		Session s = null;
		Transaction tx = null;
		List<Request>  requests = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			requests = s.createQuery("FROM models.Request R WHERE R.approval="+approval, Request.class).getResultList();
			tx.commit();
		} catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return requests;
	}

	public List<Request> getRequestsbyUser(User user) {
		Session s = null;
		Transaction tx = null;
		List<Request>  requests = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			requests = s.createQuery("FROM models.Request R WHERE R.user_Id="+user.getId(), Request.class).getResultList();
			tx.commit();
		} catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return requests;
	}

	public Request getRequestbyId(int Id) {
		Session s = null;
		Transaction tx = null;
		Request request = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			request = s.get(Request.class, Id);
			tx.commit();
		} catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return request;
	}

	public void updateRequest(Request request) {
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.update(request);
			tx.commit();
			
		} catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	}

	public void deleteRequest(Request request) {
		// TODO Auto-generated method stub
		
	}

	public User getUser(int Id) {
		Session s = null;
		Transaction tx = null;
		User user = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			user = s.get(User.class, Id);
			tx.commit();
			
		} catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return user;
	}

	public User getUser(String email) {
		Session s = null;
		Transaction tx = null;
		User user = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			String sql = "FROM models.User U WHERE U.email= :email";
	
			Query<User> query = s.createQuery(sql, User.class);
			query.setParameter("email",email);
			user = (User) query.getSingleResult();
			
		} catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
			tx.commit();
		} finally {
			s.close();
		}
		return user;
	}
	
	public List<User> getAllUsers() {
		Session s = null;
		Transaction tx = null;
		List<User> users = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			users = s.createQuery("FROM models.User", User.class).getResultList();
			tx.commit();
		} catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return users;
	}

}
