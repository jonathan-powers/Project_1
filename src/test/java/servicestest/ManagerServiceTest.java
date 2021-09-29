package servicestest;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import DAO.DAOsession;
import org.junit.Assert;
import org.junit.BeforeClass;

import models.Request;
import models.User;

public class ManagerServiceTest {
	
	@InjectMocks
	private static services.ManagerService ManagerService;
	
	@Mock
	private DAOsession mockdao;

	{
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeClass
	public static void setUp() {
		ManagerService = new services.ManagerService();
	}
	
	@Test
	public void getReviewedRequestsTest() {
		Mockito.when(mockdao.getAllRequests()).thenReturn(
				Arrays.asList(
						new Request(1, 0, 0, null, null, 1),
						new Request(2, 0, 0, null, null, -1),
						new Request(3, 0, 0, null, null, 1),
						new Request(4, 0, 0, null, null, -1),
						new Request(5, 0, 0, null, null, 0),
						new Request(6, 0, 0, null, null, 0),
						new Request(7, 0, 0, null, null, 0)
						)
				);
		List<Request> requests = ManagerService.getReviewedRequests();
		
		Assert.assertEquals(4, requests.size());
	}
	
	@Test
	public void getPendingRequestsTest() {
		Mockito.when(mockdao.getAllRequests()).thenReturn(
				Arrays.asList(
						new Request(1, 0, 0, null, null, 1),
						new Request(2, 0, 0, null, null, -1),
						new Request(3, 0, 0, null, null, 1),
						new Request(4, 0, 0, null, null, -1),
						new Request(5, 0, 0, null, null, 0),
						new Request(6, 0, 0, null, null, 0),
						new Request(7, 0, 0, null, null, 0)
						)
				);

		
		List<Request> requests = ManagerService.getPendingRequests();
		
		Assert.assertEquals(3, requests.size());
	}
	
	@Test
	public void findAverageTest() {
		Mockito.when(mockdao.getAllRequests()).thenReturn(
				Arrays.asList(
						new Request(1, 0, 10, null, null, 1),
						new Request(2, 0, 10, null, null, -1),
						new Request(3, 0, 12, null, null, 1),
						new Request(4, 0, 13, null, null, -1),
						new Request(5, 0, 15, null, null, 0),
						new Request(6, 0, 15, null, null, 0),
						new Request(7, 0, 20, null, null, 0)
						)
				);
		double average = ManagerService.findAverage();
		
		Assert.assertEquals(13.5, average,0.1);
	}
	
	@Test
	public void findMaxTest() {
		Mockito.when(mockdao.getAllRequests()).thenReturn(
				Arrays.asList(
						new Request(1, 0, 10, null, null, 1),
						new Request(2, 0, 10, null, null, -1),
						new Request(3, 0, 12, null, null, 1),
						new Request(4, 0, 13, null, null, -1),
						new Request(5, 0, 15, null, null, 0),
						new Request(6, 0, 15, null, null, 0),
						new Request(7, 0, 20, null, null, 0)
						)
				);
		double max = ManagerService.findMax();
		
		Assert.assertEquals(20, max, 0.1);
	}
	
	@Test
	public void findMostTest() {
		Mockito.when(mockdao.getAllUsers()).thenReturn(
				Arrays.asList(
						new User(1, null, null, null, false),
						new User(2, null, null, null, false),
						new User(3, null, null, null, false)
						)
				);
		
		Mockito.when(mockdao.getAllRequests()).thenReturn(
				Arrays.asList(
						new Request(1, 1, 10, null, null, 1),
						new Request(2, 2, 10, null, null, -1),
						new Request(3, 3, 12, null, null, 1),
						new Request(4, 1, 13, null, null, -1),
						new Request(5, 2, 15, null, null, 0),
						new Request(6, 3, 15, null, null, 0),
						new Request(7, 1, 20, null, null, 0)
						)
				);
		int user = ManagerService.findMost();
		
		Assert.assertEquals(1, user);
	}
}
