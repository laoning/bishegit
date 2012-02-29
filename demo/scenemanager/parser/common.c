#include "parser.h"

extern FILE *yyin;

void push(f)
    FileInfo f;
{
    if (stackp >= &stack[NSTACK])
        execerror("stack overflow", (char *) 0);
    stackp->nfile = f.nfile;
    stackp->line  = f.line;
    stackp++;
}

FileInfo pop()
{
    if (stackp <= stack) 
        execerror("stack underflow", (char *) 0);
    return *--stackp;
}

void open_input_file()
{
    FILE *file;
    pathname = malloc(strlen(dname) + strlen(bname) + 2);
    if (!pathname) {
        fprintf(stderr, "could not allocate memory for pathname\n");
        exit(1);
    }
    pathname = strcpy(pathname, dname);
    pathname = strcat(pathname, "/");
    pathname = strcat(pathname, bname);
    
    file = fopen(pathname, "r");
    if (!file) {
        fprintf(stderr, "could not open %s\n", pathname);
        exit(1);
    }
    yyin = file;
    free(pathname);
}

