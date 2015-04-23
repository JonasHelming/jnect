# C# Source #

The C# code for accessing the Kinect SDK can be found [here](https://code.google.com/a/eclipselabs.org/p/jnect/source/browse/#git%2Forg.jnect.kinect%2FMicrosoftKinectWrapper). It is embedded in Jnects Java code by [jni4net](http://jni4net.sourceforge.net/).

# JNI4NET Bridge #

In the project org.jnect.kinect, you can find the C# wrapper source code (and a corresponding Visual Studio Project). Furthermore, there is everything needed in order to generate the jni4net bridge out of the C# binaries. If you made any changes to the C# code, the generation needs to be done as follows:

  1. Compile the C# sources and generate an assembly _MicrosoftKinectWrapper.dll_ from it. (Just build the VS Solution "MicrosoftKinectWrapper" in Visual Studio).
  1. Generate the jni4net bridges by executing the script _generate.bat_ in the folder _org.jnect.kinect\MicrosoftKinectWrappers\jni4net\_generation_. This script needs to be started from the Visual Studio Command Prompt (Start -> VisualStudio -> Visual Studio Tools) and in the folder it is located in (jni4net\_generation).

The general approach of how to generate the jni4net bridge can be found [here](https://docs.google.com/document/d/18SvSZXpmJhjg6K5Z-BAJ-PD1_4uHTDTsAujQ9tzlbtM/edit). If you should face any problems whith the generation precedure, have a look at the explanations there. (It describes in detail, what the script does and possible problems there could be).

[Here](http://jni4net.sourceforge.net/) is the website of the jni4net project. You can find more info there.