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
    private SymTabInfo rightHandArg1;
    private SymTabInfo rightHandArg2;
    private SymTabInfo leftHandResult;

    public Node(OpCodes opCode, Node n1, Node n2) {

    }

    /******************************************************************************************************************/
    /************************************ STATIC FUNCTIONS ************************************************************/
    /******************************************************************************************************************/

    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public OpCodes getOpCode() {
        return opCode;
    }

    public void setOpCode(OpCodes opCode) {
        this.opCode = opCode;
    }

    public SymTabInfo getRightHandArg1() {
        return rightHandArg1;
    }

    public void setRightHandArg1(SymTabInfo rightHandArg1) {
        this.rightHandArg1 = rightHandArg1;
    }

    public SymTabInfo getRightHandArg2() {
        return rightHandArg2;
    }

    public void setRightHandArg2(SymTabInfo rightHandArg2) {
        this.rightHandArg2 = rightHandArg2;
    }

    public SymTabInfo getLeftHandResult() {
        return leftHandResult;
    }

    public void setLeftHandResult(SymTabInfo leftHandResult) {
        this.leftHandResult = leftHandResult;
    }
}
