# Introduction #
In this example we want to demonstrate how to modify the size and position of an e4 application with jnect.
This is the example that was shown in the "Eclipse Magazin".

# Details #
First we have to create 2 Adapters. One for the left hand and one for the right hand. With the left hand the top left corner of the application is controlled and thus the position. With the difference between the right hand and the left the width and height of the application is set.
```
public class LeftHandAdapter extends AdapterImpl implements Adapter {

	private final MWindow window;

	public LeftHandAdapter(MWindow window) {
		this.window = window;
	}

	@Override
	public void notifyChanged(Notification msg) {
		window.setX((int) ((((PositionedElement)msg.getNotifier()).getX()+1)*500));
		window.setY((int) (((((PositionedElement)msg.getNotifier()).getY()*-1)+1)*200));
		super.notifyChanged(msg);
	}

}
public class RightHandAdapter extends AdapterImpl implements Adapter {
	private final MWindow window;

	public RightHandAdapter(MWindow window) {
		this.window = window;
	}

	@Override
	public void notifyChanged(Notification msg) {
		window.setWidth((int) ((skeletonModel.getRightsHand().getX()-skeletonModel.getLeftHand().getX())*500));
		window.setHeight(-1*(int) ((skeletonModel.getRightsHand().getY()-skeletonModel.getLeftHand().getY())*500));
		super.notifyChanged(msg);
	}
}
```

Next we implement a SpeechListener for recognizing "Enable Resize" and "Stop Resize". For this we also have to define two constants for the speech recognition.

```
private static final String STOP_RESIZE = "Stop Resize";
private static final String RESIZE = "Enable Resize";

SpeechListener myListener=new SpeechListener() {
	
	@Override
	public void notifySpeech(String speech) {
		if(speech.equals(RESIZE)){
			resize();
		}
		if(speech.equals(STOP_RESIZE)){
			stopresize();
		}
	}
	
	@Override
	public Set<String> getWords() {
		Set<String> ret = new HashSet<String>();
		ret.add(RESIZE);
		ret.add(STOP_RESIZE);
		return ret;
	}
}
```

For working correctly we need the method resize. It has to register both adapters and start the skeleton tracking.
```
private void resize() {
	kinectManager.startSkeletonTracking();
	skeletonModel=kinectManager.getSkeletonModel();
	kinectManager.getSkeletonModel().getLeftHand().eAdapters().add(leftHandAdapter );
	kinectManager.getSkeletonModel().getRightHand().eAdapters().add(rightHandAdapter );
}
```
We also need the stopresize method, which unregister both adapters and stops the skeleton tracking.
```
private void stopresize() {
	kinectManager.getSkeletonModel().getLeftHand().eAdapters().remove(leftHandAdapter );
	kinectManager.getSkeletonModel().getRightHand().eAdapters().remove(rightHandAdapter );
	kinectManager.stopSkeletonTracking();
}
```
As last thing we have to implement the execute method for the handler. For this we get a KinectManager, start the kinect, instantiate the adapters, add the SpeechListener and start the SpeechRecognition.
```
@Execute
public void execute(final MWindow window){
	final KinectManager kinectManager = KinectManager.INSTANCE;
	kinectManager.startKinect();
	
	final Adapter leftHandAdapter = new  LeftHandAdapter(window);
	final Adapter rightHandAdapter = new RightHandAdapter(window);

	kinectManager.addSpeechListener(mySpeechListener);
	
	kinectManager.startSpeechRecognition();
}
```
And that is all.

Here is the full code for the handler.
```
public class OpenHandler {

	private static final String STOP_RESIZE = "Stop Resize";
	private static final String RESIZE = "Enable Resize";
	private HumanContainer skeletonModel;

	public class LeftHandAdapter extends AdapterImpl implements Adapter {

		private final MWindow window;

		public LeftHandAdapter(MWindow window) {
			this.window = window;
		}

		@Override
		public void notifyChanged(Notification msg) {
			window.setX((int) ((((PositionedElement)msg.getNotifier()).getX()+1)*500));
			window.setY((int) (((((PositionedElement)msg.getNotifier()).getY()*-1)+1)*200));
			super.notifyChanged(msg);
		}

	}
	public class RightHandAdapter extends AdapterImpl implements Adapter {
		private final MWindow window;

		public RightHandAdapter(MWindow window) {
			this.window = window;
		}

		@Override
		public void notifyChanged(Notification msg) {
			window.setWidth((int) ((skeletonModel.getRightsHand().getX()-skeletonModel.getLeftHand().getX())*500));
			window.setHeight(-1*(int) ((skeletonModel.getRightsHand().getY()-skeletonModel.getLeftHand().getY())*500));
			super.notifyChanged(msg);
		}
	}

	@Execute
	public void execute(final MWindow window){
		final KinectManager kinectManager = KinectManager.INSTANCE;
		kinectManager.startKinect();
		
		final Adapter leftHandAdapter = new  LeftHandAdapter(window);
		final Adapter rightHandAdapter = new RightHandAdapter(window);
		
		kinectManager.addSpeechListener(new SpeechListener() {
			

			@Override
			public void notifySpeech(String speech) {
				if(speech.equals(RESIZE)){
					resize();
				}
				if(speech.equals(STOP_RESIZE)){
					stopresize();
				}
				
			}
			
			private void stopresize() {
				kinectManager.getSkeletonModel().getLeftHand().eAdapters().remove(leftHandAdapter );
				kinectManager.getSkeletonModel().getRightHand().eAdapters().remove(rightHandAdapter );
				kinectManager.stopSkeletonTracking();
				
			}

			private void resize() {
				kinectManager.startSkeletonTracking();
				skeletonModel=kinectManager.getSkeletonModel();
				kinectManager.getSkeletonModel().getLeftHand().eAdapters().add(leftHandAdapter );
				kinectManager.getSkeletonModel().getRightHand().eAdapters().add(rightHandAdapter );
				
			}

			@Override
			public Set<String> getWords() {
				Set<String> ret = new HashSet<String>();
				ret.add(RESIZE);
				ret.add(STOP_RESIZE);
				return ret;
			}
		});
		
		kinectManager.startSpeechRecognition();	
	}
}
```