package omniscientDebugging;

import org.junit.Assert;
import org.junit.Test;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import de.unipaderborn.visuflow.model.VFUnit;
import de.unipaderborn.visuflow.util.ServiceUtil;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class ReturnValueTest1 extends AbstractTest{
	
	@Test @Override
	public void microBenchmark() throws InterruptedException {
		TimeUnit.SECONDS.sleep(10);
		
		VFUnit currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.staticinvoke <de.userstudy.target1.Target: void dangerousTransmission(java.lang.String)>($r8)", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r8 FIELD [], LOCAL $r5 FIELD [], LOCAL $r7 FIELD [], LOCAL $r6 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r8 FIELD [], LOCAL $r5 FIELD [], LOCAL $r7 FIELD [], LOCAL $r6 FIELD []]", currentUnit.getOutSet());
		
		String EA_TOPIC_DEBUGGING_ACTION_STEP_BACK = "de/unipaderborn/visuflow/debug/stepBack";
		EventAdmin ea = ServiceUtil.getService(EventAdmin.class);
		Event stepBack = new Event(EA_TOPIC_DEBUGGING_ACTION_STEP_BACK, Collections.emptyMap());
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.$r8 = r1.<de.userstudy.target1.Target: java.lang.String data>", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r5 FIELD [], LOCAL $r7 FIELD [], LOCAL $r6 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r8 FIELD [], LOCAL $r5 FIELD [], LOCAL $r7 FIELD [], LOCAL $r6 FIELD []]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.r2.<de.userstudy.target1.Target: java.lang.String data> = $r7", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r5 FIELD [], LOCAL $r7 FIELD [], LOCAL $r6 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r5 FIELD [], LOCAL $r7 FIELD [], LOCAL $r6 FIELD []]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.$r7 = staticinvoke <de.userstudy.target1.Target: java.lang.String copyData(java.lang.String)>($r6)", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r5 FIELD [], LOCAL $r6 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r5 FIELD [], LOCAL $r7 FIELD [], LOCAL $r6 FIELD []]", currentUnit.getOutSet());
		
		// the next current unit belongs to another method
		ea.sendEvent(stepBack);
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.r1.<de.userstudy.target1.Target: java.lang.String data> = $r5", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r5 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r5 FIELD []]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.$r5 = staticinvoke <de.userstudy.target1.Target: java.lang.String getTainted()>()", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r5 FIELD []]", currentUnit.getOutSet());
		
		// the next two current units belongs to other methods
		ea.sendEvent(stepBack);		
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
	}
	
	@Override
	String getAnalysis() {
		return "/ReturnValueAnalysis1";
	}
	
	@Override
	String getTarget() {
		return "/ReturnValueTest1";
	}
}