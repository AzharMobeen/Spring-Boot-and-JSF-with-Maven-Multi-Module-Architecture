package com.az.entities;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Data
@Entity
@XmlAccessorType(XmlAccessType.NONE)
public class ForecastDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@XmlElement
    private String phenomenon;

	@XmlElement
    private int tempmax;

	@XmlElement
    private int tempmin;

    @OneToMany(mappedBy = "forecastDetail",cascade=CascadeType.ALL, orphanRemoval= true)
	@XmlElement(name="wind")
    private List<Wind> windList;

	@XmlElement
    @Column(length = 1024)
    private String peipsi;

	@XmlElement
    @Column(length = 1024)
    private String text;

	@XmlElement
    @Column(length = 1024)
    private String sea;

	/*@Transient*/
    @OneToMany(mappedBy="forecastDetail" ,cascade=CascadeType.ALL, orphanRemoval= true)
	@XmlElement(name="place")
    private List<Place> placeList;

    @ManyToOne
    @JoinColumn(name = "forecast_id", referencedColumnName = "id")
    private Forecast forecast;

    @Column(name = "day_Night")
    private String dayOrNight;
    
    public Place addPlace(Place place) {
		this.placeList.add(place);
		place.setForecastDetail(this);
		return place;
	}	
    
}