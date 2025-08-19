package Tests;

import logic.Question;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {

    private final Question question=new Question("Πως σε λένε;");

    @Test
    public void getQuestion() {
        String question1= question.getQuestion();
        assertEquals(question1,"Πως σε λένε;");
    }

    @Test
    public void getCorrectAnswer() {
        question.setCorrectAnswer("απάντηση");
        String correctAnswer=question.getCorrectAnswer();
        assertEquals(correctAnswer,"απάντηση");
    }

    @Test
    public void getAnswers() {
        question.addAnswer("απάντηση1");
        question.addAnswer("απάντηση2");
        question.addAnswer("απάντηση3");
        question.addAnswer("απάντηση4");
        assertEquals(question.getAnswers(),"<html>" + "A. " + "απάντηση1" + "<br>" + "B. " + "απάντηση2" + "<br>" + "C. " + "απάντηση3" + "<br>" + "D. " + "απάντηση4" + "</html>");
    }

    @Test
    public void checkAnswer1() {
        question.addAnswer("απάντηση1");
        question.addAnswer("απάντηση2");
        question.addAnswer("απάντηση3");
        question.addAnswer("απάντηση4");
        question.setCorrectAnswer("απάντηση1");
        boolean check=question.checkAnswer("A");
        assertTrue(check);
    }

    @Test
    public void checkAnswer2() {
        question.addAnswer("απάντηση1");
        question.addAnswer("απάντηση2");
        question.addAnswer("απάντηση3");
        question.addAnswer("απάντηση4");
        question.setCorrectAnswer("απάντηση3");
        boolean check=question.checkAnswer("B");
        assertFalse(check);
    }

    @Test
    public void setCorrectAnswer() {
    }

    @Test
    public void addAnswer() {
    }

    @Test
    public void answersInRandom() {
    }
}