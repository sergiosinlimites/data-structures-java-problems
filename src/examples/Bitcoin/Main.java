package src.examples.Bitcoin;
import java.util.Scanner;

/**
 * Investing in Bitcoin
  Problem statement
  Juan is thinking of investing all his savings in Bitcoin, but first he wants to do a small stock analysis with some historical data on the price of this cryptocurrency in dollars. To do this, he will take a list P of prices with each price p with 1 <= p <= 65000 of the last n days with 2 <= n <= 10000, and for each day P[n] it must be discovered how many days he has to wait to be able to buy at a price and then be able to sell at a higher price.

  Input
  List of prices of the last n days separating each price with a space.

  Output
  Days to wait to buy at a price and then be able to sell at a higher price, separating each price with a space.

  Examples

  Example 1 Input

  2000 2500 2452 3000
  Example 1 Output

  1 2 1 0

  Example 2 Input

  60000 35000 23452 50000
  Example 2 Output

  0 2 1 0

  Explanation of the first example case
  If you buy on the first day at $2000, you only need to wait one day to sell at $2500. If you buy on the second day at $2500, you have to wait 2 days to sell at a higher price of $3000. If you buy on the third day at $2452, you only need to wait one day to sell at $3000. Finally, since there is no more data after day 4, you cannot sell at a higher price than $3000.

  Notes
  The output should not have a newline character at the end of the file, otherwise it may receive the incorrect response verdict.
 */

public class Main {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    String[] prices = scanner.nextLine().split(" ");
    StringBuilder stringBuilder = new StringBuilder();
    
    NodeList myList = new NodeList(null);
    for(int i = prices.length - 1; i >= 0; i--){
      myList.pushFront(new Node(Integer.valueOf(prices[i])));
    }
    while(myList.getHead().getNext() != null){
      int value = myList.findDaysUntil(myList.getHead(), myList.getHead().getData());
      myList.head = myList.getHead().getNext();
      stringBuilder.append(value+" ");
    }
    stringBuilder.append("0");

    System.out.print(stringBuilder.toString().trim());
  }

  static class Node {
    private Integer data = null;
    private Node next = null;
    public Node(int data){
      this.data = data;
      this.next = null;
    }
  
    public Node getNext(){
      return this.next;
    }
  
    public void setNext(Node node){
      this.next = node;
    }
  
    public Integer getData(){
      return this.data;
    }
  
    public void setData(int data){
      this.data = data;
    }
    
  }

  static class NodeList {

    public Node head;
    public Node nodeSelected;
    public Node nextNode;
  
    public NodeList(Node node){
      this.head = node;
    }
  
    public Node getHead(){
      return this.head;
    }
  
    public void pushFront(Node node){
      node.setNext(this.head);
      head = node;
    }

    public void pushBack(Node node){
      this.head.setNext(node);
      this.head = node;
    }

    public int findDaysUntil(Node startNode, int value){
      int days = 0;
      boolean found = false;
      this.nodeSelected = startNode;
      while(this.nodeSelected != null && !found){
        if(this.nodeSelected.getData() > value){
          found = true;
          break;
        } else {
          this.nodeSelected = this.nodeSelected.next;
          days++;
        }
      }
      return found ? days : 0;
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
  
}