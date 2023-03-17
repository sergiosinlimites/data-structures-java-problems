package src.nodes.NodeList;

import src.nodes.Node.Node;

public class NodeList {

  public Node head;

  public NodeList(Node node){
    this.head = node;
  }

  public Node getHead(){
    return this.head;
  }

  public void pushFront(Node node){
    node.setNext(this.head);
    System.out.println("El nodo"+node.getData());
    System.out.println("La data"+ head.getData());
    head = node;
  }

  public Boolean insert(Node node){
    // no se aceptan duplicados
    Node nextNode = this.head;
    Node prevNode = null;
    Boolean inserted = false;
    // System.out.println("El siguiente nodo es: " + nextNode.getData());
    // System.out.println("El anterior nodo es: " + prevNode);
    while(nextNode != null && nextNode.getData() < node.getData()){
      prevNode = nextNode;
      nextNode = nextNode.getNext();
    }
    // System.out.println("El siguiente nodo 2 es: " + nextNode);
    // System.out.println("El anterior nodo 2 es: " + prevNode.getData());
    if(prevNode != null && prevNode.getData() == node.getData()){
      inserted = true;
      node.setNext(nextNode);
      prevNode.setNext(node);
    }
    if(prevNode == null){
      inserted = true;
      node.setNext(nextNode);
      this.head = node;
    }
    return inserted;
  }

  public void printAll(){
    print(this.head);
  }

  public void print(Node node){
    Node currentNode = node;
    if(currentNode != null){
      System.out.println("La data del nodo es: "+currentNode.getData());
      this.print(currentNode.getNext());
    }
  }
}
