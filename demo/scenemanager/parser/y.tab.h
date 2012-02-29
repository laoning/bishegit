/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton interface for Bison's Yacc-like parsers in C

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




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
#line 6 "parser.y"
{
    double  dbval;
    long    intval;
    char    * string;
}
/* Line 1489 of yacc.c.  */
#line 113 "y.tab.h"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE yylval;

