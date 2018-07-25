package tictactoe;

import java.util.ArrayList;

import ann.Brain;
import ann.Connection;
import ann.math.IActivationFunction;

public class ANNPlayer extends Player {
	private Brain ai;

	public ANNPlayer(Board board, int fieldSizeX, int fieldSizeY, int hiddenNeuronsPerLayer, int hiddenLayers, IActivationFunction hiddenActivation, IActivationFunction outputActivation) {
		super(board);
		ai = new Brain(fieldSizeX * fieldSizeY, hiddenNeuronsPerLayer, fieldSizeX * fieldSizeY, hiddenLayers, hiddenActivation, outputActivation);
	}
	
	public ANNPlayer(ANNPlayer player) {
		super(player.board);
		ai = new Brain(player);
	}
	
	/**
	 * Gets input from neuronal net.
	 */
	@Override
	public boolean turn(){
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
		
		board.set(pos % board.getSizeX(), (int) Math.floor(pos / board.getSizeX()), symbol);
		
		return board.checkWinner(this);
	}
	
	public Brain getBrain(){
		return ai;
	}
	
	public ArrayList<Connection> getConnections(){
		return ai.getConnections();
	}
}
