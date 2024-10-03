package com.denniseckerskorn.tema04ejercicio02.models;

import java.util.Objects;

public class Country {
    private final String countryCode;
    private final String countryName;
    private final long population;
    private final String capital;
    private final String isoAlpha3;
    private final int flagResource;

    public Country(String countryCode, String countryName, long population, String capital, String isoAlpha3, int flagResource) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.population = population;
        this.capital = capital;
        this.isoAlpha3 = isoAlpha3;
        this.flagResource = flagResource;
    }

    public String getCountryCode() {
        return countryCode;
    }


    public String getCountryName() {
        return countryName;
    }

    public long getPopulation() {
        return population;
    }

    public String getCapital() {
        return capital;
    }

    public String getIsoAlpha3() {
        return isoAlpha3;
    }

    public int getFlagResource() {
        return flagResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return population == country.population && Objects.equals(countryCode, country.countryCode) && Objects.equals(countryName, country.countryName) && Objects.equals(capital, country.capital) && Objects.equals(isoAlpha3, country.isoAlpha3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, countryName, population, capital, isoAlpha3);
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", population=" + population +
                ", capital='" + capital + '\'' +
                ", isoAlpha3='" + isoAlpha3 + '\'' +
                '}';
    }
}
