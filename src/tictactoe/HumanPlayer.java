package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {

	public HumanPlayer(int symbol) {
		super(symbol);
	}

	@Override
	public Position getInput(){
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
