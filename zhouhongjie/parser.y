%{
//#include "parser.h"
//#include "inter.h"
%}

%union {
    double  dbval;
    long    intval;
    char    * string;
}


%token VAR
%token QUESTION1
%token VALUE

/*
%token <string> NUMERAL
%token <string> SOMENAME

%token EOL

%type <dbval> dbvalue
%type <intval> intvalue
%type <string> somename
*/

%%

exampaper:        /* nothing */
            |   exampaper sentence
            ;
sentence:	VAR '=' VALUE ';' { printf("%s\n","Get a sentence. ") }
            |   QUESTION QTYPE QLIST
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
