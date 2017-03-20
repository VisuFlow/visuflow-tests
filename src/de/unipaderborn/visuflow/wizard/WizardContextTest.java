package de.unipaderborn.visuflow.wizard;

import org.junit.Assert;
import org.junit.Test;

public class WizardContextTest {
	public boolean checked1;
	WizardContext w=new WizardContext();
	@Test
	public void testIsChecked() {
		Assert.assertFalse(w.isChecked());
	}
	public void setChecked(boolean check) {
		this.checked1 = check;
	}
	
}
