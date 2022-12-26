import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Formatter;
import java.nio.file.Paths;

public class HighScore {

    public class HighScoreEntry {
        private String name;
        private int score;
    
        public HighScoreEntry(String n, int s) {
            name = n;
            score = s;
        }
    
        public String getName() {
            return name;
        }
    
        public int getScore() {
            return score;
        }
    }
    
    public void writeHighScores(HighScoreEntry[] highScores) {
        Formatter f = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("highscores.txt");
            f = new Formatter(fw);
            for (int i = 0; i < highScores.length; i++) {
                f.format("%s:%d%n", highScores[i].getName(), highScores[i].getScore());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing the high scores file.");
        } finally {
            if (f != null) {
                f.close();
            }
        }
    }
    
    public HighScoreEntry[] readHighScores() {
        HighScoreEntry[] highScores = new HighScoreEntry[10];
    
        // Initialize the high scores array with default values
        for (int i = 0; i < highScores.length; i++) {
            highScores[i] = new HighScoreEntry("", 0);
        }
        
        Scanner reader = null;
    
        try {
            reader = new Scanner(Paths.get("highscores.txt"));
            int i = 0;
            while (reader.hasNextLine() && i < 10) {
                String line = reader.nextLine();
                String[] parts = line.split(":");
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);
                highScores[i] = new HighScoreEntry(name, score);
                i++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the high scores file.");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    
        return highScores;
    }
    
    public void updateHighScores(String name, int score) {

        // Create a new HighScoreEntry object with the player's name and score
        HighScoreEntry newEntry = new HighScoreEntry(name, score);

       // Read the current high scores from the file
        HighScoreEntry[] highScores = readHighScores();

        // Add the new entry to the high scores array
        highScores[highScores.length - 1] = newEntry;

        // Sort the high scores in descending order
        sortHighScores(highScores);

        // Write the sorted high scores to the file
        writeHighScores(highScores);
    }    

    private void sortHighScores(HighScoreEntry[] highScores) {
        for (int i = 0; i < highScores.length - 1; i++) {
            for (int j = i + 1; j < highScores.length; j++) {
                if (highScores[i].getScore() < highScores[j].getScore()) {
                    HighScoreEntry temp = highScores[i];
                    highScores[i] = highScores[j];
                    highScores[j] = temp;
                }
            }
        }
    }    
}