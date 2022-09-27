package Miet.pin33.klychkov.lab7;

public class Main {

	public static void main(String[] args) {
		
		startGame();		
		System.out.println(Thread.activeCount() + "\n");		
	}
	
	public static void startGame() {
		
		GamePanel panel = new GamePanel();
		/*
		panel.newBall();
		panel.newPaddles();
		panel.newScore();
		panel.newMenu();
		GameFrame frame = new GameFrame(panel);
		
		frame.dispose();*/
		new InitPlayerFrame(panel);
		
	}

}
