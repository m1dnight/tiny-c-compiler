# This makefile is intended to create all the classes
# that are required for the project.
# This file should thus be run before compiling any
# Java stuff.

JAVA=java
JAVAC=javac
CUP=cup
JFLEX=jflex
FILES = Jflex/tinyc.jflex Cup/Parser.cup



all:
	# Generate the Jflex files
	cd src/Jflex/ ; $(JFLEX) tinyc.jflex
	# Generate the Cup files
	cd src/Cup/ ; $(CUP) Parser.cup
clean:
	# Remove all output files created by IntelliJ
	rm -Rf out/
	# Remove the generated files by Cup
	cd src/Cup/ ; rm -f parser.java sym.java
	# Remove the generated files by Jflex
	cd src/Jflex/ ; rm -f Lexer.java