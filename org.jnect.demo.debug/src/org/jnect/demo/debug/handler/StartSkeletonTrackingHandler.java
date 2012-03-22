package org.jnect.demo.debug.handler;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.jnect.core.KinectManager;
import org.jnect.demo.debug.listener.DebugGestureListener;
import org.jnect.gesture.GestureProxy;
import org.jnect.gesture.detectors.CrouchGestureDetector;
import org.jnect.gesture.detectors.RightHandAboveHeadGestureDetector;


public class StartSkeletonTrackingHandler extends AbstractHandler {

	private static boolean addedGestures = false;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (!addedGestures) {
			GestureProxy.INSTANCE.addGestureListener(new DebugGestureListener());
			GestureProxy.INSTANCE.addGestureDetector(new RightHandAboveHeadGestureDetector());
			GestureProxy.INSTANCE.addGestureDetector(new CrouchGestureDetector());
			addedGestures = true;
		}
		KinectManager.INSTANCE.startSkeletonTracking();
		return null;
	}

}
