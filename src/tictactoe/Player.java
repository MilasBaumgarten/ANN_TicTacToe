package tictactoe;

abstract class Player {
	
	private int symbol;
	
	public Player(int symbol){
		this.symbol = symbol;
	}
	
	public abstract Position getInput();
	
	public int getSymbol(){
		return symbol;
	}
}
