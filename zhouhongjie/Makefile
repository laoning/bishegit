parser: y.tab.c lex.yy.c y.tab.h parser.h
	gcc -g -o parser y.tab.c lex.yy.c -ll -ly
lex.yy.c: parser.l y.tab.h
	lex parser.l
y.tab.c y.tab.h: parser.y
	yacc -d parser.y
clean:
	- rm  lex.yy.c > /dev/null 2>&1
	- rm  parser > /dev/null 2>&1
	- rm  y.tab.c > /dev/null 2>&1
	- rm  y.tab.h > /dev/null 2>&1
