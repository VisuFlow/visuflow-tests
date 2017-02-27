package de.unipaderborn.visuflow.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import soot.SootClass;

public class VFClassTest {
   private static final SootClass Someclass=null;

   @Test
	public void testVFClass() {
	  
	}

	@Test
	public void testGetSootClass() {
	
	}

	@Test
	public void testGetMethods() {
		VFClass v=new VFClass(Someclass);
		Assert.assertNotNull(v.getMethods());
	}

}
