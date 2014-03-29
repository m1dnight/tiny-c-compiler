package MyGCC;

import java.util.*;

/**
 * Class used for managing if/else blocks
 * and generating the assembler codem along with positionning of labels, ect...
 **/
public class LogicalIfElse extends LogicalBlock {
  
  public Block elseBlock = null;
  
  public LogicalIfElse(Expression r, InstructionType op) {
    super(r, op);
  }
  
  public LogicalIfElse(Expression r, InstructionType op, Block b, Block elseBlock) {
    super(r, op, b);
    this.elseBlock = elseBlock;
  }
  
  public int nbReturns(){
    int i = 0;
    if(elseBlock != null)
      i += elseBlock.nbReturns();
    return i + this.block.nbReturns();
  }
  
  public static String instructionToAssembly(LogicalIfElse instruct, Context context) throws Exception {
    StringBuffer sb = new StringBuffer();
    String label1 = LabelManager.getLabel();
    String label2 = LabelManager.getLabel();
    if(instruct != null){
      if(instruct.rexpr == null){
        System.out.println("rexpr is null");
        // do nothing: already handled in epilogue()
        return sb.toString();
      }
      
      sb.append(instruct.rexpr.handleExpression(null, context));
      sb.append("\t");
      
      if(instruct.rexpr.flag != null && instruct.rexpr.flag.equals(Flag.NOT))
        sb.append(OperationType.EQUALS + " ");
      else
        sb.append(instruct.rexpr.op + " ");
        
      sb.append(label1); sb.append("\n");
      sb.append(instruct.block.toCode());
       
      if(instruct.elseBlock != null) {
        sb.append("\t"); sb.append(Assembly.JUMP + " "); sb.append(label2); sb.append("\n");
      }

      sb.append(label1); sb.append(":\n");
        
      if(instruct.elseBlock != null) {
        sb.append(instruct.elseBlock.toCode());
        sb.append(label2); sb.append(":\n");
      }
      
      return sb.toString();
    }
    
    System.err.println("The instruction is null");
    return null;
  }
}
