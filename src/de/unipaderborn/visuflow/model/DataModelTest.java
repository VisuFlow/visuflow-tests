package de.unipaderborn.visuflow.model;

import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockClass;
import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.osgi.service.event.EventAdmin;

import de.unipaderborn.visuflow.model.graph.ICFGStructure;
import de.unipaderborn.visuflow.model.impl.DataModelImpl;
import de.unipaderborn.visuflow.util.ServiceUtil;

public class DataModelTest {

	@Test
	public void testListClassesForEmptyModel() {
		DataModelImpl dataModel = new DataModelImpl();
		List<VFClass> classes = dataModel.listClasses();
		Assert.assertEquals(0, classes.size());
	}

	@Test
	public void testListClassesForFilledModel() {
		DataModel dataModel = setupDataModel();
		VFClass mock = createMockClass("foobar", "a", "b", "c");
		dataModel.setClassList(Collections.singletonList(mock));

		List<VFClass> classes = dataModel.listClasses();
		Assert.assertEquals(1, classes.size());
		Assert.assertEquals(mock, classes.get(0));
	}

	@Test
	public void testListMethodsForEmptyModel() {
		DataModelImpl dataModel = new DataModelImpl();
		List<VFMethod> methods = dataModel.listMethods(createMockClass("foobar"));
		Assert.assertEquals(0, methods.size());
	}

	@Test
	public void testListMethodsForFilledModel() {
		DataModel dataModel = setupDataModel();
		VFClass mockA = createMockClass("mockA", "a", "b", "c");
		VFClass mockB = createMockClass("mockB", "e", "f", "g");
		dataModel.setClassList(listOf(mockA, mockB));

		List<VFMethod> methods = dataModel.listMethods(mockA);
		Assert.assertEquals(3, methods.size());
		Assert.assertEquals("a", methods.get(0).getSootMethod().getName());
		Assert.assertEquals("b", methods.get(1).getSootMethod().getName());
		Assert.assertEquals("c", methods.get(2).getSootMethod().getName());
		methods = dataModel.listMethods(mockB);
		Assert.assertEquals(3, methods.size());
		Assert.assertEquals("e", methods.get(0).getSootMethod().getName());
		Assert.assertEquals("f", methods.get(1).getSootMethod().getName());
		Assert.assertEquals("g", methods.get(2).getSootMethod().getName());
	}


	@Test
	public void testSelectedClass() {
		DataModel dataModel = setupDataModel();

		Assert.assertNull(dataModel.getSelectedMethod());
		Assert.assertEquals(0, dataModel.getSelectedClassMethods().size());
		Assert.assertEquals(0, dataModel.getSelectedMethodUnits().size());

		VFClass mockA = createMockClass("mockA", "a", "b", "c");
		VFClass mockB = createMockClass("mockB", "e", "f", "g");
		mockB.getMethods().get(0).getUnits().add(createMockUnit("a.b.c.Unit"));
		dataModel.setClassList(listOf(mockA, mockB));

		dataModel.setSelectedClass(mockB);
		Assert.assertEquals(mockB, dataModel.getSelectedClass());

		VFMethod e = mockB.getMethods().get(0);
		Assert.assertEquals(e, dataModel.getSelectedMethod());
		Assert.assertEquals(mockB.getMethods(), dataModel.getSelectedClassMethods());
		Assert.assertEquals(e.getUnits(), dataModel.getSelectedMethodUnits());
	}

	@Test
	public void testSetInSet() {
		DataModel dataModel = setupDataModel();
		VFClass mockA = createMockClass("mockA", "a", "b", "c");
		dataModel.setClassList(listOf(mockA));
		VFMethod a = mockA.getMethods().get(0);
		a.setVfClass(mockA);
		String fqn = "a.b.c.Unit";
		VFUnit mockUnit = createMockUnit(fqn);
		a.getUnits().add(mockUnit);

		dataModel.setInSet(fqn, "inset", "foobar");
		Assert.assertEquals(mockUnit.getInSet(), "foobar");
	}

	@Test
	public void testSetOutSet() {
		DataModel dataModel = setupDataModel();
		VFClass mockA = createMockClass("mockA", "a", "b", "c");
		dataModel.setClassList(listOf(mockA));
		VFMethod a = mockA.getMethods().get(0);
		a.setVfClass(mockA);
		String fqn = "a.b.c.Unit";
		VFUnit mockUnit = createMockUnit(fqn);
		a.getUnits().add(mockUnit);

		dataModel.setOutSet(fqn, "inset", "foobar");
		Assert.assertEquals(mockUnit.getOutSet(), "foobar");
	}

	@Test
	public void testSelectedMethod() {
		DataModel dataModel = setupDataModel();
		VFClass mockA = createMockClass("mockA", "a", "b", "c");
		VFClass mockB = createMockClass("mockB", "e", "f", "g");
		dataModel.setClassList(listOf(mockA, mockB));

		VFMethod e = mockB.getMethods().get(0);
		dataModel.setSelectedMethod(e, false);
		Assert.assertEquals(e, dataModel.getSelectedMethod());
	}

	@Test
	public void testGetVfMethodByName() {
		DataModel dataModel = setupDataModel();
		VFClass mockA = createMockClass("mockA", "a", "b", "c");
		dataModel.setClassList(listOf(mockA));

		VFMethod a = mockA.getMethods().get(0);
		Assert.assertEquals(a, dataModel.getVFMethodByName(a.getSootMethod()));

		// this has not been added to the model, so it should not be found
		VFClass unknownClass = createMockClass("mockB", "a", "b", "c");
		VFMethod unknownMethod = unknownClass.getMethods().get(0);
		Assert.assertEquals(null, dataModel.getVFMethodByName(unknownMethod.getSootMethod()));
	}

	private DataModel setupDataModel() {
		DataModelImpl model = new DataModelImpl();
		model.setEventAdmin(ServiceUtil.getService(EventAdmin.class));
		model.setIcfg(new ICFGStructure());
		model.setClassList(new ArrayList<VFClass>());
		return model;
	}

	@SafeVarargs
	private final <T> List<T> listOf(T... items) {
		return Arrays.asList(items);
	}
}
