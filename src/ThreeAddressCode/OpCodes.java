package ThreeAddressCode;

/**
 * This class contains a simple enum that holds all the possible
 * OP codes used in three address code.
 */
public enum OpCodes {
    A2PLUS, A2TIMES, A2MINUS, A2DIV, A1MINUS, A1FTOI, A1ITOF, A0,
    GOTO, IFEQ, PARAM, CALL, AAC, AAS, ADDR, DEREF, DEREFA
}
