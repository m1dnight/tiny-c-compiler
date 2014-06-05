package Assembly.DAG;

import SymbolTable.SymTabInfo;

import java.util.ArrayList;

/**
 * Created by christophe on 6/5/14.
 */
public class LeafNode extends Node {

    public LeafNode(SymTabInfo label)
    {
        this.AddSymbol(label);
    }
}
