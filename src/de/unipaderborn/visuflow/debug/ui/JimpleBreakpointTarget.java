package de.unipaderborn.visuflow.debug.ui;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.junit.Assert;
import org.junit.Test;

public class JimpleBreakpointTarget  {
	String id="targetID";
	JimpleBreakpointToggleTargetFactory j=new JimpleBreakpointToggleTargetFactory();
	IWorkbenchPart testpart;
	ISelection testselection;
	@Test
	public void testGetToggleTargets() {
		Assert.assertNotNull(j.getToggleTargets(testpart,testselection));
	}

	@Test
	public void testGetDefaultToggleTarget() {
		Assert.assertNotNull(j.getDefaultToggleTarget(testpart,testselection));
	}


	@Test
	public void testGetToggleTargetName() {
		
		Assert.assertNotNull(j.getToggleTargetName(id));
	}

	@Test
	public void testGetToggleTargetDescription() {
		Assert.assertNull(j.getToggleTargetDescription(id));
	}

	

}