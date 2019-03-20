package omniscientDebugging;

import org.junit.Assert;
import org.junit.Test;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import de.unipaderborn.visuflow.VisuflowConstants;
import de.unipaderborn.visuflow.model.VFUnit;
import de.unipaderborn.visuflow.util.ServiceUtil;

import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

public class InterproceduralTest extends AbstractTest{
	
	@Test @Override
	public void microBenchmark() throws InterruptedException {
		TimeUnit.SECONDS.sleep(10);
		
		VFUnit currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.callDangerousTransmission.staticinvoke <de.userstudy.target1.Target: void dangerousTransmission(java.lang.String)>($r1)", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL r0 FIELD [<de.userstudy.target1.Target: java.lang.String knowledge>], LOCAL $r1 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL r0 FIELD [<de.userstudy.target1.Target: java.lang.String knowledge>], LOCAL $r1 FIELD []]", currentUnit.getOutSet());
		
		String EA_TOPIC_DEBUGGING_ACTION_STEP_BACK = "de/unipaderborn/visuflow/debug/stepBack";
		EventAdmin ea = ServiceUtil.getService(EventAdmin.class);
		Event stepBack = new Event(EA_TOPIC_DEBUGGING_ACTION_STEP_BACK, Collections.emptyMap());
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.callDangerousTransmission.$r1 = r0.<de.userstudy.target1.Target: java.lang.String knowledge>", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL r0 FIELD [<de.userstudy.target1.Target: java.lang.String knowledge>]]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL r0 FIELD [<de.userstudy.target1.Target: java.lang.String knowledge>], LOCAL $r1 FIELD []]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.callDangerousTransmission.r0 := @parameter0: de.userstudy.target1.Target", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL r0 FIELD [<de.userstudy.target1.Target: java.lang.String knowledge>]]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL r0 FIELD [<de.userstudy.target1.Target: java.lang.String knowledge>]]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.run.r1.<de.userstudy.target1.Target: java.lang.String knowledge> = r2", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL r2 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL r2 FIELD [], LOCAL $r3 FIELD [<de.userstudy.target1.Target: java.lang.String knowledge>]]", currentUnit.getOutSet());
		
		Dictionary<String, Object> properties = new Hashtable<>();
		properties.put("direction", false);
		VFUnit destination = dataModel.getVFUnit("de.userstudy.target1.Target.run.r1.<de.userstudy.target1.Target: java.lang.String knowledge> = r2");
		properties.put("destination", destination);
		Event stepTo = new Event(VisuflowConstants.EA_TOPIC_DEBUGGING_ACTION_STEP_TO_UNIT, properties);
		ea.sendEvent(stepTo);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.run.r2 = staticinvoke <de.userstudy.target1.Target: java.lang.String getSecret()>()", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL r2 FIELD []]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.run.specialinvoke $r3.<de.userstudy.target1.Target: void <init>()>()", currentUnit.getFullyQualifiedName());
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
		Assert.assertEquals("de.userstudy.target1.Target.run.r0 := @this: de.userstudy.target1.Target", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.<init>.return", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
	}
	
	@Override
	String getAnalysis() {
		return "/InterproceduralAnalysis";
	}
	
	@Override
	String getTarget() {
		return "/InterproceduralTest";
	}
}