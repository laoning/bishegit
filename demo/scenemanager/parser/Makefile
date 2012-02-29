parser: y.tab.c lex.yy.c y.tab.h parser.h common.c inter.h inter.cpp
	gcc -g -o parser y.tab.c lex.yy.c common.c inter.cpp -ll -ly -lstdc++
lex.yy.c: parser.l y.tab.h
	lex --nounistd parser.l
y.tab.c y.tab.h: parser.y
	yacc -d parser.y
clean:
	- rm  lex.yy.c > /dev/null 2>&1
	- rm  parser > /dev/null 2>&1
	- rm  y.tab.c > /dev/null 2>&1
	- rm  y.tab.h > /dev/null 2>&1
