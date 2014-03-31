package Jflex;
import java_cup.runtime.*;
import java.io.FileInputStream;
import java.io.InputStream;
import Cup.*;



%%
%class Lexer
%public
%line
%column
%unicode
%cup

%eofval{
  System.out.println("Scanner reached EOF.");
  return new Symbol(sym.EOF);
%eofval}

%{
  
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }

  private Symbol symbol(int type, Object val) {
    return new Symbol(type, yyline, yycolumn, val);
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
"int"                   { return symbol(sym.INT);}
"char"                  { return symbol(sym.CHAR);}
"return"                { return symbol(sym.RETURN);}
"if"					{ return symbol(sym.IF); }
"else"					{ return symbol(sym.ELSE); }
"while"					{ return symbol(sym.WHILE); }
"do"					{ return symbol(sym.DO); }
"length"				{ return symbol(sym.LENGTH); }
"write"				    { return symbol(sym.WRITE); }
"read"				    { return symbol(sym.READ); }

","						{ return symbol(sym.COMMA); }
";"						{ return symbol(sym.SEMICOLON); }

"+"						{ return symbol(sym.PLUS); }
"-"						{ return symbol(sym.MINUS); }
"*"						{ return symbol(sym.TIMES); }
"/"						{ return symbol(sym.DIVIDE); }
"("						{ return symbol(sym.LPAR); }
")"						{ return symbol(sym.RPAR); }
"["						{ return symbol(sym.LBRACK); }
"]"						{ return symbol(sym.RBRACK); }
"{"						{ return symbol(sym.LBRACE); }
"}"						{ return symbol(sym.RBRACE); }


">"						{ return symbol(sym.GREATER); }
"<"						{ return symbol(sym.LESS); }
"!="					{ return symbol(sym.NEQUAL); }
"=="					{ return symbol(sym.EQUAL); }

"!"						{ return symbol(sym.NOT); }
"="						{ return symbol(sym.ASSIGN); }

{identifier}			{ System.out.println("NAME: " + yytext()); return symbol(sym.NAME, yytext()); 	}
{digit}+				{ System.out.println("NUMBER: " + Integer.parseInt(yytext())); return symbol(sym.NUMBER, new Integer(Integer.parseInt(yytext()))); }

{commment}				{ /* yyline += countLines(yytext()); */}

[\n]					{ ++yyline; }
[\r\t\f\ ]+				{  }
.						{ System.err.println("unexpected char " + yytext() + " !\n"); System.exit(0); }

