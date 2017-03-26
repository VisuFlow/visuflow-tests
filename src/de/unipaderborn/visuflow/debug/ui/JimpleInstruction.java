package de.unipaderborn.visuflow.debug.ui;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.eclipse.swt.graphics.Image;
public class JimpleInstruction {
	Image img;
	JimpleInstructionPointerAnnotation j=new JimpleInstructionPointerAnnotation(img); 
	@Test
	public void testGetImage() {
		
		Assert.assertEquals(img,j.getImage());
	}

}
