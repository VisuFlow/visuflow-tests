package de.unipaderborn.visuflow.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import de.unipaderborn.visuflow.model.graph.ControlFlowGraph;
import soot.Body;
import soot.SootMethod;

public class VFMethodTest {
	protected SootMethod wrapped;
	private Body body;
	private ControlFlowGraph controlFlowGraph;
//	@Test
//	public void testGetVfClass() {
//		VFMethod v= new VFMethod(wrapped);
//		Assert.assertNotNull(v.getVfClass());
//	}


	@Test
	public void testGetId() {
		VFMethod v= new VFMethod(wrapped);
		Assert.assertNotNull(v.getId());
	}
	@Test
	public void testgetControlFlowGraph() {
		VFMethod v= new VFMethod(wrapped);
		Assert.assertEquals(controlFlowGraph,v.getControlFlowGraph());
	}

	
	
	@Test
	public void testGetSootMethod() {
		VFMethod v= new VFMethod(wrapped);
		Assert.assertEquals(wrapped,v.getSootMethod());
	}

	@Test
	public void testGetUnits() {
		VFMethod v= new VFMethod(wrapped);
		Assert.assertNotNull(v.getUnits());
		
	}

	@Test
	public void testGetBody() {
		VFMethod v= new VFMethod(wrapped);
		Assert.assertEquals(body,v.getBody());
	}

	

//	@Test
//	public void testGetControlFlowGraph() {
//		VFMethod v= new VFMethod(wrapped);
//		Assert.assertNotNull(v.getControlFlowGraph());
//	}

	@Test
	public void testToString() {
		
	}

	@Test
	public void testGetIncomingEdges() {
		VFMethod v= new VFMethod(wrapped);
		Assert.assertNotNull(v.getIncomingEdges());
	}


	@Test
	public void testAddIncomingEdge() {
		
	}

}
