package servicestest;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import DAO.DAOsession;
import models.Request;

public class UserServiceTest {
	
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
}
