package de.unipaderborn.visuflow.ui.graph.formatting;

import org.junit.Assert;
import org.junit.Test;

import soot.Unit;
import soot.jimple.AssignStmt;
import soot.jimple.internal.JReturnVoidStmt;

public class UnitFormatterFactoryTest {

	@Test
	public void testCreateFormatter() {
		Unit invokeStmt = InvokeStmtFormatterTest.createInvokeStmt();
		UnitFormatter formatter = UnitFormatterFactory.createFormatter(invokeStmt);
		Assert.assertTrue(formatter instanceof InvokeStmtFormatter);

		Unit returnStmt = new JReturnVoidStmt();
		formatter = UnitFormatterFactory.createFormatter(returnStmt);
		Assert.assertTrue(formatter instanceof DefaultFormatter);

		AssignStmt assignStmt = AssignStmtFormatterTest.createAssignStmt();
		formatter = UnitFormatterFactory.createFormatter(assignStmt);
		Assert.assertTrue(formatter instanceof AssignStmtFormatter);
	}

}
