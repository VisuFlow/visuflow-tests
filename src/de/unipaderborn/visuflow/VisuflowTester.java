package de.unipaderborn.visuflow;

import org.junit.Assert;
import org.junit.Test;



public class VisuflowTester {


	@Test
	public void testGetDefault() {
		Assert.assertNull(Visuflow.getDefault());
	}

	@Test
	public void testGetLogger() {
		Visuflow V=new Visuflow();
		Assert.assertNull(V.getLogger());
		
	}

}
