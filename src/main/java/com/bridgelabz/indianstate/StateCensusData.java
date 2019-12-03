package com.bridgelabz.indianstate;

import com.opencsv.bean.CsvBindByName;

public class StateCensusData {
    @CsvBindByName(column = "SrNo")
    private String SrNo;

    @CsvBindByName(column = "StateName")
    private String State;


    @CsvBindByName(column = "TIN")
    private String TIN;

    @CsvBindByName(column = "StateCode")
    private String StateCode;


    @CsvBindByName(column = "Population")
    private String Population;

    @CsvBindByName(column = "AreaInSqKm")
    private String AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    private String DensityPerSqKm;


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

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public String getAreaInSqKm() {
        return AreaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        AreaInSqKm = areaInSqKm;
    }

    public String getDensityPerSqKm() {
        return DensityPerSqKm;
    }

    public void setDensityPerSqKm(String densityPerSqKm) {
        DensityPerSqKm = densityPerSqKm;
    }

    @Override
    public String toString() {
        return "StateCensusData{" +
                "SrNo='" + SrNo + '\'' +
                ", State='" + State + '\'' +
                ", TIN='" + TIN + '\'' +
                ", StateCode='" + StateCode + '\'' +
                ", Population='" + Population + '\'' +
                ", AreaInSqKm='" + AreaInSqKm + '\'' +
                ", DensityPerSqKm='" + DensityPerSqKm + '\'' +
                '}';
    }
}
