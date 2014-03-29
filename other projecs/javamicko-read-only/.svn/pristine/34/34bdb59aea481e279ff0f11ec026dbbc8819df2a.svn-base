/* 
	Author: Aleksandar Milenkovic 56/05
	Rachunarski Fakultet (RAF), Univerzitet Union, 2008
	Prerada mikro c kompajlera u Javu
	
	SVN: http://code.google.com/p/javamicko/
	
   JFlex lexer specifikacija za MiCKo
  
   JFlex - The Fast Scanner Generator for Java

*/

import main.Constants;
import java_cup.runtime.Symbol;

%%

%class Lexer
%cup
%implements sym, Constants
%line
%column

%xstate STRING


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

letter = [_a-zA-Z]
digit = [0-9]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent = ( [^*] | \*+ [^/*] )*

%%

<YYINITIAL>
{
/* keywords */
[ \t]+                      {   /* skip */    }

"int"                       {   return symbol( _TYPE, var_type.SIGNED_TYPE );   }
"unsigned"                  {   return symbol( _TYPE, var_type.UNSIGNED_TYPE ); }
"float"						{	return symbol( _TYPE, var_type.FLOAT_TYPE );	}
"void"						{	return symbol( _TYPE, var_type.VOID_TYPE );		}
"if"                        {   return symbol(_IF);									}
"else"                      {   return symbol(_ELSE);	                            }
"while"                     {   return symbol(_WHILE);		                        }
"return"                    {   return symbol(_RETURN);		                        }
"switch"					{	return symbol(_SWITCH);		    					}
"break"						{	return symbol(_BREAK);								}
"case"						{	return symbol(_CASE);								}
"default"					{	return symbol(_DEFAULT);							}	


/* names */
{letter}({letter}|{digit})* { return symbol(_ID, yytext()); }                       

{digit}{1,10}[uU]           {
                                String s = yytext();
                                //skini u sa kraja
                                return symbol( _UNSIGNED_NUMBER, s.substring(0, s.length()-1));  
                            }

[\-\+]?{digit}{1,10}        {	return symbol( _SIGNED_NUMBER, yytext());			}
[\-\+]?{digit}?\.{digit}*	{	return symbol( _FLOAT_NUMBER, yytext());			}

"("							{	return symbol(_LPAREN);								}
")"							{	return symbol(_RPAREN);								}
","							{	return symbol(_COMMA);								}
"{"							{	return symbol(_LBRACKET);							}
"}"							{	return symbol(_RBRACKET);							}
"="							{	return symbol(_ASSIGN, new Integer(PLAIN));			}
"+="						{	return symbol(_ASSIGN, new Integer(ADD_EQ);			}
"*="						{	return symbol(_ASSIGN, new Integer(MUL_EQ);			}
"-="						{	return symbol(_ASSIGN, new Integer(SUB_EQ);			}
"/="						{	return symbol(_ASSIGN, new Integer(DIV_EQ);			}
";"							{	return symbol(_SEMICOLON);							}
":"							{	return symbol(_COLON);								}
"+"                         {	return symbol(_PLUS);								}
"-"                         {	return symbol(_MINUS);								}
"*"                         {	return symbol(_MULOP, new Integer(TIMES));			}
"/"                         {	return symbol(_MULOP, new Integer(DIV));			}
"||"                        {	return symbol(_OR);									}
"&&"                        {	return symbol(_AND);								}
"<"                         {	return symbol(_RELOP, new Integer(LT));				}
">"                         {	return symbol(_RELOP, new Integer(GT));				}
"<="                        {	return symbol(_RELOP, new Integer(LE));				}
">="                        {	return symbol(_RELOP, new Integer(GE));				}
"=="                        {	return symbol(_RELOP, new Integer(EQ));				}
"!="                        {	return symbol(_RELOP, new Integer(NE));				}
"++"						{	return symbol(_INC);								}
"--"						{	return symbol(_DEC);								}
\" 							{ 	string.setLength(0);  yybegin(STRING); 				}

Comment						{			/* comments */								}	
\/\/.*                      {			/* skip */									}

.                           {	error("LEXICAL ERROR: " + yytext());				}




{white_space}     { /* ignore */ }
}
                  
<STRING> 
{
\" 							{ 	yybegin(YYINITIAL);
								return symbol(sym.STRING_LITERAL, string.toString()); 
																					}
																					
[^\n\r\"\\]+ 				{ 	string.append( yytext() ); 							}
\\t 						{ 	string.append(’\t’); 								}
\\n 						{ 	string.append(’\n’); 								}
\\r 						{ 	string.append(’\r’); 								}
\\\" 						{ 	string.append(’\"’); 								}
\\ 							{ 	string.append(’\\’); 								}
}

/* error fallback */
.|\n 						{ throw new Error("Illegal character <"+yytext()+">"); 	}