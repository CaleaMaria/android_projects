package com.example.tema_quiz_19;

public class User {
    private String firstName;
    private String lastName;
    private String birthdate;
    private String profile;

    // Constructor
    public User(String firstName, String lastName, String birthdate, String profile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.profile = profile;
    }

    // Getters și Setters
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
