package MyGCC;

public class Variable<T> extends Expression{
  
  private T value;
  public Expression index = null;

  public Variable(OperationType op, T value){
    super();
    this.op = op;
    this.value = value;
  }
  
  public Variable(OperationType op, T value, Expression e){
    super();
    this.op = op;
    this.value = value;
    this.index = e;
  }
  
  
    
  public Variable(OperationType op, Expression l, Expression r){
    super();
    this.op = op;
    this.left = l;
    this.right = r;
  }
    
  public T getValue(){
    return value;
  }
}
