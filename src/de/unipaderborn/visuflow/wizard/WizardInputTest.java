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
		Path sootPath1 = null;
		wi.setSootPath(sootPath1);
		Assert.assertEquals(sootPath1,wi.getSootPath());
	}

	@Test
	public void testGetFile() {
		WizardInput wi=new WizardInput();
		File f = null;
		wi.setFile(f);
		
		Assert.assertEquals(f,wi.getFile());
	}
	

	@Test
	public void testGetFlowType() {
		WizardInput wi=new WizardInput();
		String fl = null;
		wi.setFlowType(fl);
		Assert.assertEquals(fl,wi.getFlowType());
	}
	
	
	@Test
	public void testGetFlowType1() {
		WizardInput wi=new WizardInput();
		String fl1 = null;
		wi.setFlowType1(fl1);
		Assert.assertEquals(fl1,wi.getFlowType1());
	}
	

	@Test
	public void testGetFlowtype2() {
		WizardInput wi=new WizardInput();
		String fl2 = null;
		wi.setFlowType1(fl2);
		Assert.assertEquals(fl2,wi.getFlowtype2());
	}
	

	@Test
	public void testGetCustomClassFirst() {
		WizardInput wi=new WizardInput();
		String customCl = null;
		wi.setCustomClassFirst(customCl);
		Assert.assertEquals(customCl,wi.getCustomClassFirst());
	}
	
	@Test
	public void testGetCustomClassSecond() {
		WizardInput wi=new WizardInput();
		String customCl2 = null;
		wi.setCustomClassSecond(customCl2);
		Assert.assertEquals(customCl2,wi.getCustomClassSecond());
	}
	
	
	@Test
	public void testGetProjectPath() {
		WizardInput wi=new WizardInput();
		String projPath = null;
		wi.setProjectPath(projPath);
		Assert.assertEquals(projPath,wi.getProjectPath());
	}

	@Test
	public void testGetTargetPath() {
		WizardInput wi=new WizardInput();
		String target="a";
		wi.setTargetPath(target);
		Assert.assertEquals(target,wi.getTargetPath());
	}
	
	@Test
	public void testGetProjectName() {
		WizardInput wi=new WizardInput();
		String projName= "something";
		wi.setProjectName(projName);
		Assert.assertEquals(projName,wi.getProjectName());
	}
	

	@Test
	public void testGetPackageName() {
		WizardInput wi=new WizardInput();
		String packName="somepackage";
		wi.setPackageName(packName);
		Assert.assertEquals(packName,wi.getPackageName());
	}
	
	@Test
	public void testGetClassName() {
		WizardInput wi=new WizardInput();
		String clName="classname";
		wi.setClassName(clName);
		Assert.assertEquals(clName,wi.getClassName());
	}
    
   
	@Test
	public void testGetAnalysisType() {
		WizardInput wi=new WizardInput();
		String analysistype="InterProceduralAnalysis";
		String analysistype1="IntraProceduralAnalysis";
		wi.setAnalysisType("InterProceduralAnalysis");
		Assert.assertEquals(analysistype,wi.getAnalysisType());
		wi.setAnalysisType("IntraProceduralAnalysis");
		Assert.assertEquals(analysistype1,wi.getAnalysisType());
	}
	
	
	@Test
	public void testGetAnalysisFramework() {
		WizardInput wi=new WizardInput();
		
		String analysisFramework="frame";
		wi.setAnalysisFramework(analysisFramework);
		Assert.assertEquals(analysisFramework,wi.getAnalysisFramework());
	}
	
	@Test
	public void testGetAnalysisDirection() {
		WizardInput wi=new WizardInput();
		String analysisDirection="Forward";
		wi.setAnalysisDirection(analysisDirection);
		Assert.assertEquals(analysisDirection,wi.getAnalysisDirection());
		String analysisDirection1="Backward";
		wi.setAnalysisDirection(analysisDirection1);
		Assert.assertEquals(analysisDirection1,wi.getAnalysisDirection());
	}
	

}
