package logic;

public class Main {

    /**
     * H main δημιουργεί ένα αντικείμενο της κλάσης GameGUI και ξεκινάει ένα καινούργιο παιχνίδι.
     * @param args array of command-line arguments
     */
    public static void main(String[] args) {
        GameGUI g = new GameGUI();
        g.newGame();
    }
}