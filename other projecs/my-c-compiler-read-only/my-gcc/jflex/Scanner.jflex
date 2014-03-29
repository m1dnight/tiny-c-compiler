package MyGCC;
import java_cup.runtime.SymbolFactory;
import java_cup.runtime.*;
import java.util.ArrayList;
%%

%cup
%line
%column
%class Scanner
%{
  public StringBuilder tmpString;
  public static ArrayList<String> errors = new ArrayList<String>();
  private SymbolFactory sf;
  
  public Scanner(java.io.InputStream r, SymbolFactory sf){
    this(r);
    this.sf=sf;
  }

  public int yyline(){
    return yyline + 1;
  }

  public int yycolumn(){
    return yycolumn;
  }
  
  public static ArrayList<String> getErrors(){
    return Scanner.errors;
  }
  
%}

%eofval{
  return sf.newSymbol("EOF",sym.EOF);
%eofval}

ALPHA = [a-zA-Z_]
ALPHA_NUM = {ALPHA}|[0-9]
IDENT = {ALPHA}({ALPHA_NUM})*

NEWLINE = \n | \u2028 | \u2029 | \u000B | \u000C | \u0085
SPACING = [ \t\r\f] | {NEWLINE}

%xstates MULTI_COMMENT, MONO_COMMENT, STRING

%%

<MONO_COMMENT> {
  {NEWLINE}         {yybegin(YYINITIAL);}
  .                 {}
}


<MULTI_COMMENT>{ 
  \*\/              {yybegin(YYINITIAL);}
  . | {NEWLINE}     {}
}

<STRING> {
  \"                {yybegin(YYINITIAL);
                     tmpString.append('"');
                     return sf.newSymbol("String",sym.STRING, tmpString.toString());}
  . | \\\"          {tmpString.append(yytext());}
}


<YYINITIAL>{

  \/\/              {yybegin(MONO_COMMENT);}
  \/\*              {yybegin(MULTI_COMMENT);}

  "=="              {return sf.newSymbol("Equals",sym.EQUALS); }
  "!="              {return sf.newSymbol("Different",sym.DIFF); }
  "<="              {return sf.newSymbol("Less or equal",sym.LEQL); }
  ">="              {return sf.newSymbol("Greater or equal",sym.GEQL); }
  "|"               {return sf.newSymbol("Binary Or",sym.OR); }
  "&"               {return sf.newSymbol("Binary And",sym.AND); }

  ","               {return sf.newSymbol("Comma",sym.COMMA); }
  ";"               {return sf.newSymbol("Semicolon",sym.SEMI); }
  "+"               {return sf.newSymbol("Plus",sym.PLUS); }
  "*"               {return sf.newSymbol("Times",sym.TIMES); }
  "-"               {return sf.newSymbol("Minus",sym.MINUS); }
  "/"               {return sf.newSymbol("Divided",sym.DIV); }
  "%"               {return sf.newSymbol("Modulo",sym.MOD); }
  "="               {return sf.newSymbol("Affectation", sym.EQL); }
  "!"               {return sf.newSymbol("Not",sym.NOT); }
  "<"               {return sf.newSymbol("Less",sym.LESS); }
  ">"               {return sf.newSymbol("Greater",sym.GREATER); }

  "("               {return sf.newSymbol("Left Parenthesis",sym.LPAREN); }
  ")"               {return sf.newSymbol("Right Parenthesis",sym.RPAREN); }
  "["               {return sf.newSymbol("Left Hook",sym.LHOOK); }
  "]"               {return sf.newSymbol("Right Hook",sym.RHOOK); }
  "{"               {return sf.newSymbol("Left Bracket",sym.LBRACKET); }
  "}"               {return sf.newSymbol("Right Bracket",sym.RBRACKET); }

  "if"              {return sf.newSymbol("If statement",sym.IF); }
  "else"            {return sf.newSymbol("Else statement",sym.ELSE); }
  "while"           {return sf.newSymbol("While statement",sym.WHILE); }
  "int"             {return sf.newSymbol("Int type", sym.INT); }
  "void"            {return sf.newSymbol("Void type", sym.VOID); }
  "static"          {return sf.newSymbol("Static type", sym.STATIC); }
  "exit"            {return sf.newSymbol("Exit instruction", sym.EXIT); }
  "return"          {return sf.newSymbol("Return instruction", sym.RETURN); }
  "read_int"        {return sf.newSymbol("Read_int instruction", sym.READ_INT); }
  "printf"          {return sf.newSymbol("Printf", sym.PRINTF); }

  {IDENT}           {return sf.newSymbol("Identificator",
                                         sym.IDENT,
                                         new String(yytext())); }
  [0-9]+            {return sf.newSymbol("Integral Number",
                                         sym.NB_INT,
                                         new Integer(yytext())); }
                                 
  \"                {tmpString = new StringBuilder();
                     tmpString.append('"');
                     yybegin(STRING);}     

  {SPACING}         {/* ignore white space. */ }
  .                 {Scanner.errors.add(new String(":"
                                                   + yyline()
                                                   + ":"
                                                   + yycolumn()
                                                   + ": error: illegal character: "
                                                   + yytext())); }
}
