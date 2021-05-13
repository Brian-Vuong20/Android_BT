package com.example.nationapp;

public class Country {
    public String countryName;

    public long population;

    public double area;

    public String flag;
    public Country(String countryName, long population, double area, String flag) {
        this.countryName = countryName;
        this.population = population;
        this.area = area;
        this.flag = flag;
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


}