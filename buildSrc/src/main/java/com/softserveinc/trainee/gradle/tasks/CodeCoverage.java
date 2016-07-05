package com.softserveinc.trainee.gradle.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import java.io.*;
import java.util.Properties;

public class CodeCoverage extends DefaultTask{

    private static final String PATH_TO_TEST_REPORT_FILE = "service/build/reports/jacoco/test/jacocoTestReport.csv";
    private static final String PATH_TO_TEST_PROPERTIES_FILE = "service/src/main/resources/test.properties";
    private static final String CODE_COVERAGE_KEY_PROPERTIES = "percentCodeCoverage";

    @TaskAction
    public void findPercentOfCodeCoverage(){
        int allMissedInstruction = 0;
        int allCoveredInstructed = 0;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(PATH_TO_TEST_REPORT_FILE)))){
            String line = bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null){
                String[] arg = line.split(",");
                int missed = Integer.parseInt(arg[3]);
                int covered = Integer.parseInt(arg[4]);
                allMissedInstruction += missed;
                allCoveredInstructed += covered;
            }
            } catch (IOException e) {
                throw new GradleException("Coundn't read test report file.");
            }
        int actuallyPercentCodeCoverage = (int) Math.round(((double)allCoveredInstructed/(allMissedInstruction + allCoveredInstructed))*100);

        Properties properties = new Properties();
        try(InputStream inputStream = new FileInputStream(PATH_TO_TEST_PROPERTIES_FILE)){
            properties.load(inputStream);
        } catch (IOException e) {
            throw new GradleException("Coundn't load properties file for tests");
        }
        int expectedPercentCodeCoverage = Integer.parseInt(properties.getProperty(CODE_COVERAGE_KEY_PROPERTIES));
        if(expectedPercentCodeCoverage > actuallyPercentCodeCoverage) {
            throw new GradleException("Code coverage failed. Expected = " + expectedPercentCodeCoverage + "%, Actually = " + actuallyPercentCodeCoverage + "%");
        }else {
            System.out.println("Passed Code Coverage Checks");
        }
    }
}
