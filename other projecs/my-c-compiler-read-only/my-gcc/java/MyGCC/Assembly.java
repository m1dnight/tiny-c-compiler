package MyGCC;

/**
 * An enumeration of all the Assembler instructions
 * the compiler can manage
 **/
public enum Assembly{
  
  MOV("movl", "movq"),
		PUSH("pushl", "pushq"),
		POP("popl", "popq"),
		CALL("call", "call"),
		CONVERT("cdq", "cqto"),
		COMPARE("cmpl", "cmpq"),
		NEG("negl", "negq"),
		JUMP("jmp", "jmp"),
		LEA("leal", "leaq"),
		TEST("testl", "testq");
  
  private String name32;
  private String name64;
  
  private Assembly (String name32, String name64) {
    this.name32 = name32;
    this.name64 = name64;
  }
  
  public String toString(){
    if(CodeGenerator.mode64)
      return this.name64;
    return this.name32;
  }
  
}
