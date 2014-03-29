package MyGCC;

import java.util.ArrayList;
import java.util.HashMap;

public class GlobalContext extends Context{
  
  public GlobalContext() {
    this.inheritedContext = null;
  }

	/**
	 * returns the assembly code necessary to access a given variable.
	 * Throws an exception if the variable is not declared
	 **/
  public String getVariableLocation(String name) throws Exception{
    // searching in Local Variables
    ContextEntry ce = localVariables.get(name);
    if (!(ce == null)) {
      if(ce.arraySize == 0) {
        return name + "(" + Register.RIP + ")";// TO PERFECT
      }
			return name + "(, " + Register.RAX + ", " + ce.type.size + ")";
    }
    throw new Exception("No parameter with the specified name : <" + name + "> found");
  }

	public String getArrayLocation(String name) throws Exception{
		ContextEntry ce = localVariables.get(name);
    
		if(ce != null)
			if(ce.arraySize != 0)
				return name + "(, " + Register.RDX + ", " + ce.type.size + ")";
		
		if(inheritedContext != null)
			return inheritedContext.getArrayLocation(name);
    throw new Exception("No parameter with the specified name : <" + name + "> found");
  }
  
  public boolean isGlobalArray(String name) throws Exception{
		ContextEntry ce = localVariables.get(name);
		if(ce != null)
			if(ce.arraySize != 0)
				return true;
		return false;
	}
  
	public String makeLabels() {
    StringBuffer sb = new StringBuffer();
    for(String c : localVariables.keySet()) {
      ContextEntry ce = localVariables.get(c);
      int as = ce.arraySize;
      if(as == 0)
        as++;
      as *= ce.type.size;
			// Find the closest power of 2 to as;
			double as2 = Math.pow(2, Math.ceil(Math.log(as)));
			if(ce.isStatic)
				sb.append("\t.local " + c + "\n");
      sb.append("\t.comm " + c + "," + as +"," + (int)as2 + "\n");
    }
    return sb.toString();
  }
}
