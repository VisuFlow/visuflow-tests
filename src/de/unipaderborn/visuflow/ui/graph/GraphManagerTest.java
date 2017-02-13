package de.unipaderborn.visuflow.ui.graph;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class GraphManagerTest {
	
	private GraphManager manager = new GraphManager("test", "url('file:styles/stylesheet.css')");
	
	@Test
	public void testEvaluatesExpression() {
		assertUiApplet();
	}

	private void assertUiApplet()
	{
		assertNotNull(manager.getApplet());
	}
	
//	private void assertZoomIn()
//	{
//		
//	}
}
