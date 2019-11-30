package com.bridgelabz.indianstate;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCodeAndCensusAnalyser {
    private static final String STATECODE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/main/resources/StateCode.csv";
    private static final String STATECENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/StateCensusData.csv";


    public static Integer counter;

    public static String totalRecordAvailableInStateCode(int expected) throws StateCensusAnalysisException {
        try {

            CsvToBean<StateCodePOJO> csvToBean = StateCodeAndCensusAnalyser.OpenCSVBuilder("STATECODE_CSV_FILE_PATH","com.bridgelabz.StateCodePOJO");
            Iterator<StateCodePOJO> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCodePOJO csvUser = csvUserIterator.next();
                counter++;
                System.out.println(csvUser.toString());
            }
            if (expected == counter)
                return "HAPPY";

            else
                return "SAD";
        }  catch (RuntimeException e) {
            e.printStackTrace();
            throw new StateCensusAnalysisException("Wrong Delimeter or Wrong Header");
        }

    }


    public static String totalRecordAvailableInStateCensus(int expected) throws StateCensusAnalysisException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(STATECENSUS_CSV_FILE_PATH));
            CSVReader csvReader = new CSVReader(reader);
            CsvToBean<StateCodePOJO> csvToBean = new CsvToBeanBuilder(reader).withType(StateCodePOJO.class).withIgnoreLeadingWhiteSpace(true).build();
            Iterator<StateCodePOJO> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCodePOJO csvUser = csvUserIterator.next();
                counter++;
                System.out.println(csvUser.toString());
            }
            if (expected == counter)
                return "HAPPY";

            else
                return "SAD";
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalysisException("Please Enter valid File Name or File Type");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new StateCensusAnalysisException("Wrong Delimeter or Wrong Header");
        }
        return null;
    }

    public static  <T> CsvToBean OpenCSVBuilder(String filename, String classname) {
        int count = 0;
        Iterator<T> csvDataIterator = null;
        CsvToBean<T> csvToBean;
        try {
            Class classTemp = Class.forName(classname);
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Class.forName(classname))
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            //csvDataIterator = csvToBean.iterator();
            return csvToBean;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
