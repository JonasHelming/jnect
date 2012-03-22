package org.jnect.core.impl;

import org.w3c.dom.Document;

public interface KinectDataHandler {

	void handleSkeletonData(Document doc);
	
	void handleSpeechData(String word);
}
