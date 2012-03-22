package org.jnect.gesture.impl;

import org.jnect.gesture.Gesture;

public interface GestureProxyCallback {

	void notifyGestureDetected(Class<? extends Gesture> gesture);
}
