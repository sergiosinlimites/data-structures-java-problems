import src.nodes.Node.Node;
import src.nodes.NodeList.NodeList;

public class Nodes {
  public static void main(String[] args){
    NodeList myList = new NodeList(new Node(10));
    myList.pushFront(new Node(12));
    myList.printAll();
  }
}