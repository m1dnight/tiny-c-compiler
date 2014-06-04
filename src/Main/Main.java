package Main;

import Assembly.BasicBlock;
import Assembly.Generator;
import CodeGeneration.CodeContainer;
import Cup.parser;
import SymbolTable.SymbolTable;

import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    static public void main(String argv[]) {
        SymbolTable scope = new SymbolTable();
    /* Start the parser */
        try {
            parser p = new parser(new Jflex.Lexer(new FileReader(argv[0])));
            CodeContainer result = (CodeContainer) p.parse().value;
            ArrayList<BasicBlock> basicBlocks =  Generator.SplitToBlocks(result);
            Generator.PrintBlocks(basicBlocks);

        } catch (Exception e) {
      /* do cleanup here -- possibly rethrow e */
            e.printStackTrace();
        }

    }
}