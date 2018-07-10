package tictactoe;

public class Game {
	private static int fieldSizeX = 3;
	private static int fieldSizeY = 3;
	private static int rounds = 1;
	
	private static int[] scoreBoard = new int[rounds];
	private static Board board = new Board(fieldSizeX, fieldSizeY);
	
	private static boolean gameover = false;
	private static Player currentPlayer;
	
	// players
	private static Player p1 = new HumanPlayer(1);
	private static Player p2 = new HumanPlayer(2);
	
	public static void main(String[] args){
		// play x rounds
		for (int round = 0; round < rounds; round++){
			// game runs until a player wins
			while(!gameover){
				// switch player
				currentPlayer = (currentPlayer == p1)? p2 : p1;
				
				turn();
				
				// visualize game
				board.printBoard();
			}
			
			gameWon();
			
			// reset board after win/ draw
			board.clearBoard();
		}
	}
	
	/**
	 * Gets input from current player.
	 * Tries to execute input.
	 */
	public static void turnPlayer(){
		Position pos = p1.getInput();
		
		// check if field is already taken
		if (board.get(pos.x,pos.y) == 0){
			board.set(pos.x,pos.y, currentPlayer.getSymbol());
		} else{
			System.out.println(pos.x + ":" + pos.x + " = " + board.get(pos.x,pos.y));
			System.out.println("Cell already taken!");
			
			turnPlayer();
			}
		
		gameover = board.checkWinner(currentPlayer);
	}
	
	/**
	 * Prints win message.
	 * Additional configurations (e.g. score board) can be done here.
	 */
	private static void gameWon(){
		gameover = false;
		System.out.println("Player " + currentPlayer.getSymbol() + " won!");
	}
}
