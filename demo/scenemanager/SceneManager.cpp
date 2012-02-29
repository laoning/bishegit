#include "SceneManager.h"
#include<iostream>
#include<direct.h>
#include<vector>
using namespace std;

const int RGBA=4;
const int RGB=3;

//Vector3d类的定义
Vector3d::Vector3d(float v1,float v2 ,float v3)
{
	this->x = v1;
	this->y = v2;
	this->z = v3;
}
void Vector3d::setX(float v)
{
	Vector3d::x = v;
}

void Vector3d::initialize()
{
	x=0.0f;
	y=0.0f;
	z=0.0f;
}
		
void Vector3d::setY(float v)
{
	Vector3d::y = v;
}
		
void Vector3d::setZ(float v)
{
	Vector3d::z = v;
}

void Vector3d::setVector(float v1,float v2,float v3)
{
	Vector3d::x = v1;
	Vector3d::y = v2;
	Vector3d::z = v3;
}

float Vector3d::getX()const
{
	return Vector3d::x;
}

float Vector3d::getY()const
{
	return Vector3d::y;
}

float Vector3d::getZ()const
{
	return Vector3d::z;
}

Vector3d Vector3d::getVector()const
{
	Vector3d temp;
	temp.x = this->x;
	temp.y = this->y;
	temp.z = this->z;
	return temp;
}

//RotationOnArbitraryAxis类的定义
RotationOnArbitraryAxis::RotationOnArbitraryAxis(float v1,Vector3d& v2)
{
	this->angle = v1;
	this->axis = v2;
}

void RotationOnArbitraryAxis::initialize()
{
	this->angle=0.0f;
	this->axis.initialize();
}
void RotationOnArbitraryAxis::setAngle(float v)
{
	this->angle = v;
}
void RotationOnArbitraryAxis::setAxis(Vector3d& v)
{
	this->axis = v;
}

void RotationOnArbitraryAxis::setAxis(float x,float y,float z)
{
	this->axis.setVector(x,y,z);
}
		
float RotationOnArbitraryAxis::getAngle()const
{
	return this->angle;
}
		
Vector3d RotationOnArbitraryAxis::getAxis()const
{
	return this->axis;
}


//StaticTransform类的定义
StaticTransform::StaticTransform(Vector3d& v1,RotationOnArbitraryAxis& v2,Vector3d& v3)
{
	this->scale = v1;
	this->rotation = v2;
	this->translation = v3;
}

void StaticTransform::initialize()
{
	this->scale.setVector(1.0f,1.0f,1.0f);
	this->rotation.initialize();
	this->translation.initialize();
}

void StaticTransform::setScale(Vector3d& v)
{
	this->scale = v;
}

void StaticTransform::setScale(float new_var1,float new_var2,float new_var3)
{
	this->scale.setVector(new_var1,new_var2,new_var3);
}
		
void StaticTransform::setRotation(RotationOnArbitraryAxis& v)
{
	this->rotation = v;
}

void StaticTransform::setRotation(float new_var1,float new_var2,float new_var3,float angle)
{
	this->rotation.setAxis(new_var1,new_var2,new_var3);
	this->rotation.setAngle(angle);
}
		
void StaticTransform::setTranslation(Vector3d& v)
{
	this->translation = v;
}

void StaticTransform::setTranslation(float new_var1,float new_var2,float new_var3)
{
	this->translation.setVector(new_var1,new_var2,new_var3);
}
       
Vector3d StaticTransform::getScale()const
{
	return this->scale;
}
		
Vector3d StaticTransform::getTranslation()const
{
	return this->translation;
}
        
RotationOnArbitraryAxis StaticTransform::getRotation()const
{
	return this->rotation;
}

//Camera类的定义
void Camera::setLocation(Vector3d& new_var)
{
	Camera::location = new_var;
}

void Camera::setLocation(float new_var1,float new_var2,float new_var3)
{
    this->location.setX(new_var1);
	this->location.setY(new_var2);
	this->location.setZ(new_var3);
}

void Camera::setDirection(Vector3d& new_var)
{
	Camera::direction = new_var;
}

void Camera::setDirection(float new_var1,float new_var2,float new_var3)
{
	this->direction.setX(new_var1);
	this->direction.setY(new_var2);
	this->direction.setZ(new_var3);
}

void Camera::setUp(Vector3d& new_var)
{
	Camera::up = new_var;
}

void Camera::setUp(float new_var1,float new_var2,float new_var3)
{
	this->up.setX(new_var1);
	this->up.setY(new_var2);
	this->up.setZ(new_var3);
}

void Camera::setAngle(float new_var)
{
	Camera::angle = new_var;
}

void Camera::setResolutionX(int new_var)
{
	Camera::resolutionX = new_var;
}

void Camera::setResolutionY(int new_var)
{
	Camera::resolutionY = new_var;
}

Vector3d Camera::getLocation()const
{
	return this->location;
}
		
Vector3d Camera::getDirectrion()const
{
    return this->direction;
}

Vector3d Camera::getUp()const
{
	return this->up;
}

float Camera::getAngle()const
{
	return this->angle;
}

int Camera::getResolotionX()const
{
	return this->resolutionX;
}

int Camera::getResolotionY()const
{
	return this->resolutionY;
}

//Color类的定义
Color::Color(float v1,float v2,float v3)
{
	this->red = v1;
	this->green = v2;
	this->blue = v3;	
}

void Color::initialize()
{
    this->red = 0.0f;
	this->green = 0.0f;
	this->blue = 0.0f;
}

void Color::setRed(float v)
{
	this->red = v;
}
		
void Color::setGreen(float v)
{
    this->green = v;
}

void Color::setBlue(float v)
{
    this->blue = v;
}

void Color::setColor(float new_var1,float new_var2,float new_var3)
{
    this->red = new_var1;
	this->green = new_var2;
	this->blue = new_var3;
}

float Color::getRed()const
{
	return this->red;
}

float Color::getGreen()const
{
	return this->green;
}

float Color::getBlue()const
{
    return this->blue;
}

//Lamp类的定义

void Lamp::setLightType(LightSourceType new_var)
{
	Lamp::type = new_var;
}

void Lamp::setBrightness(Color& new_var)
{
	Lamp::brightness = new_var;
}

void Lamp::setBrightness(float new_var1,float new_var2,float new_var3)
{
	this->brightness.setRed(new_var1);
	this->brightness.setGreen(new_var2);
	this->brightness.setBlue(new_var3);
}
		
void Lamp::setLocation(Vector3d& new_var)
{
	Lamp::location = new_var;
}

void Lamp::setLocation(float new_var1,float new_var2,float new_var3)
{
	this->location.setX(new_var1);
    this->location.setY(new_var2);
	this->location.setZ(new_var3);
}
		
void Lamp::setName(string new_var)
{
	this->name = new_var;
}

LightSourceType Lamp::getLightType()const
{
	return this->type;
}
		
Color Lamp::getBrightness()const
{
	return this->brightness;
}

Vector3d Lamp::getLocation()const
{
	return this->location;
}

string Lamp::getName()const
{
	return this->name;
}


//UVCoordinate类的定义
UVCoordinate::UVCoordinate(float v1,float v2)
{
	this->u = v1;
	this->v = v2;
}

void UVCoordinate::setU(float new_var)
{
	this->u = new_var;
}

void UVCoordinate::setV(float new_var)
{
	this->v = new_var;
}

void UVCoordinate::setUV(float new_var1,float new_var2)
{
	this->u = new_var1;
	this->v = new_var2;
}

float UVCoordinate::getU()const
{
	return this->u;
}

float UVCoordinate::getV()const
{
	return this->v;
}

//IndexTriple类的定义
IndexTriple::IndexTriple(long new_var1,long new_var2,long new_var3)
{
	this->a = new_var1;
	this->b = new_var2;
	this->c = new_var3;
}

void IndexTriple::initialize()
{
	this->a = -1;
	this->b = -1;
	this->c = -1;
}

void IndexTriple::setA(long new_var)
{
	this->a = new_var;
}

void IndexTriple::setB(long new_var)
{
	this->b = new_var;
}

void IndexTriple::setC(long new_var)
{
	this->c = new_var;
}

void IndexTriple::setIndexTriple(long new_var1,long new_var2,long new_var3)
{
    this->a = new_var1;
	this->b = new_var2;
	this->c = new_var3;
}

long IndexTriple::getA()const
{
	return this->a;
}

long IndexTriple::getB()const
{
	return this->b;
}

long IndexTriple::getC()const
{
	return this->c;
}


//TextureMap类的定义

TextureMap::TextureMap(const TextureMap& new_var)
{
     this->texturePicFileName = new_var.texturePicFileName;
	 this->idx = new_var.idx;
	 this->uvCoordNum = new_var.uvCoordNum;
	 this->width = new_var.width;
	 this->height = new_var.height;
	 if(this->uvCoordNum > 0)
	 {
		 this->uvCoord = new UVCoordinate[this->uvCoordNum];
		 memcpy((void*)this->uvCoord,(void*)new_var.uvCoord,sizeof(UVCoordinate)*this->uvCoordNum);
	 }

	 if(this->width >0 && this->height)
	 {
		 this->rgba = new unsigned char[this->width*this->height*RGBA];
		 memcpy(this->rgba,new_var.rgba,sizeof(unsigned char)*this->width*this->height*RGBA);
	 }
}

TextureMap& TextureMap::operator=(const TextureMap& new_var)
{
	this->texturePicFileName = new_var.texturePicFileName;
	this->idx = new_var.idx;
	this->uvCoordNum = new_var.uvCoordNum;
	this->width = new_var.width;
	this->height = new_var.height;
	if(this->uvCoordNum > 0)
	{
		this->uvCoord = new UVCoordinate[this->uvCoordNum];
		memcpy((void*)this->uvCoord,(void*)new_var.uvCoord,sizeof(UVCoordinate)*this->uvCoordNum);
	}

	if(this->width >0 && this->height)
	{
		this->rgba = new unsigned char[this->width*this->height*RGBA];
		memcpy(this->rgba,new_var.rgba,sizeof(unsigned char)*this->width*this->height*RGBA);
	}
	return *this;
}

void TextureMap::setResolution(int new_var1,int new_var2)
{
    if(new_var1 <=0 || new_var2 <=0)
	{
		cout<<"Resolution must > 0!"<<endl;
		return;
	}

	this->width = new_var1;
	this->height = new_var2;
}

void TextureMap::setRGBA(unsigned char* new_var)
{
    if(this->width<=0 || this->height<=0)
	{
		cout<<"Resolution must > 0!"<<endl;
		return;
	}
	this->rgba = new unsigned char[this->width*this->height*RGBA];
	if(NULL != this->rgba)
	{
		memcpy(this->rgba,new_var,sizeof(unsigned char)*this->width*this->height*RGBA);
	}
	else
	{
		cout<<"out of memory!"<<endl;
	}
		
}
void TextureMap::setTextruePicFileName(string new_var)
{
    this->texturePicFileName = new_var;
}

void TextureMap::setUvCoordNum(int new_var)
{
    if(new_var<=0)
		return;
	this->uvCoordNum = new_var;
    this->uvCoord = new UVCoordinate[this->uvCoordNum];
}

void TextureMap::setUvCoord(UVCoordinate& new_var)
{
	if(this->uvCoordNum <= 0)
		return;
	if(this->idx >= this->uvCoordNum-1)
		return;
	this->idx++;
	this->uvCoord[this->idx] = new_var;
}

void TextureMap::setUvCoord(float new_var1,float new_var2)
{
	if(this->uvCoordNum <= 0)
		return;
	if(this->idx >= this->uvCoordNum)
		return;
	this->idx++;
	this->uvCoord[this->idx].setUV(new_var1,new_var2);    
}

string TextureMap::getTexturePicFileName()const
{
	return this->texturePicFileName;
}

int TextureMap::getUvCoordNum()const
{
	return this->uvCoordNum;
}

int TextureMap::getIdx()const
{
	return this->idx;
}

UVCoordinate* TextureMap::getUvCoord()const
{
	return this->uvCoord;
}

int TextureMap::getWidth()const
{
	return this->width;
}

int TextureMap::getHeight()const
{
    return this->height;
}

unsigned char* TextureMap::getRGBA()const
{
	return this->rgba;
}

TextureMap::~TextureMap()
{
	if(NULL != this->uvCoord)
	{
		delete[] this->uvCoord;
        this->uvCoord = NULL;
	}

	if(NULL != this->rgba)
	{
		delete[] this->rgba;
		this->rgba = NULL;
	}
}

//Triangle类的定义
Triangle::Triangle(IndexTriple& new_var1,IndexTriple& new_var2,IndexTriple& new_var3)
{
	this->triangleIndices = new_var1;
	this->normalIndices = new_var2;
	this->textureCoordIndices = new_var3;
}

void Triangle::setTriangleIndices(IndexTriple& new_var)
{
    this->triangleIndices = new_var;
}

void Triangle::setNormalIndices(IndexTriple& new_var)
{
	this->normalIndices = new_var;
}

void Triangle::setTextureCoordIndices(IndexTriple& new_var)
{
	this->textureCoordIndices = new_var;
}

void Triangle::setTriangle(IndexTriple& new_var1,IndexTriple& new_var2,IndexTriple& new_var3)
{
    this->triangleIndices = new_var1;
	this->normalIndices = new_var2;
	this->textureCoordIndices = new_var3;
}

IndexTriple Triangle::getTriangleIndices()const
{
	return this->triangleIndices;
}

IndexTriple Triangle::getNormalIndices()const
{
	return this->normalIndices;
}

IndexTriple Triangle::getTextureCoordIndices()const
{
	return this->textureCoordIndices;
}



//TriangleMesh类的定义
TriangleMesh::TriangleMesh(const TriangleMesh& new_var)
{
	this->verticesNum = new_var.verticesNum;
	this->normalsNum = new_var.normalsNum;		
	this->triangleNum = new_var.triangleNum;

	this->verticesIdx = new_var.verticesIdx;
	this->normalsIdx = new_var.normalsIdx;
	this->triangleIdx = new_var.triangleIdx;
    
	this->texture = new_var.texture;

	if(this->verticesNum > 0)
	{
		this->vertices = new Vertex[this->verticesNum];
		memcpy((void*)this->vertices,(void*)new_var.vertices,sizeof(Vertex)*this->verticesNum);
	}

	if(this->normalsNum > 0)
	{
		this->normals = new Normal[this->normalsNum];
		memcpy((void*)this->normals,(void*)new_var.normals,sizeof(Normal)*this->normalsNum);
	}

	if(this->triangleNum)
	{
		this->triangles = new Triangle[this->triangleNum];
		memcpy((void*)this->triangles,(void*)new_var.triangles,sizeof(Triangle)*this->triangleNum);
	}	
	
}

TriangleMesh& TriangleMesh::operator=(const TriangleMesh& new_var)
{
	this->verticesNum = new_var.verticesNum;
	this->normalsNum = new_var.normalsNum;		
	this->triangleNum = new_var.triangleNum;

	this->verticesIdx = new_var.verticesIdx;
	this->normalsIdx = new_var.normalsIdx;
	this->triangleIdx = new_var.triangleIdx;

	this->texture = new_var.texture;

	if(this->verticesNum > 0)
	{
		this->vertices = new Vertex[this->verticesNum];
		memcpy((void*)this->vertices,(void*)new_var.vertices,sizeof(Vertex)*this->verticesNum);
	}

	if(this->normalsNum > 0)
	{
		this->normals = new Normal[this->normalsNum];
		memcpy((void*)this->normals,(void*)new_var.normals,sizeof(Normal)*this->normalsNum);
	}

	if(this->triangleNum)
	{
		this->triangles = new Triangle[this->triangleNum];
		memcpy((void*)this->triangles,(void*)new_var.triangles,sizeof(Triangle)*this->triangleNum);
	}	

	return *this;
}

void TriangleMesh::setVerticesNum(int new_var)
{
	if(new_var <= 0)
		return;
	this->verticesNum = new_var;
	this->vertices = new Vertex[this->verticesNum];
}

void TriangleMesh::setNormalsNum(int new_var)
{
	if(new_var <= 0)
		return;
	this->normalsNum = new_var;
	this->normals = new Normal[this->normalsNum];
}

void TriangleMesh::setUvCoordNum(int new_var)
{
	if(new_var <= 0)
		return;
	this->texture.setUvCoordNum(new_var);	
}

void TriangleMesh::setTriangleNum(int new_var)
{
	if(new_var <= 0)
		return;
	this->triangleNum = new_var;
	this->triangles = new Triangle[this->triangleNum];
}

void TriangleMesh::setVertices(Vertex& new_var)
{
	if(this->verticesNum <= 0)
		return;
	if(this->verticesIdx >= this->verticesNum-1)
		return;
	this->verticesIdx++;
	this->vertices[this->verticesIdx] = new_var;
}
	
void TriangleMesh::setVertices(float new_var1,float new_var2,float new_var3)
{
	if(this->verticesNum <= 0)
		return;
	if(this->verticesIdx >= this->verticesNum-1)
		return;
	this->verticesIdx++;
	this->vertices[this->verticesIdx].setVector(new_var1,new_var2,new_var3);    
}

void TriangleMesh::setNormals(Normal& new_var)
{
	if(this->normalsNum <= 0)
		return;
	if(this->normalsIdx >= this->normalsNum-1)
		return;
	this->normalsIdx++;
	this->normals[this->normalsIdx] = new_var;
}

void TriangleMesh::setNormals(float new_var1,float new_var2,float new_var3)
{
	if(this->normalsNum <= 0)
		return;
	if(this->normalsIdx >= this->normalsNum-1)
		return;
	this->normalsIdx++;
	this->normals[this->normalsIdx].setVector(new_var1,new_var2,new_var3);    
}

void TriangleMesh::setTexturePicName(string new_var)
{
	this->texture.setTextruePicFileName(new_var);
}

void TriangleMesh::setTextruecoords(UVCoordinate& new_var)
{
	this->texture.setUvCoord(new_var);
}

void TriangleMesh::setTextruecoords(float new_var1,float new_var2)
{
    this->texture.setUvCoord(new_var1,new_var2);
}
		
void TriangleMesh::setTriangles(Triangle& new_var)
{
	if(this->triangleNum <= 0)
		return;
	if(this->triangleIdx >= this->triangleNum-1)
	{   
		cout<<"triangleIdx >= triangleNum"<<endl;
		return;
	}
	this->triangleIdx++;
	this->triangles[this->triangleIdx] = new_var;
}

void TriangleMesh::setTriangles(IndexTriple& new_var1,IndexTriple& new_var2,IndexTriple& new_var3)
{
	if(this->triangleNum <= 0)
		return;
	if(this->triangleIdx >= this->triangleNum-1)
	{
		cout<<"triangleIdx >= triangleNum"<<endl;
		return;
	}
	this->triangleIdx++;
	this->triangles[this->triangleIdx].setTriangle(new_var1,new_var2,new_var3);	
}

Vertex* TriangleMesh::getVertices()const
{
	return this->vertices;
}

Normal* TriangleMesh::getNormals()const
{
	return this->normals;
}

TextureMap* TriangleMesh::getTexture()
{
	return &(this->texture);
}

Triangle* TriangleMesh::getTriangles()const
{
	return this->triangles;
}

int TriangleMesh::getTriangleNum()const
{
    return this->triangleNum;
}

void TriangleMesh::setTexture(TexNode &tnode)
{
	if(tnode.m_width <= 0 || tnode.m_height <=0 )
	{
		cout<<"Error: Resolution 必须大于0！"<<endl;
		return;
	}
    if(NULL == tnode.m_data)
	{
		cout<<"Error: there is no RGBA"<<endl;
		return;
	}
    
	this->texture.setResolution(tnode.m_width,tnode.m_height);	
	this->texture.setRGBA(tnode.m_data);
	
}

TriangleMesh::~TriangleMesh()
{
	if(NULL != this->vertices)
	{
		delete[] this->vertices;
		this->vertices = NULL;
	}

	if(NULL != this->normals)
	{
		delete[] this->normals;
		this->normals = NULL;
	}

	if(NULL != this->triangles)
	{
		delete[] this->triangles;
		this->triangles = NULL;
	}

	
}

//Material类的定义
Material::Material(Color& new_var1,Color& new_var2,Color& new_var3,float new_var4,float new_var5,float new_var6)
{
    this->ambientColor = new_var1;
	this->diffuseColor = new_var2;
	this->specularColor = new_var3;
	this->shine = new_var4;
	this->transmittance = new_var5;
	this->refractionIndex = new_var6;
}

void Material::initialize()
{
	this->ambientColor.initialize();
	this->diffuseColor.initialize();
	this->specularColor.initialize();
	this->shine = 0.0f;
	this->transmittance = 0.0f;
	this->refractionIndex = 0.0f;
}

void Material::setAmbientColor(Color& new_var)
{
	this->ambientColor = new_var;
}

void Material::setAmbientColor(float new_var1,float new_var2,float new_var3)
{
	this->ambientColor.setRed(new_var1);
	this->ambientColor.setGreen(new_var2);
	this->ambientColor.setBlue(new_var3);
}

void Material::setDiffuseColor(Color& new_var)
{
    this->diffuseColor = new_var;
}

void Material::setDiffuseColor(float new_var1,float new_var2,float new_var3)
{
	this->diffuseColor.setRed(new_var1);
	this->diffuseColor.setGreen(new_var2);
	this->diffuseColor.setBlue(new_var3);
}

void Material::setSpecularColor(Color& new_var)
{
	this->specularColor = new_var;
}

void Material::setSpecularColor(float new_var1,float new_var2,float new_var3)
{
	this->specularColor.setRed(new_var1);
	this->specularColor.setGreen(new_var2);
	this->specularColor.setBlue(new_var3);
}

void Material::setShine(float new_var)
{
	this->shine = new_var;
}

void Material::setTransmittance(float new_var)
{
	this->transmittance = new_var;
}
void Material::setRefractionIndex(float new_var)
{
	this->refractionIndex = new_var;
}

Color Material::getAmbientColor()const
{
	return this->ambientColor;
}

Color Material::getDiffuseColor()const
{
	return this->diffuseColor;
}

Color Material::getSpecularColor()const
{
	return this->specularColor;
}
float Material::getShine()const
{
	return this->shine;
}

float Material::getTransmittance()const
{
	return this->transmittance;
}

float Material::getRefractionIndex()const
{
	return this->refractionIndex;
}


//Mesh类的定义

void Mesh::setTriangleMesh ( TriangleMesh* new_var )
{
	this->triangleMesh = new_var;
}

void Mesh::setStaticTransform ( StaticTransform& new_var )
{
	this->staticTransform = new_var;
}

void Mesh::setMaterial ( Material& new_var )
{
	this->material = new_var;
}

void Mesh::setSubmesh ( Mesh* new_var )
{
	this->submesh = new_var;
	//if(NULL != this->submesh)
	//{
	//	Mesh* s = new Mesh;
	//	s->setMaterial(new_var->getMaterial());
	//	s->setName(new_var->getName());
	//	s->setStaticTransform(new_var->getStaticTransform());
	//	s->setSubmesh(new_var->getSubmesh());
	//	//s->setSubMeshArray(new_var->getSubMeshArray());
	//	s->setTriangleMesh(new_var->getTriangleMesh());
	//	this->submesh = s;
	//	
	//}
	//else
	//{
 //       Mesh* s = new Mesh;
	//	s->setMaterial(new_var->getMaterial());
	//	s->setName(new_var->getName());
	//	s->setStaticTransform(new_var->getStaticTransform());
	//	s->setSubmesh(new_var->getSubmesh());
	//	//s->setSubMeshArray(new_var->getSubMeshArray());
	//	s->setTriangleMesh(new_var->getTriangleMesh());
 //       Mesh* temp = this->submesh;
	//	
	//	while(NULL != temp)
	//	{
	//		temp = temp->getSubmesh();
	//	}
	//	temp = s;

	//}
	
}

void Mesh::setName ( string new_var )
{
	this->name = new_var;
}

void Mesh::setSubMeshArray(Mesh* new_mesh)
{
	if(NULL != this->subMeshArray)
	{
		MeshArray* s = new MeshArray;
		//Mesh* meshNode = new Mesh;
		//meshNode->setMaterial(new_mesh->getMaterial());
        //meshNode->setName(new_mesh->getName());
		//meshNode->setStaticTransform(new_mesh->getStaticTransform());
		//meshNode->setSubmesh(new_mesh->getSubmesh());
		////meshNode->setSubMeshArray(new_mesh->getSubMeshArray());
		//meshNode->setTriangleMesh(new_mesh->getTriangleMesh());

		//s->setmeshMember(meshNode);
		s->setmeshMember(new_mesh);
		s->setNextMember(NULL);
		this->subMeshArray = s;		
	}
	else
	{
		MeshArray* s = new MeshArray;
		MeshArray* temp = this->subMeshArray;
		//Mesh* meshNode = new Mesh;
		//meshNode->setMaterial(new_mesh->getMaterial());
		//meshNode->setName(new_mesh->getName());
		//meshNode->setStaticTransform(new_mesh->getStaticTransform());
		//meshNode->setSubmesh(new_mesh->getSubmesh());
		////meshNode->setSubMeshArray(new_mesh->getSubMeshArray());
		//meshNode->setTriangleMesh(new_mesh->getTriangleMesh());

		//s->setmeshMember(meshNode);
		s->setmeshMember(new_mesh);
		s->setNextMember(NULL);

		while(NULL != temp)
		{
			temp = temp->getNextMember();
		}
		temp = s;
	}
}

string Mesh::getName()const
{
	return this->name;
}

Material Mesh::getMaterial()const
{
	return this->material;
}

TriangleMesh* Mesh::getTriangleMesh()const
{
	return this->triangleMesh;
}

StaticTransform Mesh::getStaticTransform()const
{
	return this->staticTransform;
}

Mesh* Mesh::getSubmesh()const
{
	return this->submesh;
}

MeshArray* Mesh::getSubMeshArray()const
{
	return this->subMeshArray;
}

Mesh::~Mesh()
{
	/*if(NULL!= this->submesh)
	{
		Mesh* meshPointer = this->submesh;
		while(NULL != meshPointer)
		{
			Mesh* temp = meshPointer;
			meshPointer = meshPointer->submesh;
			delete temp;

		}
	}*/

	if(NULL != this->subMeshArray)
	{
		MeshArray* meshArrayPointer = this->subMeshArray;
		while(NULL != meshArrayPointer)
		{
			MeshArray* temp = meshArrayPointer;
			//delete meshArrayPointer->getMesh();
            meshArrayPointer = meshArrayPointer->getNextMember();
			delete temp;
		}
	}
}

//LampArray类的定义
void LampArray::setLampMember(Lamp* lamp)
{
	LampArray::lampMember = lamp;
}
	
void LampArray::setNextMember(LampArray* next)
{
	LampArray::NextMember = next;
}
    
Lamp* LampArray::getLamp()const
{
	return LampArray::lampMember;
}
    
LampArray* LampArray::getNextMember()const
{
	return LampArray::NextMember;
}

//MeshArray类的定义
void MeshArray::setmeshMember(Mesh* mesh)
{
	MeshArray::meshMember = mesh;
}

void MeshArray::setNextMember(MeshArray* next)
{
	MeshArray::NextMember = next;
}
    
Mesh* MeshArray::getMesh()const
{
	return MeshArray::meshMember;
}
    
MeshArray* MeshArray::getNextMember()const
{
	return MeshArray::NextMember;
}


//Scene类的定义
int Scene::create ( string name )
{
	Scene::name = name;
	return 0;
}

int Scene::setCamera(Camera& new_camera)
{
	this->camera = new_camera;
	return 0;
}
		
int Scene::setGlobalAmbientLight ( Color& light )
{
	Scene::globalAmbient = light;
	return 0;
}
		
int Scene::addLamp (Lamp* new_lamp)
{
	if(NULL == Scene::lamp)
	{
	   LampArray* s = new LampArray;

	   Lamp* lampNode = new Lamp;
	   lampNode->setBrightness(new_lamp->getBrightness());	   
	   lampNode->setLocation(new_lamp->getLocation());
	   lampNode->setLightType(new_lamp->getLightType());
	   lampNode->setName(new_lamp->getName());

	   s->setLampMember(lampNode);
	   s->setNextMember(NULL);
	   Scene::lamp = s;
	   return 0;
	}
	else
	{
	   LampArray* s = new LampArray;
       LampArray* temp = Scene::lamp;

	   Lamp* lampNode = new Lamp;
	   lampNode->setBrightness(new_lamp->getBrightness());	   
	   lampNode->setLocation(new_lamp->getLocation());
	   lampNode->setLightType(new_lamp->getLightType());
	   lampNode->setName(new_lamp->getName());

	   s->setLampMember(lampNode);
	   s->setNextMember(NULL);

	   while(NULL != temp->getNextMember())
	   {
		   temp = temp->getNextMember();
	   }
	   temp->setNextMember(s);
	}
	
	return 1;
}

void Scene::setLampArr(LampArray* new_lamparr)
{
	if(NULL != this->lamp)
	{
		LampArray* lampPointer = this->lamp;
		while(NULL != lampPointer)
		{   
			LampArray* temp = lampPointer;
			delete lampPointer->getLamp();
			lampPointer = lampPointer->getNextMember();
			delete temp;
		}
       
		this->lamp = new_lamparr;
	}
	else
	{
        this->lamp = new_lamparr;
	}
}
		
int Scene::setViewpoint ( Camera& camera )
{
	Scene::camera = camera;
	return 0;
}
		
int Scene::addMesh (Mesh* new_mesh)
{
	if(NULL == Scene::mesh)
	{
	   MeshArray* s = new MeshArray;//(MeshArray*)malloc(sizeof(MeshArray));
	   s->setmeshMember(new_mesh);
	   s->setNextMember(NULL);
	   Scene::mesh = s;
	   return 0;
	}
	else
	{
	   MeshArray* s = new MeshArray;//(MeshArray*)malloc(sizeof(MeshArray));
       MeshArray* temp = Scene::mesh;
	   s->setmeshMember(new_mesh);
	   s->setNextMember(NULL);

	   while(NULL != temp->getNextMember())
	   {
		   temp = temp->getNextMember();
	   }
	   temp->setNextMember(s);
	}
	
	return 1;
}

MeshArray* Scene::getMesh()const
{
	return this->mesh;
}

LampArray* Scene::getLamp()const
{
	return this->lamp;
}

Camera Scene::getCamera()const
{
    return this->camera;
}

Color Scene::getGlobalAmbient()const
{
	return this->globalAmbient;
}

string Scene::getName()const
{
	return this->name;
}

void Scene::outputLight(string path)const
{
	int lightNum = 0;
	if(NULL == this->lamp)
	{
	   cout<<"there is no light!"<<endl;
	   return;
	}
    
	LampArray* pointer = lamp;

	while(NULL != pointer)
	{
	   lightNum++;
	   pointer = pointer->getNextMember();
	}
    path += "\\light.txt";
	FILE* fp = fopen(path.c_str(),"w");
	fprintf(fp,"%d\n",lightNum);
    
	pointer = lamp;
    while(NULL != pointer)
	{
		Lamp* temp;
		temp = pointer->getLamp();
		Vector3d location;
		Color bright;
		location = temp->getLocation();
		bright = temp->getBrightness();
		fprintf(fp,"%.2f %.2f %.2f %.2f %.2f %.2f\n",location.getX(),location.getY(),location.getZ(),bright.getRed(),bright.getGreen(),bright.getBlue());
		pointer = pointer->getNextMember();
	}
	fclose(fp);
	fp = NULL;

}

void Scene::eatComments(FILE*& f)
{
	int ch;
	while((ch=getc(f))=='#')
	{
		char str[1000];
		fgets(str,1000,f);
	}
	ungetc(ch,f);
	
}

void Scene::eatWhitespace(FILE*& f)
{
	int ch;
	ch=getc(f);
	while(ch==' ' || ch=='\t' || ch=='\n' || ch=='\f' || ch=='\r')
	{
		ch=getc(f);
	}
	ungetc(ch,f);
	
}
void Scene::setRgb(string picName,FILE*& ppmrgb_fp,FILE* &ppminf_fp)
{
	string path = ".\\"+picName;
	int width, height;
	if("" == picName)
	{
		cout<<"please set thi picture name!"<<endl;
		return;
	}

	FILE* fp = fopen(path.c_str(),"rb");
	if(NULL == fp)	
	{
		cout<<"can't open "<<picName<<endl;
		return;
	}


	char str[100];
	eatWhitespace(fp);
	eatComments(fp);
	eatWhitespace(fp);
	fscanf(fp,"%s",str);
	if(strcmp(str,"P6")!=0)
	{
		cout<<"Error: the texture image file must be of raw color PPM format"<<endl;
		cout<<"i.e., it must have P6 in the header. File: %s\n"<<picName<<endl;;
		return;
	}
	eatWhitespace(fp);
	eatComments(fp);
	eatWhitespace(fp);
	fscanf(fp,"%d %d",&width,&height);
	if(width <=0 || height<=0)
	{
		cout<<"Error: width and height of the image must be greater than zero. File: %s"<<picName<<endl;
		width = -1;
		height = -1;
		return;
	}
	eatWhitespace(fp);
	eatComments(fp);
	eatWhitespace(fp);
	int colres;
	fscanf(fp,"%d",&colres);
	if(colres!=255)
	{
		cout<<"Error: color resolution must be 255.File: %s"<<picName<<endl;;
		return ;
	}

	char ch=0;
	while(ch!='\n') 
		fscanf(fp,"%c",&ch);

	unsigned char* tempRGB=new unsigned char[3*width*height];
	fread((void*)tempRGB,3*width*height,1,fp); 

	fclose(fp);
	for(int i=0;i<3*width*height;i+=3)
	{
		Color temp;
		temp.setColor((float)tempRGB[i]/255,(float)tempRGB[i+1]/255,(float)tempRGB[i+2]/255);
        fwrite(&temp,sizeof(Color),1,ppmrgb_fp);
		
	}	
	fwrite(&width,sizeof(int),1,ppminf_fp);
	fwrite(&height,sizeof(int),1,ppminf_fp);

}

void Scene::output_texnode(string path)
{
	int triangleNum = 0;	
	int uvtotalnum = 0;
	int textureIndex = -1;

	vector<string> picName;
	vector<int> pictureResolution;

	path += "\\";
	string fileName;
	fileName = "vertices.txt";
	FILE* vertices_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "mDiffuseColorbinary.txt";
	FILE* diffuse_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "mSpecularColorbinary.txt";
	FILE* specular_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "AmbientColorbinary.txt";
	FILE* ambient_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "normalFlagbinary.txt";
	FILE* normalFlag_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "normalbinary.txt";
	FILE* normal_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "texFlagbinary.txt";
	FILE* texFlag_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "ppminfbinary.txt";
	FILE* ppminf_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "ppmrgbbinary.txt";
	FILE* ppmrgb_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "texuvbinary.txt";
	FILE* texuv_fp = fopen((path+fileName).c_str(),"wb");

	if(NULL == this->mesh)
	{
		cout<<"there is no light!"<<endl;
		return;
	}

	MeshArray* meshPointer = mesh;
	while(NULL != meshPointer)
	{  
		TriangleMesh* tri_meshTemp;
		TextureMap* textcoordTemp;
		tri_meshTemp = meshPointer->getMesh()->getTriangleMesh();
		triangleNum += tri_meshTemp->getTriangleNum();
		textcoordTemp = tri_meshTemp->getTexture();

		if(textcoordTemp->getUvCoordNum() > 0 && "" != textcoordTemp->getTexturePicFileName())
		{

			vector<string>::iterator iter;

			if(picName.empty())
			{
				picName.push_back(textcoordTemp->getTexturePicFileName());
				pictureResolution.push_back(textcoordTemp->getWidth());
				pictureResolution.push_back(textcoordTemp->getHeight());
				unsigned char* rgb_data = textcoordTemp->getRGBA();
				int width =textcoordTemp->getWidth();
				int height =textcoordTemp->getHeight();
				for(int i=0; i< width*height;i++)
				{
					int red=0,green=0,blue=0;
					red = *(rgb_data++);
					green = *(rgb_data++);
					blue = *(rgb_data++);
					rgb_data++;       //舍掉RGBA中的A值
					float rgb = float(red)/255;
					fwrite(&rgb,sizeof(float),1,ppmrgb_fp);
					rgb = float(green)/255;
					fwrite(&rgb,sizeof(float),1,ppmrgb_fp);
					rgb = float(blue)/255;
					fwrite(&rgb,sizeof(float),1,ppmrgb_fp);
				}
			}
			else
			{
				for(iter = picName.begin();iter != picName.end();iter++)
				{
					if(*iter == textcoordTemp->getTexturePicFileName())
						break;
				}
				if(iter == picName.end())
				{
					picName.push_back(textcoordTemp->getTexturePicFileName());
					pictureResolution.push_back(textcoordTemp->getWidth());
					pictureResolution.push_back(textcoordTemp->getHeight());
					unsigned char* rgb_data = textcoordTemp->getRGBA();
					for(int i=0; i< textcoordTemp->getWidth()*textcoordTemp->getHeight();i++)
					{
						int red=0,green=0,blue=0;
						red = *(rgb_data++);
                        green = *(rgb_data++);
                        blue = *(rgb_data++);
						rgb_data++;       //舍掉RGBA中的A值
                        float rgb = float(red)/255;
						fwrite(&rgb,sizeof(float),1,ppmrgb_fp);
                        rgb = float(green)/255;
                        fwrite(&rgb,sizeof(float),1,ppmrgb_fp);
                        rgb = float(blue)/255;
						fwrite(&rgb,sizeof(float),1,ppmrgb_fp);
					}
				}
			}


			if(textcoordTemp->getUvCoordNum()>0)
			{
				uvtotalnum += tri_meshTemp->getTriangleNum();
			}
		}


		meshPointer = meshPointer->getNextMember();
	}
    
    
	int ppmNum = picName.size();
	fwrite(&ppmNum,sizeof(int),1,ppminf_fp);
	for(vector<int>::iterator iter = pictureResolution.begin();iter != pictureResolution.end();++iter)
	{
		fwrite(&(*iter),sizeof(int),1,ppminf_fp);
	}
	

	meshPointer = mesh;
	Mesh* mesh_in_scene;

	fwrite(&triangleNum,sizeof(int),1,vertices_fp);
	fwrite(&triangleNum,sizeof(int),1,texFlag_fp);
	fwrite(&uvtotalnum,sizeof(int),1,texuv_fp);

	while(NULL != meshPointer)
	{   
		TriangleMesh* tri_meshTemp;
		Color ambient,diffuse,specular;
		float shine=0.0f;
		mesh_in_scene = meshPointer->getMesh();
		tri_meshTemp = mesh_in_scene->getTriangleMesh();
		ambient = mesh_in_scene->getMaterial().getAmbientColor();
		diffuse = mesh_in_scene->getMaterial().getDiffuseColor();
		specular = mesh_in_scene->getMaterial().getSpecularColor();
		shine = mesh_in_scene->getMaterial().getShine();


		Vertex* vertices = tri_meshTemp->getVertices();
		Normal* normals = tri_meshTemp->getNormals();	
		Triangle* triTemp = tri_meshTemp->getTriangles();
		TextureMap* texturcoord = tri_meshTemp->getTexture();
		for(int i=0;i<tri_meshTemp->getTriangleNum();i++)
		{   			
			fwrite(&ambient,sizeof(Color),1,ambient_fp);
			fwrite(&diffuse,sizeof(Color),1,diffuse_fp);
			fwrite(&specular,sizeof(Color),1,specular_fp);
			fwrite(&shine,sizeof(float),1,specular_fp);

			IndexTriple indexTemp = triTemp[i].getTriangleIndices();
			fwrite(&vertices[indexTemp.getA()],sizeof(Vertex),1,vertices_fp);
			fwrite(&vertices[indexTemp.getB()],sizeof(Vertex),1,vertices_fp);
			fwrite(&vertices[indexTemp.getC()],sizeof(Vertex),1,vertices_fp);

			if(NULL == normals)
			{ 
				int temp = -1;
				fwrite(&temp,sizeof(int),1,normalFlag_fp);
			}
			else
			{
				int temp = 0;			   
				fwrite(&temp,sizeof(int),1,normalFlag_fp);
				indexTemp = triTemp[i].getNormalIndices();
				fwrite(&normals[indexTemp.getA()],sizeof(Normal),1,normal_fp);
				fwrite(&normals[indexTemp.getB()],sizeof(Normal),1,normal_fp);
				fwrite(&normals[indexTemp.getC()],sizeof(Normal),1,normal_fp);
			}

			if(NULL != texturcoord)
			{
				if(texturcoord->getUvCoordNum() < 0)
				{
					int temp = -1;
					fwrite(&temp,sizeof(int),1,texFlag_fp);
				}
				else
				{
					textureIndex++;
					indexTemp = triTemp[i].getTextureCoordIndices();
					UVCoordinate* uvCoord = texturcoord->getUvCoord();
					fwrite(&textureIndex,sizeof(int),1,texFlag_fp);
					fwrite(&uvCoord[indexTemp.getA()],sizeof(UVCoordinate),1,texuv_fp);
					fwrite(&uvCoord[indexTemp.getB()],sizeof(UVCoordinate),1,texuv_fp);
					fwrite(&uvCoord[indexTemp.getC()],sizeof(UVCoordinate),1,texuv_fp);
					int picIndex = -1;
					vector<string>::iterator iter = picName.begin(); 
					for(iter = picName.begin();iter != picName.end();iter++)
					{
						picIndex++;
						if(*iter == texturcoord->getTexturePicFileName())
							break;
					}
					
					fwrite(&picIndex,sizeof(picIndex),1,texuv_fp);	
					
				}

			}

		}
        
        

		meshPointer =  meshPointer->getNextMember();       
	}
	
	fclose(vertices_fp);
	fclose(diffuse_fp);
	fclose(specular_fp);
	fclose(ambient_fp);
	fclose(normalFlag_fp);
	fclose(normal_fp);
	fclose(texFlag_fp);
	fclose(ppminf_fp);
	fclose(ppmrgb_fp);
	fclose(texuv_fp);
}

void Scene::output(string path)
{
    int triangleNum = 0;	
	int uvtotalnum = 0;
	int textureIndex = -1;
	
	vector<string> picName;
	
	if(NULL == this->mesh)
	{
	   cout<<"there is no light!"<<endl;
	   return;
	}
 
	MeshArray* meshPointer = mesh;
	while(NULL != meshPointer)
	{  
	   TriangleMesh* tri_meshTemp;
	   TextureMap* textcoordTemp;
	   tri_meshTemp = meshPointer->getMesh()->getTriangleMesh();
	   triangleNum += tri_meshTemp->getTriangleNum();
	   textcoordTemp = tri_meshTemp->getTexture();
       
	   if(textcoordTemp->getUvCoordNum() > 0 && "" != textcoordTemp->getTexturePicFileName())
	   {
           
		   vector<string>::iterator iter;
           
		   if(picName.empty())
		   {
               picName.push_back(textcoordTemp->getTexturePicFileName());
		   }
		   else
		   {
			   for(iter = picName.begin();iter != picName.end();iter++)
			   {
				   if(*iter == textcoordTemp->getTexturePicFileName())
					   break;
			   }
			   if(iter == picName.end())
			   {
                   picName.push_back(textcoordTemp->getTexturePicFileName());
			   }
		   }
		   

		   if(textcoordTemp->getUvCoordNum()>0)
		   {
			   uvtotalnum += tri_meshTemp->getTriangleNum();
		   }
	   }
	   

	   meshPointer = meshPointer->getNextMember();
	}

	
	path += "\\";
	string fileName;
	fileName = "vertices.txt";
	FILE* vertices_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "mDiffuseColorbinary.txt";
    FILE* diffuse_fp = fopen((path+fileName).c_str(),"wb");
    fileName = "mSpecularColorbinary.txt";
    FILE* specular_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "AmbientColorbinary.txt";
    FILE* ambient_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "normalFlagbinary.txt";
	FILE* normalFlag_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "normalbinary.txt";
	FILE* normal_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "texFlagbinary.txt";
	FILE* texFlag_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "ppminfbinary.txt";
	FILE* ppminf_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "ppmrgbbinary.txt";
	FILE* ppmrgb_fp = fopen((path+fileName).c_str(),"wb");
	fileName = "texuvbinary.txt";
	FILE* texuv_fp = fopen((path+fileName).c_str(),"wb");

    meshPointer = mesh;
	Mesh* mesh_in_scene;
    
	fwrite(&triangleNum,sizeof(int),1,vertices_fp);
	fwrite(&triangleNum,sizeof(int),1,texFlag_fp);
    fwrite(&uvtotalnum,sizeof(int),1,texuv_fp);

	while(NULL != meshPointer)
	{   
		TriangleMesh* tri_meshTemp;
		Color ambient,diffuse,specular;
		float shine=0.0f;
		mesh_in_scene = meshPointer->getMesh();
		tri_meshTemp = mesh_in_scene->getTriangleMesh();
		ambient = mesh_in_scene->getMaterial().getAmbientColor();
		diffuse = mesh_in_scene->getMaterial().getDiffuseColor();
		specular = mesh_in_scene->getMaterial().getSpecularColor();
		shine = mesh_in_scene->getMaterial().getShine();
		
                
		 Vertex* vertices = tri_meshTemp->getVertices();
		 Normal* normals = tri_meshTemp->getNormals();	
		 Triangle* triTemp = tri_meshTemp->getTriangles();
		 TextureMap* texturcoord = tri_meshTemp->getTexture();
		for(int i=0;i<tri_meshTemp->getTriangleNum();i++)
		{   			
			fwrite(&ambient,sizeof(Color),1,ambient_fp);
			fwrite(&diffuse,sizeof(Color),1,diffuse_fp);
			fwrite(&specular,sizeof(Color),1,specular_fp);
			fwrite(&shine,sizeof(float),1,specular_fp);
			
			IndexTriple indexTemp = triTemp[i].getTriangleIndices();
			fwrite(&vertices[indexTemp.getA()],sizeof(Vertex),1,vertices_fp);
			fwrite(&vertices[indexTemp.getB()],sizeof(Vertex),1,vertices_fp);
			fwrite(&vertices[indexTemp.getC()],sizeof(Vertex),1,vertices_fp);

			if(NULL == normals)
			{ 
			   int temp = -1;
			   fwrite(&temp,sizeof(int),1,normalFlag_fp);
			}
			else
			{
			   int temp = 0;			   
			   fwrite(&temp,sizeof(int),1,normalFlag_fp);
			   indexTemp = triTemp[i].getNormalIndices();
               fwrite(&normals[indexTemp.getA()],sizeof(Normal),1,normal_fp);
			   fwrite(&normals[indexTemp.getB()],sizeof(Normal),1,normal_fp);
			   fwrite(&normals[indexTemp.getC()],sizeof(Normal),1,normal_fp);
			}
            
			if(NULL != texturcoord)
			{
				if(texturcoord->getUvCoordNum() < 0)
				{
					int temp = -1;
					fwrite(&temp,sizeof(int),1,texFlag_fp);
				}
				else
				{
					textureIndex++;
					indexTemp = triTemp[i].getTextureCoordIndices();
					UVCoordinate* uvCoord = texturcoord->getUvCoord();
					fwrite(&textureIndex,sizeof(int),1,texFlag_fp);
					fwrite(&uvCoord[indexTemp.getA()],sizeof(UVCoordinate),1,texuv_fp);
					fwrite(&uvCoord[indexTemp.getB()],sizeof(UVCoordinate),1,texuv_fp);
					fwrite(&uvCoord[indexTemp.getC()],sizeof(UVCoordinate),1,texuv_fp);
					int picIndex = -1;
					for(vector<string>::iterator iter = picName.begin();iter != picName.end();iter++)
					{
                        picIndex++;
						if(*iter == texturcoord->getTexturePicFileName())
							break;
					}
                    fwrite(&picIndex,sizeof(picIndex),1,texuv_fp);
					
				}
               

			}
			

			
		}

		

		meshPointer =  meshPointer->getNextMember();       
	}

	if(!picName.empty())
	{  
		int ppmNum = picName.size();
		fwrite(&ppmNum,sizeof(int),1,ppminf_fp);
		vector<int> ppmResolution;
		for(vector<string>::iterator iter = picName.begin();iter != picName.end();iter++)
		{
			setRgb(*iter,ppmrgb_fp,ppminf_fp);
		}
	}
	else
	{   
		int ppmNum = 0;
        fwrite(&ppmNum,sizeof(int),1,ppminf_fp);
	}
	

	fclose(vertices_fp);
	fclose(diffuse_fp);
	fclose(specular_fp);
	fclose(ambient_fp);
	fclose(normalFlag_fp);
	fclose(normal_fp);
	fclose(texFlag_fp);
	fclose(ppminf_fp);
	fclose(ppmrgb_fp);
	fclose(texuv_fp);


}

void Scene::outputFiles()
{
	string path;
	if("" == this->name)
	{
		cout<<"scene is empty!"<<endl;
		return;
	}
	else
	{
		path = ".\\"+this->name;
		_mkdir(path.c_str());
	}

	this->outputLight(path);
	//this->output(path);
	this->output_texnode(path);
}

Scene::~Scene()
{
  if(NULL != this->lamp)
  {
	  LampArray* lampPointer = this->lamp;
	  while(NULL != lampPointer)
	  {   
		  LampArray* temp = lampPointer;
		  delete lampPointer->getLamp();
		  lampPointer = lampPointer->getNextMember();
		  delete temp;
	  }
  }

  if(NULL != this->mesh)
  {
	  MeshArray* meshPointer = this->mesh;
	  while(NULL != meshPointer)
	  {
		  MeshArray* temp = meshPointer;
		  delete meshPointer->getMesh();
		  meshPointer = meshPointer->getNextMember();
		  delete temp;
	  }
  }
}