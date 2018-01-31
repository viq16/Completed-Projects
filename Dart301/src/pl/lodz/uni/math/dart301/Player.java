package pl.lodz.uni.math.dart301;

public class Player {
	private int score;
	private String name;
	
	public Player(String _name) {
		this.score=301;
		this.setName(_name);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score -= score;
	}
	
	public void setNewScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
