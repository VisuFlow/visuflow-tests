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

public class LoopTest2 extends AbstractTest{
	
	@Test
	public void microBenchmark() throws InterruptedException {
		TimeUnit.SECONDS.sleep(10);
		
		VFUnit currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.staticinvoke <de.userstudy.target1.Target: void dangerousTransmission(java.lang.String)>($r9)", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r8 FIELD [], LOCAL $r5 FIELD [], LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r9 FIELD [], LOCAL $r7 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r8 FIELD [], LOCAL $r5 FIELD [], LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r9 FIELD [], LOCAL $r7 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getOutSet());
		
		String EA_TOPIC_DEBUGGING_ACTION_STEP_BACK = "de/unipaderborn/visuflow/debug/stepBack";
		EventAdmin ea = ServiceUtil.getService(EventAdmin.class);
		Event stepBack = new Event(EA_TOPIC_DEBUGGING_ACTION_STEP_BACK, Collections.emptyMap());
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.$r9 = r1.<de.userstudy.target1.Target: java.lang.String data>", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r8 FIELD [], LOCAL $r5 FIELD [], LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r7 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r8 FIELD [], LOCAL $r5 FIELD [], LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r9 FIELD [], LOCAL $r7 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.specialinvoke r2.<de.userstudy.target1.Target: void overwrite(java.lang.String)>(\"\")", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r8 FIELD [], LOCAL $r5 FIELD [], LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r7 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r8 FIELD [], LOCAL $r5 FIELD [], LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r7 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getOutSet());
		
		// the next current unit belongs to another class
		ea.sendEvent(stepBack);
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.i0 = i0 + 1", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r8 FIELD [], LOCAL $r5 FIELD [], LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r7 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r8 FIELD [], LOCAL $r5 FIELD [], LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r7 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getOutSet());
		
		// the current unit stays the same
		ea.sendEvent(stepBack);
		
		String EA_TOPIC_DEBUGGING_ACTION_PATH_CHOSEN = "de/unipaderborn/visuflow/debug/pathChosen";
		Dictionary<String, Object> properties = new Hashtable<>();
		String fqn = "de.userstudy.target1.Target.main.if $z0 == 0 goto (branch)";
		properties.put("choice", fqn);
		Event pathChosen = new Event(EA_TOPIC_DEBUGGING_ACTION_PATH_CHOSEN, properties);
		ea.sendEvent(pathChosen);
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.$r6 = r1.<de.userstudy.target1.Target: java.lang.String data>", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.i0 = 0", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.r2.<de.userstudy.target1.Target: java.lang.String data> = $r5", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r5 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.$r5 = staticinvoke <de.userstudy.target1.Target: java.lang.String getSecret()>()", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r5 FIELD []]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.specialinvoke $r4.<de.userstudy.target1.Target: void <init>()>()", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		// the current unit stays the same
		ea.sendEvent(stepBack);		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.<init>.return", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.specialinvoke $r3.<de.userstudy.target1.Target: void <init>()>()", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		// the current unit stays the same
		ea.sendEvent(stepBack);
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.<init>.return", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		String EA_TOPIC_DEBUGGING_ACTION_STEP_OVER = "de/unipaderborn/visuflow/debug/stepOver";
		Event stepOver = new Event(EA_TOPIC_DEBUGGING_ACTION_STEP_OVER, Collections.emptyMap());
		for(int i = 0; i < 14; i++) {
			ea.sendEvent(stepOver);
		}
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.specialinvoke r2.<de.userstudy.target1.Target: void overwrite(java.lang.String)>(\"\")", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r7 FIELD [], LOCAL r1 FIELD [], LOCAL $r8 FIELD [], LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL r1 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r7 FIELD [], LOCAL r1 FIELD [], LOCAL $r8 FIELD [], LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL r1 FIELD []]", currentUnit.getOutSet());
		
		// the next current unit belongs to another method
		ea.sendEvent(stepBack);
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.i0 = i0 + 1", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r7 FIELD [], LOCAL r1 FIELD [], LOCAL $r8 FIELD [], LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getInSet());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r7 FIELD [], LOCAL r1 FIELD [], LOCAL $r8 FIELD [], LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getOutSet());
		
		// the current unit stays the same
		ea.sendEvent(stepBack);
		
		properties = new Hashtable<>();
		fqn = "de.userstudy.target1.Target.main.i0 = i0 + 1";
		properties.put("choice", fqn);
		pathChosen = new Event(EA_TOPIC_DEBUGGING_ACTION_PATH_CHOSEN, properties);
		ea.sendEvent(pathChosen);
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.$r8 = staticinvoke <de.userstudy.target1.Target: java.lang.String copyData(java.lang.String)>($r7)", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r7 FIELD [], LOCAL $r8 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r7 FIELD [], LOCAL $r8 FIELD []]", currentUnit.getOutSet());
		
		// the next current unit belongs to another method
		ea.sendEvent(stepBack);		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.if i0 < 10 goto $r7 = r2.<de.userstudy.target1.Target: java.lang.String data>", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], ]", currentUnit.getInSet());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], ]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.goto [?= (branch)]", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getInSet());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getOutSet());
		
		// the next current unit stays the same
		ea.sendEvent(stepBack);
		
		properties = new Hashtable<>();
		fqn = "de.userstudy.target1.Target.main.if $z0 == 0 goto (branch)";
		properties.put("choice", fqn);
		pathChosen = new Event(EA_TOPIC_DEBUGGING_ACTION_PATH_CHOSEN, properties);
		ea.sendEvent(pathChosen);
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.$r6 = r1.<de.userstudy.target1.Target: java.lang.String data>", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getInSet());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.i0 = 0", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getInSet());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.r2.<de.userstudy.target1.Target: java.lang.String data> = $r5", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[ LOCAL $r5 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[ LOCAL $r5 FIELD [], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>]]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.$r5 = staticinvoke <de.userstudy.target1.Target: java.lang.String getSecret()>()", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[ LOCAL $r5 FIELD []]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.specialinvoke $r4.<de.userstudy.target1.Target: void <init>()>()", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		// the current unit stays the same
		ea.sendEvent(stepBack);		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.<init>.return", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());

		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.specialinvoke $r3.<de.userstudy.target1.Target: void <init>()>()", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		// the current unit stays the same
		ea.sendEvent(stepBack);		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.<init>.return", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
	}
	
	String getAnalysis() {
		return "/LoopAnalysis2";
	}
	
	String getTarget() {
		return "/LoopTest2";
	}
}