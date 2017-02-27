package de.unipaderborn.visuflow.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;



public class VFEdgeTest {
	int id;
	VFNode s;
	VFNode d;
VFEdge ve=new VFEdge(id, s, d);
		

	@Test
	public void testGetId() {
		Assert.assertNotNull(ve.getId());
	}


	@Test
	public void testGetSource() {
		Assert.assertEquals(s,ve.getSource());
	}


	@Test
	public void testGetDestination() {
		Assert.assertEquals(d,ve.getDestination());
	}
	

}
