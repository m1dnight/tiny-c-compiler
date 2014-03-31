JAVA=java
JAVAC=javac
JFLEX=jflex
FILES = Jflex/tinyc.jflex Cup/Parser.cup



all: $(FILES)
	$(JFLEX) Jflex/tinyc.jflex
	cd Cup/ ; $(JAVA) -jar cup.jar -package Cup Parser.cup
clean:
	rm -Rf *.class Jflex/Lexer.java Cup/parser.java Cup/sym.java