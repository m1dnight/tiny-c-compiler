JAVA=java
JAVAC=javac
JFLEX=jflex
FILES = SymbolTable.java TypeInfo.java Types.java Main.java



all: $(FILES)
	$(JFLEX) tinyc.jflex
	$(JAVA) -jar "./lib/cup.jar" Parser.cup
	$(JAVAC) -cp ".:./lib/*"  Main.java
clean:
	rm -f *.class Lexer.java parser.java sym.java *~