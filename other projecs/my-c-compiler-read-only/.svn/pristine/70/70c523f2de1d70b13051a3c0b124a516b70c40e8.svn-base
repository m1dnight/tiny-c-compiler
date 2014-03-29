package MyGCC;
import java.util.ArrayList;
import java.util.HashMap;

public class FunctionContext extends Context{

  /** Contain the "virtual" location of the parameters,
   *  for example: if the parameter <i>p</i> is in <i>-4(%rbp)</i>, the tuple will be <i>(p.name,-4)</i> **/
  private HashMap<String,Integer> parametersLocations = new HashMap<String,Integer>();
  private boolean parametersLocated = false;
  //private ArrayList<Parameter> parameters;

  public FunctionContext(Context inheritedContext){
    this.inheritedContext = inheritedContext;     
  }

  public void setParameters(ArrayList<Parameter> newParameters){
    this.parameters = newParameters;
  }

  public void prepareParametersLocation(){
    if (parametersLocated) return;
    prepareLocalVariablesLocation();
    int k = 1;
    if (parameters != null){// TODO usually parameters should never be null but that's an issue coming from the parser
      for (Parameter p : parameters){
        if (k <= 6){
          parametersLocations.put(p.name,-variablesTotalSize - 8 * k);
          stackPosition -= 8;
        }
        else
          //offset used by the push in beginning of function and some compiling directives it seems
          parametersLocations.put(p.name, (k - 7) * 8 + 16);
        k++;
      }
    }
    parametersLocated = true;
  }
  
  public int nbParameters(){
    return parameters.size();
  }
  
  public String getArrayLocation(String name) throws Exception{
		Integer result = parametersLocations.get(name);
		if (result != null){
			for(Parameter p : parameters){
				if(p.name.equals(name) && p.arraySize != 0)
					return "(" + Register.RCX + ", " + Register.RDX + ", " + "8" + ")";
			}
		}
		return super.getArrayLocation(name);
		//throw new Exception("No parameter with the specified name : <" + name + "> found");
	}

  public String getVariableLocation(String name) throws Exception{
    // searching in Parameters
    Integer result = parametersLocations.get(name);
    if (result != null)	return result.intValue() + "(" + Register.RBP + ")";
    return super.getVariableLocation(name);
  }

  public Parameter getParameter(int index){
    return parameters.get(index);
  }
  
  public boolean isGlobalArray(String name) throws Exception{
		return inheritedContext.isGlobalArray(name);
	}

}
