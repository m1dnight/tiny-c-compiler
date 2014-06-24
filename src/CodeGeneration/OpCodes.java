package CodeGeneration;

/**
 * This class contains a simple enum that holds all the possible
 * OP codes used in three address code.
 */
public enum OpCodes {
    A2PLUS, A2TIMES, A2MINUS, A2DIV, A1MINUS, A1FTOI, A1ITOF, A0,
    GOTO, IFEQ, PARAM, CALL, AAC, AAS, ADDR, DEREF, DEREFA, A2NEQ, A2EQ, A2GT, A2LT, LABEL, RETURN, IFFALSE, A2EQIF, A2NEQIF, A2GTIF, A2LTIF, GETPARAM, SCRUB, ALLOC_ARRAY, WRITE, WRITEINT, IF
}
