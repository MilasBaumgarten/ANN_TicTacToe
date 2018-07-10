package tictactoe;

import ann.Brain;
import ann.math.Sigmoid;


/* TODO
 * - Unentschieden checken
 * - teste ob Neuron calculated Probleme macht
 */
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
		Brain ai = new Brain(fieldSizeX * fieldSizeY, 3, fieldSizeX * fieldSizeY, 3, 0, new Sigmoid(), new Sigmoid());
		
		// play x rounds
		for (int round = 0; round < rounds; round++){
			// game runs until a player wins
			while(!gameover){
				// switch player
				currentPlayer = (currentPlayer == p1)? p2 : p1;
				
				if (currentPlayer == p1){
					turnPlayer();
				} else{
					turnAI(ai);
				}
				
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
	 * Gets input from neuronal net.
	 */
	public static void turnAI(Brain ai){
		ai.setValues(board.getValues());
		
		ai.transmit();
		
		double[] values = ai.getOutput(board);
		
		// find highest value
		int pos = 0;
		double max = values[0];
		for (int i = 1; i < values.length; i++){
			if (max < values[i]){
				max = values[i];
				pos = i;
			}
		}
		
		board.set(pos % fieldSizeX, (int) Math.floor(pos / fieldSizeX), currentPlayer.getSymbol());
		
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
