public class NodoAEA {
    //atributos
    char data;
    NodoAEA izquierdo, derecho;

    //constructor vacio
    public NodoAEA(){}

    //constructor con data
    public NodoAEA(char data){
        this.data = data;
        this.izquierdo = this.derecho = null;
    }
}
