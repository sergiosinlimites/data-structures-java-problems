package examples.DevelopersSelection;

/**
 * Problem statement:
 * Selección de desarrolladores

Descripción del problema
Una prestigiosa empresa está buscando desarrolladores para un nuevo proyecto propuesto durante la última junta directiva. N personas se postularon para la vacante, de las cuales la empresa solo tendrá en cuenta a aquellas que cumplan con las competencias mínimas. Estas competencias están representadas por números enteros positivos distintos de cero. La empresa tiene un listado de M competencias que deben cumplir sus desarrolladores. También pueden considerar aquellos desarrolladores con aptitudes adicionales, siempre y cuando cumplan con las competencias mínimas exigidas. ¿Cuántos postulantes cumplen con las condiciones de la empresa?



Entrada
El caso de prueba inicia con una línea con un entero M ​que representa la cantidad de competencias exigidas por la empresa. La segunda línea contiene M números separados por espacios que enumeran las cualidades buscadas. Luego sigue una línea con un número M que representa la cantidad de personas que aplicaron al trabajo. Finalmente, N líneas siguen, cada una con números separados por espacios que representan las competencias de cada una de las personas.



Restricciones
1 <= N <= 10^3
1 <= M <= 10^3

Salida
Se debe imprimir según la descripción, es decir, ¿cuántas personas cumplen con las condiciones de la empresa?


Example

Input Example 1

5
1 2 3 4 5
4
1 2 3 4 5 6
5 1
1 2 3 4
5 4 3 2 1
 */

import java.util.Scanner;

public class DevelopersSelection {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    int m = scanner.nextInt();
    int[] competencias = new int[m];
    for (int i = 0; i < m; i++) {
      competencias[i] = scanner.nextInt();
    }
    int n = scanner.nextInt();
    int elegibles = 0;
    scanner.nextLine();
    for (int i = 0; i < n; i++) {
      String[] competenciasCandidato = scanner.nextLine().trim().split(" ");
      int count = 0;
      for (int j = 0; j < competenciasCandidato.length; j++) {
        for (int competencia : competencias) {
          if (competencia == Integer.valueOf(competenciasCandidato[j])) {
            count++;
          }
        }
      }
      if (count >= m) {
        elegibles++;
      }
    }
    System.out.print(elegibles);
  }
}

class SimpleLinkedList<T> {
  private Node<T> head;

  private static class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
      this.data = data;
    }
  }

  public void pushFront(T data) {
    Node<T> newNode = new Node<>(data);
    newNode.next = head;
    head = newNode;
  }

  public void pushBack(T data) {
    Node<T> newNode = new Node<>(data);
    if (head == null) {
      head = newNode;
    } else {
      Node<T> current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
  }

  public T popFront() {
    if (head == null) {
      return null;
    }
    T removed = head.data;
    head = head.next;
    return removed;
  }

  public T popBack() {
    if (head == null) {
      return null;
    }
    if (head.next == null) {
      T removed = head.data;
      head = null;
      return removed;
    }
    Node<T> current = head;
    while (current.next.next != null) {
      current = current.next;
    }
    T removed = current.next.data;
    current.next = null;
    return removed;
  }
}