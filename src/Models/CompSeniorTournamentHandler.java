package Models;

import java.io.File;
import java.io.*;

public class CompSeniorTournamentHandler {
    private final String fileName;

    public CompSeniorTournamentHandler(String fileName) {
        this.fileName = fileName;
    }

    public void addTournamentData(String tournamentName, String date, String discipline, String[][] swimmers) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            // Skriv turneringsheader
            writer.write("\nTournament: " + tournamentName + "    dato: " + date + "    *" + discipline + "*\n");

            // Skriv svømmerens data
            for (String[] swimmer : swimmers) {
                String name = swimmer[0];
                String time = swimmer[1];
                String placement = swimmer[2];
                writer.write(name + " Time: " + time + " Nr.: " + placement + "\n");
            }

            System.out.println("Tournament data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error while saving tournament data: " + e.getMessage());
        }
    }

    public void loadTournamentData() {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("No tournament data found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("\n--- Existing Tournament Data ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("--------------------------------");
        } catch (IOException e) {
            System.out.println("Error while reading tournament data: " + e.getMessage());
        }
    }
}