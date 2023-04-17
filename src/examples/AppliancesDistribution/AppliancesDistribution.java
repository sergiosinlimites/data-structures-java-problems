package examples.AppliancesDistribution;

/**
 * This is the problem:
 * Reparto de electrodomésticos

Esta prueba tiene como objetivo evaluar su conocimiento en el tema de pilas y colas, por eso se espera que para este problema lo aplique.


Enunciado del problema

Usted es el gerente de ventas de una empresa distribuidora de electrodomésticos. Los productos que son terminados se deslizan por una banda para ser enviados como se explica a continuación. A medida que van llegando, deben organizarse por grupos, un grupo para cada tienda. Los productos que van a una misma tienda deben estar apilados en el orden en el que llegan, para su posterior distribución.


Los electrodomésticos se deben distribuir en el siguiente orden: primero se apilan los productos para la tienda 1 hasta completar el número de pedido de esta tienda; luego, se deben apilar los electrodomésticos que van hacía la tienda 2, hasta completar el número de pedido de dicha tienda; de esta manera, se debe continuar el proceso de organización de productos, tienda por tienda, hasta completar los pedidos.


Se debe desarrollar un programa que lea por consola el orden en que llegan los diferentes electrodomésticos y el número de ellos que debe enviarse a cada una de las tiendas; luego organizar los grupos de electrodomésticos para cada tienda e imprimir como quedaron cada una de las pilas.


Entrada

La primera línea debe contener un número entero C, que representa la cantidad de "casos" que se procesan en una misma ejecución. Es decir, el número de veces que como gerente, debe distribuir su banda de producción entre un número de tiendas dado (Nota: Cada caso funciona por separado, si sobran electrodomésticos en una iteración, estos se desechan o donan a la caridad).


La siguiente línea debe contener un número entero N, que representa la cantidad de electrodomésticos que deben distribuirse entre las tiendas;


En la siguiente línea se deben leer N cadenas de caracteres (strings), que representan los nombres de todos los productos que deben ser procesados; la primera cadena en esta línea de entrada representa el producto ubicado en la primera parte de la línea de producción (y que por lo tanto deberá ser el primero en procesar) y la última cadena el producto que está en la parte de atrás de la banda (y que por lo tanto deberá ser el último en procesar). Para su implementación use una cola.


La siguiente línea debe leer un entero T, que indica el número de tiendas a las que se deben enviar los productos;


Luego, sigue una línea con T enteros (T_{1}, T_{2}, ... T_{x}...) qué representan el número de productos que deben enviarse a cada una de las T tiendas, separadas por un espacio como se muestra: T_{1} es el número de productos a enviar a la tienda 1, T_{2} el número de productos a enviar a la tienda 2, y así sucesivamente.


Muy importante: En el caso que una pila de productos en una determinada tienda esté vacía, se debe imprimir “[]” (sin las comillas).


1 <= C <= 10^{3}

1 <= N <= 10^{6}

1 <= T <= 10^{6}

1 <= T_{i} <= 10^{6}


Salida

La salida debe consistir de L líneas así:


la primera línea, L_{1}, contiene los elementos de la pila de productos a enviar a la tienda 1;

la segunda línea, L_{2}, contiene los elementos de la pila de productos a enviar a la tienda 2;

la i-ésima línea, L_{i-ésima}, contiene los elementos de la pila de productos a enviar a la tienda i-ésima.
 */

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AppliancesDistribution {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int casesNum = Integer.valueOf(scanner.nextLine());
    for (int i = 1; i <= casesNum; i++) {
      int __appliancesNum = Integer.valueOf(scanner.nextLine());
      String appliances = scanner.nextLine();
      String[] appliancesList = appliances.split(" ");
      Queue<String> appliancesQueue = new Queue<String>();
      for (int j = 0; j < appliancesList.length; j++) {
        appliancesQueue.enqueue(appliancesList[j]);
      }
      int __storesNum = Integer.valueOf(scanner.nextLine());
      String[] storesDemand = scanner.nextLine().split(" ");

      for (int j = 0; j < storesDemand.length; j++) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int n = 1; n <= Integer.valueOf(storesDemand[j]); n++) {
          if (!appliancesQueue.isEmpty()) {
            stringBuilder.append(
                appliancesQueue.dequeue().trim() + (n == Integer.valueOf(storesDemand[j]).intValue() ? "" : " "));
          }
        }
        if (j == storesDemand.length - 1 && i == casesNum) {
          System.out.print(stringBuilder.toString().trim() + "]");
        } else {
          System.out.println(stringBuilder.toString().trim() + "]");
        }
      }
    }
    scanner.close();
  }
}

class Queue<T> {
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
