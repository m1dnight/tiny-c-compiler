JAVA=java
JAVAC=javac
JFLEX=jflex
CUP=$(JAVA) java_cup.Main <

all:
	jflex tinyc.jflex
	java -jar "./lib/cup.jar" Parser.cup