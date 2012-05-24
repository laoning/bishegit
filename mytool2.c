#include "mytool2.h"
#include <stdio.h>

void mytool2_print(char *print_str)
{
         printf("This is mytool2 print : %s ",print_str);
};

#Mytool2.h:

#ifndef _MYTOOL_2_H
#define _MYTOOL_2_H
        void mytool2_print(char *print_str);
#endif
