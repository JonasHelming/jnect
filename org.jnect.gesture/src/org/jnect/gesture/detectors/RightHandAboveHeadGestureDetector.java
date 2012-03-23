package org.jnect.gesture.detectors;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.jnect.bodymodel.Head;
import org.jnect.bodymodel.PositionedElement;
import org.jnect.bodymodel.RightHand;
import org.jnect.gesture.Gesture;
import org.jnect.gesture.impl.MovingAverageCalculator;


public class RightHandAboveHeadGestureDetector extends Gesture {

	private static final int NUM_PERIODS = 10;
	
	private MovingAverageCalculator yMovingAvgHead;
	private MovingAverageCalculator yMovingAvgRightHand;
	
	private boolean alreadyNotified = false;
	
	
	public RightHandAboveHeadGestureDetector() {
		this.yMovingAvgHead = new MovingAverageCalculator(NUM_PERIODS);
		this.yMovingAvgRightHand = new MovingAverageCalculator(NUM_PERIODS); // Two feet in one moving average
	}
	
	@Override
	public boolean isGestureDetected(Notification notification) {
		if (notification.getEventType() == Notification.SET && notification.wasSet()) {
			EAttribute feature = (EAttribute) notification.getFeature();
			PositionedElement humanBodyPart = (PositionedElement) notification.getNotifier();
			
			if ("y".equals(feature.getName())) {
				float sensorValue = notification.getNewFloatValue();
				
				if (Head.class.isInstance(humanBodyPart)) {
					this.yMovingAvgHead.calculateMovingAvg(sensorValue);
				} else if (RightHand.class.isInstance(humanBodyPart)) {
					this.yMovingAvgRightHand.calculateMovingAvg(sensorValue);
				} 
				
				if (yMovingAvgRightHand.getMovingAvg()>yMovingAvgHead.getMovingAvg()) {
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
