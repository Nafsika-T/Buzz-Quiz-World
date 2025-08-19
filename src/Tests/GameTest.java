package Tests;

import logic.Game;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

    private final Game game = new Game();

    @Test
    public void getQuestionsPerRounds() {
        int questionsPerRound = game.getQuestionsPerRounds();
        assertEquals(questionsPerRound,2);
    }

    @Test
    public void getMaxPlayer() {
        int maxPlayer = game.getMaxPlayer();
        assertEquals(maxPlayer,2);
    }

    @Test
    public void getRounds() {
        int rounds = game.getRounds();
        assertEquals(rounds,3);
    }

    @Test
    public void getTotalPlayers() {
        game.setTotalPlayers(1);
        int totalPlayers = game.getTotalPlayers();
        assertEquals(totalPlayers,1);
    }

    @Test
    public void getBettingPoints() {
        ArrayList<String> names = new ArrayList<>();
        names.add("vivi");
        names.add("nansi");
        game.saveNames(names);
        game.setBettingPoints(0,750);
        int betting = game.getBettingPoints(0);
        assertEquals(betting,750);
    }

    @Test
    public void getRoundPoints() {
        ArrayList<String> names=new ArrayList<>();
        names.add("vivi");
        names.add("nansi");
        game.saveNames(names);
        game.setSeconds(0,1200);
        int points = game.getRoundPoints(0);
        assertEquals(points,240);
    }

    @Test
    public void getName() {
        ArrayList<String> names = new ArrayList<>();
        names.add("vivi");
        names.add("nansi");
        game.saveNames(names);
        String name = game.getPlayers().get(1).getName();
        assertEquals(name,"nansi");
    }

    @Test
    public void getGiveBetting1() {
        ArrayList<String> names = new ArrayList<>();
        names.add("vivi");
        names.add("nansi");
        game.saveNames(names);
        game.setBetGiven(1,true);
        boolean betting = game.getBetGiven(1);
        assertTrue(betting);
    }

    @Test
    public void getGiveBetting() {
        ArrayList<String> names = new ArrayList<>();
        names.add("vivi");
        names.add("nansi");
        game.saveNames(names);
        game.setBetGiven(0,false);
        boolean betting = game.getBetGiven(0);
        assertFalse(betting);
    }

    @Test
    public void getAnswerPlayer() {
        ArrayList<String> names = new ArrayList<>();
        names.add("vivi");
        names.add("nansi");
        game.saveNames(names);
        game.setPlayersAnswer(0,"A");
        String answer = game.getPlayersAnswer(0);
        assertEquals(answer,"A");
    }
}