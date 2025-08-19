package logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Η κλάση αυτή αναπαριστά το σύνολο των αποθηκών ερωτήσεων που αντιστοιχούν σε κάθε κατηγορία.
 */
public class CategoryStorage {

    private final ArrayList<QuestionStorage> categories;

    /**
     * Κατασκευαστής.
     * Δημιουργεί μια αποθήκη ερωτήσεων για κάθε κατηγορία και τις προσθέτει σε ένα ArrayList.
     */
    public CategoryStorage() {
        categories = new ArrayList<>();
        QuestionStorage technology = new QuestionStorage("TechnologyQuestions.txt", "TechnologyCorrectAnswers.txt", "TechnologyAnswers.txt", "Τεχνολογία");
        QuestionStorage space = new QuestionStorage("SpaceQuestions.txt", "SpaceCorrectAnswers.txt", "SpaceAnswers.txt", "Διάστημα");
        QuestionStorage history = new QuestionStorage("HistoryQuestions.txt", "HistoryCorrectAnswers.txt", "HistoryAnswers.txt", "Ιστορία");
        QuestionStorage geography = new QuestionStorage("GeographyQuestions.txt", "GeographyCorrectAnswers.txt", "GeographyAnswers.txt","Γεωγραφία");
        categories.add(technology);
        categories.add(space);
        categories.add(history);
        categories.add(geography);
    }

    /**
     * @return μια τυχαία αποθήκη ερωτήσεων.
     */
    public QuestionStorage getRandomCategory(){
        Random random = new Random();
        return categories.get(random.nextInt(categories.size()));
    }
}
