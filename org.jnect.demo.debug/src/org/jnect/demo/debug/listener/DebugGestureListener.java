package org.jnect.demo.debug.listener;

import org.jnect.demo.debug.DebugHelper;
import org.jnect.gesture.Gesture;
import org.jnect.gesture.GestureListener;
import org.jnect.gesture.detectors.CrouchGestureDetector;
import org.jnect.gesture.detectors.RightHandAboveHeadGestureDetector;

public class DebugGestureListener extends GestureListener {

	@Override
	public void notifyGestureDetected(Class<? extends Gesture> gesture) {
		System.out.println("Gesture recognized: " + gesture.getSimpleName());
		
		if (RightHandAboveHeadGestureDetector.class.equals(gesture)) {
			DebugHelper.stepOver();
		} else if(CrouchGestureDetector.class.equals(gesture)) {
			DebugHelper.stepInto();
		}
		
	}
}
