package src.lists.DoubleLinkedList;

public class DoubleLinkedList<T> {
  private Node<T> head;
  private Node<T> tail;
  private int size;

  private static class Node<T> {
    private T data;
    private Node<T> prev;
    private Node<T> next;

    public Node(T data) {
      this.data = data;
    }
  }

  public void pushFront(T data) {
    Node<T> newNode = new Node<>(data);
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    }
    size++;
  }

  public void pushBack(T data) {
    Node<T> newNode = new Node<>(data);
    if (tail == null) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.prev = tail;
      tail.next = newNode;
      tail = newNode;
    }
    size++;
  }

  public T popFront() {
    if (head == null) {
      return null;
    }
    T removed = head.data;
    if (head == tail) {
      head = null;
      tail = null;
    } else {
      head = head.next;
      head.prev = null;
    }
    size--;
    return removed;
  }

  public T popBack() {
    if (tail == null) {
      return null;
    }
    T removed = tail.data;
    if (head == tail) {
      head = null;
      tail = null;
    } else {
      tail = tail.prev;
      tail.next = null;
    }
    size--;
    return removed;
  }
}
