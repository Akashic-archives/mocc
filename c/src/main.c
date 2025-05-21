#include <stdio.h>
#include <string.h>

#include "lexer.c"

int main(){
	
	char code[100];
	int code_size = 0;

	struct Token tokens[128];
	int tokens_size = 0;

	while(1) {
		char ch;
		ch = getchar();
		if (ch == EOF)
			break;
		code[code_size] = ch;
		code_size++;
	}

	printf(code);

	printf("\n");

	

	lexerer(code, tokens, &tokens_size);

	for (int i = 0; i < 128; i++) {
		printf("%d", tokens[i]);
		//printf("\n");
	}

	printf("\n");

	return 0;
}


// read input file
// call lexer to tokenize
// call parser to build ast
// call codegen

