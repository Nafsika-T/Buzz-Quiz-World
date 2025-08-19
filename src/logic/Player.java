package logic;

/**
 * Αυτή η κλάση αναπαριστά έναν παίχτη του παιχνιδιού με το όνομά του και το σκορ που πέτυχε.
 */
public class Player {

    private final String name;
    private String answer;
    private int score;
    private int correctAnswers;
    private final Betting betting;
    private final Clock clock;

    /**
     * Κατασκευαστής
     * @param name το όνομα του παίχτη
     */
    public Player(String name) {
        this.name = name;
        score = 0;
        correctAnswers = 0;
        betting = new Betting();
        clock = new Clock();
    }

    /**
     * @return το αντικείμενο της κλάσης Clock του παίχτη
     */
    public Clock getClock(){
        return clock;
    }

    /**
     * @return το αντικείμενο της κλάσης Betting του παίχτη
     */
    public Betting getBetting() {
        return betting;
    }

    /**
     * @return το όνομα του παίχτη
     */
    public String getName() {
        return name;
    }

    /**
     * Αυτή η μέθοδος προσθέτει στο τελικό σκορ του παίχτη τους πόντους που πέτυχε μετά από κάθε γύρο του παιχνιδιού.
     * @param points οι πόντοι που πέτυχε ο παίχτης μετά από εναν γύρο
     */
    public void addPoints(int points) {
        score += points;
    }

    /**
     * Αυτή η μέθοδος αφαιρεί πόντους από το τελικό σκορ του παίχτη.
     * @param points οι πόντοι που αφαιρούνται
     */
    public void removePoints(int points) {
        score -= points;
    }

    /**
     * @return το τελικό σκορ του παίχτη
     */
    public int getScore() { return score; }

    /**
     * @param answer η απάντηση του παίχτη
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return την απάντηση του παίχτη
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Αυτή η μέθοδος αυξάνει το πλήθος των σωστών απαντήσεων κατά 1 , όταν ο παίχτης απαντάει σωστά.
     */
    public void addCorrectAnswer(){ correctAnswers++; }

    /**
     * @return το πλήθος των σωστών απαντήσεων
     */
    public int getCorrectAnswers(){ return correctAnswers; }

    /**
     * @param correctAnswers το πλήθος των σωστών απαντήσεων
     */
    public void setCorrectAnswers(int correctAnswers){ this.correctAnswers = correctAnswers; }
}