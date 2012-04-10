#include <stdio.h>
#include <stdlib.h>

int main()
{
 FILE *fpi, *fpo;
 int c;
 if((fpi=fopen("/home/wml/rsw.txt","r"))==NULL)
  {
   printf("文件rsw.txt打开失败!");
   exit(1);
  }
  printf("文件rsw.txt成功打开可用于读!");
 if((fpo=fopen("text.txt","w"))==NULL)
   {
   printf("text.txt打开失败!");
   exit(2);
  }
   printf("text.txt成功打开可用于写!");
 while(!feof(fpi))
 {
  c=fgetc(fpi);
  fputc(c,fpo);
 }
 fclose(fpo);
 fclose(fpi);

}
 
