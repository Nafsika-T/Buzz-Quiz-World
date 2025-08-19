package Tests;

import logic.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    private final Player player=new Player("George");

    @Test
    public void getName() {
        String name= player.getName();
        assertEquals(name,"George");
    }

    @Test
    public void getScore() {
        player.addPoints(1000);
        player.removePoints(250);
        assertEquals(player.getScore(),750);
    }

    @Test
    public void getAnswer() {
        player.setAnswer("απάντηση");
        assertEquals(player.getAnswer(),"απάντηση");
    }

    @Test
    public void getCorrectAnswers() {
        player.setCorrectAnswers(0);
        player.addCorrectAnswer();
        player.addCorrectAnswer();
        player.addCorrectAnswer();
        assertEquals(player.getCorrectAnswers(),3);
    }

    @Test
    public void addPoints() {
        player.addPoints(1000);
        player.addPoints(250);
        assertEquals(player.getScore(),1250);
    }

    @Test
    public void removePoints() {
        player.removePoints(300);
        player.removePoints(250);
        assertEquals(player.getScore(),-550);
    }
}