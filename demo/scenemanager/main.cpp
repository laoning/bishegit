#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include <windows.h>

#include"SceneManager.h"


#include ".//parser//parser.h"

LampArray* lamp_arr = NULL;         //存放场景中光源数据
Camera camera;
MeshArray* mesh_arr = NULL;         //存放场景中mesh数组
int main()
{
	char * charstr[3];
	charstr[0] = "parser";
	charstr[1] = ".//kitchen";
	charstr[2] = "sink.aff";        //所要扫描的文件	
	parser_main(3,charstr);
   
	Scene myscene;
	myscene.setLampArr(lamp_arr);
	
    
	return 0;
}


