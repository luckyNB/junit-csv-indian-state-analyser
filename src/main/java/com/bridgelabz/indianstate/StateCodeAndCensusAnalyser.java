package com.bridgelabz.indianstate;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StateCodeAndCensusAnalyser<T> {
    public static Integer counter = 0;

    public static String totalRecordAvailableInStateCode(int expected, String stateCodeCsvFilePath, String stateCodePojoClassPath) throws StateCensusAnalysisException {
        try {
            CsvToBean<StateCodePOJO> csvToBean = StateCodeAndCensusAnalyser.openCSVBuilder(stateCodeCsvFilePath, stateCodePojoClassPath);
            Iterator<StateCodePOJO> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCodePOJO csvUser = csvUserIterator.next();
                counter++;
            }
            if (expected == counter)
                return "HAPPY";
            else
                return "SAD";
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new StateCensusAnalysisException("Wrong Delimeter or Wrong Header");
        }
    }

    public static String totalRecordAvailableInStateCensus(int expected, String stateCensusFilePath, String stateCensusPojoClassPath) throws StateCensusAnalysisException {
        try {
            CsvToBean<StateCensusPOJO> csvToBean = StateCodeAndCensusAnalyser.openCSVBuilder(stateCensusFilePath, stateCensusPojoClassPath);
            Iterator<StateCensusPOJO> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCensusPOJO csvUser = csvUserIterator.next();
                counter++;
                System.out.println(csvUser.toString());
            }
            if (expected == counter)
                return "HAPPY";

            else
                return "SAD";
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new StateCensusAnalysisException("Wrong Delimeter or Wrong Header");
        }
    }

    public static <T> CsvToBean openCSVBuilder(String filename, String classname) throws StateCensusAnalysisException {
        int count = 0;
        // Iterator<T> csvDataIterator = null;
        CsvToBean<T> csvToBean;
        try {
            //   Class classTemp = Class.forName(classname);
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Class.forName(classname))
                    .withIgnoreLeadingWhiteSpace(true)

                    .build();
            return csvToBean;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new StateCensusAnalysisException("POJO class or qualified path is wrong");
        }
        return null;
    }

    public static void loadingCSVStatesDataInto(String stateCodeFilePath, String stateCodePojoClassPath, String stateCensusFilePath, String stateCensusPojoClassPath) throws StateCensusAnalysisException {
     try {

            CsvToBean<StateCodePOJO> csvToBeanStateCode = StateCodeAndCensusAnalyser.openCSVBuilder(stateCodeFilePath, stateCodePojoClassPath);
            Iterator<StateCodePOJO> csvStateCodeIterator = csvToBeanStateCode.iterator();
            List<StateCensusData> stateCensusDataList = new ArrayList<>();
            while (csvStateCodeIterator.hasNext()) {
                StateCodePOJO stateCodePOJO = csvStateCodeIterator.next();
                CsvToBean<StateCensusPOJO> csvToBeanStateCensus = StateCodeAndCensusAnalyser.openCSVBuilder(stateCensusFilePath, stateCensusPojoClassPath);
                Iterator<StateCensusPOJO> csvStateCensusIterator = csvToBeanStateCensus.iterator();

                while (csvStateCensusIterator.hasNext()) {
                    StateCensusPOJO stateCensusPOJO = csvStateCensusIterator.next();


                    if (stateCodePOJO.getState().contains(stateCensusPOJO.getState())) {

                        StateCensusData stateCensusData=new StateCensusData();
                        stateCensusData.setSrNo(stateCodePOJO.getSrNo());
                        stateCensusData.setState(stateCodePOJO.getState());
                        stateCensusData.setTIN(stateCodePOJO.getTIN());
                        stateCensusData.setStateCode(stateCodePOJO.getStateCode());
                        stateCensusData.setDensityPerSqKm(stateCensusPOJO.getDensityPerSqKm());
                        stateCensusData.setAreaInSqKm(stateCensusPOJO.getAreaInSqKm());
                        stateCensusData.setPopulation(stateCensusPOJO.getPopulation());
                        stateCensusDataList.add(stateCensusData);
                        break;

                    }

                }
               // System.out.println(stateCensusDataList.toString());
            }


            for (StateCensusData censusData:stateCensusDataList){
                System.out.println(censusData.toString());
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new StateCensusAnalysisException("POJO class or qualified path is wrong");

        }


    }


}
