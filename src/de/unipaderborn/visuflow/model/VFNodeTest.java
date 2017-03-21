package de.unipaderborn.visuflow.model;

import org.junit.Assert;
import org.junit.Test;


public class VFNodeTest {

	@Test
	public void testConstructor() {
		VFUnit unit = DataModelMockFactory.createMockUnit("a.b.c.Foo.bar.return");
		VFNode node = new VFNode(unit, 123);
		Assert.assertEquals(unit, node.getVFUnit());
		Assert.assertEquals(unit.getUnit(), node.getUnit());
		Assert.assertEquals(123, node.getId());
	}

	@Test
	public void testSetLabel() {
		VFNode node = new VFNode(null, 123);
		VFUnit unit = DataModelMockFactory.createMockUnit("a.b.c.Foo.bar.return");
		node.setLabel(unit);
		Assert.assertEquals(unit, node.getVFUnit());
		Assert.assertEquals(unit.getUnit(), node.getUnit());
		Assert.assertEquals(123, node.getId());
	}

	@Test
	public void testSetId() {
		VFNode node = new VFNode(null, 123);
		node.setId(456);
		Assert.assertEquals(456, node.getId());
	}
}
