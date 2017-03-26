package de.unipaderborn.visuflow.model;

import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockClass;
import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockMethod;
import static de.unipaderborn.visuflow.model.DataModelMockFactory.createMockUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import de.unipaderborn.visuflow.model.graph.ICFGStructure;
import de.unipaderborn.visuflow.model.impl.DataModelImpl;

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
	public void testListUnitsForEmptyModel() {
		DataModel dataModel = new DataModelImpl();
		List<VFUnit> units = dataModel.listUnits(createMockMethod("Foo", "foobar"));
		Assert.assertEquals(0, units.size());
	}

	@Test
	public void testListUnitsForFilledModel() {
		DataModel dataModel = setupDataModel();
		VFClass mockA = createMockClass("mockA", "a", "b", "c");
		dataModel.setClassList(listOf(mockA));
		VFMethod a = mockA.getMethods().get(0);
		VFUnit unit1 = createMockUnit("a.b.c.Foo.bar.r1 = $r2;");
		unit1.setVfMethod(a);
		VFUnit unit2 = createMockUnit("a.b.c.Foo.bar.r2 = $r3;");
		unit2.setVfMethod(a);
		VFUnit unit3 = createMockUnit("a.b.c.Foo.bar.return;");
		unit3.setVfMethod(a);
		a.getUnits().addAll(listOf(unit1, unit2, unit3));

		List<VFUnit> units = dataModel.listUnits(a);
		Assert.assertEquals(3, units.size());
		Assert.assertTrue(units.contains(unit1));
		Assert.assertTrue(units.contains(unit2));
		Assert.assertTrue(units.contains(unit3));
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
	public void testSetIcfg() {
		DataModel dataModel = setupDataModel();
		ICFGStructure icfg = new ICFGStructure();
		dataModel.setIcfg(icfg);
		Assert.assertEquals(icfg, dataModel.getIcfg());
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

	@Test
	public void testFilterGraph() {
		DataModelImpl impl = setupDataModel();
		EventAdminImpl eventAdmin = new EventAdminImpl();
		impl.setEventAdmin(eventAdmin);

		VFClass vfClass = createMockClass("a.b.c.Foo", "bar");
		VFMethod vfMethod = vfClass.getMethods().get(0);
		VFUnit unit = createMockUnit("a.b.c.Foo.bar.return;");
		vfMethod.getUnits().add(unit);
		unit.setVfMethod(vfMethod);
		impl.setClassList(Collections.singletonList(vfClass));
		VFNode node = new VFNode(unit, 0);
		List<VFNode> toFilter = Collections.singletonList(node);

		try {
			impl.filterGraph(toFilter, true, false, "filter");
			Event event = eventAdmin.event;
			Assert.assertEquals(DataModel.EA_TOPIC_DATA_FILTER_GRAPH, event.getTopic());
			Assert.assertEquals(toFilter, event.getProperty("nodesToFilter"));
			Assert.assertEquals(true, event.getProperty("selection"));
			Assert.assertEquals("filter", event.getProperty("uiClassName"));
			Assert.assertEquals(false, event.getProperty("panToNode"));

			impl.filterGraph(toFilter, true, false, null);
			event = eventAdmin.event;
			Assert.assertEquals("filter", event.getProperty("uiClassName"));

			impl.filterGraph(Collections.emptyList(), true, false, null);
			event = eventAdmin.event;
			Assert.assertEquals(0, ((List<?>)event.getProperty("nodesToFilter")).size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testRefreshView() {
		DataModelImpl model = new DataModelImpl();
		EventAdminImpl eventAdmin = new EventAdminImpl();
		model.setEventAdmin(eventAdmin);
		model.refreshView();

		Event event = eventAdmin.event;
		Assert.assertEquals(DataModel.EA_TOPIC_DATA_VIEW_REFRESH, event.getTopic());
		Assert.assertEquals(1, event.getPropertyNames().length);
	}

	private DataModelImpl setupDataModel() {
		DataModelImpl model = new DataModelImpl();
		model.setEventAdmin(new EventAdminImpl());
		model.setIcfg(new ICFGStructure());
		model.setClassList(new ArrayList<VFClass>());
		return model;
	}

	@SafeVarargs
	private final <T> List<T> listOf(T... items) {
		return Arrays.asList(items);
	}

	private class EventAdminImpl implements EventAdmin {
		Event event;

		@Override
		public void postEvent(Event event) {
			this.event = event;
		}

		@Override
		public void sendEvent(Event event) {
			this.event = event;
		}
	}
}
