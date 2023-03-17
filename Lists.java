import src.lists.DynamicCircularArrayList.DynamicCircularArrayList;
import src.lists.StaticNormalNotLinkedList.StaticNormalNotLinkedList;;

public class Lists {
  public static void main(String[] args){
    DynamicCircularArrayList myList = new DynamicCircularArrayList(5);
    myList.add(12);
    myList.add(14);
    myList.add(20);
    myList.add(6);
    myList.add(10);
    myList.add(60);
    myList.remove((14));
    myList.printList();

    StaticNormalNotLinkedList myStaticList = new StaticNormalNotLinkedList();
    myStaticList.pushFront(2);
    myStaticList.pushFront(3);
    myStaticList.pushBack(4);
    myStaticList.pushFront(4);
    myStaticList.pushFront(5);
    myStaticList.pushFront(6);
    myStaticList.pushFront(7);
    myStaticList.print();
    
  }
}
