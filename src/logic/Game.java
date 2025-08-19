package logic;

import java.util.ArrayList;
import java.util.Random;

/** Η κλάση Game αναπαριστά ένα παιχνίδι. Έχει τα εξής χαρακτηριστικά: το πλήθος των γύρων, το πλήθος των ερωτήσεων ανά γύρο,
 * το πλήθος των παιχτών, το μέγιστο πλήθος των παιχτών που μπορεί να υπάρξει και δύο ArrayLists για την αποθήκευση των
 * παιχτών και του κάθε τύπου γύρου.
 */
public class Game {
    private final int rounds;
    private final int questionsPerRounds;
    private int totalPlayers;
    private final int maxPlayers;
    private final ArrayList<String> roundTypes;
    private final ArrayList<Player> players;

    /**
     * Κατασκευαστής
     */
    public Game(){
        roundTypes = new ArrayList<>();
        roundTypes.add("Ποντάρισμα");
        roundTypes.add("Σταμάτησε το χρονόμετρο");
        roundTypes.add("Σωστή Απάντηση");
        roundTypes.add("Θερμόμετρο");
        roundTypes.add("Γρήγορη απάντηση");
        players = new ArrayList<>();
        maxPlayers = 2;
        rounds = 3;
        questionsPerRounds = 2;
    }

    /**
     * @return το πλήθος των ερωτήσεων ανά γύρο
     */
    public int getQuestionsPerRounds() {
        return questionsPerRounds;
    }

    /**
     * @return το μέγιστο αριθμό των παιχτών
     */
    public int getMaxPlayer() {
        return maxPlayers;
    }

    /**
     * @return το πλήθος των γύρων
     */
    public int getRounds() {
        return rounds;
    }

    /**
     * Αυτή η μέθοδος αποθηκεύει σε ένα ArrayList αντικείμενα της κλάσης Player για κάθε παίχτη
     * @param names τα ονόματα των παιχτών
     */
    public void saveNames(ArrayList<String> names){
        for (String name : names) {
            Player player = new Player(name);
            players.add(player);
        }
    }

    /**
     * @return το πλήθος των παιχτών
     */
    public int getTotalPlayers() {
        return totalPlayers;
    }

    /**
     * @return ένα τυχαίο τύπο γύρου
     */
    public String getRandomTypesRound(){
        Random random = new Random();
        int randomInt = random.nextInt(roundTypes.size());
        return roundTypes.get(randomInt);
    }

    /**
     * @return το ArrayList με στοιχεία του τα αντικείμενα της κλάσης Player
     */
    public ArrayList<Player> getPlayers(){
        return players;
    }

    /**
     * @param totalPlayers το πλήθος των παιχτών
     */
    public void setTotalPlayers(int totalPlayers){
        this.totalPlayers = totalPlayers;
    }

    /**
     * @param i ένας παίχτης
     * @return την απάντηση του παίχτη i
     */
    public String getPlayersAnswer(int i){
        return getPlayers().get(i).getAnswer();
    }

    /**
     * @param i ένας παίχτης
     * @param answer η απάντηση του παίχτη i
     */
    public void setPlayersAnswer(int i, String answer){
        getPlayers().get(i).setAnswer(answer);
    }

    /**
     * @param i ένας παίχτης
     * @param bettingPoints τους πόντους που πόνταρε ο παίχτης i
     */
    public void setBettingPoints(int i, int bettingPoints){
        getPlayers().get(i).getBetting().setBettingPoints(bettingPoints);
    }

    /**
     * @param i ένας παίχτης
     * @return τους πόντους που πόνταρε ο παίχτης i
     */
    public int getBettingPoints(int i){
        return getPlayers().get(i).getBetting().getBettingPoints();
    }

    /**
     * @param i ένας παίχτης
     * @param totalSeconds τα δευτερόλεπτα που απέμειναν από τη στιγμή που ο παίχτης i απαντήσει σωστά στην ερώτηση
     */
    public void setSeconds(int i, int totalSeconds){
       getPlayers().get(i).getClock().setSeconds(totalSeconds);
    }

    /**
     *
     * @param i ένας παίχτης
     * @return τους πόντους του παίχτη i στο γύρο "Χρονόμετρο".
     */
    public int getRoundPoints(int i){
        return getPlayers().get(i).getClock().getRoundPoints();
    }


    /**
     * @param i ένας παίχτης
     * @return το όνομα του παίχτη i
     */
    public String getName(int i){
        return getPlayers().get(i).getName();
    }

    /**
     * @param i ένας παίχτης
     * @param given αν ο παίχτης i πόνταρε ή όχι
     */
    public void setBetGiven(int i, boolean given){
        getPlayers().get(i).getBetting().setBetGiven(given);
    }

    /**
     *
     * @param i ένας παίχτης
     * @return αν ο παίχτης i πόνταρε ή όχι
     */
    public boolean getBetGiven(int i){
        return getPlayers().get(i).getBetting().getBetGiven();
    }
}