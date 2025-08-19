package logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Η κλάση logic.GameGUI αναπαριστά το γραφικό περιβάλλον χρήστη του παιχνιδιού
 */
public class GameGUI extends JFrame{

    private final ArrayList<String> names = new ArrayList<>();
    private final ArrayList<JTextField> nameTexts = new ArrayList<>();
    private final ArrayList<String> usedQuestions = new ArrayList<>();
    private int currentRound = 0;
    private int questionsPerRound = 0;
    private Question question;
    private String roundTypeName;
    private Timer timer;
    private int totalSeconds;
    private int player;
    private boolean bet;
    private boolean wrongAnswer;
    private final Data data = new Data();
    private final JPanel categoryPanel = new JPanel();
    private KeyAdapter keyAdapter1;
    private KeyAdapter keyAdapter2;
    private JLabel winnerLabel = new JLabel();
    private Game game;
    private boolean isOver;

    /**
     * Αυτή η μέθοδος ξεκινάει ένα καινούργιο παιχνίδι. Δημιουργεί ένα αντικείμενο της κλάσης logic.Game και προσθέτει κουμπιά για
     * την επιλογή του πλήθος των παιχτών, για την έναρξη του παιχνιδιού και για την εμφάνιση του σκορ του κάθε παίχτη.
     */
    public void newGame() {
        getContentPane().removeAll();
        repaint();
        game = new Game();
        setTitle("Buzz!: Quiz World");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setFocusable(true);
        setLayout(new GridBagLayout());
        JLabel label = new JLabel("Επίλεξε τον αριθμό των παικτών");
        add(label);
        for (int i = 0; i < game.getMaxPlayer(); i++) {
            JButton b1 = new JButton(String.valueOf(i + 1));
            add(b1);
            b1.addActionListener(e -> {
                getContentPane().removeAll();
                repaint();
                if (e.getActionCommand().equals("1")) {
                    game.setTotalPlayers(1);
                    JButton b2 = new JButton("ΠΑΙΞΕ");
                    add(b2);
                    JButton b3 = new JButton("ΣΚΟΡ");
                    add(b3);
                    b2.addActionListener(e1 -> handleEvent());
                    b3.addActionListener(e1 -> handleEvent2("Score.txt"));
                } else {
                    game.setTotalPlayers(2);
                    JButton b2 = new JButton("ΠΑΙΞΤΕ");
                    add(b2);
                    JButton b3 = new JButton("ΝΙΚΕΣ");
                    add(b3);
                    b2.addActionListener(e1 -> handleEvent());
                    b3.addActionListener(e1 -> handleEvent2("Wins.txt"));
                }
                setVisible(true);
            });
            setVisible(true);
        }
    }

    /**
     * Αυτή η μέθοδος καλείται όταν πατηθεί το κουμπί για την έναρξη του παιχνιδιού. Ζητάει από τον χρήστη ή τους χρήστες
     * τα ονόματά τους και τα αποθηκεύει σε αντίστοιχα ArrayLists. Προσθέτει στο Frame το κουμπί OK, για την έναρξη των γύρων.
     */
    public void handleEvent() {
        getContentPane().removeAll();
        repaint();
        nameTexts.clear();
        names.clear();
        JButton OK = new JButton("OK");
        if (game.getTotalPlayers() > 1) {
            for (int i = 0; i < game.getTotalPlayers(); i++) {
                int player = i + 1;
                JLabel nameLabel = new JLabel("Όνομα " + player + "ου παίκτη");
                JTextField nameText = new JTextField("name");
                add(nameLabel);
                add(nameText);
                nameTexts.add(nameText);
            }
        } else {
            JLabel nameLabel = new JLabel("Όνομα");
            JTextField nameText = new JTextField("name");
            add(nameLabel);
            add(nameText);
            nameTexts.add(nameText);
        }
        add(OK);
        setVisible(true);
        OK.addActionListener(e -> {
            for (JTextField name : nameTexts) {
                names.add(name.getText());
            }
            getContentPane().removeAll();
            repaint();
            game.saveNames(names);
            startRound();
        });
    }

    /**
     * Η μέθοδος αυτή καλείται όταν πατηθεί το αντίστοιχο κουμπί για την εμφάνιση του σκορ ή των νικών. Εμφανίζει τα ονόματα
     * και το σκορ ή τις νίκες του κάθε παίχτη από τα αντίστοιχα αρχεία. Σε περίπτωση που δε βρεθούν τα αρχεία τότε εμφανίζεται
     * στο Frame το μήνυμα "File not found". Προσθέτει το κουμπί ΠΙΣΩ ώστε ο χρήστης να μπορέσει να πλοηγηθεί στο αρχικό Frame.
     * @param fileName το όνομα του αρχείου για ανάγνωση (τα σκορ ή οι νίκες).
     */
    private void handleEvent2(String fileName) {
        getContentPane().removeAll();
        repaint();
        setSize(400, 700);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        try {
            JPanel scorePanel = new JPanel();
            scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
            Scanner scanner1 = new Scanner(new File("Names.txt"));
            Scanner scanner2 = new Scanner(new File(fileName));
            while (scanner1.hasNextLine() && scanner2.hasNextLine()) {
                JLabel label = new JLabel();
                String name = scanner1.nextLine();
                String score = scanner2.nextLine();
                label.setText(name + " " + score);
                scorePanel.add(label);
            }
            add(scorePanel);
        } catch (FileNotFoundException e) {
            JLabel label = new JLabel("File not found");
            add(label);
        }
        JButton backButton = new JButton("ΠΙΣΩ");
        add(backButton, BorderLayout.PAGE_END);
        backButton.addActionListener(e1 -> newGame());
        setVisible(true);
    }

    /**
     * Αυτή η μέθοδος προσθέτει σε ένα Panel μια τυχαία κατηγορία του παιχνιδιού και το εμφανίζει στο Frame.
     */
    public void showCategory() {
        categoryPanel.removeAll();
        categoryPanel.setBounds(40, 80, 200, 200);
        categoryPanel.setBackground(Color.white);
        CategoryStorage c = new CategoryStorage();
        QuestionStorage category = c.getRandomCategory();
        JLabel label = new JLabel(category.getCategory());
        categoryPanel.add(label);
        question = category.getRandomQuestion();
        for (String q : usedQuestions) {
            while (question.getQuestion().equals(q)) {
                question = category.getRandomQuestion();
            }
        }

        usedQuestions.add(question.getQuestion());
        add(categoryPanel);
        setVisible(true);
    }

    /**
     * Αυτή η μέθοδος προσθέτει σε ένα Panel μια τυχαία ερώτηση του παιχνιδιού (εικόνα ή συμβολοσειρά) που δεν έχει ξαναεμφανιστεί
     * στο παιχνίδι, μαζί με τις τέσσερις πιθανές απαντήσεις σε τυχαία σειρά και το εμφανίζει στο Frame.
     */
    public void showQuestion() {
        JPanel questionsPanel = new JPanel();
        JLabel questionLabel = new JLabel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));
        if (question.getQuestion().contains(".jpg")) {
            setSize(1500, 1500);
            setLocationRelativeTo(null);
            URL imageURL = this.getClass().getResource(question.getQuestion());
            ImageIcon icon = new ImageIcon(imageURL);
            icon.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
            questionLabel.setIcon(icon);
        } else {
            setSize(1000, 200);
            setLocationRelativeTo(null);
            questionLabel.setText(question.getQuestion());
        }
        questionsPanel.add(questionLabel);
        question.answersInRandom();
        JLabel answersLabel = new JLabel(question.getAnswers());
        questionsPanel.add(answersLabel);
        add(questionsPanel);
        setVisible(true);
    }

    /**
     * Αυτή η μέθοδος διαβάζει το χαρακτήρα που πληκτρολογεί ο κάθε παίχτης. Ο κάθε χαρακτήρας αντιστοιχίζεται στις τέσσερις
     * πιθανές απαντήσεις. Για τον 2ο παίχτη οι χαρακτήρες K, L, M, N  αντιστοιχίζονται ένας προς έναν με τις απαντήσεις A, B, C, D
     * της κάθε ερώτησης.
     * @param e το γεγονός όπου ο χρήστης πάτησε κάποιο κουμπί στο πληκτρολόγιο
     */
    public void setAnswer(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'A':
                player = 0;
                game.setPlayersAnswer(player, "A");
                break;
            case 'B':
                player = 0;
                game.setPlayersAnswer(player, "B");
                break;
            case 'C':
                player = 0;
                game.setPlayersAnswer(player, "C");
                break;
            case 'D':
                player = 0;
                game.setPlayersAnswer(player, "D");
                break;
            case 'K':
                player = 1;
                game.setPlayersAnswer(player, "A");
                break;
            case 'L':
                player = 1;
                game.setPlayersAnswer(player, "B");
                break;
            case 'M':
                player = 1;
                game.setPlayersAnswer(player, "C");
                break;
            case 'N':
                player = 1;
                game.setPlayersAnswer(player, "D");
                break;
        }
    }

    /**
     * Αυτή η μέθοδος προσθέτει στο Frame τη σωστή απάντηση μιας ερώτησης.
     */
    public void showRightAnswer(){
        JLabel label = new JLabel(" Σωστή Απάντηση: " + question.getCorrectAnswer());
        add(label);
    }

    /**
     * Αυτή η μέθοδος ξεκινάει έναν γύρο του παιχνιδιού. Επιλέγει τυχαία τον τύπο ενός γύρου. Ελέγχει αν ο γύρος που τυγχαίνει
     * όταν το πλήθος των παιχτών ισούται με 1 είναι ποντάρισμα ή σωστή απάντηση. Εμφανίζει στο Frame τον τύπο του γύρου και
     * τον αριθμό του. Ελέγχει ποιος τύπος γύρου έτυχε και καλεί τις αντίστοιχες μεθόδους.
     */
    public void startRound() {
        getContentPane().removeAll();
        repaint();
        setSize(1000, 200);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        repaint();
        currentRound++;
        roundTypeName = game.getRandomTypesRound();
        while ((roundTypeName.equals("Σταμάτησε το χρονόμετρο") || roundTypeName.equals("Θερμόμετρο") || roundTypeName.equals("Γρήγορη απάντηση")) && game.getTotalPlayers() <= 1) {
            roundTypeName = game.getRandomTypesRound();
        }
        JLabel roundLabel = new JLabel(currentRound + "ος γύρος: " + roundTypeName);
        add(roundLabel);
        switch (roundTypeName) {
            case "Σωστή Απάντηση":
                startCorrectAnswer();
                break;
            case "Ποντάρισμα":
                startBetting();
                break;
            case "Σταμάτησε το χρονόμετρο":
                startClock();
                break;
            case "Γρήγορη απάντηση":
                startQuickAnswer();
                break;
            case "Θερμόμετρο":
                startThermometer();
                break;
        }
        setVisible(true);
    }

    /**
     * Αυτή η μέθοδος ξεκινάει έναν γύρο τύπου "Σωστή Απάντηση". Διαβάζει για κάθε παίχτη την απάντηση που έδωσε από το πληκτρολόγιο
     * και ελέγχει αν αυτή είναι σωστή με τη μέθοδο checkCorrectAnswer.
     */
    public void startCorrectAnswer() {
        questionsPerRound++;
        showCategory();
        showQuestion();
        keyAdapter1 = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                removeKeyListener(keyAdapter1);
                setAnswer(e);
                if (game.getTotalPlayers() > 1) {
                    keyAdapter2 = new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            super.keyTyped(e);
                            removeKeyListener(keyAdapter2);
                            setAnswer(e);
                            checkCorrectAnswer();
                        }
                    };
                    addKeyListener(keyAdapter2);
                } else {
                    checkCorrectAnswer();
                }
            }
        };
        addKeyListener(keyAdapter1);
        setVisible(true);
    }

    /**
     * Η μέθοδος αυτή ελέγχει αν η απάντηση που δίνει κάθε παίχτης στον γύρο "Σωστή Απάντηση" είναι σωστή καλώντας για κάθε
     * παίχτη τη μέθοδο checkAnswer. Αν έστω και ένας παίχτης απάντησε λάθος στην ερώτηση τότε κάλει τη μέθοδο showRightAnswer
     * για την εμφάνιση της σωστής απάντησης.
     */
    public void checkCorrectAnswer() {
        getContentPane().removeAll();
        repaint();
        wrongAnswer = false;
        for (int i = 0; i < game.getTotalPlayers(); i++) {
            checkAnswer(i, 1000);
        }
        if (wrongAnswer) {
            showRightAnswer();
        }
        showNext();
    }

    /**
     * Αυτή η μέθοδος ελέγχει αν η απάντηση του κάθε παιχτή είναι σωστή και προσθέτει τους πόντους που του αναλογούν.
     * @param i ο παίχτης που απάντησε
     * @param points οι πόντοι που θα προστεθούν στον παίχτη αν απάντησε σωστά
     */
    public void checkAnswer(int i, int points) {
        if (question.checkAnswer(game.getPlayersAnswer(i))) {
            JLabel label6 = new JLabel(game.getName(i) + " απάντησες σωστά! Παίρνεις " + points + " πόντους ");
            add(label6);
            game.getPlayers().get(i).addPoints(points);
        } else {
            JLabel label = new JLabel(game.getName(i) + " απάντησες λάθος! ");
            add(label);
            wrongAnswer = true;
        }
    }

    /**
     * Αυτή η μέθοδος ξεκινάει έναν γύρο τύπου "Ποντάρισμα". Εμφανίζει στο Frame την κατηγορία της ερώτησης με τη μέθοδο showCategory
     * και καλεί τη μέθοδο showBettingButtons για κάθε παίχτη.
     */
    public void startBetting() {
        setSize(2500, 200);
        setLocationRelativeTo(null);
        questionsPerRound++;
        showCategory();
        bet = true;
        for (int i = 0; i < game.getTotalPlayers(); i++) {
            game.getPlayers().get(i).getBetting().setBetGiven(false);
            showBettingButtons(i);
        }
    }

    /**
     * Αυτή η μέθοδος προσθέτει τα κουμπιά στο Frame ώστε ο κάθε παίχτης να μπορεί να ποντάρει 250, 500, 750 ή 1000 πόντους.
     * @param i ο  παίχτης που ποντάρει
     */
    public void showBettingButtons(int i) {
        JLabel label = new JLabel(game.getName(i) + " μπορείς να ποντάρεις 250,500,750 ή 1000 πόντους");
        categoryPanel.add(label);
        JButton Button250 = new JButton("250");
        categoryPanel.add(Button250);
        JButton Button500 = new JButton("500");
        categoryPanel.add(Button500);
        JButton Button750 = new JButton("750");
        categoryPanel.add(Button750);
        JButton Button1000 = new JButton("1000");
        categoryPanel.add(Button1000);
        Button250.addActionListener(e -> handleEventBetting(e, Button250, Button500, Button750, Button1000, i));
        Button500.addActionListener(e -> handleEventBetting(e, Button500, Button250, Button750, Button1000, i));
        Button750.addActionListener(e -> handleEventBetting(e, Button750, Button250, Button500, Button1000, i));
        Button1000.addActionListener(e -> handleEventBetting(e, Button1000, Button250, Button500, Button750, i));
        setVisible(true);
    }

    /**
     * Η μέθοδος αυτή ελέγχει ποιο κουμπί πονταρίσματος πάτησε ο κάθε παίχτης και καλεί τη μέθοδο setBettingPoints. Όταν έχουν
     * ποντάρει όλοι οι παίχτες, τότε εμφανίζει το κουμπί OK για την έναρξη των ερωτήσεων.
     * @param e το γεγονός όπου πατήθηκε ένα κουμπί
     * @param button1 το 1ο κουμπί για το ποντάρισμα 250 πόντων
     * @param button2 το 2ο κουμπί για το ποντάρισμα 500 πόντων
     * @param button3 το 3ο κουμπί για το ποντάρισμα 750 πόντων
     * @param button4 το 4ο κουμπί για το ποντάρισμα 1000 πόντων
     * @param i ο παίχτης που ποντάρει
     */
    public void handleEventBetting(ActionEvent e, JButton button1, JButton button2, JButton button3, JButton button4, int i) {
        switch (e.getActionCommand()) {
            case "250":
                game.setBettingPoints(i, 250);
                break;
            case "500":
                game.setBettingPoints(i, 500);
                break;
            case "750":
                game.setBettingPoints(i, 750);
                break;
            case "1000":
                game.setBettingPoints(i, 1000);
                break;
        }
        button1.setBackground(Color.BLUE);
        button2.setBackground(Color.white);
        button3.setBackground(Color.white);
        button4.setBackground(Color.white);
        game.setBetGiven(i, true);
        int count = 0;
        for (int j = 0; j < game.getTotalPlayers(); j++) {
            if (game.getBetGiven(j)) {
                count++;
            }
        }
        if (count == game.getTotalPlayers() && bet) {
            JButton OK2 = new JButton("OK");
            OK2.addActionListener(e1 -> Betting());
            add(OK2);
            bet = false;
        }
        setVisible(true);
    }

    /**
     * Αυτή η μέθοδος διαβάζει για κάθε παίχτη την απάντηση που έδωσε από το πληκτρολόγιο και ελέγχει αν αυτή είναι σωστή με
     * τη μέθοδο correctAnswerBetting.
     */
    public void Betting() {
        getContentPane().removeAll();
        repaint();
        showQuestion();
        setVisible(true);
        keyAdapter1 = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                removeKeyListener(keyAdapter1);
                setAnswer(e);
                if (game.getTotalPlayers() > 1) {
                    keyAdapter2 = new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            super.keyTyped(e);
                            removeKeyListener(keyAdapter2);
                            setAnswer(e);
                            getContentPane().removeAll();
                            repaint();
                            correctAnswerBetting();
                            setVisible(true);
                        }
                    };
                    addKeyListener(keyAdapter2);
                }else {
                    correctAnswerBetting();
                }
            }
        };
        addKeyListener(keyAdapter1);
    }

    /**
     * Η μέθοδος αυτή ελέγχει αν η απάντηση που δίνει κάθε παίχτης στον γύρο "Ποντάρισμα" είναι σωστή. Αν είναι σωστή τότε
     * προσθέτει τους πόντους που πόνταρε, αλλιώς τους αφαιρεί. Αν έστω και ένας παίχτης απάντησε λάθος στην ερώτηση τότε
     * κάλει τη μέθοδο showRightAnswer για την εμφάνιση της σωστής απάντησης.
     */
    public void correctAnswerBetting() {
        wrongAnswer = false;
        getContentPane().removeAll();
        repaint();
        for (int i = 0; i < game.getTotalPlayers(); i++) {
            if (question.checkAnswer(game.getPlayersAnswer(i))) {
                JLabel label6 = new JLabel(game.getName(i) + " απάντησες σωστά! Παίρνεις " + game.getBettingPoints(i) + " πόντους");
                add(label6);
                game.getPlayers().get(i).addPoints(game.getBettingPoints(i));
            }else {
                wrongAnswer = true;
                JLabel label6 = new JLabel(game.getName(i) + " απάντησες λάθος! Χάνεις " + game.getBettingPoints(i) + " πόντους");
                add(label6);
                game.getPlayers().get(i).removePoints(game.getBettingPoints(i));
            }
            setVisible(true);
        }
        if(wrongAnswer){
            showRightAnswer();
        }
        showNext();
    }

    /**
     * Αυτή η μέθοδος ξεκινάει ένα γύρο τύπου "Σταμάτησε το χρονόμετρο". Ξεκινάει η αντίστροφη μέτρηση από το 10000. Για κάθε
     * παίχτη διαβάζεται η απάντηση που έδωσε από το πληκτρολόγιο και ελέγχεται αν αυτή είναι σωστή με τη μέθοδο checkAnswer.
     * Αν απαντήσουν όλοι οι παίχτες και ο χρόνος δεν έχει τελειώσει, τότε το χρονόμετρο σταματά. Αν η απάντηση ενός παίχτη
     * είναι σωστή τότε προστίθεται στο τελικό σκορ τα δευτερόλεπτα που απέμειναν από τη στιγμή που απαντήθηκε η ερώτηση επί 0.2.
     */
    public void startClock() {
        questionsPerRound++;
        showCategory();
        JLabel label = new JLabel("10000");
        label.setVerticalAlignment(JLabel.CENTER);
        add(label);
        JButton button1 = new JButton("Start");
        add(button1);
        setVisible(true);
        totalSeconds = 10000;
        button1.addActionListener(e -> {
            label.setText("10000");

            timer = new Timer(100, e1 -> {
                if (totalSeconds <= 0) {
                    ((Timer) e1.getSource()).stop();
                } else {
                    totalSeconds -= 100;
                }
                label.setText(Integer.toString(totalSeconds));
                if (totalSeconds == 0) {
                    getContentPane().removeAll();
                    repaint();
                    JLabel timeEnded = new JLabel("Τέλος Χρόνου!");
                    add(timeEnded);
                    showNext();
                    setVisible(true);
                }
            });
            timer.start();
            remove(button1);
            showQuestion();
            keyAdapter1 = new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    super.keyTyped(e);
                    removeKeyListener(keyAdapter1);
                    setAnswer(e);
                    game.setSeconds(player, totalSeconds);
                    keyAdapter2 = new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            super.keyTyped(e);
                            removeKeyListener(keyAdapter2);
                            timer.stop();
                            setAnswer(e);
                            game.setSeconds(player, totalSeconds);
                            getContentPane().removeAll();
                            repaint();
                            wrongAnswer = false;
                            for (int i = 0; i < game.getTotalPlayers(); i++) {
                                int points = game.getRoundPoints(i);
                                checkAnswer(i, points);
                            }
                            if (wrongAnswer) {
                                showRightAnswer();
                            }
                            setVisible(true);
                            showNext();
                        }
                    };
                    addKeyListener(keyAdapter2);
                }
            };
            addKeyListener(keyAdapter1);
            setVisible(true);
        });
    }

    /**
     * Αυτή η μέθοδος ξεκινάει ένα γύρο τύπου "Γρήγορη απάντηση". Για κάθε παίχτη διαβάζεται η απάντηση που έδωσε από το
     * πληκτρολόγιο και ελέγχεται αν αυτή είναι σωστή με τη μέθοδο checkAnswer. Ο πρώτος παίχτης που απαντάει κερδίζει 1000
     * πόντους, ενώ όλοι οι υπολοιποί που απαντούν σωστά κερδίζουν 500 πόντους.
     */
    public void startQuickAnswer() {
        questionsPerRound++;
        showCategory();
        showQuestion();
        keyAdapter1 = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                removeKeyListener(keyAdapter1);
                setAnswer(e);
                int first = player;
                keyAdapter2 = new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        super.keyTyped(e);
                        removeKeyListener(keyAdapter2);
                        setAnswer(e);
                        getContentPane().removeAll();
                        repaint();
                        boolean firstCorrect = false;
                        if (question.checkAnswer(game.getPlayersAnswer(first))) {
                            JLabel firstLabel = new JLabel(game.getName(first) + " απάντησες σωστά! Παίρνεις 1000 πόντους");
                            add(firstLabel);
                            game.getPlayers().get(first).addPoints(1000);
                            firstCorrect = true;
                        } else {
                            JLabel firstLabel = new JLabel(game.getName(first) + " απάντησες λάθος!");
                            add(firstLabel);
                        }
                        for (int i = 0; i < game.getTotalPlayers(); i++) {
                            if (question.checkAnswer(game.getPlayersAnswer(i)) && firstCorrect && i != first) {
                                JLabel secondLabel = new JLabel(game.getName(i) + " απάντησες σωστά παίρνεις 500 πόντους");
                                add(secondLabel);
                                game.getPlayers().get(i).addPoints(500);
                            } else if (question.checkAnswer(game.getPlayersAnswer(i)) && i != first) {
                                JLabel secondLabel = new JLabel(game.getName(i) + " απάντησες σωστά παίρνεις 1000 πόντους");
                                add(secondLabel);
                                game.getPlayers().get(i).addPoints(1000);
                            } else if (i != first) {
                                JLabel secondLabel = new JLabel(game.getName(i) + " απάντησες λάθος!");
                                add(secondLabel);
                                showRightAnswer();
                            }
                        }
                        setVisible(true);
                        showNext();
                    }
                };
                addKeyListener(keyAdapter2);
            }
        };
        addKeyListener(keyAdapter1);
        setVisible(true);
    }

    /**
     * Αυτή η μέθοδος ξεκινάει ένα γύρο τύπου "Θερμόμετρο". Εμφανίζονται ερωτήσεις μέχρις ότου να απαντηθούν 5 σωστά. Σε περίπτωση
     * που απαντούν όλοι οι παίχτες λάθος, τότε εμφανίζεται η σωστή απάντηση της ερώτησης και στη συνέχεια μια καινούργια
     * ερώτηση. Ο παίχτης που θα απαντήση σωστά σε 5 ερωτήσει κερδίζει 5000 πόντους.
     */
    public void startThermometer(){
        wrongAnswer = false;
        showCategory();
        showQuestion();
        setFocusable(true);
        keyAdapter1 = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                removeKeyListener(keyAdapter1);
                setAnswer(e);
                keyAdapter2 = new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        super.keyTyped(e);
                        getContentPane().removeAll();
                        repaint();
                        removeKeyListener(keyAdapter2);
                        setAnswer(e);
                        for (int i = 0; i < game.getTotalPlayers(); i++) {
                            if (question.checkAnswer(game.getPlayersAnswer(i))) {
                                game.getPlayers().get(i).addCorrectAnswer();
                                JLabel label = new JLabel(game.getName(i) + " απάντησες σωστά!");
                                add(label);
                            }
                            else{
                                JLabel label = new JLabel(game.getName(i) + " απάντησες λάθος!");
                                add(label);
                                wrongAnswer = true;
                            }
                        }
                        if(wrongAnswer){
                            showRightAnswer();
                        }
                        isOver = false;
                        for (int i = 0; i < game.getTotalPlayers(); i++) {
                            if (game.getPlayers().get(i).getCorrectAnswers() == 5){
                                game.getPlayers().get(i).addPoints(5000);
                                JLabel label = new JLabel(game.getName(i) + " παίρνεις 5000 πόντους!");
                                add(label);
                                isOver = true;
                                setVisible(true);
                            }
                        }
                        showNextThermometer();
                    }
                };
                addKeyListener(keyAdapter2);
            }
        };
        addKeyListener(keyAdapter1);
        setVisible(true);
    }

    /**
     * Αυτή η μέθοδος χρησιμοποιείται μετά την απάντηση κάποιας ερώτησης στο γύρο Θερμόμετρο. Προσθέτει στο Frame το κουμπί
     * "Επόμενο". Όταν πατηθεί τότε: αν έχουν ξεπεραστεί οι γύροι του παιχνιδιού, το παιχνίδι τελειώνει και καλέιται η μέθοδος
     * gameOver. Αν δεν έχουν απαντηθεί σωστά 5 ερωτήσεις ξανακαλείται η μέθοδος startThermometer. Αν εχούν απαντηθεί σωστά και
     * οι 5, δηλαδή ο γύρος Θερμόμετρο τελειώσει και έχουν απομείνει και άλλοι γύροι, τότε ξεκινάει καινούργιος γύρος με τη
     * μέθοδο startRound.
     */
    public void showNextThermometer() {
        JButton button = new JButton("Επόμενο");
        add(button);
        setVisible(true);
        button.addActionListener(e -> {
            getContentPane().removeAll();
            repaint();
            if (isOver){
                if (currentRound != game.getRounds()) {
                    for (int i = 0; i < game.getTotalPlayers(); i++) {
                        game.getPlayers().get(i).setCorrectAnswers(0);
                    }
                    questionsPerRound = 0;
                    startRound();
                } else {
                    gameOver();
                }
            } else {
                startThermometer();
            }
        });
    }

    /**
     * Αυτή η μέθοδος χρησιμοποιείται μετά την απάντηση μιας ερώτησης σε κάποιο γύρο. Προσθέτει στο Frame το κουμπί "Επόμενο".
     * Όταν πατηθεί τότε: αν έχουν ξεπεραστεί οι γύροι του παιχνιδιού, το παιχνίδι τελειώνει και καλέιται η μέθοδος gameOver.
     * Αν δεν έχει ξεπεραστεί το πλήθος των ερωτήσεων, τότε ξανακαλείται η αντίστοιχη μέθοδος για τον ίδιο γύρο. Αν έχουν απαντηθεί
     * όλες οι ερωτήσεις, δηλαδή ο γύρος τελειώσει και έχουν απομείνει και άλλοι γύροι, τότε ξεκινάει καινούργιος γύρος με τη
     * μέθοδο startRound.
     */
    public void showNext() {
        JButton button4 = new JButton("Επόμενο");
        add(button4);
        setVisible(true);
        button4.addActionListener(e -> {
            getContentPane().removeAll();
            repaint();
            if (questionsPerRound != game.getQuestionsPerRounds()) {
                switch (roundTypeName) {
                    case "Σωστή Απάντηση":
                        startCorrectAnswer();
                        break;
                    case "Ποντάρισμα":
                        startBetting();
                        break;
                    case "Σταμάτησε το χρονόμετρο":
                        startClock();
                        break;
                    case "Γρήγορη απάντηση":
                        startQuickAnswer();
                        break;
                }
            } else if (currentRound != game.getRounds()) {
                questionsPerRound = 0;
                startRound();
            } else {
                gameOver();
            }
        });
    }

    /**
     * Η μέθοδος αυτή χρησιμοποείται για τη λήξη ενός παιχνιδιού. Εμφανίζει το μήνυμα "Τέλος παιχνιδιού!" και το συνολικο σκορ
     * του κάθε παίχτη. Αν το παιχνίδι δεν είναι ατομικό, τότε εμφανίζει ποιος παίχτης νίκησε ή ισοπαλία, ανάλογα με το σκορ
     * του κάθενος. Αποθηκεύει τα ονόματα και το σκορ ή τις νίκες σε αντίστοιχα αρχεία, καλώντας μεθόδους της κλάσης Data.
     * Προσθέτει στο Frame το κουμπί "Νέο Παιχνίδι"). Αν πατηθεί τότε ξεκινάει καινούργιο παιχνίδι καλώντας τη μέθοδο newGame.
     */
    public void gameOver() {
        getContentPane().removeAll();
        repaint();
        JPanel panel3 = new JPanel();
        JLabel label8 = new JLabel("Τέλος παιχνιδιού!");
        panel3.add(label8);
        boolean draw = false;
        int maxScore = 0;
        int winner = game.getPlayers().get(0).getScore();
        for (int i = 0; i < game.getTotalPlayers(); i++) {
            JLabel label = new JLabel(game.getName(i) + " συγκέντρωσες " + game.getPlayers().get(i).getScore() + " πόντους");
            panel3.add(label);
            int score = game.getPlayers().get(i).getScore();
            if (score > maxScore) {
                maxScore = score;
                winner = i;
            } else if (score == maxScore) {
                draw = true;
            }

        }
        for (int i = 0; i < game.getTotalPlayers(); i++) {
            int score = game.getPlayers().get(i).getScore();
            String name = game.getName(i);
            if (game.getTotalPlayers() == 1) {
                data.saveScore(name, score);
            }
        }
        if (draw) {
            this.winnerLabel = new JLabel("Ισοπαλία");
        } else {
            if (game.getTotalPlayers()>1) {
                this.winnerLabel = new JLabel("Ο νικητής είναι ο/η " + game.getName(winner));
                data.saveWin(game.getName(winner));
            }
        }
        add(panel3);
        add(this.winnerLabel);
        setVisible(true);
        JButton newGameButton = new JButton("Νέο Παιχνίδι");
        add(newGameButton);
        newGameButton.addActionListener(e -> {
            getContentPane().removeAll();
            repaint();
            questionsPerRound = 0;
            currentRound = 0;
            newGame();
        });
        setVisible(true);
    }
}