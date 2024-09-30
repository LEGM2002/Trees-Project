public class ClearConsoleScreen {
    public static void main(String[] args){
        //ANSI scape code for clean de console
        //\033 means ESC, combine with [H the cursor moves to a specific position
        //and the last characters 033[2] clear the entire screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
