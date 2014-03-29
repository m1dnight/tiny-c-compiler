package MyGCC;

/**
 * Used for identifying different types of elements
 * that were parsed and sent to the code generator.
 **/
public enum ResultType{
  TYPE,
		ID,
		PARAMETER,
		PARAMETERS,
		BODY,
		BLOCK,
		PROTOTYPE,
		FUNCTION,
		VARIABLE,
		QUALIFIER,
		UNKNOWN;

	public static boolean isLogical(InstructionType it) {
		switch(it) {
		case IF:
			return true;
		case WHILE:
			return true;
		default:
			return false;
		}
	}

}
