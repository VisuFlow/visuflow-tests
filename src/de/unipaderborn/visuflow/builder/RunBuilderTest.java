package de.unipaderborn.visuflow.builder;

import static org.junit.Assert.*;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.junit.Assert;
import org.junit.Test;

import de.unipaderborn.visuflow.debug.handlers.RunBuilder;
import de.unipaderborn.visuflow.model.DataModel;
import de.unipaderborn.visuflow.util.ServiceUtil;

public class RunBuilderTest {
RunBuilder r=new RunBuilder();
ExecutionEvent event1;
	@Test
	public void testExecute() {
		ServiceUtil.getService(DataModel.class).triggerProjectRebuild();
		try {
			Assert.assertNull(r.execute(event1));
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			Assert.fail("failed");
		}
	}

}
