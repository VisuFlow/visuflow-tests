package omniscientDebugging;

import org.junit.Assert;
import org.junit.Test;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import de.unipaderborn.visuflow.model.VFUnit;
import de.unipaderborn.visuflow.util.ServiceUtil;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class OuterClassTest extends AbstractTest{
	
	@Test @Override
	public void microBenchmark() throws InterruptedException {
		TimeUnit.SECONDS.sleep(10);
		
		VFUnit currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.staticinvoke <de.userstudy.target1.Target: void dangerousTransmission(java.lang.String)>($r9)", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r9 FIELD [], LOCAL $r5 FIELD [<de.userstudy.target1.Target: de.userstudy.target1.Target$Secret secret>, <de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r6 FIELD [<de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r8 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r9 FIELD [], LOCAL $r5 FIELD [<de.userstudy.target1.Target: de.userstudy.target1.Target$Secret secret>, <de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r6 FIELD [<de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r8 FIELD []]", currentUnit.getOutSet());
		
		String EA_TOPIC_DEBUGGING_ACTION_STEP_BACK = "de/unipaderborn/visuflow/debug/stepBack";
		EventAdmin ea = ServiceUtil.getService(EventAdmin.class);
		Event stepBack = new Event(EA_TOPIC_DEBUGGING_ACTION_STEP_BACK, Collections.emptyMap());
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.$r9 = r1.<de.userstudy.target1.Target: java.lang.String data>", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r5 FIELD [<de.userstudy.target1.Target: de.userstudy.target1.Target$Secret secret>, <de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r6 FIELD [<de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r8 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r9 FIELD [], LOCAL $r5 FIELD [<de.userstudy.target1.Target: de.userstudy.target1.Target$Secret secret>, <de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r6 FIELD [<de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r8 FIELD []]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.r1.<de.userstudy.target1.Target: java.lang.String data> = $r8", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r5 FIELD [<de.userstudy.target1.Target: de.userstudy.target1.Target$Secret secret>, <de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r6 FIELD [<de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r8 FIELD []]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r5 FIELD [<de.userstudy.target1.Target: de.userstudy.target1.Target$Secret secret>, <de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r4 FIELD [<de.userstudy.target1.Target: java.lang.String data>], LOCAL $r6 FIELD [<de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r8 FIELD []]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.$r8 = virtualinvoke r3.<de.userstudy.target1.Target$Secret: java.lang.String taint()>()", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[LOCAL $r5 FIELD [<de.userstudy.target1.Target: de.userstudy.target1.Target$Secret secret>, <de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r6 FIELD [<de.userstudy.target1.Target$Secret: java.lang.String secret>]]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r5 FIELD [<de.userstudy.target1.Target: de.userstudy.target1.Target$Secret secret>, <de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r6 FIELD [<de.userstudy.target1.Target$Secret: java.lang.String secret>], LOCAL $r8 FIELD []]", currentUnit.getOutSet());
		
		// the next current unit belongs to another method
		ea.sendEvent(stepBack);		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.specialinvoke $r6.<de.userstudy.target1.Target$Secret: void <init>(de.userstudy.target1.Target,de.userstudy.target1.Target$Secret)>(r2, null)", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[LOCAL $r6 FIELD [<de.userstudy.target1.Target$Secret: java.lang.String secret>]]", currentUnit.getOutSet());
		
		// the next current unit stays same
		ea.sendEvent(stepBack);	
		// the next current unit belongs to another method
		ea.sendEvent(stepBack);
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.$r6 = new de.userstudy.target1.Target$Secret", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
		
		ea.sendEvent(stepBack);
		currentUnit = dataModel.getCurrentUnit();
		Assert.assertEquals("de.userstudy.target1.Target.main.specialinvoke $r5.<de.userstudy.target1.Target: void <init>()>()", currentUnit.getFullyQualifiedName());
		Assert.assertEquals("[]", currentUnit.getInSet());
		Assert.assertEquals("[]", currentUnit.getOutSet());
	}
	
	@Override
	String getAnalysis() {
		return "/OuterClassAnalysis";
	}
	
	@Override
	String getTarget() {
		return "/OuterClassTest";
	}
}