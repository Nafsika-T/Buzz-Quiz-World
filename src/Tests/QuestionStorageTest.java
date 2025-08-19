package Tests;

import logic.QuestionStorage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuestionStorageTest {

    private final QuestionStorage questionStorage=new QuestionStorage("TechnologyQuestions.txt", "TechnologyCorrectAnswers.txt", "TechnologyAnswers.txt", "Τεχνολογία");
    @Test
    public void getCategory() {
        String category=questionStorage.getCategory();
        assertEquals(category,"Τεχνολογία");
    }

    @Test
    public void getRandomQuestion() {
    }
}