package MyGCC;

import java.util.*;
import java.util.Map.Entry;
import java.util.AbstractMap.*;

public class RegisterManager {
  /**
   * Lists all the registers that currently contain a variable
   **/
  private HashMap<Register, String> usedRegisters;
  private LinkedList<Register> usedRegistersList;
  
  public RegisterManager() {
    this.usedRegisters = new HashMap<Register, String>();
    this.usedRegistersList = new LinkedList<Register>();
    //this.usedStackPositions = new HashMap<Integer, String>();
  }
  
  public boolean isListedVariable(String var) {
    return this.usedRegisters.containsValue(var);
  }
  
  /*public boolean isListedValue(int i) {
    return this.usedStackPositions.containsValue(i);
    }*/
  
  public boolean isRegisterUsed(Register r) {
    return this.usedRegisters.containsKey(r);
  }
  
  /**
   * Returns a Register that has no assigned variable.
   **/
  public Register getFreeRegister(Register.RegisterType type) {
    ArrayList<HashSet<Register>> al = new ArrayList<HashSet<Register>>();
    HashSet<Register> list;
    switch(type) {
    case CALLER_SAVED:
      list = Register.getCallerSaved();
      al.add(list);
      break;
    case CALLEE_SAVED:
      list = Register.getCalleeSaved();
      al.add(list);
      break;
    default:
      list = Register.getCallerSaved();
      al.add(list);
      list = Register.getCalleeSaved();
      al.add(list);
    }
    
    Register r;
    Iterator<Register> iter;
    
    for(HashSet<Register> hsr : al) {
      iter = hsr.iterator();
      
      while(iter.hasNext()) {
        r = iter.next();
        if(!this.usedRegisters.containsKey(r)) {
          return r;
        }
      }
    }

    return freeUselessRegister(); // No free registers have been found, we should now attempt to free some by pushing to the stack.
  }
  

  /**
   * Returns a pair of Registers, in order to perform more complex tasks
   **/
  public SimpleEntry<Register, Register> getTwoRegisters(Register.RegisterType type) { // TODO Resolve : When two registers are called upon, they will both have the same type ? (yes ?)
    ArrayList<HashSet<Register>> al = new ArrayList<HashSet<Register>>();   
    HashSet<Register> list;
    
    switch(type) {
    case CALLER_SAVED:
      list = Register.getCallerSaved();
      al.add(list);
      break;
    case CALLEE_SAVED:
      list = Register.getCalleeSaved();
      al.add(list);
      break;
    }

    
    Iterator<Register> iter;
    Register r = null;
    Register r1 = null;
    Register r2 = null;
    
    for(HashSet<Register> hsr : al) {
      iter = hsr.iterator();
    
      while(iter.hasNext()) {
        r = iter.next();
        if(!this.usedRegisters.containsKey(r))
          if(r1 == null)
            r1 = r;
          else
            r2 = r;
      }
    
      if(r1 != null && r2 != null)
        return (new SimpleEntry<Register, Register>(r1, r2));
    }

    r1 = freeUselessRegister();
    r2 = freeUselessRegister();
    return new SimpleEntry<Register, Register>(r1, r2);
  }
  
  /**
   * Adds the variable var to a free register of type "type"
   **/
  public Register addVariableToRegister(String var, Register.RegisterType type) {

    System.out.println("searching variable " + var);
    Set<Register> regSet = this.usedRegisters.keySet();
    for(Register reg : regSet) {
      if(reg.getType().equals(type)) {
        if(this.usedRegisters.get(reg).equals(var)){
          System.out.println("variable found ");
          return reg;
        }
      }
    }
    System.out.println("variable not found");
    Register r = this.getFreeRegister(type);
    this.usedRegisters.put(r, var);
    this.usedRegistersList.addFirst(r);
    return r;
  }
  
  /**
   * Adds the variable to the specified Register
   **/
  public Register addVariableToRegister(String var, Register reg) {
    if(isRegisterUsed(reg)){
      freeRegister(reg);
      this.usedRegisters.put(reg, var);
      this.usedRegistersList.addFirst(reg);
      return reg;
    }
    else{
      this.usedRegisters.put(reg, var);
      this.usedRegistersList.addFirst(reg);
      return reg;
    }
  }
  
  public SimpleEntry<Register, Register> addTwoVariablesToRegisters(String var1, String var2, Register.RegisterType type) {
    Register r1, r2;
    SimpleEntry<Register, Register> se;
    
    boolean b1, b2;
    b1 = !isListedVariable(var1);
    b2 = !isListedVariable(var2);
    if(b1 && b2) {
      se = getTwoRegisters(type);
      r1 = se.getKey();
      this.usedRegisters.put(r1, var1);
      this.usedRegistersList.addFirst(r1);
      r2 = se.getValue();
      this.usedRegisters.put(r2, var2);
      this.usedRegistersList.addFirst(r2);
      return se;
    }
    
    r1 = addVariableToRegister(var1, type);
    r2 = addVariableToRegister(var2, type);

    if(r1 != null && r2 != null)
      return new SimpleEntry<Register, Register>(r1, r2);
      
    System.err.println("One or several of the registers were null");
    return null;
  }
  
  /**
   * Adds the variable to the nth argument register.
   **/
  public String getArgReg(String var, int n, int nb_params) {
    String result = null;
    if (n < 6){
      Register reg = Register.getArgumentRegister(n);
      reg = addVariableToRegister(var, reg);
      return reg.toString();
    }
    int position = (n - 6) * 8;
    result = position + "(" + Register.RSP + ")";
    return result;
  }
  
  public String getRegisterContent(Register reg) {
    if(this.usedRegisters.containsKey(reg))
      return this.usedRegisters.get(reg);
    return null;
  }
  
  /**
   * Removes the specified Register from the list of usedRegisters.
   **/
  public void freeRegister(Register r) {
    if(this.usedRegisters.containsKey(r)) {
      this.usedRegisters.remove(r);
      this.usedRegistersList.remove(r);
    } else
      System.err.println("The Register did not have any variables set to it.");
  }
  
  /**
   * Removes the Register that is linked to var from the list of usedRegisters.
   **/
  public void freeVariable(String var) {
    Set<Register> regSet = new HashSet<Register>();
    if(this.usedRegisters.containsValue(var))
      regSet = this.usedRegisters.keySet();
    for(Register reg : regSet)
      if(this.usedRegisters.get(reg).equals(var)){
        this.usedRegisters.remove(reg);
        this.usedRegistersList.remove(reg);
      }
      else
        System.err.println("The variable was not set to any registers.");
  }
  

  public Register freeUselessRegister() {
    Register r = this.usedRegistersList.pollLast();
    if(this.usedRegisters.containsKey(r))
      this.usedRegisters.remove(r);
    return r;
  }
  
  public void printState(){
    System.out.println("Entries :");
    for (Entry<Register,String> e: usedRegisters.entrySet()){
      System.out.println("\t" +e.getKey() +" -> " + e.getValue());
    }
    System.out.println("list :");
    for (Register r : usedRegistersList){
      System.out.println("\t"+ r + " ->");
    }
  }
}
