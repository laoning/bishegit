#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[])
{
 FILE *fpi, *fpo;
// int  c,i;
 char buffer[256];
 if((fpi=fopen("experiment/assi.md","r"))==NULL)
  {
   printf("文件assi.md打开失败!");
   exit(1);
  }
  printf("文件assi.md成功打开可用于读!");
 if((fpo=fopen("text.txt","w"))==NULL)
   {
   printf("text.txt打开失败!");
   exit(2);
  }
 printf("text.txt成功打开可用于写!");
 while(fgets(buffer,sizeof(buffer),fpi))
 {
  if (feof(fpi)) break;
  if(*buffer=='1')
  {
    while(fgets(buffer,sizeof(buffer),fpi))
         {
            if(*buffer!='2')
            fputs(buffer,fpo);
            if(*buffer=='2')
            {
              while(fgets(buffer,sizeof(buffer),fpi))
                  {
                   
                       if(*buffer!='3')
                     fputs(buffer,fpo);
                     if(*buffer=='3')
                     {
                      while(fgets(buffer,sizeof(buffer),fpi))
                        {
                          if(*buffer!='4')
              
                           fputs(buffer,fpo);
                           if(*buffer=='4')
                         {
                          while(fgets(buffer,sizeof(buffer),fpi))                            
                          {
                           if(*buffer!='5')
                           fputs(buffer,fpo);
                           }
                        }
                      }
                     }

                  }
            //  fputs(buffer,fpo);
            }
        }  
   }

 }
 fclose(fpo);
 fclose(fpi);

}

 
