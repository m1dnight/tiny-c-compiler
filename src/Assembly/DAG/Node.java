package Assembly.DAG;

import CodeGeneration.OpCodes;
import SymbolTable.SymTabInfo;

import java.util.ArrayList;

/**
 * Created by christophe on 6/4/14.
 * Represents a node in the form of:
 *
 */
public class Node {
    private OpCodes               opCode;
    private Node                  left;
    private Node                  right;
    private ArrayList<SymTabInfo> labels;


    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public Node(SymTabInfo label)
    {
        this.labels = new ArrayList<SymTabInfo>();
        this.labels.add(label);
    }
    public Node(OpCodes opCode, Node left, Node right) {
        this.opCode = opCode;
        this.left   = left;
        this.right  = right;
    }
    public Node(OpCodes opCode, Node single)
    {
        this.opCode = opCode;
        this.left = single;
    }

    public Node() {
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public void AddSymbol(SymTabInfo label)
    {
        this.labels.add(label);
    }

    public boolean ContainsSymbol(SymTabInfo label)
    {
        return labels.contains(label);
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

    public ArrayList<SymTabInfo> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<SymTabInfo> labels) {
        this.labels = labels;
    }
}
