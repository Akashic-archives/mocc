package javaCompiler;

public class Token{
  private TokenType tokenType;
  private String value;

  public enum TokenType {
    INT,
    MAIN,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS,
    LEFT_BRACKET,
    RIGHT_BRACKET,
    RETURN,
    NUMBER, // both needs to have a value
    SEMICOLON,
    NAME // variable name or other non basic tokenCounter
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
}

