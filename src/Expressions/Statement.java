package Expressions;

import CodeGeneration.ThreeAddressCode;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class Statement {
    public ArrayList<ThreeAddressCode> getCodeList()
    {
        throw new Error("getCodeList() should be implemented by sub classes!");
    }
}
