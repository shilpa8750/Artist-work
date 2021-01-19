package com.shilpa.mycovidapplication;

public class CountryModel {

    private String flag,country,cases,totalCases,deaths,totalDeaths,active,recovered,critical;

    public CountryModel() {
    }

    public CountryModel(String flag, String country, String cases, String totalCases, String deaths, String totalDeaths, String active, String recovered, String critical) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.totalCases = totalCases;
        this.deaths = deaths;
        this.totalDeaths = totalDeaths;
        this.active = active;
        this.recovered = recovered;
        this.critical = critical;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }
}
