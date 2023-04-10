package src.lists.Stack;

import java.util.EmptyStackException;

public class Stack<T> {
  private Node<T> head;
  private int size;

  private static class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
      this.data = data;
    }
  }

  public Stack() {
    head = null;
    size = 0;
  }

  // Return the number of elements in the stack
  public int size() {
    return size;
  }

  // Check if the stack is empty
  public boolean isEmpty() {
    return size == 0;
  }

  // Add an element to the top of the stack
  public void push(T element) {
    Node<T> newNode = new Node<>(element);
    newNode.next = head;
    head = newNode;
    size++;
  }

  // Remove the element at the top of the stack
  public T pop() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    T element = head.data;
    head = head.next;
    size--;
    return element;
  }

  // Return the element at the top of the stack without removing it
  public T peek() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    return head.data;
  }
}
