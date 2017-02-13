package de.unipaderborn.visuflow.ui.graph;

import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import de.unipaderborn.visuflow.ui.view.CFGView;

public class GraphManagerTest {

	private GraphManager manager;

	@Before
	public void setup() {
		ClassLoader loader = CFGView.class.getClassLoader();
		URL stylesheetUrl = loader.getResource("/styles/styleSheet.css");
		manager = new GraphManager("test", "url('"+stylesheetUrl.toString()+"')");
	}

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
