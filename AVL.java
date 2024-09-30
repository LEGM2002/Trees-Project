import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase para representar y realizar operaciones de arboles AVL.
 * @author Diego Aldair García Hernández
 * @version 1.0
 */

public class AVL {

    /**
     * Atributo que presenta una varible de tipo Nodo que representa la raíz del arbol a crear.
     */
    NodoAVL root;

    /**
     * Constrctor vacio para la creacion de arboles AVL.
     * No recibe parametros.
     * Se asigna el valor de null a la raíz del arbol.
     */
    public AVL() {
        root = null;
    }


    /**
     * Constrctor para la creacion de arboles AVL. 
     * Se crea un nuevo Nodo representando la raíz del arbol
     * @param val Define la raíz del arbol AVL. 
     */
    public AVL(int val) {
        root = new NodoAVL(val);
    }

    // *************************************Metodos auxiliares *************************************

    /**
     * Metodo recursivo auxiliar de eliminación para poder encontrar el valor predecessor,
     *  es decir, el elemento más a la derecha del sub-árbol izquerdo
     * @param eliminado Valor de tipo Nodo que representa el elemento a eliminar.
     * @return Retorna un valor de tipo Nodo que será el elemento predecessor.
     */
    private NodoAVL encontrarPred(NodoAVL eliminado) {
        if (eliminado.der != null) {
            if (eliminado.der.der != null) {
                return encontrarPred(eliminado.der.der);
            }
        }

        return eliminado;
    }


    /**
     * Metodo recursivo auxiliar de eliminación para poder encontrar el valor successor,
     *  es decir, el elemento más a la izquierda del sub-árbol derecho.
     * @param eliminado Valor de tipo Nodo que representa el elemento a eliminar..
     * @return Retorna un valor de tipo Nodo que será el elemento successor.
     */
    private NodoAVL encontrarSucc(NodoAVL eliminado) {
        if (eliminado.izq != null) {
            if (eliminado.izq.izq != null) {
                return encontrarSucc(eliminado.izq.izq);
            }
        }
        return eliminado;
    }

    /**
     * Método auxiliar que ayuda a encontrar la altura de un elemento dentro del arbol.
     * @param nodo Variable de tipo Nodo que será el elemento al que se debe buscar la altura. 
     * @return Retorna 0 en caso de que el nodo sea nulo, y la altura del nodo en caso de que exista.
     */
    private int encontrarAltura(NodoAVL nodo) {
        if (nodo == null)
            return 0;
        return nodo.altura;
    }

    /**
     * Método auxiliar que interviene para encontrar el factor de balance o equilibrio de un elemento. 
     * El factor de balance se obtiene restando la altura del nodo izq menos la del nodo der.
     * @param nodo Variable de tipo Nodo que será el elemento al que se debe buscar su factor de balance.
     * @return Retorna un valor de tipo int representando el factor de balance.
     */
    private int facBalance(NodoAVL nodo) {
        int balance = 0;
        if (nodo == null)
            return balance;
        else {
            balance = encontrarAltura(nodo.izq) - encontrarAltura(nodo.der);
        }
        return balance;
    }

    /**
     * Método auxiliar de arboles AVL que asiste para realizar un rotación simple a la derecha. 
     * @param n Valor de tipo Nodo que será el elemento que se rotara a la derecha.
     * @return Retorna el nuevo valor que estará en la posición en donde se encontraba n.
     */
    private NodoAVL rotacionDerecha(NodoAVL n) {
        System.out.println("\nRotacion a la derecha...\n");
        NodoAVL m = n.izq;
        NodoAVL aux = m.der;
        // System.out.println("aux: " + aux.valor);

        m.der = n;
        n.izq = aux;

        n.altura = Math.max(encontrarAltura(n.izq), encontrarAltura(n.der)) + 1;
        m.altura = Math.max(encontrarAltura(m.izq), encontrarAltura(m.der)) + 1;

        return m;
    }

    /**
     * Método auxiliar de arboles AVL que contribuye al realizar una rotación simple a la izquierda.
     * @param m Valor de tipo Nodo que será el elemento que se rotara. a la izquierda.
     * @return Retorna el nuevo valor presentado en la posición en donde se encontraba m
     */
    private NodoAVL rotacionIzquierda(NodoAVL m) {
        System.out.println("\nRotación a la izquierda...\n");
        NodoAVL n = m.der;
        NodoAVL aux = n.izq;

        n.izq = m;
        m.der = aux;

        m.altura = Math.max(encontrarAltura(m.izq), encontrarAltura(m.der)) + 1;
        n.altura = Math.max(encontrarAltura(n.izq), encontrarAltura(n.der)) + 1;

        return n;
    }

    // *************************************Inserción*************************************
    
    /**
     * Método que realiza el proceso para agregar un elemento o nodo al arbol AVL.
     * Se apoya de metodos como: encontrarAltura(), rotacionIzquierda(),
     *  rotacionDerecha() y facBalance() para realizar la insecion correctamente.
     * @param raiz Nodo que será la raíz del arbol AVL.
     * @param key Valor de tipo int a insertar dentro del arbol AVL.
     * @return Retorna la raíz
     */
    public NodoAVL add(NodoAVL raiz, int key) {

        // Agregar Nodo
        if (raiz == null) {
            System.out.println("\nSe ha agregado el valor correctamente...");
            return (new NodoAVL(key));
        }
        if (key < raiz.valor) {
            raiz.izq = add(raiz.izq, key);
        } else if (key > raiz.valor) {
            raiz.der = add(raiz.der, key);
        } else {
            System.out.println("\nSe ha agregado el valor correctamente...");
            return raiz;
        }

        
        //Establecer altura del nodo
        raiz.altura = 1 + Math.max(encontrarAltura(raiz.izq), encontrarAltura(raiz.der));

        //System.out.println("Altura: " + (raiz.altura - 1));
        
        // Establecer factor equilibrante al nodo
        int balance = facBalance(raiz);
        //System.out.println("Factor balance: " + balance);

        //Se verifica que se cumpla el factor de balance permitido. 
        //En caso contrario se realiza rotaciones de acuerdo a la necesidad
        if (balance > 1 && key < raiz.izq.valor) {
            return rotacionDerecha(raiz);
        }
        if (balance < -1 && key > raiz.der.valor) {
            return rotacionIzquierda(raiz);
        }
        if (balance > 1 && key > raiz.izq.valor) {
            raiz.izq = rotacionIzquierda(raiz.izq);
            return rotacionDerecha(raiz);
        }
        if (balance < -1 && key < raiz.der.valor) {
            raiz.der = rotacionDerecha(raiz.der);
            return rotacionIzquierda(raiz);
        }
        return raiz;
    }

    // *************************************Eliminacion********************************

    /**
     * Método que realiza la función de eliminar un elemento del arbol.
     * Se apoya de metodos como: removeCase1(), removeCase2, removeCase3,
     * encontrarAltura(), rotacionIzquierda(), rotacionDerecha() 
     * y facBalance() para realizar la insecion correctamente.
     * @param padre Valor de tipo Nodo que representa el padre del elemento a eliminar del arbol.
     * @param eliminado Valor de tipo Nodo que representa el elemento a eliminar.
     * @return Valor de tipo boolean reprsentando si el elemento fue eliminado correctamente.
     */
    public boolean removeAVL(NodoAVL padre, NodoAVL eliminado) {
        if (eliminado == null || padre == null) {
            System.out.println("\nNodo no encontrado...");
            return false;
        } else {
            boolean NodoDer = eliminado.der != null ? true : false;
            boolean NodoIzq = eliminado.izq != null ? true : false;

            if (NodoDer == false && NodoIzq == false) {
                removeCase1(padre, eliminado);
            }

            if (NodoDer == true && NodoIzq == false) {
                removeCase2(padre, eliminado);
            }

            if (NodoDer == false && NodoIzq == true) {
                removeCase2(padre, eliminado);
            }

            if (NodoDer == true && NodoIzq == true) {
                removeCase3(padre, eliminado);
            }

        }
        padre.altura = Math.max(encontrarAltura(padre.izq), encontrarAltura(padre.der)) + 1;

        int balance = facBalance(padre);
        //System.out.println("Factor balance: " + balance);


        if (balance > 1 && facBalance(padre.izq) >= 0)
            rotacionDerecha(padre);

        if (balance > 1 && facBalance(padre.izq) < 0) {
            padre.izq = rotacionIzquierda(padre.izq);
            rotacionDerecha(padre);
        }

        if (balance < -1 && facBalance(padre.der) <= 0)
            rotacionIzquierda(padre);

        if (balance < -1 && facBalance(padre.der) > 0) {
            padre.der = rotacionDerecha(padre.der);
            rotacionIzquierda(padre);
        }

        return true;

    }


    /**
     * Metodo auxiliar de eliminación que representa el caso 1 (no existencia de hijos)
     * @param padre Valor de tipo Nodo que reprsenta el padre del elemento a eliminar.
     * @param eliminado 
     * @return Valor de tipo boolean representando si el elemento fue eliminado correctamente
     */
    public boolean removeCase1(NodoAVL padre, NodoAVL eliminado) {
        NodoAVL Hijoizq = padre.izq;
        NodoAVL Hijoder = padre.der;

        if (Hijoizq == eliminado) {
            padre.izq = null;
            return true;
        }

        if (Hijoder == eliminado) {
            padre.der = null;
            return true;
        }

        return false;

    }

    /**
     * Método auxiliar de eliminación que reprenseta el caso 2 (existencia de solo un hijo)
     * @param padre Valor de tipo Nodo que representa el padre del elemento a eliminar.
     * @param eliminado Valor de tipo Nodo que representa el elemento a eliminar.
     * @return valor de tipo boolean representando si el elemento fue eliminado correctamente
     */
    public boolean removeCase2(NodoAVL padre, NodoAVL eliminado) {
        NodoAVL Hijoizq = padre.izq;
        NodoAVL Hijoder = padre.der;

        NodoAVL hijoActual;
        if (eliminado.izq != null) {
            hijoActual = eliminado.izq;
        } else {
            hijoActual = eliminado.der;
        }

        if (Hijoizq == eliminado) {
            padre.izq = hijoActual;

            eliminado.izq = null;
            eliminado.der = null;

            return true;
        }

        if (Hijoder == eliminado) {
            padre.der = hijoActual;

            eliminado.izq = null;
            eliminado.der = null;

            return true;
        }

        return false;
    }

    /**
     * Método auxiliar de eliminación que reprenseta el caso 3 (existencia de ambos hijos)
     * @param padre Nodo que representa el padre de el elemento a eliminar.
     * @param eliminado Nodo que representa el elemento a eliminar.
     * @return valor de tipo boolean representando si el elemento fue eliminado correctamente
     */
    public boolean removeCase3(NodoAVL padre, NodoAVL eliminado) {
        NodoAVL masIzq = encontrarSucc(eliminado.der);
        NodoAVL masDer = encontrarPred(eliminado.izq);
        //System.out.println("mas izq:" + masIzq.valor);
        //System.out.println("mas der:" + masDer.valor);

        if (masIzq.izq != null) {
            eliminado.valor = masIzq.izq.valor;
            //System.out.println("Elemento izq: " + masIzq.izq.valor);
            // masIzq = null;
            removeAVL(masIzq, masIzq.izq);

            return true;
        } else if (masDer.der != null) {
            //System.out.println("Elemento der: " + masDer.der.valor);
            eliminado.valor = masDer.der.valor;
            removeAVL(masDer, masDer.der);

            return true;
        } else {
            eliminado.valor = masDer.valor;
            removeAVL(eliminado, masDer);
        }
        return false;

    }


    // *************************************Métodos de busqueda*************************************

    /**
     * Método que realiza el proceso de busqueda de un elemento dentro del arbol AVL.
     * @param val Valor de tipo int que simboliza el elemento a buscar.
     * @return Nodo encontrado con su valor y altura. En caso contrario, retorna null.
     */
    public NodoAVL searchAVL(int val) {
        NodoAVL r = root;
        Queue<NodoAVL> queue = new LinkedList<NodoAVL>();
        if (r != null) {
            queue.add(r);
            while (!queue.isEmpty()) {
                r = (NodoAVL) queue.poll();
                if (val == r.valor) {
                    System.out.println("\nSi existe el nodo con valor " + r.valor 
                    + " y su altura es " + encontrarAltura(r));
                    return r;
                } else {
                    if (r.izq != null)
                        queue.add(r.izq);
                    if (r.der != null)
                        queue.add(r.der);
                }
                // System.out.println("Tamano: " + queue.size());
            }
        }
        System.out.println("\nNo existe el nodo con valor: " + val);
        return null;
    }

    /**
     * Método que realiza el proceso de busqueda del padre de un elemento dentro del arbol AVL.
     * @author Luis Eduardo García Martinez
     * @param child Nodo que representa el elemento del que se buscara el padre.
     * @return Variable de tipo nodo que representa el padre del elemento  buscado.
     */
    public NodoAVL searchParentNode(NodoAVL child) {
        NodoAVL parent = root;
        // is the root
        if (child == parent) {
            return parent;
        }
        // queue to add the visited nodes
        Queue<NodoAVL> queue = new LinkedList<>();
        if (parent != null) {
            // add the root node
            queue.add(parent);
            while (!queue.isEmpty()) {
                // cast to Nodo class
                parent = (NodoAVL) queue.poll();
                // compare the father childs with child node
                if (parent.der != null && parent.der == child || parent.izq != null && parent.izq == child) {
                    // isn't necessary to continue the search
                    System.out.println("\nEl padre del nodo a eliminar es: " + parent.valor);
                    break;
                } else {
                    // visit left and right node until one of these is empty
                    if (parent.izq != null)
                        queue.add(parent.izq);
                    if (parent.der != null)
                        queue.add(parent.der);
                }
            }
        }
        return parent;
    }


    //*************************************Métodos para mostrar arbol*************************************
    
    /**
     * Método auxiliar de recorrido para poder mostrar el elemento en conjunto con su altura.
     * @param n Valor de tipo Nodo que representa el elemento a imprimir en pantalla.
     */
    protected void visit(NodoAVL n) {
        System.out.println(n.valor + " - Altura: " + (encontrarAltura(n)));
    }

    /**
     * Método para realizar el recorrido del arbol AVL y mostrar en pantalla los elementos.
     * @return Retorna un valor de tipo boolean para representar que el recorrido se hizo correctamente.
     */
    public Boolean breadthFirst() {
        NodoAVL r = root;
        Queue<NodoAVL> queue = new LinkedList<NodoAVL>();
        if (r != null) {
            queue.add(r);
            while (!queue.isEmpty()) {
                r = (NodoAVL) queue.poll();
                visit(r);
                if (r.izq != null)
                    queue.add(r.izq);
                if (r.der != null)
                    queue.add(r.der);
            }
        }
        return true;
    }

    /**
     * Método para mostrar en pantalla el recorrido preOrden del arbol AVL. 
     * @param nodo
     */
    public void preOrden(NodoAVL nodo) {

        if (nodo == null)
            return;

        System.out.print(nodo.valor + " ");
        preOrden(nodo.izq);
        preOrden(nodo.der);

    }

}

