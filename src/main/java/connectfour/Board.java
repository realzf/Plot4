/**
 * Name: Zaeem Farhan
 * Assignment: CIS*2430 A2
 * Date: Nov 1 2021
 * Description: This is the board class responsible for the state of the connect4 board
 */
package connectfour;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;

public class Board{
    private char[][] board = new char[8][15];       //the connect4 board
    private int columnInput;                        //stores column input by user
    private int depth;                              //depth of the number of positions used
    private int p1;                                 //number of 1's in file
    private int p2;                                 //number of 2's in file

    /**
     * No arg constructor
     * @author Zaeem Farhan
     */
    public Board() {
        createBoard();
        columnInput = 0;
        depth = 0;
        p1 = 0;
        p2 = 0;
    }

    /**
     * Arg-constructor
     * @param c Column input
     * @param d Depth
     * @param player1 Amount of 1's
     * @param player2 Amount of 2's
     * @author Zaeem Farhan
     */
    public Board(int c, int d, int player1, int player2) {
        createBoard();
        columnInput = c;
        depth = d;
        p1 = player1;
        p2 = player2;
    }

    /**
     * This sets the columnInput chosen by user and converts it to necessary offset
     * @param input Column input by user
     * @author Zaeem Farhan
     */
    public void setColumnInput(int input){
        columnInput = input * 2 - 1;
    }

    /**
     * This method returns columnInput
     * @return Current column input
     * @author Zaeem Farhan
     */
    public int getColumnInput(){
        return columnInput;
    }

    /**
     * This method increments/updates the current depth of the board
     * @author Zaeem Farhan
     */
    public void setDepth(){
        depth++;
    }

    /**
     * Returns the current depth of the board
     * @return Current depth of the board
     * @author Zaeem Farhan
     */
    public int getDepth(){
        return depth;
    }

    /**
     * Returns number of rows of the board
     * @return Total number of rows
     * @author Zaeem Farhan
     */
    private int getNumRows(){
        return 6;
    }

    /**
     * Returns number of columns of the board
     * @return Total number of columns
     * @author Zaeem Farhan
     */
    private int getNumColumns(){
        return 7;
    }

    /**
     * Updates board with player token according to columnInput and returns boolean depending on if the board was
     * updated or not
     * @param player Current player token (either R or Y)
     * @return If board is set or not
     * @author Zaeem Farhan
     */
    public boolean setBoard(char player){
        boolean flag = false;

        if(board[5][getColumnInput()] == ' '){
            board[5][getColumnInput()] = player;
            flag = true;

        }else {
            for (int i = 5; i >= 0; i--) {
                if (board[i][getColumnInput()] == ' ') {
                    board[i][getColumnInput()] = player;
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * Returns if the number of 1's and 2's in the given file are valid as a char
     * @return Which colours turn it is or N if file format is not correct
     * @author Zaeem Farhan
     */
    public char getCurrentPlayer(){
        if(p1 == p2){
            return 'R';

        }else if(p1 == p2+1){
            return 'Y';

        }else{
            return 'N';
        }
    }

    /**
     * Creates the connect4 board layout
     * @author Zaeem Farhan
     */
    private void createBoard(){
        int count = 1;

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){

                if(i == board.length - 1){
                    if(j % 2 != 0) {
                        board[i][j] = (char) (count + '0');
                        count++;
                    }else{
                        board[i][j] = ' ';
                    }

                }else if(i == board.length - 2){
                    if(j % 2 != 0) {
                        board[i][j] = '-';
                    }else{
                        board[i][j] = ' ';
                    }

                }else{
                    if (j % 2 == 0) {
                        board[i][j] = '|';
                    } else {
                        board[i][j] = ' ';
                    }
                }
            }
        }
    }

    /**
     * Calls private methods to check if a winner is found
     * @return True if winner is found, false otherwise
     * @author Zaeem Farhan
     */
    public boolean checkWinner() {
        return horizontalWinCheck() || verticalWinCheck() || diagonalWinCheck();
    }

    /**
     * Check if a player has won horizontally
     * @return Returns true if winner is found otherwise false
     * @author Zaeem Farhan
     */
    private boolean horizontalWinCheck() {
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 1; j <= getNumColumns(); j = j + 2) {
                if (board[i][j] == board[i][j + 2] && board[i][j] == board[i][j + 4] && board[i][j] == board[i][j + 6]
                        && board[i][j] == 'R') {
                    return true;
                }
                if (board[i][j] == board[i][j + 2] && board[i][j] == board[i][j + 4] && board[i][j] == board[i][j + 6]
                        && board[i][j] == 'Y') {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if a player has won vertically
     * @return Returns true if winner is found otherwise false
     * @author Zaeem Farhan
     */
    private boolean verticalWinCheck() {
        for (int i = 0; i < getNumRows() - 3; i++) {
            for (int j = 1; j <= getNumColumns() + 6; j = j + 2) {
                if (board[i][j] == board[i + 1][j] && board[i][j] == board[i + 2][j] && board[i][j] == board[i + 3][j]
                        && board[i][j] == 'R') {
                    return true;
                }

                if (board[i][j] == board[i + 1][j] && board[i][j] == board[i + 2][j] && board[i][j] == board[i + 3][j]
                        && board[i][j] == 'Y') {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if a player has won diagonally
     * @return Returns true if winner is found otherwise false
     * @author Zaeem Farhan
     */
    private boolean diagonalWinCheck(){
        //ascending diagonal check
        for(int i = 3; i < getNumRows(); i++){
            for(int j = 1; j <= getNumColumns(); j = j + 2){
                if(board[i][j] == board[i-1][j+2] && board[i][j] == board[i-2][j+4] && board[i][j] 
                    == board[i-3][j+6] && board[i][j] == 'R') {
                    return true;
                }

                if(board[i][j] == board[i-1][j+2] && board[i][j] == board[i-2][j+4] && board[i][j] == board[i-3][j+6]
                        && board[i][j] == 'Y') {
                    return true;
                }
            }
        }

        //descending diagonal check
        for(int i = 0; i < getNumRows() - 3; i++) {
            for (int j = 1; j <= getNumColumns(); j = j + 2) {
                if(board[i][j] == board[i+1][j+2] && board[i][j] == board[i+2][j+4] && board[i][j] == board[i+3][j+6]
                        && board[i][j] == 'R') {
                    return true;
                }

                if(board[i][j] == board[i+1][j+2] && board[i][j] == board[i+2][j+4] && board[i][j] == board[i+3][j+6]
                        && board[i][j] == 'Y') {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method saved the board to a given file, returns false if there is an error saving, true otherwise
     * @param filename Filename inputter by user
     * @return Returns true if game is saved to file, false otherwise
     * @author Zaeem Farhan
     */
    public boolean saveGame(String filename){
        try {
            //create/open file to write to
            FileWriter writer = new FileWriter(filename);
            for(int i = 0; i < 6; i++){

                //writes contents of board array to file
                for(int j = 1; j <= 13; j = j + 2){
                    if (board[i][j] == 'Y') {
                        writer.write("2,");
                    } else if(board[i][j] == 'R'){
                        writer.write("1,");
                    }else{
                        writer.write("0,");
                    }
                }
                writer.write("\n"); //create new line in file
            }
            writer.close();

        //error checking
        }catch(IOException e){
            return false;
        }
        return true;
    }

    /**
     * This method loads a game from file
     * @param filename File name inputted by user
     * @return Returns true if file was loaded correctly, false otherwise
     * @autoher Zaeem Farhan
     */
    public boolean loadGame(String filename){
        String line = "";
        String[] temp;
        int row = -1;
        int column = 1;
        try{
            File file = new File(filename);
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            while((line = br.readLine()) != null) {
                temp = line.split(","); //tokenize
                row++;
                for (int i = 0; i < temp.length;i++){ //update board array with file contents
                    if(temp[i].equals("1")) {
                        board[row][column] = 'R';
                        column += 2;
                        setDepth();
                        p1++;
                    }else if(temp[i].equals("2")) {
                        board[row][column] = 'Y';
                        column += 2;
                        setDepth();
                        p2++;
                    }else if(temp[i].equals("0")) {
                        board[row][column] = ' ';
                        column += 2;
                    }else{
                        return false;
                    }
                }
                if(column != 15){ //error trap in case file contains too many columns
                    return false;
                }
                column = 1;
            }
            br.close();
            read.close();
            if(row != 5){
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        if(getCurrentPlayer() == 'N' || !checkTokenPlacement()){
            return false;
        }
        return true;
    }

    /**
     * Checks if board layout in file has full does not have any empty positions under a position which is a token,
     * @return Returns true if file format is correct, false otherwise
     * @uthor Zaeem Farhan
     */
    private boolean checkTokenPlacement(){
        //Runs for each row in board
        for(int i = 0; i < 6; i++){
            //runs for each column in board
            for(int j = 1; j <= 13; j = j + 2){
                if(board[i][j] == 'R' || board[i][j] == 'Y'){
                    for(int index = i; index < 6; index++){
                        if(board[index][j] != 'R' && board[index][j] != 'Y') {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * toString method
     * @return String builder which contains the current board layout
     * @author Zaeem Farhan
     */
    @Override
    public String toString(){
        StringBuilder toReturn = new StringBuilder();

        for (char[] chars : board) {
            for (char aChar : chars) {
                toReturn.append(aChar);
            }
            toReturn.append("\n");
        }
        return toReturn.toString();
    }
}