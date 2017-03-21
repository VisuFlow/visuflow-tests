package de.unipaderborn.visuflow.ui.view.filter;

import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockClass;
import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockUnit;

import org.junit.Assert;
import org.junit.Test;

import de.unipaderborn.visuflow.model.VFClass;
import de.unipaderborn.visuflow.model.VFMethod;
import de.unipaderborn.visuflow.model.VFUnit;

public class ResultViewFilterTest {

	@Test
	public void testSelect() {
		ResultViewFilter filter = new ResultViewFilter();
		VFClass foo = createMockClass("Foo", "bar");
		VFMethod bar = foo.getMethods().get(0);
		VFUnit unit = createMockUnit("a.b.c.Foo.bar.return");
		unit.setVfMethod(bar);
		bar.getUnits().add(unit);
		unit.getHmCustAttr().put("key", "value");

		filter.setSearchText(null);
		Assert.assertTrue(filter.select(null, null, unit));
		filter.setSearchText("");
		Assert.assertTrue(filter.select(null, null, unit));
		filter.setSearchText("return");
		Assert.assertTrue(filter.select(null, null, unit));
		filter.setSearchText("JReturnVoidStmt");
		Assert.assertTrue(filter.select(null, null, unit));
		filter.setSearchText("n/a");
		Assert.assertTrue(filter.select(null, null, unit));
		unit.setInSet("inset");
		unit.setOutSet("outset");
		filter.setSearchText("inset");
		Assert.assertTrue(filter.select(null, null, unit));
		filter.setSearchText("outset");
		Assert.assertTrue(filter.select(null, null, unit));
		filter.setSearchText("key");
		Assert.assertTrue(filter.select(null, null, unit));
		filter.setSearchText("value");
		Assert.assertTrue(filter.select(null, null, unit));
		filter.setSearchText("gibberish");
		Assert.assertFalse(filter.select(null, null, unit));
	}
}
