package de.unipaderborn.visuflow.model.graph;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import de.unipaderborn.visuflow.model.graph.FlowAbstraction;
import soot.Local;
import soot.SootField;
import soot.Unit;

public class FlowAbstractionTest {
	private Unit testsource;
	private Local testlocal;
	private SootField testfield;
	private Unit newtestSource;
	

	@Test
	public void testHashCode() {
		FlowAbstraction fa=new FlowAbstraction(testsource, testlocal);
		Assert.assertNotNull(fa.hashCode());
	}

	@Test
	public void testGetSource() {
		FlowAbstraction fa=new FlowAbstraction(testsource, testlocal);
		Assert.assertEquals(testsource,fa.getSource());
	}

	@Test
	public void testGetLocal() {
		FlowAbstraction fa=new FlowAbstraction(testsource, testlocal);
		Assert.assertEquals(testlocal,fa.getLocal());
	}

	@Test
	public void testGetField() {
		FlowAbstraction fa=new FlowAbstraction(testsource,testfield);
		Assert.assertEquals(testfield,fa.getField());
	}

	
	@Test
	public void testToString() {
		String s="LOCAL " + testlocal;
		String f="LOCAL " + testlocal;
		String nul="";
		FlowAbstraction fa=new FlowAbstraction(testsource,testlocal);
		if (testlocal != null)
		Assert.assertEquals(s,fa.toString());
		if (testfield != null)
	Assert.assertEquals(f,fa.toString());
	Assert.assertEquals(nul,fa.toString());
	}

	@Test
	public void testDeriveWithNewSource() {
		
		FlowAbstraction fa=new FlowAbstraction(testsource,testlocal);
		Assert.assertNotNull(fa.deriveWithNewSource(newtestSource));
	}

}
