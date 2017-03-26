package de.unipaderborn.visuflow.builder;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VisuflowNatureTest {

	IProject project;

	@Before
	public void createProject() throws CoreException {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		project = root.getProject("TestProject");
		project.create(null);
		project.open(null);
	}

	@After
	public void deleteProject() throws CoreException {
		project.delete(true, null);
	}

	@Test
	public void testConfigure() {
		try {
			VisuFlowNature vn = new VisuFlowNature();
			vn.setProject(project);
			vn.configure();
			IProjectDescription desc = project.getDescription();
			ICommand[] buildCommands = desc.getBuildSpec();
			boolean jimpleBuilderInstalled = false;
			for (ICommand cmd : buildCommands) {
				if(cmd.getBuilderName().equals(JimpleBuilder.BUILDER_ID)) {
					jimpleBuilderInstalled = true;
					break;
				}
			}
			Assert.assertTrue(jimpleBuilderInstalled);
		} catch (CoreException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testDeconfigure() {
		try {
			testConfigure();

			VisuFlowNature vn = new VisuFlowNature();
			vn.setProject(project);
			vn.deconfigure();
			IProjectDescription desc = project.getDescription();
			ICommand[] buildCommands = desc.getBuildSpec();
			boolean jimpleBuilderInstalled = false;
			for (ICommand cmd : buildCommands) {
				if(cmd.getBuilderName().equals(JimpleBuilder.BUILDER_ID)) {
					jimpleBuilderInstalled = true;
					break;
				}
			}
			Assert.assertFalse(jimpleBuilderInstalled);
		} catch (CoreException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testGetProject() {
		VisuFlowNature vn = new VisuFlowNature();
		Assert.assertNull(vn.getProject());
		vn.setProject(project);
		Assert.assertEquals(project, vn.getProject());
	}
}
