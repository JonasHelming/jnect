package org.jnect.gesture.detectors;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.jnect.bodymodel.Head;
import org.jnect.bodymodel.PositionedElement;
import org.jnect.gesture.Gesture;
import org.jnect.gesture.util.MovingAverageCalculator;


public class CrouchGestureDetector extends Gesture {
	
	private static final int NUM_PERIODS = 15;
	private static final float THRESHOLD = 0.4f;
	
	private MovingAverageCalculator yMovingAvgHead;
	
	private boolean alreadyNotified = false;
	
	
	public CrouchGestureDetector() {
		this.yMovingAvgHead = new MovingAverageCalculator(NUM_PERIODS);
	}
	
	@Override
	public boolean isGestureDetected(Notification notification) {
		if (notification.getEventType() == Notification.SET && notification.wasSet()) {
			EAttribute feature = (EAttribute) notification.getFeature();
			PositionedElement humanBodyPart = (PositionedElement) notification.getNotifier();
			
			if ("y".equals(feature.getName()) && Head.class.isInstance(humanBodyPart)) {
				float sensorValue = notification.getNewFloatValue();
				this.yMovingAvgHead.addValue(sensorValue);
				float avgHeadValue = this.yMovingAvgHead.getMovingAvg();
				float delta = avgHeadValue - sensorValue;
				
				if (delta > avgHeadValue * THRESHOLD) {
					if (!this.alreadyNotified) {
						this.alreadyNotified = true;
						return true;
					}
				} else {
					this.alreadyNotified = false;
				}
			}
		}
		return false;
	}
}
