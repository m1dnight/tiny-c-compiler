/* 
	Author: Aleksandar Milenkovic 56/05
	Rachunarski Fakultet (RAF), Univerzitet Union, 2008
	Prerada mikro c kompajlera u Javu
	
	SVN: http://code.google.com/p/javamicko/
	
	Interfejs sa konstantama za Lexer
*/

package main;

public interface Constants {

	public enum sym_type { 
		NO_KIND(0x1, "NONE"), 
		WORKING_REGISTER (0x2, "WORKING_REGISTER"),
		GLOBAL_VAR (0x4, "GLOBAL_VAR"), 
		FUNCTION (0x8, "FUNCTION"), 
		PARAMETER (0x10, "PARAMETER"), 
		LOCAL_VAR (0x20, "LOCAL_VAR"), 
		CONSTANT (0x40, "CONSTANT"); 
	
	private final int value;
	private final String kind;
		
	private sym_type(int value, String kind)
	{
		this.value = value;
		this.kind = kind;
	}
		
	public int value() { return value; }
	public String kind() { return kind; }
	};

	public enum var_type { NO_TYPE, SIGNED_TYPE, UNSIGNED_TYPE, FLOAT_TYPE, VOID_TYPE };
	
	public enum value_elements {VAR_TYPE, SCOPE, PARAM_TYPES, SHADOWED_BY }; 
	
	public enum mulop_type { TIMES, DIV };
	public enum relop_type { LT, GT, LE, GE, EQ, NE };
	public enum scope_type { GLOBAL_LEVEL, LOCAL_LEVEL, PARAMETER_LEVEL };
	public enum assign_types { PLAIN, ADD_EQ, SUB_EQ, MUL_EQ, DIV_EQ };
	
	public final static int NO_ATTRIB = -1;
	public final static int WORKING_REG_NUMBER = 12;
	public final static int RETURN_REGISTER = WORKING_REG_NUMBER + 1;
	public final static int TYPE_BIT_SIZE = 32; //4 bytes
}
	
/*
 * public static final int INTTYPE = 1; public static final int BOOLTYPE = 2;
 * 
 * public static final int TRUE = 1; public static final int FALSE = 0;
 * 
 * public static final int PLUS = 1; public static final int MINUS = 2; public
 * static final int MULT = 3; public static final int DIV = 4;
 * 
 * public static final int LESS_EQUAL = 1; public static final int NOT_EQUAL =
 * 2; public static final int GREATER_EQUAL = 3; public static final int LESS =
 * 4; public static final int GREATER = 5; public static final int EQUAL = 6;
 * 
 * 
 * public static final int VARIABLE = 1; public static final int ARRAYVAR = 2;
 * public static final int PAR = 3; public static final int VARPAR = 4; public
 * static final int ARRAYPAR = 5; public static final int VARARRAYPAR = 6;
 * public static final int PROCEDURE = 7; public static final int PROGRAM = 8;
 */ 

