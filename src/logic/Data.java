package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Η κλάση Data αναπαριστά τα δεδομένα κάθε παίχτη (όνομα, σκορ, νίκες) και τα αποθηκεύει σε αρχεία κειμένου.
 */
public class Data {

    /**
     * Αυτή η μέθοδος δέχεται το όνομα και το σκορ ενός παίχτη και τα αποθηκεύει στα αρχεία κειμένου Names.txt και Score.txt.
     * Αν το όνομα του παίχτη υπάρχει ήδη, τότε αν το σκορ που πέτυχε στο πιο πρόσφατο παιχνίδι είναι μεγαλύτερο από το προηγούενο,
     * το αντικαθιστά με το καινούργιο.
     * @param namePlayer το όνομα του παίχτη
     * @param scorePlayer το σκορ του παίχτη
     */
    public void saveScore(String namePlayer, int scorePlayer){
        try {
            FileWriter Score = new FileWriter("Score.txt",true);
            FileWriter Name = new FileWriter("Names.txt",true);
            FileWriter Wins = new FileWriter("Wins.txt",true);

            Scanner scanner = new Scanner(new File("Names.txt"));
            Scanner scanner1 =new Scanner(new File("Score.txt"));
            boolean existingName = false;
            int line = 0;
            while (scanner.hasNextLine()) {
                line++;
                String name = scanner.nextLine();
                String score = scanner1.nextLine();
                if (name.equals(namePlayer)){
                    existingName =true;
                    if (score.equals("-") || scorePlayer > Integer.parseInt(score)){
                        setVariable(line, String.valueOf(scorePlayer), "Score.txt");
                    }
                }
            }
            if (!existingName){
                Name.write(namePlayer+"\n");
                Score.write(scorePlayer +"\n");
                Wins.write("-"+"\n");
            }
            Score.close();
            Wins.close();
            Name.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Αυτή η μέθοδος δέχεται το όνομα ενός παίχτη. Αν το όνομα του παίχτη δεν υπάρχει, τότε το αποθηκεύει στο αρχείο Names.txt
     * και του προσθέτει 1 νίκη την οποία αποθηκεύει στο αρχείο Wins.txt. Αν το όνομα του παίχτη υπάρχει ήδη, τότε απλά του προσθέτει
     * μια επιπλέον νίκη.
     * @param namePlayer το όνομα του παίχτη
     */
    public void saveWin(String namePlayer) {
        try {
            FileWriter Wins = new FileWriter("Wins.txt",true);
            Scanner scanner = new Scanner(new File("Names.txt"));
            Scanner scanner1 = new Scanner(new File("Wins.txt"));
            boolean existingName = false;
            int line = 0;
            while (scanner.hasNextLine()) {
                line++;
                String name = scanner.nextLine();
                String wins = scanner1.nextLine();
                if (name.equals(namePlayer)){
                    existingName =true;
                    if (wins.equals("-") ){
                        setVariable(line,"1", "Wins.txt");
                    }
                    else{
                        int winPlayer=Integer.parseInt(wins)+1;
                        setVariable(line, String.valueOf(winPlayer), "Wins.txt");
                    }
                }
            }
            if (!existingName){
                FileWriter name= new FileWriter("Names.txt",true);
                name.write(namePlayer+"\n");
                Wins.write(1 +"\n");
                FileWriter win = new FileWriter("Wins.txt",true);
                FileWriter score = new FileWriter("Score.txt",true);
                score.write("-"+"\n");
                name.close();
                score.close();
                win.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Αυτή η μέθοδος αντικαθιστά τα παλιά δεδομένα για το σκορ ή τις νίκες ενός παίχτη με τα καινούργια.
     * @param lineNumber η γραμμή που πρέπει να αντικατασταθεί
     * @param data τα καινούργια δεδομένα
     * @param file το αρχείο στο οποίο θα πρέπει να γίνει η αντικατάσταση
     * @throws IOException εξαίρεση σε περίπτωση αποτυχίας φόρτωσης του αρχείου
     */
    public static void setVariable(int lineNumber, String data, String file) throws IOException {
        Path path = Paths.get(file);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber - 1, data);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
}
