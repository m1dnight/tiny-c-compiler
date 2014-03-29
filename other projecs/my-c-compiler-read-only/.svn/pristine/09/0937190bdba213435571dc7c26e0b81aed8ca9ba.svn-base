package MyGCC;

import java.util.Collections;
import java.util.Stack;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.io.*;

/**
 * The nerve center of the compiler, in charge of interfacing between the parser and the rest
 * of the compiler
 **/
public class CodeGenerator{

  private LinkedList<Stack<Object>> myStack;
  private LinkedList<Prototype> globalPrototypes = new LinkedList<Prototype>();
  private LinkedList<Function> globalFunctions = new LinkedList<Function>();
  public static StringManager sm = new StringManager();

  private GlobalContext globalContext = new GlobalContext();
  private Context actualContext;
  private Function currentFunction;
  private Block currentBlock = null;
  private Stack<Expression> args;
  public static boolean mode64 = true;

  public CodeGenerator(){
    myStack = new LinkedList<Stack<Object>>();
    args = new Stack();
  }
  
  public void pushArgument(Expression e){
    args.push(e);
  }
  
  public ArrayList<Expression> getArguments(){
    ArrayList<Expression> lst = new ArrayList<Expression>();
    while(!args.empty())
      lst.add(args.pop());
    return lst;
  }
  
  public Block getCurrentBlock() {
    return this.currentBlock;
  }
  
  public void pushInstruction(Instruction i){
    if(Parser.DEBUG) System.out.println("Pushing instruction to function: " + currentFunction.name);
    if (currentFunction == null)
      System.err.println("ERROR: currentFunction is null");
    this.currentBlock.pushInstruction(i);
  }
  
  public void pushInformation(Object o){
    if(o == null)
      System.err.println("Pushing NULL object");
      
    if (myStack.size() == 0)
      openNewContext();
    myStack.getLast().push(o);
  }

  public void openNewContext(){
    if(Parser.DEBUG) System.out.println("Opening a new context, actual stack size :" + myStack.size());
    myStack.add(new Stack<Object>());
  }

  @SuppressWarnings("unchecked")
		public void declarePrototype(){
    Type returnType = null;
    String name = null;
    ArrayList<Type> parameters = new ArrayList<Type>();

    Stack<Object> tmpStack = myStack.getLast();
    while (!tmpStack.isEmpty()){
      ParsingResult r = (ParsingResult) tmpStack.pop();
      switch (r.type){
      case TYPE :        returnType = ((ParsingResult<Type>)r).getValue();          break;
      case ID:           name       = ((ParsingResult<String>)r).getValue();        break;
      case PARAMETER :   parameters.add(((ParsingResult<Type>)r).getValue());       break;
      }
    }
    Collections.reverse(parameters);
    if(Parser.DEBUG)
      System.out.println("Declaring a prototype with :"
                         + "\n\tReturnType : " + returnType
                         + "\n\tname : " + name);
    globalPrototypes.add(new Prototype(returnType, name, parameters));
  }
  
  public String getIdentifierOnStack(){
    for (Object o : myStack.getLast()){
      ParsingResult r = (ParsingResult) o;
      if (r.type == ResultType.ID){
        return ((ParsingResult<String>)r).getValue();
      }
        
    }
    return null;
  }

  @SuppressWarnings("unchecked")
    public void startFunctionDefinition(){
    if(Parser.DEBUG) System.out.println("Starting function definition");
    Type returnType = null;
    String name = null;
    ArrayList<Parameter> parameters = new ArrayList<Parameter>();
    Stack<Object> tmpStack = myStack.getLast();
    while (!tmpStack.isEmpty()){
      ParsingResult r = (ParsingResult) tmpStack.pop();
        
      switch (r.type){
      case TYPE :        returnType = ((ParsingResult<Type>)r).getValue();          break;
      case ID:           name       = ((ParsingResult<String>)r).getValue();        break;
      case PARAMETER :   parameters.add(((ParsingResult<Parameter>)r).getValue());  break;
      }
    }
    Collections.reverse(parameters);
    Function f = new Function(name, returnType, parameters, globalContext);
    globalFunctions.add(f);
    this.currentFunction = f;
    this.currentBlock = f.body;
  }
  
  public void startBlockDefinition(Block b) {
    if(b != null) {
      b.parent = this.currentBlock;
      this.currentBlock = b;
      if(b.parent != null) {
        this.currentBlock.myContext = b.parent.myContext;
      }
    }
  }
  
  public void startBlockDefinition() {
    Block b = new Block();
    b.parent = this.currentBlock;
    this.currentBlock = b;
    if(this.currentBlock.parent != null)
      this.currentBlock.myContext = this.currentBlock.parent.myContext;
  }
  
  public void closeBlockDefinition() {
    this.currentBlock = this.currentBlock.parent;
  }


  @SuppressWarnings("unchecked")
    public void declareVariable(){
    Type type = null;
    String identifier = null; 
    int arraySize = 0;
		boolean isStatic = false;
    Stack<Object> tmpStack = myStack.getLast();
    while(!tmpStack.isEmpty()) {
      ParsingResult r = (ParsingResult) tmpStack.pop();
      switch(r.type) {
      case TYPE : 
        type = ((ParsingResult<Type>)r).getValue();
				if(Parser.DEBUG) System.out.println("type : " + type);
        break;
      case ID : 
        identifier = ((ParsingResult<String>)r).getValue();
				if(Parser.DEBUG) System.out.println("identifier : " + identifier);
        break;
      case VARIABLE :
        arraySize = ((ParsingResult<Integer>)r).getValue();
				if(Parser.DEBUG) System.out.println("arraySize : " + arraySize);
        break;
			case QUALIFIER :
				//				if(((ParsingResult<String>)r).getValue().equals("static"))
				isStatic = true;
				if(Parser.DEBUG) System.out.println("isStatic : " + isStatic);
				break;
      default:
        System.err.println("Unexpected Type :" + r.type);
      }
    }
    if(currentFunction != null) {
			if(Parser.DEBUG) System.out.println("Adding to function : " + type + ", " + identifier + ", " + arraySize + ", " + isStatic);
      currentFunction.addDeclaration(type, identifier, arraySize, isStatic);
		}
    else {
      this.globalContext.addVariable(type, identifier, arraySize, isStatic);
    }
  }
  
  public void checkValidity(String variable, int line, int col) throws Exception {
		this.globalContext.prepareLocalVariablesLocation();
    currentFunction.loadParameters(); //FIXME find a more suitable way to do this
    try{
      currentFunction.getFunctionContext().getVariableLocation(variable);
    }catch (Exception e){
      Parser.errors.add(":" + line + ":" + col + ": error: <" + variable + "> undeclared.");
    }
  }
  
  public void checkFunction(String variable, int line, int col){
    //System.out.println("Checking for prototypes matching the following name : " + variable);
    for(Prototype p : globalPrototypes){
      //TODO affiner le test pour vérifier que les variables déclarées sont bonnes
      //System.out.println("\tIdentifier : " + p.identifier);
      if(p.identifier.equals(variable))
        return;
    }
    Parser.errors.add(":" + line + ":" + col + ": error: <" + variable + "> undefined reference to function.");
  }
  

  public String generateCode(){
    StringBuffer sb = new StringBuffer();
    try{
      for (Function f : globalFunctions){
        sb.append("\n" + f.toCode() + "\n");
      }
    }catch(Exception e){
      Parser.errors.add(":An internal error has occured, no code will be produced");
      e.printStackTrace();
    }
    return sb.toString();
  }
  
  public void closeFunction(){
    currentFunction = null;
  }
  
  public GlobalContext getGlobalContext() {
    return this.globalContext;
  }

}
