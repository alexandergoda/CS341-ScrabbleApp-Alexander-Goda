CS341 Assignment 2 - Application 1: Scrabble App
Alexander Goda

PROJECT CONTENTS
src/scrabble/ScrabbleApp.java
src/scrabble/ScrabbleGenerator.java
src/tester/ScrabbleGeneratorTester.java

ECLIPSE IMPORT
File -> Import -> General -> Existing Projects into Workspace
Choose "Select archive file" and select the project ZIP.

WINDOWBUILDER
Right-click ScrabbleApp.java -> Open With -> WindowBuilder Editor.
Use the Source and Design tabs to move between Java code and the GUI designer.

RUN THE APP
Right-click ScrabbleApp.java -> Run As -> Java Application.

RUN THE TEST CODE
Right-click ScrabbleGeneratorTester.java -> Run As -> Java Application.
All seven tests should report PASS.

GENERATE JAVADOC IN ECLIPSE
Project -> Generate Javadoc...
Select the scrabble package (and tester too if desired), choose an output folder,
and finish the wizard.

DEBUG PRACTICE REQUIRED BY ASSIGNMENT
Use Eclipse's Debug perspective to practice:
- Suspend, Resume, Terminate
- Breakpoints
- Step Into, Step Over, Step Return
- Viewing variable values
- Evaluating expressions

SUGGESTED BREAKPOINTS
ScrabbleApp.buildOutput()
ScrabbleGenerator.validateInput()
ScrabbleGenerator.buildArrangements()
