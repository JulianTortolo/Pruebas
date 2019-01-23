package org.apache.maven.model;

import org.apache.maven.domain.Tasks.City;

import java.util.Date;

public class UserModel {
    private int id;
    private String firstName;
    private String lastName;
    private City city;
    private Date birthDate;

    public UserModel(int id, String firstName, String lastName, City city){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public UserModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
