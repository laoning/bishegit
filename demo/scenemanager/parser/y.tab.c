/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton implementation for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "2.3"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Using locations.  */
#define YYLSP_NEEDED 0



/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     V = 258,
     FROM = 259,
     AT = 260,
     UP = 261,
     ANGLE = 262,
     HITHER = 263,
     RESOLUSION = 264,
     FM = 265,
     B = 266,
     AM = 267,
     L = 268,
     XS = 269,
     X = 270,
     K = 271,
     A = 272,
     M = 273,
     VERTICES = 274,
     NORMALS = 275,
     TEXTURECOORDS = 276,
     TRIANGLES = 277,
     TRANSL = 278,
     ROT = 279,
     SCALE = 280,
     VISIBILITY = 281,
     NUMERAL = 282,
     SOMENAME = 283,
     EOL = 284
   };
#endif
/* Tokens.  */
#define V 258
#define FROM 259
#define AT 260
#define UP 261
#define ANGLE 262
#define HITHER 263
#define RESOLUSION 264
#define FM 265
#define B 266
#define AM 267
#define L 268
#define XS 269
#define X 270
#define K 271
#define A 272
#define M 273
#define VERTICES 274
#define NORMALS 275
#define TEXTURECOORDS 276
#define TRIANGLES 277
#define TRANSL 278
#define ROT 279
#define SCALE 280
#define VISIBILITY 281
#define NUMERAL 282
#define SOMENAME 283
#define EOL 284




/* Copy the first part of user declarations.  */
#line 1 "parser.y"

#include "parser.h"
#include "inter.h"


/* Enabling traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* Enabling the token table.  */
#ifndef YYTOKEN_TABLE
# define YYTOKEN_TABLE 0
#endif

#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
#line 6 "parser.y"
{
    double  dbval;
    long    intval;
    char    * string;
}
/* Line 187 of yacc.c.  */
#line 165 "y.tab.c"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif



/* Copy the second part of user declarations.  */


/* Line 216 of yacc.c.  */
#line 178 "y.tab.c"

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#elif (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
typedef signed char yytype_int8;
#else
typedef short int yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(msgid) dgettext ("bison-runtime", msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(msgid) msgid
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(e) ((void) (e))
#else
# define YYUSE(e) /* empty */
#endif

/* Identity function, used to suppress warnings about constant conditions.  */
#ifndef lint
# define YYID(n) (n)
#else
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static int
YYID (int i)
#else
static int
YYID (i)
    int i;
#endif
{
  return i;
}
#endif

#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#     ifndef _STDLIB_H
#      define _STDLIB_H 1
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's `empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (YYID (0))
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined _STDLIB_H \
       && ! ((defined YYMALLOC || defined malloc) \
	     && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef _STDLIB_H
#    define _STDLIB_H 1
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
	 || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss;
  YYSTYPE yyvs;
  };

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

/* Copy COUNT objects from FROM to TO.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(To, From, Count) \
      __builtin_memcpy (To, From, (Count) * sizeof (*(From)))
#  else
#   define YYCOPY(To, From, Count)		\
      do					\
	{					\
	  YYSIZE_T yyi;				\
	  for (yyi = 0; yyi < (Count); yyi++)	\
	    (To)[yyi] = (From)[yyi];		\
	}					\
      while (YYID (0))
#  endif
# endif

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack)					\
    do									\
      {									\
	YYSIZE_T yynewbytes;						\
	YYCOPY (&yyptr->Stack, Stack, yysize);				\
	Stack = &yyptr->Stack;						\
	yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
	yyptr += yynewbytes / sizeof (*yyptr);				\
      }									\
    while (YYID (0))

#endif

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  2
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   327

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  32
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  71
/* YYNRULES -- Number of rules.  */
#define YYNRULES  113
/* YYNRULES -- Number of states.  */
#define YYNSTATES  295

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   284

#define YYTRANSLATE(YYX)						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    30,     2,    31,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint16 yyprhs[] =
{
       0,     0,     3,     4,     7,    10,    13,    16,    19,    22,
      25,    31,    35,    37,    40,    46,    52,    58,    62,    66,
      71,    77,    83,    92,    94,    97,   100,   107,   115,   117,
     119,   121,   123,   127,   132,   137,   143,   146,   147,   153,
     155,   158,   163,   164,   170,   172,   175,   180,   181,   188,
     190,   193,   197,   198,   204,   206,   209,   210,   216,   218,
     221,   222,   228,   230,   233,   234,   240,   242,   245,   248,
     252,   256,   261,   265,   269,   273,   288,   306,   308,   310,
     325,   329,   338,   340,   342,   344,   346,   349,   352,   355,
     358,   359,   365,   366,   372,   373,   379,   380,   386,   388,
     391,   400,   402,   405,   415,   417,   420,   429,   431,   434,
     438,   444,   446,   448
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int8 yyrhs[] =
{
      33,     0,    -1,    -1,    33,    34,    -1,    33,    35,    -1,
      33,    38,    -1,    33,    39,    -1,    33,    40,    -1,    33,
      82,    -1,    33,    99,    -1,    11,   102,   102,   102,    29,
      -1,     3,    29,    36,    -1,    37,    -1,    36,    37,    -1,
       4,   102,   102,   102,    29,    -1,     5,   102,   102,   102,
      29,    -1,     6,   102,   102,   102,    29,    -1,     7,   102,
      29,    -1,     8,   102,    29,    -1,     9,   102,   102,    29,
      -1,    12,   102,   102,   102,    29,    -1,    13,   102,   102,
     102,    29,    -1,    13,   102,   102,   102,   102,   102,   102,
      29,    -1,    41,    -1,    40,    40,    -1,    78,    40,    -1,
      79,    30,    29,    40,    31,    29,    -1,    79,    30,    29,
      40,    82,    31,    29,    -1,    42,    -1,    43,    -1,    44,
      -1,    45,    -1,    46,    47,    59,    -1,    46,    47,    51,
      62,    -1,    46,    47,    55,    65,    -1,    46,    47,    51,
      55,    68,    -1,    18,    29,    -1,    -1,    19,   101,    29,
      48,    49,    -1,    50,    -1,    49,    50,    -1,   102,   102,
     102,    29,    -1,    -1,    20,   101,    29,    52,    53,    -1,
      54,    -1,    53,    54,    -1,   102,   102,   102,    29,    -1,
      -1,    21,   101,   100,    29,    56,    57,    -1,    58,    -1,
      57,    58,    -1,   102,   102,    29,    -1,    -1,    22,   101,
      29,    60,    61,    -1,    71,    -1,    61,    71,    -1,    -1,
      22,   101,    29,    63,    64,    -1,    72,    -1,    64,    72,
      -1,    -1,    22,   101,    29,    66,    67,    -1,    73,    -1,
      67,    73,    -1,    -1,    22,   101,    29,    69,    70,    -1,
      74,    -1,    70,    74,    -1,    75,    29,    -1,    75,    76,
      29,    -1,    75,    77,    29,    -1,    75,    76,    77,    29,
      -1,   101,   101,   101,    -1,   101,   101,   101,    -1,   101,
     101,   101,    -1,    10,   102,   102,   102,   102,   102,   102,
     102,   102,   102,   102,   102,   102,    29,    -1,    10,   102,
     102,   102,    29,   102,   102,   102,    29,   102,   102,   102,
      29,   102,   102,   102,    29,    -1,    80,    -1,    81,    -1,
      14,   102,   102,   102,    29,   102,   102,   102,   102,    29,
     102,   102,   102,    29,    -1,    15,   100,    29,    -1,    16,
     100,    29,    30,    29,    81,    31,    29,    -1,    83,    -1,
      85,    -1,    87,    -1,    89,    -1,    81,    83,    -1,    81,
      85,    -1,    81,    87,    -1,    81,    89,    -1,    -1,    23,
     101,    29,    84,    91,    -1,    -1,    24,   101,    29,    86,
      93,    -1,    -1,    25,   101,    29,    88,    95,    -1,    -1,
      26,   101,    29,    90,    97,    -1,    92,    -1,    91,    92,
      -1,   102,   102,   102,   102,   102,   102,   102,    29,    -1,
      94,    -1,    93,    94,    -1,   102,   102,   102,   102,   102,
     102,   102,   102,    29,    -1,    96,    -1,    95,    96,    -1,
     102,   102,   102,   102,   102,   102,   102,    29,    -1,    98,
      -1,    97,    98,    -1,   102,   102,    29,    -1,    17,   102,
     102,   101,    29,    -1,    28,    -1,    27,    -1,    27,    -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,    51,    51,    52,    53,    54,    55,    56,    57,    58,
      61,    63,    65,    66,    68,    69,    70,    71,    72,    73,
      75,    77,    78,    80,    81,    82,    83,    84,    86,    87,
      88,    89,    91,    95,   100,   105,   111,   113,   113,   116,
     117,   119,   121,   121,   124,   125,   127,   129,   129,   132,
     133,   135,   137,   137,   140,   141,   143,   143,   146,   147,
     149,   149,   152,   153,   155,   155,   158,   159,   162,   165,
     168,   171,   173,   175,   177,   179,   185,   192,   193,   195,
     203,   205,   210,   211,   212,   213,   214,   215,   216,   217,
     219,   219,   222,   222,   225,   225,   228,   228,   231,   232,
     234,   236,   237,   239,   241,   242,   244,   246,   247,   249,
     251,   253,   255,   257
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || YYTOKEN_TABLE
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "V", "FROM", "AT", "UP", "ANGLE",
  "HITHER", "RESOLUSION", "FM", "B", "AM", "L", "XS", "X", "K", "A", "M",
  "VERTICES", "NORMALS", "TEXTURECOORDS", "TRIANGLES", "TRANSL", "ROT",
  "SCALE", "VISIBILITY", "NUMERAL", "SOMENAME", "EOL", "'{'", "'}'",
  "$accept", "element", "background_color", "viewpoint", "v_param_list",
  "v_param", "g_amb_light", "lamp", "submesh", "triangle_mesh",
  "triangle_mesh_naked", "triangle_mesh_with_normals",
  "triangle_mesh_with_texturecoords",
  "triangle_mesh_with_normals_and_texturecoords", "mesh_tag",
  "vertices_sec", "@1", "vertices", "vertex", "normals_sec", "@2",
  "normals", "normal", "texturecoords_sec", "@3", "texturecoords",
  "texturecoord", "triangles_sec_naked", "@4", "triangles_naked",
  "triangles_sec_with_normals", "@5", "triangles_with_normals",
  "triangles_sec_with_texturecoords", "@6", "triangles_with_texturecoords",
  "triangles_sec_with_normals_and_texturecoords", "@7",
  "triangles_with_normals_and_texturecoords", "triangle_naked",
  "triangle_with_normals", "triangle_with_texturecoords",
  "triangle_with_normals_and_texturecoords", "vertex_index",
  "normal_index", "texture_coord_index", "material", "transform",
  "static_tran", "dynamic_tran", "keyframe", "transls_sec", "@8",
  "rots_sec", "@9", "scales_sec", "@10", "visibilities_sec", "@11",
  "transls", "transl", "rots", "rot", "scales", "scale", "visibilities",
  "visibility", "animation", "somename", "intvalue", "dbvalue", 0
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     123,   125
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    32,    33,    33,    33,    33,    33,    33,    33,    33,
      34,    35,    36,    36,    37,    37,    37,    37,    37,    37,
      38,    39,    39,    40,    40,    40,    40,    40,    41,    41,
      41,    41,    42,    43,    44,    45,    46,    48,    47,    49,
      49,    50,    52,    51,    53,    53,    54,    56,    55,    57,
      57,    58,    60,    59,    61,    61,    63,    62,    64,    64,
      66,    65,    67,    67,    69,    68,    70,    70,    71,    72,
      73,    74,    75,    76,    77,    78,    78,    79,    79,    80,
      81,    82,    81,    81,    81,    81,    81,    81,    81,    81,
      84,    83,    86,    85,    88,    87,    90,    89,    91,    91,
      92,    93,    93,    94,    95,    95,    96,    97,    97,    98,
      99,   100,   101,   102
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     0,     2,     2,     2,     2,     2,     2,     2,
       5,     3,     1,     2,     5,     5,     5,     3,     3,     4,
       5,     5,     8,     1,     2,     2,     6,     7,     1,     1,
       1,     1,     3,     4,     4,     5,     2,     0,     5,     1,
       2,     4,     0,     5,     1,     2,     4,     0,     6,     1,
       2,     3,     0,     5,     1,     2,     0,     5,     1,     2,
       0,     5,     1,     2,     0,     5,     1,     2,     2,     3,
       3,     4,     3,     3,     3,    14,    17,     1,     1,    14,
       3,     8,     1,     1,     1,     1,     2,     2,     2,     2,
       0,     5,     0,     5,     0,     5,     0,     5,     1,     2,
       8,     1,     2,     9,     1,     2,     8,     1,     2,     3,
       5,     1,     1,     1
};

/* YYDEFACT[STATE-NAME] -- Default rule to reduce with in state
   STATE-NUM when YYTABLE doesn't specify something else to do.  Zero
   means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       2,     0,     1,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     3,     4,     5,
       6,     7,    23,    28,    29,    30,    31,     0,     0,     0,
      77,    78,     8,    82,    83,    84,    85,     9,     0,   113,
       0,     0,     0,     0,     0,   111,     0,     0,     0,    36,
     112,     0,     0,     0,     0,    24,     0,     0,    25,     0,
      86,    87,    88,    89,     0,     0,     0,     0,     0,     0,
      11,    12,     0,     0,     0,     0,     0,    80,     0,     0,
      90,    92,    94,    96,     0,     0,     0,     0,     0,     0,
      32,     0,     0,     0,     0,     0,     0,     0,    13,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
      37,     0,     0,     0,     0,     0,    33,     0,    34,     0,
       0,     0,     0,    17,    18,     0,     0,     0,    10,    20,
      21,     0,     0,     0,   110,    91,    98,     0,    93,   101,
       0,    95,   104,     0,    97,   107,     0,     0,    42,     0,
      52,     0,     0,    35,     0,     0,     0,     0,     0,     0,
      19,     0,     0,     0,     0,     0,    99,     0,   102,     0,
     105,     0,   108,     0,    38,    39,     0,     0,    47,     0,
      56,     0,    60,    26,     0,    14,    15,    16,     0,     0,
       0,     0,     0,     0,     0,     0,   109,    40,     0,    43,
      44,     0,     0,    53,    54,     0,     0,     0,    64,     0,
      27,     0,     0,    22,     0,    81,     0,     0,     0,     0,
      45,     0,    48,    49,     0,    55,    68,     0,    57,    58,
       0,     0,    61,    62,     0,     0,     0,     0,     0,     0,
       0,    41,     0,    50,     0,    72,    59,     0,     0,    65,
      66,     0,    63,     0,     0,     0,     0,     0,     0,     0,
       0,    46,    51,    69,     0,    67,     0,    70,     0,     0,
       0,     0,     0,     0,     0,    73,     0,    74,     0,     0,
       0,   100,     0,   106,    71,     0,     0,     0,   103,     0,
      75,    79,     0,     0,    76
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     1,    17,    18,    70,    71,    19,    20,    55,    22,
      23,    24,    25,    26,    27,    57,   147,   174,   175,    88,
     177,   199,   200,    89,   202,   222,   223,    90,   179,   203,
     116,   207,   228,   118,   209,   232,   153,   231,   249,   204,
     229,   233,   250,   205,   247,   253,    28,    29,    30,    31,
      32,    33,   106,    34,   107,    35,   108,    36,   109,   135,
     136,   138,   139,   141,   142,   144,   145,    37,    46,   206,
     137
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -184
static const yytype_int16 yypact[] =
{
    -184,   279,  -184,   -24,   -10,   -10,   -10,   -10,   -10,     6,
       6,   -10,   -11,    -2,    -2,    -2,    -2,  -184,  -184,  -184,
    -184,    32,  -184,  -184,  -184,  -184,  -184,    16,    32,    23,
    -184,    -3,  -184,  -184,  -184,  -184,  -184,  -184,   147,  -184,
     -10,   -10,   -10,   -10,   -10,  -184,    30,    38,   -10,  -184,
    -184,    44,    50,    58,    62,    32,    -2,    64,    32,    63,
    -184,  -184,  -184,  -184,   -10,   -10,   -10,   -10,   -10,   -10,
     147,  -184,   -10,   -10,   -10,   -10,   -10,  -184,    67,    -2,
    -184,  -184,  -184,  -184,    65,    -2,    -2,    -2,    10,    77,
    -184,    32,   -10,   -10,   -10,    71,    72,   -10,  -184,    14,
      73,    78,    25,    79,    81,    82,   -10,   -10,   -10,   -10,
    -184,    84,     6,    85,    -2,    97,  -184,    -2,  -184,   296,
     -10,   -10,   -10,  -184,  -184,    91,   -10,   -10,  -184,  -184,
    -184,   -10,   -10,     4,  -184,   -10,  -184,   -10,   -10,  -184,
     -10,   -10,  -184,   -10,   -10,  -184,   -10,   -10,  -184,    92,
    -184,    95,    -2,  -184,    96,   100,    75,   101,   102,   103,
    -184,   -10,   -10,   -10,   -10,    52,  -184,   -10,  -184,   -10,
    -184,   -10,  -184,   106,   -10,  -184,   -10,   -10,  -184,    -2,
    -184,   109,  -184,  -184,   112,  -184,  -184,  -184,   -10,   -10,
     115,   -10,   116,   -10,   -10,   -10,  -184,  -184,   -10,   -10,
    -184,   -10,   -10,    -2,  -184,   132,    -2,    -2,  -184,    -2,
    -184,   133,   -10,  -184,   -10,  -184,   -10,   -10,   -10,   135,
    -184,   -10,   -10,  -184,   -10,  -184,  -184,    -2,    -2,  -184,
      -2,    -2,    -2,  -184,    -2,   -10,   -10,   137,   -10,   -10,
     -10,  -184,   139,  -184,   140,  -184,  -184,   142,    -2,    -2,
    -184,    -2,  -184,   145,    -2,   -10,   -10,   -10,   -10,   -10,
     -10,  -184,  -184,  -184,    -2,  -184,    -2,  -184,    -2,   -10,
     -10,   -10,   146,   -10,   148,  -184,   149,  -184,   150,   -10,
     -10,  -184,   151,  -184,  -184,   -10,   152,   153,  -184,   -10,
    -184,  -184,   -10,   154,  -184
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -184,  -184,  -184,  -184,  -184,    56,  -184,  -184,     5,  -184,
    -184,  -184,  -184,  -184,  -184,  -184,  -184,  -184,     2,  -184,
    -184,  -184,   -13,   104,  -184,  -184,   -34,  -184,  -184,  -184,
    -184,  -184,  -184,  -184,  -184,  -184,  -184,  -184,  -184,    -7,
     -35,   -33,   -49,  -183,   -48,   -64,  -184,  -184,  -184,    74,
      86,   -19,  -184,   -17,  -184,   -16,  -184,   -15,  -184,  -184,
      69,  -184,    68,  -184,    70,  -184,    80,  -184,     3,    -5,
      -4
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If zero, do what YYDEFACT says.
   If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -1
static const yytype_uint16 yytable[] =
{
      40,    41,    42,    43,    44,    38,    21,    48,    51,    52,
      53,    54,    60,    47,    61,    62,    63,    39,    49,     9,
      13,    14,    15,    16,   230,    50,   234,    13,    14,    15,
      16,    86,   114,    58,    45,    56,    72,    73,    74,    75,
      76,    39,     4,   126,    79,   230,     8,     9,   251,   234,
      12,    84,    39,    59,   130,    13,    14,    15,    16,    77,
      92,    93,    94,    95,    96,    97,   251,    78,    99,   100,
     101,   102,   103,    80,   105,    13,    14,    15,    16,    81,
     111,   112,   113,   192,    85,    86,    87,    82,   120,   121,
     122,    83,    91,   125,   110,   127,   119,   104,   131,   117,
     123,   124,   128,   140,   143,   146,   184,   129,   132,   151,
     133,   134,   154,   148,   150,   149,   157,   158,   159,   152,
     160,   178,   161,   162,   180,   182,    98,   163,   164,   183,
     185,   186,   187,   167,   140,   196,   169,   143,   208,   171,
     146,   210,   173,   176,   213,   215,    60,   181,    61,    62,
      63,    64,    65,    66,    67,    68,    69,   188,   189,   190,
     191,   226,   235,   193,   241,   194,   257,   195,   261,   262,
     176,   263,   198,   201,   267,   281,   197,   283,   284,   285,
     288,   290,   291,   294,   211,   212,   220,   214,   243,   216,
     217,   218,   115,   246,   219,   201,   225,   221,   224,   252,
     265,   227,   276,   266,   166,   156,   168,   165,   236,     0,
     237,   170,   238,   239,   240,     0,     0,   242,   224,     0,
     244,     0,   245,     0,   172,   248,     0,     0,     0,   254,
       0,   255,   256,     0,   258,   259,   260,     0,     0,     0,
       0,     0,     0,   264,     0,     0,   248,     0,     0,   268,
       0,   269,   270,   271,   272,   273,   274,     0,     0,   275,
       0,   254,     0,   277,     0,   278,   279,   280,     0,   282,
       0,     0,     0,     0,     0,   286,   287,     0,     0,     2,
       0,   289,     3,     0,     0,   292,     0,     0,   293,     4,
       5,     6,     7,     8,     9,    10,    11,    12,     0,     0,
       0,     0,    13,    14,    15,    16,     4,     0,     0,     0,
       8,     9,    10,     0,    12,     0,     0,     0,     0,    13,
      14,    15,    16,     0,     0,     0,     0,   155
};

static const yytype_int16 yycheck[] =
{
       4,     5,     6,     7,     8,    29,     1,    11,    13,    14,
      15,    16,    31,    10,    31,    31,    31,    27,    29,    15,
      23,    24,    25,    26,   207,    27,   209,    23,    24,    25,
      26,    21,    22,    28,    28,    19,    40,    41,    42,    43,
      44,    27,    10,    29,    48,   228,    14,    15,   231,   232,
      18,    56,    27,    30,    29,    23,    24,    25,    26,    29,
      64,    65,    66,    67,    68,    69,   249,    29,    72,    73,
      74,    75,    76,    29,    79,    23,    24,    25,    26,    29,
      85,    86,    87,    31,    20,    21,    22,    29,    92,    93,
      94,    29,    29,    97,    29,    99,    91,    30,   102,    22,
      29,    29,    29,   107,   108,   109,    31,    29,    29,   114,
      29,    29,   117,    29,    29,   112,   120,   121,   122,    22,
      29,    29,   126,   127,    29,    29,    70,   131,   132,    29,
      29,    29,    29,   137,   138,    29,   140,   141,    29,   143,
     144,    29,   146,   147,    29,    29,   165,   152,   165,   165,
     165,     4,     5,     6,     7,     8,     9,   161,   162,   163,
     164,    29,    29,   167,    29,   169,    29,   171,    29,    29,
     174,    29,   176,   177,    29,    29,   174,    29,    29,    29,
      29,    29,    29,    29,   188,   189,   199,   191,   222,   193,
     194,   195,    88,   228,   198,   199,   203,   201,   202,   232,
     249,   206,   266,   251,   135,   119,   138,   133,   212,    -1,
     214,   141,   216,   217,   218,    -1,    -1,   221,   222,    -1,
     224,    -1,   227,    -1,   144,   230,    -1,    -1,    -1,   234,
      -1,   235,   236,    -1,   238,   239,   240,    -1,    -1,    -1,
      -1,    -1,    -1,   248,    -1,    -1,   251,    -1,    -1,   254,
      -1,   255,   256,   257,   258,   259,   260,    -1,    -1,   264,
      -1,   266,    -1,   268,    -1,   269,   270,   271,    -1,   273,
      -1,    -1,    -1,    -1,    -1,   279,   280,    -1,    -1,     0,
      -1,   285,     3,    -1,    -1,   289,    -1,    -1,   292,    10,
      11,    12,    13,    14,    15,    16,    17,    18,    -1,    -1,
      -1,    -1,    23,    24,    25,    26,    10,    -1,    -1,    -1,
      14,    15,    16,    -1,    18,    -1,    -1,    -1,    -1,    23,
      24,    25,    26,    -1,    -1,    -1,    -1,    31
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,    33,     0,     3,    10,    11,    12,    13,    14,    15,
      16,    17,    18,    23,    24,    25,    26,    34,    35,    38,
      39,    40,    41,    42,    43,    44,    45,    46,    78,    79,
      80,    81,    82,    83,    85,    87,    89,    99,    29,    27,
     102,   102,   102,   102,   102,    28,   100,   100,   102,    29,
      27,   101,   101,   101,   101,    40,    19,    47,    40,    30,
      83,    85,    87,    89,     4,     5,     6,     7,     8,     9,
      36,    37,   102,   102,   102,   102,   102,    29,    29,   102,
      29,    29,    29,    29,   101,    20,    21,    22,    51,    55,
      59,    29,   102,   102,   102,   102,   102,   102,    37,   102,
     102,   102,   102,   102,    30,   101,    84,    86,    88,    90,
      29,   101,   101,   101,    22,    55,    62,    22,    65,    40,
     102,   102,   102,    29,    29,   102,    29,   102,    29,    29,
      29,   102,    29,    29,    29,    91,    92,   102,    93,    94,
     102,    95,    96,   102,    97,    98,   102,    48,    29,   100,
      29,   101,    22,    68,   101,    31,    82,   102,   102,   102,
      29,   102,   102,   102,   102,    81,    92,   102,    94,   102,
      96,   102,    98,   102,    49,    50,   102,    52,    29,    60,
      29,   101,    29,    29,    31,    29,    29,    29,   102,   102,
     102,   102,    31,   102,   102,   102,    29,    50,   102,    53,
      54,   102,    56,    61,    71,    75,   101,    63,    29,    66,
      29,   102,   102,    29,   102,    29,   102,   102,   102,   102,
      54,   102,    57,    58,   102,    71,    29,   101,    64,    72,
      75,    69,    67,    73,    75,    29,   102,   102,   102,   102,
     102,    29,   102,    58,   102,   101,    72,    76,   101,    70,
      74,    75,    73,    77,   101,   102,   102,    29,   102,   102,
     102,    29,    29,    29,   101,    74,    76,    29,   101,   102,
     102,   102,   102,   102,   102,   101,    77,   101,   102,   102,
     102,    29,   102,    29,    29,    29,   102,   102,    29,   102,
      29,    29,   102,   102,    29
};

#define yyerrok		(yyerrstatus = 0)
#define yyclearin	(yychar = YYEMPTY)
#define YYEMPTY		(-2)
#define YYEOF		0

#define YYACCEPT	goto yyacceptlab
#define YYABORT		goto yyabortlab
#define YYERROR		goto yyerrorlab


/* Like YYERROR except do call yyerror.  This remains here temporarily
   to ease the transition to the new meaning of YYERROR, for GCC.
   Once GCC version 2 has supplanted version 1, this can go.  */

#define YYFAIL		goto yyerrlab

#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)					\
do								\
  if (yychar == YYEMPTY && yylen == 1)				\
    {								\
      yychar = (Token);						\
      yylval = (Value);						\
      yytoken = YYTRANSLATE (yychar);				\
      YYPOPSTACK (1);						\
      goto yybackup;						\
    }								\
  else								\
    {								\
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;							\
    }								\
while (YYID (0))


#define YYTERROR	1
#define YYERRCODE	256


/* YYLLOC_DEFAULT -- Set CURRENT to span from RHS[1] to RHS[N].
   If N is 0, then set CURRENT to the empty location which ends
   the previous symbol: RHS[0] (always defined).  */

#define YYRHSLOC(Rhs, K) ((Rhs)[K])
#ifndef YYLLOC_DEFAULT
# define YYLLOC_DEFAULT(Current, Rhs, N)				\
    do									\
      if (YYID (N))                                                    \
	{								\
	  (Current).first_line   = YYRHSLOC (Rhs, 1).first_line;	\
	  (Current).first_column = YYRHSLOC (Rhs, 1).first_column;	\
	  (Current).last_line    = YYRHSLOC (Rhs, N).last_line;		\
	  (Current).last_column  = YYRHSLOC (Rhs, N).last_column;	\
	}								\
      else								\
	{								\
	  (Current).first_line   = (Current).last_line   =		\
	    YYRHSLOC (Rhs, 0).last_line;				\
	  (Current).first_column = (Current).last_column =		\
	    YYRHSLOC (Rhs, 0).last_column;				\
	}								\
    while (YYID (0))
#endif


/* YY_LOCATION_PRINT -- Print the location on the stream.
   This macro was not mandated originally: define only if we know
   we won't break user code: when these are the locations we know.  */

#ifndef YY_LOCATION_PRINT
# if YYLTYPE_IS_TRIVIAL
#  define YY_LOCATION_PRINT(File, Loc)			\
     fprintf (File, "%d.%d-%d.%d",			\
	      (Loc).first_line, (Loc).first_column,	\
	      (Loc).last_line,  (Loc).last_column)
# else
#  define YY_LOCATION_PRINT(File, Loc) ((void) 0)
# endif
#endif


/* YYLEX -- calling `yylex' with the right arguments.  */

#ifdef YYLEX_PARAM
# define YYLEX yylex (YYLEX_PARAM)
#else
# define YYLEX yylex ()
#endif

/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)			\
do {						\
  if (yydebug)					\
    YYFPRINTF Args;				\
} while (YYID (0))

# define YY_SYMBOL_PRINT(Title, Type, Value, Location)			  \
do {									  \
  if (yydebug)								  \
    {									  \
      YYFPRINTF (stderr, "%s ", Title);					  \
      yy_symbol_print (stderr,						  \
		  Type, Value); \
      YYFPRINTF (stderr, "\n");						  \
    }									  \
} while (YYID (0))


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_value_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# else
  YYUSE (yyoutput);
# endif
  switch (yytype)
    {
      default:
	break;
    }
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (yytype < YYNTOKENS)
    YYFPRINTF (yyoutput, "token %s (", yytname[yytype]);
  else
    YYFPRINTF (yyoutput, "nterm %s (", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_stack_print (yytype_int16 *bottom, yytype_int16 *top)
#else
static void
yy_stack_print (bottom, top)
    yytype_int16 *bottom;
    yytype_int16 *top;
#endif
{
  YYFPRINTF (stderr, "Stack now");
  for (; bottom <= top; ++bottom)
    YYFPRINTF (stderr, " %d", *bottom);
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)				\
do {								\
  if (yydebug)							\
    yy_stack_print ((Bottom), (Top));				\
} while (YYID (0))


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_reduce_print (YYSTYPE *yyvsp, int yyrule)
#else
static void
yy_reduce_print (yyvsp, yyrule)
    YYSTYPE *yyvsp;
    int yyrule;
#endif
{
  int yynrhs = yyr2[yyrule];
  int yyi;
  unsigned long int yylno = yyrline[yyrule];
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
	     yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      fprintf (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr, yyrhs[yyprhs[yyrule] + yyi],
		       &(yyvsp[(yyi + 1) - (yynrhs)])
		       		       );
      fprintf (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)		\
do {					\
  if (yydebug)				\
    yy_reduce_print (yyvsp, Rule); \
} while (YYID (0))

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef	YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif



#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static YYSIZE_T
yystrlen (const char *yystr)
#else
static YYSIZE_T
yystrlen (yystr)
    const char *yystr;
#endif
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static char *
yystpcpy (char *yydest, const char *yysrc)
#else
static char *
yystpcpy (yydest, yysrc)
    char *yydest;
    const char *yysrc;
#endif
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
	switch (*++yyp)
	  {
	  case '\'':
	  case ',':
	    goto do_not_strip_quotes;

	  case '\\':
	    if (*++yyp != '\\')
	      goto do_not_strip_quotes;
	    /* Fall through.  */
	  default:
	    if (yyres)
	      yyres[yyn] = *yyp;
	    yyn++;
	    break;

	  case '"':
	    if (yyres)
	      yyres[yyn] = '\0';
	    return yyn;
	  }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into YYRESULT an error message about the unexpected token
   YYCHAR while in state YYSTATE.  Return the number of bytes copied,
   including the terminating null byte.  If YYRESULT is null, do not
   copy anything; just return the number of bytes that would be
   copied.  As a special case, return 0 if an ordinary "syntax error"
   message will do.  Return YYSIZE_MAXIMUM if overflow occurs during
   size calculation.  */
static YYSIZE_T
yysyntax_error (char *yyresult, int yystate, int yychar)
{
  int yyn = yypact[yystate];

  if (! (YYPACT_NINF < yyn && yyn <= YYLAST))
    return 0;
  else
    {
      int yytype = YYTRANSLATE (yychar);
      YYSIZE_T yysize0 = yytnamerr (0, yytname[yytype]);
      YYSIZE_T yysize = yysize0;
      YYSIZE_T yysize1;
      int yysize_overflow = 0;
      enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
      char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
      int yyx;

# if 0
      /* This is so xgettext sees the translatable formats that are
	 constructed on the fly.  */
      YY_("syntax error, unexpected %s");
      YY_("syntax error, unexpected %s, expecting %s");
      YY_("syntax error, unexpected %s, expecting %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s");
# endif
      char *yyfmt;
      char const *yyf;
      static char const yyunexpected[] = "syntax error, unexpected %s";
      static char const yyexpecting[] = ", expecting %s";
      static char const yyor[] = " or %s";
      char yyformat[sizeof yyunexpected
		    + sizeof yyexpecting - 1
		    + ((YYERROR_VERBOSE_ARGS_MAXIMUM - 2)
		       * (sizeof yyor - 1))];
      char const *yyprefix = yyexpecting;

      /* Start YYX at -YYN if negative to avoid negative indexes in
	 YYCHECK.  */
      int yyxbegin = yyn < 0 ? -yyn : 0;

      /* Stay within bounds of both yycheck and yytname.  */
      int yychecklim = YYLAST - yyn + 1;
      int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
      int yycount = 1;

      yyarg[0] = yytname[yytype];
      yyfmt = yystpcpy (yyformat, yyunexpected);

      for (yyx = yyxbegin; yyx < yyxend; ++yyx)
	if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR)
	  {
	    if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
	      {
		yycount = 1;
		yysize = yysize0;
		yyformat[sizeof yyunexpected - 1] = '\0';
		break;
	      }
	    yyarg[yycount++] = yytname[yyx];
	    yysize1 = yysize + yytnamerr (0, yytname[yyx]);
	    yysize_overflow |= (yysize1 < yysize);
	    yysize = yysize1;
	    yyfmt = yystpcpy (yyfmt, yyprefix);
	    yyprefix = yyor;
	  }

      yyf = YY_(yyformat);
      yysize1 = yysize + yystrlen (yyf);
      yysize_overflow |= (yysize1 < yysize);
      yysize = yysize1;

      if (yysize_overflow)
	return YYSIZE_MAXIMUM;

      if (yyresult)
	{
	  /* Avoid sprintf, as that infringes on the user's name space.
	     Don't have undefined behavior even if the translation
	     produced a string with the wrong number of "%s"s.  */
	  char *yyp = yyresult;
	  int yyi = 0;
	  while ((*yyp = *yyf) != '\0')
	    {
	      if (*yyp == '%' && yyf[1] == 's' && yyi < yycount)
		{
		  yyp += yytnamerr (yyp, yyarg[yyi++]);
		  yyf += 2;
		}
	      else
		{
		  yyp++;
		  yyf++;
		}
	    }
	}
      return yysize;
    }
}
#endif /* YYERROR_VERBOSE */


/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
#else
static void
yydestruct (yymsg, yytype, yyvaluep)
    const char *yymsg;
    int yytype;
    YYSTYPE *yyvaluep;
#endif
{
  YYUSE (yyvaluep);

  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  switch (yytype)
    {

      default:
	break;
    }
}


/* Prevent warnings from -Wmissing-prototypes.  */

#ifdef YYPARSE_PARAM
#if defined __STDC__ || defined __cplusplus
int yyparse (void *YYPARSE_PARAM);
#else
int yyparse ();
#endif
#else /* ! YYPARSE_PARAM */
#if defined __STDC__ || defined __cplusplus
int yyparse (void);
#else
int yyparse ();
#endif
#endif /* ! YYPARSE_PARAM */



/* The look-ahead symbol.  */
int yychar;

/* The semantic value of the look-ahead symbol.  */
YYSTYPE yylval;

/* Number of syntax errors so far.  */
int yynerrs;



/*----------.
| yyparse.  |
`----------*/

#ifdef YYPARSE_PARAM
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void *YYPARSE_PARAM)
#else
int
yyparse (YYPARSE_PARAM)
    void *YYPARSE_PARAM;
#endif
#else /* ! YYPARSE_PARAM */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void)
#else
int
yyparse ()

#endif
#endif
{
  
  int yystate;
  int yyn;
  int yyresult;
  /* Number of tokens to shift before error messages enabled.  */
  int yyerrstatus;
  /* Look-ahead token as an internal (translated) token number.  */
  int yytoken = 0;
#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

  /* Three stacks and their tools:
     `yyss': related to states,
     `yyvs': related to semantic values,
     `yyls': related to locations.

     Refer to the stacks thru separate pointers, to allow yyoverflow
     to reallocate them elsewhere.  */

  /* The state stack.  */
  yytype_int16 yyssa[YYINITDEPTH];
  yytype_int16 *yyss = yyssa;
  yytype_int16 *yyssp;

  /* The semantic value stack.  */
  YYSTYPE yyvsa[YYINITDEPTH];
  YYSTYPE *yyvs = yyvsa;
  YYSTYPE *yyvsp;



#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  YYSIZE_T yystacksize = YYINITDEPTH;

  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;


  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY;		/* Cause a token to be read.  */

  /* Initialize stack pointers.
     Waste one element of value and location stack
     so that they stay on the same level as the state stack.
     The wasted elements are never initialized.  */

  yyssp = yyss;
  yyvsp = yyvs;

  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
	/* Give user a chance to reallocate the stack.  Use copies of
	   these so that the &'s don't force the real ones into
	   memory.  */
	YYSTYPE *yyvs1 = yyvs;
	yytype_int16 *yyss1 = yyss;


	/* Each stack pointer address is followed by the size of the
	   data in use in that stack, in bytes.  This used to be a
	   conditional around just the two extra args, but that might
	   be undefined if yyoverflow is a macro.  */
	yyoverflow (YY_("memory exhausted"),
		    &yyss1, yysize * sizeof (*yyssp),
		    &yyvs1, yysize * sizeof (*yyvsp),

		    &yystacksize);

	yyss = yyss1;
	yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
	goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
	yystacksize = YYMAXDEPTH;

      {
	yytype_int16 *yyss1 = yyss;
	union yyalloc *yyptr =
	  (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
	if (! yyptr)
	  goto yyexhaustedlab;
	YYSTACK_RELOCATE (yyss);
	YYSTACK_RELOCATE (yyvs);

#  undef YYSTACK_RELOCATE
	if (yyss1 != yyssa)
	  YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;


      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
		  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
	YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     look-ahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to look-ahead token.  */
  yyn = yypact[yystate];
  if (yyn == YYPACT_NINF)
    goto yydefault;

  /* Not known => get a look-ahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid look-ahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = YYLEX;
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yyn == 0 || yyn == YYTABLE_NINF)
	goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  if (yyn == YYFINAL)
    YYACCEPT;

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the look-ahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token unless it is eof.  */
  if (yychar != YYEOF)
    yychar = YYEMPTY;

  yystate = yyn;
  *++yyvsp = yylval;

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     `$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 7:
#line 56 "parser.y"
    { mesh(); }
    break;

  case 21:
#line 77 "parser.y"
    { lamp((yyvsp[(2) - (5)].dbval), (yyvsp[(3) - (5)].dbval), (yyvsp[(4) - (5)].dbval)); }
    break;

  case 22:
#line 78 "parser.y"
    { lamp_rgb((yyvsp[(2) - (8)].dbval), (yyvsp[(3) - (8)].dbval), (yyvsp[(4) - (8)].dbval), (yyvsp[(5) - (8)].dbval), (yyvsp[(6) - (8)].dbval), (yyvsp[(7) - (8)].dbval)); }
    break;

  case 23:
#line 80 "parser.y"
    { triangle_mesh_end(); }
    break;

  case 24:
#line 81 "parser.y"
    { mesh_and_mesh(); }
    break;

  case 25:
#line 82 "parser.y"
    { set_material(); }
    break;

  case 26:
#line 83 "parser.y"
    { set_transform(); }
    break;

  case 27:
#line 84 "parser.y"
    { set_transform(); set_keyframe(); }
    break;

  case 36:
#line 111 "parser.y"
    { triangle_mesh(bname); }
    break;

  case 37:
#line 113 "parser.y"
    { vertices_sec_head((yyvsp[(2) - (3)].intval)); }
    break;

  case 41:
#line 119 "parser.y"
    { vertex((yyvsp[(1) - (4)].dbval), (yyvsp[(2) - (4)].dbval), (yyvsp[(3) - (4)].dbval)); }
    break;

  case 42:
#line 121 "parser.y"
    { normals_sec_head((yyvsp[(2) - (3)].intval)); }
    break;

  case 46:
#line 127 "parser.y"
    { normal((yyvsp[(1) - (4)].dbval), (yyvsp[(2) - (4)].dbval), (yyvsp[(3) - (4)].dbval)); }
    break;

  case 47:
#line 129 "parser.y"
    { texturecoords_sec_head((yyvsp[(2) - (4)].intval)); }
    break;

  case 51:
#line 135 "parser.y"
    { texturecoord((yyvsp[(1) - (3)].dbval), (yyvsp[(2) - (3)].dbval)); }
    break;

  case 52:
#line 137 "parser.y"
    { triangles_sec_head((yyvsp[(2) - (3)].intval)); }
    break;

  case 56:
#line 143 "parser.y"
    { triangles_sec_head((yyvsp[(2) - (3)].intval)); }
    break;

  case 60:
#line 149 "parser.y"
    { triangles_sec_head((yyvsp[(2) - (3)].intval)); }
    break;

  case 64:
#line 155 "parser.y"
    { triangles_sec_head((yyvsp[(2) - (3)].intval)); }
    break;

  case 72:
#line 173 "parser.y"
    { vertex_index((yyvsp[(1) - (3)].intval), (yyvsp[(2) - (3)].intval), (yyvsp[(3) - (3)].intval)); }
    break;

  case 73:
#line 175 "parser.y"
    { normal_index((yyvsp[(1) - (3)].intval), (yyvsp[(2) - (3)].intval), (yyvsp[(3) - (3)].intval)); }
    break;

  case 74:
#line 177 "parser.y"
    { texture_coord_index((yyvsp[(1) - (3)].intval), (yyvsp[(2) - (3)].intval), (yyvsp[(3) - (3)].intval)); }
    break;

  case 75:
#line 184 "parser.y"
    { material((yyvsp[(2) - (14)].dbval), (yyvsp[(3) - (14)].dbval), (yyvsp[(4) - (14)].dbval), (yyvsp[(5) - (14)].dbval), (yyvsp[(6) - (14)].dbval), (yyvsp[(7) - (14)].dbval), (yyvsp[(8) - (14)].dbval), (yyvsp[(9) - (14)].dbval), (yyvsp[(10) - (14)].dbval), (yyvsp[(11) - (14)].dbval), (yyvsp[(12) - (14)].dbval), (yyvsp[(13) - (14)].dbval)); }
    break;

  case 76:
#line 190 "parser.y"
    { material((yyvsp[(2) - (17)].dbval), (yyvsp[(3) - (17)].dbval), (yyvsp[(4) - (17)].dbval), (yyvsp[(6) - (17)].dbval), (yyvsp[(7) - (17)].dbval), (yyvsp[(8) - (17)].dbval), (yyvsp[(10) - (17)].dbval), (yyvsp[(11) - (17)].dbval), (yyvsp[(12) - (17)].dbval), (yyvsp[(14) - (17)].dbval), (yyvsp[(15) - (17)].dbval), (yyvsp[(16) - (17)].dbval)); }
    break;

  case 79:
#line 198 "parser.y"
    { static_tran((yyvsp[(2) - (14)].dbval), (yyvsp[(3) - (14)].dbval), (yyvsp[(4) - (14)].dbval),
                                (yyvsp[(6) - (14)].dbval), (yyvsp[(7) - (14)].dbval), (yyvsp[(8) - (14)].dbval), (yyvsp[(9) - (14)].dbval),
                                (yyvsp[(11) - (14)].dbval), (yyvsp[(12) - (14)].dbval), (yyvsp[(13) - (14)].dbval)); }
    break;

  case 90:
#line 219 "parser.y"
    { transls_sec_head((yyvsp[(2) - (3)].intval)); }
    break;

  case 92:
#line 222 "parser.y"
    { rots_sec_head((yyvsp[(2) - (3)].intval)); }
    break;

  case 94:
#line 225 "parser.y"
    { scales_sec_head((yyvsp[(2) - (3)].intval)); }
    break;

  case 96:
#line 228 "parser.y"
    { visibilities_sec_head((yyvsp[(2) - (3)].intval)); }
    break;

  case 111:
#line 253 "parser.y"
    { (yyval.string) = (yyvsp[(1) - (1)].string); }
    break;

  case 112:
#line 255 "parser.y"
    { (yyval.intval) = atol((yyvsp[(1) - (1)].string)); }
    break;

  case 113:
#line 257 "parser.y"
    { (yyval.dbval) = atof((yyvsp[(1) - (1)].string)); }
    break;


/* Line 1267 of yacc.c.  */
#line 1799 "y.tab.c"
      default: break;
    }
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;


  /* Now `shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*------------------------------------.
| yyerrlab -- here on detecting error |
`------------------------------------*/
yyerrlab:
  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
      {
	YYSIZE_T yysize = yysyntax_error (0, yystate, yychar);
	if (yymsg_alloc < yysize && yymsg_alloc < YYSTACK_ALLOC_MAXIMUM)
	  {
	    YYSIZE_T yyalloc = 2 * yysize;
	    if (! (yysize <= yyalloc && yyalloc <= YYSTACK_ALLOC_MAXIMUM))
	      yyalloc = YYSTACK_ALLOC_MAXIMUM;
	    if (yymsg != yymsgbuf)
	      YYSTACK_FREE (yymsg);
	    yymsg = (char *) YYSTACK_ALLOC (yyalloc);
	    if (yymsg)
	      yymsg_alloc = yyalloc;
	    else
	      {
		yymsg = yymsgbuf;
		yymsg_alloc = sizeof yymsgbuf;
	      }
	  }

	if (0 < yysize && yysize <= yymsg_alloc)
	  {
	    (void) yysyntax_error (yymsg, yystate, yychar);
	    yyerror (yymsg);
	  }
	else
	  {
	    yyerror (YY_("syntax error"));
	    if (yysize != 0)
	      goto yyexhaustedlab;
	  }
      }
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse look-ahead token after an
	 error, discard it.  */

      if (yychar <= YYEOF)
	{
	  /* Return failure if at end of input.  */
	  if (yychar == YYEOF)
	    YYABORT;
	}
      else
	{
	  yydestruct ("Error: discarding",
		      yytoken, &yylval);
	  yychar = YYEMPTY;
	}
    }

  /* Else will try to reuse look-ahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule which action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;	/* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (yyn != YYPACT_NINF)
	{
	  yyn += YYTERROR;
	  if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
	    {
	      yyn = yytable[yyn];
	      if (0 < yyn)
		break;
	    }
	}

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
	YYABORT;


      yydestruct ("Error: popping",
		  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  if (yyn == YYFINAL)
    YYACCEPT;

  *++yyvsp = yylval;


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#ifndef yyoverflow
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEOF && yychar != YYEMPTY)
     yydestruct ("Cleanup: discarding lookahead",
		 yytoken, &yylval);
  /* Do not reclaim the symbols of the rule which action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
		  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  /* Make sure YYID is used.  */
  return YYID (yyresult);
}


#line 259 "parser.y"



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

