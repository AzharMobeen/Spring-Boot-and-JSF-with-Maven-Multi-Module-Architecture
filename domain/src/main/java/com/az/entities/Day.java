package com.az.entities;

import lombok.Data;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class Day
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@XmlElement
    private String phenomenon;

	@XmlElement
    private String tempmax;

	@XmlElement
    private String tempmin;

    //@OneToMany(mappedBy = "day",cascade=CascadeType.ALL, orphanRemoval= true)
	@XmlElement(name="wind")
    private List<Wind> windList;

	@XmlElement
    private String peipsi;

	@XmlElement
    private String text;

	@XmlElement
    private String sea;

    //@OneToMany(mappedBy="day" ,cascade=CascadeType.ALL, orphanRemoval= true)
	@XmlElement(name="place")
    private List<Place> placeList;

    @OneToOne
    @JoinColumn(name = "forecast_id")
    private Forecast forecast;
}