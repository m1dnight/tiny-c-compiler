package Optimisations;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Utils.MyBiMap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;

/**
 * Created by christophe on 09.06.14.
 */
public class LocalValueNumbering
{
    class MapEntry
    {
        public SymTabInfo var;
        public Integer    number;
    }
    public static BasicBlock Optimize(BasicBlock block)
    {
        int number = 0; // Global counter
        MyBiMap<SymTabInfo, Integer> symbolToNumber = new MyBiMap<SymTabInfo, Integer>();
        MyBiMap<String,Integer>      opToNumber     = new MyBiMap<String,Integer>();


        for (ThreeAddressCode tac : block.getTacs())
        {
            if (tac.getOpCode() == OpCodes.A2PLUS || tac.getOpCode() == OpCodes.A2TIMES || tac.getOpCode() == OpCodes.A2MINUS ||
                    tac.getOpCode() == OpCodes.A2DIV || tac.getOpCode() == OpCodes.A2NEQ || tac.getOpCode() == OpCodes.A2EQ ||
                    tac.getOpCode() == OpCodes.A2GT || tac.getOpCode() == OpCodes.A2LT)
            {
                // Look if we already have a number for these SymTabInfo's.
                if (!symbolToNumber.containsKey(tac.getArg1()))
                {
                    symbolToNumber.put(tac.getArg1(), number);
                    number++;
                }
                if (!symbolToNumber.containsKey(tac.getArg2()))
                {
                    symbolToNumber.put(tac.getArg2(), number);
                    number++;
                }

                // Generate hash string
                System.out.println(symbolToNumber.toString());
                String hash = symbolToNumber.getValue(tac.getArg1()) + " " + tac.getOpCode() + " " + symbolToNumber.getValue(tac.getArg2());
                System.out.println("New hash:" + hash);
                System.out.println(opToNumber.toString());

                if (opToNumber.containsKey(hash))
                {
                    int symbolNumber = opToNumber.getValue(hash);
                    SymTabInfo var = symbolToNumber.getKey(symbolNumber);

                    tac.setOpCode(OpCodes.A0);
                    tac.setArg2(null);
                    tac.setArg1(var);
                } else
                {
                    // We have not found the hash key
                    // so we put the resulting variable with the new number.
                    opToNumber.put(hash, number);
                    symbolToNumber.put(tac.getResult(), number);
                    number++;
                }
            }

            if (tac.getOpCode() == OpCodes.A0)
            {
                // If the argument does not exist we add a unique number to it.
                if (!symbolToNumber.containsKey(tac.getArg1()))
                {
                    symbolToNumber.put(tac.getArg1(), number);
                    number++;
                }
                int symbolNumberOfArg  = symbolToNumber.getValue(tac.getArg1());
                symbolToNumber.put(tac.getResult(), symbolNumberOfArg);
            }
        }
        return block;

    }
}

