#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include <windows.h>

#include"SceneManager.h"


#include ".//parser//parser.h"

LampArray* lamp_arr = NULL;         //��ų����й�Դ����
Camera camera;
MeshArray* mesh_arr = NULL;         //��ų�����mesh����
int main()
{
	char * charstr[3];
	charstr[0] = "parser";
	charstr[1] = ".//kitchen";
	charstr[2] = "sink.aff";        //��Ҫɨ����ļ�	
	parser_main(3,charstr);
   
	Scene myscene;
	myscene.setLampArr(lamp_arr);
	
    
	return 0;
}


