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
//	@Test
	public void testsetSootPath(Path sootPath1) {
		this.testsootPath = sootPath1;
	}
	@Test
	public void testGetFile() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testfile,wi.getFile());
	}
	
	public void testsetFile(File f) {
		this.testfile = f;
	}

	@Test
	public void testGetFlowType() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testflowType,wi.getFlowType());
	}
	
	public void testsetFlowType(String fl) {
		this.testflowType = fl;
	}
	@Test
	public void testGetFlowType1() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testflowType1,wi.getFlowType1());
	}
	
	public void testsetFlowType1(String fl1) {
		this.testflowType1 = fl1;
	}

	@Test
	public void testGetFlowtype2() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testflowtype2,wi.getFlowtype2());
	}
	
	public void setFlowtype2(String fl2) {
		this.testflowtype2 = fl2;
	}
	
	public void setCustomClassFirst(String customCl) {
		this.testcustomClassFirst = customCl;
	}
	@Test
	public void testGetCustomClassFirst() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testcustomClassFirst,wi.getCustomClassFirst());
	}
	
	public void setCustomClassSecond(String customClSecond) {
		this.testcustomClassSecond = customClSecond;
	}
	@Test
	public void testGetCustomClassSecond() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testcustomClassSecond,wi.getCustomClassSecond());
	}
	
	public void setProjectPath(String projPath) {
		testProjectPath = projPath;
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
	
	public void testsetTargetPath(String target) {
		testTargetPath = target;
	}
	@Test
	public void testGetProjectName() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testProjectName,wi.getProjectName());
	}
	
	public void testsetProjectName(String projName) {
		testProjectName = projName;
	}

	@Test
	public void testGetPackageName() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testPackageName,wi.getPackageName());
	}
	
	public void setPackageName(String packName) {
		testPackageName = packName;
	}
	@Test
	public void testGetClassName() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testClassName,wi.getClassName());
	}
    
    public void setClassName(String clName) {
	testClassName = clName;
    }
	@Test
	public void testGetAnalysisType() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testAnalysisType,wi.getAnalysisType());
	}
	
	public void testsetAnalysisType(String analysistype) {
		switch(analysistype){
		case "Inter Procedural Analysis":
			testAnalysisType = "InterProceduralAnalysis";
			break;
		case "Intra Procedural Analysis":
			testAnalysisType = "IntraproceduralAnalysis";
			break;
		default:testAnalysisType = analysistype;
		}
	}
	@Test
	public void testGetAnalysisFramework() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testAnalysisFramework,wi.getAnalysisFramework());
	}
	
	public void testsetAnalysisFramework(String analysisFramework) {
		testAnalysisFramework = analysisFramework;
	}

	@Test
	public void testGetAnalysisDirection() {
		WizardInput wi=new WizardInput();
		Assert.assertEquals(testAnalysisDirection,wi.getAnalysisDirection());
	}
	
	public void testsetAnalysisDirection(String analysisDirection) {
		testAnalysisDirection = analysisDirection;
	}

}
