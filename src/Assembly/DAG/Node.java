package Assembly.DAG;

import CodeGeneration.OpCodes;
import SymbolTable.SymTabInfo;

/**
 * Created by christophe on 6/4/14.
 * Represents a node in the form of:
 *
 */
public class Node {
    private OpCodes    opCode;
    private Node       left;
    private Node       right;
    private SymTabInfo label;

    public Node(OpCodes opCode, Node left, Node right) {
        this.opCode = opCode;
        this.left   = left;
        this.right  = right;
    }



    /******************************************************************************************************************/
    /************************************ STATIC FUNCTIONS ************************************************************/
    /******************************************************************************************************************/
    public static boolean isSame(Node a, Node b)
    {
        if(this.opCode == toCompare.getOpCode() &&

    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public OpCodes getOpCode() {
        return opCode;
    }

    public void setOpCode(OpCodes opCode) {
        this.opCode = opCode;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public SymTabInfo getLabel() {
        return label;
    }

    public void setLabel(SymTabInfo label) {
        this.label = label;
    }
}
