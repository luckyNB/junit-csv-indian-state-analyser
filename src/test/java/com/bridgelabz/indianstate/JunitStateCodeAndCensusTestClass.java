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
            Assert.assertEquals("HAPPY", result);
        } catch (StateCensusAnalysisException e) {

            Assert.assertEquals("Please Enter valid File Name", e.getMessage());
            e.printStackTrace();
        }

    }
}
