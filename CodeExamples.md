# Introduction #

Here you can see examples of how to create your own speechlistener and your own gesturelistener.

# SpeechListener #
As an example a "Hello World" SpeechListener will be implemented.

The abstract SpeechListener class from org.jnect.core defines 3 methods:
  * `void notifySpeech(String speech)`
  * `Set <String> getWords()`
  * `boolean isFiltered()`

notifySpeech is called if a phrase is recognized. The passed parameter is the recognized phrase.

getWords is called when you add your SpeechListener to the KinectManager. This method should provide a set of words that should be recognized.

isFiltered is also called when the SpeechListener is added to the KinectManager. This value decides, whether the SpeechListener will be notified only for the phrases it defines or for all phrases known. The default return value is true, so if you don't overwrite this class, then the SpeechListener will only be notified when a phrase provided by it is recognized.

Here is the example:
```java

import java.util.HashSet;
import java.util.Set;
import org.jnect.core.KinectManager;
import org.jnect.core.SpeechListener;

public class HelloWorldSpeechListener extends SpeechListener {

private final Set<String> words = new HashSet<String>();

public HelloWorldSpeechListener(){
words.add("Hello World");
}

@Override
public Set<String> getWords() {
return words;
}

@Override
public void notifySpeech(String speech) {
System.out.println("Speech recongized: " + speech);
}
}
```

As you can see the method isFiltered is not overriden so the HelloWorldSpeechListener will only be notified if "Hello World" is recognized.

# GestureListener #
In this example a HelloWorldGestureListener will be shown.

Gesture-classes can be found in the org.jnect.gesture plugin. The abstract GestureListener defines the following methods:
  * `void notifyGestureDetected(Class<? extends Gesture> gesture)`
  * `Set<Gesture> getGestures()`
  * `boolean isFiltered()`

notifyGestureDetected is called when a gesture is detected.

getGestures is called when adding the GestureListener to the GestureProxy. This is used to notify the GestureListener only for gestures it knows. The default is empty because the GestureListener is not filtered.

isFiltered is called when adding the GestureListener to the GestureProxy. If true then the listener will only be notified about the gestures it knows. The default value is false.

Now let's take a look at the example:
```java

import java.util.HashSet;
import java.util.Set;
import org.jnect.gesture.GestureListener;
import org.jnect.gesture.GestureProxy;
import org.jnect.gesture.detectors.CrouchGestureDetector;

public class HelloWorldGestureListener extends GestureListener {

@Override
public void notifyGestureDetected(Class<? extends Gesture> gesture) {
if (CrouchGestureDetector.class.equals(gesture)) {
System.out.println("Hello World!");
}

}
}
```

In the example a CrouchGestureDetector is expeced. So if a coruch gesture is recognized then "Hello World" will be printed.