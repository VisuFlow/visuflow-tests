package de.unipaderborn.visuflow.ui;

import org.junit.Assert;
import org.junit.Test;

public class JimpleEditorTest {

	@Test
	public void testIsEditable() {
		JimpleEditor editor = new JimpleEditor();
		Assert.assertFalse(editor.isEditable());
	}

	@Test
	public void testIsEditorInputReadOnly() {
		JimpleEditor editor = new JimpleEditor();
		Assert.assertTrue(editor.isEditorInputReadOnly());
	}

	@Test
	public void testIsEditorInputModifiable() {
		JimpleEditor editor = new JimpleEditor();
		Assert.assertFalse(editor.isEditorInputModifiable());
	}

}
