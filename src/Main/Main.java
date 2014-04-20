package Main;

import Assembly.Generator;
import CodeGeneration.CodeContainer;
import Cup.parser;
import SymbolTable.SymbolTable;

import java.io.FileReader;
   
public class Main {
  static public void main(String argv[]) {
    SymbolTable scope = new SymbolTable();
    /* Start the parser */
    try {
      parser p = new parser(new Jflex.Lexer(new FileReader(argv[0])));
      CodeContainer result = (CodeContainer) p.parse().value;
        new Generator().SplitToBlocks(result);

    } catch (Exception e) {
      /* do cleanup here -- possibly rethrow e */
      e.printStackTrace();
    }

  }
}