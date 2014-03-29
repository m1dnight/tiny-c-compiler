package MyGCC;

/**
 * Class designed for storing all the information
 * relevant to a specific parameter passed to a function.
 **/
public class Parameter{
  
  public Type type;
  public String name;
  public int arraySize;

  public Parameter(Type type,String name, int arraySize){
    this.type = type;
    this.name = name;
    this.arraySize = arraySize;
  }
  
  /* Since Parameter can be a key of an HashMap, this function has to be overrided */
  public int hashCode(){
    return name.hashCode();
  }

}
