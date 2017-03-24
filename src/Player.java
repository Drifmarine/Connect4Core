import java.awt.Color;

public class Player {
	private final String name;
	private static int universalID = 1;// This stores the total amount of players and determine the id for each player
	private final int uniqueID;
	private Color chessColor;
	
	
	// Constructors
	public Player(){
		this.name = "";
		this.chessColor = Color.BLACK;
		uniqueID = universalID;
		universalID++;
	}
	
	public Player(String name){
		this.name = name;
		this.chessColor = Color.BLACK;
		uniqueID = universalID;
		universalID++;
	}
	
	public Player(String name, Color chessColor){
		this.name = name;
		this.chessColor = chessColor;
		uniqueID = universalID;
		universalID++;
	}
	
	// A method may set Player's chess color
	public void setColor(Color chessColor){
		this.chessColor = chessColor;		
	}
	
	public int getID(){
		return this.uniqueID;
	}
	
	public String toString(){
		return this.name;
	}
}
