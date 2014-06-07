package Optimisations;

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

    public String toString()
    {
        //return String.format(opCode.name() + " | " + (left == null ? "" : left.getLabels()) + " - " + (right == null ? "" : right.getLabels()) + " | " + this.getLabels());
        StringBuilder sb = new StringBuilder();
        if (this.right == null && this.left == null) {
            sb.append("Node:  " + "Operation: " + this.opCode + "Labels: " + this.getLabels() + "\n  No children");
            return sb.toString();
        }
        if (this.right == null)
            if (this.left.getClass() == LeafNode.class) // We have a left leaf
            {
                sb.append("Node:  " + "Operation: " + this.opCode + "Labels: " + this.getLabels() + "\n  *Null" + "\n  *Left: " + this.left);
                return sb.toString();
            } else {
                sb.append("Node:  " + "Operation: " + this.opCode + "Labels: " + this.getLabels() + "\n  *Null" + "\n  *Left: " + this.left.getOpCode());
                return sb.toString();
            }

        if (this.left == null)
            if (this.left.getClass() == LeafNode.class) // We have a left leaf
            {
                sb.append("Node:  " + "Operation: " + this.opCode + "Labels: " + this.getLabels() + "\n  *Right: " + this.right + "\n  *Null");
                return sb.toString();
            } else {
                sb.append("Node:  " + "Operation: " + this.opCode + "Labels: " + this.getLabels() + "\n  *Right: " + this.right.getOpCode() + "\n  *Null");
                return sb.toString();
            }

        if (this.right.getClass() == Node.class && this.left.getClass() == Node.class) {
            sb.append("Node:  " + "Operation: " + this.opCode + "Labels: " + this.getLabels() + "\n  *Left: " + this.left.getOpCode() + "\n  *Right:" + this.right.getOpCode());
            return sb.toString();
        }
        if (this.right.getClass() == LeafNode.class && this.left.getClass() == LeafNode.class) {
            sb.append("Node:  " + "Operation: " + this.opCode + "Labels: " + this.getLabels() + "\n  *Left: " + this.left + "\n  *Right:" + this.right);
            return sb.toString();
        }
        if (this.left.getClass() == LeafNode.class) // Right is an internal node
        {
            sb.append("Node:  " + "Operation: " + this.opCode + "Labels: " + this.getLabels() + "\n  *Left: " + this.left + "\n  *Right:" + this.right.getOpCode());
            return sb.toString();
        }
        if (this.right.getClass() == LeafNode.class) // Left is an internal node
        {
            sb.append("Node:  " + "Operation: " + this.opCode + "Labels: " + this.getLabels() + "\n  *Left: " + this.left.getOpCode() + "\n  *Right:" + this.right);
            return sb.toString();
        }
        return sb.toString();
    }

    public void AddSymbol(SymTabInfo label)
    {
        if(this.labels == null) this.labels = new ArrayList<SymTabInfo>();
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
        if(this.labels == null) return new ArrayList<SymTabInfo>(0);
        return labels;
    }

    public void setLabels(ArrayList<SymTabInfo> labels) {
        this.labels = labels;
    }
}
