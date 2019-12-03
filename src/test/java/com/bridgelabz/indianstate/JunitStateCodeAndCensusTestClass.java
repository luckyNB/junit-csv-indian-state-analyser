package com.bridgelabz.indianstate;

import org.junit.Assert;
import org.junit.Test;

public class JunitStateCodeAndCensusTestClass {

    private static final String STATECODE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/main/resources/StateCode.csv";
    private static final String STATECENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/junit-csv-indian-state-analyzer/src/test/resources/StateCensusData.csv";
    private static final String stateCodeClassPath = "com.bridgelabz.indianstate.StateCodePOJO";
    private static final String stateCensusClassPath = "com.bridgelabz.indianstate.StateCensusPOJO";

    @Test
    public void givenStateCodeCSVData_Should_ReturnSizeOfRecord() throws StateCensusAnalysisException {
        StateCodeAndCensusAnalyser.loadingCSVStatesDataInto(STATECODE_CSV_FILE_PATH, stateCodeClassPath, STATECENSUS_CSV_FILE_PATH, stateCensusClassPath);
        String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37, STATECODE_CSV_FILE_PATH, stateCodeClassPath);

        Assert.assertEquals("HAPPY", result);
    }

    @Test
    public void givenFileName_WhenIncorrect_Should_ThrowStateCensusAnalysisException() {
        String result = null;
        try {
            result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37, STATECODE_CSV_FILE_PATH, stateCodeClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Please Enter valid File Name or File Type", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenFile_WhenIncorrectFileType_Should_ThrowStateCensusAnalysisException() {
        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37, STATECODE_CSV_FILE_PATH, stateCodeClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Please Enter valid File Name or File Type", e.getMessage());
        }

    }

    @Test
    public void givenIncorrectDelimeter_Should_ThrowStateCensusAnalysisException() {

        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37, STATECODE_CSV_FILE_PATH, stateCodeClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Wrong Delimeter or Wrong Header", e.getMessage());
        }

    }

    @Test
    public void givenIncorrectHeader_Should_ThroStateCensusAnalysisException() {
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
    public void givenFileName_WhenIncorrectFileName_Should_ThrowStateCensusAnalysisException() {
        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCensus(29, STATECENSUS_CSV_FILE_PATH, stateCensusClassPath);

        } catch (StateCensusAnalysisException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIncorrectDelimeterToStateCensus_Should_ThrowStateCensusAnalysisException() {
        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCensus(29, STATECENSUS_CSV_FILE_PATH, stateCensusClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Wrong Delimeter or Wrong Header", e.getMessage());

        }
    }

    @Test
    public void givenIncorrectHeader_Should_ThrowStateCensusAnalysisException() {
        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCensus(29, STATECENSUS_CSV_FILE_PATH, stateCensusClassPath);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Wrong Delimeter or Wrong Header", e.getMessage());

        }
    }


}

