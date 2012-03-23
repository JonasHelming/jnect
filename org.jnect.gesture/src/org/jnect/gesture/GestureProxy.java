package org.jnect.gesture;

import org.jnect.gesture.impl.GestureProxyImpl;

public interface GestureProxy {

	public GestureProxy INSTANCE = GestureProxyImpl.getInstance();
	/**
	 * add a gesture listener
	 * @param gestureListener the listener to add
	 */
	public void addGestureListener(GestureListener gestureListener);
	/**
	 * removed a gesture listener
	 * @param gestureListener the listener to remove
	 */
	public void removeGestureListener(GestureListener gestureListener);
	
	/**
	 * add a gesture detector
	 * @param gestureDetector the detector to add
	 */
	public void addGestureDetector(Gesture gestureDetector);
	/**
	 * remove a gesture detector
	 * @param gestureDetector to remove
	 */
	public void removeGestureDetector(Gesture gestureDetector);
}