#include "mytool1.h"
#include <stdio.h>
void mytool1_print(char *print_str)
{
         printf("This is mytool1 print : %s ",print_str);
};
#mytool1.h
#ifndef _MYTOOL_1_H
#define _MYTOOL_1_H
        void mytool1_print(char *print_str);
#endif
