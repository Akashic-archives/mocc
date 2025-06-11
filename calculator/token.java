package mocc.java;

public class Token {
  private TokenType type;
  private int value;
  
  public Token(TokenType type, int value) {
    this.type = type;
    this.value = value;
  }

  public TokenType getType() {
    return type;
  }

  public int getValue() {
    return value;
  }
}
