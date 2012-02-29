#ifndef _INTER_H_
#define _INTER_H_

#ifdef __cplusplus
extern "C" {
#endif

void vertex( double, double, double);
void lamp( double, double, double);
void lamp_rgb( double, double, double, double, double, double);
void vertices_sec_head(long);
void normals_sec_head(long);
void texturecoords_sec_head(long);
void triangles_sec_head(long);
void normal(double, double, double);
void texturecoord(double, double);
void vertex_index(long, long, long);
void normal_index(long, long, long);
void texture_coord_index(long, long, long);
void material(double, double, double, double, double, double, double, double, double, double, double, double);
void static_tran(double, double, double, double, double, double, double, double, double, double);
void transls_sec_head(long);
void rots_sec_head(long);
void scales_sec_head(long);
void visibilities_sec_head(long);
void mesh();
void triangle_mesh(char *);
void triangle_mesh_end();
void set_material();
void set_transform();
void set_keyframe();
void mesh_and_mesh();

#ifdef __cplusplus
}
#endif

#endif
