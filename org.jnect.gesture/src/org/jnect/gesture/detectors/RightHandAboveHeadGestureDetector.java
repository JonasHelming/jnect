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
	private static final float THRESHOLD_HEAD = 0.1f;
	private static final float THRESHOLD_FOOT = 0.1f;
	
	private MovingAverageCalculator yMovingAvgHead;
	private MovingAverageCalculator yMovingAvgRightHand;
	
	private boolean gestureHead = false;
	private boolean gestureRightHandAboveHead = false;
	
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
				
				if (humanBodyPart.eClass().equals(Head.class)) {
					float avgHeadValue = this.yMovingAvgHead.calculateMovingAvg(sensorValue);
					float delta = sensorValue - avgHeadValue;
					
					gestureHead = (delta > avgHeadValue * THRESHOLD_HEAD);
				} else if (humanBodyPart.eClass().equals(RightHand.class)) {
					float avgFootValue = this.yMovingAvgRightHand.calculateMovingAvg(sensorValue);
					float delta = sensorValue - avgFootValue;
					
					gestureRightHandAboveHead = (delta > -avgFootValue * THRESHOLD_FOOT);
				} 
				
				if (gestureHead && gestureRightHandAboveHead) {
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
