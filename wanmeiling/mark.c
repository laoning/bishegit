#include <stdio.h>
#include <stdlib.h>

int main()
{
 FILE *fpi, *fpo;
 int  a[4]={'<','/','h','>'};
 int  c,i;
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
  if(c=='\n')
 {   
   for(i=0;i<4;i++)
   {
    c=a[i];
    fputc(c,fpo);
   }
  c='\n';
  fputc(c,fpo); 

}
 

  fputc(c,fpo);
 }
 fclose(fpo);
 fclose(fpi);

}

 
