%{
#include "y.tab.h"
#include "parser.h"
%}

/*
numeral         -?(([0-9]+)|([0-9]*\.[0-9]+)([eE][-+]?[0-9]+)?)
*/

none_blank_str  [^ \t\r\n\f;=]+
quote_str       \"[^"]*\"
comment         [#%].*
comment_line    ^[ \t]*[#%].*\n
eol             \n
blankline       ^[ \t\r]*\n
cr              \r


%%

{cr}                /* ignore the Carriage Return */
=               { return '='; }
;               { return ';'; }
{quote_str}      { yylval.string=strdup(yytext); return VAL ; }
[ \t]+
"Question"                { printf("%s\n","Question!"); return QUESTION ; }
{none_blank_str}          { yylval.string=strdup(yytext); return NBS ; }

{blankline}     { lineno++; }
{eol}           { lineno++; }
{comment}       { ; }
{comment_line}  { lineno++; }
.               { ECHO ; }


%%

void main(argc, argv)
    int argc;
	char *argv[];
{
        lineno = 1;
        setjmp(begin);
        yyparse();
}

yywrap()
{
    return 1;
}
