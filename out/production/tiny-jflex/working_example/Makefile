JAVA=java
JAVAC=javac
JFLEX=jflex
CUP=$(JAVA) java_cup.Main <

all:
	jflex lcalc.flex
	java -jar "./lib/cup.jar" ycalc.cup
	javac -cp ".:lib/jflex.jar" Main.java
