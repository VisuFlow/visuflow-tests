package de.unipaderborn.visuflow.debug.handlers;

import static org.junit.Assert.*;

import java.util.Collections;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.junit.Assert;
import org.junit.Test;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import de.unipaderborn.visuflow.util.ServiceUtil;

public class DebugResumeHandlerTest {

	@Test
	public void testExecute() {
		ExecutionEvent event1 = null;
		DebugStepOverHandler db=new DebugStepOverHandler();
		 String EA_TOPIC_DEBUGGING_ACTION_RESUME = "de/unipaderborn/visuflow/debug/resume";
		EventAdmin ea = ServiceUtil.getService(EventAdmin.class);
		Event stepOver = new Event(EA_TOPIC_DEBUGGING_ACTION_RESUME, Collections.emptyMap());
		ea.sendEvent(stepOver);

		try {
			Assert.assertEquals(event1,db.execute(event1));
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			Assert.fail(e.getMessage());
		}
	}

}
