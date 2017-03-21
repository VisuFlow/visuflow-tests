package de.unipaderborn.visuflow.model;

import org.junit.Assert;
import org.junit.Test;

public class VFClassTest {

	@Test
	public void testGetSootClass() {
		VFClass c = DataModelMockFactory.createMockClass("foo.Bar", "a", "b", "c");
		Assert.assertNotNull(c.getSootClass());
		Assert.assertEquals("foo.Bar", c.getSootClass().getName());
	}

	@Test
	public void testGetMethods() {
		VFClass c = DataModelMockFactory.createMockClass("foo.Bar", "a", "b", "c");
		Assert.assertNotNull(c.getMethods());
		Assert.assertEquals(3, c.getMethods().size());
	}

}
