package com.task4;

import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Notes App =====");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Clear All Notes");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
                System.out.print("Choose an option: ");
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addNote(scanner);
                case 2 -> viewNotes();
                case 3 -> clearNotes();
                case 4 -> System.out.println("Goodbye");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        scanner.close();
    }

    private static void addNote(Scanner scanner) {
        System.out.print("\nEnter your note:");
        String note = scanner.nextLine().trim();

        if (note.isEmpty()) {
            System.out.println("Note cannot be empty!");
            return;
        }

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(note + System.lineSeparator());
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.err.println("Error writing note: " + e.getMessage());
        }
    }

    private static void viewNotes() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("\nNo notes found. Try adding some first!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean hasNotes = false;

            System.out.println("\n===== Your Notes =====");
            while ((line = reader.readLine()) != null) {
                hasNotes = true;
                System.out.println("- " + line);
            }

            if (!hasNotes) {
                System.out.println("No notes available.");
            }

        } catch (IOException e) {
            System.err.println("Error reading notes: " + e.getMessage());
        }
    }

    private static void clearNotes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No notes to clear.");
            return;
        }

        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            writer.write("");
            System.out.println("All notes cleared successfully!");
        } catch (IOException e) {
            System.err.println("Error clearing notes: " + e.getMessage());
        }
    }
}

