#include "stdio.h"
#include"inter.h"
#ifdef WIN32
#include "..\\SceneManager.h"
#include <stack>
extern LampArray* lamp_arr;//������main.cpp��
extern MeshArray* mesh_arr;//������main.cpp�� 

MeshArray* mesh_arr_temp = NULL;
Mesh* mesh_temp=NULL;
IndexTriple vertexIndex,normalIndex,texturecoordIndex;
int count=0;
Material material_temp;
//StaticTransform staticTran;
stack<StaticTransform> staticTran;
#endif

/*********************************
  ���º�������parser.y�б�����
  ********************************/

void lamp(double x, double y, double z)
{

}

//��ȡ������Դ���ݣ����볡���Ĺ�Դ������
void lamp_rgb(double x, double y, double z, double r, double g, double b)
{
    //printf("lamp_rgb:<%g, %g, %g, %g, %g, %g>\n", x, y, z, r, g, b);
#ifdef WIN32
	Lamp* lamp_temp = new Lamp;
	lamp_temp->setLocation(x,y,z);
	lamp_temp->setBrightness(r,g,b);
	if(NULL == lamp_arr)
	{
		LampArray* s = new LampArray;
        s->setLampMember(lamp_temp);
		s->setNextMember(NULL);
        lamp_arr = s;
	}
	else
	{
        LampArray* s = new LampArray;
		LampArray* pointer = lamp_arr;
		while(pointer->getNextMember() != NULL)
		{
			pointer = pointer->getNextMember();
		}
		s->setLampMember(lamp_temp);
		s->setNextMember(NULL);
		pointer->setNextMember(s);
	}
#endif
}

//����ÿ��mesh�Ķ�����Ŀ
void vertices_sec_head(long num_verts)
{
#ifdef WIN32
	if(NULL != mesh_arr_temp){
		MeshArray* mesh_pointer = mesh_arr_temp;
		while(NULL != mesh_pointer->getNextMember())
		{
			mesh_pointer = mesh_pointer->getNextMember();
		}
		mesh_temp = mesh_pointer->getMesh();
		
	}
	else{
		return;
	}
		
	TriangleMesh* triangleMesh_temp = new TriangleMesh;
	triangleMesh_temp->setVerticesNum(num_verts);
	mesh_temp->setTriangleMesh(triangleMesh_temp);	
	  
#endif
}

//����ÿ��mesh�ķ�����Ŀ
void normals_sec_head(long num_norms)
{
#ifdef WIN32
	if(NULL == mesh_temp)
	{
		printf("mesh_temp == NULL in normals_sec_head!\n");
		return;
	}
	if(NULL == mesh_temp->getTriangleMesh()){
		printf("mesh_temp->getTriangleMesh() == NULL in normals_sec_head!\n");
		return;
	}
   mesh_temp->getTriangleMesh()->setNormalsNum(num_norms);
#endif
}

//����ÿ��mesh������������Ŀ
void texturecoords_sec_head(long num_texcoords)
{
#ifdef WIN32
	if(NULL == mesh_temp)
	{
		printf("mesh_temp == NULL in texturecoords_sec_head!\n");
		return;
	}
	if(NULL == mesh_temp->getTriangleMesh()){
		printf("mesh_temp->getTriangleMesh() == NULL in texturecoords_sec_head!\n");
		return;
	}
	mesh_temp->getTriangleMesh()->setUvCoordNum(num_texcoords);
#endif
}

//����ÿ��mesh������Ƭ�����Ŀ
void triangles_sec_head(long num_tris)
{
#ifdef WIN32
	if(NULL == mesh_temp)
	{
		printf("mesh_temp == NULL in triangles_sec_head!\n");
		return;
	}
	if(NULL == mesh_temp->getTriangleMesh()){
		printf("mesh_temp->getTriangleMesh() == NULL in triangles_sec_head!\n");
		return;
	}
	mesh_temp->getTriangleMesh()->setTriangleNum(num_tris);
#endif
}

//����mesh��ÿ�����������
void vertex(double x, double y, double z)
{
#ifdef WIN32
	
	if(NULL == mesh_temp)
	{
		printf("mesh_temp == NULL in vertex!\n");
		return;
	}
	if(NULL == mesh_temp->getTriangleMesh()){
		printf("mesh_temp->getTriangleMesh() == NULL in vertex!\n");
		return;
	}
	mesh_temp->getTriangleMesh()->setVertices(x,y,z);
#else

#endif    
}

//����mesh��ÿ������ķ���ֵ
void normal(double x, double y, double z)
{
#ifdef WIN32
	if(NULL == mesh_temp)
	{
		printf("mesh_temp == NULL in normal!\n");
		return;
	}
	if(NULL == mesh_temp->getTriangleMesh()){
		printf("mesh_temp->getTriangleMesh() == NULL in normal!\n");
		return;
	}
	mesh_temp->getTriangleMesh()->setNormals(x,y,z);
#endif
}

//����mesh��ÿ���������������
void texturecoord(double u, double v)
{
#ifdef WIN32
	if(NULL == mesh_temp)
	{
		printf("mesh_temp == NULL in texturecoord!\n");
		return;
	}
	if(NULL == mesh_temp->getTriangleMesh()){
		printf("mesh_temp->getTriangleMesh() == NULL in texturecoord!\n");
		return;
	}
	mesh_temp->getTriangleMesh()->setTextruecoords(u,v);

#endif
}

//����mesh��ÿ������Ƭ�ж�������
void vertex_index(long a, long b, long c)
{
#ifdef WIN32
	vertexIndex.initialize();
	normalIndex.initialize();
	texturecoordIndex.initialize();
	
	vertexIndex.setIndexTriple(a,b,c);
	
#endif
}

//����mesh��ÿ������Ƭ�з�������
void normal_index(long a, long b, long c)
{
#ifdef WIN32
     normalIndex.setIndexTriple(a,b,c);
#endif
}

//����mesh��ÿ������Ƭ��������������
void texture_coord_index(long a, long b, long c)
{
#ifdef WIN32
      texturecoordIndex.setIndexTriple(a,b,c);
	  if(NULL == mesh_temp)
	  {
		  printf("mesh_temp == NULL in texture_coord_index!\n");
		  return;
	  }
	  if(NULL == mesh_temp->getTriangleMesh()){
		  printf("mesh_temp->getTriangleMesh() == NULL in texture_coord_index!\n");
		  return;
	  }
	  mesh_temp->getTriangleMesh()->setTriangles(vertexIndex,normalIndex,texturecoordIndex);
#endif
}

//����mesh�Ĳ�������
void material(double amb_r,
				double amb_g,
				double amb_b,
				double diff_r,
				double diff_g,
				double diff_b,
				double spec_r,
				double spec_g,
				double spec_b,
				double shine,
				double t,
				double index_of_refraction)
{
#ifdef WIN32
    
	material_temp.setAmbientColor(amb_r,amb_g,amb_b);
	material_temp.setDiffuseColor(diff_r,diff_g,diff_b);
	material_temp.setSpecularColor(spec_r,spec_g,spec_b);
	material_temp.setShine(shine);
	material_temp.setTransmittance(t);
	material_temp.setRefractionIndex(index_of_refraction);
#endif
}

//����mesh�ı任����
void static_tran(double sx, double sy, double sz,
                    double rx, double ry, double rz, double angle_deg,
                    double tx, double ty, double tz)
{
#ifdef WIN32
     StaticTransform staticTran_temp;
	 staticTran_temp.setScale(sx,sy,sz);
	 staticTran_temp.setRotation(rx,ry,rz,angle_deg);
	 staticTran_temp.setTranslation(tx,ty,tz);

	 staticTran.push(staticTran_temp);
#endif
}

void transls_sec_head(long num_keyframes)
{
#ifdef WIN32

#endif
}


void rots_sec_head(long num_keyframes)
{
#ifdef WIN32

#endif
}


void scales_sec_head(long num_keyframes)
{
#ifdef WIN32

#endif
}


void visibilities_sec_head(long num_keyframes)
{
#ifdef WIN32

#endif
}

void mesh()
{
#ifdef WIN32
	int a=98;
	int b=a;
	count=0;
#else

#endif
}

//�½�һ��mesh
void triangle_mesh(char *mesh_name)
{
#ifdef WIN32
	printf("%s\n",mesh_name);
	Mesh* meshnode = new Mesh;
	MeshArray* mesharrnode = new MeshArray;
	count++;
	if(NULL != mesh_arr_temp){
		MeshArray* mesh_pointer = mesh_arr_temp;
		while(NULL != mesh_pointer->getNextMember())
		{
			mesh_pointer = mesh_pointer->getNextMember();
		}
		mesharrnode->setmeshMember(meshnode);
		mesh_pointer->setNextMember(mesharrnode);
	}
	else
	{
		mesh_arr_temp = mesharrnode;
		mesharrnode->setmeshMember(meshnode);
	}
#else
    printf("Trianlge:[%s]\n", mesh_name);
#endif
}

//һ��mesh����
void triangle_mesh_end()
{
#ifdef WIN32
    int a=45;
	int b=a;
    
	mesh_temp->setMaterial(material_temp);
	if(!staticTran.empty()){
         mesh_temp->setStaticTransform(staticTran.top());
	}
	else{
		if(NULL != mesh_arr) {
			MeshArray* mesh_pointer = mesh_arr;
			while(NULL != mesh_pointer->getNextMember())
			{
				mesh_pointer = mesh_pointer->getNextMember();
			}
			mesh_pointer->setNextMember(mesh_arr_temp);
			mesh_arr_temp = NULL;
		}
		else{
			mesh_arr = mesh_arr_temp;
            mesh_arr_temp = NULL;
		}	 
	}
	
	
   
#else
    printf("Trianlge End\n");
#endif
}

void set_material()
{
#ifdef WIN32

#else

#endif
}

//һ�α任�Ľ���
void set_transform()
{
#ifdef WIN32
     staticTran.pop();
	 if(staticTran.empty())
	 {		 
		 if(NULL != mesh_arr) {
			 MeshArray* mesh_pointer = mesh_arr;
			 while(NULL != mesh_pointer->getNextMember())
			 {
                  mesh_pointer = mesh_pointer->getNextMember();
			 }
			 mesh_pointer->setNextMember(mesh_arr_temp);
			 mesh_arr_temp = NULL;
		 }
		 else{
             mesh_arr = mesh_arr_temp;
			 mesh_arr_temp = NULL;
		 }	 
		
	 }
#else

#endif
}

void set_keyframe()
{
#ifdef WIN32

#else

#endif
}

//��ʾ�ոս�����mesh��ǰһ��mesh��һ�����е��ֵܣ�ͬ����һ����mesh
void mesh_and_mesh()
{
#ifdef WIN32
    int a=34;
	int b=a;
	if(NULL != mesh_arr_temp){
		MeshArray* mesh_pointer = mesh_arr_temp;
		MeshArray* mesh_pointer_pre = mesh_pointer;
		while(NULL != mesh_pointer->getNextMember())
		{
			mesh_pointer_pre = mesh_pointer;
			mesh_pointer = mesh_pointer->getNextMember();
		}
		if(mesh_pointer_pre != mesh_pointer){
			Mesh* temp = mesh_pointer_pre->getMesh();
			MeshArray* double_mesh = new MeshArray;
			double_mesh->setmeshMember(mesh_pointer->getMesh());
            
		}
	}
#else

#endif
}

