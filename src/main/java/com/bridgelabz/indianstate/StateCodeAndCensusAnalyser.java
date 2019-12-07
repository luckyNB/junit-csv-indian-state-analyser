package com.bridgelabz.indianstate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

public class StateCodeAndCensusAnalyser<T extends Comparable<T>> {
    public static <T> CsvToBean openCSVBuilder(String filename, String classname) throws StateCensusAnalysisException {
        int count = 0;
        Iterator<T> csvDataIterator = null;
        CsvToBean<T> csvToBean;
        try {
            //   Class classTemp = Class.forName(classname);
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Class.forName(classname))
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(',')
                    .build();
            return csvToBean;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new StateCensusAnalysisException("POJO class or qualified path is wrong");
        }
        return null;
    }

    public static int totalRecordAvailableInStateCode(String stateCodeCsvFilePath, String stateCodePojoClassPath) throws StateCensusAnalysisException {
        int counter = 0;
        try {
            CsvToBean<StateCodePOJO> csvToBean = StateCodeAndCensusAnalyser.openCSVBuilder(stateCodeCsvFilePath, stateCodePojoClassPath);
            Iterator<StateCodePOJO> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCodePOJO csvUser = csvUserIterator.next();
                counter++;
            }
            return counter;
        }
        catch (RuntimeException e) {
            throw new StateCensusAnalysisException(StateCensusAnalysisException.ExceptionType.WRONG_FILE_TYPE, "Wrong Delimeter or Wrong Header");
        }

    }

    public static int writingAndSortingStateCensusDataIntoJsonFile(String stateCensusFilePath, String stateCensusPojoClassPath) throws StateCensusAnalysisException {
        List<StateCensusPOJO> list = new ArrayList<>();
        int counter = 0;
        try {
            CsvToBean<StateCensusPOJO> csvToBean = StateCodeAndCensusAnalyser.openCSVBuilder(stateCensusFilePath, stateCensusPojoClassPath);
            Iterator<StateCensusPOJO> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCensusPOJO csvUser = csvUserIterator.next();
                list.add(csvUser);
                counter++;
                System.out.println(csvUser.toString());
            }
            boolean status = StateCodeAndCensusAnalyser.writingStateCensusDataIntoJsonFile(list, "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/SampleJson.json");
        } catch (RuntimeException | StateCensusAnalysisException | IOException e) {
            throw new StateCensusAnalysisException(StateCensusAnalysisException.ExceptionType.WRONG_DATA_FORMAT, "Wrong Data format");
        }
        return (list.size());
    }

    private static boolean writingStateCensusDataIntoJsonFile(List<StateCensusPOJO> list, String filePath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(json);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            fileWriter.close();
        }
        return true;
    }

    public static int totalRecordAvailableInStateCensus(int expected, String stateCensusFilePath, String stateCensusPojoClassPath) throws StateCensusAnalysisException {
        int counter = 0;
        try {
            CsvToBean<StateCensusPOJO> csvToBean = StateCodeAndCensusAnalyser.openCSVBuilder(stateCensusFilePath, stateCensusPojoClassPath);
            Iterator<StateCensusPOJO> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCensusPOJO csvUser = csvUserIterator.next();
                counter++;
                System.out.println(csvUser.toString());
            }
            return counter;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new StateCensusAnalysisException(StateCensusAnalysisException.ExceptionType.WRONG_DELIMETER_OR_WRONG_HEADER, "Wrong Delimeter or Wrong Header");
        }
    }

    public static List<StateCensusPOJO> genericSort(String stateCensusFilePath, String stateCensusPojoClassPath, String onTheBasisOf, String jsonFilePath) throws StateCensusAnalysisException {
        int counter = 0;
        List<StateCensusPOJO> list = new ArrayList<>();
        try {
            CsvToBean<StateCensusPOJO> csvToBean = StateCodeAndCensusAnalyser.openCSVBuilder(stateCensusFilePath, stateCensusPojoClassPath);
            Iterator<StateCensusPOJO> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCensusPOJO csvUser = csvUserIterator.next();
                list.add(csvUser);
                counter++;
            }
            Collections.sort(list, new Comparator<StateCensusPOJO>() {
                @Override
                public int compare(StateCensusPOJO o1, StateCensusPOJO o2) {
                    try {
                        Field stateCensusField = StateCensusPOJO.class.getDeclaredField(onTheBasisOf);
                        stateCensusField.setAccessible(true);
                        Comparable stateCensusFieldValue1 = (Comparable) stateCensusField.get(o1);
                        Comparable stateCensusFieldValue2 = (Comparable) stateCensusField.get(o2);
                        return stateCensusFieldValue1.compareTo(stateCensusFieldValue2);
                    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                        // when proper field is not entered sorting or any exception occurs
                        try {
                            throw new StateCensusAnalysisException(StateCensusAnalysisException.ExceptionType.NO_PROPER_FIELD, "Proper field is not given ");
                        } catch (StateCensusAnalysisException ex) {
                            ex.printStackTrace();
                        }
                        return 0;
                    }
                }
            });
            StateCodeAndCensusAnalyser.writingStateCensusDataIntoJsonFile(list, jsonFilePath);
        } catch (RuntimeException | StateCensusAnalysisException | IOException e) {
            throw new StateCensusAnalysisException(StateCensusAnalysisException.ExceptionType.NO_PROPER_FIELD, "Proper field is not given");
        }
        return list;
    }

}
