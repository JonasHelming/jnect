package org.jnect.gesture;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.jnect.gesture.impl.GestureProxyCallback;


public abstract class Gesture extends EContentAdapter {

	protected GestureProxyCallback gestureProxy;
	
	/**
	 * DO NOT CALL THIS METHOD, IT WILL BE CALLED BY THE GESTUREPROXY
	 * @param gestureProxy the proxy to notify when a gesture is detected
	 */
	public void setGestureProxy(GestureProxyCallback gestureProxy) {
		this.gestureProxy = gestureProxy;
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (gestureProxy != null && isGestureDetected(notification)) {
			this.gestureProxy.notifyGestureDetected(this.getClass());
		}
	}

	/**
	 * checks whether the searched gesture is detected
	 * @param notification the notification containing the model changes
	 * @return true if the gesture was detected
	 */
	protected abstract boolean isGestureDetected(Notification notification);
}
