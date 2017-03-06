package de.unipaderborn.visuflow.wizard;



import java.io.File;

import org.eclipse.core.runtime.Path;
import org.junit.Assert;
import org.junit.Test;



public class WizardInputTest {
	String testflowType;
	String testflowType1;
	String testflowtype2;
	String testcustomClassFirst;
	String testcustomClassSecond;
	String testProjectPath;
	String testTargetPath;
	String testProjectName;
	String testPackageName;
	String testClassName;
	String testAnalysisType;
	String testAnalysisFramework;
	String testAnalysisDirection;
	Path testsootPath;
	File testfile;
	
	@Test
	public void testGetSootPath() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testsootPath,wi.getSootPath());
	}

	@Test
	public void testGetFile() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testfile,wi.getFile());
	}

	@Test
	public void testGetFlowType() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testflowType,wi.getFlowType());
	}

	@Test
	public void testGetFlowType1() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testflowType1,wi.getFlowType1());
	}

	@Test
	public void testGetFlowtype2() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testflowtype2,wi.getFlowtype2());
	}

	@Test
	public void testGetCustomClassFirst() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testcustomClassFirst,wi.getCustomClassFirst());
	}

	@Test
	public void testGetCustomClassSecond() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testcustomClassSecond,wi.getCustomClassSecond());
	}

	@Test
	public void testGetProjectPath() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testProjectPath,wi.getProjectPath());
	}

	@Test
	public void testGetTargetPath() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testTargetPath,wi.getTargetPath());
	}

	@Test
	public void testGetProjectName() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testProjectName,wi.getProjectName());
	}

	@Test
	public void testGetPackageName() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testPackageName,wi.getPackageName());
	}

	@Test
	public void testGetClassName() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testClassName,wi.getClassName());
	}

	@Test
	public void testGetAnalysisType() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testAnalysisType,wi.getAnalysisType());
	}

	@Test
	public void testGetAnalysisFramework() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testAnalysisFramework,wi.getAnalysisFramework());
	}

	@Test
	public void testGetAnalysisDirection() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testAnalysisDirection,wi.getAnalysisDirection());
	}

}
