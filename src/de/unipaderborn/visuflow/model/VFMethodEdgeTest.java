package de.unipaderborn.visuflow.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class VFMethodEdgeTest {
	int id;
	VFMethod s;
	VFMethod d;
	
	@Test
	public void testGetId() {
		VFMethodEdge vn=new VFMethodEdge(id, s, d);
		Assert.assertNotNull(vn.getId());
	}


	@Test
	public void testGetSourceMethod() {
		VFMethodEdge vn=new VFMethodEdge(id, s, d);
		Assert.assertEquals(s,vn.getSourceMethod());
	}

	@Test
	public void testGetDestMethod() {
		VFMethodEdge vn=new VFMethodEdge(id, s, d);
		Assert.assertEquals(d,vn.getDestMethod());
	}

	
	@Test
	public void testToString() {
		VFMethodEdge vn=new VFMethodEdge(id, s, d);
		Assert.assertNotNull(vn.toString());
	}

}
