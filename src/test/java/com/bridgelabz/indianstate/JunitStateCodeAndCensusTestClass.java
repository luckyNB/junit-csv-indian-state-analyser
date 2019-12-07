package com.bridgelabz.indianstate;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class JunitStateCodeAndCensusTestClass {

    private static final String STATECODE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/main/resources/StateCode1.csv";
    private static final String STATECENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/StateCensusData.csv";
    private static final String stateCodeClassPath = "com.bridgelabz.indianstate.StateCodePOJO";
    private static final String stateCensusClassPath = "com.bridgelabz.indianstate.StateCensusPOJO";

    @Test
    public void givenStateCodeCSVData_Should_ReturnSizeOfRecordIfMatches_ReturnHappy() {
        try {
            int result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(STATECODE_CSV_FILE_PATH, stateCodeClassPath);
            Assert.assertEquals(37, result);
        } catch (StateCensusAnalysisException e) {

        }
    }

    @Test
    public void givenStateCSVCodeFileName_WhenIncorrect_Should_ThrowStateCensusAnalysisException() {
        int result = 0;
        try {
            result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(STATECODE_CSV_FILE_PATH, stateCodeClassPath);
            Assert.assertEquals(37, result);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals(StateCensusAnalysisException.ExceptionType.WRONG_FILE_TYPE, e.type);
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCSVCodeFile_WhenIncorrectFileType_Should_ThrowStateCensusAnalysisException() {
        try {
            int result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(STATECODE_CSV_FILE_PATH, stateCodeClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Please Enter valid File Name or File Type", e.getMessage());
        }

    }

    @Test
    public void givenStateCSVCodeFile_WhenIncorrectDelimeter_Should_ThrowStateCensusAnalysisException() {

        try {
            int result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(STATECODE_CSV_FILE_PATH, stateCodeClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals(StateCensusAnalysisException.ExceptionType.WRONG_DELIMETER_OR_WRONG_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCSVCodeFileIncorrectHeader_Should_ThroStateCensusAnalysisException() {
        try {
            int result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(STATECODE_CSV_FILE_PATH, stateCodeClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Wrong Delimeter or Wrong Header", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVData_Should_ReturnSizeOfRecord() throws StateCensusAnalysisException {
        int result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCensus(29, STATECENSUS_CSV_FILE_PATH, stateCensusClassPath);
        Assert.assertEquals(29, result);
    }

    @Test
    public void giventateCensusCSVFileName_WhenIncorrectFileName_Should_ThrowStateCensusAnalysisException() {
        try {
            int result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCensus(29, STATECENSUS_CSV_FILE_PATH, stateCensusClassPath);

        } catch (StateCensusAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giventateCensusCSVIncorrectDelimeterToStateCensus_Should_ThrowStateCensusAnalysisException() {
        try {
            int result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCensus(29, STATECENSUS_CSV_FILE_PATH, stateCensusClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals(StateCensusAnalysisException.ExceptionType.WRONG_DELIMETER_OR_WRONG_HEADER, e.type);

        }
    }

    @Test
    public void giventateCensusCSVIncorrectHeader_Should_ThrowStateCensusAnalysisException() {
        try {
            int result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCensus(29, STATECENSUS_CSV_FILE_PATH, stateCensusClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals(StateCensusAnalysisException.ExceptionType.WRONG_DELIMETER_OR_WRONG_HEADER, e.type);

        }
    }

    @Test
    public void sortedListOfStateCensusCSVDataByStateName_Should_AndamanandNicobarIslandsAsFirstStateName() {
        try {
            StateCodeAndCensusAnalyser<String> obj = new StateCodeAndCensusAnalyser<>();
            List<StateCensusPOJO> result = StateCodeAndCensusAnalyser.genericSort(STATECENSUS_CSV_FILE_PATH, stateCensusClassPath, "StateName",
                    "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/StateName.json");
            Assert.assertEquals("Andaman and Nicobar Islands", result.get(0).getStateName());
        } catch (StateCensusAnalysisException e) {

        }
    }

    @Test
    public void sortedListOfStateCensusCSVDataByPopulation_Should_UttarPradeshAsFirstStateName() {
        try {
            List<StateCensusPOJO> result = StateCodeAndCensusAnalyser.genericSort(STATECENSUS_CSV_FILE_PATH, stateCensusClassPath, "Population",
                    "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/population.json");
            Assert.assertEquals("Uttar Pradesh", result.get((result.size() - 1)).getStateName().trim());
        } catch (StateCensusAnalysisException e) {

        }
    }


    @Test
    public void sortedListOfStateCensusCSVDataByDensity_Should_BiharAsFirstStateName() {
        try {
            List<StateCensusPOJO> result = StateCodeAndCensusAnalyser.genericSort(STATECENSUS_CSV_FILE_PATH, stateCensusClassPath, "DensityPerSqKm",
                    "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/Density.json");
            Assert.assertEquals("Bihar", result.get((result.size() - 1)).getStateName().trim());
        } catch (StateCensusAnalysisException e) {

        }
    }

    @Test
    public void sortedListOfStateCensusCSVDataByAreaSqKm_Should_RajshthanAsFirstStateName() {
        try {
            List<StateCensusPOJO> result = StateCodeAndCensusAnalyser.genericSort(STATECENSUS_CSV_FILE_PATH, stateCensusClassPath, "AreaInSqKm",
                    "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/areaInSQKM.json");
            Assert.assertEquals("Rajasthan", result.get((result.size() - 1)).getStateName().trim());

        } catch (StateCensusAnalysisException e) {

        }
    }
}

