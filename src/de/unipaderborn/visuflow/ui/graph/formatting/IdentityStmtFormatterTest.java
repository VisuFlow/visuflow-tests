package de.unipaderborn.visuflow.ui.graph.formatting;

import static de.unipaderborn.visuflow.model.DataModelMockFactory.createSootClass;

import org.junit.Assert;
import org.junit.Test;

import soot.IntType;
import soot.RefType;
import soot.SootClass;
import soot.Unit;
import soot.Value;
import soot.jimple.Jimple;
import soot.jimple.internal.JIdentityStmt;
import soot.jimple.internal.JimpleLocal;

public class IdentityStmtFormatterTest {

	@Test
	public void test() {
		Value local = new JimpleLocal("foobar", IntType.v());
		RefType refType = RefType.v();
		SootClass declaringClass = createSootClass("a.b.c.Foobar");
		refType.setSootClass(declaringClass);
		refType.setClassName(declaringClass.getName());
		Value thisRef = Jimple.v().newThisRef(refType);
		Unit u = new JIdentityStmt(local, thisRef);

		IdentityStmtFormatter formatter = new IdentityStmtFormatter();
		Assert.assertEquals("foobar := @this: a.b.c.Foobar", formatter.format(u, 30));
		Assert.assertEquals("foobar := @this: a.b.c.Fo\u2026", formatter.format(u, 25));
	}

}
