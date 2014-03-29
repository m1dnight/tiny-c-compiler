package MyGCC;

public enum Type {
  INT("int", true, 8),
  VOID("void", false, 8);
  
  private boolean isVarType;
  private String strType;
  public int size;
    
  private Type(String s, boolean ia, int size) {
    this.isVarType = ia;
    this.strType = s;
    this.size = size;
  }
  
  public String toString(){
    return this.strType;
  }
    
}
