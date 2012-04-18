%{
#include "parser.h"
%}

%union {
    double  dbval;
    long    intval;
    char    * string;
}


%token QUESTION
%token <string> VAL
%token <string> NBS
/*
%token <string> NUMERAL
%token <string> SOMENAME

%token EOL

%type <dbval> dbvalue
%type <intval> intvalue
%type <string> somename
*/

%%

exampaper:      sentence
            |   exampaper sentence
            ;
sentence:	NBS '=' VAL ';'             { printf("%s\n","Get a set up value sentence. "); }
            |   QUESTION NBS qlist ';'  { printf("%s\n","It is question. "); }
            ;
qlist:          NBS         { printf("%s\n","Here is a NBS."); }
            |   qlist NBS
            ;
%%



yyerror (s)  /* 语法分析函数yyparse()出错时调用该函数 */
 	char *s;
{

}

warning(s, t)
	char *s, *t;
{
	fprintf(stderr, "%s: %s", progname, s);
	if (t)
		fprintf(stderr, " %s", t);
	fprintf(stderr, " near line %ld\n", lineno);
}

execerror(s, t)	/* recover from run-time error */
	char *s, *t;
{
	warning(s, t);
	longjmp(begin, 0);
}
