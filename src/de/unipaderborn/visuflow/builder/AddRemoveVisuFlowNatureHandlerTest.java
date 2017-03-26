package de.unipaderborn.visuflow.builder;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.junit.Assert;
import org.junit.Test;

public class AddRemoveVisuFlowNatureHandlerTest {

	@Test
	public void testToggleNature() {
		try {
			IProject project = VisuflowNatureTest.createIProject();
			AddRemoveVisuFlowNatureHandler handler = new AddRemoveVisuFlowNatureHandler();
			handler.toggleNature(project);
			Assert.assertTrue(VisuflowNatureTest.isJimpleBuilderInstalled(project));
			handler.toggleNature(project);
			Assert.assertFalse(VisuflowNatureTest.isJimpleBuilderInstalled(project));
		} catch (CoreException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
