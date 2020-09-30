# Using JavaFX for the Advanced Task

There are two steps required to configure your environment for the advanced
task.  Step 1 is to edit `settings.gradle` and uncomment the last four lines
of the file - the ones referencing the `advanced`, `display`, `animate`
and `game` projects.

Step 2 depends upon where you'll be working.  We assume three possibilities:
JDK 8 on SoC Linux machines, JDK 11 on your own PC and JDK 13 on your own
PC.  The instructions differ slightly for each environment - see the
appropriate section below for further details.

## JDK 8 on School of Computing Linux Machines

In a terminal window, move into each of the directories `advanced`, `display`,
`animate` and `game`.  In each of these directories, enter the following
command:

    cp buildfiles/jdk8.gradle build.gradle

Then move up to the `cwk2` directory and enter the following:

    cp jdk8-gradle.properties gradle.properties

## JDK 11 on your own PC

In a terminal window, move into each of the directories `advanced`, `display`,
`animate` and `game`.  In each of these directories, enter the following
command:

    cp buildfiles/jdk11.gradle build.gradle

## JDK 13 on your own PC

In a terminal window, move into each of the directories `advanced`, `display`,
`animate` and `game`.  In each of these directories, enter the following
command:

    cp buildfiles/jdk13.gradle build.gradle
