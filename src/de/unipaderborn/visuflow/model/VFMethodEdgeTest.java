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
	public void testsetSourceMethod(VFMethod srcMethod) {
		this.s = srcMethod;
	}
	@Test
	public void testGetDestMethod() {
		VFMethodEdge vn=new VFMethodEdge(id, s, d);
		Assert.assertEquals(d,vn.getDestMethod());
	}
	public void testsetDestMethod(VFMethod dest) {
		this.d = dest;
	}
	
	@Test
	public void testToString() {
		VFMethodEdge vn=new VFMethodEdge(id, s, d);
		Assert.assertNotNull(vn.toString());
	}

}
