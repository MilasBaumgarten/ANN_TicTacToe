package tictactoe;

import java.util.Arrays;

public class Game {
	private static int fieldSizeX = 3;
	private static int fieldSizeY = 3;
	private static int rounds = 1;
	
	private static int[] scoreBoard;
	private static int[][] field;
	
	private static boolean gameover = false;
	private static Player currentPlayer;
	
	// players
	private static Player p1 = new HumanPlayer(1);
	private static Player p2 = new HumanPlayer(2);
	
	public static void main(String[] args){
		// setup
		field = new int[fieldSizeX][fieldSizeY];	// create board (standard field value = 0)
		scoreBoard = new int[rounds];
		
		
		// play x rounds
		for (int i = 0; i < rounds; i++){
			// game runs until a player wins
			while(!gameover){
				// switch player
				currentPlayer = (currentPlayer == p1)? p2 : p1;
				
				turn();
				
				// visualize game
				printBoard();
			}
			
			gameWon();
			
			// reset board after win/ draw
			clearBoard();
		}
	}
	
	/**
	 * Gets input form current player.
	 * Tries to execute input.
	 */
	public static void turn(){
		Position pos = p1.getInput();
		
		// check if field is already taken
		if (field[pos.x][pos.y] == 0){
			field[pos.x][pos.y] = currentPlayer.getSymbol();
			
		} else{
			System.out.println(pos.x + ":" + pos.x + " = " + field[pos.x][pos.y]);
			System.out.println("Cell already taken!");
			
			turn();
		}
		
		checkWinner();
	}
	
	/**
	 * Check whether the current player won.
	 */
	private static void checkWinner(){
		boolean won = true;
		
		// check horizontal
		for (int x = 0; x < fieldSizeX; x++){
			for (int y = 0; y < fieldSizeY; y++){
				if (field[x][y] != currentPlayer.getSymbol()){
					won = false;
					break;
				}
			}
			
			if (won){
				gameover = true;
				return;
			}
		}
		
		// check vertical
		for (int y = 0; y < fieldSizeY; y++){
			won = true;
			for (int x = 0; x < fieldSizeX; x++){
				if (field[x][y] != currentPlayer.getSymbol()){
					won = false;
					break;
				}
			}
			
			if (won){
				gameover = true;
				return;
			}
		}
		
		// diagonal check (right top to left bottom)
		for (int y = 0; y < fieldSizeY; y++){
			won = true;
			if (field[field.length - y - 1][y] != currentPlayer.getSymbol()){
				won = false;
				break;
			}
			
		}
		if (won){
			gameover = true;
			return;
		}
		
		// diagonal check (left top to right bottom)
				for (int y = 0; y < fieldSizeY; y++){
					won = true;
					if (field[y][y] != currentPlayer.getSymbol()){
						won = false;
						break;
					}
					
				}
				if (won){
					gameover = true;
					return;
				}
	}
	
	/**
	 * Prints win message.
	 * Additional configurations (e.g. score board) can be done here.
	 */
	private static void gameWon(){
		gameover = false;
		System.out.println("Player " + currentPlayer.getSymbol() + " won!");
	}
	
	/**
	 * Displays board.
	 */
	private static void printBoard(){
		for (int x = 0; x < fieldSizeX; x++){
			System.out.println(Arrays.toString(field[x]));
		}
	}
	
	/**
	 * Resets board to default configuration (each cell = 0).
	 */
	private static void clearBoard(){
		field = new int[fieldSizeX][fieldSizeY];
	}
}
