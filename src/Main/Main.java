package Main;

import Assembly.BasicBlock;
import Assembly.DAG.DAGraph;
import Assembly.Generator;
import CodeGeneration.CodeContainer;
import CodeGeneration.ThreeAddressCode;
import Cup.parser;
import Expressions.Program;
import SymbolTable.SymbolTable;

import java.io.FileReader;
import java.util.ArrayList;

import static Assembly.BasicBlock.DetermineLiveness;

public class Main {
    static public void main(String argv[]) {
        SymbolTable scope = new SymbolTable();
    /* Start the parser */
        try {
            parser p = new parser(new Jflex.Lexer(new FileReader(argv[0])));
            // Parse and lex.
            Program result = (Program) p.parse().value;
            // Print out the symbol table.
            System.out.println(result.getSymbolTable());

            // Print out all the TACs.
            for(ThreeAddressCode tac : result.toThreeAddressCode())
            {
                System.out.println(tac.toString());
            }

            // Generate basic blocks.
            ArrayList<BasicBlock> basicBlocks =  Generator.SplitToBlocks(result.toThreeAddressCode());

            // Determine liveness for each variable in the basic block.
            for(BasicBlock b : basicBlocks)
                DetermineLiveness(result.getSymbolTable(), b);

            // Print the symbol table again.
            //System.out.println(result.getSymbolTable());

            // Generate a DAG for each basic block.
            int hash = 0;
            for(BasicBlock b : basicBlocks)
                hash |= DAGraph.GenerateGraph(b).hashCode();
            Generator.PrintBlocks(basicBlocks);

        } catch (Exception e) {
      /* do cleanup here -- possibly rethrow e */
            e.printStackTrace();
        }

    }
}