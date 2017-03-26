package de.unipaderborn.visuflow.ui.graph.formatting;

import org.junit.Assert;
import org.junit.Test;

import soot.Unit;
import soot.jimple.internal.JReturnVoidStmt;

public class DefaultFormatterTest {

	@Test
	public void testFormat() {
		Unit u = new JReturnVoidStmt();
		DefaultFormatter formatter = new DefaultFormatter();
		Assert.assertEquals("return", formatter.format(u, 30));
		Assert.assertEquals("retu\u2026", formatter.format(u, 4));
	}
}
