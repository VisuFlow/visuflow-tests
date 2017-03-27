package de.unipaderborn.visuflow.ui.view.filter;

import static org.junit.Assert.*;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
//import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.AbstractContentProvider;
//import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.ItemsFilter;
//import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog.ItemsFilter;
import org.junit.Assert;
import org.junit.Test;

import de.unipaderborn.visuflow.Visuflow;
import de.unipaderborn.visuflow.model.VFUnit;

public class ReturnPathFilterTest {
	Shell s;
	ReturnPathFilter r=new ReturnPathFilter(s);
	@Test
	public void testSetPaths() {
		List<VFUnit> path = null;
		r.setPaths(path);
		}

	
	@Test
	public void testCreateExtendedContentAreaComposite() {
		
		Composite arg = null;
		Assert.assertNull(r.createExtendedContentArea(arg));
	}



//	@Test
//	public void testFillContentProviderAbstractContentProviderItemsFilterIProgressMonitor() {
//		AbstractContentProvider contentProvider;
//		ItemsFilter itemsFilter;
//		IProgressMonitor progressMonitor;
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetDialogSettings() {
		IDialogSettings settings1 = Visuflow.getDefault().getDialogSettings();
		Assert.assertEquals(settings1, r.getDialogSettings());
	}

	@Test
	public void testGetElementNameObject() {
		Object someitem = "a";
		Assert.assertNotNull(r.getElementName(someitem));
	}


	@Test
	public void testValidateItemObject() {
		Object item1="a";
		Assert.assertEquals(Status.OK_STATUS,r.validateItem(item1));
	}

}
