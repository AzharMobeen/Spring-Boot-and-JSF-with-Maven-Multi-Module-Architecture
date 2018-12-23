package com.az.entities;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Data
@Entity
@XmlAccessorType(XmlAccessType.NONE)
public class Forecast {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Transient
	@XmlElement(name="night")
	private ForecastDetail night;

	@Transient
	@XmlElement(name="day")
	private ForecastDetail day;

	@XmlAttribute(name="date")
	private String date;

	// I'm using this only for frontend view.
	@Transient
	private String dayOrNightTitle;

	@OneToMany(mappedBy = "forecast",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ForecastDetail> forecastDetailList;

	public ForecastDetail addForecastDetail(ForecastDetail forecastDetail) {
		this.forecastDetailList.add(forecastDetail);
		forecastDetail.setForecast(this);
		return forecastDetail;
	}	
}
