package de.unipaderborn.visuflow.wizard;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.junit.Test;

public class WizardHandlerTest {
	private WizardPageHandler page1;
	private WizardHandlerPageTwo page2;
	private ISelection selection;
	WizardHandler w=new WizardHandler();
	@Test
	public void testAddPages() {
		page1 = new WizardPageHandler(selection);
		w.addPage(page1);
		page2 = new WizardHandlerPageTwo(selection);
		w.addPage(page2);
	}



	@Test
	public void testInit() {
		
		IStructuredSelection selection = null;
		this.selection = selection;
	}

}
