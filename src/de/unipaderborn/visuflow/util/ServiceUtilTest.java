package de.unipaderborn.visuflow.util;

import org.junit.Assert;
import org.junit.Test;
import org.osgi.framework.ServiceException;
import org.osgi.service.event.EventAdmin;

public class ServiceUtilTest {

    @Test
    public void testGetService() {
        EventAdmin ea = ServiceUtil.getService(EventAdmin.class, 1000);
        Assert.assertNotNull(ea);
    }

    @Test(expected=ServiceException.class)
    public void testServiceNotFound() {
        // StringBuilder does not make sense here, so it should throw an ServiceException
        Object service = ServiceUtil.getService(StringBuilder.class, 1);
        Assert.assertNull(service);
    }
}
