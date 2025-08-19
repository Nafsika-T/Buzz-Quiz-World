package logic;

/**
 * Αυτή η κλάση αναπαριστά ένα τυπό γύρου "Ποντάρισμα" για κάθε παίχτη.
 */
public class Betting {
    private int bettingPoints;
    private boolean betGiven;

    /**
     * Κατασκευαστής. Θέτει τους πόντους πονταρίσματος ίσους με το 0.
     */
    public Betting() { bettingPoints = 0; }

    /**
     * @param bettingPoints οι πόντοι πονταρίσματος
     */
    public void setBettingPoints(int bettingPoints){
        this.bettingPoints = bettingPoints;
    }

    /**
     * @return τους πόντους πονταρίσματος
     */
    public int getBettingPoints(){
        return bettingPoints;
    }

    /**
     * @param betGiven αν ο παίχτης πόνταρε ή όχι
     */
    public void setBetGiven(boolean betGiven){
        this.betGiven = betGiven;
    }

    /**
     * @return αν ο παίχτης πόνταρε
     */
    public boolean getBetGiven(){
        return betGiven;
    }

}