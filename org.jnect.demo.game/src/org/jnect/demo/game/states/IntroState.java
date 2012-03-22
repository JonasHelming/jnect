package org.jnect.demo.game.states;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.jnect.demo.game.GameHelper;
import org.jnect.gesture.Gesture;


public class IntroState implements GameState {

	@Override
	public void performAction() {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				GameHelper
						.runCommand("org.eclipse.debug.ui.commands.DebugLast");
			}
		});
	}

	@Override
	public void paintScreen(final Label label) {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				label.setText("Say \"Start Debug Mode\"..");
			}
		});
	}

	@Override
	public String getRequiredSpeechString() {
		return "Start debug mode";
	}

	@Override
	public boolean isGestureEnabled() {
		return false;
	}

	@Override
	public boolean isSpeechEnabled() {
		return true;
	}

	@Override
	public Class<? extends Gesture> getRequiredGesture() {
		return null;
	}

}
