package src.lists.Queue;

import java.util.NoSuchElementException;

public class Queue<T> {
  private Node<T> head;
  private Node<T> tail;
  private int size;

  private static class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
      this.data = data;
    }
  }

  public Queue() {
    head = null;
    tail = null;
    size = 0;
  }

  // Return the number of elements in the queue
  public int size() {
    return size;
  }

  // Check if the queue is empty
  public boolean isEmpty() {
    return size == 0;
  }

  // Add an element to the back of the queue
  public void enqueue(T element) {
    Node<T> newNode = new Node<>(element);
    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    } else {
      tail.next = newNode;
      tail = newNode;
    }
    size++;
  }

  // Remove the element at the front of the queue
  public T dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    T element = head.data;
    head = head.next;
    size--;
    if (isEmpty()) {
      tail = null;
    }
    return element;
  }

  // Return the element at the front of the queue without removing it
  public T peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return head.data;
  }
}
