import java.util.*;

/**
 * Clase para crear un árbol de expresiones, realizar recorridos sobre él
 * y resolver la expresión aritmética
 * @author Luis Eduardo Garcia Martinez
 * @version 1.0
 */

public class ArbolExpresionAritmetica {
    //atributos
    public NodoAEA raiz;
    public String expresionPostfija = "";

    /**
     * Constructor vacío
     */
    public ArbolExpresionAritmetica(){
        this.raiz = null;
    }

    //constructor de arbol a partir de una expresion aritmetica
    /**
     * Constructor de un árbol de expresión aritmética
     * @param expresion Expresión aritmética ingresada por el usuario
     */
    public ArbolExpresionAritmetica(String expresion){
        Stack<NodoAEA> pilaDeExpresion = new Stack<>();
        Stack<NodoAEA> pilaDeOperadores = new Stack<>();
        NodoAEA operandoUno = new NodoAEA(), operandoDos = new NodoAEA(), operador = new NodoAEA(), nodoCaracter;
        char caracter;
        int prioridad;
        for(int index = 0; index < expresion.length(); index++){
            caracter = expresion.charAt(index);
            prioridad = prioridad(caracter);
            nodoCaracter = new NodoAEA(caracter);
            if(EsNumero.esNumero(caracter)){
                pilaDeExpresion.add(nodoCaracter);
            }
            else{
                switch(caracter){
                    case '(':
                        pilaDeOperadores.add(nodoCaracter);
                        break;
                    case ')':
                        while(!pilaDeOperadores.isEmpty() && pilaDeOperadores.peek().data != '('){
                            operandoDos = pilaDeExpresion.pop();
                            operandoUno = pilaDeExpresion.pop();
                            operador = pilaDeOperadores.pop();
                            operador.izquierdo = operandoUno;
                            operador.derecho = operandoDos;
                            pilaDeExpresion.add(operador);
                        }
                        //eliminar parentesis abierto
                        pilaDeOperadores.pop();
                        break;
                    default:
                        while(!pilaDeOperadores.isEmpty() && prioridad <= prioridad(pilaDeOperadores.peek().data)){
                            operandoDos = pilaDeExpresion.pop();
                            operandoUno = pilaDeExpresion.pop();
                            operador = pilaDeOperadores.pop();
                            operador.izquierdo = operandoUno;
                            operador.derecho = operandoDos;
                            pilaDeExpresion.add(operador);
                        }
                        pilaDeOperadores.add(nodoCaracter);
                }
            }
        }
        //si quedan operaciones pendientes se realizan
        while(!pilaDeOperadores.isEmpty()){
            operandoDos = pilaDeExpresion.pop();
            operandoUno = pilaDeExpresion.pop();          
            operador = pilaDeOperadores.pop();         
            operador.izquierdo = operandoUno;
            operador.derecho = operandoDos;
            pilaDeExpresion.add(operador);
        }  
        System.out.println("\nSe ha construido el árbol correctamente...");
        //el ultimo nodo que queda es la raiz ya con todos sus hijos     
        this.raiz = pilaDeExpresion.pop();
        postorden(this.raiz);
    }

    //imprimir valor del nodo
    /**
     * Método para imprimir la información del nodo que reciba
     * @param nodo Nodo del cual mostrará su información
     */
    public void visit(NodoAEA nodo){
        if(nodo.data == '(' || nodo.data == ')')
            System.out.print("");
        else
            System.out.println(nodo.data);
    }	
    
    //recorrer arbol binario por expansion
    /**
     * Método para realizar el recorrido por expansión de un árbol
     */
    public void breadthFrist(){
        if(this.raiz == null){
            System.out.println("\nNo se ha ingresado una expresión...");
        }
        NodoAEA raiz = this.raiz;
        //cola para agregar los nodos visitados
        Queue<NodoAEA> queue = new LinkedList<>();
        if(raiz != null){
            //añadir la raiz a la cola
            queue.add(raiz);
            while(!queue.isEmpty()){
                //FIFO y castear
                raiz = (NodoAEA)queue.poll();
                visit(raiz);
                //visitar nodo izquierdo o derecho hasta que alguno de ellos este vacio
                if(raiz.izquierdo != null)
                    queue.add(raiz.izquierdo);
                if(raiz.derecho != null)
                    queue.add(raiz.derecho);
            }
        }
    }

    //prioridad de los operadores
    /**
     * Método para obtener la prioridad de las operaciones
     * @param caracter Operando del cual se evaluará su prioridad
     * @return Retorna un entero que correponde a la prioridad
     */
    public int prioridad(char caracter){
        int prioridad = -1;
        switch(caracter){
            case '+':
            case '-':
                prioridad = 1;
                break;
            case '*':
            case '/':
                prioridad = 2;
                break;
            case '^':
                prioridad = 3;
                break;
            default:
                break;
        }
        return prioridad;
    }

    //recorrido en postorden para obtener la notacion polaca inversa
    /**
     * Método para realizar el recorrido en postorden en un árbol
     * @param nodo Nodo del cuál se hará el recorrido
     */
    public void postorden(NodoAEA nodo){
        if(nodo != null){
            postorden(nodo.izquierdo);
            postorden(nodo.derecho);
            this.expresionPostfija = this.expresionPostfija + nodo.data;
        }
    }

    //resolver sub-expresion
    /**
     * Método para resolver una sub expresión aritmética del árbol de expresión aritmética
     * @param operandoUno Valor del primer operando
     * @param operandoDos Valor del segundo operando
     * @param operador Operador
     * @return Retorna el resultado de la operación correspondiente dependiendo el valor del operador
     */
    public double resolverSubExpresion(double operandoUno, double operandoDos, char operador){
        //dependiendo el operador se retorna la expresion correspondiente
        switch(operador){
            case '+':
                return operandoUno + operandoDos;
            case '-':
                return operandoUno - operandoDos;
            case '*':
                return operandoUno * operandoDos;
            case '/':
                return operandoUno / operandoDos;
            case '^':
                return Math.pow(operandoUno, operandoDos);
            default:
                return -1;
        }
    }

    //resolver expresion usando una pila y la notacion polaca inversa
    /**
     * Método para resolver un árbol de expresión aritmética utilizando su notación postfija
     */
    public void resolver(){
        double resultado = 0;
        if(this.expresionPostfija == "")
            System.out.println("\nNo se ha ingresado la expresión...");
        else
            System.out.println("\nLa notación postfija es: " + this.expresionPostfija + "\n");
        Stack<Double> pila = new Stack<>();
        double operandoUno, operandoDos, resultadoSubExpresion;
        char caracter;
        //tomar cada caracter de la notacion posfija
        for(int i = 0; i < this.expresionPostfija.length(); i++){
            caracter = this.expresionPostfija.charAt(i);
            //si es numero se castea a double y se agrega a la pila
            if(EsNumero.esNumero(caracter)){
                pila.add(Double.parseDouble(String.valueOf(caracter)));
            }
            //si no, se retiran los dos operandos anteriores
            else{
                try{
                    operandoDos = pila.pop();
                    operandoUno = pila.pop();
                    //se resuelve la expresion entre el operador y los operandos
                    resultadoSubExpresion = resolverSubExpresion(operandoUno, operandoDos, caracter);
                    //se añade el resultado a la pila porque se van resolviendo subexpresiones
                    pila.add(resultadoSubExpresion);
                } catch (EmptyStackException ex){
                    pila = new Stack<>();
                }
            }
        }
        try{
            resultado = pila.pop();
        } catch (EmptyStackException ex){
            pila = new Stack<>();
        }
        System.out.println("El resultado de la expresión es: " + resultado);
    }
}


// //convertir expresion infija a posfija
    // /**
    //  * Método para convertir de notación infija a postfija
    //  * @param infija Expresión aritmética ingresada por le usuario
    //  * @return Retorna la expresión aritmética en notación postfija
    //  */
    // public String infijaAPostfija(String infija){
    //     infija = '(' + infija + ')';
    //     //pila para guardar parentesis y operadores
    //     Stack<Character> pila = new Stack<>();
    //     String postfija = "";
    //     char caracter, caracterDesapilado;
    //     for(int i = 0; i < infija.length(); i++){
    //         caracter = infija.charAt(i);
    //         //si es un numero se concatena a la notacion postfija
    //         if(EsNumero.esNumero(caracter))
    //             postfija += caracter;
    //         //de caso contrario se agrega a la pila
    //         else{
    //             if(caracter != ')'){
    //                 pila.add(caracter);
    //             }
    //             else {
    //                 for(int j = pila.size(); j >= 0; j--){
    //                     //si la pila esta vacia se instancia una nueva
    //                     try{
    //                         caracterDesapilado = pila.pop();
    //                         if(caracterDesapilado != '(')
    //                             postfija += caracterDesapilado;
    //                         else
    //                             continue;
    //                     } catch (EmptyStackException ex){
    //                         pila = new Stack<>();
    //                     }
                        
    //                 }
    //             }
    //         }
    //     }
    //     return postfija;
    // }
