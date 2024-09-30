public class EsNumero {
    public static boolean esNumero(char caracter){
        boolean numero;
        try{
            Integer.parseInt(String.valueOf(caracter));
            numero = true;
        }catch(NumberFormatException ex){
            numero = false;
        }
        return numero;
    }
}
