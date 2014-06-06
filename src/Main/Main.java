package Main;

import Assembly.BasicBlock;
import Assembly.Generator;
import CodeGeneration.CodeContainer;
import CodeGeneration.ThreeAddressCode;
import Cup.parser;
import Expressions.Program;
import SymbolTable.SymbolTable;

import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    static public void main(String argv[]) {
        SymbolTable scope = new SymbolTable();
    /* Start the parser */
        try {
            parser p = new parser(new Jflex.Lexer(new FileReader(argv[0])));
            Program result = (Program) p.parse().value;
            for(ThreeAddressCode tac : result.toThreeAddressCode())
            {
                if(tac != null)
                System.out.println(tac.toString());
            }
            int x = 5;
/*            ArrayList<BasicBlock> basicBlocks =  Generator.SplitToBlocks(result);
            Generator.PrintBlocks(basicBlocks);*/

        } catch (Exception e) {
      /* do cleanup here -- possibly rethrow e */
            e.printStackTrace();
        }

    }
}