package de.unipaderborn.visuflow.ui.graph.formatting;

import org.junit.Assert;
import org.junit.Test;

import soot.IntType;
import soot.Value;
import soot.jimple.AssignStmt;
import soot.jimple.internal.JAssignStmt;
import soot.jimple.internal.JimpleLocal;

public class AssignStmtFormatterTest {

	@Test
	public void test() {
		AssignStmt assignStmt = createAssignStmt();
		AssignStmtFormatter formatter = new AssignStmtFormatter();
		Assert.assertEquals("r1 = $r2", formatter.format(assignStmt, 30));
	}

	public static AssignStmt createAssignStmt() {
		Value left = new JimpleLocal("r1", IntType.v());
		Value right = new JimpleLocal("$r2", IntType.v());
		AssignStmt assignStmt = new JAssignStmt(left, right);
		return assignStmt;
	}

}
