package de.unipaderborn.visuflow.debug.ui;

import org.eclipse.jface.text.source.Annotation;
import org.junit.Assert;
import org.junit.Test;

public class JimpleUnitPointerImageProviderTest {
	Annotation a;
	String imagedesc;
	JimpleUnitPointerImageProvider jp=new JimpleUnitPointerImageProvider();
	@Test
	public void testGetManagedImage() {
		if(a instanceof JimpleInstructionPointerAnnotation) {
			Assert.assertNotNull(jp.getManagedImage(a));
		}
		Assert.assertNull(jp.getManagedImage(a));
	}
	@Test
	public void testGetImageDescriptorId() {
		Assert.assertNull(jp.getImageDescriptorId(a));
	}

	@Test
	public void testGetImageDescriptor() {
		Assert.assertNull(jp.getImageDescriptor(imagedesc));
	}

}
