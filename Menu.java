//Clase principal del Proyecto 1
import java.util.*;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueInThisMenu = true;
        int mainOption, option; 
        while(continueInThisMenu){
            System.out.println("\t-----MENÚ PRINCIPAL-----\n"
                    + "\n[1]. Árbol binario de búsqueda balanceado."
                    + "\n[2]. Heaps."
                    + "\n[3]. Árbol de expresiones aritméticas."
                    + "\n[4]. Salir.");
            System.out.printf("\nIngrese la opción elegida: ");
            mainOption = sc.nextInt();
            switch(mainOption){
                case 1:
                    AVL avl = new AVL();
                    do{
                        ClearConsoleScreen.main(args);
                        System.out.println("-----MENÚ ÁRBOL BINARIO DE BÚSQUEDA BALANCEADO-----\n"
                            + "\n[1]. Agregar dato."
                            + "\n[2]. Buscar dato."
                            + "\n[3]. Eliminar dato."
                            + "\n[4]. Mostrar árbol."
                            + "\n[5]. Regresar.");
                        System.out.printf("\nIngrese la opción elegida: ");
                        option = sc.nextInt();
                        switch(option){
                            case 1:
                                ClearConsoleScreen.main(args);
                                System.out.print("\nIngresa el valor a agregar --> ");
                                int valorAAgregar = sc.nextInt();
                                avl.root = avl.add(avl.root, valorAAgregar);
                                sc.nextLine();
                                EnterToContinue.main(args);
                                break;
                            case 2:
                                ClearConsoleScreen.main(args);
                                System.out.print("\nIngresa el valor a buscar --> ");
                                int valorABuscar = sc.nextInt();
                                avl.searchAVL(valorABuscar);
                                EnterToContinue.main(args);
                                break;
                            case 3:
                                ClearConsoleScreen.main(args);
                                System.out.print("\nIngresa el valor a eliminar --> ");
                                int valorAEliminar = sc.nextInt();
                                NodoAVL eliminado = avl.searchAVL(valorAEliminar);
                                if (eliminado != null) {
                                    NodoAVL padre = avl.searchParentNode(eliminado);
                                    if (padre != null) {
                                        avl.removeAVL(padre, eliminado);
                                    }
                                }
                                else{
                                    System.out.println("\nEl nodo no existe...");
                                }
                                EnterToContinue.main(args);
                                break;
                            case 4:
                                ClearConsoleScreen.main(args);
                                System.out.println("\n---Árbol binario de búsqueda balanceado por expansión---\n");
                                avl.breadthFirst();
                                System.out.println("\n---Notación prefija---\n");
                                avl.preOrden(avl.root);
                                System.out.println();
                                EnterToContinue.main(args);
                                break;
                            case 5:
                                ClearConsoleScreen.main(args);
                                break;
                            default:
                                ClearConsoleScreen.main(args);
                                System.out.println("Opción incorrecta, intente de nuevo...\n");
                                continue;
                        }
                    }
                    while(option != 5);
                    ClearConsoleScreen.main(args);
                    break;
                case 2:
                    PriorityQueue<Integer> HeapPrincipal = new PriorityQueue<Integer>(Collections.reverseOrder());
                    do{
                        ClearConsoleScreen.main(args);
                        System.out.println("-----MENÚ HEAPS-----\n"
                            + "\n[1]. Agregar dato."
                            + "\n[2]. Eliminar dato."
                            + "\n[3]. Mostrar árbol."
                            + "\n[4]. Regresar.");
                        System.out.printf("\nIngrese la opción elegida: ");
                        option = sc.nextInt();
                        switch(option){
                            case 1:
                                ClearConsoleScreen.main(args);
                                System.out.print("\nIngresa el valor a agregar --> ");
                                int valorAAgregar = sc.nextInt();
                                sc.nextLine();
                                HeapPrincipal = Heap.agregar(valorAAgregar, HeapPrincipal);
                                EnterToContinue.main(args);
                                break;
                            case 2:
                                ClearConsoleScreen.main(args);
                                System.out.print("\nIngresa el valor a eliminar --> ");
                                int valorAEliminar = sc.nextInt();
                                sc.nextLine();
                                HeapPrincipal = Heap.eliminar(valorAEliminar, HeapPrincipal);
                                EnterToContinue.main(args);
                                break;
                            case 3:
                                ClearConsoleScreen.main(args);
                                System.out.println("\n----Heap por expansión----\n");
                                Heap.imprimirArbol(HeapPrincipal);
                                EnterToContinue.main(args);
                                break;
                            case 4:
                                ClearConsoleScreen.main(args);
                                break;
                            default:
                                ClearConsoleScreen.main(args);
                                System.out.println("Opción incorrecta, intente de nuevo...\n");
                                continue;
                        }
                    }
                    while(option != 4);
                    ClearConsoleScreen.main(args);
                    break;
                case 3:
                    ArbolExpresionAritmetica arbolEA = new ArbolExpresionAritmetica();
                    String expresion = "";
                    do{
                        ClearConsoleScreen.main(args);
                        System.out.println("-----MENÚ ÁRBOL DE EXPRESIONES ARITMÉTICAS-----\n"
                            + "\n[1]. Ingresar expresión."
                            + "\n[2]. Mostrar árbol."
                            + "\n[3]. Resolver."
                            + "\n[4]. Regresar.");
                        System.out.printf("\nIngrese la opción elegida: ");
                        option = sc.nextInt();
                        switch(option){
                            case 1:
                                ClearConsoleScreen.main(args);
                                System.out.printf("\nIngresa la expresión aritmética: ");
                                expresion = sc.next();
                                arbolEA = new ArbolExpresionAritmetica(expresion);
                                EnterToContinue.main(args);
                                break;
                            case 2:
                                ClearConsoleScreen.main(args);
                                System.out.println("\n----Árbol de expresión aritmética por expansión----\n");
                                arbolEA.breadthFrist();
                                EnterToContinue.main(args);
                                break;
                            case 3:
                                ClearConsoleScreen.main(args);
                                arbolEA.resolver();
                                EnterToContinue.main(args);
                                break;
                            case 4:
                                ClearConsoleScreen.main(args);
                                break;
                            default:
                                ClearConsoleScreen.main(args);
                                System.out.println("Opción incorrecta, intente de nuevo...\n");
                                continue;
                        }
                    }
                    while(option != 4);
                    ClearConsoleScreen.main(args);
                    break;
                case 4:
                    continueInThisMenu = false;
                    break;
                default:
                    ClearConsoleScreen.main(args);
                    System.out.println("Opción incorrecta, intente de nuevo...\n");
                    continue;
            }
        }
        sc.close();
    }
}
