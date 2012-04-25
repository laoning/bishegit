aaa: lex.yy.c y.tab.h 
	gcc -g -o aaa y.tab.c lex.yy.c  -ll 
lex.yy.c: aaa.l y.tab.h
	lex --nounistd aaa.l
y.tab.h: aaa.y
	yacc -d aaa.y
clean:
	- rm  lex.yy.c > /dev/null 2>&1
	- rm  aaa > /dev/null 2>&1
	- rm  y.tab.c > /dev/null 2>&1
	- rm  y.tab.h > /dev/null 2>&1
