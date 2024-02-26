package com.example.pruebatec.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
public class Phone {
    private int number;
    private int citycode;
    private int contrycode;
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCitycode() {
        return citycode;
    }

    public void setCitycode(int citycode) {
        this.citycode = citycode;
    }

    public int getContrycode() {
        return contrycode;
    }

    public void setContrycode(int contrycode) {
        this.contrycode = contrycode;
    }



}
