package MyGCC;

import java.util.ArrayList;

public class FunctionCall extends Expression{
  
  private String tag;
  private ArrayList<Expression> args;
  

  public FunctionCall(String tag, ArrayList<Expression> arguments){
    super();
    this.tag = tag;
    this.args = arguments;
  }
  
  public int maxParameters(){
    return args.size();
  }
  
  public String getTag(){
    return tag;
  }
  
  public ArrayList<Expression> getArgs(){
    return args;
  }

}
