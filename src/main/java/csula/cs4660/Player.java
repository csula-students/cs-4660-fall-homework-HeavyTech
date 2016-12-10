import java.util.*;
import java.io.*;
import java.math.*;
import java.nio.file.NotDirectoryException;

public class Player {

	public static long startTime; //Keeps track of Tron Game 
	public static Node[][] nodeBoard = new Node[20][30]; // Fixed size
	public static int [][] board = new int[20][30];

	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;


	public static boolean playerOne = true;
	public static boolean playerTwo = true;
	public static boolean playerThree = true;
	public static boolean playerFour = true;
	
	public static Node firstPlayer = new Node();
	public static Node secondPlayer = new Node();
	public static Node thirdPlayer = new Node();
	public static Node fourthPlayer = new Node();

	public static Node position; 
	public int direction; 
	public int number; 
	public static BoardGame bBoard;
	public static int idPlayer = 0;

	public Player(Node position, int direction, BoardGame bBoard){
		Player.position = position;
		this.direction = direction;
		Player.bBoard = bBoard;
		

		Player.bBoard.setCell(Player.position, number++);

	}
	
	public Player(Node position,BoardGame bBoard,int idPlayer){
		Player.position = position;
		Player.bBoard = bBoard;
		Player.idPlayer = idPlayer;
		
	}
	
	public Player(){}

	public Node getPosition() {
		return position;
	}
	public void setPosition(Node position) {
		Player.position = position;
	
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
		Node toReturn = new Node(Player.position);

		Player.position = getNextCell();
		bBoard.setCell(Player.position,this.number);
		return toReturn;
	}

	public boolean isCollision(){
		Node next = getNextCell();

		if(Player.position.getX() <= 0 || Player.position.getX() >= bBoard.getWidth() -1 ){
			return true;
		}
		else if (Player.position.getY() <= 0 || Player.position.getY() >= bBoard.getHeight() -1){
			return true;
		}
		else if (bBoard.getCell(next) != 0){
			return true;
		}
		else{
			return false;
		}
	}

	public Node getNextCell(){


		Node toReturn = new Node(Player.position);

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

		BoardGame board  = new BoardGame(20,30);
		board.fillBoard(board);

		Scanner in = new Scanner(System.in);

		while(true){


			int N = in.nextInt();
			int P = in.nextInt();

			for (int i = 0; i < N; i++) {
				int X0 = in.nextInt(); // starting X coordinate of lightcycle (or -1)
				int Y0 = in.nextInt(); // starting Y coordinate of lightcycle (or -1)
				int X1 = in.nextInt(); // starting X coordinate of lightcycle (can be the same as X0 if you play before this player)
				int Y1 = in.nextInt(); // starting Y coordinate of lightcycle (can be the same as Y0 if you play before this player)


				if(i ==  0 && playerOne){
					firstPlayer.setX(X0);
					firstPlayer.setY(Y1);
					board.board[firstPlayer.getX()][firstPlayer.getY()] = 0;
					board.setCell(firstPlayer, 1);
					System.err.println("Player one Successfully added");
				}
				if(i == 1 && playerTwo){
					secondPlayer.setX(X0);
					secondPlayer.setY(Y0);
					board.board[secondPlayer.getX()][secondPlayer.getY()] =1;
					board.setCell(secondPlayer, 2);
					System.err.println("Player two Successfully added");
				}
				
				if(i == 2 && playerThree){
					thirdPlayer.setX(X0);
					thirdPlayer.setY(Y0);
					board.board[thirdPlayer.getX()][thirdPlayer.getY()] = 2;
					board.setCell(thirdPlayer, 3);
					System.err.println("Player third successfully added");
					
				}
				if(i ==3 && playerFour){
					fourthPlayer.setX(X0);
					fourthPlayer.setY(Y0);
					board.board[fourthPlayer.getX()][fourthPlayer.getY()] = 3;
					board.setCell(fourthPlayer, 4);
					System.err.println("Fourth player added");
				}
			

			}




		}




	}




	public static String floodFill(Node player, Node enemy){




		return null;



	}


	public static int miniMax(Node root, Integer depth, Integer alpha, Integer beta, Boolean max){
		if(depth == 0){
			return nodeBoard[root.getX()][root.getY()].getCost();
		}
		else{

			if(max){

				List<Node> children = new ArrayList<Node>();

				for(Node node : root.getNeighbors()){
					if( board[node.getX()][node.getY()]== -1 && depth ==2){
						children.add(node);
					}
				}

				int best = Integer.MIN_VALUE;
				if(children.isEmpty()){
					best = 1;
				}

				for(Node node : children){
					int currentDepth = depth -1 ;
					int nextValue = miniMax(nodeBoard[node.getX()][node.getY()],currentDepth,alpha,beta,false);
					best = Math.max(best, nextValue);
					alpha = Math.max(alpha, best);

					if(beta <= alpha){ //gets cut off 
						break;
					}

				}

				root.setCost(best);
				return best;

			}

			else{

				List<Node> children = new ArrayList<Node>();

				for(Node node : root.getNeighbors()){
					if( board[node.getX()][node.getY()]== -1){
						children.add(node);
					}
				}

				int best = Integer.MAX_VALUE;
				if(children.isEmpty()){
					best = 1;
				}

				for(Node node : children){
					int currentDepth = depth - 1;
					int nextVal = miniMax(nodeBoard[node.getX()][node.getY()], currentDepth,alpha, beta, true);
					best = Math.max(best, nextVal);
					alpha = Math.max(alpha, best);

					if(beta <= alpha){ // Gets cut off 
						break;
					}

				}

				root.setCost(best);
				return best;
			}

		}


	}

}

class Node {

	public int x;
	public int y;
	public int cost;
	public List<Node> neighbors;

	public Node(int x,int y){
		this.x = x;
		this.y = y;
		cost = 0;
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
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
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


	public void printBoard( ){

		for (int[] s: board){

			for(int i = 0; i < s.length; i++){
				System.out.print(s[i] + " ");
			}
			System.out.println();
		}
	}

	public void fillBoard(BoardGame board){

		for(int i = 0; i < board.width; i ++){
			for ( int j = 0; j < board.height; j++){
				board.board[i][j] = 0;
			}
		}


	}

}







