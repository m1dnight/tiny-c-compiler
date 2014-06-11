package Assembly.x86;

import Assembly.BasicBlock;

import java.util.ArrayList;

/**
 * Created by christophe on 11.06.14.
 */
public class BasicBlockToX86Generator
{
    private StringBuilder prologue = new StringBuilder();
    private StringBuilder globl = new StringBuilder();

    private void Prologue()
    {
        prologue.append(".section .data");
        prologue.append(".section .text");
        globl.append(".globl _start");
    }

    public void Compile(ArrayList<BasicBlock> blocks)
    {

    }
}
