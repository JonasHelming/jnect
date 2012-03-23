package org.jnect.core;

import org.jnect.bodymodel.Body;
import org.jnect.core.impl.KinectManagerImpl;



public interface KinectManager {
	
	KinectManager INSTANCE = KinectManagerImpl.getInstance();
	
	void startKinect();
	void stopKinect();
	boolean isStarted();
	
	/**
	 * get skeletonmodel
	 * @return {@link Body} - the skeletonmopdel
	 */
	Body getSkeletonModel();

	void startSkeletonTracking();
	void stopSkeletonTracking();
	boolean isSkeletonTrackingStarted();
	
	void addSpeechListener(SpeechListener listener);
	void removeSpeechListener(SpeechListener listener);
	
	void startSpeechRecognition();
	void stopSpeechRecognition();
	boolean isSpeechRecognitionStarted();
}
