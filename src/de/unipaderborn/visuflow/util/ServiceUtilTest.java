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
	
	@Test(expected=ServiceException.class)
	public void testServiceNotFound() {
		// StringBuilder does not make sense here, so it should throw an Exception
		Object service = ServiceUtil.getService(StringBuilder.class);
		Assert.assertNull(service);
	}
}
