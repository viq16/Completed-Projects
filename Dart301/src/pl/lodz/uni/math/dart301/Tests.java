package pl.lodz.uni.math.dart301;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class Tests {
	
	
	private GameSettings gs = new GameSettings();
	
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void gameConfig() {
		Player player1=new Player("Radek");
		Player player2=new Player("Karol");
		assertEquals("Radek", player1.getName());
		Assert.assertEquals("Karol", player2.getName());
		Assert.assertEquals(301, player1.getScore());
		Assert.assertEquals(301, player2.getScore());
	}
	@Test
	public void fillingArray() {
		Assert.assertEquals(63,gs.dartValues.length);
		for(int i=0; i<63; i++)
		{
			Assert.assertEquals(true,gs.dartValues[i]>=0 && gs.dartValues[i]<=60);
		}
	}
	@Test
	public void countingPoint() {
		Player player1=new Player("Radek");
		gs.playerThrowingDartManipulated(player1, 30);
		gs.playerThrowingDartManipulated(player1, 20);
		gs.playerThrowingDartManipulated(player1, 60);
		Assert.assertEquals(191, player1.getScore());
	}
	@Test
	public void playerWon() {
		Player player1=new Player("Radek");
		gs.playerThrowingDartManipulated(player1, 301);
		Assert.assertEquals(true, gs.ifPlayerWin(player1));
	}
	@Test
	public void testGame() {
		boolean player1Win = false,player2Win=false;
		Player player1=new Player("Radek");
		Player player2=new Player("Karol");
		GameSettings  play = new GameSettings();
		
		while(true) {
			player1Win = play.playerPlayingLeg(player1);
			if (player1Win == false) {
			player2Win = play.playerPlayingLeg(player2);
			if(player2Win == true){
				break;
			}
			}else {
				break;
			}
		}
		if(player1Win == true) {
		Assert.assertEquals("Radek", play.nameOfWonPlayer(player1, player1Win));
		}else {
			Assert.assertEquals("Karol", play.nameOfWonPlayer(player2, player2Win));
		}
	}
}
