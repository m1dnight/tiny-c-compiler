#!/bin/sh
ant clean
ant

OUR_COMPILER="java -jar jar/Compiler.jar"

TEST1="tests/batterie_test"
TEST2="tests/test3"


#$OUR_COMPILER $TEST1.c
$OUR_COMPILER $TEST2.c

#gcc -g $TEST1.s tests/full_test.c -o full
gcc -g $TEST2.s tests/executor.c -o exec

#./full
./exec
