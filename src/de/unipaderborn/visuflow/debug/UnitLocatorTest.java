package de.unipaderborn.visuflow.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class UnitLocatorTest {

	@Test
	public void testFind() {
		List<String> haystack = new ArrayList<>();
		haystack.add("de.visuflow.userstudy2.target.TheTarget r0;");
		haystack.add("r0 := @this: de.visuflow.userstudy2.target.TheTarget;");
		haystack.add("specialinvoke r0.<java.lang.Object: void <init>()>();");
		haystack.add(" ");
		haystack.add("r0.<de.visuflow.userstudy2.target.TheTarget: boolean spy> = 1;");
		haystack.add("r0.<de.visuflow.userstudy2.target.TheTarget: int securityStatus> = 5;");
		haystack.add("r0.<de.visuflow.userstudy2.target.TheTarget: int department> = 8;");
		haystack.add("r0.<de.visuflow.userstudy2.target.TheTarget: java.lang.String name> = \"John Doe\";");
		haystack.add("return;");

		String needle = "r0.<de.visuflow.userstudy2.target.TheTarget: boolean spy> = 1;";
		int[] locations = UnitLocator.find(haystack, needle, 0);
		Assert.assertEquals(154, locations[0]);
		Assert.assertEquals(216, locations[1]);
		Assert.assertEquals(5, locations[2]);
	}

	@Test(expected = NoSuchElementException.class)
	public void testOffsetTooHigh() {
		List<String> haystack = new ArrayList<>();
		haystack.add("de.visuflow.userstudy2.target.TheTarget r0;");
		haystack.add("r0 := @this: de.visuflow.userstudy2.target.TheTarget;");
		haystack.add("specialinvoke r0.<java.lang.Object: void <init>()>();");
		haystack.add(" ");
		haystack.add("r0.<de.visuflow.userstudy2.target.TheTarget: boolean spy> = 1;");
		haystack.add("r0.<de.visuflow.userstudy2.target.TheTarget: int securityStatus> = 5;");
		haystack.add("r0.<de.visuflow.userstudy2.target.TheTarget: int department> = 8;");
		haystack.add("r0.<de.visuflow.userstudy2.target.TheTarget: java.lang.String name> = \"John Doe\";");
		haystack.add("return;");

		String needle = "r0.<de.visuflow.userstudy2.target.TheTarget: boolean spy> = 1;";
		UnitLocator.find(haystack, needle, 160);
	}

	@Test(expected = NoSuchElementException.class)
	public void testFindNotFound() {
		List<String> haystack = new ArrayList<>();
		String needle = "r0.<de.visuflow.userstudy2.target.TheTarget: boolean spy> = 1;";
		UnitLocator.find(haystack, needle, 0);
	}
}
