package de.unipaderborn.visuflow.model;
import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockMethod;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VFMethodEdgeTest {

	@Test
	public void testConstructor() {
		VFMethod src = createMockMethod("a.b.c.Foo", "src");
		VFMethod dst = createMockMethod("a.b.c.Foo", "dst");
		VFMethodEdge edge = new VFMethodEdge(123, src, dst);
		assertEquals(123, edge.getId());
		assertEquals(src, edge.getSourceMethod());
		assertEquals(dst, edge.getDestMethod());
	}

	@Test
	public void testSetGetId() {
		VFMethod src = createMockMethod("a.b.c.Foo", "src");
		VFMethod dst = createMockMethod("a.b.c.Foo", "dst");
		VFMethodEdge edge = new VFMethodEdge(123, src, dst);
		assertEquals(123, edge.getId());
		edge.setId(456);
		assertEquals(456, edge.getId());
	}

	@Test
	public void testSetGetSourceMethod() {
		VFMethod src = createMockMethod("a.b.c.Foo", "src");
		VFMethod dst = createMockMethod("a.b.c.Foo", "dst");
		VFMethodEdge edge = new VFMethodEdge(123, src, dst);
		assertEquals(src, edge.getSourceMethod());
		edge.setSourceMethod(dst);
		assertEquals(dst, edge.getSourceMethod());
	}

	@Test
	public void testSetGetDestMethod() {
		VFMethod src = createMockMethod("a.b.c.Foo", "src");
		VFMethod dst = createMockMethod("a.b.c.Foo", "dst");
		VFMethodEdge edge = new VFMethodEdge(123, src, dst);
		assertEquals(dst, edge.getDestMethod());
		edge.setDestMethod(src);
		assertEquals(src, edge.getDestMethod());
	}


	@Test
	public void testToString() {
		VFMethod src = createMockMethod("a.b.c.Foo", "src");
		VFMethod dst = createMockMethod("a.b.c.Foo", "dst");
		VFMethodEdge edge = new VFMethodEdge(123, src, dst);
		assertEquals("Source: src | Destination: dst", edge.toString());
	}

}
