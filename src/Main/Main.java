package Main;

import Assembly.BasicBlock;
import Assembly.Generator;
import Assembly.x86.BasicBlockToX86Generator;
import CodeGeneration.ThreeAddressCode;
import Cup.parser;
import Expressions.Program;
import Optimisations.LocalValueNumbering;
import Optimisations.RedudantVariablesBasic;
import SymbolTable.SymbolTable;

import java.io.*;
import java.util.ArrayList;

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
            /**********************************************************************************************************/
            /**********************************************************************************************************/
            /**********************************************************************************************************/
            System.out.println("*************************************");
            System.out.println("***Original basic blocks ************");
            System.out.println("*************************************");
            for(BasicBlock b : basicBlocks)
            {
                System.out.println(b.toString());
            }
            /**********************************************************************************************************/
            /**********************************************************************************************************/
            /**********************************************************************************************************/
            System.out.println("*************************************");
            System.out.println("***Remove redundant variables********");
            System.out.println("*************************************");
            for(BasicBlock b : basicBlocks) {
                //hash |= DAGraph.GenerateGraph(b).hashCode();
                b.setTacs(RedudantVariablesBasic.Optimize(b).getTacs());
            }
            for(BasicBlock b : basicBlocks)
            {
                System.out.println(b.toString());
            }
            /**********************************************************************************************************/
            /**********************************************************************************************************/
            /**********************************************************************************************************/
            System.out.println("*************************************");
            System.out.println("***Local Value Numbering ************");
            System.out.println("*************************************");
            System.out.println("Local Value Numbering");
            // Generate a DAG for each basic block.
            int hash = 0;
            for(BasicBlock b : basicBlocks) {
                //hash |= DAGraph.GenerateGraph(b).hashCode();
                b = LocalValueNumbering.Optimize(b);
            }
            for(BasicBlock b : basicBlocks)
            {
                System.out.println(b.toString());
            }
            /**********************************************************************************************************/
            /**********************************************************************************************************/
            /**********************************************************************************************************/
            System.out.println("*************************************");
            System.out.println("***Constant Propagatoin *************");
            System.out.println("*************************************");
            System.out.println("Constant Propagation");
            // Generate a DAG for each basic block.
            hash = 0;
            for(BasicBlock b : basicBlocks) {
                //hash |= DAGraph.GenerateGraph(b).hashCode();
                //b.setTacs(ConstantPropagation.Optimize(b).getTacs());
            }

            for(BasicBlock b : basicBlocks)
            {
                System.out.println(b.toString());
            }
            /**********************************************************************************************************/
            /**********************************************************************************************************/
            /**********************************************************************************************************/
            System.out.println("*************************************");
            System.out.println("***Compilation of basic blocks ******");
            System.out.println("*************************************");
            BasicBlockToX86Generator g = new BasicBlockToX86Generator(basicBlocks);


            System.out.println(g.getProgram());
            WriteToFile(g.getProgram(), argv[1]);
            //Generator.PrintBlocks(basicBlocks);

        } catch (Exception e) {
      /* do cleanup here -- possibly rethrow e */
            e.printStackTrace();
        }

    }

    private static void WriteToFile(String s, String path)
    {		try {


        File file = new File(path);

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(s + "\n");
        bw.close();

        System.out.println("File updated");

    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}