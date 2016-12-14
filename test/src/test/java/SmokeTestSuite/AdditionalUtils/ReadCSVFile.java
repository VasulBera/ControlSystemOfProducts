package SmokeTestSuite.AdditionalUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Salome on 14.12.2016.
 */
public class ReadCSVFile {

    public static String resultCSVFile;

    public static String readDataFromCSVFile()
    {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("D:\\CARSS.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        scanner.useDelimiter(",");

        while (scanner.hasNext())
        {
            resultCSVFile = scanner.next();
        }

        scanner.close();
        return resultCSVFile;
    }
}
