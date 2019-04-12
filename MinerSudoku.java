/* CS1101 – Intro to Computer Science
Instructor: [Akbar, Ceberio, or Villanueva]
Comprehensive Lab 2 – MinerSudoku!
Submitted by: [Kevin Rodriguez]
*/


import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class MinerSudoku {
    public static Scanner scnr;

    public static int[][] loadSudoku (String fileName)throws IOException {
        int rowNum = 0;
        String line;
        //int [] row = new int [9];
        int[][] sudoku = new int [9][9];
        File puzzle = new File (fileName);
        Scanner scnr = new Scanner(puzzle);

        while(scnr.hasNextLine()) {
            line = scnr.nextLine();

            String[] row = line.split(",");

            for(int col = 0; col < row.length; col++) {
                sudoku[rowNum][col] = Integer.parseInt(row[col]);


            }

            rowNum = rowNum + 1;

        }

        return sudoku;

    }


    public static boolean isValidInput (int[][] sudoku, int row, int col, int number) {
        Boolean isValid = true;

        //calling the method openSpot to check if the number if that area is a zero
        if (!openSpot(sudoku, row, col)) {
            isValid = false;
            return isValid;
        }

        //checking if that row has the value of the number the user input
        for(int i = 0; i<9; i++) {
            if(number == sudoku[row][i]) {
                isValid = false;
                return isValid;
            }

        }

        //checking if that column has the value of the number the user input
        for(int i = 0; i<9; i++) {
            if(number == sudoku[i][col]) {
                isValid = false;
                return isValid;
            }
        }

        //calling the method isValid3x3 to see if the number does not appear the 3x3 square.
        if(!isValid3x3(sudoku, row, col, number)) {
            isValid = false;
            return isValid;
        }

        return isValid;

    }

    //check the specific 3x3 square
    public static boolean isValid3x3 (int[][] sudoku, int row, int col, int number) {
        Boolean isValid = true;

        //making the row and col to the first position of the specific sub-square.
        int subTopCol = (col/3 * 3);
        int subTopRow = (row/3 * 3);


        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if (sudoku[subTopRow + i][subTopCol + j] == number) {
                    isValid = false;
                }

            }
        }

        return isValid;

    }


    public static boolean openSpot (int[][] sudoku, int row, int col) {
        Boolean isValid = false;

        if(sudoku[row][col] == 0) {
            isValid = true;
        }

        return isValid;

    }

    public static void printSudoku(int[][] sudoku){

        for(int i=0; i<9; i++) {
            System.out.print(" | ");
            for(int j=0; j<9; j++) {
                System.out.print(sudoku[i][j] + " | ");
            }
            System.out.println();
        }



    }

    //checks if the puzzle is completed
    public static boolean isComplete (int[][] sudoku) {
        boolean noZeros = true;

        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++){
                if(sudoku[i][j] == 0) {
                    noZeros = false;
                }
            }
        }

        return noZeros;

    }


    public static void main(String[] args)throws IOException {
        Scanner scnr = new Scanner (System.in);
        String menu = "1. Load Sudoku \n 2. Play sudoku \n 3. Exit";
        String menu2 = "2.Play \n 3. Exit";
        int answer;
        String fileName ="";
        int row;
        int col;
        int number;
        int [][] sudoku1 = new int[9][9];
        boolean isValidInput = false;
        boolean openSpot = false;
        boolean isValid3x3 = false;
        boolean isComplete = false;
        boolean shouldExit = false;
        int option;


        System.out.println(menu);
        answer = scnr.nextInt();

        while (!shouldExit) {
            if (answer == 1) {
                System.out.println("Please enter the name of the file: ");
                fileName = scnr.next();
                sudoku1 = loadSudoku(fileName); //returns array

                System.out.println(menu);
                answer = scnr.nextInt();

            }
            else if(answer == 2) {

                while (answer == 2 && isComplete == false) {
                    printSudoku(sudoku1);

                    isComplete = isComplete(sudoku1);

                        System.out.println("Please enter the row you want to access: ");
                        row = (scnr.nextInt() - 1);

                        System.out.println("Please enter the column you want to access: ");
                        col = (scnr.nextInt() - 1);

                        System.out.println("Please enter the desired number: ");
                        number = scnr.nextInt();

                        isValidInput = isValidInput(sudoku1, row, col, number);


                        if (isValidInput == true) {
                            sudoku1[row][col] = number;
                        } else
                            System.out.println("Number is invalid");

                        if(isComplete){
                            System.out.println("Congratulations! You have solved the puzzle!");

                         }

                        System.out.println(menu2);
                        answer = scnr.nextInt();

                }
            }
            else if(answer == 3) {
                System.out.println("Goodbye!");
                shouldExit = true;

            }

        }
    }
}
