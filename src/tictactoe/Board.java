package tictactoe;

import java.util.Arrays;

public class Board {
	private static int[][] board;
	
	// create board (standard field value = 0)
	public Board(int fieldSizeX, int fieldSizeY) {
		board = new int[fieldSizeX][fieldSizeY];
	}
	
	/**
	 * Check whether the current player won.
	 * @param p = current player
	 * @return true if the current player has won.
	 */
	public boolean checkWinner(Player p){
		boolean won = true;
		
		// check horizontal
		for (int x = 0; x < board.length; x++){
			for (int y = 0; y < board[0].length; y++){
				if (board[x][y] != p.getSymbol()){
					won = false;
					break;
				}
			}
			
			if (won){
				return true;
			}
		}
		
		// check vertical
		for (int y = 0; y < board[0].length; y++){
			won = true;
			for (int x = 0; x < board.length; x++){
				if (board[x][y] != p.getSymbol()){
					won = false;
					break;
				}
			}
			
			if (won){
				return true;
			}
		}
		
		// diagonal check (right top to left bottom)
		for (int y = 0; y < board[0].length; y++){
			won = true;
			if (board[board.length - y - 1][y] != p.getSymbol()){
				won = false;
				break;
			}
			
		}
		if (won){
			return true;
		}
		
		// diagonal check (left top to right bottom)
		for (int y = 0; y < board[0].length; y++){
			won = true;
			if (board[y][y] != p.getSymbol()){
				won = false;
				break;
			}
			
		}
		if (won){
			return true;
		}
		
		return false;
	}

	/**
	 * Displays board.
	 */
	public void printBoard(){
		for (int x = 0; x < board.length; x++){
			System.out.println(Arrays.toString(board[x]));
		}
	}
	
	/**
	 * Resets board to default configuration (each cell = 0).
	 */
	public void clearBoard(){
		board = new int[board.length][board[0].length];
	}
	
	/**
	 * Returns cell symbol/ value at given position.
	 * @return cell symbol
	 */
	public int get(int x, int y){
		return board[x][y];
	}
	
	/**
	 * Set player symbol at given position.
	 * @param symbol = player symbol
	 */
	public void set(int x, int y, int symbol){
		board[x][y] = symbol;
	}
}
