/**
 * Description: This is the class prints everything for the connect4 game
 */
package connectfour;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI{
    private final Scanner inputInt;
    private final Scanner inputString;
    private int menuChoice;
    private int columnNumber;
    private String filename;

    /**
     * No arg constructor
     */
    public TextUI(){
        inputInt = new Scanner(System.in);
        inputString = new Scanner(System.in);
        menuChoice = 0;
        columnNumber = 0;
        filename = "No name";
    }

    /**
     * Displays menu to user and gets input
     * @return menuOption
     * @author Zaeem Farhan
     */
    public int menu(){
        boolean error;

        do {
            System.out.print("\nWelcome to CONNECT4\n(1) New game\n(2) Load game\n(3) Instructions\n(4) Exit\nEnter "
                    + "Choice Number: ");

            try{
                error = false;
                menuChoice = inputInt.nextInt();

            }catch(InputMismatchException e){
                inputInt.next();
                System.err.println("Please enter a integer between 1 and 4, try again\n");
                error = true;
            }

        }while(error);
        return menuChoice;
    }

    /**
     * Displays the actual game
     * @param board board class toString
     * @param turn  current player
     * @return Column number chosen by user
     * @author Zaeem Farhan
     */
    public int playGame(String board, char turn){
        boolean error;

        do{
            printBoard(board);
            System.out.print("\nTurn: " + turn + "\n(To save game enter 0)\nEnter column number (1-7): ");
            try {
                error = false;
                columnNumber = inputInt.nextInt();

            }catch(InputMismatchException e){
                inputInt.next();
                System.err.println("Please enter a integer between 1 and 7, try again\n");
                error = true;
            }
            //error message
            if(!error && (columnNumber < 0 || columnNumber > 7)){
                System.err.println("Please enter a valid integer between 1 and 7, try again\n");
                error = true;
            }
        }while(error);

        return columnNumber;
    }

    /**
     * Displays message if column is full
     * @author Zaeem Farhan
     */
    public void columnFullMessage(){
        System.err.println("\nThe chosen column is full, choose another column.\n");
    }

    /**
     * Displays current board
     * @param board board
     * @author Zaeem Farhan
     */
    public void printBoard(String board){
        System.out.print("\n" + board);
    }

    /**
     * Prints instructions of game to user
     * @author Zaeem Farhan
     */
    public void printInstructions(){
        Scanner enterKey = new Scanner(System.in);
        System.out.print("\n\nINSTRUCTIONS:\n\n1. In this game there are 2 players, Player R (Red) and Player\n   " 
                +"Y (Yellow) and it is played on a 6x7 Grid.\n\n2. Player R goes first, and players will then alter" 
                +"nate turns.\n\n3. You drop your checker (either 'R' or 'Y) into a column and\n   your goal is to " 
                +"get 4 checkers of your colour in a row.\n\n4. If you are the first player to get four in a row " 
                +"(vertically,\n   horizontally or diagonally), you win the game!\n\nPress Enter key to continue...");

        enterKey.nextLine();
    }

    /**
     * Prints final result of current game
     * @param player Winner
     * @param tieOrWin Tie or win boolean, true if someone won, false if tie
     * @author Zaeem Farhan
     */
    public void printResult(char player, boolean tieOrWin){
        if(tieOrWin) {
            System.out.println("\nCongratulations, the winner is " + player + "!");

        }else{
            System.out.println("\nThe game is a tie!");
        }
    }

    /**
     * Gets user input for file name
     * @return file name
     * @author Zaeem Farhan
     */
    public String getFileName(){
        System.out.print("\nEnter filename: ");
        filename = inputString.nextLine();

        return filename;
    }

    /**
     * Prints message to user after saving game to file
     * @param flag True if file saving was sucessful, false otherwise
     * @author Zaeem Farhan
     */
    public void printSaveFileMsg(boolean flag){
        if(flag){
            System.out.println("\nSave to file successful!");

        }else{
            System.out.println("\nSave to file unsuccessful.");
        }
    }

    /**
     * Prints message to user after loading file
     * @param flag True if file loading was successful, false otherwise
     * @author Zaeem Farhan
     */
    public void printLoadFileMsg(boolean flag){
        if(flag){
            System.out.println("\nLoad file successful!");

        }else{
            System.out.println("\nLoad to file unsuccessful.\nThe file does not exist OR the file does not follow " 
                    +"proper Connect4 format.");
        }
    }

    /**
     * toString method
     * @return toString
     * @author Zaeem Farhan
     */
    @Override
    public String toString(){
        return "\nMenu:\nWelcome to CONNECT4\n(1) New game\n(2) Load game\n(3) Instructions\n(4) Exit\nCurrent User " 
                +"Menu Choice: " + menuChoice + "\nColumn chosen by player: " + columnNumber;
    }
}
