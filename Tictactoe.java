import java.util.Random;
import java.util.Scanner;

abstract class Player{
	String name;
	char mark;
	abstract void makeMove();
	
	boolean isVlaidMove(int row,int col){
		if(row>=0 && row<=2  && col>=0 && col<=2) {
			if(Game.board[row][col]==' ') {
				return true;
			}
		}
		return false;
	}
}



class Human extends Player{
	
	Human(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	
	void makeMove() {
		Scanner sc=new Scanner(System.in);
		int row,col;
		do {
			System.out.println("Enter the row and col");
			row=sc.nextInt();
		    col=sc.nextInt();
		}
		while(!isVlaidMove(row,col));
		
		Game.inputdata(row, col, mark);
		
	}
}


class AI extends Player{
	
	AI(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	
	void makeMove() {
		Scanner sc=new Scanner(System.in);
		int row,col;
		do {
			Random r=new Random();
			row=r.nextInt(3);
			col=r.nextInt(3);		}
		while(!isVlaidMove(row,col));
		
		Game.inputdata(row, col, mark);
		
	}
}


class Game{
	static char[][] board;

	public Game() {
		board=new char[3][3];
		initBoard();
	}

	void initBoard() {
		for(int i=0;i<board.length;i++) {
		for(int j=0;j<board[i].length;j++) {
			board[i][j]=' ';
		  }
		}
	 }
	
	static void display() {
		System.out.println("-------------");
		for(int i=0;i<board.length;i++) {
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++) {
				System.out.print(board[i][j] +" | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	
	static void inputdata(int row,int column,char mark) {
		if(row>=0 && row<=2 && column>=0 && column<=2) {
			board[row][column]=mark;
		}
		else
			System.out.println("Invalid position");
	}
	
	static boolean checkColWin() {
		for(int j=0;j<=2;j++) {
			if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j]) {
				return true;
			}
		}
		return false;
	}
	
	static boolean checkRowWin() {
		for(int i=0;i<=2;i++) {
			if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2]) {
				return true;
			}
		}
		return false;
	}
	
	static boolean checkDiagWin(){
		if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] ||
		   board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0]) {
			return true;
		}
		return false;
	}
	
	static boolean checkDraw(){
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(board[i][j]==' ') {
					return false;
				}
			}
		}
		return true;
	}
	
}

public class Tictactoe {
  public static void main(String[] args) {
	   Game g=new Game();
         Human h=new Human("reva",'X');
         Human h1=new Human("tharun",'O');
//         AI h1=new AI("AI",'O');
         
         Player cp;
         cp=h;
         while(true) {
        	 System.out.println(cp.name+" turn");
             cp.makeMove();
             Game.display();
             
             if(Game.checkColWin()|| Game.checkRowWin() || Game.checkDiagWin()) {
            	 System.out.println(cp.name+" has won");
            	 break;
             }
             else if(Game.checkDraw()) {
            	 System.out.println("Game is draw");
            	 break;
             }
             else {
            	 if(cp==h) {
            		 cp=h1;
            	 }
            	 else
            		 cp=h;
             }
         }  
   } 
}
