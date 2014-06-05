package Assembly.DAG;

import SymbolTable.SymTabInfo;

/**
 * Created by christophe on 6/5/14.
 */
public class LeafNode {
    private final SymTabInfo label;

    public LeafNode(SymTabInfo label) {
        this.label = label;
    }
    /******************************************************************************************************************/
    /************************************ GETTERS AND SETTERS *********************************************************/
    /******************************************************************************************************************/
    public SymTabInfo getLabel() {
        return label;
    }
}
