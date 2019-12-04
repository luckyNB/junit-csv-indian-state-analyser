package com.bridgelabz.indianstate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

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

    public static List<StateCensusPOJO> sortingStateCensusDataByStateName(List<StateCensusPOJO> list) {
        list.sort(Comparator.comparing(StateCensusPOJO::getStateName, Comparator.nullsFirst(Comparator.naturalOrder())));
        return list;
    }


    public static List<StateCensusPOJO> sortingStateCensusDataByPopulation(List<StateCensusPOJO> list) {
        Comparator<StateCensusPOJO> c = (s2, s1) -> Integer.parseInt(s2.getPopulation().trim()) - Integer.parseInt(s1.getPopulation().trim());
        list.sort(c);
        return list;
    }
    public static List<StateCensusPOJO> sortingStateCensusDataByDesity(List<StateCensusPOJO> list) {
        Comparator<StateCensusPOJO> c = (s1, s2) -> Integer.parseInt(s2.getDensityPerSqKm().trim()) - Integer.parseInt(s1.getDensityPerSqKm().trim());
        list.sort(c);
        return list;
    }

    public static int writingAndSortingStateCensusDataIntoJsonFile(String stateCensusFilePath, String stateCensusPojoClassPath) {
        List<StateCensusPOJO> list = new ArrayList<>();
        try {

            CsvToBean<StateCensusPOJO> csvToBean = StateCodeAndCensusAnalyser.openCSVBuilder(stateCensusFilePath, stateCensusPojoClassPath);
            Iterator<StateCensusPOJO> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCensusPOJO csvUser = csvUserIterator.next();
                list.add(csvUser);
                counter++;
                System.out.println(csvUser.toString());
            }
            List<StateCensusPOJO> sortedList = sortingStateCensusDataByStateName(list);
            boolean status = StateCodeAndCensusAnalyser.writingStateCensusDataIntoJsonFile(list,"/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/SampleJson.json");
            if (status) {
                System.out.println("Data written into file successfully");
            }
        } catch (RuntimeException | StateCensusAnalysisException e) {
            e.printStackTrace();
        }

        return (list.size());
    }

    private static boolean writingStateCensusDataIntoJsonFile(List<StateCensusPOJO> list,String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    public static int sortingStateCensusDataByPopulationInAscendingOrder(String stateCensusFilePath, String stateCensusPojoClassPath){
        List<StateCensusPOJO> list = new ArrayList<>();
        try {

            CsvToBean<StateCensusPOJO> csvToBean = StateCodeAndCensusAnalyser.openCSVBuilder(stateCensusFilePath, stateCensusPojoClassPath);
            Iterator<StateCensusPOJO> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCensusPOJO csvUser = csvUserIterator.next();
                list.add(csvUser);
                counter++;
                System.out.println(csvUser.toString());
            }
            List<StateCensusPOJO> sortedList = sortingStateCensusDataByPopulation(list);
            boolean status = StateCodeAndCensusAnalyser.writingStateCensusDataIntoJsonFile(sortedList,"/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/population.json");
            if (status) {
                System.out.println("Data written into file successfully");
            }

        }
        catch (RuntimeException | StateCensusAnalysisException e){
            e.printStackTrace();
        }

        return (list.size());
    }


    public static int sortingStateCensusDataByDensityInDescendingOrder(String stateCensusFilePath, String stateCensusPojoClassPath){
        List<StateCensusPOJO> list = new ArrayList<>();
        try {

            CsvToBean<StateCensusPOJO> csvToBean = StateCodeAndCensusAnalyser.openCSVBuilder(stateCensusFilePath, stateCensusPojoClassPath);
            Iterator<StateCensusPOJO> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCensusPOJO csvUser = csvUserIterator.next();
                list.add(csvUser);
                counter++;
                System.out.println(csvUser.toString());
            }
            List<StateCensusPOJO> sortedList = sortingStateCensusDataByDesity(list);
            boolean status = StateCodeAndCensusAnalyser.writingStateCensusDataIntoJsonFile(sortedList,"/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/Density.json");
            if (status) {
                System.out.println("Data written into file successfully");
            }

        }
        catch (RuntimeException | StateCensusAnalysisException e){
            e.printStackTrace();
        }

        return (list.size());
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
        Iterator<T> csvDataIterator = null;
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
}
