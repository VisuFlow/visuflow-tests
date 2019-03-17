package omniscientDebugging;

import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.Launch;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.junit.Before;
import org.junit.Test;

import de.unipaderborn.visuflow.ProjectPreferences;
import de.unipaderborn.visuflow.Visuflow;
import de.unipaderborn.visuflow.builder.AddRemoveVisuFlowNatureHandler;
import de.unipaderborn.visuflow.builder.GlobalSettings;
import de.unipaderborn.visuflow.debug.LaunchConfigurationDelegate;
import de.unipaderborn.visuflow.model.DataModel;
import de.unipaderborn.visuflow.util.ServiceUtil;

public abstract class AbstractTest {
	
	Visuflow visuflow;
	DataModel dataModel;
	IResource resourceAnalysis;
	IResource resourceTarget;
	
	@Before
	public void setup() throws IOException, CoreException, InterruptedException {
		visuflow = Visuflow.getDefault();
		linkTarget();
		LaunchConfigurationDelegate launcher = new LaunchConfigurationDelegate();
		ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfiguration[] configurations = launchManager.getLaunchConfigurations();
		ILaunchConfiguration runner = configurations[0];
		ILaunch launch = new Launch(runner, "debug", null);
		IProgressMonitor tmp = new NullProgressMonitor();
		IProgressMonitor monitor = SubMonitor.convert(tmp, 0);
		launcher.launch(runner, "debug", launch, monitor);
	}
	
	@Test
	public abstract void microBenchmark() throws InterruptedException ;
	
	abstract String getAnalysis();
	
	abstract String getTarget();
	
	void linkTarget() throws CoreException {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		resourceAnalysis = root.findMember(new Path(getAnalysis()));
		resourceTarget = root.findMember(new Path(getTarget()));
		IJavaProject targetProject = JavaCore.create(resourceTarget.getProject());
		try {
			String outputLocation = targetProject.getOutputLocation().toString();
			IResource binFolder = ResourcesPlugin.getWorkspace().getRoot().findMember(outputLocation);
			String classFiles = binFolder.getLocation().toString();
			GlobalSettings.put("Target_Path", classFiles);	
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		IJavaProject analysisProject = JavaCore.create(resourceAnalysis.getProject());
		GlobalSettings.put("AnalysisProject", analysisProject.getProject().getName());
		GlobalSettings.put("TargetProject", targetProject.getProject().getName());
		ProjectPreferences projPref = new ProjectPreferences();
		projPref.createPreferences();
		AddRemoveVisuFlowNatureHandler addNature = new AddRemoveVisuFlowNatureHandler();
		try {
			if(!analysisProject.getProject().isNatureEnabled("JimpleBuilder.VisuFlowNature"))
				addNature.toggleNature(analysisProject.getProject());
		} catch (CoreException e) {
			e.printStackTrace();
		}
		dataModel = ServiceUtil.getService(DataModel.class);
		dataModel.triggerProjectRebuild();
	}

}
