package org.jnect.demo.debug.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.jnect.core.KinectManager;
import org.jnect.demo.debug.listener.DebugSpeechListener;


public class StartSpeechReconitionHandler extends AbstractHandler {

	private static boolean addedListener = false;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (!addedListener) {
			KinectManager.INSTANCE.addSpeechListener(new DebugSpeechListener());
			addedListener = true;
		}
		KinectManager.INSTANCE.startSpeechRecognition();
		return null;
	}

}
