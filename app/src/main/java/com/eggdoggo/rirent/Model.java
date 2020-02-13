package com.eggdoggo.rirent;

/*
* Model class contains default constructor, getters and setters for values
* used in the app.
*/

public class Model {
    String id, rent, ristan, electricity, internet, stairs, total, totalExpenses, totalPerson , numPeople, date, addTimeStamp, updateTimeStamp;

    public Model(String id, String rent, String ristan, String electricity, String internet,
                 String stairs, String total, String totalExpenses, String totalPerson, String numPeople, String date, String addTimeStamp, String updateTimeStamp) {
        this.id = id;
        this.rent = rent;
        this.ristan = ristan;
        this.electricity = electricity;
        this.internet = internet;
        this.stairs = stairs;
        this.total = total;
        this.totalExpenses = totalExpenses;
        this.totalPerson = totalPerson;
        this.numPeople = numPeople;
        this.date = date;
        this.addTimeStamp = addTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getRent() {
        return rent;
    }
    public void setRent(String rent) {
        this.rent = rent;
    }


    public String getRistan() {return ristan; }
    public void setRistan(String ristan) {
        this.ristan = ristan;
    }


    public String getElectricity() {
        return electricity;
    }
    public void setElectricity(String electricity) { this.electricity = electricity; }


    public String getInternet() { return internet;}
    public void setInternet(String internet) { this.internet = internet; }


    public String getStairs() { return stairs; }
    public void setStairs(String stairs) { this.stairs = stairs; }


    public String getTotal(){ return total; }
    public void setTotal(String total){this.total = total;}


    public String getTotalExpenses(){ return totalExpenses; }
    public void setTotalExpenses(String totalExpenses) { this.totalExpenses = totalExpenses;}


    public String getTotalPerson(){ return totalPerson; }
    public void setTotalPerson(String totalPerson){ this.totalPerson = totalPerson; }


    public String getNumPeople(){ return numPeople; }
    public void setNumPeople(String numPeople) { this.numPeople=numPeople; }


    public String getDate(){ return date; }
    public void setDate(String date){ this.date = date; }


    public String getAddTimeStamp() { return addTimeStamp; }
    public void setAddTimeStamp(String addTimeStamp) { this.addTimeStamp = addTimeStamp; }


    public String getUpdateTimeStamp() { return updateTimeStamp;}
    public void setUpdateTimeStamp(String updateTimeStamp) {this.updateTimeStamp = updateTimeStamp;}
}
