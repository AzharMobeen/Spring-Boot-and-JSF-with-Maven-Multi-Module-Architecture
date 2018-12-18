package com.az.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@ToString
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String name;
    private int classification;
    private boolean isOpen;
    protected Hotel(){

    }
    public Hotel(String name,int classification,boolean isOpen){
        this.name = name;
        this.classification = classification;
        this.isOpen = isOpen;
    }
}
