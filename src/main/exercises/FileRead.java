package csula.cs4660.exercises;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

// Author Jose Virgen 9/2/2016

/**
 * Introduction Java exercise to read file
 */
public class FileRead {
    private int[][] numbers;
    /**
     * Read the file and store the content to 2d array of int
     * @param file read file
     */
    public FileRead(File file) {
        // TODO: read the file content and store content into numbers

        String filePath = "array.txt";


        FileReader inputFile = new FileReader(filePath);


        BufferedReader br = new BufferedReader(inputFile);
        ArrayList<int[]> rows = new ArrayList<>();
        int length = 0;


        try{

            String line = br.readLine();

            while( line != null){

                String[] tokens = br.readLine().split(" ");
                length = tokens.length;

                int[] row = new int[length];

                //Parsing Data and populating the rows
                for(int i = 0; i < length;i++){
                    row[i] = Integer.parseInt(tokens[i]);
                }
                rows.add(row);
            }

            numbers = new int[rows.size()][length];

            //Populate the 2D Array
            for (int i = 0; i < rows.size(); i++) {
                for (int j = 0; j < length; j++) {
                    int[] currRow = rows.get(i);
                    numbers[i][j] = currRow[j];
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Read the file assuming following by the format of split by space and next
     * line. Display the sum for each line and tell me
     * which line has the highest mean.
     *
     * lineNumber starts with 0 (programming friendly!)
     */

    public static void main()
    public int mean(int lineNumber) {

        int avg = 0;
        int sum = 0;

        for(int i = 0; i < numbers[lineNumber].length; i ++){
            sum += numbers[lineNumber][i];
        }
        avg = sum / numbers[lineNumber].length;





        return avg;
    }

    public int max(int lineNumber) {

        int maxValue = 0;

        for(int i = 0; i < numbers[lineNumber].length ;i++) {
            if (numbers[lineNumber][i] > maxValue) {
                maxValue = numbers[lineNumber][i];
            }

        }
            return maxValue;
    }

    public int min(int lineNumber) {

        int minValue = 0;

        for(int i = 0; i < numbers[lineNumber].length; i++) {
            if (numbers[lineNumber][i] < minValue) {
                minValue = numbers[lineNumber][i];
            }
        }
        return minValue;
    }

    public int sum(int lineNumber) {
        int sum = 0;

        for (int i = 0; i < numbers[lineNumber].length;i++){
            sum +=numbers[lineNumber][i];
        }
        return sum;

        return 0;
    }
}
