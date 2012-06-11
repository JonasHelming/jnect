package org.jnect.emfstore.replay.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.jnect.emfstore.KinectBodyPresentationManager;
import org.jnect.emfstore.replay.Replay;

public class StartReplayHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Replay replay = Replay.getInstance();
		replay.setupBody();
		replay.displaySlider();
		KinectBodyPresentationManager.showReplayBody();
		return null;
	}
}
