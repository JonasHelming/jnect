package org.jnect.gesture;

import java.util.Collections;
import java.util.Set;

public abstract class GestureListener {

	/**
	 * callback method, that gets called when a gesture is detected
	 * @param gesture class that was detected
	 */
	public abstract void notifyGestureDetected(Class<? extends Gesture> gesture);
	/**
	 * Set of Gestures this listener listens to	
	 * @return Set of Gestures to be notified about 
	 */
	public Set<Gesture> getGestures(){
		return Collections.emptySet();
	}
	/**
	 * Wether the listener listens only to special gestures 
	 * @return true if only special gestures should be provided to the listener
	 */
	public boolean isFiltered() {
		return false;
	}
}
