package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Αυτή η κλάση αναπαριστά μια αποθήκη ερωτήσεων με τα εξής χαρακτηριστικά: την κατηγορία, ένα ArrayList με τις ερωτήσεις.
 */
public class QuestionStorage {

    private final String category;
    public final ArrayList<Question> questions;

    /**
     * Κατασκευαστής
     * Ο κατασκευαστής φορτώνει τις ερωτήσεις, τις πιθανές απαντήσεις και τις σωστές απαντήσεις από τα αντίστοιχα αρχεία κειμένου
     * και αρχικοποιεί την κατηγορία. Οι σωστές απαντήσεις είναι γραμμένες στο αρχείο κειμένου με την ίδια σειρά που είναι και
     * οι αντίστοιχες ερωτήσεις καθώς και οι πιθανές απαντήσεις οι οποίες είναι γραμμένες ανά τέσσερις στο αρχείο κειμένου.
     * @param QuestionFileName το αρχείο κειμένου που περιέχει τις ερωτήσεις
     * @param CorrectAnswerFileName το αρχείο κεμένου που περιέχει τις σωστές απαντήσεις
     * @param AnswerFileName το αρχείο κειμένου που περιέχει τις πιθανές απαντήσεις
     * @param category η κατηγορία της αποθήκης ερωτήσεων
     */
    public QuestionStorage(String QuestionFileName, String CorrectAnswerFileName, String AnswerFileName, String category) {
        questions = new ArrayList<>();
        this.category=category;

        try {
            Scanner scanner = new Scanner(new File(QuestionFileName));
            Scanner scanner1 = new Scanner(new File(CorrectAnswerFileName));
            Scanner scanner2 = new Scanner(new File(AnswerFileName));

            while (scanner.hasNextLine() && scanner1.hasNextLine()) {
                String question = scanner.nextLine();
                String correctAnswer = scanner1.nextLine();
                Question q = new Question(question);

                for (int i = 1; scanner2.hasNextLine() && i%5 != 0 ; i++) {
                    String answer = scanner2.nextLine();
                    q.addAnswer(answer);
                }
                q.setCorrectAnswer(correctAnswer);
                questions.add(q);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * @return την κατηγορία της αποθήκης ερωτήσεων.
     */
    public String getCategory() { return category; }

    /**
     * @return μια τυχαία ερώτηση από την αποθήκη ερωτήσεων.
     */
    public Question getRandomQuestion() {
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }
}