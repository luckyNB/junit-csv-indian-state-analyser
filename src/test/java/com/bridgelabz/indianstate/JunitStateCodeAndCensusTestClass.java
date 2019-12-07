package com.bridgelabz.indianstate;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class JunitStateCodeAndCensusTestClass {

    private static final String STATECODE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/main/resources/StateCode.csv";
    private static final String STATECENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/StateCensusData.csv";
    private static final String stateCodeClassPath = "com.bridgelabz.indianstate.StateCodePOJO";
    private static final String stateCensusClassPath = "com.bridgelabz.indianstate.StateCensusPOJO";

    @Test
    public void givenStateCodeCSVData_Should_ReturnSizeOfRecordIfMatches_ReturnHappy() {

        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37, STATECODE_CSV_FILE_PATH, stateCodeClassPath);

            Assert.assertEquals("HAPPY", result);
        } catch (StateCensusAnalysisException e) {

        }
    }

    @Test
    public void givenStateCSVCodeFileName_WhenIncorrect_Should_ThrowStateCensusAnalysisException() {
        String result = null;
        try {
            result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37, STATECODE_CSV_FILE_PATH, stateCodeClassPath);
            Assert.assertEquals("HAPPY", result);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Please Enter valid File Name or File Type", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCSVCodeFile_WhenIncorrectFileType_Should_ThrowStateCensusAnalysisException() {
        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37, STATECODE_CSV_FILE_PATH, stateCodeClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Please Enter valid File Name or File Type", e.getMessage());
        }

    }

    @Test
    public void givenStateCSVCodeFile_WhenIncorrectDelimeter_Should_ThrowStateCensusAnalysisException() {

        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37, STATECODE_CSV_FILE_PATH, stateCodeClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Wrong Delimeter or Wrong Header", e.getMessage());
        }

    }

    @Test
    public void givenStateCSVCodeFileIncorrectHeader_Should_ThroStateCensusAnalysisException() {
        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37, STATECODE_CSV_FILE_PATH, stateCodeClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Wrong Delimeter or Wrong Header", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVData_Should_ReturnSizeOfRecord() throws StateCensusAnalysisException {
        String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCensus(29, STATECENSUS_CSV_FILE_PATH, stateCensusClassPath);
        Assert.assertEquals("HAPPY", result);
    }

    @Test
    public void giventateCensusCSVFileName_WhenIncorrectFileName_Should_ThrowStateCensusAnalysisException() {
        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCensus(29, STATECENSUS_CSV_FILE_PATH, stateCensusClassPath);

        } catch (StateCensusAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giventateCensusCSVIncorrectDelimeterToStateCensus_Should_ThrowStateCensusAnalysisException() {
        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCensus(29, STATECENSUS_CSV_FILE_PATH, stateCensusClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Wrong Delimeter or Wrong Header", e.getMessage());

        }
    }

    @Test
    public void giventateCensusCSVIncorrectHeader_Should_ThrowStateCensusAnalysisException() {
        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCensus(29, STATECENSUS_CSV_FILE_PATH, stateCensusClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Wrong Delimeter or Wrong Header", e.getMessage());

        }
    }

    @Test
    public void sortedListOfStateCensusCSVDataByStateName_Should_AndamanandNicobarIslandsAsFirstStateName()   {
        StateCodeAndCensusAnalyser<String> obj = new StateCodeAndCensusAnalyser<>();
        List<StateCensusPOJO> result = StateCodeAndCensusAnalyser.genericSort(STATECENSUS_CSV_FILE_PATH, stateCensusClassPath, "StateName",
                "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/StateName.json");
        Assert.assertEquals("Andaman and Nicobar Islands", result.get(0).getStateName());
    }

    @Test
    public void sortedListOfStateCensusCSVDataByPopulation_Should_UttarPradeshAsFirstStateName() {
        List<StateCensusPOJO> result = StateCodeAndCensusAnalyser.genericSort(STATECENSUS_CSV_FILE_PATH, stateCensusClassPath, "Population",
                "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/population.json");
        Assert.assertEquals("Uttar Pradesh", result.get((result.size() - 1)).getStateName().trim());
    }


    @Test
    public void sortedListOfStateCensusCSVDataByDensity_Should_BiharAsFirstStateName() {
        List<StateCensusPOJO> result = StateCodeAndCensusAnalyser.genericSort(STATECENSUS_CSV_FILE_PATH, stateCensusClassPath, "DensityPerSqKm",
                "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/Density.json");
        Assert.assertEquals("Bihar", result.get((result.size() - 1)).getStateName().trim());

    }

    @Test
    public void sortedListOfStateCensusCSVDataByAreaSqKm_Should_RajshthanAsFirstStateName() {
        List<StateCensusPOJO> result = StateCodeAndCensusAnalyser.genericSort(STATECENSUS_CSV_FILE_PATH, stateCensusClassPath, "AreaInSqKm",
                "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/areaInSQKM.json");
        Assert.assertEquals("Rajasthan", result.get((result.size() - 1)).getStateName().trim());
    }
}

