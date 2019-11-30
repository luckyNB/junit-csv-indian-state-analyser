package com.bridgelabz.indianstate;

import org.junit.Assert;
import org.junit.Test;

public class JunitStateCodeAndCensusTestClass {
    @Test
    public void givenStateCodeCSVData_Should_ReturnSizeOfRecord()   {
        String result = StateCodeAndCensusAnalyser.totalRecordAvailableInStateCode(37);
        Assert.assertEquals("HAPPY", result);
    }
}
