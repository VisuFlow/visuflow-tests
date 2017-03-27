package de.unipaderborn.visuflow.debug.handlers;

import static org.junit.Assert.*;

import java.util.Collections;

import de.unipaderborn.visuflow.VisuflowConstants;
import de.unipaderborn.visuflow.util.ServiceUtil;

import org.junit.Assert;
import org.junit.Test;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
public class DebugStepOverTest {

	@Test
	public void testExecute() {
		DebugStepOverHandler db=new DebugStepOverHandler();
		ExecutionEvent event1 = null;
		 String EA_TOPIC_DEBUGGING_ACTION_STEP_OVER = "de/unipaderborn/visuflow/debug/stepOver";
		EventAdmin ea = ServiceUtil.getService(EventAdmin.class);
		Event stepOver = new Event(EA_TOPIC_DEBUGGING_ACTION_STEP_OVER, Collections.emptyMap());
		ea.sendEvent(stepOver);

		// has to be null (see javadoc)
		
			try {
				Assert.assertEquals(event1,db.execute(event1));
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				Assert.fail(e.getMessage());
			}
		
	}

}
