# arthurpicht/console

High-level java functionality for writing to text console

Features:

* text-effects (e.g. bold, italic ...)
* colorization
* formatting
* 3 levels of output verbosity
* delegation to slf4j logger
* highly programmatically configurable 

## Programmatic configuration

Configuration of Console can be done by calling *Console.init()*.
If not, default values are applied.

Example:

```java
Console.init(new ConsoleConfigurationBuilder()
    .addLoggerDelegation("CONSOLE") 
    .withSuppressedColors()        
    .build());
```
See `ConsoleConfigurationBuilder` for a full list of configuration parameters, documentation and default values.

## Usage

See test case `DemosAsTest` for some demos.

