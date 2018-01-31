package pl.lodz.uni.math.dart301;

import java.util.Random;

public class GameSettings {
	public int [] dartValues=new int[63] ;
	boolean ifWon=false,playerWon=false;
	int scoreBeforeLeg;
	
	public GameSettings() {
		fillDartValues();
	};
	
	private void fillDartValues() {
		for(int i=0;i<=20;i++) {
			dartValues[i]=i;
		}
		for(int i=1;i<=20;i++) {
			dartValues[i+20]=i*2;
		}
		for(int i=1;i<=20;i++) {
			dartValues[i+40]=i*3;
		}
		dartValues[61]=25;
		dartValues[62]=50;
	}
	
	public void playerThrowingDart(Player p) {
		int tabPlace = 0,score = 0;
		Random r=new Random();
		tabPlace= r.nextInt(63);
		score = dartValues[tabPlace];
		p.setScore(score);
	}
	
	public void playerThrowingDartManipulated(Player p,int score) {
		p.setScore(score);
	}
	
	public boolean ifPlayerWin(Player p) {
		if(p.getScore()==0) {
		return true;
		}else {
			return false;
		}
	}
	
	public boolean playerPlayingLeg(Player p) {
		int throwCounts = 1;
		scoreBeforeLeg = p.getScore();
		System.out.println("Player " + p.getName() + " start score: "  + p.getScore());
		while(throwCounts < 4) {
			playerThrowingDart(p);
			ifWon = ifPlayerWin(p);
			System.out.println("Player " + p.getName() + " throw nr: "+ throwCounts +" score: "  + p.getScore());
			if(ifWon == true) {
				break;
			}
			throwCounts++;
		}
		if(scoreUnderZero(p,scoreBeforeLeg)) {
		playerWon = playerWonGame(ifWon,p);
		}
		return playerWon;
	}
	
	private boolean playerWonGame(boolean won,Player p) {
		if (won == true) {
			System.out.println("Player " + p.getName() + " won !!");
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean scoreUnderZero(Player p,int scoreBeforeLeg) {
		if(p.getScore() < 0) {
			p.setNewScore(scoreBeforeLeg);
			return false;
		}else {
			return true;
		}
	}
	
	public String nameOfWonPlayer(Player p,boolean player) {
		String name ="";
		if (player == true) {
			name = p.getName();
		}
		return name;
	}

}
