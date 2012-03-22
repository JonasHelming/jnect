package org.jnect.gesture;

import org.jnect.gesture.impl.GestureProxyImpl;

public interface GestureProxy {

	public GestureProxy INSTANCE = GestureProxyImpl.getInstance();

	public void addGestureListener(GestureListener gestureListener);
	public void removeGestureListener(GestureListener gestureListener);

	public void addGestureDetector(Gesture gestureDetector);
	public void removeGestureDetector(Gesture gestureDetector);
}