package de.unipaderborn.visuflow.builder;

import org.junit.Assert;
import org.junit.Test;

public class GlobalSettingsTest {

	@Test
	public void testPutGet() {
		GlobalSettings.put("foo", "bar");
		Assert.assertEquals("bar", GlobalSettings.get("foo"));
		GlobalSettings.put("foo", "bar");
		Assert.assertEquals("bar", GlobalSettings.get("foo"));
	}
}
