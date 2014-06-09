package Optimisations;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;

import java.util.*;

/**
 * Created by christophe on 6/4/14.
 */
public class DAGraph {

    public static ArrayList<Node> GenerateGraph(BasicBlock basicBlock) {
        ArrayList<Node> nodes                   = new ArrayList<Node>();
        LinkedHashMap<SymTabInfo, Node> mapping = new LinkedHashMap<SymTabInfo, Node>();

        // For each instruction in the basicblock
        for (ThreeAddressCode tac : basicBlock.getTacs()) {
            if (tac.getOpCode() == OpCodes.A2PLUS    || tac.getOpCode() == OpCodes.A2TIMES || tac.getOpCode() == OpCodes.A2MINUS ||
                    tac.getOpCode() == OpCodes.A2DIV || tac.getOpCode() == OpCodes.A2NEQ   || tac.getOpCode() == OpCodes.A2EQ    ||
                    tac.getOpCode() == OpCodes.A2GT  || tac.getOpCode() == OpCodes.A2LT) {
                // If the the first variable in the TAC (e.g., A = B + C, we mean B) is not yet in the DAG, we add it.
                Node n1 = getNode(mapping, tac.getArg1());
                if (n1 == null)
                {
                    n1 = new LeafNode(tac.getArg1());
                    nodes.add(n1);
                    mapping.put(tac.getArg1(), n1);
                }

                Node n2 = getNode(mapping, tac.getArg2());
                if (n2 == null)
                {
                    n2 = new LeafNode(tac.getArg2());
                    nodes.add(n2);
                    mapping.put(tac.getArg2(), n2);
                }

                // Find the node that has these two nodes as children
                Node opNode = FindNode(nodes, tac.getOpCode(), n1, n2);
                if (opNode == null) {
                    opNode = new Node(tac.getOpCode(), n1, n2);
                    nodes.add(opNode);
                }
                // Remove x from the list of labels in the current node.
                UpdateLabels(mapping, tac.getResult());
                opNode.AddSymbol(tac.getResult());
                mapping.put(tac.getResult(), opNode);
            }
            /**
             * This part is for dealing with assignments
             * A = B
             */
            if (tac.getOpCode() == OpCodes.A0) {
                Node n1 = getNode(mapping, tac.getArg1());
                if (n1 == null) {
                    n1 = new LeafNode(tac.getArg1());
                    nodes.add(n1);
                    mapping.put(tac.getArg1(), n1);
                }
                UpdateLabels(mapping, tac.getResult());
                n1.AddSymbol(tac.getResult());
                mapping.put(tac.getResult(), n1);
            }
        }
        for(Node n : nodes)
        {
            if(n instanceof LeafNode)
                System.out.println("Leaf: " + n.getLabels());
            else
                System.out.println("Node: " + n.getOpCode() + " - " + n.getLabels());
        }
        return nodes;
    }

    private static void UpdateLabels(LinkedHashMap<SymTabInfo, Node> nodes, SymTabInfo result) {
        Node n = getNode(nodes, result);
        if(n != null)
            n.RemoveSymbol(result);
    }

    private static Node FindNode(ArrayList<Node> nodes, OpCodes opCode, Node n1, Node n2) {
        for (Node n : nodes) {
            if (n.getOpCode() == opCode &&
                    n1 == n.getLeft()   &&
                    n2 == n.getRight())
                return n;
        }
        return null;
    }

    private static Node getNode(LinkedHashMap<SymTabInfo, Node> nodes, SymTabInfo label) {
        // Iterate over all the nodes. If we have a node that corresponds to this label,
        // return the node.
        for (Map.Entry<SymTabInfo, Node> entry : nodes.entrySet()) {
            if (entry.getKey().equals(label))
                return entry.getValue();
        }
        return null;
    }
}
