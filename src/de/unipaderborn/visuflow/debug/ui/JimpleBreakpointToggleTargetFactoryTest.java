package de.unipaderborn.visuflow.debug.ui;

import static org.junit.Assert.*;

import java.util.Set;

import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTargetFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.junit.Assert;
import org.junit.Test;

import de.unipaderborn.visuflow.VisuflowConstants;

public class JimpleBreakpointToggleTargetFactoryTest implements IToggleBreakpointsTargetFactory, VisuflowConstants {
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

	@Override
	public Set<String> getToggleTargets(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultToggleTarget(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IToggleBreakpointsTarget createToggleTarget(String targetID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToggleTargetName(String targetID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToggleTargetDescription(String targetID) {
		// TODO Auto-generated method stub
		return null;
	}

}
