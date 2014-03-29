Tiny C Compiler
===============
This implementation aims to build a compiler for the language 'Tiny'.
It is a subset of the C programming language. The project is an assignment 
for the course 'Compilers' at Vrije Universiteit Brussel, given by
Professor Dirk Vermeir.

Tools
=====
The tools used are JFlex and CUP. These are alternatives to Flex and Bison
which use Java instead of C.

To compile:
>jflex lcalc.flex
>java -jar "./lib/cup.jar" ycalc.cup
>javac -cp ".:lib/jflex.jar" Main.java

To run:
>java -cp ".:lib/jflex.jar" Main test.txt

