package MyGCC;

import java.util.*;

public class Block {
  
  public Block parent = null;
  public LinkedList<Object> code;
  public Context myContext;

  
  public Block() {
    this.code = new LinkedList<Object>();
    this.myContext = null;
  }
  
  public Block(LinkedList<Object> code) {
    this.code = code;
    this.myContext = null;
  }
  
  public Block(Block b, Context c) {
    this.code = new LinkedList<Object>(b.code);
    this.myContext = c;
  }
  
  public Block(Context c) {
    this.code = new LinkedList<Object>();
    this.myContext = c;
  }
  
  /**
   * This functions return the highest number of parameters contained in a
   * function call of the block.
   * The value returned is <b>-1</b> if there's no call to functions in this block
   */  
  public int maxParameters(){
    int n = -1;
    for (Object i : code){
      if(i instanceof Instruction)
        n = Math.max(((Instruction) i).maxParameters(), n);
      else
        n = Math.max(((Block) i).maxParameters(), n);
    }
    return n;
  }
  
  public int nbReturns(){
    int i = 0;
    for(Object o : this.code){
      
      if(o instanceof LogicalBlock)
        i += ((LogicalBlock)o).nbReturns();
      else if(o instanceof LogicalIfElse)
        i += ((LogicalIfElse)o).nbReturns();
      else if(o instanceof Instruction){
        if(((Instruction)o).type.equals(InstructionType.RETURN))
          i++;
      }
    }
    return i;
  }
  
  public void pushInstruction(Instruction e) {
    if(this.code != null)
      if(e != null) {
        this.code.add(e);
      } else
        System.err.println("The specified instruction is null");
    else
      System.err.println("this.instructions: is null");
  }
  
  public void pushBlock(Block bl) {
    if(this.code != null)
      if(bl != null)
        this.code.add(bl);
      else
        System.err.println("The specified block is null");
    else
      System.err.println("this.blocks: is null");
  }

  public LinkedList<Object> getInstructions() {
    return this.code;
  }
    
  public String toCode() throws Exception{
    StringBuffer sb = new StringBuffer();
    boolean unreachable = false;
    Block b = this;
    while(b.parent != null)
      b = b.parent;

    for(Object o : code){
      if(!unreachable){
        if(o instanceof LogicalIfElse) {
          sb.append(LogicalIfElse.instructionToAssembly((LogicalIfElse)o, myContext));
        } else if(o instanceof LogicalBlock) {
          sb.append(LogicalBlock.instructionToAssembly((LogicalBlock)o, myContext));
        } else if(o instanceof Instruction) {
          if(((Instruction)o).type.equals(InstructionType.RETURN))
            unreachable = true;
          sb.append(Instruction.instructionToAssembly((Instruction)o, myContext, ((Body)b).mFunction));
        }
      }
    }

    return sb.toString();
  }
}
