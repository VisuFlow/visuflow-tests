package de.unipaderborn.visuflow.wizard;

import org.junit.Assert;
import org.junit.Test;

public class WizardContextTest {
	public boolean checked1;
	WizardContext w=new WizardContext();
	@Test
	public void testIsChecked() {
		w.setChecked(checked1);
		Assert.assertEquals(checked1,w.isChecked());
	}

	
}
