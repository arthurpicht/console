# arthurpicht/console

High-level java functionality for writing to text console. Intended to
support rich CLI applications.

Features:

* text-effects (e.g. bold, italic ...)
* colorization
* respect for NO_COLOR environment variable
* formatting
* 4 levels of output verbosity
* writing console messages to file
* extensible message processing functionality
* delegating console messages to slf4j by extension
* highly programmatically configurable 

## Usage

### Hello World

A simple substitution for `System.out.println`:

    Console.println("Hello world!");

Write a line in red color:

    Console.println("Text in RED.", Format.RED_TEXT);

Write with bright yellow background and bold:

    Console.println("With bright yellow background and bold.", Format.BOLD, Format.BRIGHT_YELLOW_BACK);

The same as before but now centered in a block with a width of 25 characters:

```java
Console.out(new MessageBuilder()
        .addText("With bright yellow background and bold.",
                Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(),
                new BlockFormat.Builder()
                        .withWidth(25)
                        .withAlign(BlockFormat.Align.CENTER)
                        .withExpandedTextEffects()
                        .build())
        .build());
```

### Configuring Console

First configure Console at the entry point of the application. If not done so,
a default configuration will be applied. Then call `Console` methods to
write to console instead of `System.out` or `System.err`.

```java
ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
        .withLevel(Level.VERBOSE)
        .withPlainOutput(false)
        .withSuppressedColors(false)
        .withMutedOutput(false)
        .build();
Console.configure(consoleConfiguration);
```

Available Parameters:

* **level** (Level): Level of console output. Default: NORMAL.
* **suppressedColor** (boolean): No color will be applied.
* **plain** (boolean): No control characters will be applied.
* **mutedOutput** (boolean): Output will be muted.
* **recorder** (boolean): Initialize and start output recorder.
* **addMessageChannel** (MessageChannel): A `MessageChannel` implementation will be added.
* **standardOutput** (PrintStream): PrintStream to be written to. Default: System.out.
* **standardError** (PrintStream): PrintStream to be written to for error message: System.err.

### Console methods

The `Console` class consists of three types of methods:

#### print methods

`Console` methods beginning with `print` accept plain string objects and optionally one
or more `Format` declarations. There are such print methods for all levels of verbosity
and also a generic method that accepts the verbosity level as a parameter.
Based on `System.out.print` and `System.out.println` respectively `System.error` there
are corresponding `print` and `println` methods, e.g. `printlnVerbose`.  

#### out method

The `Console.out` method accepts an object of type `Message` an allows for printing out
fine-grained message definitions. A message consists of one or more text chunks, each with
it own format definition.

Example:

```java
Console.out(new MessageBuilder()
        .addText("First text.", Format.RED_TEXT())
        .addText("This text in red.",
                Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(),
                new BlockFormat.Builder()
                        .withWidth(25)
                        .withAlign(BlockFormat.Align.CENTER)
                        .withExpandedTextEffects()
                        .build())
        .addText("normal again")
        .build());
```

### printStackTrace

Simply prints stackTrace. Example:

     Console.printStackTrace(e);

### Format definitions

There are four types of format definitions:

1. text effects: e.g. bold, italic
2. text colors
3. text background colors
4. text block definitions with alignments

Text colors can be specified by predefined constants or by color number or respectively by
rgb-codes.

See class `de.arthurpicht.console.message.format.Format` for more infos.

### Verbosity levels

There are four levels of verbosity: `REGULAR`, `VERBOSE`, `VERY_VERBOSE`, `VERY_VERY_VERBOSE`.
Similar to the use in logger applications, a console message on level `VERBOSE` will only be
shown, if `Console` is configured for at least `VERBOSE` level.

The output to the error stream occurs regardless of any configuration of verbosity level. 

See `ConsoleConfigurationBuilder` for a full list of configuration parameters, documentation and default values.

### MessageBuilder

For the output of some rather simple messages, using `Console.print...` methods may be sufficient.
However, in order to fully utilize the possible functionality, the usage of the `MessageBuilder` is required.

Example:

```java
new MessageBuilder()
    .addText("|", Format.BOLD())
    .addText("1234567890abcdefghijklmnopqrstuvwxyz",
        Format.RED_TEXT(), Format.BRIGHT_YELLOW_BACK(),
        new BlockFormat.Builder()
            .withWidth(10)
            .withAbbreviationSign("...")
            .build())
    .addText("|", Format.BOLD())
    .build()
```

`MessageBuilder` has the following methods:

* ***asNormal()***, ***asVerbose()***, ***asVeryVerbose()***, ***asVeryVeryVerbose()***, ***asLevel(Level level)***: specify verbosity level, default: NORMAL
* ***toStandardOut()***, ***toErrorStream()***: destination stream, default: standard out
* ***addText(String text, Format format)***
* ***clearLine()***: previously written text on current line will be overwritten
* ***withNoLineFeed()***: no line feed will be executed after message, default: perform line feed
* ***withIndentation(int inden)***: number of spaces to be used as leading indentation, default: 0.

### BlockFormat

A `BlockFormat` is a subtype of `Format` which allows for building text blocks with a fixed sized width.
The text can be aligned within the block. Furthermore, text with excess length can be shortened and
a trailing abbreviation sign can optionally be inserted.

Methods of `BlockFormat.Builder`:

* ***withWidth(int i)***: width of text block, mandatory
* ***withAlign(Align align)***: align for text positioning in block, default: Align.LEFT
* ***withAbbreviationSign(String string)***: specify string that will be inserted at the end of the shortened string,
in case string exceeds width.
* ***withExpandedTextEffects()***: lets text effects (especially background colors) be applied to the full width
including leading and trailing spaces
* ***withOverflowStrategy(OverflowStrategy overflowStrategy)***: specifies strategy how to deal with exceeding text.
default: LIMIT

### FileChannel

Console can be configured to write messages to a file. This can be achieved by configuring a
FileChannel. Use the `FileChannelBuilder` to build `FileChannel` object and add it to
`ConsoleConfiguration` like this:

```java
FileChannel fileChannel = new FileChannelBuilder()
        .withFile(Paths.get("myFile.log"))
        .build();
ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
        .addMessageChannel(fileChannel)
        .build();

Console.println("This message will apear on console and will also be written to file.");
```

`FileChannel` has the following parameters:

* **file** (Path): Path to file. Mandatory.
* **muted** (boolean): mute output. Default: false.
* **level** (Level): verbosity level in which should be outputted. Default: Level is inherited
from Console configuration.
* **writeTimestamp** (boolean): add Timestamp to each line
* **writeLevel** (boolean): add level name to each line

See `de.arthurpicht.console.messageChannel.fileChannel.FileChannelBuilder` for more infos.

### NO_COLOR environment variable

If the [NO_COLOR](https://no-color.org/) environment variable is set to whatever value, no colors will
be presented on console output. This behaviour can be overridden by using `ConsoleBuilder`-methods 
`withIgnoredNoColorEnvVar()` or `withIgnoredNoColorEnvVar(boolean ...)`.

### Output Recorder

An output recorder can be configured, that will save all output in memory for a later processing. The rendering of
messages is connected to the console configuration in the following way:

* configuration *plain* will also affect recorded messages
* configuration *level* will also affect recorded messages
* recorded messages are free of colors and ANSI-based formatting
* messages to a muted console will also be omitted on recording
* the recorder has an additional mute flag: the recorder can be turned off independently of console

Caution: As messages are stored in memory, recording output in long-running processes can be dangerous as this can 
effectively appear as a memory-leak.

The output recorder is initialized on configuration time:

```java
ConsoleConfiguration consoleConfiguration = new ConsoleConfigurationBuilder()
        .withRecorder()
        .build();
Console.configure(consoleConfiguration);
```

After initialization the recorder starts recording every printed message and can be accessed by the `ConsoleRecorder` class. `ConsoleRecorder` provides the
following functionalities:

* **mute** and **unmute**

    Turns recorder off and on:

    ```java
    Console.println("Hello world!");
    ConsoleRecorder.mute();
    Console.println("Message to be muted ...");
    ConsoleRecorder.unmute();
    Console.println("A simple recorder test.");
    ```
* **getConsoleOutput**

    Returns all recorded messages:

      List<String> message = ConsoleRecorder.getConsoleOutput(); 

* **clear**

    Delete all recorded messages.

### Extensions

The processing of console messages can be realized by implementing the interface
`de.arthurpicht.console.messageChannel.MessageChannel`. See project 
[arthurpicht/console-to-slf4j](https://github.com/arthurpicht/console-to-slf4j)
as an example.

## Demos

See test case `DemosAsTest` for some demos.

