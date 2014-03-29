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
There is a makefile present that will compile everything nicely.

Executing
=========
>java -cp ".:lib/jflex.jar" Main test.txt

However, there is a simple .sh file that contains just this which can
be called in following way:
./run <inputfile.c>

