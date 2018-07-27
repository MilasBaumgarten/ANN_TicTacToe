package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {

	public HumanPlayer(Board board) {
		super(board);
	}
	
	/**
	 * Gets input from current player.
	 * Tries to execute input.
	 */
	@Override
	public boolean turn(){
		Position pos = getInput();
		
		// check if field is already taken
		if (board.get(pos.x,pos.y) == 0){
			board.set(pos.x,pos.y, symbol);
		} else{
			System.out.println((pos.x + 1) + ":" + (pos.x + 1) + " = " + board.get(pos.x,pos.y)); 
			System.out.println("Cell already taken!");
			
			turn();
		}
		
		return board.checkWinner(this);
	}

	private Position getInput(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Position pos = new Position(0, 0);
		
		System.out.print("X: ");
		try {
			pos.x = Integer.parseInt(br.readLine()) - 1;
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		System.out.print("Y: ");
		try {
			pos.y = Integer.parseInt(br.readLine()) - 1;
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		return pos;
	}
}
