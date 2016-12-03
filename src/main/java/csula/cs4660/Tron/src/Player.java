import java.util.*;
import java.io.*;
import java.math.*;

public class Player {

	public static long startTime; //Keeps track of Tron Game 
	public static int[][] nodeBoard = new int[30][20];



	/*
	 * Setting up the Player attributes 
	 */

	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;



	public Node position; //Hold the position of Node
	public int direction;  // Direction of the node
	public int number; //this is the player number
	public BoardGame board;
	public int idPlayer = 0;

	public Player(Node position, int direction, BoardGame board){
		this.position = position;
		this.direction = direction;
		this.board = board;
		idPlayer++;  //Every player will get an ID
		//Setting the player at a give positon 

		this.board.setCell(this.position, number);

	}

	public Node getPosition() {
		return position;
	}
	public void setPosition(Node position) {
		this.position = position;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {

		this.number = number;
	}


	//Moves the player, and return the previous present on 
	public Node move(){
		Node toReturn = new Node(this.position);

		this.position = getNextCell();
		board.setCell(this.position,this.number);
		return toReturn;
	}

	public boolean isCollision(){
		Node next = getNextCell();

		if(this.position.getX() <= 0 || this.position.getX() >= board.getWidth() -1 ){
			return true;
		}
		else if (this.position.getY() <= 0 || this.position.getY() >= board.getHeight() -1){
			return true;
		}
		else if (board.getCell(next) != 0){
			return true;
		}
		else{
			return false;
		}
}

public Node getNextCell(){


	Node toReturn = new Node(this.position);

	if(this.direction == UP){
		toReturn.setY(toReturn.getY() -1);
	}
	else if(this.direction == DOWN){
		toReturn.setY(toReturn.getY() + 1);
	}
	else if(this.direction == LEFT){
		toReturn.setX(toReturn.getX() -1);
	}
	else if(this.direction == RIGHT){
		toReturn.setX(toReturn.getX() + 1);
	}

	return toReturn;

}




public static void main(String[] args) {

	Scanner in = new Scanner(System.in);

	BoardGame board  = new BoardGame(20,30);


}

}

class Node {

	public int x;
	public int y;
	public List<Node> neighbors;

	public Node(int x,int y){
		this.x = x;
		this.y = y;
	}
	public Node(Node player){
		this.x = player.x;
		this.y = player.y;
	}
	public Node(){}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {

		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public List<Node> getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(List<Node> neighbors) {
		this.neighbors = neighbors;
	}
	public void printList(List<Node> neighbors){

		for(int i = 0; i < neighbors.size(); i ++){
			System.out.println(neighbors.get(i) + "\n");
		}
	}
	public String toString(){

		String tester = "";
		tester = "X : Position " + getX() + "\n" + 
				"Y : Position " + getY() + "\n";

		return tester;
	}

}



class BoardGame{

	private int width;
	private int height;
	int [][] board;
	int [][] nodeBoard;


	public BoardGame(int width , int height){//Initializing the Board Game 
		board = new int[width][height];
		this.width = width;
		this.height = height;

	}



	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	//This will  be used to initialize the player. 
	public void setCell(int x, int y, int numberOfPlayer){
		board[x][y] = numberOfPlayer; 

	}

	//This will set the position of the Player on the board game. 
	public void setCell(Node n, int numOfPlayer){
		this.setCell(n.getX(), n.getY(), numOfPlayer);
	}

	public int getCell(int x, int y){
		return board[x][y];
	}

	public int getCell(Node n){

		int position = this.getCell(n.getX(),n.getY()); //Returns the position of player4
		return position;

	}

//	public BoardGame clone(){
//
//		BoardGame copy = new BoardGame(this.getWidth(), this.getHeight());
//		for(int row = 0; row < board.length; row++){
//			for(int col = 0; col < board[0].length; col ++){
//
//			}
//		}
//
//		return copy;
//	}

	public void printBoard( ){

		for (int[] s: board){

			for(int i = 0; i < s.length; i++){
				System.out.print(s[i] + " ");
			}
			System.out.println();
		}
	}

}







