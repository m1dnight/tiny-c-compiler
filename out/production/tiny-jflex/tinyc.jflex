package miny_pascal;

import java_cup.runtime.Symbol;
import java.io.FileInputStream;
import java.io.InputStream;



%%
%cup
%line

%{

	public static void main(String args[]) throws Exception {
		InputStream is = new FileInputStream(args[0]);
		Yylex lexer = new Yylex(is);

		Symbol token = null;
		do {
			token = lexer.next_token();
			System.out.println(token == null ? "EOF" : token.toString());
		} while (token != null);
	}
	
	private int countLines(String str){
		int count = 0;
		for(int i = 0; i < str.length(); ++i){
			if(str.charAt(i) == '\n'){
				++count;
			}
		}
		return count;
	}
%}

DIGIT  =  [0-9]
alpha =  [a-zA-Z_]
IDE    =  {LETTER}({LETTER}|{DIGIT})*
INT    =  {DIGIT}+
SCALE  =  E("+"|"-")?{INT}
REAL   =  ({INT})?"."{INT}({SCALE})?
STRING =  \"(.|[^\"])*\"
COMMENT=  "(*"(.|[^\"])*\*\)

%%
"int"                   { return new Symbol(sym.INTEGER);}
"char"                  { return new Symbol(sym.CHAR);}
"return"                { return new Symbol(sym.RETURN);}
"if"						{ return new Symbol(sym.IF); }


"FOR"						{ return new Symbol(sym.FOR); }
"FROM"					{ return new Symbol(sym.FROM); }
"BY"						{ return new Symbol(sym.BY); }
"TO"						{ return new Symbol(sym.TO); }
"FIN"						{ return new Symbol(sym.FIN); }
"NEW"						{ return new Symbol(sym.NEW);}
"IDENTICAL"				{ return new Symbol(sym.IDENTICAL); }
"ELSE"					{ return new Symbol(sym.ELSE); }
"END"						{ return new Symbol(sym.END); }
"FALSE"					{ return new Symbol(sym.FALSE); }
"GOTO"					{ return new Symbol(sym.GOTO); }

"FI"						{ return new Symbol(sym.FI); }
"CASE"					{ return new Symbol(sym.CASE); }
"FIXED"					{ return new Symbol(sym.INTEGER); }
"LABEL"					{ return new Symbol(sym.LABEL); }
"NOT"						{ return new Symbol(sym.NOT); }
"OF"						{ return new Symbol(sym.OF); }
"PROCEDURE"				{ return new Symbol(sym.PROCEDURE); }
"FUNCTION"				{ return new Symbol(sym.FUNCTION ); }
"PROGRAM"					{ return new Symbol(sym.PROGRAM); }
"READ"					{ return new Symbol(sym.READ); }
"FLOAT"					{ return new Symbol(sym.REAL); }
"REPEAT"					{ return new Symbol(sym.REPEAT); }
"THEN"					{ return new Symbol(sym.THEN); }
"TRUE"					{ return new Symbol(sym.TRUE); }
"DO"						{ return new Symbol(sym.DO); }
"OD"						{ return new Symbol(sym.OD); }
"WHILE"					{ return new Symbol(sym.WHILE); }
"WRITE"					{ return new Symbol(sym.WRITE); }
{IDE}					{ return new Symbol(sym.IDE, yytext()); 	}
{INT}					{ return new Symbol(sym.INTCONST, new Integer(Integer.parseInt(yytext()))); }
{REAL}					{ return new Symbol(sym.REALCONST, new Double(Double.parseDouble(yytext()))); }
{STRING}				{ return new Symbol(sym.STRING, yytext()); }
{COMMENT}				{ yyline += countLines(yytext()); }			
"+"						{ return new Symbol(sym.ADD); }
"-"						{ return new Symbol(sym.MIN); }
"*"						{ return new Symbol(sym.MUL); }
"/"						{ return new Symbol(sym.DIV); }
"%"						{ return new Symbol(sym.MOD); }
"<"						{ return new Symbol(sym.LES); }
"<="					{ return new Symbol(sym.LEQ); }
"=="					{ return new Symbol(sym.EQU); }
"/="					{ return new Symbol(sym.NEQ); }
">"						{ return new Symbol(sym.GRE); }
">="					{ return new Symbol(sym.GEQ); }
"&"						{ return new Symbol(sym.AND); }
"|"						{ return new Symbol(sym.OR); }
"="						{ return new Symbol(sym.ASSIGN); }
"{"						{ return new Symbol(sym.LC); }
"}"						{ return new Symbol(sym.RC); }
":"						{ return new Symbol(sym.COLON); }
";"						{ return new Symbol(sym.SEMI); }
"("						{ return new Symbol(sym.LPAR); }
")"						{ return new Symbol(sym.RPAR); }
"["						{ return new Symbol(sym.LPAR_SQ); }
"]"						{ return new Symbol(sym.RPAR_SQ); }
"."						{ return new Symbol(sym.DOT); }
","						{ return new Symbol(sym.COMMA); }
"^"						{ return new Symbol(sym.PTR); }
[\n]					{ ++yyline; }
[\r\t\f\ ]+				{  }
.						{ System.err.println("unexpected char " + yytext() + " !\n"); System.exit(0); }

