#!/bin/bash

javac -d . Main.java Token.java Node.java FunctionNode.java LineNode.java &&

java javaCompiler.Main main.c &&

as --64 -o a.o a.s &&

ld -o a a.o &&

./a

echo $?

