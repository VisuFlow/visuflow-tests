package de.unipaderborn.visuflow.ui.graph.formatting;

import org.junit.Assert;
import org.junit.Test;

import soot.Unit;
import soot.Value;
import soot.jimple.InvokeStmt;
import soot.jimple.internal.JInvokeStmt;

public class InvokeStmtFormatterTest {

	@Test
	public void test() {
		Unit u = createInvokeStmt();
		InvokeStmtFormatter formatter = new InvokeStmtFormatter();
		Assert.assertEquals("<Foobar: void foobar(int,boolean)>(a, b)", formatter.format(u, 100));
		Assert.assertEquals("<Foobar: void foobar(int,boole\u2026", formatter.format(u, 30));
	}

	public static InvokeStmt createInvokeStmt() {
		Value invoke = ValueFormatterTest.createStaticInvokeExpr();
		JInvokeStmt u = new JInvokeStmt(invoke);
		return u;
	}

}
