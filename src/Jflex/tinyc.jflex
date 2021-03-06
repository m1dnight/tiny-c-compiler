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
char        =  "\'"[\x20-\x7E]"\'"
identifier  =  {alpha}+({alphanum}|{symbol})*

sl_comment  =  "//".*
ml_comment  =  "/*"((.*?)|[\n]*)*"*/"
commment    =  {sl_comment} | {ml_comment}

%%
"int"                   { return symbol(sym.INT);}
"char"                  { return symbol(sym.CHAR);}
"return"                { return symbol(sym.RETURN);}
"if"					{ return symbol(sym.IF);   }
"else"					{ return symbol(sym.ELSE); }
"while"					{ return symbol(sym.WHILE); }
"do"					{ return symbol(sym.DO); }
"length"				{ return symbol(sym.LENGTH); }
"write"				    { return symbol(sym.WRITE); }
"writeln"				    { return symbol(sym.WRITELN); }
"read"				    { return symbol(sym.READ); }
"inc"                   { return symbol(sym.PLUSPLUS);}

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

{identifier}			{ return symbol(sym.NAME, yytext()); 	}
{digit}+				{ return symbol(sym.NUMBER, new Integer(Integer.parseInt(yytext()))); }
{char}                  { return symbol(sym.CHAR, yytext().substring(1, 2));}
{commment}				{ /* yyline += countLines(yytext()); */}

[\n]					{ }
[\r\t\f\ ]+				{  }
.						{ System.err.println("unexpected char " + yytext() + " !\n"); System.exit(0); }

