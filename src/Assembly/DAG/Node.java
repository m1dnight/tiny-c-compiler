package Assembly.DAG;

import CodeGeneration.OpCodes;
import SymbolTable.SymTabInfo;

/**
 * Created by christophe on 6/4/14.
 */
public class Node {
    private SymTabInfo symbol;

    public Node(OpCodes opCode, SymTabInfo arg1, SymTabInfo arg2) {
        
    }

    public void setSymbol(SymTabInfo symbol) {
        this.symbol = symbol;
    }

    public SymTabInfo getSymbol() {
        return symbol;
    }
}
