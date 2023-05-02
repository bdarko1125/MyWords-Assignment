import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MyWords {
    //Define the main() method
    public static void main(String[] args) {
        //a filename String that is the location and name of the file to be read.
        String filename = "word.text";

        //handle (catch) any Exceptions that are thrown
        try {
            //Create a text file with some words
            PrintWriter printWriter = new PrintWriter(filename);
            printWriter.write("Happiness\nStruggle\nGrowth\nLove\nPurpose\nAdventure\nBalance\nReflection\nExperience\nGratitude\n");
            printWriter.close();

            //Read the words from the file
            ArrayList<String> words = readFile(filename);

            //Display the list of words
            printList(words);
            String choice;
            Scanner inp = new Scanner(System.in);

            //Prompt the user to add a new word with a loop
            do {
                System.out.print("DO you want to enter a new word? ");
                choice = inp.nextLine();


                if (choice.equalsIgnoreCase("yes")) {
                    addWord(words);
                } else if (choice.equalsIgnoreCase("no")) {
                    printList(words);
                    writeFile(words);
                }
            } while (!choice.equalsIgnoreCase("no"));
            inp.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //This method reads the words from the file
    private static ArrayList<String> readFile(String filename) {

        ArrayList<String> words = new ArrayList<>();

        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNextLine()) {
                String str = scan.nextLine();
                words.add(str);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return words;
    }

    // This method prints out each element of the list using a for-each loop.
    private static void printList(ArrayList<String> words) {
        for (String word : words) {
            System.out.println(word);
        }
    }

    /*
    *This method prompts the user to enter a new word, and if the word is valid,
    *it adds to the list. If the word is not valid, the method prints an error message.
    */
    private static void addWord(ArrayList<String> words) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a word: ");
        String word = scan.nextLine();
        try {
            if (checkWord(word, words)) {
                words.add(word);
            } else {
                System.out.println("The word already exits in the lists");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    *This method takes a word string and an ArrayList of words as arguments,
    * and returns true if the word is not already in the list,
    * and false otherwise. The method uses the indexOf() method to search for the word in the list
    * and returns true if the word is not found.
     */
    private static boolean checkWord(String word, ArrayList<String> words) {
        try {
            //Check if the word already exists in the words ArrayList by using indexOf() or find() methods,
            int index = words.indexOf(word);
            //returning true or false as is appropriate.
            return index <= 0 || index >= words.size();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /*This method writes the contents of the ArrayList to a file named "my_words.txt".
    *The method creates a new PrintWriter object to write to the file,
    *then uses a for-each loop to write each word on a separate line.
    * Finally, the method closes the writer.
    */
    private static void writeFile(ArrayList<String> words) {
        String outputFile = "my_words.txt";

        try {
            PrintWriter printWriter = new PrintWriter(new File(outputFile));
            for (String word : words) {
                printWriter.write(word + "\n");
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
