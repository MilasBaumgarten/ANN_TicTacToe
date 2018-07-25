package tictactoe;

abstract class Player {
	
	protected int symbol;
	protected Board board;
	
	public double score;
	
	public Player(Board board){
		this.board = board;
	}
	
	//public abstract Position getInput();
	
	public abstract boolean turn();
	
	public int getSymbol(){
		return symbol;
	}
}
