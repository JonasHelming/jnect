package org.jnect.emfstore;

import org.eclipse.ui.PlatformUI;
import org.jnect.bodymodel.Body;
import org.jnect.core.KinectManager;
import org.jnect.demo.gef.HumanDiagramGraphicalEditor;
import org.jnect.emfstore.replay.Replay;

public class KinectBodyPresentationManager {
	public static void showReplayBody() {
		Replay replay = Replay.getInstance();
		replay.setupBody();
		replay.displaySlider();
		setBody(replay.getReplayBody());
	}

	public static void showLiveBody() {
		setBody(KinectManager.INSTANCE.getSkeletonModel());
	}

	private static void setBody(Body body) {
		((HumanDiagramGraphicalEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
			.getActiveEditor()).setContent(body);

	}
}
