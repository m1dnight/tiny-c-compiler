import java_cup.runtime.*;
import java.io.FileInputStream;
import java.io.InputStream;



%%
%class Lexer
%cup
%line
%column
%unicode

%{
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int sym) {
    return new Symbol(sym, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int sym, Object val) {
    return new Symbol(sym, yyline+1, yycolumn+1, val);
  }

  private void error(String message) {
    System.out.println("Error at line "+(yyline+1)+", column "+(yycolumn+1)+" : "+message);
  }

%}

digit       =  [0-9]
alpha       =  [a-zA-Z_]
alphanum    =  [A-Za-z0-9]
symbol      =  [_]
identifier  =  {alpha}+({alphanum}|{symbol})*

sl_comment  =  "//".*
ml_comment  =  "/*"((.*?)|[\n]*)*"*/"
commment    =  {sl_comment} | {ml_comment}

%%
"int"                   { return new symbol(sym.INTEGER);}
"char"                  { return new symbol(sym.CHAR);}
"return"                { return new symbol(sym.RETURN);}
"if"					{ return new symbol(sym.IF); }
"else"					{ return new symbol(sym.ELSE); }
"while"					{ return new symbol(sym.WHILE); }
"do"					{ return new symbol(sym.DO); }
"length"				{ return new symbol(sym.LENGTH); }
"write"				    { return new symbol(sym.WRITE); }
"read"				    { return new symbol(sym.READ); }

","						{ return new symbol(sym.COMMA); }
";"						{ return new symbol(sym.SEMICOLON); }

"+"						{ return new symbol(sym.ADD); }
"-"						{ return new symbol(sym.MIN); }
"*"						{ return new symbol(sym.MUL); }
"/"						{ return new symbol(sym.DIV); }
"("						{ return new symbol(sym.LPAR); }
")"						{ return new symbol(sym.RPAR); }
"["						{ return new symbol(sym.LRBACK); }
"]"						{ return new symbol(sym.RBACK); }
"{"						{ return new symbol(sym.LBRACE); }
"}"						{ return new symbol(sym.RBRACE); }


">"						{ return new symbol(sym.GREATER); }
"<"						{ return new symbol(sym.LESS); }
"!="					{ return new symbol(sym.NEQ); }
"=="					{ return new symbol(sym.EQU); }

"!"						{ return new symbol(sym.NOT); }
"="						{ return new symbol(sym.ASSIGN); }

{identifier}			{ return new symbol(sym.NAME, yytext()); 	}
{digit}+				{ return new symbol(sym.NUMBER, new Integer(Integer.parseInt(yytext()))); }

{commment}				{ yyline += countLines(yytext()); }

[\n]					{ ++yyline; }
[\r\t\f\ ]+				{  }
.						{ System.err.println("unexpected char " + yytext() + " !\n"); System.exit(0); }

