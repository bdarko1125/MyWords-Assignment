import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MyWords {
    public static void main(String[] args) {
        //a filename String that is the location and name of the file to be read.
        String filename = "word.text";

        //handle (catch) any Exceptions that are thrown
        try {
            PrintWriter printWriter = new PrintWriter(filename);
            printWriter.write("Happiness\nStruggle\nGrowth\nLove\nPurpose\nAdventure\nBalance\nReflection\nExperience\nGratitude\n");
            printWriter.close();

            ArrayList<String> words = readFile(filename);

            printList(words);
            String choice;
            Scanner inp = new Scanner(System.in);

            //Use a loop to ask if the user wants to add a new word?
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

    //readFile() method takes the filename String as a parameter and returns an ArrayList of Strings called words.
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

    // printList() method that takes the words ArrayList as a parameter and has no return value.
    private static void printList(ArrayList<String> words) {
        for (String word : words) {
            System.out.println(word);
        }
    }

    private static void addWord(ArrayList<String> words) {
        Scanner scan = new Scanner(System.in);
        //The method prompts the user for a word, and captures the input in a string called word
        System.out.println("Enter a word: ");
        String word = scan.nextLine();
        //handle (catch) any Exceptions that are thrown
        try {
            //Use an if statement to check if the word is valid or not by calling the checkWord() method
            // and passing in the word string
            //If checkWord() returns is true, then add it to the arrayList
            if (checkWord(word, words)) {
                words.add(word);
                //If checkWord() returns false, then output - "This word already exists in the list"
            } else {
                System.out.println("The word already exits in the lists");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //checkWord() method takes the word string and words ArrayList as parameters
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

    //writeFile() method that writes words of ArrayList to a text file called "my_words.txt
    private static void writeFile(ArrayList<String> words) {
        String outputFile = "my_words.txt";

        try {
            PrintWriter printWriter = new PrintWriter(new File(outputFile));
            //words on separate lines
            for (String word : words) {
                printWriter.write(word + "\n");
            }
            //close the writer
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
