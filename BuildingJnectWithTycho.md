# Building jnect with Tycho #

### Getting started ###

Jnect is built using Tycho. Tycho is a set of Maven 3 plugins, so in order to get started download Maven 3 from [here](http://maven.apache.org/download.html) and install it. You don't have to install Tycho, because Maven will download required plugins itself.

### Adding new plugins and features ###

Every new plugin and feature will require a POM.xml. To set it up you can look at existing POMs in the repository or check the [Tycho Reference Card](http://wiki.eclipse.org/Tycho/Reference_Card). Then add the plugin/feature to the parent POM (can be found in the root directory) in the modules section. If you want to include a feature in the updatesite add it to the site.xml in org.jnect.updatesite.

### Changing versions ###

Tycho has a command to set new version numbers. This will update the versions in POM, feature.xml and MANIFEST.MF files. You can invoke it from the root directory like this:
```
mvn -Dtycho.mode=maven org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=X.Y.Z-SNAPSHOT
```

### Creating a new build ###

To create a new build invoke to following command from the root directory:
```
mvn clean install
```
The build results will be created in a "target/" directory in the respective modules.

As official jnect developer, you only need to update the version as described above and upload the project to google code again. The jenkins build server at cloudbees is currently configured to search through the repository for changes every hour and to make a new build if that is the case. Additionally a new build is made once every week. You can find the artifacts of the latest build (including the ready-to-use zip file) at https://jnect.ci.cloudbees.com/job/jnect/.