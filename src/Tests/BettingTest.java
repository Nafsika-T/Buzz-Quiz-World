package Tests;

import logic.Betting;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

public class BettingTest {

    private final Betting betting = new Betting();

    @Test
    public void getBettingPoints() {
        betting.setBettingPoints(250);
        int betting1 = betting.getBettingPoints();
        assertEquals(betting1,250);
    }

    @Test
    public void getBetGiven1() {
        betting.setBetGiven(true);
        boolean giveBetting = betting.getBetGiven();
        assertTrue(giveBetting);
    }

    @Test
    public void getBetGiven2() {
        betting.setBetGiven(false);
        boolean giveBetting = betting.getBetGiven();
        assertFalse(giveBetting);
    }
}