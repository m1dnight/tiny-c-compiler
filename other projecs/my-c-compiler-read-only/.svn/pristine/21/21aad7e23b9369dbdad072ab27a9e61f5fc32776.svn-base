package MyGCC;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Stores all the necessary information of a function's prototype
 **/
class Prototype{
  
  public Type retType;
  public String identifier;
  public ArrayList<Type> typeList;
  
  public Prototype() {
    this.retType = null;
    this.identifier = new String();
    this.typeList = new ArrayList<Type>();
  }
  
  public Prototype(Type t, String ident, ArrayList<Type> parameters) {
    this.retType = t;
    this.identifier = ident;
    this.typeList = parameters;
  }
  
  public void setReturn(Type t) {
    this.retType = t;
  }
  
  public void setIdentifier(String s) {
    this.identifier = s;
  }
  
  public void pushType(Type t) {
    this.typeList.add(t);
  }
  
  public Type getReturn() {
    return this.retType;
  }
  
  public String getIdentifier() {
    return this.identifier;
  }
  
  public ArrayList<Type> getTypeList() {
    return this.typeList;
  }
}