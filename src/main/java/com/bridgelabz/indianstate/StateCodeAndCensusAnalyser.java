package com.bridgelabz.indianstate;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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


    public static HashMap<String, StateCensusPOJO> sortByKeyValue(HashMap<String, StateCensusPOJO> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, StateCensusPOJO>> list =
                new LinkedList<Map.Entry<String, StateCensusPOJO>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, StateCensusPOJO>>() {
            public int compare(Map.Entry<String, StateCensusPOJO> o1,
                               Map.Entry<String, StateCensusPOJO> o2) {
                return (o1.getKey()).compareTo(o2.getKey());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, StateCensusPOJO> temp = new LinkedHashMap<String, StateCensusPOJO>();
        for (Map.Entry<String, StateCensusPOJO> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static void writingStateCensusDataIntoJsonFile(String stateCensusFilePath, String stateCensusPojoClassPath) {

        try {
            Map<String, StateCensusPOJO> stringStateCensusPOJOMap = new HashMap();
            CsvToBean<StateCensusPOJO> csvToBean = StateCodeAndCensusAnalyser.openCSVBuilder(stateCensusFilePath, stateCensusPojoClassPath);
            Iterator<StateCensusPOJO> csvUserIterator = csvToBean.iterator();
            while (csvUserIterator.hasNext()) {
                StateCensusPOJO csvUser = csvUserIterator.next();
                stringStateCensusPOJOMap.put(csvUser.getStateName(), csvUser);
                counter++;
                System.out.println(csvUser.toString());
            }
            for (Map.Entry<String, StateCensusPOJO> en : stringStateCensusPOJOMap.entrySet()) {
                System.out.println("Key = " + en.getKey() +
                        ", Value = " + en.getValue());
            }

            System.out.println("sotreedgdsgdsssssssssssz$%^&*()_+");
            Map<String, StateCensusPOJO> sortedMap = sortByKeyValue((HashMap<String, StateCensusPOJO>) stringStateCensusPOJOMap);

            for (Map.Entry<String, StateCensusPOJO> en : sortedMap.entrySet()) {
                System.out.println("Key = " + en.getKey() +
                        ", Value = " + en.getValue());
            }

            boolean status = StateCodeAndCensusAnalyser.writingStateCensusDataIntoJsonFile(sortedMap);
            if (status){
                System.out.println("Data written into file successfully");
            }
        } catch (RuntimeException | StateCensusAnalysisException e) {
            e.printStackTrace();

        }

    }

    private static boolean writingStateCensusDataIntoJsonFile(Map<String, StateCensusPOJO> sortedMap) {
        List<StateCensusPOJO> list = new ArrayList<>();
        for (Map.Entry<String, StateCensusPOJO> en : sortedMap.entrySet()) {
            list.add(en.getValue());
        }

        Gson gson = new Gson();
        String json = gson.toJson(list);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/SampleJson.json");
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
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
