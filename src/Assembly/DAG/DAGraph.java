package Assembly.DAG;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;

/**
 * Created by christophe on 6/4/14.
 */
public class DAGraph {

    private final BasicBlock basicBlock;

    public DAGraph(BasicBlock input)
    {
        this.basicBlock = input;
    }

    private void GenerateGraph()
    {
        // For each instruction in the basicblock
        for(ThreeAddressCode tac : basicBlock.getTacs())
        {
            if(tac.getOpCode() == OpCodes.A2PLUS  ||
               tac.getOpCode() == OpCodes.A2TIMES ||
               tac.getOpCode() == OpCodes.A2MINUS ||
               tac.getOpCode() == OpCodes.A2DIV   ||
               tac.getOpCode() == OpCodes.A2NEQ   ||
               tac.getOpCode() == OpCodes.A2EQ    ||
               tac.getOpCode() == OpCodes.A2GT    ||
               tac.getOpCode() == OpCodes.A2LT      )
            {
                // If the the first variable in the TAC (e.g., A = B + C, we mean B) is not yet in the DAG
                // we add it.
                Node n1 = getNode(tac.getArg1());
                if(n1 == null)
                 n1 = new Node(tac.getArg1());

                Node n2 = getNode(tac.getArg2());
                if(n2 == null)
                    n2 = new LeafNode(tac.getArg1());

                // Find the node that has these two nodes as children
                Node opNode = FindNode(tac.getOpCode());
                if(opNode == null)
                    opNode = new Node(tac.getOpCode(), tac.getArg1(), tac.getArg2());

                opNode.setSymbol(tac.getResult());
            }
        }
    }

    private Node FindNode(OpCodes opCode) {
        return null;
    }

    private Node getNode(SymTabInfo arg1) {
        return null;
    }
}
