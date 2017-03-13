package de.unipaderborn.visuflow;

import org.junit.Assert;
import org.junit.Test;

public class VisuflowTest {

	public static Visuflow vplugin;
	 Logger log;
	
	@Test
	public void testGetDefault() {
		Assert.assertEquals(vplugin,Visuflow.getDefault());
	}

	@Test
	public void testGetLogger() {
		Visuflow vf=new Visuflow();
		Assert.assertEquals(log,vf.getLogger());
	}

}
