# release history

## 0.1.6 from 16.07.2023

* Console.printStackTrace() implementiert

## 0.2.0 / 0.2.1 from 06.05.2024

* Delegation to logger removed into own project
* Class console extended
* removed deprecated methods from class Console:
    * init(): use configure() instead
    * initWithDefaults(): user configureWithDefaults instead
* ConfigurationBuilder: asLevel() -> withLevel()
* BlockFormat.builder(int width) - > new BlockFormatBuilder().withWidth(int width)
* MessageChannel introduced
