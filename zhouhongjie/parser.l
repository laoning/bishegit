%{
#include "y.tab.h"
//#include "parser.h"
//#include "common.h"
char    *current_file;
%}

numeral         -?(([0-9]+)|([0-9]*\.[0-9]+)([eE][-+]?[0-9]+)?)
some_name       [^ \t\r\n\f]+
comment         [#%].*
comment_line    ^[ \t]*[#%].*\n
eol             \n
blankline       ^[ \t\r]*\n
cr              \r

%x      INCLU
%x      DETAIL
%x      FILENAME

%%

{cr}                /* ignore the Carriage Return */
^b[ \t]             { return B; }
^v                  { return V; }
^from[ \t]+	        { return FROM; }
^at[ \t]+   	    { return AT; }
^up[ \t]+		    { return UP; }
^angle[ \t]+	    { return ANGLE; }
^hither[ \t]+	    { return HITHER; }
^resolution[ \t]+	{ return RESOLUSION; }

^am[ \t]+           { return AM; }
^l[ \t]+            { return L; }
^[ \t]*xs[ \t]+           { return XS; }
^[ \t]*x[ \t]+            { return X; }
^k[ \t]+            { return K; }
^a[ \t]+            { return A; }
^[ \t]*fm[ \t]+           { return FM; }
^m                  { return M; }
vertices[ \t]+      { return VERTICES; }
normals[ \t]+       { return NORMALS; }
texturecoords[ \t]+ { return TEXTURECOORDS; }
triangles[ \t]+     { return TRIANGLES; }

transl[ \t]+        { return TRANSL; }
rot[ \t]+           { return ROT; }
scale[ \t]+         { return SCALE; }
visibility[ \t]+    { return VISIBILITY; }

^[ \t]*"{"                { return '{'; }
^[ \t]*"}"                { return '}'; }

^[ \t]*i[ \t]+              { BEGIN INCLU; }
<INCLU>{numeral}[ \t]+      { BEGIN DETAIL; }
<DETAIL>{some_name}         { BEGIN FILENAME; current_file = strdup(yytext); }
<FILENAME>[ \t]*{eol}       { BEGIN 0; incl(current_file); yylval.string = current_file; }

{numeral}       { yylval.string=strdup(yytext); return NUMERAL; }
{some_name}     { yylval.string=strdup(yytext); return SOMENAME; }

{blankline}     { lineno++; }
{eol}           { lineno++; return EOL; }
{comment}       { ; }
{comment_line}  { lineno++; }

.               { ; }

<<EOF>> {
            yypop_buffer_state();

        if ( !YY_CURRENT_BUFFER )
            {
            yyterminate();
            }
        else
            {
            FileInfo f;
            f = pop();
            lineno = f.line;
            bname = f.nfile;
            }
        }

%%

void incl(fn)
    char *fn;
{
    FileInfo f;
    f.line = lineno;
    f.nfile = bname;
    push(f);

    bname = fn;
    open_input_file();
    yypush_buffer_state(yy_create_buffer( yyin, YY_BUF_SIZE ));
    lineno = 1;
}


#ifndef WIN32
    main(argc, argv)
        int argc;
        char *argv[];
    {
        parser_main(argc, argv);
    }
#endif

void parser_main(argc, argv)
    int argc;
	char *argv[];
{
    stackp = stack;
	progname = argv[0];
    pathname = "";

    if ( argc > 2 ) {
        dirc = strdup(argv[1]);
        basec = strdup(argv[2]);

        dname = dirc;
        bname = basec;

        open_input_file();
        lineno = 1;
        setjmp(begin);
        yyparse();
        free(dirc);
        free(basec);
    }
    else {
        printf("Usage: %s <Location of scene files> <Main scene file>\n", progname);
    }
}

yywrap()
{
    return 1;
}
