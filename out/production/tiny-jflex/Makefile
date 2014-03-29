JAVA=java
JAVAC=javac
JFLEX=jflex
CUP=$(JAVA) java_cup.Main <

all:
	jflex tinyc.jflex
	java -jar "./lib/cup.jar" Parser.cup
	javac -cp "./lib/*" sym.java parser.java Lexer.java Main.java
clean:
	rm -f *.class Lexer.java parser.java sym.java *~