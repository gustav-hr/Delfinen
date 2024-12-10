package services;

import java.io.File;
import java.io.*;

public class CompSeniorTournamentHandler {
    private final String fileName = "CompSeniorTournament.txt";

    // FROM CONSOLE TO FILE --------------------------------------------------------------------------------------------
    public void addSeniorTournamentData(String tournamentName, String date, String discipline, String[][] swimmers) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            // writes tournament header
            writer.write("\nTournament: " + tournamentName + "    dato: " + date + "    *" + discipline + "*\n");

            // Write swimmers data
            for (String[] swimmer : swimmers) {
                String name = swimmer[0];
                String time = swimmer[1];
                String placement = swimmer[2];
                writer.write(name + " Time: " + time + " Nr.: " + placement + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while saving tournament data: " + e.getMessage());
        }
    }

    // FROM FILE TO CONSOLE --------------------------------------------------------------------------------------------
    public String loadSeniorTournamentData() {
        File file = new File(fileName);
        if (!file.exists()) {
            return "No tournament data found.";
        }

        StringBuilder tournamentData = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            tournamentData.append("--- Existing Tournament Data ---\n");
            while ((line = reader.readLine()) != null) {
                tournamentData.append(line).append("\n");
            }
            tournamentData.append("--------------------------------");
        } catch (IOException e) {
            throw new RuntimeException("Error while reading tournament data: " + e.getMessage(), e);
        }

        return tournamentData.toString();
    }
}