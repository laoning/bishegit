#ifndef _SCENEMANAGER_H_
#define _SCENEMANAGER_H_

#include<iostream>
#include<string>
using namespace std;

//光源类型
enum LightSourceType{Point_light,Area_light,Line_light};
class Mesh;
//三元组向量
class Vector3d
{
	private:
		float x;
		float y;
		float z;
    public:
	    Vector3d():x(0.0f),y(0.0f),z(0.0f){}
        Vector3d(float,float,float);
		void initialize();
       	void setX(float);
		void setY(float);
		void setZ(float);
		void setVector(float,float,float);
		float getX()const;
		float getY()const;
		float getZ()const;
        Vector3d getVector()const;
};

typedef Vector3d Vertex;
typedef Vector3d Normal;

//绕任意轴旋转
class RotationOnArbitraryAxis
{
    private:
	    float angle;
		Vector3d axis;
    public:		
		RotationOnArbitraryAxis():angle(0.0f),axis(){}
        RotationOnArbitraryAxis(float,Vector3d&);
		void initialize();
		void setAngle(float);
		void setAxis(Vector3d&);
		void setAxis(float,float,float);
		float getAngle()const;
		Vector3d getAxis()const;
};

//静态变换
class StaticTransform
{
	private:
		Vector3d scale;
		RotationOnArbitraryAxis rotation;
		Vector3d translation;
	public:
		StaticTransform():scale(1.0f,1.0f,1.0f),rotation(),translation(){}
		StaticTransform(Vector3d&,RotationOnArbitraryAxis&,Vector3d&);
		void initialize();
		void setScale(Vector3d&);
		void setScale(float,float,float);
		void setRotation(RotationOnArbitraryAxis&);
		void setRotation(float,float,float,float);
		void setTranslation(Vector3d&);
		void setTranslation(float,float,float);
        Vector3d getScale()const;
		Vector3d getTranslation()const;
        RotationOnArbitraryAxis getRotation()const;
		
};

//相机视点
class Camera
{
	private:
		Vector3d location;
		Vector3d direction;
		Vector3d up;
		float angle;
		int resolutionX;
		int resolutionY;
    public:
		Camera():angle(63.434948823f),resolutionX(800),resolutionY(600){}
		void setLocation(Vector3d& new_var);
        void setLocation(float,float,float);		
		void setDirection(Vector3d& new_var);
		void setDirection(float,float,float);
		void setUp(Vector3d& new_var);
		void setUp(float,float,float);
		void setAngle(float new_var);
		void setResolutionX(int new_var);
		void setResolutionY(int new_var);
        Vector3d getLocation()const;
		Vector3d getDirectrion()const;
		Vector3d getUp()const;
		float getAngle()const;
		int getResolotionX()const;
		int getResolotionY()const;
};

//色彩向量
class Color
{
	private:
		float red;
		float green;
		float blue;
	public:			
		Color():red(0.0f),green(0.0f),blue(0.0f){}
		Color(float,float,float);
		void initialize();
		void setRed(float);
		void setGreen(float);
		void setBlue(float);
		void setColor(float,float,float);
		float getRed()const;
		float getGreen()const;
		float getBlue()const;
};

//光源类
class Lamp
{
	private:
		LightSourceType type;
		Color brightness;
		Vector3d location;
		int id;
		string name;
    public:
		Lamp():type(Point_light),id(-1),name(){}
		void setLightType(LightSourceType new_var );
		void setBrightness(Color& new_var );
		void setBrightness(float,float,float);
		void setLocation(Vector3d& new_var );
		void setLocation(float,float,float);
		void setName(string new_var );
        LightSourceType getLightType()const;
		Color getBrightness()const;
		Vector3d getLocation()const;
		string getName()const;
};


//面索引向量
class IndexTriple
{
private:
	long a;
	long b;
	long c;
public:		
	IndexTriple():a(-1),b(-1),c(-1){}
	IndexTriple(long,long,long);
	void initialize();
	void setA(long);
	void setB(long);
	void setC(long);
	void setIndexTriple(long,long,long);
	long getA()const;
	long getB()const;
	long getC()const;
};

//纹理图片的信息TextNode
struct TexNode
{  
	unsigned char* m_data; // 图像RGBA数据
	int m_width;  // 位图宽度
	int m_height;  // 位图高度
	TexNode():m_height(0),m_width(0),m_data(NULL){}
};

//纹理坐标
class UVCoordinate
{
	private:
		float u;
		float v;
	public:		
		UVCoordinate():u(0.0f),v(0.0f){}
		UVCoordinate(float,float);
		void setU(float);
		void setV(float);
		void setUV(float,float);
		float getU()const;
		float getV()const;
};


//纹理信息
class TextureMap
{
	private:
		string texturePicFileName;
		int uvCoordNum;
		int width;
		int height;
		int idx;
		unsigned char* rgba;
		UVCoordinate* uvCoord;
		
	public:	   
		TextureMap():texturePicFileName(),width(-1),height(-1),uvCoordNum(-1),uvCoord(NULL),rgba(NULL),idx(-1){}
		~TextureMap();
		TextureMap(const TextureMap&);
        TextureMap& operator=(const TextureMap&);
        void setTextruePicFileName(string);
		void setUvCoordNum(int);
		void setUvCoord(UVCoordinate&);
		void setUvCoord(float,float);
		void setResolution(int,int);
		void setRGBA(unsigned char*);
		string getTexturePicFileName()const;
		int getUvCoordNum()const;
		int getIdx()const;
		int getWidth()const;
		int getHeight()const;
		unsigned char* getRGBA()const;
		UVCoordinate* getUvCoord()const;
};


//三角片信息
class Triangle
{
	private:
		IndexTriple triangleIndices;
		IndexTriple normalIndices;
		IndexTriple textureCoordIndices;
	public:		
		Triangle():triangleIndices(),normalIndices(),textureCoordIndices(){}
		Triangle(IndexTriple&,IndexTriple&,IndexTriple&);
		void setTriangleIndices(IndexTriple&);
		void setNormalIndices(IndexTriple&);
		void setTextureCoordIndices(IndexTriple&);
		void setTriangle(IndexTriple&,IndexTriple&,IndexTriple&);
        IndexTriple getTriangleIndices()const;
        IndexTriple getNormalIndices()const;
		IndexTriple getTextureCoordIndices()const;
};



//三角片mesh
class TriangleMesh
{
	private:
		int verticesNum;
		int normalsNum;
		int triangleNum;
		int verticesIdx;
		int normalsIdx;
		int triangleIdx;

		Vertex* vertices;
		Normal* normals;		
		Triangle* triangles;
		TextureMap texture;
		
	public:
		TriangleMesh():verticesNum(-1),normalsNum(-1),triangleNum(-1),verticesIdx(-1),normalsIdx(-1),triangleIdx(-1),vertices(NULL),normals(NULL),texture(),triangles(NULL){}
        ~TriangleMesh();
        TriangleMesh(const TriangleMesh&);
        TriangleMesh& operator=(const TriangleMesh&);
		void setVerticesNum(int);
		void setNormalsNum(int);
		void setTriangleNum(int);
		void setUvCoordNum(int);
		void setVertices(Vertex& new_var);
        void setVertices(float,float,float);
		void setNormals(Normal& new_var);
		void setNormals(float,float,float);
		void setTexturePicName(string);
		void setTextruecoords(UVCoordinate& new_var);
		void setTextruecoords(float,float);
		void setTriangles(Triangle& new_var);
		void setTriangles(IndexTriple&,IndexTriple&,IndexTriple&);
		void setTexture(TexNode &tnode);
        Vertex* getVertices()const;
        Normal* getNormals()const;
		TextureMap* getTexture();
		Triangle* getTriangles()const;
		int getTriangleNum()const;
		unsigned char* getTex_RGBA()const;
};

//材质类
class Material
{
	private:
		Color ambientColor;
		Color diffuseColor;
		Color specularColor;
		float shine;
		float transmittance;
		float refractionIndex;
	public:		
		Material():shine(0.0f),transmittance(0.0f),refractionIndex(0.0f){}
        Material(Color&,Color&,Color&,float,float,float);
		void initialize();
		void setAmbientColor(Color&);
        void setAmbientColor(float,float,float);
		void setDiffuseColor(Color&);
        void setDiffuseColor(float,float,float);
		void setSpecularColor(Color&);
        void setSpecularColor(float,float,float);
		void setShine(float);
		void setTransmittance(float);
		void setRefractionIndex(float);
		Color getAmbientColor()const;
		Color getDiffuseColor()const;
		Color getSpecularColor()const;
		float getShine()const;
		float getTransmittance()const;
		float getRefractionIndex()const;

};


//LampArray类，用于存储Scene中的Lamp
class LampArray
{
	private:
		Lamp* lampMember;
		LampArray* NextMember;
	public:
		LampArray():lampMember(NULL),NextMember(NULL){}
		void setLampMember(Lamp* lamp);
		void setNextMember(LampArray* next);
		Lamp* getLamp()const;
		LampArray* getNextMember()const;
};

//MeshArray类，用于存储Scene中的mesh
class MeshArray
{
	private:
		Mesh* meshMember;
		MeshArray* NextMember;
	public:	
		MeshArray():meshMember(NULL),NextMember(NULL){}
		void setmeshMember(Mesh* mesh);
		void setNextMember(MeshArray* next);
		Mesh* getMesh()const;
		MeshArray* getNextMember()const;	
};


//mesh类
class Mesh
{
	private:
		int id;
		string name;
		Material material;
		TriangleMesh* triangleMesh;
		StaticTransform staticTransform;
		Mesh* submesh;
		MeshArray* subMeshArray;
	public:
		Mesh():id(-1),submesh(NULL),subMeshArray(NULL){}
		~Mesh();
		void setTriangleMesh ( TriangleMesh* new_var );
		void setStaticTransform ( StaticTransform& new_var );
		void setMaterial ( Material& new_var );
		void setSubmesh ( Mesh* new_var );
		void setName ( string new_var );
		void setSubMeshArray(Mesh*);
		string getName()const;
		Material getMaterial()const;
		TriangleMesh* getTriangleMesh()const;
		StaticTransform getStaticTransform()const;
		Mesh* getSubmesh()const;	
		MeshArray* getSubMeshArray()const;
};

//Scene场景类
class Scene
{
	private:
		MeshArray* mesh;
		LampArray* lamp;
		Camera camera;
		Color globalAmbient;
		int id;
		string name;
		void outputLight(string)const;
		void output(string);
		void eatComments(FILE*& );
		void eatWhitespace(FILE*&);
		void setRgb(string picName,FILE*& ppmrgb_fp,FILE* &ppminf_fp);
		void output_texnode(string path);
	public:
		Scene():name("scene"),id(-1),mesh(NULL),lamp(NULL){}
		~Scene();		
		int create ( string name );
		int setGlobalAmbientLight ( Color& light );
		int setCamera(Camera&);
		int addLamp (Lamp* lamp );
		int setViewpoint ( Camera& camera );
		int addMesh (Mesh* mesh );
		void setLampArr(LampArray* lamp);
		
        MeshArray* getMesh()const;
        LampArray* getLamp()const;
		Camera getCamera()const;
		Color getGlobalAmbient()const;
		string getName()const;
		void outputFiles();
};
#endif