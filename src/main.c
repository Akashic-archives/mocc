#include <stdio.h>
#include <string.h>

#include "lexer.c"

int main(){
	
	char code[100];
	int code_size = 0;

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

	/*enum TOKEN tokens[128] = lexerer(code);

	for (int i = 0; i < 128; i++) {
		printf("%i", tokens[i]);
	}*/

	for (int i = 0; i < strlen(code) - 1; i++) {
		switch(code[i]) {
			case '1':
				printf("one ");
				break;
			case '+':
				printf("plus ");
				break;
			case '=':
				printf("equals ");
				break;
			default:
				printf("error ");
		}
	}

	printf("\n");

	return 0;
}


// read input file
// call lexer to tokenize
// call parser to build ast
// call codegen

