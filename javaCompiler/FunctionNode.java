package javaCompiler;

import java.util.ArrayList;

public class FunctionNode extends Node {
  private Token token;
  private String name;
  private int leftBracket, rightBracket;
  private LineNode lineNode[];
  private ArrayList<LineNode> lineNodeArray;

  public FunctionNode(Token token) {
    this.token = token;
    lineNodeArray = new ArrayList<LineNode>();
  }
  public FunctionNode(Token token, String name, int leftBracket, int rightBracket) {
    this.token = token;
    this.name = name;
    this.leftBracket = leftBracket;
    this.rightBracket = rightBracket;
    lineNodeArray = new ArrayList<LineNode>();
  }

  public Token getToken() {
    return token;
  }
  public String getName() {
    return name;
  }
  public ArrayList<LineNode> getLineNodes() {
    return lineNodeArray;
  }
  public int getLeftBracket() {
    return leftBracket;
  }
  public int getRightBracket() {
    return rightBracket;
  }

  public void setNextLineNode(LineNode lineNode) {
    lineNodeArray.add(lineNode);
  }
}

