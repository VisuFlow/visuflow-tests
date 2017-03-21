package de.unipaderborn.visuflow.model;

import java.util.Collections;

import soot.SootClass;
import soot.SootMethod;
import soot.Unit;
import soot.VoidType;
import soot.jimple.internal.JReturnVoidStmt;
import soot.tagkit.AttributeValueException;
import soot.tagkit.Tag;

public class DataModelMockFactory {
	public static VFClass createMockClass(String name, String... methods) {
		VFClass mock = new VFClass(new SootClass(name));
		mock.getSootClass().setName(name);
		for (String method : methods) {
			VFMethod vfm = createMockMethod(name, method);
			vfm.getSootMethod().setDeclaringClass(mock.getSootClass());
			vfm.getSootMethod().setDeclared(true);
			mock.getMethods().add(vfm);
		}
		return mock;
	}

	public static VFMethod createMockMethod(String className, String methodName) {
		SootMethod sm = createSootMethod(methodName);
		VFMethod method = new VFMethod(sm);
		return method;
	}

	public static SootMethod createSootMethod(String methodName) {
		SootMethod sm = new SootMethod(methodName, Collections.emptyList(), VoidType.v());
		return sm;
	}

	public static VFUnit createMockUnit(String fqn) {
		Unit u = new JReturnVoidStmt();
		u.addTag(new Tag() {
			@Override
			public byte[] getValue() throws AttributeValueException {
				return fqn.getBytes();
			}

			@Override
			public String getName() {
				return "Fully Qualified Name";
			}
		});
		VFUnit unit = new VFUnit(u);
		return unit;
	}
}
