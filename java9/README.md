# Some new tools/options in Java 9
JDK 9 introduces some new tools/options to support module system.

## java
java command is jvm to launch a Java application. It can:
* launch a class file: 
  * java [options] mainclass [args ...]
* launch the main class in a JAR file:
  * java [options] -jar jarfile [args ...]
* launch the main class in a module:
  * java [options] -m module[/mainclass] [args ...]
  * java [options] --module module[/mainclass] [args ...]


    java -p ./java9-1.0-SNAPSHOT.jar -m budwing.java9

* launch a single source-file program:
  * java [options] source-file [args ...]

## jmod
jmod is used to create JMOD files and list the content of existing JMOD files. 
It is intended for modules that have native libraries or other configuration files, they can be linked with jlink to a runtime image.
It is not intended for runtime, so you can NOT run with a JMOD file, so don't use it in --module-path for java command. 
The following command will return error: JMOD format not supported at execution time

    java -p budwing.java9.jmod -m budwing.java9

Instead, the following command is correct:

    java -p java9-1.0-SNAPSHOT.jar -m budwing.java9

### jmod help
jmod --help
### create jmod file
create jmod for java classes, --class-path can be JAR or a directory which contains all the classes:

    jmod create --class-path java9-1.0-SNAPSHOT.jar budwing.java9.jmod

--cmds points to directory which contains binary files, will be copied to bin/ directory of JMOD file. 
--config points to directory which contains configuration files, will be copied to conf/.

    jmod create --class-path java9-1.0-SNAPSHOT.jar --cmds cmd --config config budwing.java9.jmod
Please create cmd and config directories in target/ first.
### list entries in jmod file
    jmod list budwing.java9.jmod

## describe jmod file
    jmod describe budwing.java9.jmod

## jlink & jimage
jlink is used to assemble and optimize a set of modules and their dependencies into a custom runtime image.
runtime image will be put into lib/modules. jimage is used to view and verify the images which will be used by JVM internally.

### jlink help
    jlink --help
### create runtime
    jlink -p budwing.java9.jmod --add-modules budwing.java9 --output jre
### jimage help
    jimage --help
### jimage info
    jimage info jre/lib/modules
### jimage list
    jimage list jre/lib/modules