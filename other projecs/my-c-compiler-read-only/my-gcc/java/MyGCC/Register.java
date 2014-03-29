package MyGCC;

import java.util.ArrayList;
import java.util.HashSet;
import java.lang.String;

/**
 * An enumerations of all the registers at our disposal for code generation
 * Useful for retrieving information and attributing roles to registers
 **/
public enum Register {
  
  RAX ("%eax", "%rax", "Return Value", RegisterType.CALLER_SAVED),
		RBX ("%ebx", "%rbx", "", RegisterType.CALLEE_SAVED),
		RCX ("%ecx", "%rcx", "4th Argument", RegisterType.CALLER_SAVED),
		RDX ("%edx", "%rdx", "3rd Argument", RegisterType.CALLER_SAVED),
		RSI ("%esi", "%rsi", "2nd Argument", RegisterType.CALLER_SAVED),
		RDI ("%edi", "%rdi", "1st Argument", RegisterType.CALLER_SAVED),
		RBP ("%ebp", "%rbp", "", RegisterType.SPECIAL), // Technically not a special register, but will be treated as one
		RSP ("%esp", "%rsp", "Stack Pointer", RegisterType.SPECIAL),
		R8 ("%r8d", "%r8", "5th Argument", RegisterType.CALLER_SAVED),
		R9 ("%r9d", "%r9", "6th Argument", RegisterType.CALLER_SAVED),
		R10 ("%r10d", "%r10", "", RegisterType.CALLEE_SAVED),
		R11 ("%r11d", "%r11", "Used for Linking", RegisterType.SPECIAL),
		//R12 ("Unused", RegisterType.UNUSED)  // We have no need for this registry as we will only be compiling C
		R13 ("%r13d", "%r13", "", RegisterType.CALLEE_SAVED),
		R14 ("%r14d", "%r14", "", RegisterType.CALLEE_SAVED),
		R15 ("%r15d", "%r15", "", RegisterType.CALLEE_SAVED),
		RIP ("%eip", "%rip", "", RegisterType.CALLEE_SAVED);
  
  public enum RegisterType {
    CALLEE_SAVED,
			CALLER_SAVED,
			SPECIAL;
    //UNUSED; Would be useful if ever we decided to compile additionnal languages
  }
  
  private String name32;
  private String name64;
  private String comment;
  private RegisterType type;
  public static HashSet<Register> calleeSaved = new HashSet<Register>();
  public static HashSet<Register> callerSaved = new HashSet<Register>();
  public static HashSet<Register> special = new HashSet<Register>();
  private static ArrayList<Register> arguments = new ArrayList<Register>();
  
  static {
    arguments.add(RDI);
    arguments.add(RSI);
    arguments.add(RDX);
    arguments.add(RCX);
    arguments.add(R8);
    arguments.add(R9);
    
    for (Register r : Register.values()){
      addRegister(r);
    }
  }
  
  public static Register getArgumentRegister(int i){
    return arguments.get(i);
  }
  
  private Register (String name32, String name64, String comment, RegisterType t) {

    this.name32 = name32;
    this.name64 = name64;
    this.comment = comment;
    this.type = t;
  }
  
  private void assignType(RegisterType t) {
    this.type = t;
  }
  
  private static void addRegister(Register reg) {
    switch(reg.type) {
    case CALLEE_SAVED:
      calleeSaved.add(reg);
      break;
    case CALLER_SAVED:
      callerSaved.add(reg);
      break;
    case SPECIAL:
      special.add(reg);
      break;
    default:
      System.err.println("The RegisterType passed to this function is incorrect");
    }
  }
  
  @SuppressWarnings("unchecked")
    public static HashSet<Register> getCalleeSaved() {
    return (HashSet<Register>)calleeSaved.clone();
  }
  
  @SuppressWarnings("unchecked")
    public static HashSet<Register> getCallerSaved() {
    return (HashSet<Register>)callerSaved.clone();
  }
  
  @SuppressWarnings("unchecked")
    public static HashSet<Register> getSpecial() {
    return (HashSet<Register>)special.clone();
  }
  
  public RegisterType getType() {
    return this.type;
  }
  
  public String getTypeString() {
    switch(this.type) {
    case CALLEE_SAVED:
      return new String("CALLEE_SAVED");
    case CALLER_SAVED:
      return new String("CALLER_SAVED");
    default:
      return new String("SPECIAL");
    }
  }
  
  public String toString() {
    if(CodeGenerator.mode64)
      return this.name64;
    return this.name32;
  }
}

