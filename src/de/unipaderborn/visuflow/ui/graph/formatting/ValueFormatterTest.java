package de.unipaderborn.visuflow.ui.graph.formatting;

import static de.unipaderborn.visuflow.model.DataModelMockFactory.createSootClass;
import static de.unipaderborn.visuflow.model.DataModelMockFactory.createSootMethod;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import soot.BooleanType;
import soot.IntType;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootFieldRef;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.Type;
import soot.Value;
import soot.VoidType;
import soot.jimple.FieldRef;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.InvokeExpr;
import soot.jimple.internal.JInstanceFieldRef;
import soot.jimple.internal.JNewExpr;
import soot.jimple.internal.JStaticInvokeExpr;
import soot.jimple.internal.JVirtualInvokeExpr;
import soot.jimple.internal.JimpleLocal;

public class ValueFormatterTest {

	@Test
	public void testShortenClassNames() {
		String in = "r0.<de.visuflow.userstudy2.target.TheTarget: boolean spy> = 1";
		String out = "r0.<TheTarget: boolean spy> = 1";
		Assert.assertEquals(out, ValueFormatter.shortenClassNames(in));

		in = "foobar";
		Assert.assertEquals(in, ValueFormatter.shortenClassNames(in));
	}

	@Test
	public void testShorten() {
		Assert.assertEquals("Foo", ValueFormatter.shorten("a.b.c.Foo"));
	}

	@Test
	public void testFormatInstanceInvoke() {
		InvokeExpr invoke = createInvokeExpr();
		Assert.assertEquals("r1.<Foobar: void foobar(int, boolean)>(a, b)", ValueFormatter.formatInvoke(invoke));

		invoke = createStaticInvokeExpr();
		Assert.assertEquals("<Foobar: void foobar(int,boolean)>(a, b)", ValueFormatter.formatInvoke(invoke));
	}

	@Test
	public void testFormatFieldRef() {
		FieldRef fieldRef = createFieldRef();
		Assert.assertEquals("r1.<Foobar: int foobar>", ValueFormatter.formatFieldRef(fieldRef));
	}

	@Test
	public void testFormat() {
		InvokeExpr invoke = createInvokeExpr();
		Assert.assertEquals("r1.<Foobar: void foobar(int, boolean)>(a, b)", ValueFormatter.format(invoke));

		FieldRef fieldRef = createFieldRef();
		Assert.assertEquals("r1.<Foobar: int foobar>", ValueFormatter.format(fieldRef));

		RefType refType = RefType.v();
		SootClass declaringClass = createSootClass("a.b.c.Foobar");
		refType.setSootClass(declaringClass);
		refType.setClassName(declaringClass.getName());
		JNewExpr newExpr = new JNewExpr(refType);
		Assert.assertEquals("new a.b.c.Foobar", ValueFormatter.format(newExpr));
	}

	private InvokeExpr createInvokeExpr() {
		RefType refType = RefType.v();
		Value base = new JimpleLocal("r1", refType);
		SootClass declaringClass = createSootClass("a.b.c.Foobar");
		refType.setSootClass(declaringClass);
		refType.setClassName(declaringClass.getName());
		SootMethod sootMethod = createSootMethod("foobar");
		declaringClass.addMethod(sootMethod);
		sootMethod.setDeclaringClass(declaringClass);
		List<Type> paramTypes = new ArrayList<>();
		paramTypes.add(IntType.v());
		paramTypes.add(BooleanType.v());
		sootMethod.setParameterTypes(paramTypes);
		SootMethodRef methodRef = Scene.v().makeMethodRef(declaringClass, "foobar", paramTypes, VoidType.v(), false);

		List<Value> params = new ArrayList<>();
		params.add(new JimpleLocal("a", IntType.v()));
		params.add(new JimpleLocal("b", BooleanType.v()));
		InstanceInvokeExpr invoke = new JVirtualInvokeExpr(base, methodRef, params);
		return invoke;
	}

	public static InvokeExpr createStaticInvokeExpr() {
		RefType refType = RefType.v();
		SootClass declaringClass = createSootClass("a.b.c.Foobar");
		refType.setSootClass(declaringClass);
		refType.setClassName(declaringClass.getName());
		SootMethod sootMethod = createSootMethod("foobar");
		declaringClass.addMethod(sootMethod);
		sootMethod.setDeclaringClass(declaringClass);
		List<Type> paramTypes = new ArrayList<>();
		paramTypes.add(IntType.v());
		paramTypes.add(BooleanType.v());
		sootMethod.setParameterTypes(paramTypes);
		SootMethodRef methodRef = Scene.v().makeMethodRef(declaringClass, "foobar", paramTypes, VoidType.v(), true);

		List<Value> params = new ArrayList<>();
		params.add(new JimpleLocal("a", IntType.v()));
		params.add(new JimpleLocal("b", BooleanType.v()));
		JStaticInvokeExpr invoke = new JStaticInvokeExpr(methodRef, params);
		return invoke;
	}

	private FieldRef createFieldRef() {
		RefType refType = RefType.v();
		Value base = new JimpleLocal("r1", refType);
		SootClass declaringClass = createSootClass("a.b.c.Foobar");
		refType.setSootClass(declaringClass);
		refType.setClassName(declaringClass.getName());
		SootMethod sootMethod = createSootMethod("foobar");
		declaringClass.addMethod(sootMethod);
		sootMethod.setDeclaringClass(declaringClass);
		SootFieldRef f = Scene.v().makeFieldRef(declaringClass, "foobar", IntType.v(), false);
		FieldRef fieldRef = new JInstanceFieldRef(base, f);
		return fieldRef;
	}
}
