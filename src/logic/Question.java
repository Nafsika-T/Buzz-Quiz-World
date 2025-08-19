package logic;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Αυτή η κλάση αναπαριστά μια ερώτηση του παιχνιδιού. Μια ερώτηση αποτελείται από τη συμβολοσειρά της,
 * τέσσερις πιθανές απαντήσεις και μια σωστή απάντηση.
 */
public class Question {

    private final ArrayList<String> answers;
    private String correctAnswer;
    private final String question;

    /**
     * Κατασκευαστής
     *
     * @param question η συμβολοσειρά που αναπαριστά την ερώτηση
     */
    public Question(String question) {
        answers = new ArrayList<>();
        this.question = question;
    }

    /**
     * @return τη συμβολοσειρά της ερώτησης
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Αυτή η μέθοδος αλλάζει τη σωστή απάντηση της ερώτησης.
     *
     * @param correctAnswer η σωστή απάντηση
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Αυτή η μέθοδος προσθέτει μια πιθανή απάντηση της ερώτησης.
     *
     * @param answer μια πιθανή απάντηση
     */
    public void addAnswer(String answer) {
        answers.add(answer);
    }

    /**
     * @return τη σωστή απάντηση της ερώτησης
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Αυτή η μέθοδος ανακατεύει τις πιθανές απαντήσεις έτσι ώστε να αποθηκευτούν με τυχαία σειρά.
     */
    public void answersInRandom() {
        Collections.shuffle(answers);
    }

    /**
     * @return τις τέσσερις πιθανές απαντήσεις της ερώτησης
     */
    public String getAnswers() {
        return "<html>" + "A. " + answers.get(0) + "<br>" + "B. " + answers.get(1) + "<br>" + "C. " + answers.get(2) + "<br>" + "D. " + answers.get(3) + "</html>";
    }

    /**
     * Αυτή η μέθοδος δέχεται μια απάντηση από τον παίχτη και επιστρέφει αν αυτή είναι σωστή ή λάθος.
     *
     * @param playerAnswer η απάντηση του παίχτη
     * @return αν η απάντηση του παίχτη είναι σωστή
     */
    public boolean checkAnswer(String playerAnswer) {
        return playerAnswer.equals("A") && correctAnswer.equals(answers.get(0)) || playerAnswer.equals("B") && correctAnswer.equals(answers.get(1)) || playerAnswer.equals("C") && correctAnswer.equals(answers.get(2)) || playerAnswer.equals("D") && correctAnswer.equals(answers.get(3));
    }
}
