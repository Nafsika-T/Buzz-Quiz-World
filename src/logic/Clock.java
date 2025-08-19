package logic;

/**
 * Αυτή η κλάση αναπαριστά ένα τυπό γύρου "Σταμάτησε το χρονόμετρο" για κάθε παίχτη.
 */
public class Clock {
    private int seconds;

    /**
     * Κατσκευαστής. Θέτει τα δευτερόλεπτα ίσα με το 0.
     */
    public Clock(){
        seconds = 0;
    }

    /**
     * @return τα δευτερόλεπτα που απέμειναν από τη στιγμή που θα απαντήθει η ερώτηση επί 0.2
     */
    public int getRoundPoints() {
        return (int) (seconds * 0.2);
    }

    /**
     * @param seconds τα δευτερόλεπτα που απέμειναν από τη στιγμή που θα απαντήθει η ερώτηση
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * @return τα δευτερόλεπτα που απέμειναν από τη στιγμή που θα απαντηθεί η ερώτηση
     */
    public int getSeconds() { return seconds; }
}
