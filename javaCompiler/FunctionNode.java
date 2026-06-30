package javaCompiler;

public class FunctionNode extends Node {
  private Token token;
  private String name;
  private int leftBracket, rightBracket;

  public FunctionNode(Token token) {
    this.token = token;
  }
  public FunctionNode(Token token, String name, int leftBracket, int rightBracket) {
    this.token = token;
    this.name = name;
    this.leftBracket = leftBracket;
    this.rightBracket = rightBracket;
  }

  public Token getToken() {
    return token;
  }
  public String getName() {
    return name;
  }
  public int getLeftBracket() {
    return leftBracket;
  }
  public int getRightBracket() {
    return rightBracket;
  }

}

