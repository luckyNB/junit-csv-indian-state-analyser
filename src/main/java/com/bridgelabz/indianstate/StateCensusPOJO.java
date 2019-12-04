package com.bridgelabz.indianstate;

import com.opencsv.bean.CsvBindByName;

public class StateCensusPOJO {
    @CsvBindByName(column = "StateName" )
    private String StateName;

    @CsvBindByName(column = "Population")
    private double Population;

    @CsvBindByName(column = "AreaInSqKm")
    private double AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    private double DensityPerSqKm;


    public double getPopulation() {
        return Population;
    }

    public void setPopulation(double population) {
        Population = population;
    }

    public double getAreaInSqKm() {
        return AreaInSqKm;
    }

    public void setAreaInSqKm(double areaInSqKm) {
        AreaInSqKm = areaInSqKm;
    }

    public double getDensityPerSqKm() {
        return DensityPerSqKm;
    }

    public void setDensityPerSqKm(double densityPerSqKm) {
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
