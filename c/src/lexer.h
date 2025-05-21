#ifndef LEXER_H
#define LEXER_H

enum TokenType {
	END_OF_FILE = 0,
	INT_KEYWORD,
	MAIN_KEYWORD,
	OPEN_PARENTHESES,
	CLOSE_PARENTHESES,
	OPEN_BRACE,
	CLOSE_BRACE,
	RETURN_KEYWORD,
	NUMBER,
	SEMICOLON
};

struct Token {
	enum TokenType token;
	char value[128];
};

#endif
