
#include<stdio.h>
#include<ctype.h>
int c=1;

op "++"|"--";
rop "<"|">"|"<="|">="|"=="|"!="
id [a-zA-Z][a-zA-Z0-9]*
no [0-9]*
pp [\n]

for\(({id}=({no}|{id}))?\;{id}{rop}({id}|{no})\;{id}{op}\){pp}+\{(.*\n)*.*\} {printf("correct");c=0;}

main()
{ yyin=fopen("file11.c","r");
yylex();
if(c==1)
printf("incorrect");
}
int yywrap()
{
return 1;
}
