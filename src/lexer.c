#include "lexer.h"
#include <stdio.h>
#include <string.h>

enum TOKEN lexerer(char code[]) {
	enum TOKEN tokens[128];
	int tokens_size = 0;
	for (int i = 0; i < strlen(code) - 1; i++) {
		switch (code[i]) {
			case '(':
				tokens[tokens_size] = OPEN_PARENTHESES;
				break;
			case ')':
				tokens[tokens_size] = CLOSE_PARENTHESES;
				break;
			case '{':
				tokens[tokens_size] = OPEN_BRACE;
				break;
			case '}':
				tokens[tokens_size] = CLOSE_BRACE;
				break;
			case '2':
				tokens[tokens_size] = NUMBER;
				break;
			case ';':
				tokens[tokens_size] = SEMICOLON;
				break;
			default:
				char returnStr[6];
				char intStr[3];
				char mainStr[4];
				strncpy(returnStr, code + i, 6);
				strncpy(intStr, code + i, 3);
				strncpy(mainStr, code + i, 4);

				if (strcmp(intStr, "int") == 0) {
					tokens[tokens_size] = INT_KEYWORD;
					i += 2;
				}
				else if (strcmp(mainStr, "main") == 0) {
					tokens[tokens_size] = MAIN_KEYWORD;
					i += 4;
				}
				else if (strcmp(returnStr, "return") == 0) {
					tokens[tokens_size] = RETURN_KEYWORD;
					i += 6;
				}
				else
					printf("not recognised lex");
		}
	}
}

