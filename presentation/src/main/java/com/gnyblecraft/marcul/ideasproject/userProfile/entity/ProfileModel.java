package com.gnyblecraft.marcul.ideasproject.userProfile.entity;

import android.app.Activity;


/**
 * Created by lenovo on 12.10.2017.
 */

public class ProfileModel  {
    Activity activity;
    private static ProfileModel instance;
    public static synchronized ProfileModel getInstance(int action){ //передать 0 для обнуления данных
        if(action==0){
            instance = new ProfileModel();
        }

        return instance;
    }

    private String name;
    private String surname;
    private String city;
    private String country;
    private String address;
    private String phone;
    private String email;
    private String secondEmail;
    private long birthDate;
    private String details;
    private String pictureUrl;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname =surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city =city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country =country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email =email;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public void setSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details =details;
    }

}
