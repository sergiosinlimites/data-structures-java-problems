
/**
 * ## Enunciado del problema
 * 
 * Carlos, un estudiante de ingeniería de sistemas, está pensando en viajar en
 * vacaciones para cuando acabe este semestre en julio. Para eso pregunta a su
 * amiga Mariana, que es una reconocida guía turística de la región, y ella le
 * recomienda viajar por diferentes pueblitos de Boyacá. A Carlos le gustó la
 * idea y le pidió que escribiera en una lista de diferentes lugares que debería
 * visitar. Mariana escribió la siguiente lista:
 * 
 * {"Mongui", "Sachica", "Tinjaca", "Combita", "Chiquiza", "Sutamarchan",
 * "Tibasosa", "Toca", "Guican", "Chivata", "Topaga", "Soraca", "Gameza",
 * "Guayata", "Raquira", "Nobsa", "Tenza", "Aquitania"}
 * 
 * Pero Carlos, que se apasiona de aplicar lo que aprende en Estructuras de
 * Datos en su vida cotidiana, decide que va a agregar uno a uno y en orden los
 * lugares que escribió Mariana en la lista anterior en un árbol AVL. Los
 * nombres se agregan por orden lexicográfico.
 * 
 * Una vez que Carlos crea el árbol AVL, se pregunta cuál será el menor número
 * de lugares que debe visitar entre dos lugares organizados en el árbol (se
 * tienen en cuenta los lugares de inicio y fin del recorrido).
 * 
 * Por ejemplo:
 * En la imagen anterior, se creó un árbol AVL con las letras en el rango
 * [a..i]. Para ir del nodo 'c' al nodo 'e' se debe seguir la ruta c->b->d->f->e
 * dando un total de 5 nodos.
 * 
 * # Entrada
 * 
 * Dos puntos de partida y llegada diferentes, separados por espacios.
 * 
 * 
 * Salida
 * 
 * ---
 * 
 * Número mínimo de lugares que debe visitar en el recorrido por el árbol AVL
 * (Recuerde que en esta cuenta se cuentan los puntos de salida y llegada)
 * 
 * 
 * ## Ejemplos
 */

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    AVLTree tree = new AVLTree("Mongui");

    Scanner scanner = new Scanner(System.in);
    String lugares = scanner.nextLine();
    String[] lugaresSeparados = lugares.split(" ");
    String salida = lugaresSeparados[0];
    String llegada = lugaresSeparados[1];

    String[] lista = { "Sachica", "Tinjaca", "Combita", "Chiquiza", "Sutamarchan", "Tibasosa", "Toca", "Guican",
        "Chivata", "Topaga", "Soraca", "Gameza", "Guayata", "Raquira", "Nobsa", "Tenza", "Aquitania" };

    Node root = tree.getRoot();

    for (String l : lista) {
      tree.insertNew(l);
    }

    // tree.printall(root);
    Node nodoSalida = tree.find(salida);
    tree.getPlacesBetween(nodoSalida, llegada);
  }

  static class Node {
    private String key;
    private int height;
    private Node left, right, parent;

    Node(String k) {
      key = k;
    }
  }

  static class AVLTree {
    private Node root;

    public AVLTree(String key) {
      root = new Node(key);
    }

    public Node getRoot() {
      return root;
    }

    private boolean isEqual(Node node, String key) {
      return node.key.equals(key);
    }

    private boolean isGreater(Node node, String key) {
      int minLength = Math.min(node.key.length(), key.length());
      for (int i = 0; i < minLength; i++) {
        if (node.key.charAt(i) < key.charAt(i)) {
          return true; // El nodo es menor
        } else if (node.key.charAt(i) > key.charAt(i)) {
          return false; // El nodo es mayor
        }
      }
      return key.length() > node.key.length(); // Si todos los caracteres coinciden, el nodo más largo es mayor
    }

    private boolean isLess(Node node, String key) {
      return !isGreater(node, key);
    }

    public void printall(Node node) {
      if (node != null) {
        System.out.println(node.key);
        printall(node.left);
        printall(node.right);
      }
    }

    /**
     * Gets the number of nodes between 2 nodes of the tree (counts the extremes)
     * 
     * @param n   The departure node
     * @param key The key name of the arrival node
     */
    public void getPlacesBetween(Node n, String key) {
      Node startingNode = n;
      Node currentNode = startingNode;
      int upSteps = 1;
      int downSteps = 0;
      boolean finished = false;
      downSteps = findStops(currentNode, key, upSteps);
      if (downSteps > 0) { // Search down first
        System.out.print(downSteps);
      } else { // Starts searching in the upper nodes until the root
        while (currentNode.parent != null && !finished) {
          Node lastNode = currentNode;
          currentNode = currentNode.parent;
          upSteps++;
          if (currentNode.left != null && isEqual(currentNode.left, lastNode.key) && currentNode.right != null) {
            downSteps = findStops(currentNode.right, key, upSteps);
          } else if (currentNode.right != null && isEqual(currentNode.right, lastNode.key)
              && currentNode.left != null) {
            downSteps = findStops(currentNode.left, key, upSteps);
          }
          if (downSteps > 0) {
            System.out.print(downSteps);
          }
        }
      }
    }

    /**
     * Find a node from the key
     * 
     * @param key The name
     * @return The found node
     */
    public Node find(String key) {
      Node resultNode = findNode(root, key);
      return resultNode;
    }

    /**
     * Internal method for finding (recursive)
     */
    private Node findNode(Node node, String key) {
      if (node == null || isEqual(node, key)) {
        return node;
      } else if (isLess(node, key)) {
        return findNode(node.left, key);
      } else {
        return findNode(node.right, key);
      }
    }

    /**
     * Search node by node until it finds the sought node
     */
    private int findStops(Node node, String key, int stops) {
      if (isEqual(node, key) && node != null) {
        return stops;
      } else if (isLess(node, key) && node.left != null) {
        return findStops(node.left, key, stops + 1);
      } else if (isGreater(node, key) && node.right != null) {
        return findStops(node.right, key, stops + 1);
      } else {
        return -1;
      }
    }

    /**
     * Updates the height of a node
     * 
     * @param n The node
     */
    private void updateHeight(Node n) {
      n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    /**
     * Returns the height of a node
     * 
     * @param n The node
     * @return The height
     */
    private int height(Node n) {
      return n == null ? -1 : n.height;
    }

    /**
     * For balancing the AVL
     * 
     * @param n The node that you want to know its balance
     * @return -n ... n
     */
    private int getBalance(Node n) {
      return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    /**
     * Right rotation AVL
     */
    private Node rotateRight(Node n) {
      Node left = n.left;
      Node right = left.right;
      left.right = n;
      left.parent = n.parent;
      n.parent = left;
      n.left = right;
      updateHeight(n);
      updateHeight(left);
      return left;
    }

    /**
     * Left rotation AVL
     */
    private Node rotateLeft(Node n) {
      Node right = n.right;
      Node left = right.left;
      right.left = n;
      right.parent = n.parent;
      n.parent = right;
      n.right = left;
      updateHeight(n);
      updateHeight(right);
      return right;
    }

    /**
     * Rebalances the tree if a node is unbalanced
     * 
     * @param n
     * @return
     */
    Node rebalance(Node n) {
      updateHeight(n);
      int balance = getBalance(n);
      if (balance > 1) {
        if (height(n.right.right) > height(n.right.left)) {
          n = rotateLeft(n);
        } else {
          n.right = rotateRight(n.right);
          n = rotateLeft(n);
        }
      } else if (balance < -1) {
        if (height(n.left.left) > height(n.left.right))
          n = rotateRight(n);
        else {
          n.left = rotateLeft(n.left);
          n = rotateRight(n);
        }
      }
      return n;
    }

    /**
     * Insert a new key
     * 
     * @param key
     */
    public void insertNew(String key) {
      Node node = root;
      root = this.insert(node, key);
    }

    /**
     * AVL insert method
     * 
     * @param node The node
     * @param key  The key
     * @return The new root (could be different)
     */
    public Node insert(Node node, String key) {
      if (node == null) {
        return new Node(key);
      } else if (isLess(node, key)) {
        node.left = insert(node.left, key);
        node.left.parent = node;
      } else if (isGreater(node, key)) {
        node.right = insert(node.right, key);
        node.right.parent = node;
      } else {
        throw new RuntimeException("duplicate key!");
      }
      return rebalance(node);
    }

    /**
     * AVL delete method
     */
    private Node delete(Node node, String key) {
      if (node == null) {
        return node;
      } else if (isLess(node, key)) {
        node.left = delete(node.left, key);
      } else if (isGreater(node, key)) {
        node.right = delete(node.right, key);
      } else {
        if (node.left == null || node.right == null) {
          node = (node.left == null) ? node.right : node.left;
        } else {
          Node mostLeftChild = mostLeftChild(node.right);
          node.key = mostLeftChild.key;
          node.right = delete(node.right, node.key);
        }
      }
      if (node != null) {
        node = rebalance(node);
      }
      return node;
    }

    /**
     * Gets the most left child of the tree
     * 
     * @param node
     * @return
     */
    private Node mostLeftChild(Node node) {
      while (node.left != null) {
        node = node.left;
      }
      return node;
    }
  }

}
