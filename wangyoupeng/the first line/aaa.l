%{
#include "y.tab.h"
#include "aaa.h"
%}

numeral     -?(([0-9]+)|([0-9]*\.[0-9]+)([eE][-+]?[0-9]+)?)
eol         \n
blankline   ^[ \t\r]*\n 
cr          \r

%%

{cr}                /* ignore the Carriage Return */
^b[ \t]             { return B; }

{numeral}           { yylval.string=strdup(yytext); return NUMERAL; }
{blankline}         { lineno++; }
{eol}               { lineno++; return EOL; }

%%

main(argc, argv)
int argc;
char *argv[];
{
lineno = 1;
yyparse();
/* yylex(); */
}
yywrap()
{
return 1;
}

