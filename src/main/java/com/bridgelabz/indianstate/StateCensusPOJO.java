package com.bridgelabz.indianstate;

import com.opencsv.bean.CsvBindByName;

public class StateCensusPOJO {
    @CsvBindByName(column = "StateName" )
    private String StateName;

    @CsvBindByName(column = "Population")
    private String Population;

    @CsvBindByName(column = "AreaInSqKm")
    private String AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    private String DensityPerSqKm;

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

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    @Override
    public String toString() {
        return "StateCensusPOJO{" +
                "StateName='" + StateName + '\'' +
                ", Population='" + Population + '\'' +
                ", AreaInSqKm='" + AreaInSqKm + '\'' +
                ", DensityPerSqKm='" + DensityPerSqKm + '\'' +
                '}';
    }
}
