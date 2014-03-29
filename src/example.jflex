import java_cup.runtime.*;

%%

%class Lexer
%unicode
%cup
%line
%column

%{
    StringBuffer string = new StringBuffer();

    private Symbol symbol(int type)
    {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]

Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent = ( [^*] | \*+ [^/*] )*

Identifier = [:jletter:] [:jletterdigit:]*

DecIntegerLiteral = 0 | [1-9][0-9]*

%state STRING

%%

<YYINITIAL> "abstract"      {return symbol(sym.ABSTRACT); }
<YYINITIAL> "boolean"       {return symbol(sym.BOOLEAN); }
<YYINITIAL> "break"         {return symbol(sym.BREAK); }

<YYINITIAL> {
    {Identifier}            {return symbol(sym.IDENTIFIER);}

    {DecIntegerLiteral}     {return symbol(sym.INTEGER_LITERAL); }
    \"                      { string.setLength(0); yybegin(STRING); }

    "="                     {return symbol(sym.EQ); }
    "=="                    {return symbol(sym.EQEQ); }
    "+"                     {return symbol(sym.PLUS); }

    {Comment}               {}
    {WhiteSpace}            {}
}

<STRING> {
    \"                      { yybegin(YYINITIAL);
                              return symbol(sym.STRING_LITERAL, string.toString()); }
    [^\n\r\"\\]+            { string.append( yytext() ); }
    \\tt                    {string.append('\t'); }
    \\n                     {string.append('\n');}

    \\r                     {string.append('\r'); }
    \\\"                    {string.append('\"'); }
    \\                      {string.append('\\'); }

}

[^]                         {throw new Error("Illegal char <" + yytext()+">");}


