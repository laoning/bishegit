%{
#include "aaa.h"
%}

%union {
    double  dbval;
    long    intval;
    char    * string;
}


%token V
%token FROM
%token AT
%token UP
%token ANGLE
%token HITHER
%token RESOLUSION
%token B

%token <string> NUMERAL

%token EOL

%type <dbval> dbvalue

%%

element:        /* nothing */
            |   element background_color
            ;
background_color:
                B dbvalue dbvalue dbvalue EOL          {printf("we get the background_color:Br=%g,Bb=%g,Bg=%g \n",$2, $3, $4);}
            ;
dbvalue:        NUMERAL { $$ = atof($1); }
            ;
%%

yyerror (s) /* 语法分析函数 yyparse()出错时调用该函数 */
char *s;
{
}

