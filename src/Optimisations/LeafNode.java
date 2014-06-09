package Optimisations;

import SymbolTable.SymTabInfo;

/**
 * Created by christophe on 6/5/14.
 */
public class LeafNode extends Node {

    public LeafNode(SymTabInfo label)
    {
        this.AddSymbol(label);
    }

    public String toString()
    {
        if(this.getLabels().size() == 0)
            return String.format("\nLeaf:  " + "<no labels>");
        return String.format("\nLeaf:  " + this.getLabels());
    }
}
