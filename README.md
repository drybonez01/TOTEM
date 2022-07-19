# TOTEM
**TOTEM** (Module for the De**T**ecti**O**n of **TE**st S**M**ells) is a plug-in for the IntelliJ IDEA development environment, implementing a state-of-the-art detection mechanism to detect instances of three test smell types, with a focus on usability and the implementation of a simple, user-friendly interface.

## Getting Started
TOTEM automatically detects **Test Smells** in the Test Classes of your **Java** projects. At the moment, the tool detects three types of Test Smells: **Eager Test**, **General Fixture** and **Lack of Cohesion**.

To find the Test Classes, TOTEM uses the following convention: if a class name contains the `Test` substring, and there's a class with the same name minus the `Test` substring, then the former will be a Test Class, and the latter will be the Production Class tested by it. So, before you start, make sure that your project follows the same convention!

## Installation
1. Clone this repository to your device;
2. Build the plug-in `jar` file: Open the Gradle menu, then select the `build` task in the `TOTEM\Tasks\build\` directory. The output will be saved in the `TOTEM\build\distributions\`;
3. Extract the archive. Then, in IntelliJ IDEA, go to **File** -> **Settings** -> **Plugins** -> Click on the wheel icon -> **Install Plugin From disk...** and select the `TOTEM-1.0-SNAPSHOT.jar` from the directory where it was extracted;
4. Apply changes;
5. Execute the module `TOTEM_Spring`, by running the class **TotemSpringApplication** (Right click on the class -> Select `Run 'TotemSpringApplication'`). This last step has to be done every time you wish to use the tool.

## How to use
First, make sure that the the **TOTEM_Spring** module is running in background!

Then, to start the detection, click on **Tools** -> **Execute Test Smell Detection** and wait until the analysis is complete. It may take a while, so be patient. After that, a pop-up window showing the results of the analysis will appear.

If you are interested, after the analysis a `*projectname*.json` file will be saved in the `TOTEM_Spring\results\` directory, with more data about the Test Smells detected in the project.
