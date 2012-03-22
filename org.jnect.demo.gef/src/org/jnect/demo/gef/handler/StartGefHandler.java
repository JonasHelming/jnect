package org.jnect.demo.gef.handler;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;
import org.jnect.core.KinectManager;
import org.jnect.demo.gef.HumanDiagramGraphicalEditor;


public class StartGefHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		KinectManager.INSTANCE.startSkeletonTracking();
		((HumanDiagramGraphicalEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).setContent(KinectManager.INSTANCE.getSkeletonModel());
		
		return null;
	}

}
