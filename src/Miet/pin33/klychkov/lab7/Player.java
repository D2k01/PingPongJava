package Miet.pin33.klychkov.lab7;

public class Player {
	
	String name;
	int score;
	int winSeths;
	int losingSeths;
	int winPoints;
	int losingPoints;
	int winGame;
	int Games;
	
	Player(int score, String name){
		
		this.name = name;
		this.score = score;
		this.winSeths = 0;
		this.losingSeths = 0;
		this.winPoints = 0;
		this.losingPoints = 0;
		this.winGame = 0;
		this.Games = 0;
		
	}
	Player(String name, int wS, int lS, int wP, int lP, int wG, int g){
		
		this.name = name;		
		this.winSeths = wS;
		this.losingSeths = lS;
		this.winPoints = wP;
		this.losingPoints = lP;
		this.winGame = wG;
		this.Games = g;
		
	}

}
