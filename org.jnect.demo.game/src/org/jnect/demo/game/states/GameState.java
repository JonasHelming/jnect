package org.jnect.demo.game.states;

import org.eclipse.swt.widgets.Label;
import org.jnect.gesture.Gesture;


public interface GameState {

	public Class<? extends Gesture> getRequiredGesture();

	public String getRequiredSpeechString();

	public void performAction();

	public void paintScreen(Label label);

	public boolean isGestureEnabled();

	public boolean isSpeechEnabled();
}
