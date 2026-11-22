package javaCompiler;

import java.util.ArrayList;

public class LineNode extends Node {
  private Token token;
  private Node nextLine;
  private Node nextNode;
  private ArrayList<Node> nodeArray;
 
  public LineNode() {
    nodeArray = new ArrayList<Node>();
  }

  public LineNode(Token token) {
    this.token = token;
    nodeArray = new ArrayList<Node>();
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
  public ArrayList<Node> getNodes() {
    return nodeArray;
  }

  public void setNextNode(Node node) {
    nodeArray.add(node);
  }
}

