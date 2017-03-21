package de.unipaderborn.visuflow.model;

import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockClass;
import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockMethod;
import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockUnit;
import static de.unipaderborn.visuflow.model.DataModelMockFactory.createSootMethod;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

import de.unipaderborn.visuflow.model.graph.ControlFlowGraph;
import soot.Body;
import soot.SootMethod;
import soot.jimple.Jimple;

public class VFMethodTest {
	private Body body;
	private ControlFlowGraph controlFlowGraph;

	@Test
	public void testSetGetVfClass() {
		VFClass vfClass = createMockClass("a.b.c.Foo", "foo");
		VFMethod vfMethod = vfClass.getMethods().get(0);
		vfMethod.setVfClass(vfClass);
		Assert.assertTrue(vfClass == vfMethod.getVfClass());
		Assert.assertTrue(vfClass.equals(vfMethod.getVfClass()));
	}

	@Test
	public void testGetId() {
		VFMethod vfMethod = createMockMethod("a.b.c.Foo", "foo");
		vfMethod.setId(123);
		Assert.assertEquals(123, vfMethod.getId());
	}

	@Test
	public void testgetControlFlowGraph() {
		VFMethod v = createMockMethod("a.b.c.Foo", "foo");
		Assert.assertEquals(controlFlowGraph,v.getControlFlowGraph());
	}

	@Test
	public void testGetSootMethod() {
		VFMethod vfMethod = createMockMethod("a.b.c.Foo", "foo");
		Assert.assertNotNull(vfMethod.getSootMethod());
		Assert.assertEquals("foo", vfMethod.getSootMethod().getName());
	}

	@Test
	public void testGetUnits() {
		VFMethod v = createMockMethod("a.b.c.Foo", "foo");
		Assert.assertNotNull(v.getUnits());
		Assert.assertTrue(v.getUnits().isEmpty());
	}

	@Test
	public void testGetBody() {
		VFMethod v = createMockMethod("a.b.c.Foo", "foo");
		Assert.assertEquals(body,v.getBody());
	}

	@Test
	public void testGetControlFlowGraph() {
		VFMethod v = createMockMethod("a.b.c.Foo", "foo");
		ControlFlowGraph cfg = new ControlFlowGraph();
		v.setControlFlowGraph(cfg);
		Assert.assertEquals(cfg, v.getControlFlowGraph());
	}

	@Test
	public void testToString() {
		VFMethod vfMethod = new VFMethod(null);
		Assert.assertEquals("de.unipaderborn.visuflow.model.VFMethod@" + Integer.toHexString(vfMethod.hashCode()), vfMethod.toString());

		vfMethod = createMockMethod("a.b.c.Foo", "foo");
		Assert.assertEquals("foo", vfMethod.toString());
	}

	@Test
	public void testSetGetIncomingEdges() {
		VFMethod v = createMockMethod("a.b.c.Foo", "foo");
		Assert.assertNotNull(v.getIncomingEdges());

		VFUnit unit = createMockUnit("a.b.c.Foo.bar.return");
		List<VFUnit> edges = Collections.singletonList(unit);
		v.setIncomingEdges(edges);
		Assert.assertEquals(1, v.getIncomingEdges().size());
		Assert.assertEquals(edges, v.getIncomingEdges());
		Assert.assertEquals(unit, v.getIncomingEdges().get(0));
	}


	@Test
	public void testAddIncomingEdge() {
		VFMethod v = createMockMethod("a.b.c.Foo", "foo");
		VFUnit unitA = createMockUnit("a.b.c.Foo.bar.return");
		Assert.assertTrue(v.addIncomingEdge(unitA));
		Assert.assertEquals(1, v.getIncomingEdges().size());
		Assert.assertFalse(v.addIncomingEdge(unitA));
		Assert.assertEquals(1, v.getIncomingEdges().size());

		VFUnit unitB = createMockUnit("a.b.c.Foo.bar.r1 = $r2");
		Assert.assertTrue(v.addIncomingEdge(unitB));
		Assert.assertEquals(2, v.getIncomingEdges().size());
	}

	@Test
	public void testGetUnitAfter() {
		VFMethod method = createMockMethod("a.b.c.Foo", "foo");
		VFUnit unitA = createMockUnit("a.b.c.Foo.bar.r1 = $r2");
		VFUnit unitB = createMockUnit("a.b.c.Foo.bar.r2 = $r3");
		VFUnit unitC = createMockUnit("a.b.c.Foo.bar.return");

		method.getUnits().add(unitA);
		method.getUnits().add(unitB);
		method.getUnits().add(unitC);

		Assert.assertEquals(unitB, method.getUnitAfter(unitA, 1));
		Assert.assertEquals(unitC, method.getUnitAfter(unitA, 2));
	}

	@Test(expected=UnitNotFoundException.class)
	public void testGetUnitExceptionWithEmptyUnitList() {
		VFMethod method = createMockMethod("a.b.c.Foo", "foo");
		VFUnit unitA = createMockUnit("a.b.c.Foo.bar.r1 = $r2");
		method.getUnitAfter(unitA, 1);
	}

	@Test(expected=NoSuchElementException.class)
	public void testGetUnitExceptionLastElement() {
		VFMethod method = createMockMethod("a.b.c.Foo", "foo");
		VFUnit unitA = createMockUnit("a.b.c.Foo.bar.r1 = $r2");
		VFUnit unitB = createMockUnit("a.b.c.Foo.bar.r2 = $r3");
		method.getUnits().add(unitA);
		method.getUnits().add(unitB);
		method.getUnitAfter(unitB, 1);
	}

	@Test(expected=NoSuchElementException.class)
	public void testGetUnitExceptionWithWrongIndex() {
		VFMethod method = createMockMethod("a.b.c.Foo", "foo");
		VFUnit unitA = createMockUnit("a.b.c.Foo.bar.r1 = $r2");
		method.getUnits().add(unitA);
		method.getUnitAfter(unitA, 1000);
	}

	@Test
	public void testSetGetBody() {
		SootMethod m = createSootMethod("foo");
		Body body = Jimple.v().newBody(m);
		VFMethod method = createMockMethod("a.b.c.Foo", "foo");
		method.setBody(body);
		Assert.assertEquals(body, method.getBody());
	}

	@Test
	public void test2ParamConstructor() {
		SootMethod m = createSootMethod("foo");
		VFMethod method = new VFMethod(123, m);
		Assert.assertEquals(123, method.getId());
		Assert.assertEquals(m, method.getSootMethod());
	}

}
