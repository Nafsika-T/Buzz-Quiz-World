package Tests;

import logic.Clock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClockTest {

    private final Clock clock = new Clock();

    @Test
    public void getRoundPoints() {
        clock.setSeconds(1400);
        int points = clock.getRoundPoints();
        assertEquals(points,280);
    }

    @Test
    public void getSeconds() {
        clock.setSeconds(800);
        int points = clock.getSeconds();
        assertEquals(points,800);
    }
}