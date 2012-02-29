%{
#include "parser.h"
#include "inter.h"
%}

%union {
    double  dbval;
    long    intval;
    char    * string;
}


%token V
%token FROM
%token AT
%token UP
%token ANGLE
%token HITHER
%token RESOLUSION
%token FM

%token B
%token AM
%token L
%token XS
%token X
%token K
%token A
%token M
%token VERTICES
%token NORMALS
%token TEXTURECOORDS
%token TRIANGLES

%token TRANSL
%token ROT
%token SCALE
%token VISIBILITY

%token <string> NUMERAL
%token <string> SOMENAME

%token EOL

%type <dbval> dbvalue
%type <intval> intvalue
%type <string> somename

%%

element:        /* nothing */
            |   element background_color
            |   element viewpoint
            |   element g_amb_light
            |   element lamp
            |   element submesh             { mesh(); }
            |   element keyframe       
            |   element animation       
            ;
background_color:
                B dbvalue dbvalue dbvalue EOL
            ;
viewpoint:      V EOL v_param_list 
            ;
v_param_list:   v_param
            |   v_param_list v_param
            ;
v_param:        FROM dbvalue dbvalue dbvalue EOL
            |   AT dbvalue dbvalue dbvalue EOL
            |   UP dbvalue dbvalue dbvalue EOL
            |   ANGLE dbvalue EOL
            |   HITHER dbvalue EOL
            |   RESOLUSION dbvalue dbvalue EOL
            ;
g_amb_light:    AM dbvalue dbvalue dbvalue EOL
            ;
lamp:           L dbvalue dbvalue dbvalue EOL   { lamp($2, $3, $4); }
            |   L dbvalue dbvalue dbvalue dbvalue dbvalue dbvalue EOL { lamp_rgb($2, $3, $4, $5, $6, $7); }
            ;
submesh:        triangle_mesh                   { triangle_mesh_end(); }
            |   submesh submesh                 { mesh_and_mesh(); }
            |   material submesh                { set_material(); }
            |   transform '{' EOL submesh '}' EOL   { set_transform(); }
            |   transform '{' EOL submesh keyframe '}' EOL  { set_transform(); set_keyframe(); }
            ;
triangle_mesh:  triangle_mesh_naked                             
            |   triangle_mesh_with_normals                      
            |   triangle_mesh_with_texturecoords                
            |   triangle_mesh_with_normals_and_texturecoords    
            ;
triangle_mesh_naked:                mesh_tag                                   
                                    vertices_sec
                                    triangles_sec_naked
            ;
triangle_mesh_with_normals:         mesh_tag
                                    vertices_sec
                                    normals_sec
                                    triangles_sec_with_normals
            ;
triangle_mesh_with_texturecoords:   mesh_tag
                                    vertices_sec
                                    texturecoords_sec
                                    triangles_sec_with_texturecoords
            ;
triangle_mesh_with_normals_and_texturecoords:   mesh_tag
                                                vertices_sec
                                                normals_sec
                                                texturecoords_sec
                                                triangles_sec_with_normals_and_texturecoords
            ;
mesh_tag:       M EOL                   { triangle_mesh(bname); }
            ;
vertices_sec:   VERTICES intvalue EOL   { vertices_sec_head($2); }
                        vertices
            ;
vertices:       vertex
            |   vertices vertex
            ;
vertex:         dbvalue dbvalue dbvalue EOL { vertex($1, $2, $3); }
            ;
normals_sec:    NORMALS intvalue EOL        { normals_sec_head($2); }
                normals
            ;
normals:        normal
            |   normals normal
            ;
normal:         dbvalue dbvalue dbvalue EOL     { normal($1, $2, $3); }
            ;
texturecoords_sec:      TEXTURECOORDS intvalue somename EOL  { texturecoords_sec_head($2); }
                        texturecoords
            ;
texturecoords:  texturecoord
            |   texturecoords texturecoord
            ;
texturecoord:   dbvalue dbvalue EOL                 { texturecoord($1, $2); }
            ;
triangles_sec_naked:  TRIANGLES intvalue EOL         { triangles_sec_head($2); }
                        triangles_naked
            ;
triangles_naked:        triangle_naked
            |           triangles_naked triangle_naked
            ;
triangles_sec_with_normals:  TRIANGLES intvalue EOL               { triangles_sec_head($2); }         
                        triangles_with_normals
            ;
triangles_with_normals:        triangle_with_normals
            |           triangles_with_normals triangle_with_normals
            ;
triangles_sec_with_texturecoords:  TRIANGLES intvalue EOL         { triangles_sec_head($2); }
                        triangles_with_texturecoords
            ;
triangles_with_texturecoords:        triangle_with_texturecoords
            |           triangles_with_texturecoords triangle_with_texturecoords
            ;
triangles_sec_with_normals_and_texturecoords:  TRIANGLES intvalue EOL            { triangles_sec_head($2); }
                        triangles_with_normals_and_texturecoords
            ;
triangles_with_normals_and_texturecoords:        triangle_with_normals_and_texturecoords
            |           triangles_with_normals_and_texturecoords triangle_with_normals_and_texturecoords
            ;
triangle_naked:   
                vertex_index                                            EOL
            ;
triangle_with_normals:   
                vertex_index    normal_index                            EOL
            ;
triangle_with_texturecoords:               
                vertex_index                    texture_coord_index     EOL
            ;
triangle_with_normals_and_texturecoords:
                vertex_index    normal_index    texture_coord_index     EOL
            ;
vertex_index:   intvalue intvalue intvalue          { vertex_index($1, $2, $3); }
            ;
normal_index:   intvalue intvalue intvalue          { normal_index($1, $2, $3); }
            ;
texture_coord_index:   intvalue intvalue intvalue   { texture_coord_index($1, $2, $3); }
            ;
material:       FM dbvalue dbvalue dbvalue
                        dbvalue dbvalue dbvalue
                        dbvalue dbvalue dbvalue
                        dbvalue dbvalue dbvalue EOL
                        /* single line */
                        { material($2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13); }
            |   FM dbvalue dbvalue dbvalue EOL
                        dbvalue dbvalue dbvalue EOL
                        dbvalue dbvalue dbvalue EOL
                        dbvalue dbvalue dbvalue EOL
                        /* four lines */
                        { material($2, $3, $4, $6, $7, $8, $10, $11, $12, $14, $15, $16); }
            ;
transform:      static_tran
            |   dynamic_tran
            ;
static_tran:    XS dbvalue dbvalue dbvalue EOL
                        dbvalue dbvalue dbvalue dbvalue EOL
                        dbvalue dbvalue dbvalue EOL
                { static_tran($2, $3, $4,
                                $6, $7, $8, $9,
                                $11, $12, $13); }
            ;

dynamic_tran:   X somename EOL
            ;
keyframe:       K somename EOL
                        '{' EOL
                        dynamic_tran
                        '}' EOL
            ;
dynamic_tran:   transls_sec
            |   rots_sec
            |   scales_sec
            |   visibilities_sec
            |   dynamic_tran transls_sec
            |   dynamic_tran rots_sec
            |   dynamic_tran scales_sec
            |   dynamic_tran visibilities_sec
            ;
transls_sec:    TRANSL intvalue EOL             { transls_sec_head($2); }
                        transls
            ;
rots_sec:       ROT intvalue EOL                { rots_sec_head($2); }
                        rots
            ;
scales_sec:     SCALE intvalue EOL              { scales_sec_head($2); }
                        scales
            ;
visibilities_sec:   VISIBILITY intvalue EOL         { visibilities_sec_head($2); }
                        visibilities
            ;
transls:        transl
            |   transls transl
            ;
transl:         dbvalue dbvalue dbvalue dbvalue dbvalue dbvalue dbvalue EOL
            ;
rots:           rot
            |   rots rot
            ;
rot:            dbvalue dbvalue dbvalue dbvalue dbvalue dbvalue dbvalue dbvalue EOL
            ;
scales:         scale
            |   scales scale
            ;
scale:          dbvalue dbvalue dbvalue dbvalue dbvalue dbvalue dbvalue EOL            
            ; 
visibilities:   visibility
            |   visibilities visibility
            ;
visibility:     dbvalue dbvalue EOL            
            ;
animation:      A dbvalue dbvalue intvalue EOL
            ;
somename:       SOMENAME { $$ = $1; }
            ;
intvalue:       NUMERAL { $$ = atol($1); }
            ;
dbvalue:        NUMERAL { $$ = atof($1); }
            ;
%%


yyerror (s)  /* 语法分析函数yyparse()出错时调用该函数 */
 	char *s;
{
   	warning(s, bname);
}

warning(s, t)
	char *s, *t;
{
	fprintf(stderr, "%s: %s", progname, s);
	if (t)
		fprintf(stderr, " %s", t);
	fprintf(stderr, " near line %ld\n", lineno);
}

execerror(s, t)	/* recover from run-time error */
	char *s, *t;
{
	warning(s, t);
	longjmp(begin, 0);
}
