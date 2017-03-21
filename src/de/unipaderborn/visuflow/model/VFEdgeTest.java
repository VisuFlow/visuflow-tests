package de.unipaderborn.visuflow.model;
import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockUnit;
import static org.junit.Assert.assertEquals;

import org.junit.Test;



public class VFEdgeTest {

	@Test
	public void testConstructor() {
		VFUnit unitSrc = createMockUnit("a.b.c.Foo.bar.return");
		VFUnit unitDst = createMockUnit("d.b.c.Foo.bar.return");
		VFNode src = new VFNode(unitSrc, 0);
		VFNode dst = new VFNode(unitDst, 1);
		VFEdge edge = new VFEdge(123, src, dst);
		assertEquals(123, edge.getId());
		assertEquals(src, edge.getSource());
		assertEquals(dst, edge.getDestination());
	}

	@Test
	public void testSetGetId() {
		VFUnit unitSrc = createMockUnit("a.b.c.Foo.bar.return");
		VFUnit unitDst = createMockUnit("d.b.c.Foo.bar.return");
		VFNode src = new VFNode(unitSrc, 0);
		VFNode dst = new VFNode(unitDst, 1);
		VFEdge edge = new VFEdge(123, src, dst);
		assertEquals(123, edge.getId());
		edge.setId(456);
		assertEquals(456, edge.getId());
	}

	@Test
	public void testSetGetSource() {
		VFUnit unitSrc = createMockUnit("a.b.c.Foo.bar.return");
		VFUnit unitDst = createMockUnit("d.b.c.Foo.bar.return");
		VFNode src = new VFNode(unitSrc, 0);
		VFNode dst = new VFNode(unitDst, 1);
		VFEdge edge = new VFEdge(123, src, dst);
		assertEquals(src, edge.getSource());
		edge.setSource(dst);
		assertEquals(dst, edge.getSource());
	}

	@Test
	public void testSetGetDestination() {
		VFUnit unitSrc = createMockUnit("a.b.c.Foo.bar.return");
		VFUnit unitDst = createMockUnit("d.b.c.Foo.bar.return");
		VFNode src = new VFNode(unitSrc, 0);
		VFNode dst = new VFNode(unitDst, 1);
		VFEdge edge = new VFEdge(123, src, dst);
		assertEquals(dst, edge.getDestination());
		edge.setDestination(src);
		assertEquals(src, edge.getDestination());
	}
}
