
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Sat Mar 29 16:01:45 CET 2014
//----------------------------------------------------

import java_cup.runtime.*;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Sat Mar 29 16:01:45 CET 2014
  */
public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\060\000\002\002\004\000\002\002\004\000\002\002" +
    "\002\000\002\003\003\000\002\003\003\000\002\004\010" +
    "\000\002\005\003\000\002\005\002\000\002\006\005\000" +
    "\002\006\003\000\002\007\004\000\002\010\006\000\002" +
    "\011\005\000\002\012\004\000\002\012\002\000\002\013" +
    "\003\000\002\013\003\000\002\014\005\000\002\014\002" +
    "\000\002\015\007\000\002\015\011\000\002\015\007\000" +
    "\002\015\005\000\002\015\004\000\002\015\003\000\002" +
    "\015\004\000\002\015\004\000\002\015\003\000\002\016" +
    "\003\000\002\016\006\000\002\017\005\000\002\017\005" +
    "\000\002\017\005\000\002\017\005\000\002\017\005\000" +
    "\002\017\005\000\002\017\005\000\002\017\005\000\002" +
    "\017\005\000\002\017\006\000\002\017\004\000\002\017" +
    "\003\000\002\017\006\000\002\017\003\000\002\017\003" +
    "\000\002\020\003\000\002\020\005\000\002\020\002" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\133\000\010\002\uffff\004\uffff\025\uffff\001\002\000" +
    "\010\002\010\004\011\025\013\001\002\000\010\002\ufffe" +
    "\004\ufffe\025\ufffe\001\002\000\010\002\ufffd\004\ufffd\025" +
    "\ufffd\001\002\000\004\046\014\001\002\000\004\002\000" +
    "\001\002\000\004\046\ufff1\001\002\000\010\002\001\004" +
    "\001\025\001\001\002\000\004\046\ufff2\001\002\000\006" +
    "\030\016\040\015\001\002\000\030\002\ufff5\004\ufff5\016" +
    "\ufff5\017\ufff5\021\ufff5\025\ufff5\026\ufff5\032\ufff5\033\ufff5" +
    "\036\ufff5\046\ufff5\001\002\000\010\004\011\025\013\031" +
    "\ufffa\001\002\000\004\046\135\001\002\000\006\031\ufff8" +
    "\041\133\001\002\000\004\031\023\001\002\000\004\031" +
    "\ufffb\001\002\000\004\032\024\001\002\000\026\004\011" +
    "\016\ufff3\017\ufff3\021\ufff3\025\013\026\ufff3\032\ufff3\033" +
    "\ufff3\036\ufff3\046\ufff3\001\002\000\010\002\ufffc\004\ufffc" +
    "\025\ufffc\001\002\000\022\016\042\017\033\021\034\026" +
    "\037\032\024\033\uffef\036\043\046\044\001\002\000\004" +
    "\046\032\001\002\000\026\004\011\016\ufff3\017\ufff3\021" +
    "\ufff3\025\013\026\ufff3\032\ufff3\033\ufff3\036\ufff3\046\ufff3" +
    "\001\002\000\022\016\ufff4\017\ufff4\021\ufff4\026\ufff4\032" +
    "\ufff4\033\ufff4\036\ufff4\046\ufff4\001\002\000\004\040\015" +
    "\001\002\000\004\046\044\001\002\000\004\030\126\001" +
    "\002\000\012\027\uffe6\034\121\037\122\040\uffe6\001\002" +
    "\000\006\027\uffe9\040\uffe9\001\002\000\004\030\113\001" +
    "\002\000\004\033\112\001\002\000\004\040\110\001\002" +
    "\000\014\013\050\023\045\030\046\045\047\046\051\001" +
    "\002\000\014\013\050\023\045\030\046\045\047\046\051" +
    "\001\002\000\012\027\uffe5\034\uffe5\037\uffe5\040\uffe5\001" +
    "\002\000\036\005\uffd6\006\uffd6\007\uffd6\010\uffd6\012\uffd6" +
    "\013\uffd6\014\uffd6\015\uffd6\027\uffd6\031\uffd6\034\uffd6\035" +
    "\uffd6\040\uffd6\041\uffd6\001\002\000\014\013\050\023\045" +
    "\030\046\045\047\046\051\001\002\000\036\005\uffd8\006" +
    "\uffd8\007\uffd8\010\uffd8\012\uffd8\013\uffd8\014\uffd8\015\uffd8" +
    "\027\uffd8\031\uffd8\034\uffd8\035\uffd8\040\uffd8\041\uffd8\001" +
    "\002\000\014\013\050\023\045\030\046\045\047\046\051" +
    "\001\002\000\040\005\uffd5\006\uffd5\007\uffd5\010\uffd5\012" +
    "\uffd5\013\uffd5\014\uffd5\015\uffd5\027\uffd5\030\076\031\uffd5" +
    "\034\uffd5\035\uffd5\040\uffd5\041\uffd5\001\002\000\030\005" +
    "\056\006\057\007\055\010\061\012\062\013\053\014\054" +
    "\015\060\027\uffea\034\063\040\uffea\001\002\000\014\013" +
    "\050\023\045\030\046\045\047\046\051\001\002\000\014" +
    "\013\050\023\045\030\046\045\047\046\051\001\002\000" +
    "\014\013\050\023\045\030\046\045\047\046\051\001\002" +
    "\000\014\013\050\023\045\030\046\045\047\046\051\001" +
    "\002\000\014\013\050\023\045\030\046\045\047\046\051" +
    "\001\002\000\014\013\050\023\045\030\046\045\047\046" +
    "\051\001\002\000\014\013\050\023\045\030\046\045\047" +
    "\046\051\001\002\000\014\013\050\023\045\030\046\045" +
    "\047\046\051\001\002\000\014\013\050\023\045\030\046" +
    "\045\047\046\051\001\002\000\026\005\056\006\057\007" +
    "\055\010\061\012\062\013\053\014\054\015\060\034\063" +
    "\035\065\001\002\000\036\005\uffda\006\uffda\007\uffda\010" +
    "\uffda\012\uffda\013\uffda\014\uffda\015\uffda\027\uffda\031\uffda" +
    "\034\uffda\035\uffda\040\uffda\041\uffda\001\002\000\036\005" +
    "\uffdd\006\uffdd\007\uffdd\010\uffdd\012\uffdd\013\uffdd\014\uffdd" +
    "\015\uffdd\027\uffdd\031\uffdd\034\063\035\uffdd\040\uffdd\041" +
    "\uffdd\001\002\000\036\005\uffe1\006\uffe1\007\uffe1\010\uffe1" +
    "\012\062\013\053\014\054\015\060\027\uffe1\031\uffe1\034" +
    "\063\035\uffe1\040\uffe1\041\uffe1\001\002\000\036\005\uffe3" +
    "\006\uffe3\007\uffe3\010\uffe3\012\062\013\uffe3\014\054\015" +
    "\uffe3\027\uffe3\031\uffe3\034\063\035\uffe3\040\uffe3\041\uffe3" +
    "\001\002\000\036\005\uffe0\006\uffe0\007\uffe0\010\uffe0\012" +
    "\062\013\053\014\054\015\060\027\uffe0\031\uffe0\034\063" +
    "\035\uffe0\040\uffe0\041\uffe0\001\002\000\036\005\uffdf\006" +
    "\uffdf\007\uffdf\010\uffdf\012\062\013\053\014\054\015\060" +
    "\027\uffdf\031\uffdf\034\063\035\uffdf\040\uffdf\041\uffdf\001" +
    "\002\000\036\005\uffe2\006\uffe2\007\uffe2\010\uffe2\012\062" +
    "\013\053\014\054\015\060\027\uffe2\031\uffe2\034\063\035" +
    "\uffe2\040\uffe2\041\uffe2\001\002\000\036\005\uffdc\006\uffdc" +
    "\007\uffdc\010\uffdc\012\uffdc\013\uffdc\014\uffdc\015\uffdc\027" +
    "\uffdc\031\uffdc\034\063\035\uffdc\040\uffdc\041\uffdc\001\002" +
    "\000\036\005\uffde\006\uffde\007\uffde\010\uffde\012\062\013" +
    "\uffde\014\054\015\uffde\027\uffde\031\uffde\034\063\035\uffde" +
    "\040\uffde\041\uffde\001\002\000\016\013\050\023\045\030" +
    "\046\031\uffd2\045\047\046\051\001\002\000\004\031\103" +
    "\001\002\000\030\005\056\006\057\007\055\010\061\012" +
    "\062\013\053\014\054\015\060\031\uffd4\034\063\041\101" +
    "\001\002\000\016\013\050\023\045\030\046\031\uffd2\045" +
    "\047\046\051\001\002\000\004\031\uffd3\001\002\000\036" +
    "\005\uffd7\006\uffd7\007\uffd7\010\uffd7\012\uffd7\013\uffd7\014" +
    "\uffd7\015\uffd7\027\uffd7\031\uffd7\034\uffd7\035\uffd7\040\uffd7" +
    "\041\uffd7\001\002\000\036\005\uffd9\006\uffd9\007\uffd9\010" +
    "\uffd9\012\uffd9\013\uffd9\014\uffd9\015\uffd9\027\uffd9\031\uffd9" +
    "\034\063\035\uffd9\040\uffd9\041\uffd9\001\002\000\026\005" +
    "\056\006\057\007\055\010\061\012\062\013\053\014\054" +
    "\015\060\031\106\034\063\001\002\000\036\005\uffdb\006" +
    "\uffdb\007\uffdb\010\uffdb\012\uffdb\013\uffdb\014\uffdb\015\uffdb" +
    "\027\uffdb\031\uffdb\034\uffdb\035\uffdb\040\uffdb\041\uffdb\001" +
    "\002\000\030\005\056\006\057\007\055\010\061\012\062" +
    "\013\053\014\054\015\060\027\uffe8\034\063\040\uffe8\001" +
    "\002\000\022\016\042\017\033\021\034\026\037\032\024" +
    "\033\uffef\036\043\046\044\001\002\000\004\033\ufff0\001" +
    "\002\000\014\002\ufff6\004\ufff6\025\ufff6\027\ufff6\040\ufff6" +
    "\001\002\000\014\013\050\023\045\030\046\045\047\046" +
    "\051\001\002\000\026\005\056\006\057\007\055\010\061" +
    "\012\062\013\053\014\054\015\060\031\115\034\063\001" +
    "\002\000\020\016\042\017\033\021\034\026\037\032\024" +
    "\036\043\046\044\001\002\000\006\027\117\040\uffee\001" +
    "\002\000\020\016\042\017\033\021\034\026\037\032\024" +
    "\036\043\046\044\001\002\000\006\027\uffed\040\uffed\001" +
    "\002\000\014\013\050\023\045\030\046\045\047\046\051" +
    "\001\002\000\014\013\050\023\045\030\046\045\047\046" +
    "\051\001\002\000\030\005\056\006\057\007\055\010\061" +
    "\012\062\013\053\014\054\015\060\027\uffeb\034\063\040" +
    "\uffeb\001\002\000\026\005\056\006\057\007\055\010\061" +
    "\012\062\013\053\014\054\015\060\034\063\035\125\001" +
    "\002\000\012\027\uffe4\034\uffe4\037\uffe4\040\uffe4\001\002" +
    "\000\014\013\050\023\045\030\046\045\047\046\051\001" +
    "\002\000\026\005\056\006\057\007\055\010\061\012\062" +
    "\013\053\014\054\015\060\031\130\034\063\001\002\000" +
    "\020\016\042\017\033\021\034\026\037\032\024\036\043" +
    "\046\044\001\002\000\006\027\uffec\040\uffec\001\002\000" +
    "\010\027\uffe7\034\121\040\uffe7\001\002\000\006\004\011" +
    "\025\013\001\002\000\004\031\ufff9\001\002\000\006\031" +
    "\ufff7\041\ufff7\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\133\000\004\002\003\001\001\000\012\003\011\004" +
    "\004\011\005\013\006\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\012\005\020\006\021\007\017\013" +
    "\016\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\010\024\001\001\000" +
    "\010\011\027\012\025\013\026\001\001\000\002\001\001" +
    "\000\012\010\035\014\037\015\040\016\034\001\001\000" +
    "\002\001\001\000\010\011\027\012\030\013\026\001\001" +
    "\000\002\001\001\000\002\001\001\000\004\016\131\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\017\106\001\001\000\004\017\051\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\017\104\001\001\000" +
    "\002\001\001\000\004\017\103\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\017\074\001\001\000\004\017" +
    "\073\001\001\000\004\017\072\001\001\000\004\017\071" +
    "\001\001\000\004\017\070\001\001\000\004\017\067\001" +
    "\001\000\004\017\066\001\001\000\004\017\065\001\001" +
    "\000\004\017\063\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\006\017\077\020\076" +
    "\001\001\000\002\001\001\000\002\001\001\000\006\017" +
    "\077\020\101\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\012\010\035\014\110\015\040\016\034" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\017" +
    "\113\001\001\000\002\001\001\000\010\010\035\015\115" +
    "\016\034\001\001\000\002\001\001\000\010\010\035\015" +
    "\117\016\034\001\001\000\002\001\001\000\004\017\123" +
    "\001\001\000\004\017\122\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\017\126\001\001" +
    "\000\002\001\001\000\010\010\035\015\130\016\034\001" +
    "\001\000\002\001\001\000\002\001\001\000\010\006\133" +
    "\007\017\013\016\001\001\000\002\001\001\000\002\001" +
    "\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    
    /* Change the method report_error so it will display the line and
       column of where the error occurred in the input as well as the
       reason for the error which is passed into the method in the
       String 'message'. */
    public void report_error(String message, Object info) {
   
        /* Create a StringBuffer called 'm' with the string 'Error' in it. */
        StringBuffer m = new StringBuffer("Error");
   
        /* Check if the information passed to the method is the same
           type as the type java_cup.runtime.Symbol. */
        if (info instanceof java_cup.runtime.Symbol) {
            /* Declare a java_cup.runtime.Symbol object 's' with the
               information in the object info that is being typecasted
               as a java_cup.runtime.Symbol object. */
            java_cup.runtime.Symbol s = ((java_cup.runtime.Symbol) info);
   
            /* Check if the line number in the input is greater or
               equal to zero. */
            if (s.left >= 0) {                
                /* Add to the end of the StringBuffer error message
                   the line number of the error in the input. */
                m.append(" in line "+(s.left+1));
                /* Check if the column number in the input is greater
                   or equal to zero. */
                if (s.right >= 0)                    
                    /* Add to the end of the StringBuffer error message
                       the column number of the error in the input. */
                    m.append(", column "+(s.right+1));
            }
        }
   
        /* Add to the end of the StringBuffer error message created in
           this method the message that was passed into this method. */
        m.append(" : "+message);
   
        /* Print the contents of the StringBuffer 'm', which contains
           an error message, out on a line. */
        System.err.println(m);
    }
   
    /* Change the method report_fatal_error so when it reports a fatal
       error it will display the line and column number of where the
       fatal error occurred in the input as well as the reason for the
       fatal error which is passed into the method in the object
       'message' and then exit.*/
    public void report_fatal_error(String message, Object info) {
        report_error(message, info);
        System.exit(1);
    }

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$parser$actions {
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 47: // pars ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("pars",14, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 46: // pars ::= exp COMMA pars 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("pars",14, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 45: // pars ::= exp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("pars",14, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 44: // exp ::= NAME 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 43: // exp ::= QCHAR 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 42: // exp ::= NAME LPAR pars RPAR 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 41: // exp ::= NUMBER 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 40: // exp ::= MINUS exp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 39: // exp ::= exp LBRACK exp RBRACK 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 38: // exp ::= LPAR exp RPAR 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 37: // exp ::= exp DIVIDE exp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 36: // exp ::= exp TIMES exp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35: // exp ::= exp MINUS exp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34: // exp ::= exp NEQUAL exp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33: // exp ::= exp EQUAL exp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32: // exp ::= exp LESS exp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // exp ::= exp GREATER exp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // exp ::= exp PLUS exp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",13, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // lexp ::= lexp LBRACK exp RBRACK 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("lexp",12, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // lexp ::= NAME 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("lexp",12, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // statement ::= lexp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",11, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // statement ::= READ lexp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // statement ::= WRITE exp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // statement ::= block 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",11, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // statement ::= RETURN exp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // statement ::= lexp ASSIGN exp 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // statement ::= WHILE LPAR exp RPAR statement 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // statement ::= IF LPAR exp RPAR statement ELSE statement 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // statement ::= IF LPAR exp RPAR statement 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // statements ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statements",10, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // statements ::= statement SEMICOLON statements 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statements",10, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // type ::= CHAR 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("type",9, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // type ::= INT 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("type",9, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // var_declarations ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("var_declarations",8, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // var_declarations ::= var_declaration var_declarations 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("var_declarations",8, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // var_declaration ::= type NAME SEMICOLON 
            {
              Object RESULT =null;
		 System.out.println("Found a name!"); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("var_declaration",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // block ::= LBRACE var_declarations statements RBRACE 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("block",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // formal_par ::= type NAME 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("formal_par",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // formal_params_non_empty ::= formal_par 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("formal_params_non_empty",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // formal_params_non_empty ::= formal_par COMMA formal_params_non_empty 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("formal_params_non_empty",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // formal_pars ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("formal_pars",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // formal_pars ::= formal_params_non_empty 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("formal_pars",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // fun_declaration ::= type NAME LPAR formal_pars RPAR block 
            {
              Object RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).right;
		String n = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-4)).value;
		 System.out.println("Function declaration. \n * Name: " + n + "\n * Params: "); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("fun_declaration",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // declaration ::= var_declaration 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("declaration",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // declaration ::= fun_declaration 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("declaration",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // program ::= 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("program",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= program EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // program ::= program declaration 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("program",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

