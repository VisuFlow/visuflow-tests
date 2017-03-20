package de.unipaderborn.visuflow.debug.ui;

import org.eclipse.swt.widgets.Shell;
import org.junit.Assert;
import org.junit.Test;

import de.unipaderborn.visuflow.model.VFUnit;
import de.unipaderborn.visuflow.ui.Attribute;

public class AttributeTest {
	Shell parentShell;
	VFUnit vf;
	 String analysis = "";
	 String  attribute= "";
Attribute a=new Attribute(parentShell);


	@Test
	public void testGetAnalysis() {
		Assert.assertNotNull(a.getAnalysis());
	}

	
	@Test
	public void testGetAttribute() {
		Assert.assertNotNull(a.getAttribute());
	}

	

}
