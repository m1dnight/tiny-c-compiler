package Main;

import Assembly.BasicBlock;
import Optimisations.DAGraph;
import Assembly.Generator;
import CodeGeneration.ThreeAddressCode;
import Cup.parser;
import Expressions.Program;
import Optimisations.LocalValueNumbering;
import Optimisations.RedudantVariablesBasic;
import SymbolTable.SymbolTable;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
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
            //System.out.println(result.getSymbolTable());

            // Print out all the TACs.
            System.out.println("*************************************");
            for(ThreeAddressCode tac : result.toThreeAddressCode())
            {

                System.out.println(tac.toString());
            }
            System.out.println("*************************************");
            // Generate basic blocks.
            ArrayList<BasicBlock> basicBlocks =  Generator.SplitToBlocks(result.toThreeAddressCode());

            // Determine liveness for each variable in the basic block.
            //for(BasicBlock b : basicBlocks)
            //    DetermineLiveness(result.getSymbolTable(), b);

            // Print the symbol table again.
            //System.out.println(result.getSymbolTable());
            for(BasicBlock b : basicBlocks) {
                //hash |= DAGraph.GenerateGraph(b).hashCode();
                b.setTacs(RedudantVariablesBasic.Optimize(b).getTacs());
            }
            System.out.println("*************************************");
            for(BasicBlock b : basicBlocks)
            {
                System.out.println(b.toString());
            }
            System.out.println("*************************************");
            // Generate a DAG for each basic block.
            int hash = 0;
            for(BasicBlock b : basicBlocks) {
                //hash |= DAGraph.GenerateGraph(b).hashCode();
                b = LocalValueNumbering.Optimize(b);
            }
            System.out.println("*************************************");
            for(BasicBlock b : basicBlocks)
            {
                System.out.println(b.toString());
            }
            System.out.println("*************************************");
            //Generator.PrintBlocks(basicBlocks);

        } catch (Exception e) {
      /* do cleanup here -- possibly rethrow e */
            e.printStackTrace();
        }

    }
}