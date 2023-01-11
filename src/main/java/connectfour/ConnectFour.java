/**
 * Name: Zaeem Farhan
 * Assignment: CIS*2430 A2
 * Date: Nov 1 2021
 * Description: This is the class with the main method for Connect4
 */
package connectfour;

public class ConnectFour{

    public static void main(String[] args) throws InterruptedException {
        Board board = null;
        TextUI text = new TextUI();
        int menuChoice;
        char player = 0;
        boolean loadFile = false;

        do {
            menuChoice = text.menu(); //displays menu
            switch (menuChoice) {
                //starts new game for user
                case 1:
                    board = new Board();
                    player = 'R';
                    game(board, text, player);
                    break;

                case 2:
                    board = new Board();   //user can load a game from file
                    loadFile = board.loadGame(text.getFileName());
                    text.printLoadFileMsg(loadFile);
                    if(loadFile){
                        //prints board loaded from file
                        text.printBoard(board.toString());
                        Thread.sleep(1000);
                        //prints if board loaded from file 
                        if (board.checkWinner()) {
                            text.printResult(switchPlayer(player), true);

                        } else if (board.getDepth() == 42) {
                            text.printResult(player, false);
                        //otherwise game from file will be continued
                        }else{
                            player = board.getCurrentPlayer();
                            game(board, text, player);
                        }
                    }
                    break;
                case 3:
                    text.printInstructions(); //prints instructions
                    break;
                case 4:
                    System.out.println("\nGood bye!"); //final message
                    break;
                default:
                    System.out.println("\nPlease enter a valid integer (1-4), try again.");
            }
        }while(menuChoice != 4);
    }

    /**
     * Switches turns
     * @param player
     * @return Current player
     * @author Zaeem Farhan
     */
    public static char switchPlayer(char player){
        char nextPlayer;

        if(player == 'R'){
            nextPlayer = 'Y';
        }else {
            nextPlayer = 'R';
        }
        return nextPlayer;
    }

    public static void game(Board board, TextUI text, char player){
        boolean flag;
        boolean saveFile = false;
        int columnNumber;

        do {
            do {
                flag = true;
                //gets column input from user
                columnNumber = text.playGame(board.toString(), player);

                //updates board and error checks if column input is valid
                if (columnNumber != 0) {
                    board.setColumnInput(columnNumber);
                    saveFile = true;

                    if (!board.setBoard(player)) {
                        text.columnFullMessage();
                        flag = false;
                    }

                //else if used for saving current game to file
                } else {
                    saveFile = board.saveGame(text.getFileName());
                    text.printSaveFileMsg(saveFile);
                }
            } while (!flag || !saveFile);

                //sets depth and switches player to next player
                if (columnNumber != 0) {
                board.setDepth();
                player = switchPlayer(player);
            }
        } while (board.getDepth() < 42 && !board.checkWinner() && columnNumber != 0);

        //displays final board and result
        if (columnNumber != 0) {
            text.printBoard(board.toString());

            if (board.checkWinner()) {
                text.printResult(switchPlayer(player), true);

            } else if (board.getDepth() == 42) {
                text.printResult(player, false);
            }
        }
    }
}