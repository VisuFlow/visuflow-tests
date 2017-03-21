package de.unipaderborn.visuflow.model;
import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockMethod;
import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockUnit;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import soot.Unit;

public class VFUnitTest {

	@Test
	public void testSetGetMethod() {
		VFUnit unit = createMockUnit("a.b.c.Foo.bar.r1 = $r2;");
		VFMethod method = createMockMethod("a.b.c.Foo", "bar");
		unit.setVfMethod(method);
		Assert.assertTrue(method == unit.getVfMethod());
	}

	@Test
	public void testSetGetCustomAttributes() {
		VFUnit unit = createMockUnit("a.b.c.Foo.bar.r1 = $r2;");
		Assert.assertNotNull(unit.getHmCustAttr());
		Assert.assertTrue(unit.getHmCustAttr().isEmpty());

		Map<String, String> attr = new HashMap<>();
		attr.put("foo", "bar");
		unit.setHmCustAttr(attr);

		Assert.assertTrue(attr == unit.getHmCustAttr());
		Assert.assertEquals("bar", unit.getHmCustAttr().get("foo"));
	}

	@Test
	public void testGetUnit() {
		VFUnit vfUnit = createMockUnit("a.b.c.Foo.bar.return;");
		Unit unit = vfUnit.getUnit();
		Assert.assertNotNull(unit);
		Assert.assertEquals("return", unit.toString());
	}

	@Test
	public void testSetGetInset() {
		VFUnit vfUnit = createMockUnit("a.b.c.Foo.bar.return;");
		vfUnit.setInSet("InSet");
		Assert.assertEquals("InSet", vfUnit.getInSet());
	}

	@Test
	public void testSetGetOutset() {
		VFUnit vfUnit = createMockUnit("a.b.c.Foo.bar.return;");
		vfUnit.setOutSet("OutSet");
		Assert.assertEquals("OutSet", vfUnit.getOutSet());
	}

	@Test
	public void testToString() {
		VFUnit vfUnit = createMockUnit("a.b.c.Foo.bar.return;");
		Assert.assertEquals("a.b.c.Foo.bar.return;", vfUnit.toString());
	}

	@Test
	public void testHashCode() {
		VFUnit vfUnit = createMockUnit("a.b.c.Foo.bar.return;");
		Assert.assertEquals(-66157085, vfUnit.hashCode());
	}

	@Test
	public void testEquals() {
		VFUnit a = createMockUnit("a.b.c.Foo.bar.return;");
		VFUnit b = createMockUnit("a.b.c.Foo.bar.return;");
		VFUnit c = createMockUnit("a.b.c.Bar.bar.return;");

		Assert.assertTrue(a.equals(a));
		Assert.assertTrue(a.equals(b));
		Assert.assertFalse(a.equals(null));
		Assert.assertFalse(a.equals("Wrong class"));
		Assert.assertFalse(a.equals(c));
	}

}
