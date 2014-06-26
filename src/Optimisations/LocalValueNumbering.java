package Optimisations;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Utils.MyBiMap;

/**
 * Created by christophe on 09.06.14.
 */
public class LocalValueNumbering {
    public static BasicBlock Optimize(BasicBlock block) {
        boolean changed = false;
        int number = 0; // Global counter
        MyBiMap<SymTabInfo, Integer> symbolToNumber = new MyBiMap<SymTabInfo, Integer>();
        MyBiMap<String, Integer> opToNumber = new MyBiMap<String, Integer>();

        do {
            changed = false;
            number = 0;
            symbolToNumber = new MyBiMap<SymTabInfo, Integer>();
            opToNumber = new MyBiMap<String, Integer>();
            for (ThreeAddressCode tac : block.getTacs()) {
                if (tac.getOpCode() == OpCodes.A2PLUS || tac.getOpCode() == OpCodes.A2TIMES || tac.getOpCode() == OpCodes.A2MINUS ||
                        tac.getOpCode() == OpCodes.A2DIV || tac.getOpCode() == OpCodes.A2NEQ || tac.getOpCode() == OpCodes.A2EQ ||
                        tac.getOpCode() == OpCodes.A2GT || tac.getOpCode() == OpCodes.A2LT) {
                    if (!symbolToNumber.containsKey(tac.getArg1())) {
                        symbolToNumber.put(tac.getArg1(), number);
                        number++;
                    }
                    if (!symbolToNumber.containsKey(tac.getArg2())) {
                        symbolToNumber.put(tac.getArg2(), number);
                        number++;
                    }

                    // Generate hash string
                    int valNumA = symbolToNumber.getValue(tac.getArg1());
                    int valNumB = symbolToNumber.getValue(tac.getArg2());
                    String hash;
                    if (valNumA > valNumB)
                        hash = valNumA + " " + tac.getOpCode() + " " + valNumB;
                    else
                        hash = valNumB + " " + tac.getOpCode() + " " + valNumA;


                    if (opToNumber.containsKey(hash)) {
                        int symbolNumber = opToNumber.getValue(hash);
                        SymTabInfo var = symbolToNumber.getKey(symbolNumber);

                        tac.setOpCode(OpCodes.A0);
                        tac.setArg2(null);
                        tac.setArg1(var);
                        symbolToNumber.put(tac.getResult(), symbolNumber);
                        changed = true;
                    } else {
                        // We have not found the hash key
                        // so we put the resulting variable with the new number.
                        opToNumber.put(hash, number);
                        symbolToNumber.put(tac.getResult(), number);
                        number++;
                    }
                    continue;
                }
                if (tac.getOpCode() == OpCodes.A0) {
                    // If we are assigning to a variable that is already in our hashtable we need to
                    // invalidate that record (remove it).
                    if (symbolToNumber.containsKey(tac.getResult())) {
                        int indexForSymbol = symbolToNumber.getValue(tac.getResult());
                        opToNumber.removeWithValue(indexForSymbol);
                    } else {
                        symbolToNumber.put(tac.getResult(), number);
                        number++;
                    }
                    // If the argument does not exist we add a unique number to it.
                    if (!symbolToNumber.containsKey(tac.getArg1())) {
                        symbolToNumber.put(tac.getArg1(), number);
                        number++;
                    }

                    int symbolNumberOfArg = symbolToNumber.getValue(tac.getResult());
                    symbolToNumber.put(tac.getResult(), symbolNumberOfArg);
                    continue;
                }
            }
        }
        while (changed);

        return new BasicBlock().setTacs((java.util.ArrayList<ThreeAddressCode>) block.getTacs().clone());
    }
}

