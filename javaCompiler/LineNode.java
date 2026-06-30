package javaCompiler;

public class LineNode extends Node {
  private Token token;
  private Node nextLine;
  private Node nextNode;
  
  public LineNode(Token token) {
    this.token = token;
  }

  public Token getToken() {
    return token;
  }
  public Node getNextLine() {
    return nextLine;
  }
  public Node getNextNode() {
    return nextNode;
  }

  public void setNextLine(Node nextLine) {
    this.nextLine = nextLine;
  }
  public void setNextNode(Node nextNode) {
    this.nextNode = nextNode;
  }
}

