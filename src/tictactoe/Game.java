package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Game {
	private static int fieldSizeX = 3;
	private static int fieldSizeY = 3;
	private static int rounds = 1;
	
	private static int[] score;
	private static int[][] field;
	
	private static boolean gameover = false;
	private static int currentPlayer = 1;
	
	public static void main(String[] args){
		// setup
		field = new int[fieldSizeX][fieldSizeY];	// create board (standard field value = 0)
		score = new int[rounds];
		
		
		for (int i = 0; i < rounds; i++){
			while(!gameover){
				Position pos = getInput();
				turn(pos.x, pos.y);
				checkWinner();
				printBoard();
			}
			clearBoard();
		}
	}
	
	private static Position getInput(){
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
	
	private static void clearBoard(){
		field = new int[fieldSizeX][fieldSizeY];
	}
	
	private static void checkWinner(){
		boolean won = true;
		
		int player;
		
		// temp. switch players (because of player switch after turn)
		if (currentPlayer == 1){
			player = 2;
		} else{
			player = 1;
		}
		
		// check horizontal
		for (int x = 0; x < fieldSizeX; x++){
			for (int y = 0; y < fieldSizeY; y++){
				if (field[x][y] != player){
					won = false;
					break;
				}
				
				System.out.print("field[" + x + "][" + y + "]: ");
				System.out.println(field[x][y] + " != " + player + " | " + won);
			}
			
			if (won){
				gameover = true;
				return;
			}
		}
		
		// check vertical
		for (int y = 0; y < fieldSizeY; y++){
			won = true;
			for (int x = 0; x < fieldSizeX; x++){
				if (field[x][y] != player){
					won = false;
					break;
				}
				
				System.out.print("field[" + x + "][" + y + "]: ");
				System.out.println(field[x][y] + " != " + player + " | " + won);
			}
			
			if (won){
				gameover = true;
				return;
			}
		}
		
		// diagonal check
		//
		//
		//
	}
	
	public static void turn(int placementX, int placementY){
		// check if field is already taken
		if (field[placementX][placementY] == 0){
			field[placementX][placementY] = currentPlayer;
			
			if (currentPlayer == 1){
				currentPlayer = 2;
			} else{
				currentPlayer = 1;
			}
			
		} else{
			System.out.println(placementX + ":" + placementY + " = " + field[placementX][placementY]);
			System.out.println("Platz ist bereits belegt!");
			
			Position pos = getInput();
			turn(pos.x,pos.y);
		}
	}
	
	private static void printBoard(){
		for (int x = 0; x < fieldSizeX; x++){
			System.out.println(Arrays.toString(field[x]));
		}
	}
}
