package Assembly.DAG;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;

import java.util.ArrayList;

/**
 * Created by christophe on 6/4/14.
 */
public class DAGraph {

    public static ArrayList<Node> GenerateGraph(BasicBlock basicBlock) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        // For each instruction in the basicblock
        for (ThreeAddressCode tac : basicBlock.getTacs()) {
            /**
             * This part is for when we are dealing with TAC's in the form of:
             * A = B op C
             */
            if (tac.getOpCode() == OpCodes.A2PLUS      ||
                    tac.getOpCode() == OpCodes.A2TIMES ||
                    tac.getOpCode() == OpCodes.A2MINUS ||
                    tac.getOpCode() == OpCodes.A2DIV   ||
                    tac.getOpCode() == OpCodes.A2NEQ   ||
                    tac.getOpCode() == OpCodes.A2EQ    ||
                    tac.getOpCode() == OpCodes.A2GT    ||
                    tac.getOpCode() == OpCodes.A2LT     ){
                // If the the first variable in the TAC (e.g., A = B + C, we mean B) is not yet in the DAG, we add it.
                Node n1 = getNode(nodes, tac.getArg1());
                if (n1 == null) {
                    n1 = new LeafNode(tac.getArg1());
                    nodes.add(n1);
                }

                Node n2 = getNode(nodes, tac.getArg2());
                if (n2 == null) {
                    n2 = new LeafNode(tac.getArg2());
                    nodes.add(n2);
                }

                // Find the node that has these two nodes as children
                Node opNode = FindNode(nodes, tac.getOpCode(), n1, n2);
                if (opNode == null) {
                    opNode = new Node(tac.getOpCode(), n1, n2);
                    nodes.add(opNode);
                }
                opNode.AddSymbol(tac.getResult());
            }
            /**
             * This part is for when we are dealing with TAC's in the form of:
             * A = op B
             */
            if (tac.getOpCode() == OpCodes.A1MINUS ||
                    tac.getOpCode() == OpCodes.A1FTOI ||
                    tac.getOpCode() == OpCodes.A1ITOF) {
                // Find the node that belongs to arg1.
                Node n1 = getNode(nodes, tac.getArg1());
                if (n1 == null) {
                    n1 = new LeafNode(tac.getArg1());
                    nodes.add(n1);
                }
                Node opNode = FindNode(nodes, tac.getOpCode(), n1, null);
                if (opNode == null) {
                    opNode = new Node(tac.getOpCode(), n1, null);
                    nodes.add(opNode);
                }
                opNode.AddSymbol(tac.getResult());
            }
            /**
             * This part is for dealing with assignments
             * A = B
             */
            if (tac.getOpCode() == OpCodes.A0) {
                Node n1 = getNode(nodes, tac.getArg1());
                if (n1 == null) {
                    n1 = new LeafNode(tac.getArg1());
                    nodes.add(n1);
                }
                n1.AddSymbol(tac.getResult());
            }
            /**
             * This part is for dealing with jumps.
             * IF B relop C GOTO L
             */
            if (tac.getOpCode() == OpCodes.A2EQIF  ||
                tac.getOpCode() == OpCodes.A2GTIF  ||
                tac.getOpCode() == OpCodes.A2LTIF  ||
                tac.getOpCode() == OpCodes.A2NEQIF   ) {

                Node n1 = getNode(nodes, tac.getArg1());
                if (n1 == null) {
                    n1 = new LeafNode(tac.getArg1());
                    nodes.add(n1);
                }

                Node n2 = getNode(nodes, tac.getArg2());
                if (n2 == null) {
                    n2 = new LeafNode(tac.getArg2());
                    nodes.add(n2);
                }

                Node opNode = FindNode(nodes, tac.getOpCode(), n1, n2);
                if (opNode == null) {
                    opNode = new Node(tac.getOpCode(), n1, n2);
                    nodes.add(opNode);
                }
                opNode.AddSymbol(tac.getResult());
            }
        }
        return nodes;
    }

    private static Node FindNode(ArrayList<Node> nodes, OpCodes opCode, Node n1, Node n2) {
        for (Node n : nodes) {
            if (n.getOpCode() == opCode &&
                    n1 == n.getLeft() &&
                    n2 == n.getRight())
                return n;
        }
        return null;
    }

    private static Node getNode(ArrayList<Node> nodes, SymTabInfo label) {
        for (Node n : nodes)
            if (n.ContainsSymbol(label))
                return n;
        return null;
    }
}
