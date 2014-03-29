package MyGCC;

import java.util.*;

public class Body extends Block{
  
  public Function mFunction;
  public LinkedList<Declaration> declarations;
  
  public Body(Context c, Function f) {
    super(c);
    this.declarations = new LinkedList<Declaration>();
    this.mFunction = f;
  }
  
  
  /**
   * This functions return the highest number of parameters contained in a
   * function call of the body.
   * The value returned is <b>-1</b> if there's no call to functions in this body
   */  
  /*public int maxParameters(){
    return mainBlock.maxParameters();
    }*/
  
  
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
  
  public boolean hasLogicalBlock(){
    int i = 0;
    for(Object o : this.code){
      
      if(o instanceof LogicalBlock){
        if(((LogicalBlock)o).nbReturns() > 0)
          i++;
      }
      else if(o instanceof LogicalIfElse){
        if(((LogicalIfElse)o).nbReturns() > 0)
          i++;
      }
    }
    return i > 0;
  }
      
  
  public void pushDeclaration(Declaration d) {
    if(this.declarations != null)
      if(d != null)
        this.declarations.add(d);
      else
        System.err.println("The specified declaration is null");
    else
      System.err.println("this.declarations: is null");
  }

  public LinkedList<Declaration> getDeclarations() {
    return this.declarations;
  }
}
