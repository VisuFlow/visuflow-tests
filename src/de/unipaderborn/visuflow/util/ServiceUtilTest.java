package de.unipaderborn.visuflow.util;

import org.junit.Assert;
import org.junit.Test;
import org.osgi.framework.ServiceException;
import org.osgi.service.event.EventAdmin;

public class ServiceUtilTest {

	@Test
	public void testGetService() {
		EventAdmin ea = ServiceUtil.getService(EventAdmin.class);
		Assert.assertNotNull(ea);
	}

	@Test
	public void testGetServiceWithTimeout() {
		EventAdmin ea = ServiceUtil.getService(EventAdmin.class, 1000);
		Assert.assertNotNull(ea);
	}

	@Test
	public void testRegisterService() {
		try {
			ServiceUtil.getService(MockService.class, 1);
		} catch(ServiceException e) {
			// expected exception, all good!
		}

		ServiceUtil.registerService(MockService.class, new MockServiceImpl(), null);
		MockService service = ServiceUtil.getService(MockService.class, 1);
		Assert.assertNotNull(service);
	}

	@Test(expected=ServiceException.class)
	public void testServiceNotFound() {
		// StringBuilder does not make sense here, so it should throw an ServiceException
		ServiceUtil.getService(StringBuilder.class, 1);
	}

	public interface MockService {
		public void Mock();
	}

	public class MockServiceImpl implements MockService {
		@Override
		public void Mock() {
		}
	}
}
