#include "lexer.h"
#include <stdio.h>
#include <string.h>

enum Token lexerer(char code[], enum Token* tokens, int* tokens_size) {
	
	for (int i = 0; i < strlen(code) - 1; i++) {
		switch (code[i]) {
			case '(':
				tokens[*tokens_size] = OPEN_PARENTHESES;
				printf("(");
				break;
			case ')':
				tokens[*tokens_size] = CLOSE_PARENTHESES;
				printf(")");
				break;
			case '{':
				tokens[*tokens_size] = OPEN_BRACE;
				printf("{");
				break;
			case '}':
				tokens[*tokens_size] = CLOSE_BRACE;
				printf("}");
				break;
			case '2':
				tokens[*tokens_size] = NUMBER;
				printf("2");
				break;
			case ';':
				tokens[*tokens_size] = SEMICOLON;
				printf(";");
				break;
			default:
				char returnStr[6];
				char intStr[3];
				char mainStr[4];
				strncpy(returnStr, code + i, 6);
				strncpy(intStr, code + i, 3);
				strncpy(mainStr, code + i, 4);

				if (strcmp(intStr, "int") == 0) {
					tokens[*tokens_size] = INT_KEYWORD;
					printf("int");
					i += 2;
				}
				else if (strcmp(mainStr, "main") == 0) {
					tokens[*tokens_size] = MAIN_KEYWORD;
					printf("main");
					i += 4;
				}
				else if (strcmp(returnStr, "return") == 0) {
					tokens[*tokens_size] = RETURN_KEYWORD;
					printf("return");
					i += 6;
				}
				else
					printf("not recognised lex\n");
		}
	}
}

