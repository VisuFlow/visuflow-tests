package de.unipaderborn.visuflow.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VFNodeTest {
	VFUnit vfUnit;
	int id;
	

	@Test
	public void testGetVFUnit() {
		VFNode vn=new VFNode(vfUnit,id);
		Assert.assertEquals(vfUnit,vn.getVFUnit());
	}



	@Test
	public void testGetId() {
		VFNode vn=new VFNode(vfUnit,id);
		Assert.assertNotNull(vn.getId());
	}

}
