package javaCompiler;

public class Token{
  private TokenType tokenType;
  private String value;

  public enum TokenType {
    MAIN,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS,
    LEFT_BRACKET,
    RIGHT_BRACKET,
    NUMBER, // both needs to have a value
    SEMICOLON,
    NAME, // variable name or other non basic tokenCounter
    FUNCTION,
    AUTO, // KeyWord
    BREAK,
    CASE,
    CHAR,
    CONST,
    CONTINUE,
    DEFAULT,
    DO,
    DOUBLE,
    ELSE,
    ENUM,
    EXTERN,
    FLOAT,
    FOR,
    GOTO,
    IF,
    INT,
    LONG,
    REGISTER,
    RETURN,
    SHORT,
    SIGNED,
    SIZEOF,
    STATIC,
    STRUCT,
    SWITCH,
    TYPEDEF,
    UNION,
    UNSIGNED,
    VOID,
    VOLATILE,
    WHILE
  }

  public TokenType getTokenType() {
    return tokenType;
  }
  public String getValue() {
    return value;
  }

  public Token(TokenType tokenType, String value) {
    this.tokenType = tokenType;
    this.value = value;
  }
  
  public Token(TokenType tokenType) {
    this.tokenType = tokenType;
    this.value = "";
  }

  public static boolean isKeyword(Token token) {
    return token.getTokenType() == Token.TokenType.AUTO || 
      token.getTokenType() == Token.TokenType.BREAK ||
      token.getTokenType() == Token.TokenType.CASE ||
      token.getTokenType() == Token.TokenType.CHAR ||
      token.getTokenType() == Token.TokenType.CONST ||
      token.getTokenType() == Token.TokenType.CONTINUE ||
      token.getTokenType() == Token.TokenType.DEFAULT ||
      token.getTokenType() == Token.TokenType.DO ||
      token.getTokenType() == Token.TokenType.DOUBLE ||
      token.getTokenType() == Token.TokenType.ELSE ||
      token.getTokenType() == Token.TokenType.ENUM ||
      token.getTokenType() == Token.TokenType.EXTERN ||
      token.getTokenType() == Token.TokenType.FLOAT ||
      token.getTokenType() == Token.TokenType.FOR ||
      token.getTokenType() == Token.TokenType.GOTO ||
      token.getTokenType() == Token.TokenType.IF ||
      token.getTokenType() == Token.TokenType.INT ||
      token.getTokenType() == Token.TokenType.LONG ||
      token.getTokenType() == Token.TokenType.REGISTER ||
      token.getTokenType() == Token.TokenType.RETURN ||
      token.getTokenType() == Token.TokenType.SHORT ||
      token.getTokenType() == Token.TokenType.SIGNED ||
      token.getTokenType() == Token.TokenType.SIZEOF ||
      token.getTokenType() == Token.TokenType.STATIC ||
      token.getTokenType() == Token.TokenType.STRUCT ||
      token.getTokenType() == Token.TokenType.SWITCH ||
      token.getTokenType() == Token.TokenType.TYPEDEF ||
      token.getTokenType() == Token.TokenType.UNION ||
      token.getTokenType() == Token.TokenType.UNSIGNED ||
      token.getTokenType() == Token.TokenType.VOID ||
      token.getTokenType() == Token.TokenType.VOLATILE ||
      token.getTokenType() == Token.TokenType.WHILE;
  }
}

