package com.bridgelabz.indianstate;

import com.opencsv.bean.CsvBindByName;

public class StateCodePOJO {
    @CsvBindByName(column = "SrNo")
    private String SrNo;

    @CsvBindByName(column = "StateName")
    private String State;


    @CsvBindByName(column = "TIN")
    private String TIN;

    @CsvBindByName(column = "StateCode")
    private String StateCode;

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getTIN() {
        return TIN;
    }

    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

    public String getStateCode() {
        return StateCode;
    }

    public void setStateCode(String stateCode) {
        StateCode = stateCode;
    }

    @Override
    public String toString() {
        return "StateCodePOJO{" +
                "SrNo='" + SrNo + '\'' +
                ", State='" + State + '\'' +
                ", TIN='" + TIN + '\'' +
                ", StateCode='" + StateCode + '\'' +
                '}';
    }
}
