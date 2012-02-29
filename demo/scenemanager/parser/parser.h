#ifndef _PARSER_H_
#define _PARSER_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <setjmp.h>

char    *dirc, *basec, *bname, *dname;
char	*progname;
char *pathname;
long lineno;
jmp_buf	begin;

#define NSTACK 256
typedef struct FileInfo {
    char *nfile;
    long line;
    } FileInfo;

FileInfo stack[NSTACK];
FileInfo *stackp;

#ifdef __cplusplus
extern "C" {
#endif
    void parser_main(int,char*[]);
    void incl(char *);
#ifdef __cplusplus
}
#endif

#endif
