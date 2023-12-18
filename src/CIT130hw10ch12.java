
//
// ------------------------------------------
/*
    @brief This Program:
    This Java program include the following:
    To process some student score data. This program will include several methods:

        Method 1: A method to ask for a pass code to enter the program. Make sure that the user enters
            a whole number. In order to do so, you will need exception handling statements to make sure the
            user enters a valid whole number. For example, entering abc or 12.8 will all be considered as
            invalid. The method should return the validated pass code.

       Method 2: A method that reads the name and test score (test score can be an integer or
            decimal) for each student into 2 arraylists, one for name, one for the test score from a file
            named INPUTDATA.txt. This method will return the number of students read from the file. I
            have included a file named INPUTDATA.txt for testing your program. Note that the demo files
            under the weekly modules for chapter 12 includes examples of how to read data from a file. Of
            course, your program must be able to read data from any file with the structure specified by the
            provided sample input file.

       Method 3: A method that calculates and returns the total score. This method WILL have a return statement.

       Method 4: A method that calculates and returns the average score. This method should call the
            method 3 that finds the total. Average is just the total divided by the number of students. This
            method WILL have a return statement.

       Method 5: A method that returns the highest test score. This method WILL have a return statement.

       Method 6: A method to calculate letter grades based on the following criteria: (NOTE: this
            method does not return a value, it simply displays the letter grade). This method will have as its
            parameters, the score, the grade, number of students and the highest grade. Note that you will
            create a new arraylist to hold the letter grade for each student. This method WILL NOT have a
            return statement.

                A – when score is >= best score - 10
                B – when score is >= best score - 20
                C – when score is >= best score - 30
                D – when score is >= best score - 40
                F – Otherwise.

       Method 7: A method that displays the name, test score, and letter grade for each student, the
            total score, the average score, and the highest score to the monitor and to an output file. Ask the
            user for the file name inside this method. This method WILL NOT have a return statement.

       NOTE: Display test scores, total score, average score, and the highest score with 2 digits after
       the decimal point.

       Call your methods in your main method. If the pass code entered by the user is not 1234,
       issue an error message and do not perform any action.

 */
//
// ------------------------------------------
//
//
//


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@SuppressWarnings("java:S106") // Disable the warning about System.out from SonarLint

public class CIT130hw10ch12 {

    // Create the scanner object to take inputs
    public static final Scanner inputs = new Scanner(System.in);

    public static void main(String[] args) {
        // Display the assignment title
        System.out.println("\nAssignment 10 \t\tChapter 12 \tException Handling and Text I/O \n");

        /*
        =====================================================

        WARNING!!!!!!!:
            WHEN TESTING,
                CHANGE FILE PATHS
                    FOR BOTH INPUT AND OUTPUT FILES!!!!

                    "filePath" and "outputParentPath"

        =====================================================
         */


        // Define the file path, should change when needed!
        // NEED CHANGE
        String filePath = ".\\src\\INPUTDATA.txt";

        // Initiate ArrayLists variables (names, testScores, and grades)
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Double> testScores = new ArrayList<>();
        ArrayList<Character> grades = new ArrayList<>();

        // Call method 1 - Passcode validation
        // Get method 1 passcode validation boolean value, if it is false then exit the program.
        if (!passCodeValidation()) {
            System.out.println("Invalid pass code - program is terminated!!\n");
            return;
        }

        // Call method 2 - Read Names and Test Scores from INPUTDATA.txt file
        // Pass the referents of the ArrayLists(names and testScores) and the filePath
        // Then return the number of students read from the file.
        int numOfStudents = readInputDataFile(names, testScores, filePath);

        // Call method 3 - Calculate Total Score
        // Pass the referent of the ArrayLists(testScores)
        // Then return the total score of all students read from the file.
        double totalScore = calculateTotalScore(testScores);

        // Call method 4 - Calculate Average Score
        // Pass the referents of the ArrayLists(testScores) and the Number of student
        // Call method 3 - Calculate Total Score, Inside the method 4
        // Then return the average score of all students read from the file.
        double averageScore = calculateAverageScore(testScores, numOfStudents);

        // Call method 5 - Get Highest Test Score
        // Pass the referent of the ArrayLists(testScores)
        // Then return the best score (highest score) of all students read from the file.
        double bestScore = getHighestScore(testScores);

        // Call method 6 - Calculate Letter Grade
        // Pass the referents of the ArrayLists(testScores and grades) and the value of bestScore
        // Then Calculate the letter grade for each score based on the criteria
        // Then create a new ArrayList for letter grades with new content with letter grades
        calculateLetterGrade(testScores, bestScore, grades);

        // Call method 7 - Display and Output File
        // Pass the referents of the ArrayLists(names, testScores and grades)
        //            and the values of (totalScore, averageScore and bestScore)
        // Ask user for output file path and name
        // Output the formatted data to monitor and to an output file.
        displayAndOutputFile(names, testScores, grades, totalScore, averageScore, bestScore);



    } // main


    // Methods

    // Method 1
    // Method 1 - Passcode validation
    ///passCodeValidation - To ask for a pass code to enter the program.
    ///                          - Use exception handling statements to make sure
    ///                          -     the user enters a valid whole number.
    ///@param - None
    ///return: a boolean value for pass code validation
    public static boolean passCodeValidation() {

        // Pass code 1234
        final int PASSKEY = 1234;
        // Initiate a boolean value for isValid for the do-while loop
        boolean isValid = true;

        do {
            // Try-Catch for Exception handling
            try {
                // Get the input for passcode
                System.out.println("Enter the pass code: ");
                int passcode = Integer.parseInt(inputs.next());

                // If the passcode matches the passkey, then return True,
                // if not, issue message and exit loop
                if (passcode == PASSKEY) {
                    System.out.println("\nWelcome! \n");
                    return true;
                } else {
                    System.out.println("Invalid integer pass code. ");
                    break;
                }
            } // try

            // Catch the exceptions
            catch (InputMismatchException | NumberFormatException ex) {
                System.out.println("Try again!! (Incorrect input: an integer is required!) ");
                isValid = false;
            }

        } while (!isValid);

        return false;

    } // method1 passcode validation



    // Method 2
    // Method 2 - Read Names and Test Scores from INPUTDATA.txt file
    ///readInputDataFile - Reads the name and test score (test score can be an integer
    ///                         -     or decimal) for each student into 2 arraylists,
    ///                         -     one for name, one for the test score from a file named INPUTDATA.txt.
    ///                         - Return the number of students read from the file.
    ///@param - names [ArrayList<String>] the ArrayList for student names
    ///@param - scores [ArrayList<Double>] the ArrayList for student test scores
    ///@param - filePath [String] the file path for the INPUTDATA.txt file
    ///return: the integer number of the number of students read from the file
    public static int readInputDataFile(List<String> names,
                                               List<Double> scores,
                                               String filePath) {

        // Create a File instance using file path for INPUTDATA.txt
        File file = new File(filePath);

        // Try-Catch for Exception handling
        try {
            // Create a Scanner for the file
            Scanner input = new Scanner(file);

            // Read data from a file until the end
            while (input.hasNext()) {
                // Read the name
                String name = input.next();
                // Capitalize the 1st letter the name
                String firstName = name.substring(0, 1).toUpperCase() + name.substring(1);
                // Read the test score
                double testScore = Double.parseDouble(input.next());

                // Add name and test score on each the ArrayLists
                names.add(firstName);
                scores.add(testScore);

            }

            // Close the file
            input.close();

        } // try

        // Catch the Exception (file not found) then Exit the program
        catch (FileNotFoundException ex) {
            System.out.println("Can't find your input file!");
            System.exit(0);
        }

        // return the number of the students from read file
        return names.size();

    } // method2 read input data file



    // Method 3
    // Method 3 - Calculate Total Score
    ///calculateTotalScore - Calculates and returns the total score.
    ///@param - scores [ArrayList<Double>] the ArrayList for student test scores
    ///return: the double type of the number of total score
    public static double calculateTotalScore(List<Double> scores) {

        // Initiate the total score
        double totalScore = 0.0;

        // For-Each loop culminate scores to get the total score
        for (double score : scores) {
            totalScore += score;
        }

        // Return the total score
        return totalScore;

    } // method3 calculate total score



    // Method 4
    // Method 4 - Calculate Average Score
    ///calculateAverageScore - Calculates and returns the average score.
    ///@param - scores [ArrayList<Double>] the ArrayList for student test scores
    ///@param - num [int] total number of the students
    ///return: the double type of the number of average score
    public static double calculateAverageScore(List<Double> scores, int num) {

        // Call the method 3 method3CalculateTotalScore that finds the total,
        // Then return the value which after the total score divide the number of students
        return calculateTotalScore(scores) / num;

    } // method4 calculate average score



    // Method 5
    // Method 5 - Get Highest Test Score
    ///getHighestScore - Returns the highest test score.
    ///@param - scores [ArrayList<Double>] the ArrayList for student test scores
    ///return: the double type of the value of the highest test score
    public static double getHighestScore(List<Double> scoreList) {

        // Use Max method in the Collections class to find the highest score
        // Then return the value
        return Collections.max(scoreList);

    } // method5 get  the highest test score



    // Method 6
    // Method 6 - Calculate Letter Grade
    ///method6CalculateLetterGrade - Calculate letter grades based on the criteria for each score
    ///                            - Create a new ArrayList to hold the letter grade for each student.
    ///@param - scores [ArrayList<Double>] the ArrayList for student test scores
    ///@param - bestScore [double] the highest score
    ///@param - grades [ArrayList<Character>] the ArrayList for letter grades
    ///return: None
    public static void calculateLetterGrade(List<Double> scores,
                                                   double bestScore,
                                                   List<Character> grades) {

        for (double score : scores) {

            // Add letter grade based on the criteria for each score onto the grades ArrayList
            if (score >= (bestScore - 10)) {
                grades.add('A');
            } else if (score >= (bestScore - 20)) {
                grades.add('B');
            } else if (score >= (bestScore - 30)) {
                grades.add('C');
            } else if (score >= (bestScore - 40)) {
                grades.add('D');
            } else {
                grades.add('F');
            }

        }

    } // method6 calculate letter grade



    // Method 7
    // Method 7 - Display and Output File
    ///displayAndOutputFile - Displays the name, test score, and letter grade for each student,
    ///                            -     the total score, the average score, and the highest score to the monitor
    ///                            -     and to an output file.
    ///                            - Display test scores, total score, average score, and the highest score
    ///                            -      with 2 digits after the decimal point.
    ///                            - Ask the user for the file name inside this method.
    ///@param - names [ArrayList<String>] the ArrayList for student names
    ///@param - testScores [ArrayList<Double>] the ArrayList for student test scores
    ///@param - grades [ArrayList<Character>] the ArrayList for letter grades
    ///@param - totalScore [double] the total score
    ///@param - averageScore [double] the average score
    ///@param - bestScore [double] the highest score
    ///return: None
    public static void displayAndOutputFile(List<String>names,
                                                   List<Double>testScores,
                                                   List<Character>grades,
                                                   double totalScore,
                                                   double averageScore,
                                                   double bestScore) {

        // Create a Scanner Instance
        Scanner inputs = new Scanner(System.in);

        // Define the parent path for the output file, change needed!
        // NEED CHANGE
        String outputParentPath = ".\\src";

        // Ask user for the new child output file name path
        System.out.println("Enter a file name to write to: ");
        String outputFilePath = inputs.nextLine();

        // Try-Catch for Exception handling
        try {

            // Create the output file path with parent path and child path
            File outputFile = new File(outputParentPath, outputFilePath);

            // Check if the file exist
            if (outputFile.exists()) {
                System.out.println("File already exists!");
                System.exit(0);
            }


            // Create a file, auto close file after writing it
            // Try method (can auto close file)
            try (
                    // Create an output file
                    PrintWriter outputs = new PrintWriter(outputFile)

            ) {
                // Write formatted output to the file

                outputs.printf("%nHighest Score = %.2f %n", bestScore);
                outputs.printf("Total Score = %.2f %n", totalScore);
                outputs.printf("Average Score = %.2f %n%n", averageScore);

                for (int i = 0; i < names.size(); i++) {
                    outputs.printf("%-4s\t%.2f \t %c %n", names.get(i), testScores.get(i), grades.get(i));
                }

            }

        } // try

        // Catch Exceptions
        catch (IOException ex) {
            System.out.println("An error occurred.");
            System.exit(0);
        }


        // Display the formatted output to monitor

        System.out.printf("%nHighest Score = %.2f %n", bestScore);
        System.out.printf("Total Score = %.2f %n", totalScore);
        System.out.printf("Average Score = %.2f %n%n", averageScore);

        for (int i = 0; i < names.size(); i++) {
            System.out.printf("%-4s\t%.2f \t %c %n", names.get(i), testScores.get(i), grades.get(i));
        }

        // Display a message prompting to look for new file
        System.out.printf("%nLook at your folder path: ( %s\\ ) for a file named: ( %s ) %n",
                outputParentPath,
                outputFilePath);


    } // method7 display and output file


}
