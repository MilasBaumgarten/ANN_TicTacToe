package tictactoe;

import java.util.Random;

import ann.Connection;
import ann.math.Linear;
import ann.math.Sigmoid;


public class Game {
	private static int fieldSizeX = 3;
	private static int fieldSizeY = 3;
	
	private static Board board = new Board(fieldSizeX, fieldSizeY);
	
	private static boolean gameover = false;
	private static Player currentPlayer;
	
	private static int brainsPerGeneration = 25;
	private static int maxGeneration = 200;
	private static double maxMutation = 0.4;
	private static double minMutation = 0.1;
	
	private static final double SCOREWIN = 1;
	private static final double SCOREDRAW = 0.5;
	private static final double SCORELOSS = -2;
	
	public static void main(String[] args){
		// setup AI players
		Player[] players = new ANNPlayer[brainsPerGeneration];
		
		for (int i = 0; i < brainsPerGeneration; i ++) {
			players[i] = new ANNPlayer(board, fieldSizeX, fieldSizeY, 9, 1, new Sigmoid(), new Linear());
		}
		
		playRound(new HumanPlayer(board), players[0], true);
		
		// let x generations compete against eachother
		for (int generation = 0; generation < maxGeneration; generation++){
			System.out.println("------------------------------------------------------------");
			System.out.println("Current Generation: " + generation);
			
			// let every brain play against every other brain
			for (int firstPlayerNum = 0; firstPlayerNum < brainsPerGeneration - 1; firstPlayerNum++){
				for (int secondPlayerNum = firstPlayerNum + 1; secondPlayerNum < brainsPerGeneration; secondPlayerNum++){
					
					playRound(players[firstPlayerNum], players[secondPlayerNum], false);
				}
			}
			
			printPlayerScore(players);
			
			// find champions
			Player[] bestPlayers = findBestPlayers(players);
			
			// breed next generation
			players[0] = new ANNPlayer((ANNPlayer) bestPlayers[0]);
			players[1] = new ANNPlayer((ANNPlayer) bestPlayers[1]);
			
			Random rand = new Random();
			
			// generate next brains aka players by mutating the champions
			for (int i = 2; i < brainsPerGeneration; i ++){
				if (i % 2 == 0){
					players[i] = new ANNPlayer((ANNPlayer) bestPlayers[0]);
				} else{
					players[i] = new ANNPlayer((ANNPlayer) bestPlayers[1]);
				}
				
				
				
				for (Connection con : ((ANNPlayer) players[i]).getConnections()){
					// change weights
					//		huge changes during early generations
					//		small changes during later generations
					con.weight += rand.nextDouble() * Math.max(((maxGeneration - generation) / (double)maxGeneration) * maxMutation, minMutation);
				}
			}
		}
		
		//TODO let human play against ai after x sessions (to learn)
		
		// play a round against the learned ai
		playRound(findBestPlayers(players)[0], new HumanPlayer(board), true);
		playRound(new HumanPlayer(board), findBestPlayers(players)[0], true);
	}
	
	private static void playRound(Player p1, Player p2, boolean visualizeBoard){
		p1.symbol = 1;
		p2.symbol = 2;
		while(!gameover){
			// switch player
			currentPlayer = (currentPlayer == p1)? p2 : p1;
				
			if (currentPlayer == p1){
				gameover = p1.turn();
			} else{
				gameover = p2.turn();
			}
			
			// visualize game
			if (visualizeBoard){
				board.printBoard();
				System.out.println("");
			}
			
			// check for a draw
			if (!gameover){
				boolean isDraw = true;
				
				for (int x = 0; x < board.getSizeX() && isDraw; x++){
					for (int y = 0; y < board.getSizeY() && isDraw; y++){
						if (board.get(x, y) == 0){
							isDraw = false;
						}
					}
				}
				
				// give points to players
				if (isDraw){
					p1.score += SCOREDRAW;
					p2.score += SCOREDRAW;
					
					if (!visualizeBoard){
						board.printBoard();
					}
					
					System.out.println("Draw!");
					
					board.clearBoard();
					return;
				}
			}
		}
		
		if (!visualizeBoard){
			board.printBoard();
		}
		
		gameWon(p1, p2);
		
		// reset board after win/ draw
		board.clearBoard();
	}
	
	/**
	 * Prints win message.
	 * Additional configurations (e.g. score board) can be done here.
	 */
	private static void gameWon(Player p1, Player p2){
		gameover = false;
		
		// give points to players (current player is always winner
		p1.score += (p1 == currentPlayer)? SCOREWIN : SCORELOSS;
		p2.score += (p2 == currentPlayer)? SCOREWIN : SCORELOSS;
		
		
		System.out.println("Player " + currentPlayer.getSymbol() + " won!");
	}

	private static void printPlayerScore(Player[] players){
		for (int i = 0; i < brainsPerGeneration; i++) {
			System.out.println("Player " + i + " Score: " + players[i].score);
		}
	}
	
	private static Player[] findBestPlayers(Player[] players){
		Player first = players[0];
		Player second = players[0];
		
		for (int i = 1; i < players.length; i++) {
			if (players[i].score >= first.score){
				second = first;
				first = players[i];
			}
		}
		
		// cleanup
		first.score = 0;
		second.score = 0;
		
		return new Player[] {first, second};
	}
}
