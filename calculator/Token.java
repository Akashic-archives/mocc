package mocc.calculator;

public class Token {
  private TokenType type;
  private float value;
  
  public Token(TokenType type, float value) {
    this.type = type;
    this.value = value;
  }

  public TokenType getType() {
    return type;
  }

  public float getValue() {
    return value;
  }

  enum TokenType {
    NUMBER,
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    POWER,
    POINT,
    COMMA,
    RIGHT_PARENTHESIS,
    LEFT_PARENTHESIS,
    EQUALS
	}

}
