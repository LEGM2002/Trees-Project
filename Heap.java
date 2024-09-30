import java.util.*;

    /*
     * Clase donde tendremos los métodos que utilizaremos a lo largo del método main.
     * 
     * @author Gerardo Arredondo
     * 
     * 
     * 
     */
public class Heap {
    /**
     * Método para agregar valores a nuestro heap
     * @param valorAAgregar valor que se quiere agregar al árbol
     * @param heap Nuestro árbol principal en donde se guardará el valor
     * @return devuelve el árbol ya con el nuevo elemento agregado
     */
    public static PriorityQueue<Integer> agregar(int valorAAgregar, PriorityQueue<Integer> heap){
        heap.add(valorAAgregar);
        System.out.println("\nSe ha ingresado el valor correctamente...");
        return heap;
    }

    /**
     * Método para imprimir todo el árbol
     * @param heap Nuestro árbol principal de donde se imprimirán los valores
     */
    public static void imprimirArbol(PriorityQueue<Integer> heap){
        Iterator<Integer> iterador = heap.iterator();
        while (iterador.hasNext()){
            System.out.println(iterador.next());
        }
    }

    /**
     * Método para eliminar una clave contenida dentro de nuestro árbol
     * @param valorAEliminar valor que se quiere eliminar del árbol
     * @param heap nuestro árbol principal de donde se eliminará el valor deseado
     * @return devuelve el árbol ya con el elemento eliminado
     */
    public static PriorityQueue<Integer> eliminar(int valorAEliminar, PriorityQueue<Integer> heap){
        if(heap.contains(valorAEliminar)){
            heap.remove(valorAEliminar);
            System.out.println("\nSe ha eliminado el valor...");
        }
        else{
            System.out.println("\nEl valor que ingresaste no existe dentro del árbol...");
        }
        return heap;
    }
}
