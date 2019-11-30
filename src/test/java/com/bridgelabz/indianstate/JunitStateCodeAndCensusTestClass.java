package com.bridgelabz.indianstate;

import org.junit.Assert;
import org.junit.Test;

public class JunitStateCodeAndCensusTestClass {
    @Test
    public void givenStateCodeCSVData_Should_ReturnSizeOfRecord() throws StateCensusAnalysisException {
        String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37);
        Assert.assertEquals("HAPPY", result);
    }

    @Test
    public void givenIncorrectFileName_Should_ThrowStateCensusAnalysisException() {
        String result = null;
        try {
            result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Please Enter valid File Name or File Type", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenIncorrectFileType_Should_ThrowStateCensusAnalysisException() {
        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Please Enter valid File Name or File Type", e.getMessage());
        }

    }

    @Test
    public void givenIncorrectDelimeter_Should_ThrowStateCensusAnalysisException() {

        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Wrong Delimeter or Wrong Header", e.getMessage());
        }

    }

    @Test
    public void givenIncorrectHeader_Should_ThroStateCensusAnalysisException() {
        try {
            String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37);
        } catch (StateCensusAnalysisException e) {
            Assert.assertEquals("Wrong Delimeter or Wrong Header", e.getMessage());
        }
    }

    @Test
    public void givenStateCensusCSVData_Should_ReturnSizeOfRecord() throws StateCensusAnalysisException {
        String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCensus(29);
        Assert.assertEquals("HAPPY",result);
    }

    @Test
    public void givenIncorrectFileNameToCensusCSVFile_Should_ThrowStateCensusAnalysisException() {
        try {
            String result=StateCodeAndCensusAnalyser.totalRecordAvailableInStateCensus(29);

        } catch (StateCensusAnalysisException e) {
            e.printStackTrace();
        }
    }
}
