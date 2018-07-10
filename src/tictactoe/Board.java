package tictactoe;

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
		for (int y = 0; y < getSizeY(); y++){
			won = true;
			for (int x = 0; x < getSizeX(); x++){
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
		for (int x = 0; x < getSizeX(); x++){
			won = true;
			for (int y = 0; y < getSizeY(); y++){
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
		for (int y = 0; y < getSizeY(); y++){
			won = true;
			if (board[getSizeX() - y - 1][y] != p.getSymbol()){
				won = false;
				break;
			}
			
		}
		if (won){
			return true;
		}
		
		// diagonal check (left top to right bottom)
		for (int y = 0; y < getSizeY(); y++){
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
		for (int y = 0; y < getSizeY(); y ++){
			System.out.print("[");
			for (int x = 0; x < getSizeX() - 1; x ++){
				System.out.print(board[x][y] + ", ");
			}
			System.out.println(board[getSizeX() - 1][y] + "]");
		}
	}
	
	/**
	 * Resets board to default configuration (each cell = 0).
	 */
	public void clearBoard(){
		board = new int[getSizeX()][getSizeY()];
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
	
	public int getSizeX(){
		return board[0].length;
	}
	
	public int getSizeY(){
		return board.length;
	}
	
	public double[] getValues(){
		double[] values = new double[getSizeX() * getSizeY()];
		for (int x = 0; x < getSizeX(); x++){
			for (int y = 0; y < getSizeY(); y ++){
				values[x + (getSizeX() * y)] = get(x,y);
			}
		}
		
		return values;
	}
}
