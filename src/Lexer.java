/* The following code was generated by JFlex 1.4.3 on 3/29/14 1:18 PM */

import java_cup.runtime.*;
import java.io.FileInputStream;
import java.io.InputStream;




/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 3/29/14 1:18 PM from the specification file
 * <tt>tinyc.jflex</tt>
 */
public class Lexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\44\1\4\1\0\2\44\22\0\1\44\1\42\6\0\1\32"+
    "\1\33\1\5\1\30\1\26\1\31\1\0\1\3\12\1\1\0\1\27"+
    "\1\41\1\43\1\40\2\0\32\2\1\34\1\0\1\35\1\0\1\2"+
    "\1\0\1\13\1\2\1\11\1\23\1\15\1\17\1\25\1\12\1\6"+
    "\2\2\1\20\1\2\1\7\1\24\2\2\1\14\1\21\1\10\1\16"+
    "\1\2\1\22\3\2\1\36\1\0\1\37\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\7\3"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\1\0\1\3\1\27\6\3\1\30\1\31\1\32\1\0"+
    "\1\33\7\3\1\26\1\34\1\3\1\35\1\36\5\3"+
    "\1\37\1\40\1\41\1\42";

  private static int [] zzUnpackAction() {
    int [] result = new int[65];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\45\0\112\0\157\0\224\0\45\0\45\0\271"+
    "\0\336\0\u0103\0\u0128\0\u014d\0\u0172\0\u0197\0\45\0\45"+
    "\0\45\0\45\0\45\0\45\0\45\0\45\0\45\0\45"+
    "\0\45\0\45\0\u01bc\0\u01e1\0\u0206\0\u022b\0\u0250\0\u0275"+
    "\0\157\0\u029a\0\u02bf\0\u02e4\0\u0309\0\u032e\0\u0353\0\157"+
    "\0\45\0\45\0\u0378\0\157\0\u039d\0\u03c2\0\u03e7\0\u040c"+
    "\0\u0431\0\u0456\0\u047b\0\u0250\0\157\0\u04a0\0\157\0\157"+
    "\0\u04c5\0\u04ea\0\u050f\0\u0534\0\u0559\0\157\0\157\0\157"+
    "\0\157";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[65];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\2\4"+
    "\1\11\2\4\1\12\1\13\2\4\1\14\1\4\1\15"+
    "\1\16\2\4\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34"+
    "\1\35\46\0\1\3\44\0\2\4\3\0\20\4\22\0"+
    "\1\36\1\0\1\37\40\0\2\4\3\0\1\4\1\40"+
    "\7\4\1\41\6\4\20\0\2\4\3\0\4\4\1\42"+
    "\13\4\20\0\2\4\3\0\7\4\1\43\10\4\20\0"+
    "\2\4\3\0\12\4\1\44\5\4\20\0\2\4\3\0"+
    "\7\4\1\45\10\4\20\0\2\4\3\0\4\4\1\46"+
    "\1\4\1\47\11\4\20\0\2\4\3\0\16\4\1\50"+
    "\1\4\62\0\1\51\44\0\1\52\45\0\1\35\4\36"+
    "\1\0\40\36\5\37\1\53\37\37\1\0\2\4\3\0"+
    "\2\4\1\54\15\4\20\0\2\4\3\0\5\4\1\55"+
    "\12\4\20\0\2\4\3\0\2\4\1\56\2\4\1\57"+
    "\12\4\20\0\2\4\3\0\13\4\1\60\4\4\20\0"+
    "\2\4\3\0\1\4\1\61\16\4\20\0\2\4\3\0"+
    "\1\62\17\4\20\0\2\4\3\0\1\63\17\4\17\0"+
    "\3\37\1\64\1\37\1\53\37\37\1\0\2\4\3\0"+
    "\6\4\1\65\11\4\20\0\2\4\3\0\10\4\1\66"+
    "\7\4\20\0\2\4\3\0\15\4\1\67\2\4\20\0"+
    "\2\4\3\0\7\4\1\70\10\4\20\0\2\4\3\0"+
    "\17\4\1\71\20\0\2\4\3\0\12\4\1\72\5\4"+
    "\20\0\2\4\3\0\2\4\1\73\15\4\20\0\2\4"+
    "\3\0\6\4\1\74\11\4\20\0\2\4\3\0\2\4"+
    "\1\75\15\4\20\0\2\4\3\0\7\4\1\76\10\4"+
    "\20\0\2\4\3\0\7\4\1\77\10\4\20\0\2\4"+
    "\3\0\1\4\1\100\16\4\20\0\2\4\3\0\4\4"+
    "\1\101\13\4\17\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1406];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\3\1\2\11\7\1\14\11\4\1\1\0"+
    "\11\1\2\11\1\0\26\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[65];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }

  private Symbol symbol(int type, Object val) {
    return new Symbol(type, yyline, yycolumn, val);
  }



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Lexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 114) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Yytoken yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 3: 
          { return symbol(sym.NAME, yytext());
          }
        case 35: break;
        case 12: 
          { return symbol(sym.RPAR);
          }
        case 36: break;
        case 17: 
          { return symbol(sym.GREATER);
          }
        case 37: break;
        case 19: 
          { return symbol(sym.NOT);
          }
        case 38: break;
        case 16: 
          { return symbol(sym.RBRACE);
          }
        case 39: break;
        case 28: 
          { return symbol(sym.CHAR);
          }
        case 40: break;
        case 20: 
          { return symbol(sym.ASSIGN);
          }
        case 41: break;
        case 8: 
          { return symbol(sym.SEMICOLON);
          }
        case 42: break;
        case 33: 
          { return symbol(sym.RETURN);
          }
        case 43: break;
        case 4: 
          { return symbol(sym.DIVIDE);
          }
        case 44: break;
        case 11: 
          { return symbol(sym.LPAR);
          }
        case 45: break;
        case 34: 
          { return symbol(sym.LENGTH);
          }
        case 46: break;
        case 26: 
          { return symbol(sym.EQUAL);
          }
        case 47: break;
        case 30: 
          { return symbol(sym.ELSE);
          }
        case 48: break;
        case 23: 
          { return symbol(sym.IF);
          }
        case 49: break;
        case 14: 
          { return symbol(sym.RBRACK);
          }
        case 50: break;
        case 29: 
          { return symbol(sym.READ);
          }
        case 51: break;
        case 1: 
          { System.err.println("unexpected char " + yytext() + " !\n"); System.exit(0);
          }
        case 52: break;
        case 7: 
          { return symbol(sym.COMMA);
          }
        case 53: break;
        case 24: 
          { return symbol(sym.DO);
          }
        case 54: break;
        case 15: 
          { return symbol(sym.LBRACE);
          }
        case 55: break;
        case 18: 
          { return symbol(sym.LESS);
          }
        case 56: break;
        case 25: 
          { return symbol(sym.NEQUAL);
          }
        case 57: break;
        case 22: 
          { /* yyline += countLines(yytext()); */
          }
        case 58: break;
        case 32: 
          { return symbol(sym.WRITE);
          }
        case 59: break;
        case 9: 
          { return symbol(sym.PLUS);
          }
        case 60: break;
        case 27: 
          { return symbol(sym.INT);
          }
        case 61: break;
        case 10: 
          { return symbol(sym.MINUS);
          }
        case 62: break;
        case 6: 
          { return symbol(sym.TIMES);
          }
        case 63: break;
        case 31: 
          { return symbol(sym.WHILE);
          }
        case 64: break;
        case 2: 
          { return symbol(sym.NUMBER, new Integer(Integer.parseInt(yytext())));
          }
        case 65: break;
        case 5: 
          { ++yyline;
          }
        case 66: break;
        case 21: 
          { 
          }
        case 67: break;
        case 13: 
          { return symbol(sym.LBRACK);
          }
        case 68: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
