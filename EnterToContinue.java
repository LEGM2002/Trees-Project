public class EnterToContinue {
    public static void main(String[] args){
        System.out.printf("\nPresione ENTER para continuar...");
        //try is for define a block of code to be tested 
        //catch is for the block of code to handle errors
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}
    }
}
