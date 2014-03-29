package MyGCC;

public class Instruction {
  
  public Expression lexpr;
  public Expression rexpr;
  public InstructionType type;
  public Context myContext = null;
  
  public Instruction(Expression r, InstructionType op){
    this.lexpr = null;
    this.rexpr = r;
    this.type = op;
  }
  
  public Instruction(Expression l, Expression r, InstructionType op){
    this.lexpr = l;
    this.rexpr = r;
    this.type = op;
  }
  
  /**
   * This functions return the highest number of parameters contained in a
   * function call of the instruction.
   * The value returned is <b>-1</b> if there's no call to functions in the instruction
   */
  public int maxParameters(){
    int n = -1;
    if (lexpr != null) n = Math.max(n, lexpr.maxParameters());
    if (rexpr != null) n = Math.max(n, rexpr.maxParameters());
    return n;
  }
  
  public static String instructionToAssembly(Instruction instruct, Context context, Function f) throws Exception{
    StringBuffer sb = new StringBuffer();
    Expression l = instruct.lexpr;
    Expression r = instruct.rexpr;
    Variable a;
    FunctionCall fc;
    if(instruct != null){
      if(r == null){
        System.out.println("rexpr is null");
        // do nothing: already handled in epilogue()
        return sb.toString();
      }
      
      if(Parser.DEBUG) System.out.println("\tInstruction type: " + instruct.type.toString());
      switch(instruct.type){
        
      case RETURN:
        a = (Variable)l;
        sb.append(r.handleExpression(a, context));
        if(f.endTag != null)
          sb.append("\t" + Assembly.JUMP + " " + f.endTag + "\n");
        break;
          
      case EXIT:
        fc = (FunctionCall)l;
        sb.append(r.handleExpression(fc, context));
        //TODO: do not generate ret/leave when function contains an exit and no RETURN instructions
        break;
          
      case EQL:
        a = (Variable)l;
          
        if(r.isFullyNumeric()){
          if(a.index != null)
            sb.append(a.index.handleExpression(null, context));
					sb.append("\t" + Assembly.MOV + "\t$" + ExpressionHelper.calculateNum(r) + ", " + context.getVariableLocation(String.valueOf(a.getValue())) + "\n"); 
        }
          
        else{
          sb.append(r.handleExpression(a, context));
          if(a.index != null){
						sb.append(context.virtualPush(Register.RAX.toString()));
						sb.append(a.handleExpression(a, context));

						if(!context.isGlobalArray(String.valueOf(a.getValue())))	//Shouldn't execute this instruction when handling global arrays.
						  sb.append("\t" + Assembly.MOV + "\t" + Register.RAX + ", " + Register.RDX + "\n");	//Index of left-side array
						sb.append(context.virtualPop(Register.RAX.toString()));
            sb.append("\t" + Assembly.MOV + "\t" + Register.RAX + ", " + context.getArrayLocation(String.valueOf(a.getValue())) + "\n");
					}
          else
            sb.append("\t" + Assembly.MOV + "\t" + Register.RAX + ", " + context.getVariableLocation(String.valueOf(a.getValue())) + "\n");
        }
        break;
          
      case READ_INT:
        fc = (FunctionCall)r;
        sb = ExpressionHelper.handleReadInt(sb, fc, context);
        break;
          
      case PRINTF:
        fc = (FunctionCall)r;
        sb = ExpressionHelper.handlePrint(sb, fc, context);
        break;

      default:
        // case null, ie. for instructions like "1+2;"
        // optimize and don't calculate
        break;
      }
      
      return sb.toString();
    }
    
    else{
      System.err.println("ERROR: instruction is null");
      return null;
    }
  }
  
  
}
