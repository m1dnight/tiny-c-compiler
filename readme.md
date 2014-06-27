Tiny C Compiler
===============
This implementation aims to build a compiler for the language 'Tiny'.
It is a subset of the C programming language. The project is an assignment 
for the course 'Compilers' at Vrije Universiteit Brussel, given by
Professor Dirk Vermeir.

More details about the language spec can be found here:
--> http://tinf2.vub.ac.be/~dvermeir/courses/compilers/tiny.html

Tools
=====
The tools used are JFlex and CUP. These are alternatives to Flex and Bison
which use Java instead of C.

Compiling
=========
There is a makefile present that will create the lexer and the parser.
Otherwise an IDE is recommended (I used IntelliJ).

Executing
=========
The code can be built into a jar file using an IDE. Otherwise, the program expects 2 parameters. First of all an input file (.c) and an output file. 

Important notice
================
This project has an extremely high WTF/minute value. No seriously. So it is far from what I want it to be. Nor am I very proud of the code quality.
