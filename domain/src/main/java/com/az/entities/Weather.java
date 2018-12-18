package com.az.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name="forecasts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Weather {

    @XmlElement(name="forecast")
	private List<Forecast> forecastList;
}
	