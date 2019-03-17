package omniscientDebugging;

import org.junit.Assert;
import org.junit.Test;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import de.unipaderborn.visuflow.model.VFUnit;
import de.unipaderborn.visuflow.util.ServiceUtil;

import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

public class RecursionTest extends AbstractTest{
	
	@Test
	public void microBenchmark() throws InterruptedException {
		TimeUnit.SECONDS.sleep(10);
		
		VFUnit currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.recursion.r0.<de.userstudy.target1.Target: java.lang.String data> = r2", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL r2 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL r0 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL r2 FIELD []]", currentUnit.getOutSet());
		
		String EA_TOPIC_DEBUGGING_ACTION_STEP_BACK = "de/unipaderborn/visuflow/debug/stepBack";
		EventAdmin ea = ServiceUtil.getService(EventAdmin.class);
		Event stepBack = new Event(EA_TOPIC_DEBUGGING_ACTION_STEP_BACK, Collections.emptyMap());
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.recursion.goto [?= r0.<de.userstudy.target1.Target: java.lang.String data> = r2]", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL r2 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL r2 FIELD []]", currentUnit.getOutSet());
		
		// the current unit stays the same
		ea.sendEvent(stepBack);
		
		String EA_TOPIC_DEBUGGING_ACTION_PATH_CHOSEN = "de/unipaderborn/visuflow/debug/pathChosen";
		Dictionary<String, Object> properties = new Hashtable<>();
		String fqn = "de.userstudy.target1.Target.recursion.r2 = \"\"";
		properties.put("choice", fqn);
		Event pathChosen = new Event(EA_TOPIC_DEBUGGING_ACTION_PATH_CHOSEN, properties);
		ea.sendEvent(pathChosen);
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.recursion.r1 = \"\"", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.recursion.i0 := @parameter0: int", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.recursion.r0 := @this: de.userstudy.target1.Target", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		String EA_TOPIC_DEBUGGING_ACTION_STEP_OVER = "de/unipaderborn/visuflow/debug/stepOver";
		Event stepOver = new Event(EA_TOPIC_DEBUGGING_ACTION_STEP_OVER, Collections.emptyMap());
		for(int i = 0; i < 7; i++) {
			ea.sendEvent(stepOver);
		}
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.recursion.goto [?= r0.<de.userstudy.target1.Target: java.lang.String data> = r2]", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[ LOCAL r2 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[ LOCAL r2 FIELD []]", currentUnit.getOutSet());
		
		// the current unit stays the same
		ea.sendEvent(stepBack);
		
		properties = new Hashtable<>();
		fqn = "de.userstudy.target1.Target.recursion.goto [?= r0.<de.userstudy.target1.Target: java.lang.String data> = r2]";
		properties.put("choice", fqn);
		pathChosen = new Event(EA_TOPIC_DEBUGGING_ACTION_PATH_CHOSEN, properties);
		ea.sendEvent(pathChosen);
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.recursion.r2 = \"\"", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.recursion.r1 = \"\"", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.recursion.i0 := @parameter0: int", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.recursion.r0 := @this: de.userstudy.target1.Target", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
	}
	
	String getAnalysis() {
		return "/RecursionAnalysis";
	}
	
	String getTarget() {
		return "/RecursionTest";
	}
}