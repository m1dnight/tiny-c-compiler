package Optimisations;

import Assembly.BasicBlock;
import CodeGeneration.ThreeAddressCode;

import java.util.ArrayList;

/**
 * Created by christophe on 09.06.14.
 */
public class ConstantPropagation
{
    public BasicBlock Optimize(BasicBlock block)
    {
        ArrayList<ThreeAddressCode> newTacs = new ArrayList<ThreeAddressCode>();
        for(ThreeAddressCode tac : block.getTacs())
        {

        }
    }
}
