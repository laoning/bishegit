#include<stdio.h>
#include<stdlib.h>
void main()
{
char ch;
FILE *fp;
fp=fopen("../prj/parser3d/scene/kitchen/floor.aff","r");

if((fp=fopen("../prj/parser3d/scene/kitchen/floor.aff","r"))==NULL)
{
printf("cannot open this file\n");
exit(0);
}

ch=fgetc(fp);
while(ch!=EOF)
{
putchar(ch);
ch=fgetc(fp);
}

fclose(fp);
}

