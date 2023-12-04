package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Series {

    public void seasonsAndEpisodes() {
        TextUI ui = new TextUI();
        ArrayList<String> totalSeasonsAndEpisodes = new ArrayList<>();

        boolean validInput = false;
        String seriesName = "";

        while (!validInput) {
            seriesName = ui.getInput("Enter the name of the series: ");

            try (BufferedReader br = new BufferedReader(new FileReader("100bedsteserier.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length >= 5 && parts[0].trim().equalsIgnoreCase(seriesName)) {
                        String seasonsAndEpisodes = parts[4].trim();
                        totalSeasonsAndEpisodes.add(seasonsAndEpisodes);

                        ui.displayMessage("Seasons and Episodes for " + seriesName + ": " + seasonsAndEpisodes);
                        validInput = true; // Set validInput to true to exit the loop
                        break; // Exit the loop once a match is found
                    }
                }

                if (!validInput) {
                    ui.displayMessage("Series name not found. Please try again.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int numberOfEpisodes()
    {
        return 0;
    }

    public int numberOfSeasons()
    {
        return 0;
    }
}
