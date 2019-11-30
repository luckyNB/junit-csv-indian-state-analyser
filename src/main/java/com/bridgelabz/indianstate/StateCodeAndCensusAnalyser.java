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
    private static final String STATECODE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/main/resources/StateCode1.csv";

    public static int counter;

    public static String totalRecordAvailableInStateCode(int expected) throws StateCensusAnalysisException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(STATECODE_CSV_FILE_PATH));
            CSVReader csvReader = new CSVReader(reader);
            CsvToBean<StateCodePOJO> csvToBean = new CsvToBeanBuilder(reader).withType(StateCodePOJO.class).withIgnoreLeadingWhiteSpace(true).build();
            Iterator<StateCodePOJO> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCodePOJO csvUser = csvUserIterator.next();
                counter++;
            }
            if (expected == counter)
                return "HAPPY";

            else
                return "SAD";
        }
        catch (NoSuchFileException e){
            throw  new StateCensusAnalysisException("Please Enter valid File Name or File Type");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
