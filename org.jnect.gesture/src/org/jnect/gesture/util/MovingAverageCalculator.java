package org.jnect.gesture.util;

import java.util.LinkedList;
import java.util.Queue;
/**
 * This class helps to calculate a moving average, this way the data can be cleaned.
 * @author Philip Achenbach
 * @author Eugen Neufeld
 */
public class MovingAverageCalculator {

	private final Queue<Float> window = new LinkedList<Float>();
	private final int numPeriods;
	private float sum;
	
	/**
	 * instantiate the {@link MovingAverageCalculator} with a number of periods to build the average from
	 * @param numPeriods number of values top build the average from
	 */
	public MovingAverageCalculator(int numPeriods) {
		this.numPeriods = numPeriods;
	}
	
	public void addValue(float num) {
		sum += num;
		window.add(num);
		if (window.size() > numPeriods) {
			sum -= window.remove();
		}
	}
	
	public float getMovingAvg() {
		return (window.size() == 0) ? 0 : sum / window.size();
	}
}
