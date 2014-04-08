package Main;

import Cup.parser;

import java.io.FileReader;
   
public class Main {
  static public void main(String argv[]) {
    SymbolTable scope = new SymbolTable();
    /* Start the parser */
    try {
      parser p = new parser(new Jflex.Lexer(new FileReader(argv[0])));
      Object result = p.parse().value;      
    } catch (Exception e) {
      /* do cleanup here -- possibly rethrow e */
      e.printStackTrace();
    }

  }
}