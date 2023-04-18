# Legacy JARs in JPMS
Most Java code was written prior to the introduction of the module system and must continue to work just as it does today, without change.
JPMS introduces two ways to make legacy JARs compatible with module system.

## unnamed module
Unnamed module is something like unnamed package(default package), it's the default module for JAR which does not have module and is loaded from class path.
So the key points are:
* No module-info
* Loaded from class path

unnamed module has the following features:
* Reads every other modules.
* Exports all packages.

## automatic module
Automatic module is a named module that is defined implicitly, since it does not have module declaration.
An ordinary named module is defined explicitly with a module declaration by contrast. 
Automatic module is loaded from module path compared with unnamed module:
* No module-info
* Loaded from class path

The automatic module name is derived from:
* if the JAR defines 'Automatic-Module-Name' header in its manifest, it defines the module's name.
* otherwise the JAR file name is used to determine the name

## runtime image
The runtime image can only be created from explicit named module. Unnamed module and automatic module can't be used to create runtime images.
So the legacy jar must be migrated to named module if you want to create runtime image.
### jdeps
jdeps is a dependency analyzer tool.

    # dependency details
    jdeps commons-lang3-3.12.0.jar
    # dependency summary
    jdeps -s commons-lang3-3.12.0.jar
    # generate module-info.java
    jdeps --generate-module-info . commons-lang3-3.12.0.jar

### moditect-maven-plugin
It's a maven plugin for JPMS. See [here](https://github.com/moditect/moditect).

### jlink
The command to create runtime image:

    jlink.exe -p java10-1.0-SNAPSHOT.jar;commons-lang3-3.12.0.jar --add-modules budwing.java10 --output jre
    ### try run it
    jre/bin/java -m budwing.java10

Multiple module path should be separated with ';'(Windows) or ':'(Linux).

**_Notes: git bash on Windows will return error no matter which one is used._**