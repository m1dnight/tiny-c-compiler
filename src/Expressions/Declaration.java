package Expressions;

import CodeGeneration.ThreeAddressCode;

import java.util.ArrayList;

/**
 * Created by christophe on 06.06.14.
 */
public class Declaration {
    public String toString()
    {
        throw new Error("toString() should be declared in subclasses of Declaration");
    }

    public ArrayList<ThreeAddressCode> toThreeAddressCode()
    {
        throw new Error("toThreeAddressCode() should be declared in subclasses of Declaration");
    }
}
