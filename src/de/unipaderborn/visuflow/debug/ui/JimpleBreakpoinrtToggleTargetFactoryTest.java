package de.unipaderborn.visuflow.debug.ui;

import static org.junit.Assert.*;

import java.util.Set;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.junit.Assert;
import org.junit.Test;


public class JimpleBreakpoinrtToggleTargetFactoryTest {
	JimpleBreakpointToggleTargetFactory jb=new JimpleBreakpointToggleTargetFactory();

	@Test
	public void testGetToggleTargetName() {
		String str="string";
		assertNotNull(jb.getToggleTargetName(str));
	}

	@Test
	public void testGetToggleTargetDescription() {
		String S= "S";
		assertNull(jb.getToggleTargetDescription(S));
	}

}
