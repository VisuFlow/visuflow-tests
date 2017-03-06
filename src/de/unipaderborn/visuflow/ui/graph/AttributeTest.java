package de.unipaderborn.visuflow.ui.graph;

import static org.junit.Assert.*;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.junit.Assert;
import org.junit.Test;

import de.unipaderborn.visuflow.model.VFUnit;
import de.unipaderborn.visuflow.ui.Attribute;


public class AttributeTest {
	VFUnit vfunit;
	Shell parentshell;
	@Test
	public void testGetAnalysis() {
		Attribute a=new Attribute(parentshell);
		Assert.assertNotNull(a.getAnalysis());
	}

	
	@Test
	public void testGetAttribute() {
		Attribute a=new Attribute(parentshell);
		Assert.assertNotNull(a.getAttribute());
	}

	

}
