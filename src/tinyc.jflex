import java_cup.runtime.Symbol;
import java.io.FileInputStream;
import java.io.InputStream;



%%
%class Lexer
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

digit       =  [0-9]
alpha       =  [a-zA-Z_]
alphanum    =  [A-Za-z0-9]
symbol      =  [_]
identifier  =  {alpha}+({alphanum}|{symbol})*

sl_comment  =  "//".*
ml_comment  =  "/*"((.*?)|[\n]*)*"*/"
commment    =  {sl_comment} | {ml_comment}

%%
"int"                   { return new Symbol(sym.INTEGER);}
"char"                  { return new Symbol(sym.CHAR);}
"return"                { return new Symbol(sym.RETURN);}
"if"					{ return new Symbol(sym.IF); }
"else"					{ return new Symbol(sym.ELSE); }
"while"					{ return new Symbol(sym.WHILE); }
"do"					{ return new Symbol(sym.DO); }
"length"				{ return new Symbol(sym.LENGTH); }
"write"				    { return new Symbol(sym.WRITE); }
"read"				    { return new Symbol(sym.READ); }

","						{ return new Symbol(sym.COMMA); }
";"						{ return new Symbol(sym.SEMICOLON); }

"+"						{ return new Symbol(sym.ADD); }
"-"						{ return new Symbol(sym.MIN); }
"*"						{ return new Symbol(sym.MUL); }
"/"						{ return new Symbol(sym.DIV); }
"("						{ return new Symbol(sym.LPAR); }
")"						{ return new Symbol(sym.RPAR); }
"["						{ return new Symbol(sym.LRBACK); }
"]"						{ return new Symbol(sym.RBACK); }
"{"						{ return new Symbol(sym.LBRACE); }
"}"						{ return new Symbol(sym.RBRACE); }


">"						{ return new Symbol(sym.GREATER); }
"<"						{ return new Symbol(sym.LESS); }
"!="					{ return new Symbol(sym.NEQ); }
"=="					{ return new Symbol(sym.EQU); }

"!"						{ return new Symbol(sym.NOT); }
"="						{ return new Symbol(sym.ASSIGN); }

{identifier}			{ return new Symbol(sym.NAME, yytext()); 	}
{digit}+				{ return new Symbol(sym.NUMBER, new Integer(Integer.parseInt(yytext()))); }

{commment}				{ yyline += countLines(yytext()); }

[\n]					{ ++yyline; }
[\r\t\f\ ]+				{  }
.						{ System.err.println("unexpected char " + yytext() + " !\n"); System.exit(0); }

