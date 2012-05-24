main:main.o mytool1.o mytool2.o
	cc -o main main.o mytool1.o mytool2.o
main.o:main.c mytool1.h mytool2.h
	cc -c main.c
mytool1.o:mytool1.c mytool1.h
	cc -c mytool1.c
mytool2.o:mytool2.c mytool2.h
	cc -c mytool2.c //命令行需+tab空符

