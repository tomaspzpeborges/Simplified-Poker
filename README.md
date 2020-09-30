# COMP1721 Coursework 2

Everything you need to do in this assignment, aside from editing code, is
handled by the Gradle build system.  You can run Gradle from the command
line or use it from within the IntelliJ IDE if you prefer.  If using
IntelliJ, please use a recent version of the IDE on your own PC and run
the *Import Project* wizard, specifying the project type as 'Gradle'.

**You do not need to install Gradle yourself**, as we've included a
'Gradle wrapper' that will download a compatible version of the tool, along
with various library dependencies, if required.  **This will be very slow
the first time that it runs!**

On Linux and macOS, run the wrapper with `./gradlew`.  On Windows, it should
be possible to run it with `gradlew`.  The commands below assume the use
of Linux or macOS. **Please remember that we are unable to support the use
of Windows for the practical work in this module**, and certain aspects
of the workflow - such as preparing files for submission - will definitely
*not* work on Windows.

The different coursework artifacts are organised into separate projects.
You can list these projects with `./gradlew projects`.  When you start on
the assignment, there should be two projects listed: `core` and `stats`.

Note that the project name needs to be included when running tasks within
a specific project.

## Basic & Full Solutions

### Class Testing

You must implement classes `Deck` and `PokerHand` in the files `Deck.java`
and `PokerHand.java` that have already been provided for you in
`core/src/main/java/comp1721/cwk2`.  As with Coursework 1, you need to start
with stub implementations of these classes in order to compile and run
the tests.

To run all the unit tests, recompiling code where necessary, do

    ./gradlew :core:test

To run only the tests for the basic solution, do

    ./gradlew :core:test --tests Basic

Substitute `Full` for `Basic` in the above command to run only the tests
for the full solution.

Tests and their status (PASSED or FAILED) are listed in the terminal.
A **test report** is also generated in HTML, containing detailed information
about the tests and any failures that have occurred.  You can access this
by pointing your browser at `core/build/reports/tests/test/index.html`.
We suggest you keep this page open in a browser tab, refreshing it
as necessary, so that you always have full information available on test
results.

Note that Gradle won't normally rerun tests if they have all passed, unless
you've changed something in your code.  You can force it to recompile
everything and rerun the tests using

    ./gradlew :core:test --rerun-tasks

### Style Checking

To check coding style for the classes in the `core` project, do

    ./gradlew :core:style

If there are any style violations, you'll see a count reported in the
terminal window.  A full report on the violations can be viewed by pointing
your browser at `core/build/reports/checkstyle/main.html`.  **Treat this
report as a rough guide only**.  Warnings will not necessarily lead to the
deduction of style marks.  Also, note that style checking is not exhaustive
and we may still penalise you on style grounds even if no violations are
reported here.

As with unit tests, Gradle normally won't rerun the style check unless you
change something in your code.

You can check coding style *and* run tests in one go with

    ./gradlew :core:check

### Generating Documentation

To generate HTML documentation from doc comments in the core classes, do

    ./gradlew :core:javadoc

Then view the documentation by pointing a web browser at
`core/build/docs/javadoc/index.html`.

### The `PokerStats` Application

You must implement this as a class `PokerStats`, containing a `main` method,
Put your implementation in the file `PokerStats.java` provided for you
in `stats/src/main/java/comp1721/cwk2`.

You can check coding style for this application with

    ./gradlew :stats:style

For the basic solution, you can run the application like this:

    ./gradlew :stats:run

For the full solution, the application will require 1 or 2 command line
arguments, which can be supplied like this:

    ./gradlew :stats:run --args stats.log
    ./gradlew :stats:run --args "stats.log 2500"

The first argument is the name of a log file and the optional second argument
is the number of trials to be performed.  When the latter has has't been
supplied by the user, your program should use a default of 1,000.  Note that
the quotes are necessary when supplying two command line arguments via
Gradle.

You can create a redistributable version of the application with

    ./gradlew :stats:distZip

This creates a Zip archive `stats.zip`, in `stats/build/distributions`.
This Zip archive contains the compiled code for the core class library and
application, bundled into a pair of JAR files, plus a shell script and batch
file that can be used to run the program on Linux, macOS or Windows.

## Advanced Task

As in Coursework 1, the advanced task involves the use of JavaFX.  It should
be possible to do this on SoC Linux machines or on your own PC.
[Please follow the instructions in the `README-jfx` file](README-jfx.html)
to configure JavaFX in the environment that you are using for the work.

The `advanced` project contains the class `FancyCard`, which you'll need
to use when building your solution.  The `display` and `animate` projects
contain examples of displaying and animating playing cards using JavaFX.
You can use these as inspiration for your solution.  Run them like so:

    ./gradlew :display:run
    ./gradlew :animate:run

The only place where you should add any code for the advanced solution is
in the `game` project - specifically the `game/src/main/java/comp1721/cwk2`
directory.  You can put whatever you like in here but make sure that the
application is launched from the class `PokerGame` (since this is what
the Gradle build file expects).

You should be able to run the application with

    ./gradlew :game:run

## Cleaning Up

To remove all files generated by the build process across all projects, use

    ./gradlew clean

You can also clean individual projects - e.g., using

    ./gradle :stats:clean
