/**
 * Clase para representar el nodo de un árbol AVL
 * @author Diego Aldair García Hernández.
 * @version 4.0
 */
public class NodoAVL {
    /**
     * Atributo de tipo int que representa el valor de un nodo.
     */
    int valor;
    /**
     * Atributo de tipo Nodo que representa el hijo izquiero del nodo.
     */
    NodoAVL izq = null;
    /**
     * Atributo de tipo Nodo que representa el hijo derecho del nodo.
     */
    NodoAVL der = null;
    /**
     * Atributo de tipo int que representa la altura del nodo.
     */
    int altura;

    /**
     * Constructor vacio para la creación de un nodo.
     */
    public NodoAVL() {
        izq = der = null;
    }

    /**
     * Constructor para la creación de un nodo. Se asignan valores a los atributos. 
     * @param data Valor que simboliza el valor del nodo a crear.
     */
    public NodoAVL(int data) {
        valor = data;
        altura = 1;
        izq = der = null;
    }

}