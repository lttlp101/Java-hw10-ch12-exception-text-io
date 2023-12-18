# Java Intro hw10

Java Intro assignment 10

## Description

#### Chapter 12 - Exception handling and Text I/O

This Java program include the following:
<br> To process some student score data. This program will include several methods:

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


## Built With

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

## License

![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)
<hr>