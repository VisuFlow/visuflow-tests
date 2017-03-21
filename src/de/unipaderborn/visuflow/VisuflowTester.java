package de.unipaderborn.visuflow;

import org.junit.Assert;
import org.junit.Test;

public class VisuflowTester {

	@Test
	public void testGetDefault() {
		Assert.assertNotNull(Visuflow.getDefault());
	}

	@Test
	public void testGetLogger() {
		Visuflow V = Visuflow.getDefault();
		Assert.assertNotNull(V.getLogger());

	}

}
